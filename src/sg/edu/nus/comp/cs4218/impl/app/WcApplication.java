package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.app.args.WcArguments;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class WcApplication implements WcInterface {

    private static final String NUMBER_FORMAT = " %7d";
    private static final int LINES_INDEX = 0;
    private static final int WORDS_INDEX = 1;
    private static final int BYTES_INDEX = 2;
    private static final String WC_LITERAL = "wc: ";

    /**
     * Runs the wc application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws WcException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
            throws WcException {
        // Format: wc [-clw] [FILES]
        if (stdout == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        WcArguments wcArgs = new WcArguments();
        wcArgs.parse(args);
        String result;
        try {
            if (wcArgs.getFiles().isEmpty()) {
                result = countFromStdin(wcArgs.isBytes(), wcArgs.isLines(), wcArgs.isWords(), stdin);
            }
            else if (wcArgs.getFiles().size() == 1 && wcArgs.getFiles().get(0).equals("-")) {
                result = countFromStdin(wcArgs.isBytes(), wcArgs.isLines(), wcArgs.isWords(), stdin);
                result = result.concat(" -");
            }
            else if (!wcArgs.getFiles().isEmpty() && wcArgs.getFiles().contains("-")) {
                result = countFromFileAndStdin(wcArgs.isBytes(), wcArgs.isLines(), wcArgs.isWords(), stdin, wcArgs.getFiles().toArray(new String[0]));
            }
            else {
                result = countFromFiles(wcArgs.isBytes(), wcArgs.isLines(), wcArgs.isWords(), wcArgs.getFiles().toArray(new String[0]));
            }
        } catch (Exception e) {
            // Will never happen
            throw new WcException(ERR_GENERAL); //NOPMD
        }
        try {
            stdout.write(result.getBytes());
            stdout.write(STRING_NEWLINE.getBytes());
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        } catch (IOException e) {
            throw new WcException(ERR_WRITE_STREAM);//NOPMD
        }
    }

    /**
     * Returns string containing the number of lines, words, and bytes in input files
     *
     * @param isBytes  Boolean option to count the number of Bytes
     * @param isLines  Boolean option to count the number of lines
     * @param isWords  Boolean option to count the number of words
     * @param fileName Array of String of file names
     * @throws Exception
     */
    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, //NOPMD
                                 String... fileName) throws Exception {
        if (fileName == null) {
            throw new Exception(ERR_GENERAL);
        }
        List<String> result = new ArrayList<>();
        long totalBytes = 0, totalLines = 0, totalWords = 0;
        for (String file : fileName) {
            File node = IOUtils.resolveFilePath(file).toFile();
            if (!node.exists()) {
                result.add(WC_LITERAL + ERR_FILE_NOT_FOUND);
                continue;
            }
            if (node.isDirectory()) {
                result.add(WC_LITERAL + ERR_IS_DIR);
                //print 0 0 0 dir or 0 0 based on isBytes isLines isWords
                result.add(printZeros(isBytes, isLines, isWords, file));
                continue;
            }
            if (!node.canRead()) {
                result.add(WC_LITERAL + ERR_NO_PERM);
                continue;
            }

            InputStream input = IOUtils.openInputStream(file);
            long[] count = getCountReport(input); // lines words bytes
            IOUtils.closeInputStream(input);

            // Update total count
            totalLines += count[0];
            totalWords += count[1];
            totalBytes += count[2];

            // Format all output: " %7d %7d %7d %s"
            // Output in the following order: lines words bytes filename
            StringBuilder sb = new StringBuilder(); //NOPMD
            if (isLines) {
                sb.append(String.format(NUMBER_FORMAT, count[0]));
            }
            if (isWords) {
                sb.append(String.format(NUMBER_FORMAT, count[1]));
            }
            if (isBytes) {
                sb.append(String.format(NUMBER_FORMAT, count[2]));
            }
            sb.append(String.format(" %s", file));
            result.add(sb.toString());
        }

        // Print cumulative counts for all the files
        if (fileName.length > 1) {
            StringBuilder sb = new StringBuilder(); //NOPMD
            if (isLines) {
                sb.append(String.format(NUMBER_FORMAT, totalLines));
            }
            if (isWords) {
                sb.append(String.format(NUMBER_FORMAT, totalWords));
            }
            if (isBytes) {
                sb.append(String.format(NUMBER_FORMAT, totalBytes));
            }
            sb.append(" total");
            result.add(sb.toString());
        }
        return String.join(STRING_NEWLINE, result);
    }

    /**
     * Returns string containing the number of lines, words, and bytes in standard input
     *
     * @param isBytes Boolean option to count the number of Bytes
     * @param isLines Boolean option to count the number of lines
     * @param isWords Boolean option to count the number of words
     * @param stdin   InputStream containing arguments from Stdin
     * @throws Exception
     */
    @Override
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords,
                                 InputStream stdin) throws Exception {
        if (stdin == null) {
            throw new Exception(ERR_NULL_STREAMS);
        }
        long[] count = getCountReport(stdin); // lines words bytes;

        StringBuilder sb = new StringBuilder(); //NOPMD
        if (isLines) {
            sb.append(String.format(NUMBER_FORMAT, count[0]));
        }
        if (isWords) {
            sb.append(String.format(NUMBER_FORMAT, count[1]));
        }
        if (isBytes) {
            sb.append(String.format(NUMBER_FORMAT, count[2]));
        }

        return sb.toString();
    }

    /**
     * Returns string containing the number of lines, words, and bytes in standard input and files
     *
     * @param isBytes Boolean option to count the number of Bytes
     * @param isLines Boolean option to count the number of lines
     * @param isWords Boolean option to count the number of words
     * @param stdin   InputStream containing arguments from Stdin
     * @param fileName Array of String of file names
     * @throws Exception
     */
    @Override
    public String countFromFileAndStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin, String... fileName) throws Exception {
        if (stdin == null || fileName == null) {
            throw new Exception((stdin == null) ? ERR_NULL_STREAMS : ERR_GENERAL);
        }
        List<String> result = new ArrayList<>();
        long[] linesWordsBytes = {0, 0, 0};
        for (String file : fileName) {
            if (("-").equals(file)) {
                long[] count = getCountReport(stdin); // lines words bytes;
                IntStream.range(0, 3).forEach(i -> linesWordsBytes[i] += count[i]);
                String formatCount = formatAndPrintCount(isBytes, isLines, isWords, count, file);
                if (formatCount != null) {
                    result.add(formatCount);
                }
            }
            else {
                File node = IOUtils.resolveFilePath(file).toFile();
                if (!node.exists()) {
                    result.add(WC_LITERAL + ERR_FILE_NOT_FOUND);
                    continue;
                }
                if (node.isDirectory()) {
                    result.add(WC_LITERAL + ERR_IS_DIR);
                    //print 0 0 0 dir or 0 0 based on isBytes isLines isWords
                    result.add(printZeros(isBytes, isLines, isWords, file));
                    continue;
                }
                if (!node.canRead()) {
                    result.add(WC_LITERAL + ERR_NO_PERM);
                    continue;
                }
                InputStream input = IOUtils.openInputStream(file);
                long[] count = getCountReport(input); // lines words bytes
                IOUtils.closeInputStream(input);
                IntStream.range(0, 3).forEach(i -> linesWordsBytes[i] += count[i]);
                String formattedCount = formatAndPrintCount(isBytes, isLines, isWords, count, file);
                if (formattedCount != null) {
                    result.add(formattedCount);
                }
            }
        }
        if (fileName.length > 1) {
            String totalCount = formatAndPrintTotalCount(isBytes, isLines, isWords, new long[] {linesWordsBytes[0],
                    linesWordsBytes[1], linesWordsBytes[2]});
            if (totalCount != null) {
                result.add(totalCount);
            }
        }
        return String.join(STRING_NEWLINE, result);
    }

    public String formatAndPrintCount(boolean isBytes, boolean isLines, boolean isWords, long[] count, String file) {
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (isLines) {
            stringBuilder.append(String.format(NUMBER_FORMAT, count[0]));
        }
        if (isWords) {
            stringBuilder.append(String.format(NUMBER_FORMAT, count[1]));
        }
        if (isBytes) {
            stringBuilder.append(String.format(NUMBER_FORMAT, count[2]));
        }
        stringBuilder.append(String.format(" %s", file));
        result = stringBuilder.toString();
        return result;
    }

    public String formatAndPrintTotalCount(boolean isBytes, boolean isLines, boolean isWords, long... total) {
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (isLines) {
            stringBuilder.append(String.format(NUMBER_FORMAT, total[0]));
        }
        if (isWords) {
            stringBuilder.append(String.format(NUMBER_FORMAT, total[1]));
        }
        if (isBytes) {
            stringBuilder.append(String.format(NUMBER_FORMAT, total[2]));
        }
        stringBuilder.append(" total");
        result = stringBuilder.toString();
        return result;
    }

    public String printZeros(boolean isBytes, boolean isLines, boolean isWords, String file) {
        String result = null;
        long[] count = {0,0,0};
        StringBuilder stringBuilder = new StringBuilder();
        if (isLines) {
            stringBuilder.append(String.format(NUMBER_FORMAT, count[0]));
        }
        if (isWords) {
            stringBuilder.append(String.format(NUMBER_FORMAT, count[1]));
        }
        if (isBytes) {
            stringBuilder.append(String.format(NUMBER_FORMAT, count[2]));
        }
        stringBuilder.append(String.format(" %s", file));
        result = stringBuilder.toString();
        return result;
    }

    /**
     * Returns array containing the number of lines, words, and bytes based on data in InputStream.
     *
     * @param input An InputStream
     * @throws IOException
     */
    public long[] getCountReport(InputStream input) throws Exception {
        if (input == null) {
            throw new Exception(ERR_NULL_STREAMS);
        }
        long[] result = new long[3]; // lines, words, bytes

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int inRead = 0;
        boolean inWord = false;
        while ((inRead = input.read(data, 0, data.length)) != -1) {
            for (int i = 0; i < inRead; ++i) {
                if (Character.isWhitespace(data[i])) {
                    // Use <newline> character here. (Ref: UNIX)
                    if (data[i] == '\n') {
                        ++result[LINES_INDEX];
                    }
                    if (inWord) {
                        ++result[WORDS_INDEX];
                    }

                    inWord = false;
                } else {
                    inWord = true;
                }
            }
            result[BYTES_INDEX] += inRead;
            buffer.write(data, 0, inRead);
        }
        buffer.flush();
        if (inWord) {
            ++result[WORDS_INDEX]; // To handle last word
            ++result[LINES_INDEX]; // To handle last line, assuming last word does not terminate with \n
        }

        return result;
    }
}
