
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.writeFile;

public class TeeApplicationTest {

    TeeApplication teeApplication;
    TeeApplication teeApp;

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SOURCE_DIRECTORY = Paths.get(Environment.currentDirectory).resolve("src").toString();
    private static final String NON_EXIST_FILE = "ioeqfhe";
    private static final String FILE_ONE = "fIlE-OnE";
    private static final String FILE_TWO = "FIle Two2";
    private static final String FILE_ONE_CONTENT = "I love" + NEW_LINE + "CS4218";
    private static final String FILE_TWO_CONTENT = "~!@#$%+";
    private static final String HUMAN_INPUT_ONE = "I love CS4218" + NEW_LINE;
    private static final String HUMAN_INPUT_TWO = "Testing is so fun" + NEW_LINE;
    private static final String TEE_MESSAGE = "tee: ";
    private static final String DIRECTORY_MESSAGE = ": Is a directory";

    @BeforeEach
    void setUp() throws IOException{
        teeApplication = new TeeApplication();
        teeApplication.stdout = System.out;
        teeApp = spy(TeeApplication.class);
        createFile(FILE_ONE);
        writeFile(FILE_ONE, FILE_ONE_CONTENT);
        createFile(FILE_TWO);
        writeFile(FILE_TWO, FILE_TWO_CONTENT);
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteFile(FILE_ONE);
        deleteFile(FILE_TWO);
        deleteFile(NON_EXIST_FILE);
    }

    @Test
    void checkIfFileIsAccessible_NullFileName_ThrowsException(){
        assertThrows(Exception.class, () -> teeApplication.checkIfFileIsAccessible((String) null));
    }

    @Test
    void countFromFiles_FileNameIsDirectory_ReturnsStringOfWarning() throws Exception {
        String expectedOutput = TEE_MESSAGE + SOURCE_DIRECTORY + DIRECTORY_MESSAGE;
        assertEquals(expectedOutput ,teeApplication.checkIfFileIsAccessible(SOURCE_DIRECTORY));
    }

    @Test
    void countFromFiles_FileNameContainsOneDirectoryAndOneFile_ReturnsStringOfWarning() throws Exception {
        String[] files = new String[] {SOURCE_DIRECTORY, FILE_ONE};
        String expectedOutput = TEE_MESSAGE + SOURCE_DIRECTORY + DIRECTORY_MESSAGE;
        assertEquals(expectedOutput ,teeApplication.checkIfFileIsAccessible(files));
    }

    // no options, null stdin
    @Test
    void teeFromStdin_NullStdinNoOptions_ThrowsException() throws Exception {
        assertThrows(Exception.class, () -> teeApplication.teeFromStdin(false, null));
    }

    // no options, directory
    @Test
    void teeFromStdin_OneDirectoryNoOptions_ReturnsStringOfWarning() throws Exception {
        String[] files = new String[] {SOURCE_DIRECTORY};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        String expectedOutput = TEE_MESSAGE + SOURCE_DIRECTORY + DIRECTORY_MESSAGE;
        assertEquals(expectedOutput,teeApplication.teeFromStdin(false, System.in, files));
    }

    // no options, stdin
    @Test
    void teeFromStdin_NoFilesNoOptions_ReturnsEmptyString() throws Exception {
        String[] files = new String[]{};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        String expectedOutput = "";
        assertEquals(expectedOutput, teeApplication.teeFromStdin(false, System.in, files));
    }

