package sg.edu.nus.comp.cs4218.impl.hackathon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_ARG;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.app.MvApplication;

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

    static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws IOException {
        outputStream = new ByteArrayOutputStream();
        mvApplication = new MvApplication();
        Environment.currentDirectory = tempDir.getAbsolutePath();

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
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @Test
    public void run_invalidNumOfArgs_ThrowsException() {
        String[] argList = new String[]{"-n", "file2.txt"};
        Throwable invalidNumOfArgs = assertThrows(MvException.class, () -> mvApplication.run(argList, System.in, outputStream));
        assertEquals(new MvException(ERR_MISSING_ARG).getMessage(), invalidNumOfArgs.getMessage());
    }

}