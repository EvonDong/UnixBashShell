package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_WRITE_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

class LsApplicationTest {

    private ByteArrayOutputStream outRes;
    private LsApplication lsApp;
    private final String CURR_DIR = Environment.currentDirectory;
    private static final String TEST2_FILE = "test2.txt";
    private static final String TEST3_FILE = "test3.xml";
    private static final String TEST1_FILE = "TEST1.md";
    private static final String INNER_DIR_1 = "dir1";
    private static final String INNER_DIR_2 = "dir2";
    private static final String INNER_DIR1_FILE = "testDir1.txt";
    private static final String INNER_DIR2_FILE = "testDir2.txt";
    private static final String INNER_DIR = "dir";
    private static final String TWO_INNER_DIR = "inner2Dir";
    private static final String THREE_INNER_FILE = "testInner.txt";

    @BeforeEach
    void setUp() {
        lsApp = new LsApplication();
        outRes = new ByteArrayOutputStream();
        Environment.currentDirectory = CURR_DIR.concat(StringUtils.fileSeparator() + "testFolders" + CHAR_FILE_SEP + "app" + CHAR_FILE_SEP + "lsTestFolder");
    }

    @AfterEach
    void tearDown() throws IOException {
        Environment.currentDirectory = CURR_DIR;
        outRes.close();
    }

    @Test
    public void run_NullArgs_ExceptionThrown() {
        Exception err = assertThrows(LsException.class, () -> lsApp.run(null, System.in, outRes));
        assertTrue(err.getMessage().contains(ERR_NULL_ARGS));
    }

    @Test
    public void run_NullOutStream_ExceptionThrown() {
        String[] args = {};
        Exception err = assertThrows(LsException.class, () -> lsApp.run(args, System.in, null));
        assertTrue(err.getMessage().contains(ERR_NO_OSTREAM));
    }

