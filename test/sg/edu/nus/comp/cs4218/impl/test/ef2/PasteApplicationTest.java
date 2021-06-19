package sg.edu.nus.comp.cs4218.impl.test.ef2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.impl.app.PasteApplication;

/**
 * <p>
 * NEGATIVE TEST CASES
 * run with Null args
 * run with Null stdout
 * run with null stdin
 * <p>
 * mergeStdin with null stdin
 * mergeFile with dir instead of file
 * mergeFile with non existent file
 * mergeFileAndStdin with null stdin
 * mergeFileAndStdin with dir instead of file
 * mergeFileAndStdin with non existent file
 * <p>
 * POSITIVE TEST CASES
 * mergeStdin, not serial with valid stdin
 * mergeStdin, serial with valid stdin
 * mergeFile, one file not serial
 * mergeFile, two file not Serial
 * mergeFile, three file serial
 * mergeFileAndStdin, one file 1 stdin serial
 * mergeFileAndStdin, two file 1 stdin not serial
 * mergeFileAndStdin, 1 file 2 stdin not serial
 * <p>
 * run with only file
 * run with only stdin
 * run with both file and stdin serial
 * run with both file and stdin not serial
 * <p>
 * PMD VIOLATION
 * - Ignored for duplicate use of string. (AvoidDuplicateLiterals)
 */
class PasteApplicationHackTest {

    // TODO: Declaration of pasteApp when implemented.
    PasteApplication pasteApp = new PasteApplication();

    private static final String CWD = Environment.currentDirectory;
    private static final String PASTE_TEST_DIR = Environment.currentDirectory + CHAR_FILE_SEP + "testAssets" + CHAR_FILE_SEP + "paste";

    private static final String FOLDER_1 = "folder1";
    private static final String TEXT_FILE_1 = "test1.txt";
    private static final String TEXT_FILE_2 = "test2.txt";
    private static final String TEXT_FILE_3 = "test3.txt";

    @BeforeAll
    static void setUpOnce() {
        Environment.currentDirectory = PASTE_TEST_DIR;
    }

    @AfterAll
    static void tearDownOnce() {
        Environment.currentDirectory = CWD;
    }

    @Test
    void mergeFile_OneValidFileAndNotSerial_ReturnValidOutput() throws PasteException {
        String filename = TEXT_FILE_1;
//        assertDoesNotThrow(() -> {
            String expectedResult = "line1" + STRING_NEWLINE
                    + "line2" + STRING_NEWLINE
                    + "line3" + STRING_NEWLINE
                    + "line4";

        pasteApp.mergeFile(false, filename);
            Exception err = assertThrows(PasteException.class, () -> pasteApp.mergeFile(false, filename));

//        });
        assertEquals(expectedResult, err.toString());
    }

}

