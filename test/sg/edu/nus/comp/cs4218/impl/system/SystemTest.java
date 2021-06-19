package sg.edu.nus.comp.cs4218.impl.system;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.app.SplitApplication.*;
import static sg.edu.nus.comp.cs4218.impl.app.UniqApplication.ERR_MUTUAL_EXC;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.IORedirectionHandler.ERR_FILE_COUNT;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class SystemTest {
    public static final String TEMP = "temp";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static final String EMPTY_DIR = "emptyDirectory";
    public static final Path EMPTY_DIR_PATH = Paths.get(Environment.currentDirectory, EMPTY_DIR);
    private static final String LINE1 = "I really like";
    private static final String LINE2 = "CS4218. LikE, sEriously";
    private static final String LINE3 = "Testing is so fun";
    private static final String STRING_A = LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE;
    private static final String STRING_B = LINE3 + STRING_NEWLINE;
    public static final byte[] BYTES_A = STRING_A.getBytes();
    public static final byte[] BYTES_B = STRING_B.getBytes();
    public static Deque<Path> files = new ArrayDeque<>();
    private static final String FILE_ONE = "testFile";
    private static final String FILE_TWO = "testFile2";
    private static final String OUTPUT_FILE = "output";
    private static final String STDIN_FILEPREFIX = "(standard input): ";
    private static final String OUTPUT_FILE_XAA = "xaa";
    private static final String OUTPUT_FILE_XAB = "xab";
    private static final String OUTPUT_FILE_XAC = "xac";
    private static final Integer LINES_FACTOR = 1;
    public static final String UNIQLINE1 = "I love CS4218";
    public static final String UNIQLINE2 = "Alice";
    public static final String UNIQLINE3 = "Bob";
    private static final String UNIQSTRING_A = UNIQLINE1 + STRING_NEWLINE + UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3;
    public static final byte[] UNIQBYTES_A = UNIQSTRING_A.getBytes();
    private static final String WC_NUM_FORMAT = " %7d";
    private static final String WC_TOTAL_POSTFIX = " total";

    @BeforeAll
    static void createTemp() throws IOException {
        Files.createDirectory(TEMP_PATH);
    }

    @AfterAll
    static void deleteTemp() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
        Files.delete(TEMP_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(getRelativePath(FILE_ONE));
        Files.deleteIfExists(getRelativePath(FILE_TWO));
        Files.deleteIfExists(getRelativePath(OUTPUT_FILE));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE_XAA));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE_XAB));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE_XAC));
        Files.deleteIfExists(Paths.get(EMPTY_DIR));

    }

    private Path createFile(String name) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.createFile(path);
        files.push(path);
        return path;
    }

    private Path getRelativePath(String name) {
        return Paths.get(TEMP, name);
    }

    @Test
    void redirQuote_DoubleQuotesRedirCatInput_OutputsContent() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_CAT + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + CHAR_DOUBLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_DOUBLE_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(STRING_A.getBytes(), output.toByteArray());
    }

    @Test
    void pipeBeforeSequence_EchoPipeCatSequenceEcho_OutputsCatEchoResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + "pipe before sequence" + CHAR_SPACE + CHAR_PIPE + CHAR_SPACE + APP_CAT + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "after sequence";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("pipe before sequence" + STRING_NEWLINE + "after sequence" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void pipeAfterSequence_EchoSemicolonEchoPipeCat_OutputsEchoCatResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + "before sequence" + CHAR_SEMICOLON + CHAR_SPACE + APP_ECHO + CHAR_SPACE + "pipe after sequence" + CHAR_PIPE + CHAR_SPACE + APP_CAT;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("before sequence" + STRING_NEWLINE + "pipe after sequence" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void sequenceInCommandSubstution_Echo_OutputsEchoResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + CHAR_BACK_QUOTE + APP_ECHO + CHAR_SPACE + "in command substitution 1" + CHAR_SEMICOLON + CHAR_SPACE + APP_ECHO + CHAR_SPACE + "in command substitution 2" + CHAR_BACK_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("in command substitution 1" + CHAR_SPACE + "in command substitution 2" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void pipeInCommandSubstution_Echo_OutputsEchoResults() throws IOException, AbstractApplicationException, ShellException {
        Files.write(createFile(FILE_ONE), BYTES_A);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + CHAR_BACK_QUOTE + APP_CAT + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_PIPE + CHAR_SPACE + APP_CAT + CHAR_BACK_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((LINE1 + CHAR_SPACE + LINE2 + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void quotingPipeInCommandSubstution_Echo_OutputsEchoResults() throws IOException, AbstractApplicationException, ShellException {
        Files.write(createFile(FILE_ONE), BYTES_A);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + CHAR_BACK_QUOTE + APP_CAT + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_PIPE + CHAR_SPACE + APP_CAT + CHAR_BACK_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((LINE1 + CHAR_SPACE + LINE2 + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    public void testSemiColonPipe_LsEchoPipeToPaste_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String pasteStringA =
                "I really like" + STRING_NEWLINE + CHAR_TAB + "CS4218. LikE, sEriously" + STRING_NEWLINE;
        Path filePath = createFile(FILE_ONE);
        Path filePath2 = createFile(FILE_TWO);
        Files.write(filePath, BYTES_A);
        Files.write(filePath2, BYTES_B);

        String commandString = "ls " + getRelativePath(FILE_TWO) + "; echo \"supp\" | paste - " + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((getRelativePath(FILE_TWO) + STRING_NEWLINE + "supp"  + CHAR_TAB + pasteStringA).getBytes(),
                output.toByteArray());
    }

    @Test
    public void testSemiColonPipe_EchoEchoPipeToPaste_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String pasteStringA = LINE1 + STRING_NEWLINE + CHAR_TAB + LINE2 + STRING_NEWLINE;
        Path filePath = createFile(FILE_ONE);
        Files.write(filePath, BYTES_A);

        String commandString = "echo \"hi \"; echo \" supp \" | paste - " + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("hi "  + STRING_NEWLINE + " supp " + CHAR_TAB + pasteStringA).getBytes(),
                output.toByteArray());
    }

    @Test
    public void testPipeIORedirection_EchoPipeToPasteRedirectToFile_DisplayEmptyResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Path filePath = createFile(FILE_ONE);
        Files.write(filePath, BYTES_A);

        String commandString = "echo \"hii\" | paste - " + getRelativePath(FILE_ONE) + " > AB.txt";
        File tempFile = new File(String.valueOf(IOUtils.resolveFilePath("AB.txt")));
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals("".getBytes(StandardCharsets.UTF_8) , output.toByteArray());
        assertTrue(tempFile.exists());
        assertArrayEquals(("").getBytes(), output.toByteArray());
        Files.delete(Paths.get("AB.txt"));
    }

    @Test
    public void testSequenceIORedirection_CatFromFileInputEchoCpDoubleOverwriting_DisplayDiffResultsForEachCat() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        Path fileOnePath = getRelativePath(FILE_ONE);
        Path fileTwoPath = getRelativePath(FILE_TWO);

        // cat - < testFile; echo hello > testFile; cat testFile; cp testFile2 testFile; cat testFile
        String commandString = "cat - < " + fileOnePath + "; echo echo > " + fileOnePath + "; cat " + fileOnePath + "; "
                             + "cp "  + fileTwoPath + " " + fileOnePath + "; cat " + fileOnePath;
        String expectedOutput = STRING_A + "echo" + STRING_NEWLINE + STRING_B;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(expectedOutput.getBytes(StandardCharsets.UTF_8) , output.toByteArray());
    }

    @Test
    public void testCommandSubSequencePipe_cdIntoDoublyEchoedPathThenLsPipeGrep_ChangesDirAndDisplayGrepResult() throws Exception {
        String initialDirectory = Environment.currentDirectory;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd `echo testFiles/``echo cat`; ls | grep e.txt";
        String expectedOutput = "emptyFile.txt" + STRING_NEWLINE + "fileOne.txt" + STRING_NEWLINE + "fileThree.txt" + STRING_NEWLINE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(expectedOutput.getBytes(StandardCharsets.UTF_8) , output.toByteArray());
        Environment.currentDirectory = initialDirectory;
    }

    @Test
    void testQuotingPipeIORedirectionSequence_MultipleEchoesAndCats_DisplaysEchoResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo cat > dog | echo 'cat > dog' > 'cat dog' | cat dog - < 'cat dog' ; cat 'dog'";
        String expectedOutput = "cat" + STRING_NEWLINE + "cat > dog" + STRING_NEWLINE + "cat" + STRING_NEWLINE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(expectedOutput.getBytes(StandardCharsets.UTF_8) , output.toByteArray());
        Files.delete(Paths.get("cat dog"));
        Files.delete(Paths.get("dog"));
    }

//    ===================================================== ACCEPTANCE TESTING ========================================

//  ** IO-Redirection Start **
    @Test
    void redir_PasteAndInputFileRedir_DisplaysPasteResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_PASTE + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + getRelativePath(FILE_ONE);
        String expectedOutput = LINE1 + STRING_NEWLINE + LINE2;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(expectedOutput.getBytes() , output.toByteArray());
    }

    @Test
    void catRedir_MultipleFilesProvidedForInputRedir_OutputsErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_CAT + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(FILE_TWO);
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            String expectedOutput = new ShellException(ERR_FILE_COUNT).getMessage();
            assertArrayEquals(expectedOutput.getBytes() , se.getMessage().getBytes());
            assertEquals(expectedOutput, se.getMessage());
        }
    }

    @Test
    void catRedir_InputRedirNoFilesProvided_OutputErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_CAT + CHAR_SPACE + CHAR_REDIR_INPUT;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            String expectedOutput = new ShellException(ERR_SYNTAX).getMessage();
            assertArrayEquals(expectedOutput.getBytes() , se.getMessage().getBytes());
            assertEquals(expectedOutput, se.getMessage());
        }
    }

    @Test
    void catRedir_OutputRedirNoFilesProvided_OutputErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_CAT + CHAR_SPACE + CHAR_REDIR_INPUT;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            String expectedOutput = new ShellException(ERR_SYNTAX).getMessage();
            assertArrayEquals(expectedOutput.getBytes() , se.getMessage().getBytes());
            assertEquals(expectedOutput, se.getMessage());
        }
    }

    @Test
    void catRedir_InputRedirNonExistFile_OutputErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_CAT + CHAR_SPACE + CHAR_REDIR_INPUT + getRelativePath("nonexist");
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            String expectedOutput = new ShellException("temp" + CHAR_FILE_SEP + "nonexist: " + ERR_FILE_NOT_FOUND).getMessage();
            assertArrayEquals(expectedOutput.getBytes() , se.getMessage().getBytes());
            assertEquals(expectedOutput, se.getMessage());
        }
    }

    @Test
    void catRedir_OutputRedirNonExistFile_CreatesFileAndWritesCatOutputToFile() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String uniqueOutFileName = "uniqueOutputFile";
        Files.write(createFile(FILE_ONE), BYTES_A);
        assertThrows(IOException.class, () -> Files.delete(getRelativePath(uniqueOutFileName)));
        String commandString = APP_CAT + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_REDIR_OUTPUT + getRelativePath("uniqueOutputFile");
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(getRelativePath(uniqueOutFileName)));
        assertEquals("", output.toString());
        Files.delete(getRelativePath(uniqueOutFileName));
    }
