package sg.edu.nus.comp.cs4218.impl.integration;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_STREAM_CLOSED;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;

public class IORedirectionIntegrationTest {
    private static final String CURR_DIR = Environment.currentDirectory;
    private static final String NEW_LINE = System.lineSeparator();
    private static final String TEST_FILE1 = "testFile";
    private static final String OUTPUT_FILE = "output";
    private static final String TEST_DIR1 = "testDir1";
    private static final String DIR1_SUBFILE1 = "testDir1/subFile1";
    private static final String DIR1_OUTPUT = "testDir1/output";
    private static final String TEE_OUTPUT = "teeOutput";
    private static final String EMPTY_FILE = "emptyFile";
    private static final String SAME_FILE = "grepSameFile";
    private static final String PATTERN = "e";
    private static final String GREP_CONTENT = "Our love for CS4218 is unparalleled." + NEW_LINE + "WE lovE tEsting." + NEW_LINE + "But nobody loves testing more than me :)" + NEW_LINE + "MaybE ExcEpt... you?";
    private static final String SPLIT_DEFFILE = "xaa";
    private static final String HUMAN_INPUT = "Group 22 loves testing";
    private static final String INPUT_REDIR = " < ";
    private static final String OUTPUT_REDIR = " > ";
    private static final String DOUBLE_QUOTES = "\"";
    private static final String PIPE_OPERATOR = " | ";
    private static ByteArrayOutputStream outRes;
    private String commandString;
    private ShellImpl shell;

    @BeforeAll
    static void setupAll() {
        Environment.currentDirectory = CURR_DIR + CHAR_FILE_SEP + "testFolders" + CHAR_FILE_SEP + "util" + CHAR_FILE_SEP + "ioIntegrationFolder";
    }

    @AfterAll
    static void terminate() {
        Environment.currentDirectory = CURR_DIR;
    }

    @BeforeEach
    void setUp() throws IOException {
        outRes = new ByteArrayOutputStream();
        shell = new ShellImpl();
        ArrayList<String> emptyList = new ArrayList<>();
        ApplicationRunner appRunner = new ApplicationRunner();
    }

