package sg.edu.nus.comp.cs4218.impl.hackathon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.app.LsApplication;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

class LsApplicationFixesTest {

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

}