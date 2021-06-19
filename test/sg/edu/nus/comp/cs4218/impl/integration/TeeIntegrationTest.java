package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.*;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;

public class TeeIntegrationTest {

    private static final String INITIAL_DIRECTORY = Paths.get(Environment.currentDirectory).toString();
    private static final String NEW_LINE = System.lineSeparator();
    private static final String FILE_ONE = "tee_file1.txt";
    private static final String FILE_TWO = "tee_file2.txt";
    private static final String FILE_THREE = "tee_file3.txt";
    private static final String FILE_OUTPUT = "output.txt";
    private static final String FILE_NON_EXIST = "nonexist.file";
    private static final String FILE_ONE_CONTENT = "I love" + NEW_LINE + "CS4218" + NEW_LINE + NEW_LINE;
    private static final String FILE_TWO_CONTENT = "~!@#$%+";
    private static final String FILE_THREE_CONT = "\" ' < > * | ` \"\n" + "\\ $ % ; , . ? /";
    private static final String DIRECTORY = "myDirectory";
    private static final String HUMAN_INPUT_ONE = "I love CS4218" + NEW_LINE;
    private static final String NUMBER_FORMAT = " %7d";

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
        deleteFile(FILE_ONE);
        deleteFile(FILE_TWO);
        deleteFile(FILE_THREE);
        deleteFile(FILE_OUTPUT);
        deleteFile(FILE_NON_EXIST);
        deleteDirectory(DIRECTORY);
    }

    @AfterEach
    void setUp() {
        Environment.currentDirectory = INITIAL_DIRECTORY;
        deleteFile("output");
    }

    // ========================================  POSITIVE TEE INTEGRATION =========================================

    @Test
    public void tee_WithQuoting_DisplaysCatResults() throws Exception {
        outContent.reset();
        String commandString = "tee \"tee_file1.txt\" tee_file2.txt 'tee_file3.txt' ";
        ShellImpl shell = new ShellImpl();
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        shell.parseAndEvaluate(commandString, outContent);
        assertEquals(HUMAN_INPUT_ONE, outContent.toString());
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_ONE)));
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_TWO)));
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_THREE)));
    }

    // this tc currently is generate tee_file3.txt
    @Test
    public void tee_WithIORedirection_NoDisplays() throws Exception {
        outContent.reset();
        String commandString = "tee tee_file1.txt tee_file2.txt < tee_file3.txt > " + FILE_OUTPUT;
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        assertEquals(FILE_THREE_CONT, Files.readString(IOUtils.resolveFilePath(FILE_ONE)));
        assertEquals(FILE_THREE_CONT, Files.readString(IOUtils.resolveFilePath(FILE_TWO)));
        assertEquals(FILE_THREE_CONT, Files.readString(IOUtils.resolveFilePath(FILE_OUTPUT)));
        // Files.deleteIfExists(IOUtils.resolveFilePath(FILE_THREE));
    }

    @Test
    public void tee_WithSequence_DisplaysResultWithEcho() throws Exception {
        outContent.reset();
        String commandString = "tee tee_file1.txt tee_file2.txt; echo hello; cat tee_file1.txt";
        ShellImpl shell = new ShellImpl();
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        shell.parseAndEvaluate(commandString, outContent);
        String expected = HUMAN_INPUT_ONE + "hello" + NEW_LINE + HUMAN_INPUT_ONE;
        assertEquals(expected, outContent.toString());
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_ONE)));
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_TWO)));
    }

    @Test
    public void tee_WithGlobbing_DisplaysResultWithEcho() throws Exception {
        outContent.reset();
        String commandString = "tee tee_file* " + FILE_NON_EXIST;
        ShellImpl shell = new ShellImpl();
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        shell.parseAndEvaluate(commandString, outContent);
        assertEquals(HUMAN_INPUT_ONE, outContent.toString());
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_ONE)));
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_TWO)));
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_THREE)));
        assertEquals(HUMAN_INPUT_ONE, Files.readString(IOUtils.resolveFilePath(FILE_NON_EXIST)));
    }

    @Test
    public void tee_WithPipe_DisplaysResultForWc() throws Exception {
        outContent.reset();
        String commandString = "tee tee_file1.txt | wc ";
        ShellImpl shell = new ShellImpl();
        String humanInput = FILE_TWO;
        System.setIn(new ByteArrayInputStream(humanInput.getBytes())); // simulates human typing
        shell.parseAndEvaluate(commandString, outContent);
        assertEquals(humanInput, Files.readString(IOUtils.resolveFilePath(FILE_ONE)));
        long[] fileTwoCount = new long[]{1,1};
        String fileTwoNumBytes = String.format(NUMBER_FORMAT,FILE_TWO.getBytes().length);
        String expected = String.format(NUMBER_FORMAT,fileTwoCount[0]) + String.format(NUMBER_FORMAT,fileTwoCount[1]) + fileTwoNumBytes;
        expected += NEW_LINE;
        assertEquals(expected, outContent.toString());
    }

    // ========================================  NEGATIVE TEE INTEGRATION =========================================

    @Test
    public void tee_WithFileForOutputToGlobbing_ThrowsShellException() throws Exception {
        outContent.reset();
        String commandString = "tee " + FILE_ONE + " > *";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, outContent));
    }

    @Test
    public void tee_WithSequenceCmdButSecondCmdEndsWithSemicolon_ThrowsShellException() throws Exception {
        outContent.reset();
        String commandString = "tee " + FILE_ONE + "; tee " + FILE_TWO + ";";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, outContent));
    }


}
