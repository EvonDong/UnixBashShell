package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_PERM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import sg.edu.nus.comp.cs4218.app.CatInterface;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.app.args.CatArguments;
import sg.edu.nus.comp.cs4218.impl.app.args.CatArguments.InputType;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

public class CatApplication implements CatInterface {
    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_GENERAL = "Exception Caught";


    private int lineNum = 1; // to be used when -n is turned on
    public OutputStream stdout;

    /**
     * Runs the cat application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws CatException If the file(s) specified do not exist or are unreadable.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws CatException {
        // TODO: To implement

        // Format: cat [-n] [FILES]…
        if (stdout == null) {
            throw new CatException(ERR_NULL_STREAMS);
        } else {
            this.stdout = stdout;
        }

        CatArguments catArgs = new CatArguments();
        catArgs.parse(args);

        String result;
        try {
            if (catArgs.getInputType() == InputType.FILE_ONLY) {
                result = catFiles(catArgs.isDisplayLineNum(), catArgs.getInputs().toArray(new String[0]));
            } else if (catArgs.getInputType() == InputType.STDIN_ONLY) {
                result = catStdin(catArgs.isDisplayLineNum(), stdin);
            } else {
                result = catFileAndStdin(catArgs.isDisplayLineNum(), stdin, catArgs.getInputs().toArray(new String[0]));
            }
        } catch (Exception e) {
            throw new CatException(ERR_GENERAL); // Shouldn't happen
        }

        writeToStdout(result);
        try {
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
            if (!stdin.equals(System.in)) {
                stdin.close();
            }
        } catch (IOException e) {
            throw new CatException(ERR_IO_EXCEPTION);
        }
    }

    private void writeToStdout(String content) throws CatException {
        try {
            stdout.write(content.getBytes());
        } catch (IOException e) {
            throw new CatException(ERR_WRITE_STREAM);
        }
    }

    @Override
    public String catFiles(Boolean isLineNumber, String... fileName) throws Exception {
        if (fileName == null) {
            throw new Exception(ERR_GENERAL);
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (String file : fileName) {
            File node = IOUtils.resolveFilePath(file).toFile();

            if (!node.exists()) {
                stringBuilder.append("cat: ").append(ERR_FILE_NOT_FOUND).append(STRING_NEWLINE);
                continue;
            }
            if (node.isDirectory()) {
                stringBuilder.append("cat: " + ERR_IS_DIR).append(STRING_NEWLINE);
                continue;
            }
            if (!node.canRead()) {
                stringBuilder.append("cat: ").append(ERR_NO_PERM).append(STRING_NEWLINE);
                continue;
            }

            InputStream input = IOUtils.openInputStream(file);
            String content = readInput(input, isLineNumber);
            stringBuilder.append(content);
            input.close();
        }

        return stringBuilder.toString();
    }

    public String readInput(InputStream input, boolean isLineNumber) throws Exception {
        Scanner scanner = new Scanner(input);
        StringBuilder stringBuilder = new StringBuilder();

        while(scanner.hasNextLine()) {
            if (isLineNumber) {
                stringBuilder.append(this.lineNum++).append(String.valueOf(" "));
            }
            stringBuilder.append(scanner.nextLine()).append(STRING_NEWLINE);
        }
        scanner.close();
        return stringBuilder.toString();
    }

    @Override
    public String catStdin(Boolean isLineNumber, InputStream stdin) throws Exception {
        if (stdin == null) {
            throw new Exception(ERR_NULL_STREAMS);
        }

        Scanner scanner = new Scanner(stdin);
        StringBuilder stringBuilder = new StringBuilder();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (isLineNumber) {
                line = this.lineNum + " " + line;
                this.lineNum++;
            }

            if (this.stdout.equals(System.out)) {
                writeToStdout(line + System.lineSeparator());
            } else {
                stringBuilder.append(line).append(STRING_NEWLINE);
            }
        }
        return stringBuilder.toString(); // if print to screen, empty string
    }

    @Override
    public String catFileAndStdin(Boolean isLineNumber, InputStream stdin, String... fileName) throws Exception {
        if (fileName == null) {
            throw new Exception(ERR_GENERAL);
        }

        if (stdin == null) {
            throw new Exception(ERR_NULL_STREAMS);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (String file : fileName) {
            String content;
            if (("-").equals(file)) {
                content = catStdin(isLineNumber, stdin);
            } else {
                content = catFiles(isLineNumber, file);
            }
            if (this.stdout.equals(System.out)) {
                writeToStdout(content);
            } else {
                stringBuilder.append(content);
            }
        }

        return stringBuilder.toString();
    }
}
