package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class EchoIntegrationTest {

    public static final String TEMP = "temp";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);

    @BeforeEach
    void createTemp() throws IOException {
        Files.createDirectory(TEMP_PATH);
    }

    @AfterEach
    void deleteTemp() throws IOException {
        Files.walk(TEMP_PATH)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    private Path createFile(String name) throws IOException {
        return createFile(name, TEMP_PATH);
    }

    private Path createFile(String name, Path inPath) throws IOException {
        Path path = inPath.resolve(name);
        Files.createFile(path);
        return path;
    }

    // Quoting

    @Test
    void test_DoubleQuoting_DisplaysResult() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo \" hi hi\"";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((" hi hi"  + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals(" hi hi"  + STRING_NEWLINE, output.toString());
    }

    @Test
    void test_SingleQuoting_DisplaysResult() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo \'heyy hi\'";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("heyy hi"  + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertEquals("heyy hi"  + STRING_NEWLINE, output.toString());
    }

    @Test
    void testQuoting_BackQuote_DisplaysEchoResults() throws ShellException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo 'test echo`'";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("test echo`" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testQuoting_SingleDoubleQuote_DisplaysEchoResults() throws ShellException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo \"test echo ' '\"";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("test echo ' '" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    // IO Redirection

    @Test
    void testIORedirection_RedirectToPaste_FileContentsUnchanged() throws ShellException, AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file1.txt";
        Path filePath = createFile(fileName);
        String fileText = "file text contents";
        Files.write(filePath, fileText.getBytes());
        String commandString = "echo \"hi\" > " + TEMP + CHAR_FILE_SEP + fileName;
        new ShellImpl().parseAndEvaluate(commandString, output);
        String content = Files.readString(filePath, StandardCharsets.US_ASCII);
        assertEquals("hi" + STRING_NEWLINE, content);
        assertArrayEquals(("hi" + STRING_NEWLINE).getBytes(), content.getBytes(StandardCharsets.UTF_8));
    }

    // Pipe

    @Test
    void testPipe_RedirectToPaste_DisplaysMergedResults() throws ShellException, AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file2.txt";
        Path filePath = createFile(fileName);
        String text = "file contents";
        Files.write(filePath, text.getBytes());
        String commandString = "echo \"hi\" | paste -s - " + TEMP + CHAR_FILE_SEP + fileName;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("hi" + STRING_NEWLINE + text, output.toString());
        assertArrayEquals(("hi" + STRING_NEWLINE + text).getBytes(), output.toByteArray());
    }


    // Sequence

    @Test
    void testSequenceCommand_EchoEcho_DisplaysEchoResults() throws ShellException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo 1; echo 2";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("1" + STRING_NEWLINE + "2" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testSequenceCommand_EchoCat_DisplaysEchoCatResults() throws ShellException, AbstractApplicationException,
            IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file.txt";
        Path filePath = createFile(fileName);
        String text = "file contents";
        Files.write(filePath, text.getBytes());
        String commandString = "echo 1; cat " + TEMP + CHAR_FILE_SEP + fileName;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((1 + STRING_NEWLINE + text + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testSequenceCommand_EchoPaste_DisplaysEchoPasteResults() throws ShellException, AbstractApplicationException,
            IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file.txt";
        Path filePath = createFile(fileName);
        String text = "file contents";
        Files.write(filePath, text.getBytes());
        String commandString = "echo 1; paste " + TEMP + CHAR_FILE_SEP + fileName;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals((1 + STRING_NEWLINE + text + STRING_NEWLINE), output.toString());
        assertArrayEquals((1 + STRING_NEWLINE + text + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testSequenceCommand_MultipleSequenceCommands_DisplaysEchoResults() throws ShellException,
            AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo 1; echo 2; echo 3; echo 4";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("1" + STRING_NEWLINE + "2" + STRING_NEWLINE + "3" + STRING_NEWLINE + "4" +
                STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    // Command Substitution

    @Test
    void testCommandSubstitution_EchoEcho_DisplaysInnerEchoResult() throws AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo `echo \"hello\"`";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("hello" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testCommandSubstitution_WcEcho_DisplaysLinesWordsBytesFilename() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file.txt";
        Path filePath = createFile(fileName);
        String commandString = "wc `echo " + TEMP + CHAR_FILE_SEP + fileName + "`";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("       0       0       0 " + TEMP + CHAR_FILE_SEP + fileName + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testCommandSubstitution_MultipleCommandSubstitutions_DisplaysEchoResults() throws ShellException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String commandString = "echo `echo 1` `echo 2`";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("1 2" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

}
