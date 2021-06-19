package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;

public class RmIntegrationTest {
    public static final String TEMP = "temp";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static Deque<Path> files = new ArrayDeque<>();

    private static final String NEW_LINE = System.lineSeparator();
    private static final String FILE_OUTPUT = "output.txt";
    private static final String NON_EMPTY_DIR = "nonEmptyDir";
    private static final String NESTED_FILE = "mfd.txt";
    private static final String TEST_FILE_I = "test_rm_fileI";
    private static final String FILE_I = "fileI";
    private static final String EMPTY_DIR = "emptyDir";
    public static final String RM_WARN_PREFIX = "rm: cannot remove '";
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
    void createTemp() throws IOException {
        Files.createDirectory(TEMP_PATH);
        deleteFile(FILE_OUTPUT);
    }

    @AfterEach
    void deleteTemp() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
        Files.delete(TEMP_PATH);
        Path path = IOUtils.resolveFilePath(FILE_OUTPUT);
        Files.deleteIfExists(path);
    }

    // added
    private Path createDirectoryInCurrFolder(String folder) throws IOException {
        Path path = Files.createDirectory(IOUtils.resolveFilePath(folder));
        files.push(path);
        return path;
    }

    // added
    private Path createFilesInCurrFolder(String folder) throws IOException {
        Path path = Files.createFile(IOUtils.resolveFilePath(folder));
        files.push(path);
        return path;
    }

    private Path createFile(String name, Path inPath) throws IOException {
        Path path = inPath.resolve(name);
        Files.createFile(path);
        files.push(path);
        return path;
    }


    // ========================================  POSITIVE RM INTEGRATION =========================================

    @Test
    public void rm_WithQuoting_DisplaysWarning() throws Exception {
        outContent.reset();
        Path nonEmptyDirPath = createDirectoryInCurrFolder(NON_EMPTY_DIR);
        createFile(NESTED_FILE, nonEmptyDirPath);
        createDirectoryInCurrFolder(EMPTY_DIR);
        createFilesInCurrFolder(FILE_I);
        createFilesInCurrFolder("fileJ");
        String commandString = "rm -d \""+ NON_EMPTY_DIR + "\" fileJ '" + FILE_I + "' " + EMPTY_DIR;
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        String expected = RM_WARN_PREFIX + NON_EMPTY_DIR + "': Directory not empty" + NEW_LINE;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void rm_WithIORedirection_OutputIsEmpty() throws Exception {
        outContent.reset();
        Path fileIPath = createFilesInCurrFolder(FILE_I);
        String commandString = "rm " + FILE_I + " > " + FILE_OUTPUT;
        String expected = "";
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        assertTrue(Files.notExists(fileIPath));
        Path outputPath = IOUtils.resolveFilePath(FILE_OUTPUT);
        assertEquals(expected, Files.readString(outputPath));
    }

    @Test
    public void rm_WithSequence_DisplaysRmAndEchoResults() throws Exception {
        outContent.reset();
        Path nonEmptyDirPath = createDirectoryInCurrFolder(NON_EMPTY_DIR);
        createFile(NESTED_FILE, nonEmptyDirPath);
        createDirectoryInCurrFolder(EMPTY_DIR);
        createFilesInCurrFolder("fileI");
        createFilesInCurrFolder("fileJ");
        String commandString = "rm -d fileI nonExisting.file fileJ nonEmptyDir emptyDir; echo testing";
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        String expected = RM_WARN_PREFIX + "nonExisting.file" + "': No such file or directory" + NEW_LINE
                        + RM_WARN_PREFIX + "nonEmptyDir" + "': Directory not empty" + NEW_LINE + "testing" + NEW_LINE;
        assertEquals(expected, outContent.toString());
    }

    @Test
    // Ensure no file/folder has .txt extension in the project root directory, otherwise test will fail!
    public void rm_WithGlobbing_DisplaysRmResults() throws Exception {
        outContent.reset();
        Path nonEmptyDirPath = createDirectoryInCurrFolder("test_rm_nonEmptyDir");
        createFile(NESTED_FILE, nonEmptyDirPath);
        Path emptyDir = createDirectoryInCurrFolder("test_rm_emptyDir");
        Path fileI = createFilesInCurrFolder(TEST_FILE_I);
        Path fileJ = createFilesInCurrFolder("test_rm_fileJ");
        String commandString = "rm -d test_rm_*";
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        String expected = RM_WARN_PREFIX + "test_rm_nonEmptyDir" + "': Directory not empty" + NEW_LINE;
        assertTrue(Files.exists(nonEmptyDirPath));
        assertTrue(Files.notExists(emptyDir));
        assertTrue(Files.notExists(fileI));
        assertTrue(Files.notExists(fileJ));
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void rm_WithQuoteIORedirectionPipeGlobbingSequence_DisplaysRmResults() throws Exception {
        outContent.reset();
        Path nonEmptyDir = createDirectoryInCurrFolder("nonEmptyDir_test_rm");
        createFile(NESTED_FILE, nonEmptyDir);
        Path emptyDir = createDirectoryInCurrFolder("empty_Dir_test_rm");
        Path fileI = createFilesInCurrFolder("fileI_test_rm");
        Path fileJ = createFilesInCurrFolder("fileJ_test_rm");
        String commandString = "rm *_test_rm \"nonExisting.file\" >" + FILE_OUTPUT + "; echo hello";
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        String expected = RM_WARN_PREFIX + "empty_Dir_test_rm" + "': Is a directory" + NEW_LINE
                        + RM_WARN_PREFIX + "nonEmptyDir_test_rm" + "': Is a directory" + NEW_LINE
                        + RM_WARN_PREFIX + "nonExisting.file" + "': No such file or directory" + NEW_LINE
                        + "hello" + NEW_LINE;
        assertTrue(Files.exists(nonEmptyDir));
        assertTrue(Files.exists(emptyDir));
        assertTrue(Files.notExists(fileI));
        assertTrue(Files.notExists(fileJ));
        assertEquals(expected, outContent.toString());
        Path outputPath = IOUtils.resolveFilePath(FILE_OUTPUT);
        assertEquals("", Files.readString(outputPath));
    }

    @Test
    public void rm_eWithIORedirection_OutputIsEmpty() throws Exception {
        outContent.reset();
        Path fileI = createFilesInCurrFolder("fileI");
        String commandString = "rm fileI > " + FILE_OUTPUT;
        String expected = "";
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        assertTrue(Files.notExists(fileI));
        Path outputPath = IOUtils.resolveFilePath(FILE_OUTPUT);
        assertEquals(expected, Files.readString(outputPath));
    }

    // ========================================  NEGATIVE RM INTEGRATION =========================================

    @Test
    public void rm_WithNoFilesForOutputRedirection_ThrowsShellException() throws Exception {
        outContent.reset();
        createFilesInCurrFolder("FILE_I");
        String commandString = "rm -r < fileI > output.txt";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, outContent));
    }

    @Test
    public void rm_WithMultipleInputRedirection_ThrowsShellException() throws Exception {
        outContent.reset();
        createFilesInCurrFolder(TEST_FILE_I);
        createFilesInCurrFolder("test_rm_fileJ");
        String commandString = "rm -r < " + TEST_FILE_I + " < test_rm_fileJ ";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, outContent));
    }

    @Test
    public void rm_WithMultipleOutputRedirection_ThrowsShellException() throws Exception {
        outContent.reset();
        createFilesInCurrFolder(TEST_FILE_I);
        String commandString = "rm " + TEST_FILE_I + " > output.txt > output.txt ";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, outContent));
    }

}
