package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.*;

public class WcIntegrationTest {
    private static final String INITIAL_DIRECTORY = Paths.get(Environment.currentDirectory).toString();

    private static final String NEW_LINE = System.lineSeparator();
    private static final String FILE_ONE = "wc_file1.txt";
    private static final String FILE_TWO = "wc_file2.txt";
    private static final String FILE_THREE = "wc_file3.txt";
    private static final String FILE_OUTPUT = "output.txt";
    private static final String FILE_ONE_CONTENT = "I love" + NEW_LINE + "CS4218" + NEW_LINE + NEW_LINE;
    private static final String FILE_TWO_CONTENT = "~!@#$%+";
    private static final String FILE_THREE_CONT = "\" ' < > * | ` \"\n" + "\\ $ % ; , . ? /";
    private static final String DIRECTORY = "myDirectory";
    private static final String NUMBER_FORMAT = " %7d";
    private static final String WC_FILE_NOT_FOUND = "wc: No such file or directory";
    private static final String TOTAL_TITLE = " total";

    public static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public static final PrintStream ORIGINAL_OUT = System.out;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @BeforeEach
    void init() throws IOException {
        createFile(FILE_ONE);
        writeFile(FILE_ONE, FILE_ONE_CONTENT);
        createFile(FILE_TWO);
        writeFile(FILE_TWO, FILE_TWO_CONTENT);
        createFile(FILE_THREE);
        writeFile(FILE_THREE, FILE_THREE_CONT);
        createDirectory(DIRECTORY);
    }

    @AfterEach
    void terminate() throws IOException {
        Environment.currentDirectory = INITIAL_DIRECTORY;
        deleteFile(FILE_ONE);
        deleteFile(FILE_TWO);
        deleteFile(FILE_THREE);
//        Files.deleteIfExists(IOUtils.resolveFilePath(FILE_THREE));
        deleteFile(FILE_OUTPUT);
        deleteDirectory(DIRECTORY);
    }

    @AfterEach
    void setUp() {
        Environment.currentDirectory = INITIAL_DIRECTORY;
    }

    // ========================================  POSITIVE WC INTEGRATION =========================================

