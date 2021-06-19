package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ISTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_PERM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_WRITE_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_TAB;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.comp.cs4218.app.PasteInterface;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class PasteApplication implements PasteInterface {

    private void basicValidation(String[] args, InputStream stdin, OutputStream stdout) throws PasteException {
        if (args == null) {
            throw new PasteException(ERR_NULL_ARGS);
        } else if (stdin == null) {
            throw new PasteException(ERR_NO_ISTREAM);
        } else if (stdout == null) {
            throw new PasteException(ERR_NO_OSTREAM);
        }
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws PasteException {
        basicValidation(args, stdin, stdout);
        PasteArgsParser parser = new PasteArgsParser();
        try {
            for(int i = 0; i < args.length; i++) {
                args[i] = args[i].trim();
            }
            parser.parse(args);
        } catch (Exception e) {
            throw new PasteException(e.getMessage());
        }
        boolean isSerial = parser.isSerial();
        String[] argArr = parser.getArgs().toArray(new String[0]);
        int numStdIn = 0, numFile = 0;

        for(int i = 0; i < argArr.length; i++) {
            if(argArr[i] == null) {
                throw new PasteException(ERR_NULL_ARGS);
            } else if(argArr[i].equals("-")) {
                numStdIn++;
            } else {
                argArr[i] = getAbsolutePath(argArr[i]);
                numFile++;
            }
        }

        String output;
        try {
            if(numFile == argArr.length && argArr.length > 0) {
                output = mergeFile(isSerial, argArr);
            } else if (numStdIn >= 2 || (numStdIn > 0 && numFile > 0)) {
                output = mergeFileAndStdin(isSerial, stdin, argArr);
            } else {
                output = mergeStdin(isSerial, stdin);
            }
            stdout.write(output.getBytes());
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        } catch (IOException e) {
            throw new PasteException(ERR_WRITE_STREAM);
        } catch (Exception e) {
            throw new PasteException(e.getMessage());
        }
    }

    public String getAbsolutePath(String fileName) throws PasteException {
        String path = String.valueOf(IOUtils.resolveFilePath(fileName));
        if (!(new File(path)).exists()) {
            throw new PasteException(fileName + ": " + ERR_FILE_NOT_FOUND);
        }
        File file = new File(path);
        if (file.isDirectory()) {
            throw new PasteException(path + ": " + ERR_IS_DIR);
        } else if (file.canRead()) {
            return path;
        } else {
            throw new PasteException(fileName + ": " + ERR_NO_PERM);
        }
    }

    @Override
    public String mergeStdin(Boolean isSerial, InputStream stdin) throws PasteException {

        StringBuilder out = new StringBuilder();
        BufferedReader inReader = new BufferedReader(new InputStreamReader(stdin));

        if(isSerial) {
            String line;
            try {
                line = inReader.readLine();
                while(line != null) {
                    out.append(line);
                    out.append(CHAR_TAB);
                    line = inReader.readLine();
                }
                if (out.length() > 0) {
                    out.delete(out.length() - 1, out.length());
                }
                inReader.close();
            } catch (IOException e) {
                throw new PasteException(ERR_IO_EXCEPTION);
            }
        } else {
            String line;
            try {
                line = inReader.readLine();
                while(line != null) {
                    out.append(line);
                    out.append(STRING_NEWLINE);
                    line = inReader.readLine();
                }
                if (out.length() - STRING_NEWLINE.length() >= 0) {
                    out.delete(out.length() - STRING_NEWLINE.length(), out.length());
                }
                if (!stdin.equals(System.in)) {
                    inReader.close();
                }
                inReader.close();
            } catch (IOException e) {
                throw new PasteException(ERR_IO_EXCEPTION);
            }
        }
        return out.toString();
    }

    @Override
    public String mergeFile(Boolean isSerial, String... fileName) throws PasteException {
        if (fileName == null) {
            throw new PasteException(ERR_NULL_ARGS);
        } else if (fileName.length == 0) {
            throw new PasteException(ERR_NO_ARGS);
        }

        int numFile = fileName.length;
        List<BufferedReader> reader = new ArrayList<BufferedReader>();

        try {
            for(int i = 0; i < numFile; i++) {
                reader.add(new BufferedReader(new FileReader(fileName[i])));
            }

            if(isSerial) {
                return mergeFileSerial(reader);
            } else {
                return mergeFileNotSerial(reader);
            }
        } catch (IOException e) {
            throw new PasteException(ERR_IO_EXCEPTION);
        }
    }


    /**
     * Merge the contents of the file arguments in reader list into a serial manner.
     *
     * @param reader         - a list of all bufferedreader containing the file/stdin arguments
     * @return               - a string containing the merged file contents in serial manner
     * @throws IOException   if any IO error is encountered while reading from bufferedReader
     */
    private String mergeFileSerial(List<BufferedReader> reader) throws IOException {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < reader.size(); i++) {
            String line;

            line = reader.get(i).readLine();
            while(line != null) {
                out.append(line);
                out.append(CHAR_TAB);
                line = reader.get(i).readLine();
            }
            if (out.length() > 0 && out.lastIndexOf(String.valueOf(CHAR_TAB)) == out.length() - 1) {
                out.delete(out.length() - 1, out.length());
            }
            if(i != reader.size() - 1) {
                if (i == 0) {
                    out.append(STRING_NEWLINE);
                } else if (!reader.get(i).equals(reader.get(i - 1))) {
                        out.append(STRING_NEWLINE);
                }
            }

        }
        for (BufferedReader r : reader) {
            r.close();
        }
        return out.toString();

    }

    /**
     * Merge the contents of the file arguments in reader list into a non-serial(parallel) manner.
     *
     * @param reader         - a list of all bufferedreader containing the file/stdin arguments
     * @return               - a string containing the merged file contents in parallel
     * @throws IOException   if any IO error is encountered while reading from bufferedReader
     */
    private String mergeFileNotSerial(List<BufferedReader> reader) throws IOException {

        StringBuilder out = new StringBuilder();

        boolean haveCompleted;
        do {
            String line;
            haveCompleted = true;
            int unwantedTabs = 0;
            for (int i = 0; i < reader.size(); i++) {
                line = reader.get(i).readLine();
                if (line != null) {
                    haveCompleted = false;
                    out.append(line);
                    unwantedTabs = 0;
                }
                out.append(CHAR_TAB);
                unwantedTabs++;
            }
            out.delete(out.length() - unwantedTabs, out.length());

            if (!haveCompleted) {
                out.append(STRING_NEWLINE);
            }
        } while (!haveCompleted);

        for (BufferedReader r : reader) {
            r.close();
        }
        return out.toString();
    }


    @Override
    public String mergeFileAndStdin(Boolean isSerial, InputStream stdin, String... fileName) throws PasteException {

        if (fileName == null) {
            throw new PasteException(ERR_NULL_ARGS);
        } else if (fileName.length == 0) {
            throw new PasteException(ERR_NO_ARGS);
        }

        String out;
        List<BufferedReader> reader = new ArrayList<BufferedReader>();
        BufferedReader inReader = new BufferedReader(new InputStreamReader(stdin));
        try {
            BufferedReader fileReader = null;
            for(int i = 0; i < fileName.length; i++) {
                String file = fileName[i];
                if (("-").equals(file)) {
                    reader.add(inReader);
                } else {
                    fileReader = new BufferedReader(new FileReader(file));
                    reader.add(fileReader);
                }
            }
            if(isSerial) {
                out = mergeFileSerial(reader);
            } else {
                out = mergeFileNotSerial(reader);
            }
            if(fileReader != null) {
                fileReader.close();
            }

            for (BufferedReader r : reader) {
                r.close();
            }
        } catch (IOException e) {
            throw new PasteException(ERR_IO_EXCEPTION);
        }
        return out;
    }
}
