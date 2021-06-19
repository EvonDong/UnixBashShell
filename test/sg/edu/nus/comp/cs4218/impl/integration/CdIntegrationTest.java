package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;

import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class CdIntegrationTest {
    private static final String INITIAL_DIRECTORY = Paths.get(Environment.currentDirectory).toString();
    private static final String SRC_DIR = "src";

    @AfterEach
    void setUp() {
        Environment.currentDirectory = INITIAL_DIRECTORY;
        deleteFile("output");
    }

    // ========================================  POSITIVE CD INTEGRATION =========================================

    @Test
    public void cd_WithDoubleQuoting_ChangesDirectory() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd \"src\"";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(Paths.get(INITIAL_DIRECTORY, SRC_DIR).toString(), Environment.currentDirectory);
    }

    @Test
    public void cd_WithSingleQuoting_ChangesDirectory() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd 'src'";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(Paths.get(INITIAL_DIRECTORY, SRC_DIR).toString(), Environment.currentDirectory);
    }

    @Test
    public void cd_WithIORedirection_ChangesDirectoryAndCreateOutputFile() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd src < testFiles/cat/fileOne.txt > output";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(Paths.get(INITIAL_DIRECTORY, SRC_DIR).toString(), Environment.currentDirectory);
        assertTrue(Paths.get(INITIAL_DIRECTORY, "output").toFile().exists());
    }

    @Test
    public void cd_WithInputRedirection_DirectoryUnchanged() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd < README.md";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(INITIAL_DIRECTORY, Environment.currentDirectory);
    }

    @Test
    public void cd_WithCommandSubstitution_ChangesDirectory() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd \"`echo src`\"";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(Paths.get(INITIAL_DIRECTORY, SRC_DIR).toString(), Environment.currentDirectory);
    }

    @Test
    public void cd_WithSemicolon_ChangesDirectory() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd src; cd sg";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(Paths.get(INITIAL_DIRECTORY, SRC_DIR, "sg").toString(), Environment.currentDirectory);
    }

    @Test
    public void cd_WithGlobbingThatMatchesOneDirectory_ChangesDirectory() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd sr*";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals(Paths.get(INITIAL_DIRECTORY, SRC_DIR).toString(), Environment.currentDirectory);
    }

    // ========================================  NEGATIVE CD INTEGRATION =========================================

    @Test
    public void cd_WithGlobbingThatMatchesMultipleDirectories_ThrowsException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd tes*";
        assertThrows(CdException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

    @Test
    public void cd_WithCommandSubstitutionDisabledBySingleQuotes_ThrowsException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "cd '`echo src`'";
        assertThrows(CdException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }
}
