package sg.edu.nus.comp.cs4218.impl.hackathon;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.impl.app.LsApplication;

class LsApplicationHackathonTest {

    private static final String TEST_DIRECTORY = "LS_TEST_DIRECTORY";

    private static final String CWD = Environment.currentDirectory;
    private final LsInterface lsApp = new LsApplication();

    private static String folder1Dir = "FOLDER_1";
    private static String subFolder1Dir = "SUB_FOLDER_1";
    private static String subSubFolder1Dir = "SUB_SUB_FOLDER_1";
    private static String folder2Dir = "FOLDER_2";
    private static String subFolder2Dir = "SUB_FOLDER_2";
    private static String folder3Dir = "FOLDER_3";
    private static String testTxtFile = "test.txt";
    private static String test1TxtFile = "test1.txt";
    private static String test2TxtFile = "test2.txt";
    private static String test3TxtFile = "test3.txt";
    private static String aExtTxtFile = "z.d.a";
    private static String bExtTxtFile = "y.b";
    private static String cExtTxtFile = "x.c";


    /* DIRECTORY LAYOUT
    FOLDER_1
        SUB_FOLDER_1
            SUB_SUB_FOLDER_1
                test.txt
        test.txt
    FOLDER_2
        SUB_FOLDER_2
            test.txt
    FOLDER_3
        test.txt
    test1.txt
    test2.txt
    test3.txt
    z.a
    y.b
    x.c
    .hiddenfile.txt
    .HIDDEN_FOLDER
        test.txt
    */

    private static void createFileAndDirectory(String dir) {
        try {
            File file = new File(dir);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            // This should not happen. Test cases will fail if this error occurs.
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void setUpOnce() {
        Environment.currentDirectory += CHAR_FILE_SEP + TEST_DIRECTORY;

        try {
            String dir = Environment.currentDirectory + CHAR_FILE_SEP + folder1Dir + CHAR_FILE_SEP + subFolder1Dir + CHAR_FILE_SEP + subSubFolder1Dir + CHAR_FILE_SEP + testTxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + folder1Dir + CHAR_FILE_SEP + testTxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + folder2Dir + CHAR_FILE_SEP + subFolder2Dir + CHAR_FILE_SEP + testTxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + folder3Dir + CHAR_FILE_SEP + testTxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + test1TxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + test2TxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + test3TxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + aExtTxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + bExtTxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + cExtTxtFile;
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + ".hiddenfile.txt";
            createFileAndDirectory(dir);
            dir = Environment.currentDirectory + CHAR_FILE_SEP + ".HIDDEN_FOLDER" + CHAR_FILE_SEP + testTxtFile;
            createFileAndDirectory(dir);
            Path hiddenFile = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + ".hiddenfile.txt");
            Path hiddenFolder = Paths.get(Environment.currentDirectory + CHAR_FILE_SEP + ".HIDDEN_FOLDER");
            if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
                Files.setAttribute(hiddenFile, "dos:hidden", true);
                Files.setAttribute(hiddenFolder, "dos:hidden", true);
            }

        } catch (IOException e) {
            // This should not happen. Test cases will fail if this error occurs.
            e.printStackTrace();
        }

    }

    @AfterAll
    static void tearDownOnce() {
        try {
            Files.walk(Paths.get(Environment.currentDirectory))
                    .map(Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .forEach(File::delete);
        } catch (IOException e) {
            // If this error occurs, the test folder is not deleted properly
            e.printStackTrace();
        } finally {
            Environment.currentDirectory = CWD;
        }
    }

    @Test
    void listFolderContent_RecursiveOnlyOptionsNoFolders_ReturnsCurrentDirectory() {
        String[] directories = {};
        String expectedResult =
                "." + CHAR_FILE_SEP + ":" + STRING_NEWLINE
                + folder1Dir + STRING_NEWLINE
                + folder2Dir + STRING_NEWLINE
                + folder3Dir + STRING_NEWLINE
                + test1TxtFile + STRING_NEWLINE
                + test2TxtFile + STRING_NEWLINE
                + test3TxtFile + STRING_NEWLINE
                + cExtTxtFile + STRING_NEWLINE
                + bExtTxtFile + STRING_NEWLINE
                + aExtTxtFile
                + STRING_NEWLINE + STRING_NEWLINE // Separator for new folder
                + folder1Dir + ":" + STRING_NEWLINE
                + subFolder1Dir + STRING_NEWLINE
                + testTxtFile
                + STRING_NEWLINE + STRING_NEWLINE // Separator for new folder
                + folder1Dir + CHAR_FILE_SEP + subFolder1Dir + ":" + STRING_NEWLINE
                + subSubFolder1Dir
                + STRING_NEWLINE + STRING_NEWLINE // Separator for new folder
                + folder1Dir + CHAR_FILE_SEP + subFolder1Dir + CHAR_FILE_SEP + subSubFolder1Dir + ":" + STRING_NEWLINE
                + testTxtFile
                + STRING_NEWLINE + STRING_NEWLINE // Separator for new folder
                + folder2Dir +":" + STRING_NEWLINE
                + subFolder2Dir
                + STRING_NEWLINE + STRING_NEWLINE // Separator for new folder
                + folder2Dir + CHAR_FILE_SEP + subFolder2Dir + ":" + STRING_NEWLINE
                + testTxtFile
                + STRING_NEWLINE + STRING_NEWLINE // Separator for new folder
                + folder3Dir + ":" + STRING_NEWLINE
                + testTxtFile
                ;


        assertDoesNotThrow(() -> {
            assertEquals(expectedResult, lsApp.listFolderContent(false, true, false, directories));
        });
    }
}