package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createAndWriteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createDirectory;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteDirectory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class CatIntegrationTest {
    public static final String INITIAL_DIR = Environment.currentDirectory;
    private static final String NEW_LINE = System.lineSeparator();
    public static final String TEMP_DIR = "catIntegrationTestDir";
    private static final String FILE_ONE = "fileOne.txt";
    private static final String FILE_TWO = "fileTwo.txt";
    private static final String FILE_THREE = "fileThree.txt";
    private static final String FILE_OUTPUT = "output.txt";
    private static final String FILE_ONE_CONT = "I love" + NEW_LINE + "CS4218" + NEW_LINE + NEW_LINE;
    private static final String FILE_TWO_CONT = "~!@#$%+";
    private static final String FILE_THREE_CONT = "\" ' < > * | ` \"\n" + "\\ $ % ; , . ? /";
    private static final String NON_EXIST_MSG = "cat: No such file or directory";
    private static final String DIRECTORY = "myDirectory";

    @BeforeAll
    static void init() throws IOException {
        createDirectory(TEMP_DIR);
        Environment.setCurrentDirectory(IOUtils.resolveFilePath(TEMP_DIR).toString());
        createAndWriteFile(Paths.get(TEMP_DIR, FILE_ONE).toString(), FILE_ONE_CONT);
        createAndWriteFile(Paths.get(TEMP_DIR, FILE_TWO).toString(), FILE_TWO_CONT);
        createAndWriteFile(Paths.get(TEMP_DIR, FILE_THREE).toString(), FILE_THREE_CONT);
        createDirectory(Paths.get(TEMP_DIR, DIRECTORY).toString());
    }

    @AfterAll
    static void terminate() throws IOException {
        deleteDirectory(Paths.get(TEMP_DIR).toString());
        Environment.setCurrentDirectory(INITIAL_DIR);
    }

    // ========================================  POSITIVE CAT INTEGRATION =========================================

    @Test
    public void cat_WithQuoting_DisplaysCatResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n \"fileTwo.txt\" nonExisting.file 'myDirectory' fileOne.txt";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = "1 ~!@#$%+" + NEW_LINE
                        + NON_EXIST_MSG + NEW_LINE
                        + "cat: This is a directory" + NEW_LINE
                        + "2 I love" + NEW_LINE
                        + "3 CS4218" + NEW_LINE
                        + "4 " + NEW_LINE;
        assertEquals(expected, output.toString());
    }

    @Test
    public void cat_WithIORedirection_OutputCatResultsToFile() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n fileTwo.txt nonExisting.file fileOne.txt - < fileThree.txt > output.txt";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = "1 ~!@#$%+" + NEW_LINE
                        + NON_EXIST_MSG + NEW_LINE
                        + "2 I love" + NEW_LINE
                        + "3 CS4218" + NEW_LINE
                        + "4 " + NEW_LINE
                        + "5 \" ' < > * | ` \"" + NEW_LINE
                        + "6 \\ $ % ; , . ? /" + NEW_LINE;
        assertEquals(expected, Files.readString(IOUtils.resolveFilePath(FILE_OUTPUT)));
    }

    @Test
    public void cat_WithSequence_DisplaysCatAndEchoResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n fileTwo.txt nonExisting.file fileThree.txt myDirectory fileOne.txt; echo hello";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = "1 ~!@#$%+" + NEW_LINE
                        + NON_EXIST_MSG + NEW_LINE
                        + "2 \" ' < > * | ` \"" + NEW_LINE
                        + "3 \\ $ % ; , . ? /" + NEW_LINE
                        + "cat: This is a directory" + NEW_LINE
                        + "4 I love" + NEW_LINE
                        + "5 CS4218" + NEW_LINE
                        + "6 " + NEW_LINE
                        + "hello" + NEW_LINE;
        assertEquals(expected, output.toString());
    }

    @Test
    // Ensure no file/folder has .txt extension in the project root directory, otherwise test will fail!
    public void cat_WithGlobbing_DisplaysCatResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n *.txt nonExisting.file myDirectory fileOne.txt";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = "1 I love" + NEW_LINE
                        + "2 CS4218" + NEW_LINE
                        + "3 " + NEW_LINE
                        + "4 \" ' < > * | ` \"" + NEW_LINE
                        + "5 \\ $ % ; , . ? /" + NEW_LINE
                        + "6 ~!@#$%+" + NEW_LINE
                        + NON_EXIST_MSG + NEW_LINE
                        + "cat: This is a directory" + NEW_LINE
                        + "7 I love" + NEW_LINE
                        + "8 CS4218" + NEW_LINE
                        + "9 " + NEW_LINE;
        assertEquals(expected, output.toString());
    }

    @Test
    public void cat_WithPipeGrep_DisplaysCatGrepResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n fileOne.txt fileTwo.txt fileThree.txt nonExisting.file | grep %";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = "4 ~!@#$%+" + NEW_LINE
                        + "6 \\ $ % ; , . ? /" + NEW_LINE;
        assertEquals(expected, output.toString());
    }

    @Test
    // Ensure no file/folder has .txt extension in the project root directory, otherwise test will fail!
    public void cat_WithQuoteIORedirectionPipeGlobbingSequence_DisplaysCatResults() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n *.txt \"nonExisting.file\" myDirectory 'fileOne.txt' - < fileThree.txt | grep 1; echo hello";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = "1 I love" + NEW_LINE
                        + "2 CS4218" + NEW_LINE
                        + "8 CS4218" + NEW_LINE
                        + "10 \" ' < > * | ` \"" + NEW_LINE
                        + "11 \\ $ % ; , . ? /" + NEW_LINE
                        + "hello" + NEW_LINE;
        assertEquals(expected, output.toString());
    }

    // ========================================  NEGATIVE CAT INTEGRATION =========================================

    @Test
    public void cat_WithMultipleFilesForInputRedirection_ThrowsShellException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n fileTwo.txt nonExisting.file - < fileThree.txt fileOne.txt > output.txt";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

    @Test
    public void cat_WithMultipleInputRedirection_ThrowsShellException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n fileTwo.txt - < fileThree.txt - < fileOne.txt > output.txt";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }
    @Test
    public void cat_WithAllArgsQuotedAsOne_DisplayNoSuchFileError() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cat -n \"fileTwo.txt fileThree.txt fileOne.txt\"";
        new ShellImpl().parseAndEvaluate(commandString, output);
        String expected = NON_EXIST_MSG + NEW_LINE;
        assertEquals(expected, output.toString());
    }
}
