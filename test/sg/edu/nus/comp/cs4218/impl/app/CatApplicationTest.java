package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.writeFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;


class CatApplicationTest {
    CatApplication catApplication;
    CatApplication catApp;

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SOURCE_DIRECTORY = Paths.get(Environment.currentDirectory).resolve("src").toString();
    private static final String EMPTY_FILE = "empty-file";
    private static final String NON_EXIST_FILE = "ioeqfhe";
    private static final String FILE_ONE = "fIlE-OnE";
    private static final String FILE_TWO = "FIle Two2";
    private static final String FILE_ONE_CONTENT = "I love" + NEW_LINE + "CS4218";
    private static final String FILE_TWO_CONTENT = "~!@#$%+";
    private static final String NON_EXIST_ERR_MSG = "cat: No such file or directory" + NEW_LINE;
    private static final String IS_DIR_ERR_MSG = "cat: This is a directory" + NEW_LINE;

    private static final String HUMAN_INPUT_ONE = "I love CS4218" + NEW_LINE;
    private static final String HUMAN_INPUT_TWO = "Testing is so fun" + NEW_LINE;
    private static final String OUTPUT_FILE = "output";

    @BeforeAll
    static void init() throws IOException {
        createFile(EMPTY_FILE);
        createFile(OUTPUT_FILE);
        createFile(FILE_ONE);
        writeFile(FILE_ONE, FILE_ONE_CONTENT);
        createFile(FILE_TWO);
        writeFile(FILE_TWO, FILE_TWO_CONTENT);
    }

    @AfterAll
    static void terminate() {
        deleteFile(EMPTY_FILE);
        deleteFile(FILE_ONE);
        deleteFile(FILE_TWO);
    }

    @BeforeEach
    void setUp() {
        catApplication = new CatApplication();
        catApp = spy(CatApplication.class);
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteFile(OUTPUT_FILE);
        createFile(OUTPUT_FILE);
    }

    @Test
    void catFiles_NullFile_ThrowsException() {
        assertThrows(Exception.class, () -> catApplication.catFiles(false, (String) null));
    }

   @Test
    void catFiles_EmptyFile_ReturnsEmptyString() throws Exception {
       assertEquals("", catApplication.catFiles(false, EMPTY_FILE));
   }

    @Test
    void catFiles_NonExistingFile_ReturnsErrorMessage() throws Exception {
        assertEquals(NON_EXIST_ERR_MSG, catApplication.catFiles(false, NON_EXIST_FILE));
    }

    @Test
    void catFiles_FileNameIsDirectory_ReturnsErrorMessage() throws Exception {
        assertEquals(IS_DIR_ERR_MSG, catApplication.catFiles(false, SOURCE_DIRECTORY));
    }

    @Test
    void catFiles_OneFileWithLineNum_ReturnsFileContentWithLineNum() throws Exception {
        String expectedContent = "1 I love" + NEW_LINE + "2 CS4218" + NEW_LINE;
        assertEquals(expectedContent, catApplication.catFiles(true, FILE_ONE));
    }

    @Test
    void catFiles_ManyFilesWithoutLineNum_ReturnsFileContentsWithoutLineNum() throws Exception {
        String[] files = new String[]{FILE_ONE, FILE_TWO};
        String expectedContent = FILE_ONE_CONTENT + NEW_LINE + FILE_TWO_CONTENT + NEW_LINE;
        assertEquals(expectedContent, catApplication.catFiles(false, files));
    }

    @Test
    void catFiles_ManyFilesWithLineNum_ReturnsFileContentsWithLineNum() throws Exception {
        String[] files = new String[]{FILE_TWO, FILE_ONE};
        String expectedContent = "1 ~!@#$%+" + NEW_LINE + "2 I love" + NEW_LINE
                + "3 CS4218" + NEW_LINE;
        assertEquals(expectedContent, catApplication.catFiles(true, files));
    }

    @Test
    void catFiles_ManyFilesWithDirectoryWithLineNum_ReturnsFileContentsWithLineNumWithErrorMessage() throws Exception {
        String[] files = new String[]{FILE_ONE, SOURCE_DIRECTORY, FILE_TWO};
        String expectedContent = "1 I love" + NEW_LINE + "2 CS4218" + NEW_LINE +
                IS_DIR_ERR_MSG + "3 ~!@#$%+" + NEW_LINE;
        assertEquals(expectedContent, catApplication.catFiles(true, files));
    }

    @Test
    void catFiles_ManyFilesWithDirectoryWithNonExistingFile_ReturnsFileContentsWithErrorMessages() throws Exception {
        String[] files = new String[]{NON_EXIST_FILE, FILE_ONE, SOURCE_DIRECTORY, FILE_TWO};
        String expectedContent = NON_EXIST_ERR_MSG + "1 I love" + NEW_LINE + "2 CS4218"
                        + NEW_LINE + IS_DIR_ERR_MSG + "3 ~!@#$%+" + NEW_LINE;
        assertEquals(expectedContent, catApplication.catFiles(true, files));
    }

    @Test
    void catStdin_NullStdin_ThrowsException() {
        assertThrows(Exception.class, () -> catApplication.catStdin(false, null));
    }

