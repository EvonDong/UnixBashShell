package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_INPUT;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ISTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.convertToAbsolutePath;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

import sg.edu.nus.comp.cs4218.app.SplitInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SplitException;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.convertToAbsolutePath;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;



public class SplitApplication implements SplitInterface{

    public static final String IS_DIRECTORY = "Is a directory";
    public static final String MISSING_ARGUMENT = "option requires an argument";
    public static final String INVALID_L_COUNT = "invalid number of lines:";
    public static final String INVALID_POSTFIX = "Invalid bytes postfix";
    public static final String INVALID_B_COUNT = "invalid number of bytes:";

    private static final int NUM_ARGUMENTS = 2;
    private static final char SPLIT_LINES = 'l';
    private static final char SPLIT_BYTES = 'b';
    private static final int LINES_IDX = 0;
    private static final int BYTES_IDX = 1;
    private static final char PRIMARY_PREFIX = 'x';
    private static final char SECONDARY_PREFIX = 'z';
    private static final String BYTES_POSTFIX = "bkm";
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        try {
            boolean[] splitFlags = new boolean[NUM_ARGUMENTS];
            String[] splitArgs = new String[3];
            getSplitArguments(args, splitFlags, splitArgs);
            String factor = splitArgs[0];
            String fileName = splitArgs[1];
            String prefix = splitArgs[2];
            if (stdin == null && fileName == null) {
                throw new SplitException(ERR_NO_INPUT);
            }
            if (stdin == null) {
                throw new SplitException(ERR_NO_ISTREAM);
            }
            if (stdout == null) {
                throw new SplitException(ERR_NO_OSTREAM);
            }
            if (fileName == null || fileName.isEmpty() || fileName.trim().equals("-")) {
                if (splitFlags[BYTES_IDX]) {
                    splitStdinByBytes(stdin, prefix, factor);
                } else { // default split by lines
                    int linesPerFile = 1000;
                    if (splitFlags[LINES_IDX]) {
                        linesPerFile = checkIfValidLineCount(factor);
                    }
                    splitStdinByLines(stdin, prefix, linesPerFile);
                }
            } else {
                if (splitFlags[BYTES_IDX]) {
                    splitFileByBytes(fileName, prefix, factor);
                } else { // default split by lines
                    int linesPerFile = 1000;
                    if (splitFlags[LINES_IDX]) {
                        linesPerFile = checkIfValidLineCount(factor);
                    }
                    splitFileByLines(fileName, prefix, linesPerFile);
                }
            }
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        } catch (SplitException splitException) {
            throw splitException;
        } catch (NullPointerException npe) {
            throw new SplitException("Null arguments");
        } catch (Exception e) {
            throw new SplitException(e.getMessage());
        }
    }

    private Integer checkIfValidLineCount(String factor) throws Exception {
        int result = 0;
        try {
            result = Integer.parseInt(factor);
            if (result <= 0) {
                throw new SplitException(INVALID_L_COUNT + " " + "'" + factor + "'");
            }
            return result;
        } catch (Exception e) {
            throw new SplitException (INVALID_L_COUNT + " " + "'" + factor + "'");
        }
    }

    /**
     * Separates the arguments provided by user in into flags, filename and prefix.
     *
     * @param args supplied by user
     * @param splitFlags a bool array of possible flags in split
     * @param splitArgs a String array of arguments after splitting
     */
    private void getSplitArguments(String[] args, boolean[] splitFlags, String... splitArgs) throws Exception {
        boolean isFlagRead = false;
        boolean isFactor = false;
        boolean isFile = true;
        boolean isPrefix = false;
        for (String s : args) {
            char[] arg = s.toCharArray();
            if(!s.isEmpty() && !isFlagRead && arg[0] == CHAR_FLAG_PREFIX && s.length() == 2) { // Only 1 flag allowed
                arg = Arrays.copyOfRange(arg, 1, arg.length);
                for (char c : arg) {
                    switch (c) {
                        case SPLIT_LINES:
                            splitFlags[LINES_IDX] = true;
                            break;
                        case SPLIT_BYTES:
                            splitFlags[BYTES_IDX] = true;
                            break;
                        default:
                            throw new SplitException(ERR_SYNTAX);
                    }
                }
                isFlagRead = true;
                isFactor = true; // Factor should follow flag
            } else if (isFactor) {
                splitArgs[0] = s.trim();
                isFactor = false;
                isFile = true;
            } else if (isFile) {
                isFlagRead = true;
                splitArgs[1] = s.trim();
                isFile = false;
                isPrefix = true;
            } else if (isPrefix) {
                splitArgs[2] = s.trim();
                isPrefix = false;
            }
        }
        if (splitFlags[LINES_IDX] && splitArgs[0] == null) {
            throw new SplitException(MISSING_ARGUMENT + " -- l");
        }
        if (splitFlags[BYTES_IDX] && splitArgs[0] == null) {
            throw new SplitException(MISSING_ARGUMENT + " -- b");
        }
    }

    /**
     * Updates Metadata used for naming files created
     *
     * @param chars a char array maintaining "aa" status of new file
     * @param currPrefix a String indicating curr prefix
     * @return String with appropriate prefix
     */
    private String updateNewFileMetaData(char[] chars, String currPrefix) {
        String updatedPrefix =  currPrefix;
        if (chars[0] == 'z' && chars[1] == 'z') {
            if (currPrefix.equals(String.valueOf(PRIMARY_PREFIX))) {
                updatedPrefix = String.valueOf(SECONDARY_PREFIX);
            } else {
                updatedPrefix = String.valueOf(PRIMARY_PREFIX);
            }
            chars[0] = 'a';
            chars[1] = 'a';
        } else if (chars[1] == 'z') {
            chars[0] += 1;
            chars[1] = 'a';
        } else {
            chars[1] += 1;
        }
        return updatedPrefix;
    }



    private long calculateBytesPerFile(char... bytes) throws SplitException {
        int multiplier = 1;
        int factor;
        try {
            factor = Integer.parseInt(String.valueOf(bytes[0]));
            if (factor == 0) {
                throw new Exception(INVALID_B_COUNT + " '" + new String(bytes) + "'");
            }
        } catch (Exception e) {
            throw new SplitException(INVALID_B_COUNT + " '" + new String(bytes) + "'");
        }
        long computedBytes;
        if (BYTES_POSTFIX.indexOf(bytes[bytes.length - 1]) >= 0) {
            char postfix = bytes[bytes.length - 1];
            switch (postfix) {
                case 'b':
                    multiplier = 512;
                    break;
                case 'k':
                    multiplier = 1024;
                    break;
                case 'm':
                    multiplier = 1048576;
                    break;
                default:
                    throw new SplitException(INVALID_POSTFIX + postfix);
            }
            computedBytes = factor * multiplier;
        } else {
            try {
                computedBytes = (long) Integer.parseInt(new String(bytes, 0, bytes.length)) * multiplier;
            } catch (NumberFormatException e) {
                throw new SplitException(INVALID_B_COUNT + " " + "'" + new String(bytes) + "'");
            }
        }
        return computedBytes;
    }

    private void linesSplitHandler(int linesPerFile, String outputFileName, String filePrefix, char[] chars, BufferedReader reader) throws IOException {
        String prefix;
        String outFileName;
        BufferedWriter writer;
        FileOutputStream fos;
        fos = new FileOutputStream(outputFileName);
        writer = new BufferedWriter(new OutputStreamWriter(fos));
        long lineCount = 0;
        int readChar;
        try {
            while ((readChar = reader.read()) != -1) {
                if (readChar == '\n' || readChar == '\r') {
                    if (readChar == '\n') {
                        if (++lineCount != linesPerFile) {
                            writer.newLine();
                        }
                    } else if (reader.read() == '\n' && ++lineCount != linesPerFile) {
                        writer.newLine();
                    }
                    if (lineCount == linesPerFile) {
                        lineCount = 0;
                        writer.close();
                        prefix = updateNewFileMetaData(chars, filePrefix);
                        outFileName = prefix + chars[0] + chars[1];
                        fos = new FileOutputStream(outFileName);
                        writer = new BufferedWriter(new OutputStreamWriter(fos));
                    }
                } else {
                    writer.write(readChar);
                }
            }
        } finally {
            writer.close();
            fos.close();
        }
    }

    @Override
    public void splitFileByLines(String fileName, String prefix, int linesPerFile) throws Exception {
        long lineCount = 0;
        char[] chars = new char[2];
        Arrays.fill(chars, 'a');
        String filePrefix;
        filePrefix = Objects.requireNonNullElseGet(prefix, () -> String.valueOf(PRIMARY_PREFIX));
        String path = convertToAbsolutePath(fileName);
        String outFileName = filePrefix + chars[0] + chars[1];
        File file = new File(path);
        if (!file.exists()) {
            throw new SplitException(fileName + ": " + ERR_FILE_NOT_FOUND);
        }
        if (file.isDirectory()) {
            throw new SplitException(fileName + ": " + IS_DIRECTORY);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            linesSplitHandler(linesPerFile, outFileName, filePrefix, chars, reader);
        }
    }

    @Override
    public void splitFileByBytes(String fileName, String prefix, String bytesPerFile) throws Exception {
        long bytesCount = 0;
        char[] bytes = bytesPerFile.toCharArray();
        long computedBytes = calculateBytesPerFile(bytes);
        char[] chars = new char[2];
        Arrays.fill(chars, 'a');
        String filePrefix;
        filePrefix = Objects.requireNonNullElseGet(prefix, () -> String.valueOf(PRIMARY_PREFIX));
        BufferedOutputStream writer = null;
        FileOutputStream fos = null;
        try {
            String path = convertToAbsolutePath(fileName);
            String outFileName = filePrefix + chars[0] + chars[1];
            File file = new File(path);
            if (!file.exists()) {
                throw new SplitException(fileName + ": " + ERR_FILE_NOT_FOUND);
            }
            if (file.isDirectory()) {
                throw new SplitException(fileName + ": " + IS_DIRECTORY);
            }
            byte[] data = Files.readAllBytes(Paths.get(path));
            fos = new FileOutputStream(outFileName);
            writer = new BufferedOutputStream(fos);
            for (byte datum : data) {
                if (bytesCount == computedBytes) {
                    bytesCount = 0;
                    writer.close();
                    filePrefix = updateNewFileMetaData(chars, filePrefix);
                    outFileName = filePrefix + chars[0] + chars[1];
                    fos = new FileOutputStream(outFileName);
                    writer = new BufferedOutputStream(fos);
                }
                writer.write(datum);
                bytesCount++;
            }
        } finally {
            if (writer != null){
                writer.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    @Override
    public void splitStdinByLines(InputStream stdin, String prefix, int linesPerFile) throws Exception {
        if (stdin == null) {
            throw new Exception(ERR_NO_ISTREAM);
        }
        char[] chars = new char[2];
        Arrays.fill(chars, 'a');
        String filePrefix;
        filePrefix = Objects.requireNonNullElseGet(prefix, () -> String.valueOf(PRIMARY_PREFIX));
        BufferedReader reader = null;
        try {
            String outFileName = filePrefix + chars[0] + chars[1];
            reader = new BufferedReader(new InputStreamReader(stdin));
            linesSplitHandler(linesPerFile, outFileName, filePrefix, chars, reader);
        } finally {
            reader.close();
        }
    }

    @Override
    public void splitStdinByBytes(InputStream stdin, String prefix, String bytesPerFile) throws Exception {
        if (stdin == null) {
            throw new Exception(ERR_NO_ISTREAM);
        }
        long bytesCount = 0;
        char[] bytes = bytesPerFile.toCharArray();
        long computedBytes = calculateBytesPerFile(bytes);
        char[] chars = new char[2];
        Arrays.fill(chars, 'a');
        String filePrefix;
        filePrefix = Objects.requireNonNullElseGet(prefix, () -> String.valueOf(PRIMARY_PREFIX));
        BufferedOutputStream writer = null;
        FileOutputStream fos = null;
        try {
            String outFileName = filePrefix + chars[0] + chars[1];
            byte[] data = stdin.readAllBytes();
            if (data.length > 0) {
                fos = new FileOutputStream(outFileName);
                writer = new BufferedOutputStream(fos);
                for (byte datum : data) {
                    if (bytesCount == computedBytes) {
                        bytesCount = 0;
                        writer.close();
                        filePrefix = updateNewFileMetaData(chars, filePrefix);
                        outFileName = filePrefix + chars[0] + chars[1];
                        fos = new FileOutputStream(outFileName);
                        writer = new BufferedOutputStream(fos);
                    }
                    writer.write(datum);
                    bytesCount++;
                }
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
