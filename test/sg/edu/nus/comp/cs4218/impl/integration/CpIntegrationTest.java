package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createAndWriteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createDirectory;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteDirectory;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class CpIntegrationTest {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String FILE_ONE = "fileOne.txt";
    private static final String FILE_TWO = "fileTwo.txt";
    private static final String FILE_THREE = "fileThree.txt";
    private static final String FILE_OUTPUT = "output.out";
    private static final String FILE_ONE_CONT = "I love" + NEW_LINE + "CS4218" + NEW_LINE + NEW_LINE;
    private static final String FILE_TWO_CONT = "~!@#$%+";
    private static final String FILE_THREE_CONT = "\" ' < > * | ` \"\n" + "\\ $ % ; , . ? /";
    private static final String DIRECTORY = "myDirectory";

    @BeforeAll
    static void initialize() throws IOException {
        createAndWriteFile(FILE_ONE, FILE_ONE_CONT);
        createAndWriteFile(FILE_TWO, FILE_TWO_CONT);
        createAndWriteFile(FILE_THREE, FILE_THREE_CONT);
        createDirectory(DIRECTORY);
    }

    @AfterAll
    static void terminate() throws IOException {
        for (String file : new String[]{FILE_ONE, FILE_TWO, FILE_THREE, FILE_OUTPUT}) {
            deleteFile(file);
        }
        deleteDirectory(DIRECTORY);
    }

    // verify that 1) the file exists, 2) the file's content is equal to the input content
    public void assertExistAndEquals(String file, String expectedContent) throws IOException {
        Path outputPath = IOUtils.resolveFilePath(file);
        assertTrue(outputPath.toFile().exists());
        assertEquals(expectedContent, Files.readString(outputPath));
    }

    // ========================================  POSITIVE CP INTEGRATION =========================================

    @Test
    public void cp_WithQuoting_CopiesFile() throws Exception {
        String commandString = "cp -R \"fileOne.txt\" 'output.out'";
        new ShellImpl().parseAndEvaluate(commandString, new ByteArrayOutputStream());
        assertExistAndEquals(FILE_OUTPUT, FILE_ONE_CONT);
        deleteFile(FILE_OUTPUT);
    }

    @Test
    public void cp_WithIORedirection_CopiesFileAndGenerateOutputFile() throws Exception {
        String commandString = "cp -r fileOne.txt nonExisting.file fileTwo.txt myDirectory > output.out";
        new ShellImpl().parseAndEvaluate(commandString, new ByteArrayOutputStream());
        String copiedFileOnePath = Paths.get(DIRECTORY, FILE_ONE).toString();
        String copiedFileTwoPath = Paths.get(DIRECTORY, FILE_TWO).toString();
        assertExistAndEquals(FILE_OUTPUT, "cp: No such file or directory" + NEW_LINE);
        assertExistAndEquals(copiedFileOnePath, FILE_ONE_CONT);
        assertExistAndEquals(copiedFileTwoPath, FILE_TWO_CONT);
        deleteFile(FILE_OUTPUT);
        deleteFile(copiedFileOnePath);
        deleteFile(copiedFileTwoPath);
    }

    @Test
    public void cp_WithCommandSubstitution_CopiesFile() throws Exception {
        String commandString = "cp -R `echo fileTwo.txt` `echo output.out`";
        new ShellImpl().parseAndEvaluate(commandString, new ByteArrayOutputStream());
        assertExistAndEquals(FILE_OUTPUT, FILE_TWO_CONT);
        deleteFile(FILE_OUTPUT);
    }

    @Test
    public void cp_WithSequenceCommand_CopiesFile() throws Exception {
        String commandString = "cp fileOne.txt output.out; cp fileTwo.txt myDirectory";
        new ShellImpl().parseAndEvaluate(commandString, new ByteArrayOutputStream());
        assertExistAndEquals(FILE_OUTPUT, FILE_ONE_CONT);
        String copiedFileTwoPath = Paths.get(DIRECTORY, FILE_TWO).toString();
        assertExistAndEquals(copiedFileTwoPath, FILE_TWO_CONT);
        deleteFile(FILE_OUTPUT);
        deleteFile(copiedFileTwoPath);
    }

    @Test
    public void cp_WithGlobbing_CopiesMatchedFolders() throws Exception {
        String commandString = "cp fileT* myDirectory";
        new ShellImpl().parseAndEvaluate(commandString, new ByteArrayOutputStream());
        String newFileTwoPath = Paths.get(DIRECTORY, FILE_TWO).toString();
        String newFileThreePath = Paths.get(DIRECTORY, FILE_THREE).toString();
        assertExistAndEquals(newFileTwoPath, FILE_TWO_CONT);
        assertExistAndEquals(newFileThreePath, FILE_THREE_CONT);
        deleteFile(newFileTwoPath);
        deleteFile(newFileThreePath);
    }

    @Test
    public void cp_WithPipeAndGrep_CopiesFileAndOutputGrepResult() throws Exception {
        String commandString = "cp -r fileThree.txt myDirectory fileOne.txt myDirectory | grep same";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new ShellImpl().parseAndEvaluate(commandString, output);
        String newFileOnePath = Paths.get(DIRECTORY, FILE_ONE).toString();
        String newFileThreePath = Paths.get(DIRECTORY, FILE_THREE).toString();
        assertExistAndEquals(newFileOnePath, FILE_ONE_CONT);
        assertExistAndEquals(newFileThreePath, FILE_THREE_CONT);
        assertEquals("cp: Source and destination cannot be the same" + NEW_LINE, output.toString());
        deleteFile(newFileOnePath);
        deleteFile(newFileThreePath);
    }

    // ========================================  NEGATIVE CP INTEGRATION =========================================

    @Test
    public void cp_WithMultipleFilesForOutputRedirection_ThrowsShellException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cp -r fileOne.txt nonExisting.file fileTwo.txt myDirectory > output.out output1.txt";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

    @Test
    public void cp_WithMultipleOutputRedirection_ThrowsShellException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cp -r fileOne.txt myDirectory > output.out > output1.txt";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }
    @Test
    public void cp_WithSequenceCmdButSecondCmdEndsWithSemicolon_ThrowsShellException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cp fileOne.txt fileOne.txt; cp fileOne.txt myDirectory;";
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }
}
