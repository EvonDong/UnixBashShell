package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class LsIntegrationTest {

    public static final String TEMP = "temp-ls-integration";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static Deque<Path> files = new ArrayDeque<>();
    private static final String CURR_DIR = Environment.currentDirectory;
    private static final String FILE1 = "file1.txt";
    private static final String FILE1_CONTENTS = "file contents";

    @BeforeAll
    static void createTemp() throws IOException {
        Files.deleteIfExists(TEMP_PATH);
        Files.createDirectory(TEMP_PATH);
        Environment.currentDirectory = String.valueOf(TEMP_PATH);
    }


    @AfterAll
    static void deleteFiles() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
        Files.delete(TEMP_PATH);
        Environment.currentDirectory = CURR_DIR;
    }

    private Path createFile(String name, String text) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.write(path, text.getBytes());
        files.push(path);
        return path;
    }

    // Quoting

    @Test
    public void testQuoting_BackQuote_ThrowException() {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            new ShellImpl().parseAndEvaluate("ls ''", output);
            fail();
        } catch (Exception e) {
            assertEquals("ls: No such file or directory", e.getMessage());
        }
    }


    // Command Substitution

    @Test
    public void testCommandSubstitution_BackQuote_ThrowException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;
        Path filePath = createFile(fileName, text);

        new ShellImpl().parseAndEvaluate("echo `ls`", output);
        assertEquals(fileName + STRING_NEWLINE, output.toString());
    }

    @Test
    public void testCommandSubstitution_BackQuoteNotExistApp_ThrowException() {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            new ShellImpl().parseAndEvaluate("ls `randommm.txt`", output);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid app"));
        }
    }

    // Pipe

    @Test
    public void testPipe_EchoPipeToLs_ThrowException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;
        Path filePath = createFile(fileName, text);

        new ShellImpl().parseAndEvaluate("echo 'dir1' | ls", output);
        assertEquals(FILE1 + STRING_NEWLINE, output.toString());
    }

    @Test
    public void testPipe_LsPipeToPaste_ThrowException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;
        Path filePath = createFile(fileName, text);

        new ShellImpl().parseAndEvaluate("ls | paste - ", output);
        assertEquals(FILE1, output.toString());
    }


    // Globbing

    @Test
    public void testGlobbing_WildcardInArg_DisplaysMatchingResults() throws AbstractApplicationException, ShellException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String fileName2 = "file2.txt";
        String text = FILE1_CONTENTS;
        Path filePath = createFile(fileName, text);
        Path filePath2 = createFile(fileName2, text);
        Files.write(filePath, text.getBytes());
        new ShellImpl().parseAndEvaluate("ls *.txt", output);
        assertEquals(fileName + STRING_NEWLINE + STRING_NEWLINE + fileName2 + STRING_NEWLINE, output.toString());

        Files.deleteIfExists(filePath);
        Files.deleteIfExists(filePath2);
    }

    // Sequence

    @Test
    void testSequenceCommand_EchoLs_DisplaysEchoLsResults() throws ShellException, AbstractApplicationException,
            IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file.txt";
        String text = FILE1_CONTENTS;
        Path filePath = createFile(fileName, text);
        String commandString = "echo 1; ls";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals((1 + STRING_NEWLINE + fileName + STRING_NEWLINE), output.toString());
        assertArrayEquals((1 + STRING_NEWLINE + fileName + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    public void testSequenceCommand_LsBackQuote_DisplaysEchoLsResults() {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            new ShellImpl().parseAndEvaluate("ls `;", output);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid syntax"));
        }
    }
}
