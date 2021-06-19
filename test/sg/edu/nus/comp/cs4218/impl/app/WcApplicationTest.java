package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.writeFile;

public class WcApplicationTest {
    WcApplication wcApplication;
    WcApplication wcApp;

    private static final String NUMBER_FORMAT = " %7d";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SOURCE_DIRECTORY = Paths.get(Environment.currentDirectory).resolve("src").toString();
    private static final String NON_EXIST_FILE = "ioeqfhe";
    private static final String STDIN = "-";
    private static final String FILE_ONE = "fIlE-OnE";
    private static final String FILE_TWO = "FIle Two2";
    private static final String FILE_ONE_CONTENT = "I love" + NEW_LINE + "CS4218";
    private static final String FILE_TWO_CONTENT = "~!@#$%+";
    private static final String HUMAN_INPUT_ONE = "I love CS4218" + NEW_LINE;
    private static final String FILE_NOT_FOUND = "wc: No such file or directory";
    private static final String DIRECTORY_MESSAGE = "wc: This is a directory";
    private static final String TOTAL_TITLE = " total";

    @BeforeEach
    void setUp() throws IOException{
        wcApplication = new WcApplication();
        wcApp = spy(WcApplication.class);
        createFile(FILE_ONE);
        writeFile(FILE_ONE, FILE_ONE_CONTENT);
        createFile(FILE_TWO);
        writeFile(FILE_TWO, FILE_TWO_CONTENT);
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteFile(FILE_ONE);
        deleteFile(FILE_TWO);
    }

    @Test
    void getCountReport_NullStdin_ThrowsException(){
        assertThrows(Exception.class, () -> wcApplication.getCountReport(null));
    }

    @Test
    void countFromFiles_NullFile_ThrowsException() {
        assertThrows(Exception.class, () -> wcApplication.countFromFiles(true, true, true, (String) null));
    }

    @Test
    void countFromFiles_NonExistingFile_ReturnsErrorMessage() throws Exception{
        assertEquals(FILE_NOT_FOUND, wcApplication.countFromFiles(true, true, true, "NON_EXISITING_FILE"));
    }

