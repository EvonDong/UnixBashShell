package sg.edu.nus.comp.cs4218.impl.app;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.writeFile;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.GrepException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.IS_DIRECTORY;

class GrepApplicationTest {

    GrepApplication grepApp;
    GrepApplication grepApplication;

    private static final String NEW_LINE = System.lineSeparator();
    private static final String EMPTY_FILE = "emptyFile";
    private static final String NON_EXIST_FILE = "nonExist";
    private static final String TEST_FILE1 = "fIlE-OnE";
    private static final String TEST_FILE2 = "FIle 2";
    private static final String TEST_DIR = "testDir";
    private static final String TEST_SUBDIR = "testDir/subFile";
    private static final String FILE1_CONTENT = "I really like" + NEW_LINE + "CS4218. LikE, sEriously";
    private static final String FILE2_CONTENT = "~!@#$%+e";
    private static final String PATTERN = "e";
    private static final String NON_EXIST_ERR_MSG = ": No such file or directory" + NEW_LINE;
    private static final String IS_DIR_ERR_MSG = ": " + IS_DIRECTORY + NEW_LINE;

    private static final String HUMAN_INPUT_ONE = "I really like" + NEW_LINE + "CS4218. LikE, sEriously" + NEW_LINE;
    private static final String HUMAN_INPUT_TWO = "Testing is so fun" + NEW_LINE;
    private static final String OUTPUT_FILE = "output";
    private static final String STDIN_NAME = "(standard input)";
    private final String CURR_DIR = Environment.currentDirectory;

    @BeforeAll
    static void init() throws IOException {
        createFile(EMPTY_FILE);
        createFile(OUTPUT_FILE);
        createFile(TEST_FILE1);
        writeFile(TEST_FILE1, FILE1_CONTENT);
        createFile(TEST_FILE2);
        writeFile(TEST_FILE2, FILE2_CONTENT);
    }

    @AfterAll
    static void terminate() {
        deleteFile(EMPTY_FILE);
        deleteFile(TEST_FILE1);
        deleteFile(TEST_FILE2);
    }

    @BeforeEach
    void setUp() {
        grepApplication = new GrepApplication();
        grepApp = spy(GrepApplication.class);
        Environment.currentDirectory = CURR_DIR.concat( CHAR_FILE_SEP + "TestFolders" + CHAR_FILE_SEP + "app" + CHAR_FILE_SEP + "GrepTestFolder");
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteFile(OUTPUT_FILE);
        createFile(OUTPUT_FILE);
        Environment.currentDirectory = CURR_DIR;
    }

    @Test
    void run_MissingRegex_ReturnsErrorMessage() {
        try {
            grepApp.run(new String[]{}, System.in, System.out);
        } catch (Exception e) {
            String expectedContents = "grep: No regular expression supplied";
            assertEquals(expectedContents, e.getMessage());
        }
    }

    @Test
    void run_StdinNullAndNoFiles_ReturnsErrorMessage() {
        try {
            grepApp.run(new String[]{PATTERN}, null, System.out);
        } catch (Exception e) {
            String expectedContents = "grep: No InputStream and no filenames";
            assertEquals(expectedContents, e.getMessage());
        }
    }

    @Test
    void grepFromFiles_NullFile_ThrowsException() {
        assertThrows(Exception.class, () -> grepApplication.grepFromFiles(PATTERN, false, false, false, (String) null));
    }

    @Test
    void grepFromFiles_NonExistingFile_ReturnsErrorMessage() throws Exception {
        assertEquals(NON_EXIST_FILE + NON_EXIST_ERR_MSG, grepApplication.grepFromFiles(PATTERN, false, false, false, NON_EXIST_FILE));
    }

    @Test
    void grepFromFiles_FileNameIsDirectory_ReturnsErrorMessage() throws Exception {
        String expectedContent = TEST_DIR + IS_DIR_ERR_MSG;
        assertEquals(expectedContent, grepApplication.grepFromFiles(PATTERN, false, false, false,  TEST_DIR));
    }

    @Test
    void grepFromFiles_NoFlagOneFile_ReturnsLineCountsMatchesWithPattern() throws Exception {
        String expectedContent = "I really like" + NEW_LINE;
        assertEquals(expectedContent, grepApplication.grepFromFiles(PATTERN, false, false, false, TEST_FILE1));
    }

    // sub-directory path check
    @Test
    void grepFromFiles_OneFileInSubDirectoryWithOneFlagIFlag_ReturnsLineMatchesWithPattern() throws Exception {
        String expectedContent = "subFile1" + NEW_LINE + "subFilE2" + NEW_LINE;
        assertEquals(expectedContent, grepApplication.grepFromFiles(PATTERN, true, false, false, TEST_SUBDIR));
    }