    @Test
    public void wc_WithQuoting_DisplaysWcResults() throws Exception {
        outContent.reset();
        long[] fileOneCount = new long[]{3,3};
        long[] fileTwoCount = new long[]{1,1};
        long[] fileThreeCount = new long[]{2,16};
        long[] totalCount = new long[]{6,20};
        String fileOneBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        String fileTwoBytes = String.format(NUMBER_FORMAT, FILE_TWO_CONTENT.getBytes().length);
        String fileThreeBytes = String.format(NUMBER_FORMAT, FILE_THREE_CONT.getBytes().length);
        String totalNumBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length + FILE_TWO_CONTENT.getBytes().length + FILE_THREE_CONT.getBytes().length);
        String commandString = "wc -clw \"wc_file2.txt\" nonExisting.file 'wc_file1.txt' wc_file3.txt";
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        expectedList.add(WC_FILE_NOT_FOUND);
        expectedList.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        expectedList.add(String.format(NUMBER_FORMAT,fileThreeCount[0]) + String.format(NUMBER_FORMAT,fileThreeCount[1]) + fileThreeBytes + " " + FILE_THREE);
        expectedList.add(new StringBuilder().append(String.format(NUMBER_FORMAT, totalCount[0])).append(String.format(NUMBER_FORMAT, totalCount[1])).append(totalNumBytes).append(TOTAL_TITLE).toString());
        String expected = String.join(NEW_LINE, expectedList);
        expected = expected + NEW_LINE;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void wc_WithIORedirection_OutputResultToResults() throws Exception {
        outContent.reset();
        long[] fileOneCount = new long[]{3,3};
        long[] fileTwoCount = new long[]{1,1};
        long[] fileThreeCount = new long[]{2,16};
        long[] totalCount = new long[]{6,20};
        String fileOneBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        String fileTwoBytes = String.format(NUMBER_FORMAT, FILE_TWO_CONTENT.getBytes().length);
        String fileThreeBytes = String.format(NUMBER_FORMAT, FILE_THREE_CONT.getBytes().length);
        String totalNumBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length + FILE_TWO_CONTENT.getBytes().length + FILE_THREE_CONT.getBytes().length);
        String commandString = "wc -clw wc_file2.txt wc_file1.txt - < wc_file3.txt > " + FILE_OUTPUT;
        new ShellImpl().parseAndEvaluate(commandString, outContent);

        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        expectedList.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        expectedList.add(String.format(NUMBER_FORMAT,fileThreeCount[0]) + String.format(NUMBER_FORMAT,fileThreeCount[1]) + fileThreeBytes + " " + "-");
        expectedList.add(new StringBuilder().append(String.format(NUMBER_FORMAT, totalCount[0])).append(String.format(NUMBER_FORMAT, totalCount[1])).append(totalNumBytes).append(TOTAL_TITLE).toString());
        String expected = String.join(NEW_LINE, expectedList);
        expected = expected + NEW_LINE;
        assertEquals(expected, Files.readString(IOUtils.resolveFilePath(FILE_OUTPUT)));
    }

    @Test
    public void wc_WithSequence_DisplaysOutputResultAndCdToSrc() throws Exception {
        outContent.reset();
        long[] fileOneCount = new long[]{3,3};
        long[] fileTwoCount = new long[]{1,1};
        long[] totalCount = new long[]{4,4};
        String commandString = "wc -lw wc_file2.txt wc_file1.txt nonExist; cd src";
        new ShellImpl().parseAndEvaluate(commandString, outContent);

        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + " " + FILE_TWO);
        expectedList.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + " " + FILE_ONE);
        expectedList.add(WC_FILE_NOT_FOUND);
        expectedList.add(new StringBuilder().append(String.format(NUMBER_FORMAT, totalCount[0])).append(String.format(NUMBER_FORMAT, totalCount[1])).append(TOTAL_TITLE).toString());
        String expected = String.join(NEW_LINE, expectedList);
        expected = expected + NEW_LINE;
        assertEquals(expected, outContent.toString());
        assertEquals(Paths.get(INITIAL_DIRECTORY, "src").toString(), Environment.currentDirectory);
    }

    @Test
    public void wc_WithGlobbing_DisplaysOutputResult() throws Exception {
        outContent.reset();
        long[] fileOneCount = new long[]{3,3};
        long[] fileTwoCount = new long[]{1,1};
        long[] fileThreeCount = new long[]{2,16};
        long[] totalCount = new long[]{6,20};
        String fileOneBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        String fileTwoBytes = String.format(NUMBER_FORMAT, FILE_TWO_CONTENT.getBytes().length);
        String fileThreeBytes = String.format(NUMBER_FORMAT, FILE_THREE_CONT.getBytes().length);
        String totalNumBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length + FILE_TWO_CONTENT.getBytes().length + FILE_THREE_CONT.getBytes().length);
        String commandString = "wc -cw wc_file* ";
        new ShellImpl().parseAndEvaluate(commandString, outContent);

        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        expectedList.add(String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        expectedList.add(String.format(NUMBER_FORMAT,fileThreeCount[1]) + fileThreeBytes + " " + FILE_THREE);
        expectedList.add(new StringBuilder().append(String.format(NUMBER_FORMAT, totalCount[1])).append(totalNumBytes).append(TOTAL_TITLE).toString());
        String expected = String.join(NEW_LINE, expectedList);
        expected = expected + NEW_LINE;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void wc_WithPipeTee_TeeStoresWcDisplayIntoOutputFile() throws Exception {
        outContent.reset();
        long[] fileTwoCount = new long[]{1,1};
        long[] fileThreeCount = new long[]{2,16};
        long[] totalCount = new long[]{3,17};
        String fileTwoBytes = String.format(NUMBER_FORMAT, FILE_TWO_CONTENT.getBytes().length);
        String fileThreeBytes = String.format(NUMBER_FORMAT, FILE_THREE_CONT.getBytes().length);
        String totalNumBytes = String.format(NUMBER_FORMAT, FILE_TWO_CONTENT.getBytes().length + FILE_THREE_CONT.getBytes().length);
        String commandString = "wc " + FILE_TWO + " " + FILE_THREE + "| tee " +  FILE_OUTPUT;
        new ShellImpl().parseAndEvaluate(commandString, outContent);

        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        expectedList.add(String.format(NUMBER_FORMAT,fileThreeCount[0]) + String.format(NUMBER_FORMAT,fileThreeCount[1]) + fileThreeBytes + " " + FILE_THREE);
        expectedList.add(new StringBuilder().append(String.format(NUMBER_FORMAT, totalCount[0])).append(String.format(NUMBER_FORMAT, totalCount[1])).append(totalNumBytes).append(TOTAL_TITLE).toString());
        String expected = String.join(NEW_LINE, expectedList);
        expected = expected + NEW_LINE;
        assertEquals(expected, outContent.toString());
        assertEquals(expected, Files.readString(IOUtils.resolveFilePath(FILE_OUTPUT)));
    }

    @Test
    public void wc_WithQuoteIORedirectionPipeGlobbingSequence_TeeStoresWcDisplayInOutput() throws Exception {
        outContent.reset();
        long[] fileOneCount = new long[]{3,3};
        long[] fileTwoCount = new long[]{1,1};
        long[] fileThreeCount = new long[]{2,16};
        long[] totalCount = new long[]{12,40};
        String fileOneBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        String fileTwoBytes = String.format(NUMBER_FORMAT, FILE_TWO_CONTENT.getBytes().length);
        String fileThreeBytes = String.format(NUMBER_FORMAT, FILE_THREE_CONT.getBytes().length);
        String totalNumBytes = String.format(NUMBER_FORMAT, 2 * (FILE_ONE_CONTENT.getBytes().length + FILE_TWO_CONTENT.getBytes().length + FILE_THREE_CONT.getBytes().length));

        String commandString = "wc -clw wc_file* \"wc_file1.txt\" 'wc_file2.txt' - < wc_file3.txt | tee " + FILE_OUTPUT + "; cd src";
        new ShellImpl().parseAndEvaluate(commandString, outContent);

        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        expectedList.add(String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        expectedList.add(String.format(NUMBER_FORMAT,fileThreeCount[0]) + String.format(NUMBER_FORMAT,fileThreeCount[1]) + fileThreeBytes + " " + FILE_THREE);
        expectedList.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        expectedList.add(String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        expectedList.add(String.format(NUMBER_FORMAT,fileThreeCount[0]) + String.format(NUMBER_FORMAT,fileThreeCount[1]) + fileThreeBytes + " -");
        expectedList.add(new StringBuilder().append(String.format(NUMBER_FORMAT, totalCount[0])).append(String.format(NUMBER_FORMAT, totalCount[1])).append(totalNumBytes).append(TOTAL_TITLE).toString());
        String expected = String.join(NEW_LINE, expectedList);
        expected = expected + NEW_LINE;
        assertEquals(expected, outContent.toString());
        assertEquals(expected, Files.readString(IOUtils.resolveFilePath(INITIAL_DIRECTORY + File.separator + FILE_OUTPUT)));
        assertEquals(Paths.get(INITIAL_DIRECTORY, "src").toString(), Environment.currentDirectory);
    }

    // ========================================  NEGATIVE WC INTEGRATION =========================================

    @Test
    public void wc_WithAllArgsQuotedAsOne_DisplayNoSuchFileError() throws Exception {
        outContent.reset();
        String commandString = "wc \"wc_file2.txt wc_file3.txt\"";
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        String expected = "wc: No such file or directory" + NEW_LINE;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void wc_WithMultipleFilesForInputRedirection_ThrowsShellException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "wc -cl wc_file1.txt > output.txt output2.txt";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }





}
