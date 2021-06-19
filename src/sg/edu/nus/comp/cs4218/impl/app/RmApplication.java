package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.RmInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.RmException;
import sg.edu.nus.comp.cs4218.impl.app.args.RmArguments;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class RmApplication implements RmInterface {

    OutputStream stdout;

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null) {
            throw new RmException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new RmException(ERR_NO_OSTREAM);
        } else {
            this.stdout = stdout;
        }

        RmArguments rmArgs = new RmArguments();
        rmArgs.parse(args);


        try {
            stdin.available(); // check stdin status
            remove(rmArgs.isEmptyFolder(), rmArgs.isRecursive(), rmArgs.getFiles().toArray(new String[0]));
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        } catch (Exception e) {
            throw new RmException(e.getMessage());
        }
    }

    /**
     * Removes the given files and directories with respect to the flags given.
     *
     * @param isEmptyFolder Boolean option to remove empty directories
     * @param isRecursive Boolean option to recursively remove directories
     * @param fileName Array of String of file names
     * @throws Exception
     */
    @Override
    public void remove(Boolean isEmptyFolder, Boolean isRecursive, String... fileName) throws Exception {
        if (isRecursive == null || isEmptyFolder == null) {
            throw new RmException("Null flags detected");
        }
        if (fileName == null) {
            throw new Exception(ERR_GENERAL);
        }
        if (fileName.length==0) {
            System.out.println("rm: missing operand");
            return;
        }
        for (String file: fileName) {
            File fileToRm = IOUtils.resolveFilePath(file).toFile();
            if (!fileToRm.exists()){
                System.out.println("rm: cannot remove '" + file + "': No such file or directory");
                continue;
            }
            if (!isEmptyFolder && !isRecursive) {
                if (fileToRm.isDirectory()) {
                    System.out.println("rm: cannot remove '" + file + "': Is a directory");
                } else {
                    fileToRm.delete();
                }
                continue;
            }
            if (isRecursive) {
                if (fileToRm.isDirectory()) {
                    recursiveDeleteDirectory(fileToRm);
                } else {
                    fileToRm.delete();
                }
                continue;
            }
            if (isEmptyFolder) {
                // if -d check if file is file or dir if dir check if empty
                if (fileToRm.isDirectory()) {
                    File[] allContent = fileToRm.listFiles();
                    if (allContent.length == 0) {
                        fileToRm.delete();
                    } else {
                        System.out.println("rm: cannot remove '" + file + "': Directory not empty");
                    }
                } else {
                    fileToRm.delete();
                }
            continue;
            }
        }
    }

    /**
     * Recursively deletes all contents inside a given directory.
     *
     * @param dirToDelete File containing the directory to be recursively deleted.
     * @throws Exception
     */
    boolean recursiveDeleteDirectory(File dirToDelete) {
        File[] allDirContents = dirToDelete.listFiles();
        if (allDirContents != null) {
            for (File content : allDirContents) {
                recursiveDeleteDirectory(content);
            }
        }
        return dirToDelete.delete();
    }
}