//  ** IO-redirection End **

//  ** Grep Start **
    @Test
    void grep_NoFlagsWithStdin_MatchesPatternAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_GREP + CHAR_SPACE + "e";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((LINE1 + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals(LINE1 + STRING_NEWLINE, output.toString());
    }

    @Test
    void grep_NoFlagsWithFileAsStdin_MatchesPatternAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_GREP + CHAR_SPACE + "e" + CHAR_SPACE + "-";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((LINE1 + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals(LINE1 + STRING_NEWLINE, output.toString());
    }


    @Test
    void grep_NoFlagsWithOneFile_MatchesPatternInFileAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_GREP + CHAR_SPACE + "e" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((LINE1 + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals(LINE1 + STRING_NEWLINE, output.toString());
    }

    @Test
    void grep_NoFlagsWithTwoFiles_MatchesPatternInFileAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_GREP + CHAR_SPACE + "e" + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(FILE_TWO);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = getRelativePath(FILE_ONE) + ": " + LINE1 + STRING_NEWLINE + getRelativePath(FILE_TWO) + ": " + LINE3 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
        assertEquals(expectedOutput, output.toString());
    }

    @Test
    void grep_IFlagsWithOneFile_MatchesPatternInFileAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_GREP + CHAR_SPACE + "-i " + "e" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STRING_A).getBytes(), output.toByteArray());
        assertEquals(STRING_A, output.toString());
    }

    @Test
    void grep_cFlagsWithOneFile_MatchesPatternInFileAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_GREP + CHAR_SPACE + "-c " + "e" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("1" + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals("1" + STRING_NEWLINE, output.toString());
    }

    @Test
    void grep_HFlagsWithOneFile_MatchesPatternInFileAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_GREP + CHAR_SPACE + "-H " + "e" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((getRelativePath(FILE_ONE) + ": " + LINE1 + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals(getRelativePath(FILE_ONE) + ": " + LINE1 + STRING_NEWLINE, output.toString());
    }

    @Test
    void grep_HFlagsWithStdin_MatchesPatternAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_GREP + CHAR_SPACE + "-H " + "e";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STDIN_FILEPREFIX + LINE1 + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals(STDIN_FILEPREFIX + LINE1 + STRING_NEWLINE, output.toString());
    }

    @Test
    void grep_AllFlagsWithFileAndStdin_MatchesPatternInFilesAndOutputsResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        System.setIn(new ByteArrayInputStream(STRING_B.getBytes()));
        String commandString = APP_GREP + CHAR_SPACE + "-i" + " -c" + " -H " + "e" +CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + "-";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedResult = getRelativePath(FILE_ONE) + ": " + "2" + STRING_NEWLINE + STDIN_FILEPREFIX + "1" + STRING_NEWLINE;
        assertArrayEquals(expectedResult.getBytes(), output.toByteArray());
        assertEquals(expectedResult, output.toString());
    }
