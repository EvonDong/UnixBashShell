package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class CdApplication implements CdInterface {

    public static final String ERR_MULTIPLE_ARGS = "Too many arguments";

    @Override
    public void changeToDirectory(String path) throws CdException {
        Environment.currentDirectory = getNormalizedAbsolutePath(path);
    }

    /**
     * Runs the cd application with the specified arguments.
     * Assumption: The application must take in one arg. (cd without args is not supported)
     *
     * @param args   Array of arguments for the application.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     * @throws CdException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws CdException {
        if (args == null) {
            throw new CdException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new CdException(ERR_NO_OSTREAM);
        }
        if (stdin == null) {
            throw new CdException(ERR_NO_ISTREAM);
        }
        if (args.length == 0) {
            return;
        }
        if (args.length > 1) {
            throw new CdException(ERR_MULTIPLE_ARGS);
        }
        changeToDirectory(args[0]);

        try {
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        } catch (IOException e) {
            throw new CdException(ERR_GENERAL);
        }
    }

    public String getNormalizedAbsolutePath(String pathStr) throws CdException {
        if (pathStr == null || StringUtils.isBlank(pathStr)) {
            throw new CdException(ERR_NO_ARGS);
        }

        Path path = Paths.get(pathStr);

        if (!path.isAbsolute()) {
            path = Paths.get(Environment.currentDirectory, pathStr);
        }

        // command `cd src/efqjkfnqe/..` shouldn't work, as 'efqjkfnqe' does not exist
        if (isTerminatedWithTwoDots(path) && !Files.exists(removeTwoDots(path))) {
            throw new CdException(String.format(ERR_FILE_NOT_FOUND, pathStr));
        }

        if (!Files.exists(path)) {
            throw new CdException(String.format(ERR_FILE_NOT_FOUND, pathStr));
        }

        if (!Files.isDirectory(path)) {
            throw new CdException(String.format(ERR_IS_NOT_DIR, pathStr));
        }

        return path.normalize().toString();
    }

    private boolean isTerminatedWithTwoDots(Path path) {
        String pathName = path.toString().trim();
        int pathLength = pathName.length();
        return pathLength > 3 && pathName.charAt(pathLength-2) == '.' && pathName.charAt(pathLength-1) == '.';
    }

    private Path removeTwoDots(Path path) {
        String pathName = path.toString().trim();
        String subpath = pathName.substring(0, pathName.length()-2);
        return Paths.get(subpath);
    }
}
