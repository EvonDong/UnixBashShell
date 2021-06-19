package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.SplitException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_INPUT;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.IS_DIRECTORY;

class SplitApplicationTest {
    SplitApplication splitApp;
    SplitApplication splitApplication;

    private static final String NEW_LINE = System.lineSeparator();
    private static final String EMPTY_FILE = "emptyFile";
    private static final String NON_EXIST_FILE = "nonExist";
    private static final String DEFAULT_FILE = "defaultTest";
    private static final String DEF_OUT1 = "xaa";
    private static final String DEF_OUT2 = "xab";
    private static final String DEFLINES_OUT1 = "defLines1";
    private static final String DEFKBYTES_OUT1 = "defKBytes1";
    private static final String DEFKBYTES_OUT2 = "defKBytes2";
    private static final String PREFIX = "TeSt";
    private static final String PREFIX_OUT1 = "TeStaa";
    private static final String PREFIX_OUT2 = "TeStab";
    private static final String TEST_DIR = "testDir";
    private static final String NON_EXIST_ERR_MSG = ": No such file or directory";
    private static final String IS_DIR_ERR_MSG = ": " + IS_DIRECTORY;
    private static final int LINES_FACTOR = 2;
    private static final String BYTES_FACTOR = "20";
    private static final int DEFAULT_LINES = 1000;
    private static final String SPLIT_LITERAL = "split: ";

    private static final String HUMAN_INPUT_ONE = "I love CS4218" + NEW_LINE + "Testing is" + NEW_LINE + "so fun :(" + NEW_LINE;
    private static final String OUTPUT_FILE = "output";
    private final String CURR_DIR = Environment.currentDirectory;

    @BeforeAll
    static void init() throws IOException {
        createFile(EMPTY_FILE);
        createFile(OUTPUT_FILE);
    }

    @AfterAll
    static void terminate() {
        deleteFile(EMPTY_FILE);
        deleteFile(DEF_OUT1);
        deleteFile(DEF_OUT2);
        deleteFile(PREFIX_OUT1);
        deleteFile(PREFIX_OUT2);
    }

    @BeforeEach
    void setUp() {
        splitApplication = new SplitApplication();
        splitApp = spy(SplitApplication.class);
        Environment.currentDirectory = CURR_DIR.concat(CHAR_FILE_SEP + "TestFolders" + CHAR_FILE_SEP + "app" + CHAR_FILE_SEP + "splitTestFolder");
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteFile(OUTPUT_FILE);
        createFile(OUTPUT_FILE);
        Environment.currentDirectory = CURR_DIR;
    }