//    ** Grep End **

//    ** Split Start **
    @Test
    void split_NoFlagsWithFile_SplitsFileContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_SPLIT + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
    }

    @Test
    void split_LineFlagValidFactorWithFile_SplitsFileContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "b" + CHAR_SPACE + "30" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expContentXAA = new String(Arrays.copyOfRange(STRING_A.getBytes(), 0, 30), StandardCharsets.UTF_8);
        String expContentXAB = new String(Arrays.copyOfRange(STRING_A.getBytes(), 30, STRING_A.getBytes().length), StandardCharsets.UTF_8);
        assertArrayEquals(expContentXAA.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(expContentXAB.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
    }

    @Test
    void split_LineFlagNoFactorWithFile_SplitsFileContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + getRelativePath(FILE_ONE);
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            String expectedOutput = new SplitException(INVALID_L_COUNT + CHAR_SPACE + CHAR_SINGLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_SINGLE_QUOTE).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void split_LineFlagInvalidFactorWithFile_SplitsFileContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + "-10" + CHAR_SPACE + getRelativePath(FILE_ONE);
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            String expectedOutput = new SplitException(INVALID_L_COUNT + CHAR_SPACE + CHAR_SINGLE_QUOTE + "-10" + CHAR_SINGLE_QUOTE).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void split_LineFlagZeroFactorWithFile_SplitsFileContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + "0" + CHAR_SPACE + getRelativePath(FILE_ONE);
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            String expectedOutput = new SplitException(INVALID_L_COUNT + CHAR_SPACE + CHAR_SINGLE_QUOTE + "0" + CHAR_SINGLE_QUOTE).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void split_NoFlagsWithStdin_SplitsStdinContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_SPLIT + CHAR_SPACE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
    }

    @Test
    void split_ByteFlagValidFactorWithStdin_SplitsStdinContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "b" + CHAR_SPACE + 30;
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expContentXAA = new String(Arrays.copyOfRange(STRING_A.getBytes(), 0, 30), StandardCharsets.UTF_8);
        String expContentXAB = new String(Arrays.copyOfRange(STRING_A.getBytes(), 30, STRING_A.getBytes().length), StandardCharsets.UTF_8);
        assertArrayEquals(expContentXAA.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(expContentXAB.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
    }

    @Test
    void split_ByteFlagNoFactorWithStdin_SplitsStdinContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "b";
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            String expectedOutput = new SplitException(MISSING_ARGUMENT + " -- b").getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void split_ByteFlagInvalidFactorWithStdin_SplitsStdinContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "b" + CHAR_SPACE + "-100";
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            String expectedOutput = new SplitException(INVALID_B_COUNT + CHAR_SPACE + CHAR_SINGLE_QUOTE + "-100" + CHAR_SINGLE_QUOTE).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void split_ByteFlagZeroFactorWithStdin_SplitsStdinContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "b" + CHAR_SPACE + "0";
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            String expectedOutput = new SplitException(INVALID_B_COUNT + CHAR_SPACE + CHAR_SINGLE_QUOTE + "0" + CHAR_SINGLE_QUOTE).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void split_NoFlagWithFileAndPrefix_SplitsFileContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String prefix = "prefix";
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_SPLIT + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(prefix);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(getRelativePath("prefixaa")));
        assertArrayEquals("".getBytes(), output.toByteArray());
        Files.delete(getRelativePath("prefixaa"));
    }

    @Test
    void split_NoFlagWithStdinAndPrefix_SplitsStdinContentIntoSeparateFiles() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_SPLIT + CHAR_SPACE + "-" + CHAR_SPACE + getRelativePath("prefixstd");
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(getRelativePath("prefixstdaa")));
        assertArrayEquals("".getBytes(), output.toByteArray());
        Files.delete(getRelativePath("prefixstdaa"));
    }

    @Test
    void split_AllFlagsWithFile_ReturnsErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_SPLIT + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + "200" + CHAR_SPACE + CHAR_FLAG_PREFIX + "b" + CHAR_SPACE + "200" + CHAR_SPACE + getRelativePath(FILE_ONE);
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            String expectedOutput = new SplitException("-b: " + ERR_FILE_NOT_FOUND).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }
//    ** Split End **

