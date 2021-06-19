package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

class MvApplicationTest {

    private MvApplication mvApp;
    private ByteArrayOutputStream outRes;
    private static final String CURR_DIR = Environment.currentDirectory;
    private static final String BEFORE_RENAME = "beforeRename.txt";
    private static final String AFTER_RENAME = "afterRename.txt";
    private static final String FOR_OVERWRITTEN = "forOverwrittenFile.txt";
    private static final String OVERWRITING_FILE = "overwritingFile.txt";
    private static final String FILE1 = "file1.txt";
    private static final String FILE2 = "file2.txt";
    private static final String FILE3 = "file3.txt";
    private static final String DIR1 = "dir1";
    private static final String DIR2 = "dir2";
    private static final String DIR3 = "dir3";
    private static final String DIR4 = "dir4";


    Path beforeRenamePath = IOUtils.resolveFilePath(BEFORE_RENAME);
    Path afterRenamePath = IOUtils.resolveFilePath(AFTER_RENAME);
    Path forOverwittenPath = IOUtils.resolveFilePath(FOR_OVERWRITTEN);
    Path overwrittingPath = IOUtils.resolveFilePath(OVERWRITING_FILE);
    Path file1Path = IOUtils.resolveFilePath(FILE1);
    Path file2Path = IOUtils.resolveFilePath(FILE2);
    Path file3Path = IOUtils.resolveFilePath(FILE3);
    Path dir1Path = IOUtils.resolveFilePath(DIR1);
    Path dir2Path = IOUtils.resolveFilePath(DIR2);
    Path dir3Path = IOUtils.resolveFilePath(DIR3);
    Path dir4Path = IOUtils.resolveFilePath(DIR4);
    Path dir1InDir4Path = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + DIR4 + CHAR_FILE_SEP + DIR1);


    @BeforeEach
    void setUp() {
        mvApp = new MvApplication();
        outRes = new ByteArrayOutputStream();
    }

    @BeforeAll
    static void setUpAll() {
        Environment.currentDirectory = CURR_DIR.concat(StringUtils.fileSeparator() + "testFolders" + CHAR_FILE_SEP + "app" + CHAR_FILE_SEP + "mvTestFolder");
    }

    @AfterAll
    static void terminate() {
        Environment.currentDirectory = CURR_DIR;
    }

    @AfterEach
    void tearDown() throws IOException {

        // To rename beforeRename.txt to afterRename.txt
        File file = new File(String.valueOf(beforeRenamePath));
        file.createNewFile();
        Files.deleteIfExists(afterRenamePath);

        // To overwrite overwritting file to forOverwitten file
        File overWriteFile = new File(String.valueOf(overwrittingPath));
        overWriteFile.createNewFile();
        File forOverwittenFile = new File(String.valueOf(forOverwittenPath));
        forOverwittenFile.createNewFile();

        // To rename dir1 to dir2
        File dir1 = new File(String.valueOf(dir1Path));
        dir1.mkdir();
        Files.deleteIfExists(dir2Path);

        // To move file1 into dir1
        File file1 = new File(String.valueOf(file1Path));
        file1.createNewFile();

        // To move file2 and file3 into dir4
        File file2 = new File(String.valueOf(file2Path));
        file2.createNewFile();
        File file3 = new File(String.valueOf(file3Path));
        file3.createNewFile();


        // For moving dir1 into dir3
        File dir3 = new File(String.valueOf(dir3Path));
        dir3.mkdir();

        // For moving dir1 into (dir4 containing dir1)
        File dir4 = new File(String.valueOf(dir4Path));
        dir4.mkdir();
        File dir1InDir4 = new File(String.valueOf(dir1InDir4Path));
        dir1InDir4.mkdir();

    }

    @Test
    public void run_NullArgs_ExceptionThrown() {
        String[] args = null;
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(ERR_NULL_ARGS));
    }

    @Test
    public void run_OutStream_ExceptionThrown() {
        String[] args = {BEFORE_RENAME, FOR_OVERWRITTEN};
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, null));
        assertTrue(err.getMessage().contains(ERR_NO_OSTREAM));
    }

    @Test
    public void run_InvalidOption_ExceptionThrown() {
        String[] args = { "-q", BEFORE_RENAME, FOR_OVERWRITTEN};
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains("illegal option"));

    }

    @Test
    public void run_InsufficientArg_ExceptionThrown() {
        String[] args = {BEFORE_RENAME};
        String expectedRes = "Missing Argument";
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(expectedRes));
    }

    @Test
    public void run_OverwriteFile_ReturnTrue() {
        String[] args = {BEFORE_RENAME, AFTER_RENAME};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        assertTrue(new File(String.valueOf(afterRenamePath)).exists());
    }

    @Test
    public void run_OverwriteFileNotAllowed_ReturnTrue() {
        String[] args = {"-n", BEFORE_RENAME, AFTER_RENAME};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        assertTrue(new File(String.valueOf(afterRenamePath)).exists());
    }

    @Test
    public void run_OverwriteExistingFile_ReturnTrue() {
        String[] args = {OVERWRITING_FILE, FOR_OVERWRITTEN};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        assertTrue(new File(String.valueOf(forOverwittenPath)).exists());
    }

    @Test
    public void run_OverwriteFileNotAllowed_ExceptionThrown() {
        String[] args = { "-n", OVERWRITING_FILE, FOR_OVERWRITTEN};
        String expectedRes = "Target filename forOverwrittenFile.txt already exists.";
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(expectedRes));
    }

    @Test
    public void run_OverwriteMoreFile_ExceptionThrown() {
        String[] args = {OVERWRITING_FILE, FOR_OVERWRITTEN, BEFORE_RENAME};
        String expectedRes = "Too many arguments";
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(expectedRes));
    }

    @Test
    public void run_MoveNonExistFile_ExceptionThrown() {
        String[] args = { "-n", "notExistFile.txt", DIR1};
        String expectedRes = "notExistFile.txt not found. No such file or directory";
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(expectedRes));
    }

    @Test
    public void run_RenameNonExistFile_ExceptionThrown() {
        String[] args = {"notExistFile.txt", AFTER_RENAME};
        String expectedRes = "notExistFile.txt not found. No such file or directory";
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(expectedRes));
    }


    @Test
    public void run_RenameDir_ReturnTrue() {
        String[] args = {DIR1, DIR2};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        assertTrue(new File(String.valueOf(dir2Path)).exists());
    }

    @Test
    public void run_MoveMoreFileToDir_ReturnTrue() throws IOException {
        String[] args = {FILE2, FILE3, DIR4};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        Path expectPath1 = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + DIR4 + CHAR_FILE_SEP + FILE2);
        Path expectPath2 = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + DIR4 + CHAR_FILE_SEP + FILE3);
        boolean fileMoved = Files.exists(expectPath1) && Files.exists(expectPath2) && !Files.exists(file2Path) && !Files.exists(file3Path);
        assertTrue(fileMoved);

        Files.deleteIfExists(expectPath1);
        Files.deleteIfExists(expectPath2);
    }

    @Test
    public void run_MoveFileToDir_ReturnTrue() throws IOException {
        String[] args = {FILE1, DIR1};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        Path expectPath = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + DIR1 + CHAR_FILE_SEP + FILE1);
        boolean fileMoved = Files.exists(expectPath) && !Files.exists(file1Path);
        assertTrue(fileMoved);

        Files.deleteIfExists(expectPath);
    }

    @Test
    public void run_MoveDirToAnotherDir_ReturnTrue() throws IOException {
        String[] args = {DIR1, DIR3};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        Path expectPath = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + DIR3 + CHAR_FILE_SEP + DIR1);
        boolean fileMoved = Files.exists(expectPath) && !Files.exists(dir1Path);
        assertTrue(fileMoved);

        Files.deleteIfExists(expectPath);
    }

    @Test
    public void run_MoveToUsedDirWithSameSubDirCanOverwrite_ExceptionThrown() {
        String[] args = {DIR1, DIR4};
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        Path expectPath = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + DIR4 + CHAR_FILE_SEP + DIR1);
        boolean fileMoved = Files.exists(expectPath) && !Files.exists(dir1Path);
        assertTrue(fileMoved);
    }

    @Test
    public void run_MoveToUsedDirWithSameSubDirCannotOverwrite_ExceptionThrown() {
        String[] args = {"-n", DIR1, DIR4};
        String expectedRes = "Error: Unable to move file";
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(expectedRes));
    }

    @Test
    public void run_MoveFileAndDirIntoSameDir_ExceptionThrown() throws IOException {
        String[] args = {DIR1, FILE1, DIR1};
        String expectedRes = "Error: Unable to move file";
        Exception err = assertThrows(MvException.class, () -> mvApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains(expectedRes));
    }


    @Test
    void mvFilesToFolder_NullDestFile_ThrowsException() {
        assertThrows(Exception.class, () -> mvApp.mvSrcFileToDestFile(false, "(@&^R()", null));
    }

    @Test
    void run_MoveDirToFile_ExceptionThrown() throws Exception {
        String[] args = {DIR1, "MoveDirToFileName.txt"};
        Path path = IOUtils.resolveFilePath(BEFORE_RENAME);
        assertDoesNotThrow(() -> mvApp.run(args, System.in, outRes));
        Files.exists(path);

    }


}
