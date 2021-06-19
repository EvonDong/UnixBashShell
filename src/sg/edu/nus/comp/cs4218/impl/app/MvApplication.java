package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import sg.edu.nus.comp.cs4218.app.MvInterface;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;



public class MvApplication implements MvInterface {

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws MvException {
        if (args == null) {
            throw new MvException(ERR_NULL_ARGS);
        } else if (stdout == null) {
            throw new MvException(ERR_NO_OSTREAM);
        }
        boolean isOverwrite = true;
        MvArgsParser parser = new MvArgsParser();
        String[] sourceArr;
        try {
            if(args.length <= 1) {
                throw new InvalidArgsException(ErrorConstants.ERR_MISSING_ARG);
            }
            parser.parse(args);
            sourceArr = parser.getSourceOperands();
            if(sourceArr.length < 1) {
                throw new InvalidArgsException(ErrorConstants.ERR_MISSING_ARG);
            }
            for(String src: sourceArr) {
                String path = String.valueOf(IOUtils.resolveFilePath(src));
                File temp = new File(path);
                if(!temp.exists()) {
                    throw new Exception(path + " not found. " + ERR_FILE_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            throw new MvException(e.getMessage());
        }
        sourceArr = parser.getSourceOperands();
        String targetDirOrFile = parser.getTargetFolderOrFile();
        isOverwrite = parser.isOverwrite();

        try {
            File temp = new File(String.valueOf(IOUtils.resolveFilePath(targetDirOrFile)));
            if(temp.exists() && temp.isDirectory()) {
                mvFilesToFolder(isOverwrite, targetDirOrFile, sourceArr);
            } else {
                if(sourceArr.length > 1) {
                    throw new InvalidArgsException(ErrorConstants.ERR_TOO_MANY_ARGS);
                } else if (temp.exists() && !isOverwrite) {
                    throw new Exception("Target filename " + targetDirOrFile + " already exists.");
                } else {
                    mvSrcFileToDestFile(isOverwrite, sourceArr[0], targetDirOrFile);
                }
            }
        } catch (Exception e) {
            throw new MvException(e.getMessage());
        }
    }

    @Override
    public String mvSrcFileToDestFile(Boolean isOverwrite, String srcFile, String destFile) throws MvException {
        Path source;
        Path dest;
        try {
            source = IOUtils.resolveFilePath(srcFile);
            dest = IOUtils.resolveFilePath(destFile);
//            if(new File(String.valueOf(source)).exists() && Files.isDirectory(source)) {
//                throw new Exception("rename " + srcFile + " to " + destFile + ": Not a directory");
//            }
            if(isOverwrite) {
                Files.move(source, dest, REPLACE_EXISTING);
            } else {
                Files.move(source, dest);
            }
        } catch (Exception e) {
            throw new MvException(e.getMessage());
        }

        return destFile;
    }

    @Override
    public String mvFilesToFolder(Boolean isOverwrite, String destFolder, String... fileName) throws Exception {
        Path finalDest = null;

        for (String src : fileName) {
            try {
                Path source;
                Path dest;
                source = IOUtils.resolveFilePath(src);
                dest = IOUtils.resolveFilePath(destFolder);
                String sourceFile = String.valueOf(source.getFileName());
                finalDest = Paths.get(String.valueOf(dest), sourceFile);
                if (isOverwrite) {
                    Files.move(source, finalDest, REPLACE_EXISTING);
                } else {
                    Files.move(source, finalDest);
                }
            } catch (Exception e) {
                throw new Exception("Error: Unable to move file");
            }
        }
        return String.valueOf(finalDest);
    }

}
