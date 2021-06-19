package sg.edu.nus.comp.cs4218.impl.app;

import org.apache.commons.io.output.ByteArrayOutputStream;
import sg.edu.nus.comp.cs4218.app.UniqInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.UniqException;
import sg.edu.nus.comp.cs4218.impl.app.args.UniqArguments;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.convertToAbsolutePath;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

public class UniqApplication implements UniqInterface {

    public static final String ERR_MUTUAL_EXC = "Mutually exclusive options were given!";

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        String results = "";
        try {
            stdin.available();
            UniqArguments uniqArgs = new UniqArguments();
            uniqArgs.parse(args);
            boolean isCount = uniqArgs.isCount();
            boolean isRepeated = uniqArgs.isRepeated();
            boolean isAllRepeated = uniqArgs.isAllRepeated();
            String inputFileName = uniqArgs.getInputFileName();
            String outputFileName = uniqArgs.getOutputFileName();
            if (stdin == null) {
                throw new UniqException(ERR_NO_ISTREAM);
            }
            if (isCount && isAllRepeated || isCount && isRepeated) {
                throw new UniqException(ERR_MUTUAL_EXC);
            }
            if (inputFileName == null || inputFileName.equals("-")) {
                results = uniqFromStdin(isCount, isRepeated, isAllRepeated, stdin, outputFileName);
            } else {
                results = uniqFromFile(isCount, isRepeated, isAllRepeated, inputFileName, outputFileName);
            }
            if (outputFileName == null) {
                if (!results.isEmpty()) {
                    results += STRING_NEWLINE;
                }
                stdout.write(results.getBytes(StandardCharsets.UTF_8));
            }
            if (!stdout.equals(System.out)) {
                stdout.close();
            }
        } catch (UniqException ue) {
            throw ue;
        } catch (Exception e) {
            throw new UniqException(e.getMessage());
        }
    }

    private String checkIfFileExists(String fileName) throws Exception{
        String path = convertToAbsolutePath(fileName);
        File file = new File(path);
        if (!file.exists()) {
            throw new UniqException(fileName + ": " + ERR_FILE_NOT_FOUND);
        }
        if (file.isDirectory()) { // ignore if it's a directory
            throw new UniqException(fileName + ": " + IS_DIRECTORY);
        }
        return path;
    }

    private void populateLists(BufferedReader reader, List<Integer> freq, List<String> lines) throws IOException {
        String line;
        String previousLine = null;
        int index = 0;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            if (previousLine == null) {
                count = 1;
                previousLine = line;
                freq.add(index, count);
                lines.add(index, line);
            } else {
                if (previousLine.equals(line)) {
                    count++;
                    freq.set(index, count);
                } else {
                    // previousLine != line
                    index++;
                    count = 1;
                    previousLine = line;
                    freq.add(index, count);
                    lines.add(index, line);
                }
            }
        }
    }

    private void uniqResults(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, StringJoiner results, List<Integer> freq, List<String> lines) {
        for (int i = 0; i < freq.size(); i++) {
            if (isCount) {
                results.add(freq.get(i) + " " + lines.get(i));
            } else {
                if (isAllRepeated) {
                    if (freq.get(i) > 1) {
                        for (int j = 0; j < freq.get(i); j++){
                            results.add(lines.get(i));
                        }
                    }
                } else if (isRepeated) {
                    if (freq.get(i) > 1) {
                        results.add(lines.get(i));
                    }
                } else {
                    results.add(lines.get(i));
                }
            }
        }
    }

    @Override
    public String uniqFromFile(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, String inputFileName, String outputFileName) throws Exception {
        if (isCount == null || isRepeated == null || isAllRepeated == null) {
            throw new UniqException("Null Flags detected");
        }
        if (isCount && isAllRepeated || isCount && isRepeated) {
            throw new UniqException(ERR_MUTUAL_EXC);
        }
        List<Integer> freq = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        StringJoiner lineResults = new StringJoiner(STRING_NEWLINE);
        BufferedReader reader = null;
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        try {
            String path = checkIfFileExists(inputFileName);
            // If reaches here, file exists and is not a directory
            File file = new File(path);
            reader = new BufferedReader(new FileReader(file));
            populateLists(reader, freq, lines);
            uniqResults(isCount, isRepeated, isAllRepeated, lineResults, freq, lines);
            if (outputFileName != null) {
                fos = new FileOutputStream(outputFileName);
                writer = new BufferedWriter(new OutputStreamWriter(fos));
                writer.write(lineResults.toString());
            }
            return lineResults.toString();
        } finally {
            if (writer != null){
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    @Override
    public String uniqFromStdin(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, InputStream stdin, String outputFileName) throws Exception {
        if (isCount == null || isRepeated == null || isAllRepeated == null) {
            throw new UniqException("Null Flags detected");
        }
        if (stdin == null) {
            throw new UniqException(ERR_NO_ISTREAM);
        }
        if (isCount && isAllRepeated || isCount && isRepeated) {
            throw new UniqException(ERR_MUTUAL_EXC);
        }
        List<Integer> freq = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        StringJoiner lineResults = new StringJoiner(STRING_NEWLINE);
        BufferedReader reader = null;
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(stdin));
            populateLists(reader, freq, lines);
            uniqResults(isCount, isRepeated, isAllRepeated, lineResults, freq, lines);
            if (outputFileName != null) {
                fos = new FileOutputStream(outputFileName);
                writer = new BufferedWriter(new OutputStreamWriter(fos));
                writer.write(lineResults.toString());
            }
            return lineResults.toString();
        } finally {
            if (writer != null){
                writer.close();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new UniqException(e.getMessage());
                }
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
