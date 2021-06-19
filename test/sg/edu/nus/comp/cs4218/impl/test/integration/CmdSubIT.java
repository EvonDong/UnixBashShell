package integration;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CmdSubIT {
    private final Shell shell = new ShellImpl();
    private ByteArrayOutputStream outputStream;
    private static final String CWD = Environment.currentDirectory;

    @BeforeAll
    static void setUpOnce() {
        Environment.currentDirectory = CWD + CHAR_FILE_SEP + "testAssets" + CHAR_FILE_SEP
                + "integration";
    }

    @AfterAll
    static void tearDownOnce() {
        Environment.currentDirectory = CWD;
    }

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
    }

    // Negative scenarios
    @Test
    void cmdEchoSubCatLs_FileDoesNotExist_ThrowsException() {
        CatException actual = assertThrows(CatException.class, () -> {
            shell.parseAndEvaluate("echo `cat noexist.txt` `ls`", outputStream);
        });
        CatException expected = new CatException("Could not read file");
        assertEquals(expected.getMessage(), actual.getMessage());
    }

}