    @Test
    void run_InputFileNonExistent_ThrowsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + NON_EXIST_FILE + NON_EXIST_ERR_MSG;
        String[] args = new String[]{NON_EXIST_FILE};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void splitFileByLines_InputFileNameIsDirectory_ReturnsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + TEST_DIR + IS_DIR_ERR_MSG;
        String[] args = new String[]{TEST_DIR};
        try {
            splitApplication.splitFileByLines(TEST_DIR, null, DEFAULT_LINES);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_StdInNullAndNoInputFiles_ReturnsErrorMessage() throws Exception {
        String expectedContent = "split: " + ERR_NO_INPUT;
        String[] args = new String[]{};
        try {
            splitApplication.run(args, null, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void splitFileByLines_NullFile_ThrowsException() throws Exception {
        assertThrows(Exception.class, () -> splitApplication.splitFileByLines(null, null,DEFAULT_LINES));
    }

    @Test
    void splitFileByBytes_NullFile_ThrowsException() throws Exception {
        assertThrows(Exception.class, () -> splitApplication.splitFileByBytes(null, null,"" + DEFAULT_LINES));
    }

    @Test
    void splitFileByLines_FileNameIsDirectory_ReturnsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + TEST_DIR + IS_DIR_ERR_MSG;
        try {
            splitApplication.splitFileByLines(TEST_DIR, null, DEFAULT_LINES);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void splitFileByBytes_FileNameIsDirectory_ReturnsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + TEST_DIR + IS_DIR_ERR_MSG;
        try {
            splitApplication.splitFileByBytes(TEST_DIR, null, "" + DEFAULT_LINES);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void splitFileByLines_NonExistingFileName_ReturnsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + NON_EXIST_FILE + NON_EXIST_ERR_MSG;
        try {
            splitApplication.splitFileByLines(NON_EXIST_FILE, null, DEFAULT_LINES);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void splitFileByBytes_NonExistingFileName_ReturnsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + NON_EXIST_FILE + NON_EXIST_ERR_MSG;
        try {
            splitApplication.splitFileByBytes(NON_EXIST_FILE, null, "1000");
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_LinesFlagNegativeFactor_ReturnsErrorMessage() throws Exception {
        String expectedContent = "split: invalid number of lines: '-1000'";
        String[] args = new String[]{"-l", "-1000"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_BytesFlagNegativeFactor_ReturnsErrorMessage() throws Exception {
        String expectedContent = "split: invalid number of bytes: '-1000'";
        String[] args = new String[]{"-b", "-1000"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_LinesFlagZeroFactor_ReturnsErrorMessage() throws Exception {
        String expectedContent = "split: invalid number of lines: '0'";
        String[] args = new String[]{"-l", "0"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_BytesFlagZeroFactor_ReturnsErrorMessage() throws Exception {
        String expectedContent = "split: invalid number of bytes: '0'";
        String[] args = new String[]{"-b", "0"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_BytesFlagNoFactor_ReturnsErrorMessage() throws Exception {
        String expectedContent = "split: option requires an argument -- b";
        String[] args = new String[]{"-b"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }


    @Test
    void run_IllegalFlag_ReturnsErrorMessage() throws Exception {
        String expectedContent = "split: Invalid syntax";
        String[] args = new String[]{"-a", "0"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_AllFlags_ReturnsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + "-l" + NON_EXIST_ERR_MSG;
        String[] args = new String[]{"-b", "200", "-l"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void run_NoFlagsTooPrefixArgumentsProvided_ReturnsErrorMessage() throws Exception {
        String expectedContent = SPLIT_LITERAL + ERR_SYNTAX;
        String[] args = new String[]{DEFAULT_FILE, PREFIX, "PREFIX2"};
        try {
            splitApplication.run(args, System.in, System.out);
        } catch (SplitException e) {
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    void splitStdinByLines_PositiveFactorNoPrefix_WritesToFile() throws Exception {
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes(StandardCharsets.UTF_8)));
        String expectedContent1 = "I love CS4218" + NEW_LINE + "Testing is";
        splitApplication.splitStdinByLines(System.in, null, LINES_FACTOR);
        String outputContent1 = Files.readString(Paths.get(CURR_DIR + "/" + DEF_OUT1), StandardCharsets.US_ASCII);
        assertEquals(expectedContent1, outputContent1);
        String expectedContent2 = "so fun :(" + NEW_LINE;
        String outputContent2 = Files.readString(Paths.get(CURR_DIR + "/" + DEF_OUT2), StandardCharsets.US_ASCII);
        assertEquals(expectedContent2, outputContent2);
    }

    @Test
    void splitFileByLines_NoFlagsNoPrefix_WritesToFile() throws Exception {
        String expectedContent1 = Files.readString(Paths.get(Environment.currentDirectory + "/" + DEFLINES_OUT1));
        splitApplication.splitFileByLines(DEFAULT_FILE, null, DEFAULT_LINES);
        String outputContent1 = Files.readString(Paths.get(CURR_DIR + "/" + DEF_OUT1), StandardCharsets.US_ASCII);
        assertEquals(expectedContent1, outputContent1);
        String expectedContent2 = "test1001";
        String outputContent2 = Files.readString(Paths.get(CURR_DIR + "/" + DEF_OUT2));
        assertEquals(expectedContent2, outputContent2);
    }

    @Test
    void splitStdinByBytes_PositiveFactorNoPostfixPrefix_WritesToFile() throws Exception {
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes(StandardCharsets.UTF_8)));
        byte[] expBytesArr = HUMAN_INPUT_ONE.getBytes(StandardCharsets.UTF_8);
        String expectedContent1 = new String(Arrays.copyOfRange(expBytesArr, 0, Integer.parseInt(BYTES_FACTOR)), StandardCharsets.UTF_8);
        splitApplication.splitStdinByBytes(System.in, PREFIX, BYTES_FACTOR);
        String outputContent1 = Files.readString(Paths.get(CURR_DIR + "/" + PREFIX_OUT1), StandardCharsets.US_ASCII);
        assertEquals(expectedContent1, outputContent1);
        String expectedContent2 = new String(Arrays.copyOfRange(expBytesArr, Integer.parseInt(BYTES_FACTOR), expBytesArr.length), StandardCharsets.UTF_8);
        String outputContent2 = Files.readString(Paths.get(CURR_DIR + "/" + PREFIX_OUT2), StandardCharsets.US_ASCII);
        assertEquals(expectedContent2, outputContent2);
    }

    @Test
    void splitFileByBytes_PositiveFactorKPostfixPrefix_WritesToFile() throws Exception {
        splitApplication.splitFileByBytes(DEFAULT_FILE, PREFIX, "7" + "k");
        byte[] expBytesArr = Files.readString(Paths.get(Environment.currentDirectory + "/" + DEFAULT_FILE), StandardCharsets.US_ASCII).getBytes(StandardCharsets.UTF_8);
        String expectedContent1 = new String(Arrays.copyOfRange(expBytesArr, 0, 7 * 1024), StandardCharsets.UTF_8);
        String outputContent1 = Files.readString(Paths.get(CURR_DIR + "/" + PREFIX_OUT1), StandardCharsets.US_ASCII);
        assertEquals(expectedContent1, outputContent1);
        String expectedContent2 = new String(Arrays.copyOfRange(expBytesArr, 7 * 1024, expBytesArr.length), StandardCharsets.UTF_8);
        String outputContent2 = Files.readString(Paths.get(CURR_DIR + "/" + PREFIX_OUT2), StandardCharsets.US_ASCII);
        assertEquals(expectedContent2, outputContent2);
    }

}

