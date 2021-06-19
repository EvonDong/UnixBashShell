package sg.edu.nus.comp.cs4218.impl.tdd_regression.ef1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.app.MvApplication;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

@SuppressWarnings({"PMD.LongVariable"})
class MvApplicationTest {
    @TempDir
    File tempDir;

    private MvApplication mvApplication;
    public static final String ILLEGAL_FLAG_MSG = "illegal option -- ";
    public static final String ERR_SAME_FILE = "Source and destination are the same file";

    static final String ORIGINAL_DIR = Environment.currentDirectory;

    static final String FILE1_CONTENTS = "This is file1.txt content";
    static final String FILE2_CONTENTS = "This is another file2.txt content";
    static final String SUBFILE2_CONTENTS = "This is a subfolder1 file2.txt content";

    @BeforeEach
    void setUp() throws IOException {
        mvApplication = new MvApplication();
        Environment.setCurrentDirectory(tempDir.getAbsolutePath());

        new File(tempDir, "subfolder1").mkdir();
        new File(tempDir, "subfolder1/subsubfolder1").mkdir();
        new File(tempDir, "subfolder1/file2.txt").createNewFile();
        File subFile2 = new File(tempDir, "subfolder1/file2.txt");
        FileWriter subFile2Writer = new FileWriter(subFile2);
        subFile2Writer.write(SUBFILE2_CONTENTS);
        subFile2Writer.close();

        new File(tempDir, "subfolder2").mkdir();
        new File(tempDir, "subfolder2/subsubfolder2").mkdir();

        new File(tempDir, "subfolder3").mkdir();

        new File(tempDir, "file1.txt").createNewFile();
        File file1 = new File(tempDir, "file1.txt");
        FileWriter file1Writer = new FileWriter(file1);
        file1Writer.write(FILE1_CONTENTS);
        file1Writer.close();

        new File(tempDir, "file2.txt").createNewFile();
        File file2 = new File(tempDir, "file2.txt");
        FileWriter file2Writer = new FileWriter(file2);
        file2Writer.write(FILE2_CONTENTS);
        file2Writer.close();

        File blockedFolder = new File(tempDir, "blocked");
        blockedFolder.mkdir();
        blockedFolder.setWritable(false);

        File unwritableFile = new File(tempDir, "unwritable");
        unwritableFile.createNewFile();
        unwritableFile.setWritable(false);
    }

    @AfterEach
    void tearDown() {
        // set files and folders to be writable to enable clean up
        File blockedFolder = new File(tempDir, "blocked");
        blockedFolder.setWritable(true);
        File unwritableFile = new File(tempDir, "unwritable");
        unwritableFile.setWritable(true);
    }

    @AfterAll
    static void tearDownAll() {
        Environment.setCurrentDirectory(System.getProperty("user.dir"));
    }

    @Test
    public void run_nullArgs_ThrowsException() {
        String[] argList = null;
        Throwable nullArgs = assertThrows(MvException.class, () -> mvApplication.run(argList, System.in, System.out));
        assertEquals(new MvException(ERR_NULL_ARGS).getMessage(), nullArgs.getMessage());
    }

    @Test
    public void run_invalidFlag_ThrowsException() {
        String[] argList = new String[]{"-a", "file1.txt", "file2.txt"};
        Throwable invalidFlag = assertThrows(MvException.class, () -> mvApplication.run(argList, System.in, System.out));
        assertEquals(new MvException(ILLEGAL_FLAG_MSG + "a").getMessage(), invalidFlag.getMessage());
    }

