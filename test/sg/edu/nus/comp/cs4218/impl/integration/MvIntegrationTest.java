package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class MvIntegrationTest {

    public static final String TEMP = "temp-mv-integration";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static final Path TEMP_PATH2 = Paths.get(Environment.currentDirectory, TEMP + fileSeparator() + "dir1");
    private static final String CURR_DIR = Environment.currentDirectory;

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

    // Sequence

    @Test
    public void testSequence_PasteNewFile_DisplayMergedResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file7.txt";
        String text = "file7 contents";
        Path filePath = createFile(fileName);
        Files.write(filePath, text.getBytes());
        String commandString = "mv 'file7.txt' \"newfile.txt\"; paste newfile.txt newfile.txt";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("file7 contents\tfile7 contents" + STRING_NEWLINE, output.toString());
    }


    // Quoting

    @Test
    public void testQuoting_MoveFileNameWithSingleQuote_MoveSuccessful() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file8.txt";
        String text = "file8 contents";
        String fileName2 = "file3.txt";
        String text2 = "file contents3";
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        String commandString = "mv 'file8.txt' dir1; ls dir1";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("dir1:" + STRING_NEWLINE + "file8.txt" + STRING_NEWLINE, output.toString());
    }


    // Globbing

    @Test
    public void testGlobbing_MoveFileNameWithWildcard_MoveSuccessful() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "moveFile1.txt";
        String text = "move file contents1";
        String fileName2 = "moveFile2.txt";
        String text2 = "move file contents2";
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        String commandString = "mv *.txt dir1; ls dir1";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("dir1:" + STRING_NEWLINE + "moveFile1.txt" + STRING_NEWLINE + "moveFile2.txt" + STRING_NEWLINE, output.toString());
    }

    // Command substitution

    @Test
    public void testCommandSubstitution_SubstituteInvalidArg_ThrowException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file.txt";
        String text = "file contents";

        String fileName2 = "file2.txt";
        String text2 = "file contents2";
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "mv `*.txt` dir1";
        Exception err = assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
        assertEquals("sg.edu.nus.comp.cs4218.exception.ShellException: shell: file.txt: Invalid app", err.toString());
    }

    @Test
    public void testCommandSubstitution_SubstituteValidArg_ThrowException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "file.txt";
        String text = "file contents";

        String fileName2 = "file2.txt";
        String text2 = "file2.txt";
        Path filePath = createFile(fileName);
        Path filePath2 = createFile(fileName2);
        Files.write(filePath, text.getBytes());
        Files.write(filePath2, text2.getBytes());

        String commandString = "mv `ls *2.txt` dir1; ls dir1";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertTrue(output.toString().contains(fileName2) && output.toString().contains("dir1"));
    }


}