//    ** Uniq Start **
    @Test
    void uniq_NoFlagsWithStdin_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(UNIQSTRING_A.getBytes()));
        String commandString = APP_UNIQ + CHAR_SPACE + "-";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

    @Test
    void uniq_NoFlagsWithFile_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

    @Test
    void uniq_NoFlagsWithStdinAndOutputFileName_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(UNIQSTRING_A.getBytes()));
        String commandString = APP_UNIQ + CHAR_SPACE + "-" + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3;
        assertArrayEquals(expectedOutput.getBytes(), Files.readAllBytes(getRelativePath(OUTPUT_FILE)));
        assertArrayEquals("".getBytes(), output.toByteArray());
    }

    @Test
    void uniq_NoFlagsWithFileAndOutputFileName_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE3;
        assertArrayEquals(expectedOutput.getBytes(), Files.readAllBytes(getRelativePath(OUTPUT_FILE)));
        assertArrayEquals("".getBytes(), output.toByteArray());
    }

    @Test
    void uniq_cFlagsWithFile_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + CHAR_FLAG_PREFIX + "c" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = "2 " + UNIQLINE1 + STRING_NEWLINE + "2 " + UNIQLINE2 + STRING_NEWLINE + "1 " + UNIQLINE3 + STRING_NEWLINE + "1 " + UNIQLINE2 + STRING_NEWLINE + "1 " + UNIQLINE3 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

    @Test
    void uniq_dFlagWithFile_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + CHAR_FLAG_PREFIX + "d" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

    @Test
    void uniq_DFlagWithFile_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + CHAR_FLAG_PREFIX + "D" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = UNIQLINE1 + STRING_NEWLINE + UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

    @Test
    void uniq_dDFlagsWithFile_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + CHAR_FLAG_PREFIX + "d" + CHAR_SPACE + CHAR_FLAG_PREFIX + "D" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = UNIQLINE1 + STRING_NEWLINE + UNIQLINE1 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE + UNIQLINE2 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

    @Test
    void uniq_cDFlagsWithFile_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + CHAR_FLAG_PREFIX + "c" + CHAR_SPACE + CHAR_FLAG_PREFIX + "D" + CHAR_SPACE + getRelativePath(FILE_ONE);
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (UniqException ue) {
            String expectedOutput = new UniqException(ERR_MUTUAL_EXC).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), ue.getMessage().getBytes());
        }
    }

    @Test
    void uniq_cdFlagsWithFile_DisplaysErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), UNIQBYTES_A);
        String commandString = APP_UNIQ + CHAR_SPACE + CHAR_FLAG_PREFIX + "c" + CHAR_SPACE + CHAR_FLAG_PREFIX + "d" + CHAR_SPACE + getRelativePath(FILE_ONE);
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (UniqException ue) {
            String expectedOutput = new UniqException(ERR_MUTUAL_EXC).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), ue.getMessage().getBytes());
        }
    }