    @Test
    public void run_InvalidOutStream_ExceptionThrown() throws IOException {
        String[] args = {};
        OutputStream out = null;
        try {
            out = new PipedOutputStream();
            lsApp.run(args, System.in, out);
        } catch (LsException e) {
            assert(e.getMessage().contains(ERR_WRITE_STREAM));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Test
    public void run_InvalidOption_ExceptionThrown() {
        String[] args = {"-e"};
        Exception err = assertThrows(LsException.class, () -> lsApp.run(args, System.in, outRes));
        assertTrue(err.getMessage().contains("illegal option"));
    }

    @Test
    public void run_DirArg_ExceptionThrown() throws LsException {
        String[] args = {INNER_DIR_1};
        String expectedRes = INNER_DIR1_FILE;
        lsApp.run(args, System.in, outRes);
        String actualRes = outRes.toString();
        assertTrue(actualRes.contains(expectedRes));
    }

    @Test
    public void run_FileArg_ExceptionThrown() throws LsException {
        String[] args = {TEST2_FILE};
        String expectedRes = TEST2_FILE;
        lsApp.run(args, System.in, outRes);
        String actualRes = outRes.toString();
        assertTrue(actualRes.contains(expectedRes));
    }

    @Test
    public void listFolderContent_NoArg_ReturnTrue() throws LsException {
        String expectedRes = INNER_DIR + System.lineSeparator() + INNER_DIR_1 + System.lineSeparator() + INNER_DIR_2  +
                System.lineSeparator() + TEST1_FILE + System.lineSeparator() + TEST2_FILE + System.lineSeparator() + TEST3_FILE;
        String actualRes = lsApp.listFolderContent(false, false, false);
        assertTrue(actualRes.contains(INNER_DIR) && actualRes.contains(INNER_DIR_1) && actualRes.contains(INNER_DIR_2)
                && actualRes.contains(TEST1_FILE) && actualRes.contains(TEST2_FILE) && actualRes.contains(TEST3_FILE));
    }

    @Test
    public void run_InvalidArg_ExceptionThrown() throws LsException {
        String[] args = {"notExist.txt"};
        String expectedRes = "ls: cannot access 'notExist.txt': No such file or directory";
        lsApp.run(args, System.in, outRes);
        String actualRes = outRes.toString();
        assertTrue(actualRes.contains(expectedRes));
    }

    @Test
    public void listFolderContent_OnlyFolders_ReturnTrue() throws LsException {
        String expectedRes = INNER_DIR + ":" + System.lineSeparator() + TWO_INNER_DIR;
        String actualRes = lsApp.listFolderContent(true, false, false, INNER_DIR);
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void listFolderContent_RecursionOption_ReturnTrue() throws LsException {
        String expectedRes = (INNER_DIR + ":" + System.lineSeparator() + TWO_INNER_DIR + System.lineSeparator() +
                System.lineSeparator()) + (Paths.get(INNER_DIR + StringUtils.fileSeparator() +
                TWO_INNER_DIR) + ":" + System.lineSeparator() + THREE_INNER_FILE);
        String actualRes = lsApp.listFolderContent(false, true, false, INNER_DIR);
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void listFolderContent_SortOption_ReturnTrue() throws LsException {
        String expectedRes = INNER_DIR + System.lineSeparator() + INNER_DIR_1 + System.lineSeparator() + INNER_DIR_2
                + System.lineSeparator() + TEST1_FILE + System.lineSeparator() +
                TEST2_FILE + System.lineSeparator() + TEST3_FILE;
        String actualRes = lsApp.listFolderContent(false, false, true);
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void run_SortOptionWithFileArg_ExceptionThrown() throws LsException {
        String[] args = {"-X", TEST3_FILE, TEST2_FILE, TEST1_FILE};
        String expectedRes = TEST1_FILE + System.lineSeparator() + TEST2_FILE + System.lineSeparator() + TEST3_FILE + STRING_NEWLINE;
        lsApp.run(args, System.in, outRes);
        String actualRes = outRes.toString();
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void run_SortOptionWithFileDirArg_ExceptionThrown() throws LsException {
        String[] args = {"-X", TEST3_FILE, TEST2_FILE, TEST1_FILE, INNER_DIR_1};
        String expectedRes = INNER_DIR_1 + System.lineSeparator() + TEST1_FILE + System.lineSeparator() + TEST2_FILE
                + System.lineSeparator() + TEST3_FILE + STRING_NEWLINE + STRING_NEWLINE + INNER_DIR_1 + ":" + System.lineSeparator()
                + INNER_DIR1_FILE + System.lineSeparator();
        lsApp.run(args, System.in, outRes);
        String actualRes = outRes.toString();
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void run_SortRecursiveOptionWithFileDirArg_ExceptionThrown() throws LsException {
        String[] args = {"-X", "-R", TEST3_FILE, TEST2_FILE, TEST1_FILE, INNER_DIR_1};
        String expectedRes = INNER_DIR_1 + System.lineSeparator() + TEST1_FILE + System.lineSeparator() + TEST2_FILE
                + System.lineSeparator() + TEST3_FILE + STRING_NEWLINE + STRING_NEWLINE + INNER_DIR_1 + ":" + System.lineSeparator()
                + INNER_DIR1_FILE + System.lineSeparator();
        lsApp.run(args, System.in, outRes);
        String actualRes = outRes.toString();
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void run_RecursionOption_ReturnTrue() throws LsException {
        String expectedRes = (INNER_DIR + ":" + System.lineSeparator() + TWO_INNER_DIR + System.lineSeparator() +
                System.lineSeparator()) + (Paths.get(INNER_DIR + StringUtils.fileSeparator() +
                TWO_INNER_DIR) + ":" + System.lineSeparator() + THREE_INNER_FILE) + STRING_NEWLINE;
        String[] args = {"-R", INNER_DIR};
        lsApp.run(args, System.in, outRes);
        String actualRes = outRes.toString();
        assertEquals(expectedRes, actualRes);
    }

}