    @Test
    void countFromFiles_FileNameIsDirectory_ReturnsErrorMessage() throws Exception{
        String expected = DIRECTORY_MESSAGE + NEW_LINE + String.format(NUMBER_FORMAT,0)
                + String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0) + " " + SOURCE_DIRECTORY;
        assertEquals(expected, wcApplication.countFromFiles(true, true, true, SOURCE_DIRECTORY));
    }

    @Test
    void countFromFiles_OneFile_ReturnStringOfNumbers() throws Exception{
        long[] count = new long[]{2,3,14};
        String byteLength = String.format(NUMBER_FORMAT,FILE_ONE_CONTENT.getBytes().length);
        String output = String.format(NUMBER_FORMAT,count[0]) + String.format(NUMBER_FORMAT,count[1]) + byteLength + " " + FILE_ONE;
        assertEquals(output, wcApplication.countFromFiles(true, true, true, FILE_ONE));
    }

    @Test
    // wc [-c]
    void countFromFiles_OneFileWithOnlyBytes_ReturnStringOfNumbers() throws Exception{
        String output = String.format(NUMBER_FORMAT,FILE_ONE_CONTENT.getBytes().length) + " " + FILE_ONE;
        assertEquals(output, wcApplication.countFromFiles(true, false, false, FILE_ONE));
    }

    @Test
    // wc [-l]
    void countFromFiles_OneFileWithOnlyLines_ReturnStringOfNumbers() throws Exception{
        long[] count = new long[]{2,3};
        String output = String.format(NUMBER_FORMAT,count[0]) + " " + FILE_ONE;
        assertEquals(output, wcApplication.countFromFiles(false, true, false, FILE_ONE));
    }

    @Test
    // wc [-w]
    void countFromFiles_OneFileWithOnlyWords_ReturnStringOfNumbers() throws Exception{
        long[] count = new long[]{2,3};
        String output = String.format(NUMBER_FORMAT,count[1]) + " " + FILE_ONE;
        assertEquals(output, wcApplication.countFromFiles(false, false, true, FILE_ONE));
    }

    @Test
    // wc [-cl]
    void countFromFiles_OneFileWithBytesAndLines_ReturnStringOfNumbers() throws Exception{
        long[] count = new long[]{2,3};
        String byteLength = String.format(NUMBER_FORMAT,FILE_ONE_CONTENT.getBytes().length);
        String output = String.format(NUMBER_FORMAT,count[0]) + byteLength + " " + FILE_ONE;
        assertEquals(output, wcApplication.countFromFiles(true, true, false, FILE_ONE));
    }

    @Test
    // wc [-cw]
    void countFromFiles_OneFileWithBytesAndWords_ReturnStringOfNumbers() throws Exception{
        long[] count = new long[]{2,3};
        String byteLength = String.format(NUMBER_FORMAT,FILE_ONE_CONTENT.getBytes().length);
        String output = String.format(NUMBER_FORMAT,count[1]) + byteLength + " " + FILE_ONE;
        assertEquals(output, wcApplication.countFromFiles(true, false, true, FILE_ONE));
    }

    @Test
    // wc [-lw]
    void countFromFiles_OneFileWithLinesAndWords_ReturnStringOfNumbers() throws Exception{
        long[] count = new long[]{2,3};
        String output =  String.format(NUMBER_FORMAT,count[0]) + String.format(NUMBER_FORMAT,count[1]) + " " + FILE_ONE;
        assertEquals(output, wcApplication.countFromFiles(false, true, true, FILE_ONE));
    }

    @Test
    void countFromFiles_MultipleFiles_ReturnStringOfNumbersWithTotal() throws Exception{
        String[] files = new String[] {FILE_ONE, FILE_TWO};
        long[] fileOneCount = new long[]{2,3};
        String fileOneNumBytes = String.format(NUMBER_FORMAT,FILE_ONE_CONTENT.getBytes().length);
        long[] fileTwoCount = new long[]{1,1};
        String fileTwoNumBytes = String.format(NUMBER_FORMAT,FILE_TWO_CONTENT.getBytes().length);
        long[] totalCount = new long[]{3,4};
        String totalNumBytes = String.format(NUMBER_FORMAT,(FILE_ONE_CONTENT + FILE_TWO_CONTENT).getBytes().length);
        List<String> output = new ArrayList<>();
        output.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneNumBytes + " " + FILE_ONE);
        output.add(String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoNumBytes + " " + FILE_TWO);
        output.add(new StringBuilder().append(String.format(NUMBER_FORMAT, totalCount[0])).append(String.format(NUMBER_FORMAT, totalCount[1])).append(totalNumBytes).append(TOTAL_TITLE).toString());
        String result = String.join(NEW_LINE, output);
        assertEquals(result, wcApplication.countFromFiles(true, true, true, files));
    }

    @Test
    void countFromFiles_MultipleFilesWithDirectory_ReturnStringOfNumbersWithTotal() throws Exception{
        String[] files = new String[] {FILE_ONE, SOURCE_DIRECTORY};
        List<String> output = new ArrayList<>();
        long[] fileOneCount = new long[]{2,3};
        String fileOneBytes = String.format(NUMBER_FORMAT,FILE_ONE_CONTENT.getBytes().length);
        output.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        output.add(DIRECTORY_MESSAGE);
        output.add(String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0)
                + " " + SOURCE_DIRECTORY);
        output.add(new StringBuilder().append(String.format(NUMBER_FORMAT, fileOneCount[0])).append(String.format(NUMBER_FORMAT, fileOneCount[1])).append(fileOneBytes).append(TOTAL_TITLE).toString());
        String result = String.join(NEW_LINE, output);
        assertEquals(result, wcApplication.countFromFiles(true, true, true, files));
    }

    @Test
    void countFromFiles_MultipleFilesWithDirectoryAndNonExistingFile_ReturnStringOfNumbersWithTotalWithErrorMessage() throws Exception{
        String[] files = new String[] {FILE_ONE, SOURCE_DIRECTORY, NON_EXIST_FILE};
        List<String> output = new ArrayList<>();
        long[] fileOneCount = new long[]{2,3};
        String fileOneBytes = String.format(NUMBER_FORMAT,FILE_ONE_CONTENT.getBytes().length);
        output.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        output.add(DIRECTORY_MESSAGE);
        output.add(String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0)
                + " " + SOURCE_DIRECTORY);
        output.add(FILE_NOT_FOUND);
        output.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + TOTAL_TITLE);
        String result = String.join(NEW_LINE, output);
        assertEquals(result, wcApplication.countFromFiles(true, true, true, files));
    }

    @Test
    void countFromStdin_NullStdin_ThrowsException() {
        assertThrows(Exception.class, () -> wcApplication.countFromStdin(true, true, true, null));
    }

    @Test
    void countFromStdin_OneLineInput_ReturnStringOfNumbers() throws Exception {
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        String stringOutput = wcApplication.countFromStdin(true, true, true, System.in);
        long[] stdinOne = new long[]{1,3};
        String stdinBytes = String.format(NUMBER_FORMAT,HUMAN_INPUT_ONE.getBytes().length);
        String result = String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinBytes;
        assertEquals(result, stringOutput); // should not return anything
    }

    @Test
    void countFromFileAndStdin_NullFilename_ThrowsException() {
        assertThrows(Exception.class, () -> wcApplication.countFromFileAndStdin(true, true, true, System.in, (String) null));
    }

    @Test
    void countFromFileAndStdin_NullStdin_ThrowsException() {
        assertThrows(Exception.class, () -> wcApplication.countFromFileAndStdin(true, true, true, null, (String) null));
    }

    @Test
    void countFromFileAndStdin_OneLineInputOneFile_ReturnStringOfNumbers() throws Exception {
        String[] files = new String[] {STDIN, FILE_ONE};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        long[] stdinOne = new long[]{1,3};
        String stdinBytes = String.format(NUMBER_FORMAT, HUMAN_INPUT_ONE.getBytes().length);
        List<String> output = new ArrayList<>();
        long[] fileOneCount = new long[]{2,3};
        String fileOneBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        long[] totalCount = new long[]{3,6};
        String totalBytes = String.format(NUMBER_FORMAT, (HUMAN_INPUT_ONE + FILE_ONE_CONTENT).getBytes().length);
        output.add(String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinBytes + " " + STDIN);
        output.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        output.add(String.format(NUMBER_FORMAT,totalCount[0]) + String.format(NUMBER_FORMAT,totalCount[1]) + totalBytes + TOTAL_TITLE);
        String result = String.join(NEW_LINE, output);
        String stringOutput = wcApplication.countFromFileAndStdin(true, true, true, System.in, files);
        assertEquals(result, stringOutput);
    }

    @Test
    void countFromFileAndStdin_OneLineInputOneNonExistentFile_ReturnStringOfNumbersWithTotal() throws Exception {
        String[] files = new String[] {STDIN, NON_EXIST_FILE};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        long[] stdinOne = new long[]{1,3};
        String stdinBytes = String.format(NUMBER_FORMAT, HUMAN_INPUT_ONE.getBytes().length);
        List<String> output = new ArrayList<>();
        output.add(String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinBytes + " " + STDIN);
        output.add(FILE_NOT_FOUND);
        output.add(String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinBytes + TOTAL_TITLE);
        String result = String.join(NEW_LINE, output);
        String stringOutput = wcApplication.countFromFileAndStdin(true, true, true, System.in, files);
        assertEquals(result, stringOutput);
    }

    @Test
    void countFromFileAndStdin_OneDirectoryOneLineInput_ReturnStringOfNumbersWithTotal() throws Exception {
        String[] files = new String[] {SOURCE_DIRECTORY, STDIN};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        long[] stdinOne = new long[]{1,3};
        String stdinBytes = String.format(NUMBER_FORMAT, HUMAN_INPUT_ONE.getBytes().length);
        List<String> output = new ArrayList<>();
        output.add(DIRECTORY_MESSAGE);
        output.add(String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0)
                + String.format(NUMBER_FORMAT,0) + " " + SOURCE_DIRECTORY);
        output.add(String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinBytes + " " + STDIN);
        output.add(String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinBytes + TOTAL_TITLE);
        String result = String.join(NEW_LINE, output);
        String stringOutput = wcApplication.countFromFileAndStdin(true, true, true, System.in, files);
        assertEquals(result, stringOutput);
    }

    @Test
    void countFromFileAndStdin_OneFileOneLineInputOneNonExistentFileOneDirectory_ReturnStringOfNumbersWithTotal() throws Exception {
        String[] files = new String[] {FILE_ONE, STDIN, NON_EXIST_FILE, SOURCE_DIRECTORY};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        long[] stdinOne = new long[]{1,3};
        String stdinBytes = String.format(NUMBER_FORMAT, HUMAN_INPUT_ONE.getBytes().length);
        List<String> output = new ArrayList<>();
        long[] fileOneCount = new long[]{2,3};
        String fileOneBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        long[] totalCount = new long[]{3,6};
        String totalBytes = String.format(NUMBER_FORMAT, (HUMAN_INPUT_ONE + FILE_ONE_CONTENT).getBytes().length);
        output.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        output.add(String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinBytes + " " + STDIN);
        output.add(FILE_NOT_FOUND);
        output.add(DIRECTORY_MESSAGE);
        output.add(String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0)
                + " " + SOURCE_DIRECTORY);
        output.add(String.format(NUMBER_FORMAT,totalCount[0]) + String.format(NUMBER_FORMAT,totalCount[1]) + totalBytes + TOTAL_TITLE);
        String result = String.join(NEW_LINE, output);
        String stringOutput = wcApplication.countFromFileAndStdin(true, true, true, System.in, files);
        assertEquals(result, stringOutput);
    }

    @Test
    void countFromFileAndStdin_OneStdinOneFileOneNonExistentFileOneDirectoryOneStdin_ReturnStringOfNumbersWithTotal() throws Exception {
        String[] files = new String[] {STDIN, FILE_ONE, NON_EXIST_FILE, SOURCE_DIRECTORY, STDIN};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        long[] stdinOne = new long[]{1,3};
        String stdinOneBytes = String.format(NUMBER_FORMAT, HUMAN_INPUT_ONE.getBytes().length);
        List<String> output = new ArrayList<>();
        long[] fileOneCount = new long[]{2,3};
        String fileOneBytes = String.format(NUMBER_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        long[] stdinTwo = new long[]{0,0,0};
        long[] totalCount = new long[]{3,6};
        String totalBytes = String.format(NUMBER_FORMAT, (HUMAN_INPUT_ONE + FILE_ONE_CONTENT).getBytes().length);
        output.add(String.format(NUMBER_FORMAT,stdinOne[0]) + String.format(NUMBER_FORMAT,stdinOne[1]) + stdinOneBytes + " " + STDIN);
        output.add(String.format(NUMBER_FORMAT,fileOneCount[0]) + String.format(NUMBER_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        output.add(FILE_NOT_FOUND);
        output.add(DIRECTORY_MESSAGE);
        output.add(String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0) + String.format(NUMBER_FORMAT,0)
                + " " + SOURCE_DIRECTORY);
        output.add(String.format(NUMBER_FORMAT,stdinTwo[0]) + String.format(NUMBER_FORMAT,stdinTwo[1]) + String.format(NUMBER_FORMAT,stdinTwo[2]) + " " + STDIN);
        output.add(String.format(NUMBER_FORMAT,totalCount[0]) + String.format(NUMBER_FORMAT,totalCount[1]) + totalBytes + TOTAL_TITLE);
        String result = String.join(NEW_LINE, output);
        String stringOutput = wcApplication.countFromFileAndStdin(true, true, true, System.in, files);
        assertEquals(result, stringOutput);
    }
}
