package sg.edu.nus.comp.cs4218.impl.util;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

class IORedirectionTest {
    private static final String CURR_DIR = Environment.currentDirectory;
    private static final String PATTERN = "e";
    private static final String OUTPUT_FILE = "output";
    private final static String ERR_FILE_COUNT = "Too many files provided for redirection";
    private static final String SHELL_LITERAL = "shell: ";
    private static final String GREP_KEYWORD = "grep";
    private static final String TEST_FILE1 = "File1";
    private static final String EMPTY_FILE = "emptyFile";
    private static final String SUBFILE = "testDir/subFile";
    private static final String NEW_LINE = System.lineSeparator();
    private static ByteArrayOutputStream outRes;
    private final InputStream origInputStream = System.in;
    private final OutputStream origOutputStream = System.out;
    private IORedirectionHandler ioHandler;
    private ArgumentResolver argResolver;

    @BeforeAll
    static void setupAll() {
        Environment.currentDirectory = CURR_DIR + CHAR_FILE_SEP + "TestFolders" + CHAR_FILE_SEP + "app" + CHAR_FILE_SEP + "IORedirectionFolder";
    }

    @AfterAll
    static void terminate() {
        Environment.currentDirectory = CURR_DIR;
    }

    @BeforeEach
    void setUp() {
        outRes = new ByteArrayOutputStream();
        ArrayList<String> emptyList = new ArrayList<>();
        argResolver = new ArgumentResolver();
    }

    @Test
    public void extractRedirOptions_ArgsListContainsMoreThanOneFileAfterInputRedirection_ReturnsErrorMessage() throws Exception {
        List<String> argsList = Arrays.asList("wc", "<", TEST_FILE1, EMPTY_FILE);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (ShellException e) {
            String expectedContent = SHELL_LITERAL + ERR_FILE_COUNT;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    public void extractRedirOptions_ArgsListContainsMoreThanOneFileAfterOutputRedirection_ReturnsErrorMessage() throws Exception {
        List<String> argsList = Arrays.asList("wc", TEST_FILE1, ">", EMPTY_FILE, SUBFILE);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (ShellException e) {
            String expectedContent = SHELL_LITERAL + ERR_FILE_COUNT;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    public void extractRedirOptions_ArgsListContainsAdjacentRedirectionOperators_ReturnsErrorMessage() throws Exception {
        List<String> argsList = Arrays.asList("wc", TEST_FILE1, ">", ">", EMPTY_FILE, SUBFILE);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (ShellException e) {
            String expectedContent = SHELL_LITERAL + ERR_SYNTAX;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    public void extractRedirOptions_DirectoryAsRedirectionTarget_ReturnsErrorMessage() throws Exception {
        final String testDir = "testDir";
        List<String> argsList = Arrays.asList("wc", "<", testDir);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (ShellException e) {
            String expectedContent = SHELL_LITERAL + testDir + ": " + ERR_FILE_NOT_FOUND;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    public void extractRedirOptions_NoInputRedirectionTarget_ReturnsErrorMessage() throws Exception {
        List<String> argsList = Arrays.asList("wc", "<");
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (ShellException e) {
            String expectedContent = SHELL_LITERAL + ERR_MISSING_ARG;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    public void extractRedirOptions_NoOutputRedirectionTarget_ReturnsErrorMessage() throws Exception {
        List<String> argsList = Arrays.asList("wc", TEST_FILE1, ">");
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (Exception e) {
            String expectedContent = SHELL_LITERAL + ERR_MISSING_ARG;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    public void extractRedirOptions_ArgsListContainsMultipleInputRedirectionTarget_ReturnsErrorMessage() throws Exception {
        List<String> argsList = Arrays.asList("wc", "<", TEST_FILE1, "<", SUBFILE);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (ShellException e) {
            String expectedContent = SHELL_LITERAL + ERR_MULTIPLE_STREAMS;
            assertEquals(expectedContent, e.getMessage());
        }
    }



    @Test
    public void extractRedirOptions_ArgsListContainsMultipleOutputRedirectionTarget_ReturnsErrorMessage() throws Exception {
        List<String> argsList = Arrays.asList("wc", "<", TEST_FILE1, "<", SUBFILE);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        try {
            ioHandler.extractRedirOptions();
        } catch (ShellException e) {
            String expectedContent = SHELL_LITERAL + ERR_MULTIPLE_STREAMS;
            assertEquals(expectedContent, e.getMessage());
        }
    }

    @Test
    public void grepCommandArgs_RedirectInputOnly_ChangesInputStream() throws Exception {
        List<String> argsList = Arrays.asList(GREP_KEYWORD, PATTERN, "<", TEST_FILE1);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        ioHandler.extractRedirOptions();
        assertNotEquals(origInputStream, ioHandler.getInputStream());
        List<String> expList = Arrays.asList(GREP_KEYWORD, PATTERN);
        assertEquals(expList, ioHandler.getNoRedirArgsList());
    }

    @Test
    public void grepCommandArgs_RedirectOutputOnly_ChangesOutputStream() throws Exception{
        List<String> argsList = Arrays.asList(GREP_KEYWORD, PATTERN, ">", OUTPUT_FILE);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        ioHandler.extractRedirOptions();
        assertNotEquals(origOutputStream, ioHandler.getOutputStream());
        List<String> expList = Arrays.asList(GREP_KEYWORD, PATTERN);
        assertEquals(expList, ioHandler.getNoRedirArgsList());
    }

    @Test
    public void grepCommandArgs_RedirectInputOutput_ChangesBothStream() throws Exception{
        List<String> argsList = Arrays.asList(GREP_KEYWORD, PATTERN, "<", TEST_FILE1, ">", OUTPUT_FILE);
        ioHandler= new IORedirectionHandler(argsList, origInputStream, origOutputStream, argResolver);
        ioHandler.extractRedirOptions();
        assertNotEquals(origInputStream, ioHandler.getInputStream());
        assertNotEquals(origOutputStream, ioHandler.getOutputStream());
        List<String> expList = Arrays.asList(GREP_KEYWORD, PATTERN);
        assertEquals(expList, ioHandler.getNoRedirArgsList());
    }

}