//    ** Uniq End **

//    ** Sequence Start **
    @Test
    void sequence_emptySeq_DisplaysErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = ";";
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            String expectedOutput = new ShellException(ERR_SYNTAX).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void sequence_SemicolonAfterEcho_DisplaysErrorMessage() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + LINE1 + CHAR_SEMICOLON;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            String expectedOutput = new ShellException(ERR_SYNTAX).getMessage();
            assertArrayEquals(expectedOutput.getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void successfulSequence_EchoSemiColonEcho_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + LINE1 + CHAR_SEMICOLON + CHAR_SPACE + APP_ECHO + CHAR_SPACE + LINE2;
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

    @Test
    void mixedSuccessSequence_EchoSemiColonCatNonExistFile_DisplaysResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_ECHO + CHAR_SPACE + LINE1 + CHAR_SEMICOLON + CHAR_SPACE + APP_CAT + CHAR_SPACE + getRelativePath("nonexist");
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expectedOutput = LINE1 + STRING_NEWLINE + new CatException(ERR_FILE_NOT_FOUND).getMessage() + STRING_NEWLINE;
        assertArrayEquals(expectedOutput.getBytes(), output.toByteArray());
    }

//    ** Sequence End **

//    ** Tee Start **
    @Test
    void tee_NoFlagsWithStdin_OutputMatches() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_TEE + CHAR_SPACE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STRING_A).getBytes(), output.toByteArray());
        assertEquals(STRING_A, output.toString());
    }

    @Test
    void tee_NoFlagsWithDashAsStdin_OutputMatchesStdIn() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        String commandString = APP_TEE + CHAR_SPACE  + FILE_ONE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STRING_A).getBytes(), output.toByteArray());
        assertEquals(STRING_A, output.toString());
    }

    @Test
    void tee_NoFlagsWithOneFile_OutputMatchesStdIn() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_TEE + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STRING_A).getBytes(), output.toByteArray());
        assertEquals(STRING_A, output.toString());
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(getRelativePath(FILE_ONE)));
    }

    @Test
    void tee_NoFlagsWithTwoFiles_OutputMatchesStdIn() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_A.getBytes()));
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_TEE + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(FILE_TWO);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STRING_A).getBytes(), output.toByteArray());
        assertEquals(STRING_A, output.toString());
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(getRelativePath(FILE_ONE)));
        assertArrayEquals(STRING_A.getBytes(), Files.readAllBytes(getRelativePath(FILE_TWO)));
    }

    @Test
    void tee_AFlagWithOneFile_OutputsMatchesStdInFileUpdated() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_B.getBytes()));
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_TEE + CHAR_SPACE + CHAR_FLAG_PREFIX + "a " + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STRING_B).getBytes(), output.toByteArray());
        assertEquals((STRING_A + STRING_B), new String(Files.readAllBytes(getRelativePath(FILE_ONE))));
    }

    @Test
    void tee_EchoCommandPipeTeeNoFlagsWithOneFile_OutputsMatchesStdInFileUpdated() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_B.getBytes()));
        Files.write(createFile(FILE_ONE), BYTES_A);
        String msg = "hello";
        String commandString = APP_ECHO + CHAR_SPACE + msg + CHAR_SPACE + CHAR_PIPE + CHAR_SPACE + APP_TEE
                            + CHAR_SPACE + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(msg + STRING_NEWLINE, output.toString());
        assertEquals(msg + STRING_NEWLINE, new String(Files.readAllBytes(getRelativePath(FILE_ONE))));
    }

    @Test
    void tee_AFlagWithOneFileAndOneInputIOFile_OutputsMatchesStdInFileUpdated() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(STRING_B.getBytes()));
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_TEE + CHAR_SPACE + CHAR_FLAG_PREFIX + "a " + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((STRING_B).getBytes(), output.toByteArray());
        assertEquals((STRING_A + STRING_B), new String(Files.readAllBytes(getRelativePath(FILE_ONE))));
    }

