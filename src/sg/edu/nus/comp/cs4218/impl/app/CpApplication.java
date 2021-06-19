package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_GENERAL;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_NOT_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_ARG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_SRC_OR_DEST;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MULTIPLE_TIMES;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_RECURSIVE;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SAME_SOURCE_AND_DEST;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import sg.edu.nus.comp.cs4218.app.CpInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CpException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class CpApplication implements CpInterface {
    private static final String CP_ERR_LITERAL = "cp: ";

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        checkInputs(args, stdout); // throw exception if invalid inputs
        boolean isRecursive = checkIsRecursive(args[0]);
        checkIfEnoughArgument(args, isRecursive); // throw exception if insufficient argument

        String dest = args[args.length-1];
        String[] sources = Arrays.copyOfRange(args, (isRecursive) ? 1 : 0, args.length-1);

        // 2 types of cp command: 1) copy file(folder) into file(folder), or 2) copy files into folder
        boolean isFileFile = checkDestType(sources, dest);
        try {
            String result;
            if (isFileFile) {
                cpSrcFileToDestFile(isRecursive, sources[0], dest);
            } else {
                result = cpFilesToFolder(isRecursive, dest, sources);
                stdout.write(result.getBytes());
            }
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        } catch (Exception e) {
            throw new CpException(e.getMessage());
        }
    }

    @Override
    public String cpSrcFileToDestFile(Boolean isRecursive, String srcFile, String destFile) throws Exception {
        if (srcFile == null || srcFile.equals("") || destFile == null || destFile.equals("")) {
            throw new Exception(ERR_MISSING_ARG);
        }

        Path sourcePath = IOUtils.resolveFilePath(srcFile);
        Path destPath = IOUtils.resolveFilePath(destFile);
        File sourceFile = sourcePath.toFile();
        File destinationFile = destPath.toFile();

        if (!sourceFile.exists()) {
            throw new Exception(ERR_FILE_NOT_FOUND);
        }

        if (sourceFile.isDirectory() && !isRecursive) {
            throw new Exception(ERR_NO_RECURSIVE);
        }

        if (sourcePath.toString().equals(destPath.toString())) {
            throw new Exception(ERR_SAME_SOURCE_AND_DEST);
        }

        if (sourceFile.isFile()) {
            copyFile(sourcePath, destPath);
        } else if (destinationFile.exists()) {
            copyDirectory(sourcePath, destPath.resolve(sourceFile.getName()));
        } else {
            copyDirectory(sourcePath, destPath);
        }

        return "";
    }

    @Override
    public String cpFilesToFolder(Boolean isRecursive, String destFolder, String... fileName) throws Exception {
        if (destFolder == null || destFolder.equals("") || fileName == null || fileName.length == 0 || fileName[0].equals("")) {
            throw new Exception(ERR_MISSING_ARG);
        }

        Path[] sourcePaths = Arrays.stream(fileName).map(IOUtils::resolveFilePath).toArray(Path[]::new);
        Path destPath = IOUtils.resolveFilePath(destFolder);

        if (!destPath.toFile().exists() || !destPath.toFile().isDirectory()) {
            throw new Exception(ERR_IS_NOT_DIR);
        }

        StringBuilder outputBuilder = new StringBuilder();
        Set<String> processed = new HashSet<>();

        for (Path sourcePath : sourcePaths) {
            if (!sourcePath.toFile().exists()) {
                outputBuilder.append(CP_ERR_LITERAL).append(ERR_FILE_NOT_FOUND).append(STRING_NEWLINE);
                continue;
            }

            boolean isDirectory = sourcePath.toFile().isDirectory();
            if (isDirectory && !isRecursive) {
                outputBuilder.append(CP_ERR_LITERAL).append(ERR_NO_RECURSIVE).append(STRING_NEWLINE);
                continue;
            }

            if (sourcePath.getFileName().equals(destPath.getFileName())) {
                outputBuilder.append(CP_ERR_LITERAL).append(ERR_SAME_SOURCE_AND_DEST).append(STRING_NEWLINE);
                continue;
            }

            String canonicalPath = sourcePath.toFile().getCanonicalPath();
            if (processed.contains(canonicalPath)) {
                outputBuilder.append(CP_ERR_LITERAL).append(ERR_MULTIPLE_TIMES).append(STRING_NEWLINE);
                continue;
            }

            Path destinationPath = destPath.resolve(sourcePath.getFileName());
            if (isDirectory) {
                copyDirectory(sourcePath, destinationPath);
            } else {
                copyFile(sourcePath, destinationPath);
            }
            processed.add(canonicalPath);
        }

        return outputBuilder.toString();
    }

    private void checkInputs(String[] args, OutputStream stdout) throws CpException {
        if (args == null) {
            throw new CpException(ERR_NULL_ARGS);
        }

        if (args.length == 0) {
            throw new CpException(ERR_MISSING_SRC_OR_DEST);
        }

        if (stdout == null) {
            throw new CpException(ERR_NO_OSTREAM);
        }
    }

    private boolean checkIsRecursive(String option) {
        return "-r".equals(option) || "-R".equals(option);
    }

    private void checkIfEnoughArgument(String[] args, boolean isRecursive) throws CpException {
        if (isRecursive && args.length <= 2 || !isRecursive && args.length <= 1 ) {
            throw new CpException(ERR_MISSING_SRC_OR_DEST);
        }
    }

    private boolean doesNotExist(File file) {
        return !file.exists();
    }

    private boolean checkDestType(String[] sources, String dest) throws CpException {
        File[] srcFiles = Arrays.stream(sources).map(src -> IOUtils.resolveFilePath(src).toFile()).toArray(File[]::new);
        File destFile = IOUtils.resolveFilePath(dest).toFile();

        if (srcFiles.length > 1) {
            return false;
        } else if (doesNotExist(srcFiles[0])) {
            throw new CpException(ERR_FILE_NOT_FOUND);
        } else if (srcFiles[0].isDirectory()) {
            return true;
        } else if (doesNotExist(destFile)) {
            return true;
        } else {
            return !destFile.isDirectory();
        }
    }

    public void copyDirectory(Path source, Path dest) throws CpException {
        try (Stream<Path> stream = Files.walk(source)) {
            stream.forEach(src -> copyFile(src, dest.resolve(source.relativize(src))));
        } catch (IOException e) {
            throw new CpException(ERR_IO_EXCEPTION);
        }
    }

    private void copyFile(Path source, Path dest) {
        try {
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(ERR_GENERAL); // shouldn't happen
        }
    }
}