    // Output redirection only for echo
    @Test
    public void echoCommand_redirectOutputOnly_OutputsContent() throws Exception {
        commandString = "echo " + HUMAN_INPUT + OUTPUT_REDIR + OUTPUT_FILE;
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT.getBytes(StandardCharsets.UTF_8)));
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        assertEquals(HUMAN_INPUT + NEW_LINE, Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + OUTPUT_FILE), StandardCharsets.US_ASCII));
    }

    // output redirection only for ls
    @Test
    public void lsCommand_redirectOutputOnly_OutputsContent() throws Exception {
        commandString = APP_LS + CHAR_SPACE + OUTPUT_REDIR + OUTPUT_FILE;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        String expectedContent = EMPTY_FILE + NEW_LINE + SAME_FILE + NEW_LINE + OUTPUT_FILE + NEW_LINE + TEST_DIR1 + NEW_LINE + "testDir2" + NEW_LINE + TEST_FILE1 + NEW_LINE + "testFile2" + NEW_LINE;
        assertEquals(expectedContent, Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + OUTPUT_FILE), StandardCharsets.US_ASCII));
    }

    @Test
    public void wcCommand_redirectInputOnly_OutputsContent() throws Exception{
        commandString = APP_WC + INPUT_REDIR + TEST_FILE1;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        String expectedOut = "       4      20     118" + NEW_LINE;
        assertEquals(expectedOut, actualOut);
    }

    @Test
    public void wcCommand_redirectBoth_OutputsContent() throws Exception{
        commandString = APP_WC + INPUT_REDIR + TEST_FILE1 + OUTPUT_REDIR + OUTPUT_FILE;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        String expectedContent = "       4      20     118" + NEW_LINE;
        assertEquals(expectedContent, Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + OUTPUT_FILE), StandardCharsets.US_ASCII));
    }

    @Test
    public void catCommand_redirectInput_OutputsContent() throws Exception{
        commandString = APP_CAT + INPUT_REDIR + TEST_FILE1;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        String expectedOut = Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + TEST_FILE1), StandardCharsets.US_ASCII) + NEW_LINE;
        assertEquals(expectedOut, actualOut);
    }

    @Test
    public void catCommand_redirectBoth_OutputsContent() throws Exception{
        commandString = APP_CAT + INPUT_REDIR + TEST_FILE1 + OUTPUT_REDIR + OUTPUT_FILE;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        String expectedContent = Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + TEST_FILE1), StandardCharsets.US_ASCII) + NEW_LINE;
        assertEquals(expectedContent, Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + OUTPUT_FILE), StandardCharsets.US_ASCII));
    }

    @Test
    public void grepCommand_redirectInput_OutputsContent() throws Exception{
        commandString = APP_GREP + CHAR_SPACE +  PATTERN + INPUT_REDIR + TEST_FILE1;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        String expectedOut = "Our love for CS4218 is unparalleled." + NEW_LINE + "But nobody loves testing more than me :)" + NEW_LINE;
        assertEquals(expectedOut, actualOut);
    }

    @Test
    public void grepCommand_redirectBoth_OutputsContent() throws Exception{
        commandString = APP_GREP + CHAR_SPACE + PATTERN + INPUT_REDIR + TEST_FILE1 + OUTPUT_REDIR + OUTPUT_FILE;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        String expectedContent = "Our love for CS4218 is unparalleled." + NEW_LINE + "But nobody loves testing more than me :)" + NEW_LINE;
        assertEquals(expectedContent, Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + OUTPUT_FILE), StandardCharsets.US_ASCII));
    }

    @Test
    public void grepCommand_redirectBothSameFile_OutputsContent() throws Exception{
        commandString = APP_GREP + CHAR_SPACE + PATTERN + INPUT_REDIR + SAME_FILE + OUTPUT_REDIR + SAME_FILE;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        assertEquals("", Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + SAME_FILE), StandardCharsets.US_ASCII));
    }

    @Test
    public void teeCommand_redirectInput_OutputsContent() throws Exception{
        commandString = "tee < " + TEST_FILE1;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        String expectedOut = Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + TEST_FILE1), StandardCharsets.US_ASCII);
        assertEquals(expectedOut, actualOut);
    }

    // No output for split so no reason to redirect
    @Test
    public void splitCommand_redirectInput_OutputsContent() throws Exception{
        commandString = APP_SPLIT + INPUT_REDIR + TEST_FILE1;
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        String expectedContent = Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + TEST_FILE1), StandardCharsets.US_ASCII);
        assertEquals(expectedContent, Files.readString(Paths.get(CURR_DIR + CHAR_FILE_SEP + SPLIT_DEFFILE), StandardCharsets.US_ASCII)); // stdout replaced by OUTPUT);
    }

    @Test
    public void echoCommand_QuotingRedirectOutput_OutPutsContent() throws Exception{
        commandString = APP_ECHO + CHAR_SPACE + DOUBLE_QUOTES + HUMAN_INPUT + DOUBLE_QUOTES + CHAR_SPACE + OUTPUT_REDIR + OUTPUT_FILE;
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT.getBytes(StandardCharsets.UTF_8)));
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        assertEquals(HUMAN_INPUT + NEW_LINE, Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + OUTPUT_FILE), StandardCharsets.US_ASCII));
    }

    @Test
    public void grepCommand_GlobbingRedirectOutput_ReturnsErrorMessage() throws Exception {
        commandString = APP_GREP + CHAR_SPACE + PATTERN  +  INPUT_REDIR + TEST_FILE1 + "*" + OUTPUT_REDIR + OUTPUT_FILE;
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT.getBytes(StandardCharsets.UTF_8)));
        try {
            shell.parseAndEvaluate(commandString, System.out);
        } catch (Exception e) {
            String expectedContent = "shell: " + ERR_SYNTAX;
            assertArrayEquals(expectedContent.getBytes(), e.getMessage().getBytes());
        }
    }

    @Test
    public void echoPipeToGrep_RedirectOutput_WritesToFile() throws Exception {
        commandString = APP_ECHO + CHAR_SPACE + HUMAN_INPUT + PIPE_OPERATOR + APP_GREP + CHAR_SPACE + PATTERN + CHAR_SPACE + OUTPUT_REDIR + OUTPUT_FILE;
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT.getBytes(StandardCharsets.UTF_8)));
        String actualOut = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("", actualOut);
        assertEquals(HUMAN_INPUT + NEW_LINE, Files.readString(Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + OUTPUT_FILE), StandardCharsets.US_ASCII));
    }

    @Test
    void grep_TwoStdinInFILESInputRedirection_OutputsErrorMessage() throws Exception {
        commandString = APP_GREP + CHAR_SPACE + PATTERN + CHAR_SPACE + "- " + TEST_FILE1 + " - " + INPUT_REDIR + TEST_FILE1;
        try{
            shell.parseAndEvaluate(commandString, System.out);
        } catch (GrepException e) {
            String expectedOutput = APP_GREP + ": " + ERR_STREAM_CLOSED;
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    // untestable/No meaning
    // exit
    // cd
    // MV application
    // not tested as input and output do not show visible changes

//    Milestone1 END
}
