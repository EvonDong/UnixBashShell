package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.TeeInterface;
import sg.edu.nus.comp.cs4218.exception.TeeException;
//import sg.edu.nus.comp.cs4218.impl.app.args.TeeArguments;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.IS_DIRECTORY;

public class TeeApplication implements TeeInterface {

    OutputStream stdout;
    private static final String NEW_LINE = System.lineSeparator();
    public static final char CHAR_APPD_OPTION = 'a';
    private final List<String> teeArgFiles = new ArrayList<>();
    private boolean append = false;

    public static final String ILLEGAL_OPT_FLAG = "illegal option -- ";

    /**
     * Runs the tee application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws TeeException If the file(s) specified do not exist or are unreadable.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws TeeException {
        if (args == null) {
            throw new TeeException(ERR_NULL_ARGS);
        }
        if (stdin == null) {
            throw new TeeException(ERR_NO_ISTREAM);
        }

        if (stdout == null) {
            throw new TeeException(ERR_NO_OSTREAM);
        } else {
            this.stdout = stdout;
        }
//        TeeArguments teeArgs = new TeeArguments();
        this.parse(args);
        try {
            teeFromStdin(this.isAppend(), stdin, this.getTeeArgFiles().toArray(new String[0]));
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        }
        catch (Exception e) {
            throw new TeeException(ERR_GENERAL);
        }

    }


    /**
     * Returns string the user's input for the tee command
     *
     * @param isAppend Boolean option to append to end of files
     * @param stdin   InputStream containing arguments from Stdin
     * @param fileName Array of String of file names
     * @throws Exception
     */
    @Override
    public String teeFromStdin(Boolean isAppend, InputStream stdin, String... fileName) throws Exception {
        String wrongFormatMsgs = "";
        if (fileName.length != 0){
            wrongFormatMsgs = checkIfFileIsAccessible(fileName);
            if (!("").equals(wrongFormatMsgs)) {
                System.out.println(wrongFormatMsgs);
            }
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int inRead = 0;
        while ((inRead = stdin.read(data, 0, data.length)) != -1) {
            String line = "";
            for (int i = 0; i < inRead; ++i) {
                char character = (char)data[i];
                line += character;
            }
            // line = line.trim();
            buffer.write(data, 0, inRead);
            writeToStdout(line);
        }
        String content = buffer.toString();
        if (fileName.length == 0){
            buffer.flush();
        }
        else {
            for (String file: fileName) {
                File fileToTee = IOUtils.resolveFilePath(file).toFile();
                boolean createdNew = fileToTee.createNewFile(); // if file already exists will do nothing
                if (fileToTee.isDirectory() || !fileToTee.canRead()) {
                    continue;
                }
                if (fileToTee.isFile() && !fileToTee.canWrite()) {
                    continue;
                }
                if (isAppend) {
                    Files.writeString(fileToTee.toPath(), content, StandardOpenOption.APPEND);
                }
                else {
                    Files.writeString(fileToTee.toPath(), content);
                }
            }
        }
        return wrongFormatMsgs;
    }


    /**
     * Runs the tee application with the specified arguments.
     *
     * @param content   String that is expected to be written out to the outputstream of the program.
     * @throws TeeException If the write operation causes an IOException.
     */
    private void writeToStdout(String content) throws TeeException {
        try {
            stdout.write(content.getBytes());
            //stdout.write(STRING_NEWLINE.getBytes());
        } catch (IOException e) {
            throw new TeeException(ERR_WRITE_STREAM);
        }
    }

    /**
     * Checks if the files specified in the array are directories or files.
     * Returns a string on all files that are directories.
     *
     * @param fileName Array of String of file names
     * @throws TeeException If the files are null.
     */
    public String checkIfFileIsAccessible(String... fileName) throws  Exception{
        List<String> checkStmt = new ArrayList<>();
        if (fileName == null) {
            throw new Exception(ERR_GENERAL);
        }
        for (String file: fileName) {
            File node = IOUtils.resolveFilePath(file).toFile();
            if (node.isDirectory()) {
                String stmtToAdd = "tee: " + file + ": " + IS_DIRECTORY;
                checkStmt.add(String.format(stmtToAdd));
            }
            else if (node.isFile() && !Files.isWritable(node.toPath())) {
                String stmtToAdd = "tee: " + file + ": Permission denied";
                checkStmt.add(String.format(stmtToAdd));
            }
        }
        return  String.join(NEW_LINE, checkStmt);
    }

    /**
     * Handles argument list parsing for the `tee` application.
     *
     * @param args Array of arguments to parse
     */
    public void parse(String... args) throws TeeException{
        boolean parsingFlag = true, isAppend = false;

        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.isEmpty()) {
                    continue;
                }

                if (parsingFlag && arg.charAt(0) == CHAR_FLAG_PREFIX && arg.length() != 1) {
                    for (char c : arg.toCharArray()) {
                        if (c == CHAR_FLAG_PREFIX) {
                            continue;
                        }
                        if (c == CHAR_APPD_OPTION) {
                            isAppend = true;
                            continue;
                        }
                        throw new TeeException(ILLEGAL_OPT_FLAG + c);
                    }
                } else {
                    parsingFlag = false;
                    this.teeArgFiles.add(arg.trim());
                }
            }
        }
        if (isAppend) {
            this.append = isAppend;
        }
    }

    public boolean isAppend() {
        return append;
    }

    public List<String> getTeeArgFiles() {
        return teeArgFiles;
    }
}