    // no options, 1 existing file
    @Test
    void teeFromStdin_OneExistingFileNoOptions_ReturnsEmptyString() throws Exception {
        String[] files = new String[] {FILE_ONE};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_TWO.getBytes())); // simulates human typing
        String expectedOutput = "";
        assertEquals(expectedOutput,teeApplication.teeFromStdin(false, System.in, files));
        byte[] fileBytes = Files.readAllBytes(Path.of(FILE_ONE));
        String newFileContent = new String(fileBytes);
        assertEquals(HUMAN_INPUT_TWO, newFileContent);
    }

    // no options, non-existing file
    @Test
    void teeFromStdin_NonExistingFileNoOptions_ReturnsStringOfWarning() throws Exception {
        String[] files = new String[] {NON_EXIST_FILE};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        String expectedOutput = "";
        assertEquals(expectedOutput,teeApplication.teeFromStdin(false, System.in, files));
        byte[] fileBytes = Files.readAllBytes(Path.of(NON_EXIST_FILE));
        String newFileContent = new String(fileBytes);
        assertEquals(HUMAN_INPUT_ONE, newFileContent);
    }

    // no options, 1 file 1 new file
    @Test
    void teeFromStdin_OneFileOneNonExistingFileOneDirectoryNoOptions_ReturnsStringOfWarning() throws Exception {
        String[] files = new String[] {FILE_ONE, NON_EXIST_FILE, SOURCE_DIRECTORY};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_TWO.getBytes())); // simulates human typing
        String expectedOutput = TEE_MESSAGE + SOURCE_DIRECTORY + DIRECTORY_MESSAGE;
        assertEquals(expectedOutput,teeApplication.teeFromStdin(false, System.in, files));
        byte[] fileOneBytes = Files.readAllBytes(Path.of(FILE_ONE));
        String fileOneContent = new String(fileOneBytes);
        byte[] nonExistFileBytes = Files.readAllBytes(Path.of(NON_EXIST_FILE));
        String nonExistFileCont = new String(nonExistFileBytes);
        assertEquals(HUMAN_INPUT_TWO, fileOneContent);
        assertEquals(HUMAN_INPUT_TWO, nonExistFileCont);
    }

    // -a no files
    @Test
    void teeFromStdin_NoFilesWithIsAppend_ReturnsEmptyString() throws Exception {
        String[] files = new String[]{};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        String expectedOutput = "";
        assertEquals(expectedOutput, teeApplication.teeFromStdin(true, System.in, files));
    }

    // -a 1 existing file
    @Test
    void teeFromStdin_OneExistingFileWithIsAppend_ReturnsEmptyString() throws Exception {
        String[] files = new String[]{FILE_ONE};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        String expectedOutput = "";
        assertEquals(expectedOutput, teeApplication.teeFromStdin(true, System.in, files));
        byte[] fileBytes = Files.readAllBytes(Path.of(FILE_ONE));
        String newFileContent = new String(fileBytes);
        String expectedContent = FILE_ONE_CONTENT + HUMAN_INPUT_ONE;
        assertEquals(expectedContent, newFileContent);
    }

    // -a 1 nonexistingfile
    @Test
    void teeFromStdin_OneNonExistingFileWithIsAppend_ReturnsEmptyString() throws Exception {
        String[] files = new String[]{NON_EXIST_FILE};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_TWO.getBytes())); // simulates human typing
        String expectedOutput = "";
        assertEquals(expectedOutput, teeApplication.teeFromStdin(true, System.in, files));
        byte[] fileBytes = Files.readAllBytes(Path.of(NON_EXIST_FILE));
        String fileContent = new String(fileBytes);
        String expectedContent = HUMAN_INPUT_TWO;
        assertEquals(expectedContent, fileContent);
    }

    // -a 1 existing file 1 nonexisting file 1 directory
    @Test
    void teeFromStdin_OneExistingFileOneNonExistingFileOneDirectoryWithIsAppend_ReturnsStringOfWarning() throws Exception {
        String[] files = new String[]{FILE_TWO, NON_EXIST_FILE, SOURCE_DIRECTORY};
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_TWO.getBytes())); // simulates human typing
        String expectedOutput = TEE_MESSAGE + SOURCE_DIRECTORY + DIRECTORY_MESSAGE;
        assertEquals(expectedOutput, teeApplication.teeFromStdin(true, System.in, files));
        byte[] fileTwoBytes = Files.readAllBytes(Path.of(FILE_TWO));
        String fileTwoContent = new String(fileTwoBytes);
        byte[] nonExistFileBytes = Files.readAllBytes(Path.of(NON_EXIST_FILE));
        String nonExistFileCont = new String(nonExistFileBytes);
        assertEquals(FILE_TWO_CONTENT + HUMAN_INPUT_TWO, fileTwoContent);
        assertEquals(HUMAN_INPUT_TWO, nonExistFileCont);
    }
}