    @Test
    void catStdin_SystemInStdinSystemOutStdout_ReturnsEmptyString() throws Exception {
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes())); // simulates human typing
        catApplication.stdout = System.out;
        String stringOutput = catApplication.catStdin(true, System.in);
        assertEquals("", stringOutput); // should not return anything
    }

    @Test
    void catStdin_SystemInStdinFileStdoutWithLineNum_ReturnsInputtedContentWithLineNum() throws Exception {
        System.setIn(new ByteArrayInputStream((HUMAN_INPUT_ONE + HUMAN_INPUT_TWO).getBytes())); // simulate human typing
        catApplication.stdout = new FileOutputStream(OUTPUT_FILE);
        String stringOutput = catApplication.catStdin(true, System.in);
        assertEquals("1 " + HUMAN_INPUT_ONE + "2 " + HUMAN_INPUT_TWO, stringOutput);
    }

    @Test
    void catStdin_FileStdinSystemOutStdoutWithLineNum_ReturnsEmptyString() throws Exception {
        catApplication.stdout = System.out;
        InputStream fileStdin = new FileInputStream(FILE_ONE);
        String stringOutput = catApplication.catStdin(true, fileStdin);
        assertEquals("", stringOutput); // should not return anything
    }

    @Test
    void catStdin_FileStdinFileStdout_ReturnsFileContent() throws Exception {
        catApplication.stdout = new FileOutputStream(OUTPUT_FILE);
        InputStream fileStdin = new FileInputStream(FILE_ONE);
        String stringOutput = catApplication.catStdin(false, fileStdin);
        assertEquals(FILE_ONE_CONTENT + NEW_LINE, stringOutput);
    }

    @Test
    void catFileAndStdin_NullStdin_ThrowsException() {
        assertThrows(Exception.class, () -> catApplication.catFileAndStdin(true, null, FILE_ONE));
    }

    @Test
    void catFileAndStdin_NullFileName_ThrowsException() {
        assertThrows(Exception.class, () -> catApplication.catFileAndStdin(true, System.in,
                (String) null));
    }

    @Test
    void catFileAndStdin_OneFileOneSystemInToSystemOutStdout_ReturnsEmptyString() throws Exception {
        String[] inputs = new String[]{"-", FILE_ONE};
        catApp.stdout = System.out;
        when(catApp.catStdin(false, System.in)).thenReturn(HUMAN_INPUT_TWO);
        when(catApp.catFiles(false, FILE_ONE)).thenReturn(FILE_ONE_CONTENT + NEW_LINE);
        String stringOutput = catApp.catFileAndStdin(false, System.in, inputs);
        assertEquals("", stringOutput); // should print to system.out and not return anything
    }

    @Test
    void catFileAndStdin_ManyFilesOneSystemInToFileStdout_ReturnsContents() throws Exception {
        String[] inputs = new String[]{FILE_TWO, "-", FILE_ONE};
        catApp.stdout = new FileOutputStream(OUTPUT_FILE);
        when(catApp.catFiles(false, FILE_TWO)).thenReturn(FILE_TWO_CONTENT + NEW_LINE);
        when(catApp.catStdin(false, System.in)).thenReturn(HUMAN_INPUT_TWO);
        when(catApp.catFiles(false, FILE_ONE)).thenReturn(FILE_ONE_CONTENT + NEW_LINE);
        String stringOutput = catApp.catFileAndStdin(false, System.in, inputs);
        String expectedOutput = FILE_TWO_CONTENT + NEW_LINE + HUMAN_INPUT_TWO + FILE_ONE_CONTENT + NEW_LINE;
        assertEquals(expectedOutput, stringOutput);
    }

    @Test
    void catFileAndStdin_OneFileManySystemInToFileStdout_ReturnsContents() throws Exception {
        String[] inputs = new String[]{"-", FILE_TWO, "-"};
        catApp.stdout = new FileOutputStream(OUTPUT_FILE);
        when(catApp.catFiles(false, FILE_TWO)).thenReturn(FILE_TWO_CONTENT + NEW_LINE);
        when(catApp.catStdin(false, System.in)).thenReturn(HUMAN_INPUT_TWO)
                .thenReturn(""); // EOF was found for second read from input stream, returns empty string
        String stringOutput = catApp.catFileAndStdin(false, System.in, inputs);
        String expectedOutput = HUMAN_INPUT_TWO + FILE_TWO_CONTENT + NEW_LINE;
        assertEquals(expectedOutput, stringOutput);
    }

    @Test
    void catFileAndStdin_ManyFileManySystemInToFileStdout_ReturnsContents() throws Exception {
        String[] inputs = new String[]{FILE_ONE, "-", FILE_TWO, "-"};
        catApp.stdout = new FileOutputStream(OUTPUT_FILE);
        when(catApp.catFiles(false, FILE_ONE)).thenReturn(FILE_ONE_CONTENT + NEW_LINE);
        when(catApp.catStdin(false, System.in)).thenReturn(HUMAN_INPUT_ONE)
                .thenReturn(""); // EOF was found for second read from input stream, returns empty string
        when(catApp.catFiles(false, FILE_TWO)).thenReturn(FILE_TWO_CONTENT + NEW_LINE);
        String stringOutput = catApp.catFileAndStdin(false, System.in, inputs);
        String expectedOutput = FILE_ONE_CONTENT + NEW_LINE + HUMAN_INPUT_ONE + FILE_TWO_CONTENT + NEW_LINE;
        assertEquals(expectedOutput, stringOutput);
    }
}

