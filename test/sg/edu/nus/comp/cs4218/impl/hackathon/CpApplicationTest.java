package sg.edu.nus.comp.cs4218.impl.hackathon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createAndWriteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createDirectory;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteDirectory;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MULTIPLE_TIMES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

class CpApplicationTest {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String FILE_ONE = "fileOne.txt";
    private static final String FILE_ONE_CONT = "I love" + NEW_LINE + "CS4218" + NEW_LINE + NEW_LINE;
    private static final String DIRECTORY = "myDirectory";

    @BeforeAll
    static void initialize() throws IOException {
        createAndWriteFile(FILE_ONE, FILE_ONE_CONT);
        createDirectory(DIRECTORY);
    }

    @AfterAll
    static void terminate() throws IOException {
        deleteFile(FILE_ONE);
        deleteDirectory(DIRECTORY);
    }

    // verify that 1) the file exists, 2) the file's content is equal to the input content
    public void assertExistAndEquals(String file, String expectedContent) throws IOException {
        Path outputPath = IOUtils.resolveFilePath(file);
        assertTrue(outputPath.toFile().exists());
        assertEquals(expectedContent, Files.readString(outputPath));
    }

    @Test
    public void cp_SourceSpecifiedMoreThanOnce_CopiesFirstInstanceAndOutputWarningMessage() throws Exception {
        String commandString = "cp fileOne.txt fileOne.txt myDirectory";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertExistAndEquals(Paths.get("myDirectory", "fileOne.txt").toString(), FILE_ONE_CONT);
        assertEquals("cp: " + ERR_MULTIPLE_TIMES + NEW_LINE, output.toString());
    }

    @Test
    public void cp_SourceSpecifiedMoreThanOnceInDifferentFormat_CopiesFirstInstanceAndOutputWarningMessage() throws Exception {
        String commandString = "cp fileOne.txt src/../fileOne.txt myDirectory";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertExistAndEquals(Paths.get("myDirectory", "fileOne.txt").toString(), FILE_ONE_CONT);
        assertEquals("cp: " + ERR_MULTIPLE_TIMES + NEW_LINE, output.toString());
    }
}