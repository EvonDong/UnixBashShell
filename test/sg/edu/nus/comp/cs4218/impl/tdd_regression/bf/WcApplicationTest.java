package sg.edu.nus.comp.cs4218.impl.tdd_regression.bf;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;

public class WcApplicationTest {
    public static final String TEMP = "temp-wc";;
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static String currPathString;
    public static Deque<Path> files = new ArrayDeque<>();
    public static final String WC_PREFIX = "wc: ";
    public static final String WC_TOTAL_POSTFIX =" total";
    public static final String WC_SIZE_FORMAT = "%8s";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String LINE_WORD_COUNT = "       4       8";
    public static final String WC_CONTENT = "First line" + NEW_LINE + "Second line" + NEW_LINE + "Third line"
            + NEW_LINE + "Fourth line" + NEW_LINE;

    @BeforeEach
    void changeDirectory() throws IOException {
        currPathString = Environment.currentDirectory;
        Files.createDirectory(TEMP_PATH);
        Environment.setCurrentDirectory(TEMP_PATH.toString());
    }

    @AfterEach
    void deleteFiles() throws IOException {
        Environment.setCurrentDirectory(currPathString);
        Files.walk(TEMP_PATH)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    private Path createFile(String name) throws IOException {
        String content = WC_CONTENT;
        Path path = TEMP_PATH.resolve(name);
        Files.createFile(path);
        Files.write(path, content.getBytes());
        files.push(path);
        return path;
    }

    private void createDirectory(String folder) throws IOException {
        Path path = TEMP_PATH.resolve(folder);
        Files.createDirectory(path);
        files.push(path);
    }

    private String[] toArgs(String flag, String... files) {
        List<String> args = new ArrayList<>();
        if (!flag.isEmpty()) {
            args.add("-" + flag);
        }
        for (String file : files) {
            args.add(Paths.get(file).toString());
        }
        return args.toArray(new String[0]);
    }

    @Test
    void run_SingleFileNoFlags_DisplaysLinesWordsBytesFilename() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileA.txt";
        Path filePath = createFile(fileName);
        long fileSize = Files.size(filePath);
        new WcApplication().run(toArgs("", fileName), System.in, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileSize) + " " + fileName + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileLinesFlag_DisplaysLinesFilename() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileB.txt";
        createFile(fileName);
        new WcApplication().run(toArgs("l", fileName), System.in, output);
        assertArrayEquals(("       4 " + fileName + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileWordsFlag_DisplaysWordsFilename() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileC.txt";
        createFile(fileName);
        new WcApplication().run(toArgs("w", fileName), System.in, output);
        assertArrayEquals(("       8 " + fileName + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileBytesFlag_DisplaysBytesFilename() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileD.txt";
        Path filePath = createFile(fileName);
        long fileSize = Files.size(filePath);
        new WcApplication().run(toArgs("c", fileName), System.in, output);
        assertArrayEquals((String.format(WC_SIZE_FORMAT, fileSize) + " " + fileName + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileAllFlags_DisplaysLinesWordsBytesFilename() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileE.txt";
        Path filePath = createFile(fileName);
        long fileSize = Files.size(filePath);
        new WcApplication().run(toArgs("clw", fileName), System.in, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileSize) + " " + fileName + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileUnknownFlag_Throws() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileF.txt";
        createFile(fileName);
        assertThrows(WcException.class, () -> new WcApplication().run(toArgs("x", fileName), System.in, output));
    }

    @Test
    void run_SingleInputNoFileSpecified_DisplaysLinesWordsBytes() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String input = WC_CONTENT;
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        long fileSize = input.getBytes().length;
        new WcApplication().run(toArgs(""), inputStream, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileSize) + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleInputDash_DisplaysLinesWordsBytes() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String input = WC_CONTENT;
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        long fileSize = input.getBytes().length;
        new WcApplication().run(toArgs("", "-"), inputStream, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileSize) + " -" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_MultipleFiles_DisplaysLinesWordsBytesFilenameTotal() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileGName = "fileG.txt";
        String fileHName = "fileH.txt";
        Path fileGPath = createFile(fileGName);
        Path fileHPath = createFile(fileHName);
        long fileGSize = Files.size(fileGPath);
        long fileHSize = Files.size(fileHPath);
        new WcApplication().run(toArgs("", fileGName, fileHName), System.in, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileGSize) + " " + fileGName + STRING_NEWLINE
                + LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileHSize) + " " + fileHName + STRING_NEWLINE
                + "       8      16" + String.format(WC_SIZE_FORMAT, fileGSize + fileHSize) + WC_TOTAL_POSTFIX + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileAndSingleInput_DisplaysLinesWordsBytesTotal() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileIName = "fileI.txt";
        Path fileIPath = createFile(fileIName);
        long fileISize = Files.size(fileIPath);
        String input = WC_CONTENT;
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        long inputSize = input.getBytes().length;
        new WcApplication().run(toArgs("", fileIName, "-"), inputStream, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileISize) + " " + fileIName + STRING_NEWLINE
                + LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, inputSize) + " -" + STRING_NEWLINE
                + "       8      16" + String.format(WC_SIZE_FORMAT, fileISize + inputSize) + WC_TOTAL_POSTFIX + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileAndNonexistentFile_DisplaysLinesWordsBytesFilenameErrorMessageTotal() throws IOException, AbstractApplicationException{
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileJName = "fileJ.txt";
        Path fileJPath = createFile(fileJName);
        long fileJSize = Files.size(fileJPath);
        String nonExisttFileName = "nonexistent_file.txt";
        new WcApplication().run(toArgs("", fileJName, nonExisttFileName), System.in, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileJSize) + " " + fileJName + STRING_NEWLINE
                + WC_PREFIX + ERR_FILE_NOT_FOUND + STRING_NEWLINE
                + LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, fileJSize) + WC_TOTAL_POSTFIX + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleInputAndNonexistentFile_DisplaysLinesWordsBytesDashErrorMessageTotal() throws AbstractApplicationException{
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String input = WC_CONTENT;
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        long inputSize = input.getBytes().length;
        String nonExistFileName = "nonexistent_file.txt";
        new WcApplication().run(toArgs("", "-", nonExistFileName), inputStream, output);
        assertArrayEquals((LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, inputSize) + " -" + STRING_NEWLINE
                + WC_PREFIX + ERR_FILE_NOT_FOUND + STRING_NEWLINE
                + LINE_WORD_COUNT + String.format(WC_SIZE_FORMAT, inputSize) + WC_TOTAL_POSTFIX + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void run_FilenameIsNull_Throws() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertThrows(Exception.class, () -> new WcApplication().run(toArgs("", null), System.in, output));
    }

    @Test
    void run_OutputStreamIsNull_Throws() throws IOException {
        String fileKName = "fileK.txt";
        createFile(fileKName);
        assertThrows(Exception.class, () -> new WcApplication().run(toArgs("", fileKName), System.in, null));
    }

    @Test
    void run_InputStreamIsNull_Throws() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertThrows(Exception.class, () -> new WcApplication().run(toArgs("", ""), null, output));
    }

    @Test
    void run_FilenameIsDirectory_DisplaysErrorMessage() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String folderName = "folder";
        createDirectory(folderName);
        new WcApplication().run(toArgs("", folderName), System.in, output);
        String expected = WC_PREFIX + ERR_IS_DIR + STRING_NEWLINE + "       0       0       0 "
                + folderName + STRING_NEWLINE;
        assertEquals(expected, output.toString());
        assertArrayEquals(expected.getBytes(), output.toByteArray());
    }

    @Test
    void run_NonexistentFile_DisplaysErrorMessage() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String nonExistFileName = "nonexistent_file.txt";
        new WcApplication().run(toArgs("", nonExistFileName), System.in, output);
        assertArrayEquals((WC_PREFIX + ERR_FILE_NOT_FOUND + STRING_NEWLINE).getBytes(), output.toByteArray());
    }
}