//    ** Tee End **


//    ** Wc Start **
    @Test
    void wc_AllFlagsMultipleFilesWithQuotationEchoSequence_DisplayInfoAboutFilesAndEchoOutput() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        long[] fileOneCount = new long[]{2,6};
        long[] fileTwoCount = new long[]{1,4};
        long[] totalCount = new long[]{3,10};
        String fileOneBytes = String.format(WC_NUM_FORMAT, BYTES_A.length);
        String fileTwoBytes = String.format(WC_NUM_FORMAT, BYTES_B.length);
        String totalNumBytes = String.format(WC_NUM_FORMAT, BYTES_A.length + BYTES_B.length );

        String commandString = APP_WC + CHAR_SPACE + CHAR_DOUBLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_DOUBLE_QUOTE
                + CHAR_SPACE + CHAR_SINGLE_QUOTE + getRelativePath(FILE_TWO) + CHAR_SINGLE_QUOTE + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "testing";
        new ShellImpl().parseAndEvaluate(commandString, output);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(WC_NUM_FORMAT,fileOneCount[0]) + String.format(WC_NUM_FORMAT,fileOneCount[1]) + fileOneBytes + " " + getRelativePath(FILE_ONE));
        expectedList.add(String.format(WC_NUM_FORMAT,fileTwoCount[0]) + String.format(WC_NUM_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + getRelativePath(FILE_TWO));
        expectedList.add(new StringBuilder().append(String.format(WC_NUM_FORMAT, totalCount[0])).append(String.format(WC_NUM_FORMAT, totalCount[1])).append(totalNumBytes).append(WC_TOTAL_POSTFIX).toString());
        expectedList.add("testing");
        String expected = String.join(STRING_NEWLINE, expectedList);
        expected = expected + STRING_NEWLINE;
        assertEquals(expected, output.toString());
    }

    @Test
    void wc_AllFlagsCommandSubstitutionCatFile_DisplayInfoAboutFileWithTitleOfCatFileContent() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        long[] fileOneCount = new long[]{1,1};
        long[] fileTwoCount = new long[]{1,4};
        long[] totalCount = new long[]{2,5};
        String fileTwoBytes = String.format(WC_NUM_FORMAT, BYTES_B.length);
        String fileOneBytes = String.format(WC_NUM_FORMAT, getRelativePath(FILE_TWO).toString().getBytes().length);
        String totalNumBytes = String.format(WC_NUM_FORMAT, BYTES_B.length + getRelativePath(FILE_TWO).toString().getBytes().length );
        Files.write(createFile(FILE_TWO), BYTES_B);
        Files.write(createFile(FILE_ONE), getRelativePath(FILE_TWO).toString().getBytes());

        String commandString = APP_WC + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + "`" + APP_CAT + CHAR_SPACE + getRelativePath(FILE_ONE)+"`";
        new ShellImpl().parseAndEvaluate(commandString, output);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(String.format(WC_NUM_FORMAT,fileOneCount[0]) + String.format(WC_NUM_FORMAT,fileOneCount[1]) + fileOneBytes + " " + getRelativePath(FILE_ONE));
        expectedList.add(String.format(WC_NUM_FORMAT,fileTwoCount[0]) + String.format(WC_NUM_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + getRelativePath(FILE_TWO));
        expectedList.add(new StringBuilder().append(String.format(WC_NUM_FORMAT, totalCount[0])).append(String.format(WC_NUM_FORMAT, totalCount[1])).append(totalNumBytes).append(WC_TOTAL_POSTFIX).toString());
        String expected = String.join(STRING_NEWLINE, expectedList);
        expected = expected + STRING_NEWLINE;

        assertEquals(expected, output.toString());
    }