    @Test
    void grepFromFiles_OneFlagIFlagFileStdinManyFiles_ReturnsLineMatchesWithPattern() throws Exception {
        InputStream stdin = null;
        try {
            stdin = new FileInputStream(Environment.currentDirectory + CHAR_FILE_SEP + TEST_SUBDIR);
            String expectedContent = TEST_FILE1 + ": I really like" + NEW_LINE + TEST_FILE1 + ": CS4218. LikE, sEriously" + NEW_LINE + TEST_FILE2 + ": ~!@#$%+e" + NEW_LINE + STDIN_NAME + ": subFile1" + NEW_LINE + STDIN_NAME + ": subFilE2" + NEW_LINE;
            assertEquals(expectedContent, grepApplication.grepFromFileAndStdin(PATTERN, true, false, false, stdin, TEST_FILE1, TEST_FILE2, "-"));
        } finally {
            assert stdin != null;
            stdin.close();
        }
    }

    @Test
    void grepFromStdin_AllFlagsFileStdinOneFile_ReturnsLineMatchesWithPattern() throws Exception {
        InputStream stdin = null;
        try {
            stdin = new FileInputStream(Environment.currentDirectory + CHAR_FILE_SEP + TEST_FILE2);
            String expectedContent = STDIN_NAME + ": 1" + NEW_LINE + TEST_FILE1 + ": 2" + NEW_LINE;
            assertEquals(expectedContent, grepApplication.grepFromFileAndStdin(PATTERN, true, true, true, stdin, "-", TEST_FILE1));
        } finally {
            assert stdin != null;
            stdin.close();
        }
    }

    @Test
    void grepFromStdin_AllFlags_ReturnsLineCountsMatchesWithPattern() throws Exception {
        InputStream stdin = null;
        try {
            stdin = new FileInputStream(Environment.currentDirectory + CHAR_FILE_SEP + TEST_FILE1);
            String expectedContent = STDIN_NAME + ": 2" + NEW_LINE;
            assertEquals(expectedContent, grepApplication.grepFromStdin(PATTERN, true, true, true, stdin));
        } finally {
            assert stdin != null;
            stdin.close();
        }
    }

    @Test
    void grepFromStdin_OneFlagHFlagSystemStdin_ReturnsLineMatchesWithPattern() throws Exception {
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes(StandardCharsets.UTF_8)));
        String expectedOutput = STDIN_NAME + ": I really like" + NEW_LINE;
        assertEquals(expectedOutput, grepApplication.grepFromStdin(PATTERN, false, false, true, System.in));
    }

    @Test
    void grepFromFileAndStdin_MultiFlagIFlagHFlagSystemStdin_ReturnsLineMatchesWithPattern() throws Exception {
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes(StandardCharsets.UTF_8)));
        String expectedOutput = STDIN_NAME + ": I really like" + NEW_LINE + STDIN_NAME + ": CS4218. LikE, sEriously" + NEW_LINE + TEST_SUBDIR + ": subFile1" + NEW_LINE + TEST_SUBDIR + ": subFilE2" + NEW_LINE;
        assertEquals(expectedOutput, grepApplication.grepFromFileAndStdin(PATTERN, true, false, true, System.in, "-", TEST_SUBDIR));
    }

    @Test
    public void run_PreClosedFileInputStreamNoFile_ExceptionThrown() throws Exception {
        String[] args = {PATTERN};
        try (InputStream stdin = new FileInputStream(TEST_FILE1)) {
            stdin.close();
            try (OutputStream out = new FileOutputStream(EMPTY_FILE)) {
                assertThrows(Exception.class, () -> grepApp.run(args, stdin, out));
            }
        }
    }

    @Test
    public void run_PreClosedFileOutputStreamNoFile_ExceptionThrown() throws Exception {
        String[] args = {PATTERN};
        try (InputStream stdin = new FileInputStream(TEST_FILE1)) {
            try (OutputStream out = new FileOutputStream(EMPTY_FILE)) {
                out.close();
                assertThrows(Exception.class, () -> grepApp.run(args, stdin, out));
            }
        }
    }

    @Test
    void run_NullPattern_ReturnsErrorMessage() throws Exception {
        try {
            String[] args = new String[0];
            grepApplication.run(args, System.in, System.out);
        } catch (GrepException e) {
            String expectedContent = "grep: " + ERR_NO_REGEX;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    // Requires substitution to be completed
//    @Test
//    void run_EmptyPattern_ReturnsErrorMessage() throws Exception {
//        try {
//            String[] args = new String[]{""};
//            grepApp.run(args, System.in, System.out);
//        } catch (GrepException e) {
//            String expectedContent = "grep: " + ERR_EMPTY_REGEX;
//            assertEquals(expectedContent, e.getMessage());
//        }
//    }

    @Test
    public void run_InvalidOptionFileProvided_OutputsErrorMessage() throws Exception {
        String[] args = new String[]{"-a", PATTERN, TEST_FILE1};
        String expectedOutput = PATTERN + NON_EXIST_ERR_MSG;
        String actualOutput = tapSystemOut(() -> {
            grepApp.run(args, System.in, System.out);
        });
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void run_RepeatedFlagsIFlag_OutputsContent() throws Exception {
        String[] args = new String[]{"-i", "-i", PATTERN, TEST_FILE1};
        String expectedOutput = FILE1_CONTENT + NEW_LINE;
        String actualOutput = tapSystemOut(() -> {
            grepApp.run(args, System.in, System.out);
        });
        assertEquals(expectedOutput, actualOutput);
    }
}