    @Test
    public void run_WithoutFlag2ArgsDestExist_RemoveSrcAndOverrideFile() throws Exception {
        String[] argList = new String[]{"file1.txt", "file2.txt"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "file1.txt");
        File expectedNewFile = new File(tempDir, "file2.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(FILE1_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithoutFlags2ArgsDestFileNonExist_RenameFile() throws Exception {
        String[] argList = new String[]{"file2.txt", "file4.txt"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "file2.txt");
        File expectedNewFile = new File(tempDir, "file4.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(FILE2_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithFlagRenameOneSubFileIntoFolder_RenameSubFile() throws Exception {
        String[] argList = new String[]{"subfolder1/file2.txt", "file5.txt"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder1/file2.txt");
        File expectedNewFile = new File(tempDir, "file5.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(SUBFILE2_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithFlagRenameOneSubFileIntoSubFile_RenameSubFile() throws Exception {
        String[] argList = new String[]{"subfolder1/file2.txt", "subfolder2/file5.txt"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder1/file2.txt");
        File expectedNewFile = new File(tempDir, "subfolder2/file5.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(SUBFILE2_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithFlags2ArgsDestFoldersNonExist_RenameFile() throws Exception {
        String[] argList = new String[]{"-n", "subfolder1", "newSubFolder"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder1");
        File expectedNewFile = new File(tempDir, "newSubFolder");

        assertTrue(!expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        assertTrue(Files.isDirectory(expectedNewFile.toPath()));
        List<String> subFiles = Arrays.asList(expectedNewFile.listFiles())
                .stream().map(f -> f.getName()).collect(Collectors.toList());
        assertEquals(2, subFiles.size());
        assertTrue(subFiles.contains("file2.txt"));
        assertTrue(subFiles.contains("subsubfolder1"));
    }

    @Test
    public void run_WithFlags2ArgsSrcFolderDestFileNonExist_RenameFile() throws Exception {
        String[] argList = new String[]{"-n", "subfolder1", "file3.txt"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder1");
        File expectedNewFile = new File(tempDir, "file3.txt");

        assertTrue(!expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        assertTrue(Files.isDirectory(expectedNewFile.toPath()));
        List<String> subFiles = Arrays.asList(expectedNewFile.listFiles())
                .stream().map(f -> f.getName()).collect(Collectors.toList());
        assertEquals(2, subFiles.size());
        assertTrue(subFiles.contains("file2.txt"));
        assertTrue(subFiles.contains("subsubfolder1"));
    }

    @Test
    public void run_WithFlags2ArgsDiffFileTypesNonExist_ConvertFolderToFile() throws Exception {
        String[] argList = new String[]{"-n", "file1.txt", "file1.png"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "file1.txt");
        File expectedNewFile = new File(tempDir, "file1.png");

        assertTrue(!expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(FILE1_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithoutFlagMoveOneFileIntoFolder_MovedIntoFolder() throws Exception {
        String[] argList = new String[]{"file1.txt", "subfolder1/"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "file1.txt");
        File expectedNewFile = new File(tempDir, "subfolder1/file1.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(FILE1_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithoutFlagMoveOneSubFileIntoFolder_MovedIntoFolder() throws Exception {
        String[] argList = new String[]{"subfolder1/file2.txt", "subfolder2"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder1/file2.txt");
        File expectedNewFile = new File(tempDir, "subfolder2/file2.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(SUBFILE2_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithoutFlagMoveOneSubFileIntoSubSFolder_MovedIntoSubFolder() throws Exception {
        String[] argList = new String[]{"subfolder1/file2.txt", "subfolder2/subsubfolder2/"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder1/file2.txt");
        File expectedNewFile = new File(tempDir, "subfolder2/subsubfolder2/file2.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(SUBFILE2_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithoutFlagMoveOneAbsolutePathFileIntoSubFolder_MovedIntoSubFolder() throws Exception {
        String[] argList = new String[]{tempDir.getAbsolutePath() + StringUtils.CHAR_FILE_SEP + "subfolder1/file2.txt",
                tempDir.getAbsolutePath() + StringUtils.CHAR_FILE_SEP + "subfolder2/subsubfolder2/"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder1/file2.txt");
        File expectedNewFile = new File(tempDir, "subfolder2/subsubfolder2/file2.txt");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        List<String> expectedNewFileContents = Files.readAllLines(expectedNewFile.toPath());
        assertEquals(SUBFILE2_CONTENTS, expectedNewFileContents.get(0));
    }

    @Test
    public void run_WithoutFlagsMoveOneFolderIntoFolder_MovedIntoFolder() throws Exception {
        String[] argList = new String[]{"subfolder2", "subfolder1"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile = new File(tempDir, "subfolder2");
        File expectedNewFile = new File(tempDir, "subfolder1/subfolder2");

        assertFalse(expectedRemovedFile.exists());
        assertTrue(expectedNewFile.exists());
        assertTrue(Files.isDirectory(expectedNewFile.toPath()));
        File[] subFiles = expectedNewFile.listFiles();
        assertEquals(1, subFiles.length);
        assertEquals("subsubfolder2", subFiles[0].getName());
    }

    @Test
    public void run_WithoutFlagsMoveMultipleFilesIntoFolder_MovedAllIntoFolder() throws Exception {
        String[] argList = new String[]{"file1.txt", "subfolder2", "subfolder1"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemainingSubFile2 = new File(tempDir, "subfolder1/file2.txt");
        File expectedRemovedFile1 = new File(tempDir, "file1.txt");
        File expectedRemovedSubFolder2 = new File(tempDir, "subfolder2");
        File expectedNewFile1 = new File(tempDir, "subfolder1/file1.txt");
        File expectedNewSubFolder2 = new File(tempDir, "subfolder1/subfolder2");

        assertTrue(expectedRemainingSubFile2.exists());
        assertFalse(expectedRemovedFile1.exists());
        assertFalse(expectedRemovedSubFolder2.exists());
        assertTrue(expectedNewFile1.exists());
        List<String> expectedNewFile1Contents = Files.readAllLines(expectedNewFile1.toPath());
        assertEquals(FILE1_CONTENTS, expectedNewFile1Contents.get(0));
        assertTrue(expectedNewSubFolder2.exists());
        assertTrue(Files.isDirectory(expectedNewSubFolder2.toPath()));
        File[] subFiles = expectedNewSubFolder2.listFiles();
        assertEquals(1, subFiles.length);
        assertEquals("subsubfolder2", subFiles[0].getName());
    }

    @Test
    public void run_WithoutFlagsMoveFileWithSameNameIntoFolder_MovedIntoFolderWithOverriding() throws Exception {
        String[] argList = new String[]{"file1.txt", "file2.txt", "subfolder2", "subfolder1"};
        mvApplication.run(argList, System.in, System.out);

        File expectedRemovedFile1 = new File(tempDir, "file1.txt");
        File expectedRemovedFile2 = new File(tempDir, "file2.txt");
        File expectedRemovedSubFolder2 = new File(tempDir, "subfolder2");
        File expectedNewFile1 = new File(tempDir, "subfolder1/file1.txt");
        File expectedNewFile2 = new File(tempDir, "subfolder1/file2.txt");
        File expectedNewSubFolder2 = new File(tempDir, "subfolder1/subfolder2");

        assertFalse(expectedRemovedFile1.exists());
        assertFalse(expectedRemovedFile2.exists());
        assertFalse(expectedRemovedSubFolder2.exists());
        assertTrue(expectedNewFile1.exists());
        List<String> expectedNewFile1Contents = Files.readAllLines(expectedNewFile1.toPath());
        assertEquals(FILE1_CONTENTS, expectedNewFile1Contents.get(0));
        assertTrue(expectedNewFile2.exists());
        List<String> expectedNewFile2Contents = Files.readAllLines(expectedNewFile2.toPath());
        assertEquals(FILE2_CONTENTS, expectedNewFile2Contents.get(0)); //override with file2.txt contents
        assertTrue(expectedNewSubFolder2.exists());
        assertTrue(Files.isDirectory(expectedNewSubFolder2.toPath()));
        File[] subFiles = expectedNewSubFolder2.listFiles();
        assertEquals(1, subFiles.length);
        assertEquals("subsubfolder2", subFiles[0].getName());
    }

}