//    ** Wc End **

//    ** Rm Start **
    @Test
    void rm_NoFlagsNoFile_Warning() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = APP_RM + CHAR_SPACE ;
        String actualOutput = tapSystemOut(() ->  new ShellImpl().parseAndEvaluate(commandString, output));
        assertEquals("rm: missing operand" + STRING_NEWLINE, actualOutput);
    }

    @Test
    void rm_NoFlagsOneFile_NoWarningDisplayedFileRemoved() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_RM + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("").getBytes(), output.toByteArray());
        assertTrue(Files.notExists(getRelativePath(FILE_ONE)));
    }

    @Test
    void rm_EmptyDirFlagMultipleFiles_NoWarningFileRemoved() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_RM + CHAR_SPACE + CHAR_FLAG_PREFIX + "d" + CHAR_SPACE + getRelativePath(FILE_ONE)
                + CHAR_SPACE + getRelativePath(FILE_TWO);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("").getBytes(), output.toByteArray());
        assertTrue(Files.notExists(getRelativePath(FILE_ONE)));
        assertTrue(Files.notExists(getRelativePath(FILE_TWO)));
    }

    @Test
    void rm_NoFlagsOneFileSequenceEcho_DisplayWarningMessageWithEcho() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_RM + CHAR_SPACE + getRelativePath(FILE_TWO) + CHAR_SPACE + "; "
                + APP_ECHO + " hello; " + APP_ECHO + CHAR_SPACE + "bye";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = "hello" + STRING_NEWLINE + "bye" + STRING_NEWLINE;
        assertEquals(expected, output.toString());
        assertTrue(Files.notExists(getRelativePath(FILE_TWO)));
    }

    @Test
    void rm_EmptyDirectoryFlagMultipleFilesPipeTeeOneFile_CreateNewFile() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_RM + CHAR_SPACE + CHAR_FLAG_PREFIX + "d" + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(FILE_TWO)
                + CHAR_SPACE + CHAR_PIPE + APP_TEE + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("", output.toString());
        assertTrue(Files.exists(getRelativePath(OUTPUT_FILE)));
    }

    @Test
    void rm_NoFlagsCommandSubstitutionCatFile_DeleteFile() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_TWO), BYTES_B);
        Files.write(createFile(FILE_ONE), getRelativePath(FILE_TWO).toString().getBytes());
        String commandString = APP_RM + CHAR_SPACE + "`" + APP_CAT + CHAR_SPACE + getRelativePath(FILE_ONE)+"`";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("", output.toString());
        assertTrue(Files.notExists(getRelativePath(FILE_TWO)));
        assertTrue(Files.exists(getRelativePath(FILE_ONE)));
    }

//    ** Rm End **

}
