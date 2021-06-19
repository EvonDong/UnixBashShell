package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_TAB;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class PasteIntegrationTest {

    public static final String TEMP = "temp-paste-integration";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static final Path TEMP_PATH2 = Paths.get(Environment.currentDirectory, TEMP + fileSeparator() + "dir1");
    private static final String CURR_DIR = Environment.currentDirectory;
    private static final String FILE1 = "file.txt";
    private static final String FILE1_CONTENTS = "file contents";
    private static final String FILE2 = "file2.txt";
    private static final String FILE2_CONTENTS = "file contents2";

    @BeforeEach
    void createTemp() throws IOException {
        Files.createDirectory(TEMP_PATH);
        Files.createDirectory(TEMP_PATH2);
        Environment.currentDirectory = String.valueOf(TEMP_PATH);
    }

    @AfterEach
    void deleteTemp() throws IOException {
        Files.walk(TEMP_PATH)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        Environment.currentDirectory = CURR_DIR;
    }

    private Path createFile(String name) throws IOException {
        return createFile(name, TEMP_PATH);
    }

    private Path createFile(String name, Path inPath) throws IOException {
        Path path = inPath.resolve(name);
        Files.createFile(path);
        return path;
    }

    // Pipe

    @Test
    public void testPipe_CatPipePaste_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;

        String fileName2 = FILE2;
        String text2 = FILE2_CONTENTS;
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "cat file2.txt | paste - file.txt";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((text2 + CHAR_TAB + text + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    // Sequence

    @Test
    public void testSequence_PasteNewFile_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;

        String fileName2 = FILE2;
        String text2 = FILE2_CONTENTS;
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "cat file2.txt; paste file2.txt file.txt";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((FILE2_CONTENTS + STRING_NEWLINE + FILE2_CONTENTS + CHAR_TAB + FILE1_CONTENTS + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    // IORedirection

    @Test
    public void testIORedirection_EchoRedirectToPaste_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;

        String fileName2 = FILE2;
        String text2 = FILE2_CONTENTS;
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "echo \"hii\" | paste - file.txt > AB.txt";
        File tempFile = new File(String.valueOf(IOUtils.resolveFilePath("AB.txt")));
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("" , output.toString());
        assertTrue(tempFile.exists());
    }

    // Globbing

    @Test
    public void testGlobbing_EchoRedirectToPaste_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;

        String fileName2 = FILE2;
        String text2 = FILE2_CONTENTS;
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "paste *.txt";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals( FILE1_CONTENTS + CHAR_TAB + FILE2_CONTENTS + STRING_NEWLINE , output.toString());
    }

    // Globbing

    @Test
    public void testQuoting_EchoRedirectToPaste_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;

        String fileName2 = FILE2;
        String text2 = FILE2_CONTENTS;
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "paste \"file.txt\"";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals( FILE1_CONTENTS + STRING_NEWLINE, output.toString());
    }

    // Command substitution

    @Test
    public void testCommandSubstitution_SubstituteArgs_ThrowException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = FILE1;
        String text = FILE1_CONTENTS;

        String fileName2 = FILE2;
        String text2 = FILE2_CONTENTS;
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "paste `file.txt`";
        Exception err = assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
        assertEquals( "sg.edu.nus.comp.cs4218.exception.ShellException: shell: file.txt: Invalid app", err.toString());
    }

}
