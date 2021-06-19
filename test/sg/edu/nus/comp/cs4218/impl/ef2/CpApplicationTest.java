package sg.edu.nus.comp.cs4218.impl.ef2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createAndWriteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createDirectory;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteDirectory;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.isDirectoryEqual;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.isFilesEqual;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.writeFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.BrokenOutputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CpException;
import sg.edu.nus.comp.cs4218.impl.app.CpApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;


class CpApplicationTest {

    CpApplication cpApplication;
    public static final String TEMP = "temp-cp";

    // source files
    private static final String FILE_ONE = "fIlE 1.txt";
    private static final String FILE_ONE_CONT = "I l0VE C$4z!8";
    private static final String FILE_TWO = "FILE-2.txt";
    private static final String FILE_TWO_CONT = "Hello, " + System.lineSeparator() + "~!@#$%^&*()_+[];':";
    private static final String FILE_THREE = "_file-3.txt";
    private static final String FILE_THREE_CONT = System.lineSeparator() + "the Tester testing\thello";
    private static final String FILE_FOUR = "file #4";
    private static final String FILE_FOUR_CONT = "a" + System.lineSeparator() + "bigApple" + System.lineSeparator();
    public static final String FILE_NON_EXISTING = "eqjfl";

    // source directories
    private static final String DIRECTORY_ONE = "my documents";
    private static final String DIRECTORY_TWO = "my-cs4218";

    // destination directories
    private static final String DEST_DIR_ONE = "destination-directory-1"; // EMPTY directory.
    private static final String DEST_DIR_TWO = "destination-directory-2"; // Non-existing.

    // destination files
    private static final String DEST_FILE_1 = DEST_DIR_ONE + "/destination-file-1"; // Non-existing.
    private static final String DEST_FILE_2 = "destination file 2.txt"; // Non-existing.

    // TDD
    private static final String SRC_FILE_A = "srcA_file.txt";
    private static final String SRC_FILE_B = "srcB_file.txt";
    private static final String DEST_FILE = "dest_file.txt";
    private static final String SRC_DIR_A = "srcA_dir";
    private static final String SRC_DIR_B = "srcB_dir";
    private static final String DEST_DIR = "dest_dir";
    private static final String SRC_CONTENT = "This is the source file.";


    @BeforeAll
    static void initialize() throws IOException {
        createFile(FILE_ONE);
        createFile(FILE_THREE);
        createDirectory(DIRECTORY_ONE + "/" + DIRECTORY_TWO);
        createFile(DIRECTORY_ONE + "/" + FILE_TWO);
        createFile(DIRECTORY_ONE + "/" + DIRECTORY_TWO + "/" + FILE_FOUR);
        writeFile(FILE_ONE, FILE_ONE_CONT);
        writeFile(FILE_THREE, FILE_THREE_CONT);
        writeFile(DIRECTORY_ONE + "/" + FILE_TWO, FILE_TWO_CONT);
        writeFile(DIRECTORY_ONE + "/" + DIRECTORY_TWO + "/" + FILE_FOUR, FILE_FOUR_CONT);
        createDirectory(DEST_DIR_ONE);
    }

    @AfterAll
    static void terminate() throws IOException {
        deleteFile(FILE_ONE);
        deleteFile(FILE_THREE);
        deleteDirectory(DIRECTORY_ONE); // recursive deletion
    }

    @BeforeEach
    void setup() {
        cpApplication = new CpApplication();
    }

    @AfterEach
    void tearDown() {
        try {
            deleteFile(DEST_FILE_1);
            deleteFile(DEST_FILE_2);
            deleteDirectory(DEST_DIR_ONE);
            createDirectory(DEST_DIR_ONE);
            deleteDirectory(DEST_DIR_TWO);
        } catch (IOException e) {
            // expected for certain test cases
        }
    }

    private String[] toArgs(String flag, String... files) {
        List<String> args = new ArrayList<>();
        if (!flag.isEmpty()) {
            args.add("-" + flag);
        }
        for (String file : files) {
            args.add(Paths.get(file).toString());
        }
        return args.toArray(new String[0]);
    }

    @Test
    void testRun_DirectoryDestFile_ExceptionNotThrown() {
        final OutputStream stdout = new ByteArrayOutputStream();
        String[] args = { "-r", FILE_ONE, DIRECTORY_ONE};
        assertDoesNotThrow(() -> cpApplication.run(args, System.in, stdout));
    }

    @Test
    void testRun_DirectorySrcFileRecursive_ExceptionNotThrown() {
        final OutputStream stdout = new ByteArrayOutputStream();
        String[] args = { "-r", DIRECTORY_ONE, DEST_FILE_1};
        assertDoesNotThrow(() -> cpApplication.run(args, System.in, stdout));
    }

    @Test
    void testRun_DirectorySrcDestFileNotRecursive_ExceptionNotThrown() {
        assertDoesNotThrow(() -> cpApplication.cpFilesToFolder(false, DIRECTORY_ONE, DIRECTORY_ONE));
    }

    @Test
    void testRun_DirectorySrcDestFileRecursive_ExceptionNotThrown() {
        assertDoesNotThrow(() -> cpApplication.cpFilesToFolder(true, DIRECTORY_ONE, DIRECTORY_ONE));
    }

    @Test
    void testRun_NotExistFile_ExceptionThrown() {
        final OutputStream stdout = new ByteArrayOutputStream();
        String[] args = { "-r", "NotExist.txt", DEST_FILE_1};
        assertThrows(AbstractApplicationException.class, () -> cpApplication.run(args, System.in, stdout));
    }

    @Test
    void cpSrcFileToDestFile_SameSrcDestFile_ThrowsException() throws Exception {
        createFile(FILE_TWO);
        writeFile(FILE_TWO, FILE_TWO_CONT); // create existing file
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(false, FILE_TWO, FILE_TWO));
        deleteFile(FILE_TWO);
    }

    @Test
    void testCpSrcFileToDestFile_NonExistFile_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(false, "srcFile", "destFile"));
    }

    @Test
    void testRun_FileToFile_ExceptionNotThrown(){
        final OutputStream stdout = new ByteArrayOutputStream();
        String[] args = { "-r", FILE_ONE, DEST_FILE_1};
        assertDoesNotThrow(() -> cpApplication.run(args, System.in, stdout));
    }

    @Test
    void testRun_EmptyStdin_ExceptionThrown() throws Exception {
        final InputStream stdin = InputStream.nullInputStream();
        final OutputStream stdout = new ByteArrayOutputStream();
        String[] args = { "-r", FILE_ONE, DEST_FILE_1};
        cpApplication.run(args, stdin, stdout);
    }

    @Test
    void testRun_BrokenStdout_ExceptionThrown() {
        final InputStream stdin = new ByteArrayInputStream("content".getBytes());
        final OutputStream stdout = new BrokenOutputStream();
        String[] args = { "-r", FILE_ONE, DEST_FILE_1};
        assertThrows(AbstractApplicationException.class, () -> cpApplication.run(args, stdin, stdout));
    }

    @Test
    public void run_NullArgs_ExceptionThrown() {
        assertThrows(Exception.class, () -> cpApplication.run(null, System.in, System.out));
    }

    @Test
    public void run_nullStdout_ExceptionThrown() throws IOException {
        String[] args = { "-r", FILE_ONE, FILE_NON_EXISTING};
        assertThrows(Exception.class, () -> cpApplication.run(args, System.in, null));
        Files.deleteIfExists(Paths.get(FILE_NON_EXISTING));
    }

    @Test
    public void run_InvalidOption_ExceptionThrown() throws IOException {
        String[] args = { "-Z", FILE_ONE, FILE_NON_EXISTING};
        assertThrows(Exception.class, () -> cpApplication.run(args, System.in, System.out));
        Files.deleteIfExists(Paths.get(FILE_NON_EXISTING));
    }

    @Test
    void cpSrcFileToDestFile_NullSrcFile_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(false, null, DEST_FILE_1));
    }

    @Test
    void cpSrcFileToDestFile_NullDestFile_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(true, FILE_ONE, null));
    }

    @Test
    void cpSrcFileToDestFile_EmptySrcFileName_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(false, "", DEST_FILE_1));
    }

    @Test
    void cpSrcFileToDestFile_EmptyDestFileName_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(true, FILE_THREE, ""));
    }

    @Test
    void cpSrcFileToDestFile_NonExistingSrcFileOrSrcDirectory_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(false, FILE_NON_EXISTING, DEST_FILE_1));
    }

    @Test
    void cpSrcFileToDestFile_SrcIsFileDestIsNonExistingFileNoRecursive_FileCopied() throws Exception {
        cpApplication.cpSrcFileToDestFile(false, FILE_ONE, DEST_FILE_1);
        assertTrue(isFilesEqual(FILE_ONE, DEST_FILE_1));
    }

    @Test
    void cpSrcFileToDestFile_SrcIsFileDestIsNonExistingFileIsRecursive_FileCopied() throws Exception {
        cpApplication.cpSrcFileToDestFile(true, FILE_ONE, DEST_FILE_2);
        assertTrue(isFilesEqual(FILE_ONE, DEST_FILE_2));
    }

    @Test
    void cpSrcFileToDestFile_SrcIsFileDestIsNonExistingFileIsRecursiveUseAbsolutePath_FileCopied() throws Exception {
        String sourceFileAbsPath = IOUtils.resolveFilePath(FILE_ONE).toString();
        String destFileAbsPath = IOUtils.resolveFilePath(DEST_FILE_1).toString();
        cpApplication.cpSrcFileToDestFile(false, sourceFileAbsPath, destFileAbsPath);
        assertTrue(isFilesEqual(FILE_ONE, DEST_FILE_1));
    }

    @Test
    void cpSrcFileToDestFile_SrcIsFileDestIsExistingFile_FileCopiedAndExistingFileOverwritten() throws Exception {
        createFile(FILE_TWO);
        writeFile(FILE_TWO, FILE_TWO_CONT); // create existing file
        cpApplication.cpSrcFileToDestFile(false, FILE_ONE, FILE_TWO);
        assertTrue(isFilesEqual(FILE_ONE, FILE_TWO));
        deleteFile(FILE_TWO);
    }

    @Test
    void cpSrcFileToDestFile_SrcIsDirDestIsNonExistingDirNoRecursive_ThrowsExceptionBecauseNoRecursive() {
        assertThrows(Exception.class, () -> cpApplication.cpSrcFileToDestFile(false, DIRECTORY_ONE, DEST_DIR_TWO));
    }

    @Test
    void cpSrcFileToDestFile_SrcIsDirDestIsNonExistingDirIsRecursive_DirCopied() throws Exception {
        cpApplication.cpSrcFileToDestFile(true, DIRECTORY_ONE, DEST_DIR_TWO);
        assertTrue(isDirectoryEqual(DIRECTORY_ONE, DEST_DIR_TWO));
    }

    @Test
    void cpSrcFileToDestFile_SrcIsDirDestIsNonExistingDirIsRecursiveUseAbsolutePath_DirCopied() throws Exception {
        String sourceDirAbsPath = IOUtils.resolveFilePath(DIRECTORY_ONE).toString();
        String destDirAbsPath = IOUtils.resolveFilePath(DEST_DIR_TWO).toString();
        cpApplication.cpSrcFileToDestFile(true, sourceDirAbsPath, destDirAbsPath);
        assertTrue(isDirectoryEqual(DIRECTORY_ONE, DEST_DIR_TWO));
    }

    @Test
    // special test case
    void cpSrcFileToDestFile_SrcIsDirDestIsExistingDirIsRecursive_DirCopiedIntoExistingDir() throws Exception {
        cpApplication.cpSrcFileToDestFile(true, DIRECTORY_ONE, DEST_DIR_ONE);
        assertTrue(Files.exists(Paths.get(DEST_DIR_ONE, DIRECTORY_ONE)));
        assertTrue(isDirectoryEqual(DIRECTORY_ONE, DEST_DIR_ONE + "/" + DIRECTORY_ONE));
    }

    @Test
    void cpFilesToFolder_NullSrcFile_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.
                cpFilesToFolder(false, DEST_DIR_ONE, null));
    }

    @Test
    void cpFilesToFolder_NullDestFolder_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpFilesToFolder(false, null, FILE_ONE));
    }

    @Test
    void cpFilesToFolder_EmptySrcFileName_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpFilesToFolder(false, DEST_DIR_ONE, ""));
    }

    @Test
    void cpFilesToFolder_EmptyDestFolderName_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpFilesToFolder(false, "", FILE_THREE));
    }

    @Test
    void cpFilesToFolder_NonExistingDestFolder_ThrowsException() {
        assertThrows(Exception.class, () -> cpApplication.cpFilesToFolder(false, DEST_DIR_TWO, FILE_THREE));
    }

    @Test
    void cpFilesToFolder_OneSrcFileNoRecursive_SrcFileCopiedIntoDestFolder() throws Exception {
        cpApplication.cpFilesToFolder(false, DEST_DIR_ONE, FILE_ONE);
        assertTrue(Files.exists(Paths.get(DEST_DIR_ONE, FILE_ONE)));
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_ONE, FILE_ONE));
    }

    @Test
    void cpFilesToFolder_OneSrcFileIsRecursive_SrcFileCopiedIntoDestFolder() throws Exception {
        cpApplication.cpFilesToFolder(true, DEST_DIR_ONE, FILE_THREE);
        assertTrue(Files.exists(Paths.get(DEST_DIR_ONE, FILE_THREE)));
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_THREE, FILE_THREE));
    }

    @Test
    void cpFilesToFolder_ManySrcFilesNoRecursive_ManySrcFilesCopiedIntoDestFolder() throws Exception {
        String[] srcFiles = {FILE_ONE, DIRECTORY_ONE + "/" + FILE_TWO, FILE_THREE};
        cpApplication.cpFilesToFolder(false, DEST_DIR_ONE, srcFiles);
        for (String file : new String[]{FILE_ONE, FILE_TWO, FILE_THREE}) {
            assertTrue(Files.exists(Paths.get(DEST_DIR_ONE, file)));
        }
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_ONE, FILE_ONE));
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_TWO, DIRECTORY_ONE + "/" + FILE_TWO));
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_THREE, FILE_THREE));
    }

    @Test
    void cpFilesToFolder_ManySrcFilesIsRecursive_ManySrcFilesCopiedIntoDestFolder() throws Exception {
        String[] srcFiles = {FILE_ONE, DIRECTORY_ONE + "/" + DIRECTORY_TWO + "/" + FILE_FOUR, FILE_THREE};
        cpApplication.cpFilesToFolder(true, DEST_DIR_ONE, srcFiles);
        for (String file : new String[]{FILE_ONE, FILE_FOUR, FILE_THREE}) {
            assertTrue(Files.exists(Paths.get(DEST_DIR_ONE, file)));
        }
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_ONE, FILE_ONE));
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_FOUR, DIRECTORY_ONE + "/" + DIRECTORY_TWO + "/" + FILE_FOUR));
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_THREE, FILE_THREE));
    }

    @Test
    void cpFilesToFolder_ManySrcFilesButAnotherFileInDestFolderHasSameNameAsOneSrcFile_ManySrcFilesCopiedIntoDestFolderAndExistingFileWithSameNameOverwritten()
            throws Exception {
        createFile(DEST_DIR_ONE + "/" + FILE_THREE);
        writeFile(DEST_DIR_ONE + "/" + FILE_THREE, FILE_FOUR_CONT);
        String[] srcFiles = {FILE_ONE, DIRECTORY_ONE + "/" + FILE_TWO, FILE_THREE};
        cpApplication.cpFilesToFolder(false, DEST_DIR_ONE, srcFiles);
        for (String file : new String[]{FILE_ONE, FILE_TWO, FILE_THREE}) {
            assertTrue(Files.exists(Paths.get(DEST_DIR_ONE, file)));
        }
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_ONE, FILE_ONE));
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_TWO, DIRECTORY_ONE + "/" + FILE_TWO));

        // Initially, the file "DEST_DIRECTORY_ONE/FILE_THREE" contains FILE_FOUR_CONTENT.
        // But "FILE_THREE" from current directory containing FILE_THREE_CONTENT is copied into DEST_DIRECTORY_ONE
        // Since "FILE_THREE" already exists in DEST_DIRECTORY_ONE, its original FILE_FOUR_CONTENT gets overwritten with
        // FILE_THREE_CONTENT. The statement below asserts this.
        assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + FILE_THREE, FILE_THREE));
    }

    @Test
    void cpFilesToFolder_ManySrcFilesIncludingNonExistingOnesIsRecursive_OnlyExistingSrcFilesCopiedIntoDestFolderAndReturnsErrorMessage()
            throws Exception {
        String[] srcFiles = {FILE_ONE, FILE_NON_EXISTING, FILE_THREE};
        String returnMessage = cpApplication.cpFilesToFolder(true, DEST_DIR_ONE, srcFiles);
        for (String file : new String[]{FILE_ONE, FILE_THREE}) {
            assertTrue(Files.exists(Paths.get(DEST_DIR_ONE, file)));
            assertTrue(isFilesEqual(DEST_DIR_ONE + "/" + file, file));
        }
        assertFalse(Files.exists(Paths.get(DEST_DIR_ONE, FILE_NON_EXISTING)));
        assertNotEquals("", returnMessage);
    }


    // ============================================ TDD Test Cases ===================================================

    @Test
    void run_EmptyFileToNonemptyFile_OverwritesDestWithEmpty() throws IOException, AbstractApplicationException {
        createFile(SRC_FILE_A);
        String destContent = "This file is not empty.";
        createAndWriteFile(DEST_FILE, destContent);
        new CpApplication().run(toArgs("", SRC_FILE_A, DEST_FILE), System.in, System.out);
        assertArrayEquals(("").getBytes(), Files.readAllBytes(Paths.get(DEST_FILE)));
        deleteFile(SRC_FILE_A);
        deleteFile(DEST_FILE);
    }

    @Test
    void run_NonemptyFileToEmptyFile_CopiesContentToDest() throws IOException, AbstractApplicationException {
        String srcContent = "This file is not empty.";
        createAndWriteFile(SRC_FILE_A, srcContent);
        createFile(DEST_FILE);
        new CpApplication().run(toArgs("", SRC_FILE_A, DEST_FILE), System.in, System.out);
        assertArrayEquals(srcContent.getBytes(), Files.readAllBytes(Paths.get(DEST_FILE)));
        deleteFile(SRC_FILE_A);
        deleteFile(DEST_FILE);
    }

    @Test
    void run_NonemptyFileToNonemptyFile_OverwritesDest() throws IOException, AbstractApplicationException {
        String srcContent = SRC_CONTENT;
        String destContent = "This is the destination file.";
        createAndWriteFile(SRC_FILE_A, srcContent);
        createAndWriteFile(DEST_FILE, destContent);
        new CpApplication().run(toArgs("", SRC_FILE_A, DEST_FILE), System.in, System.out);
        assertArrayEquals(srcContent.getBytes(), Files.readAllBytes(Paths.get(DEST_FILE)));
        deleteFile(SRC_FILE_A);
        deleteFile(DEST_FILE);
    }

    @Test
    void run_NonemptyFileToSameFile_Throws() throws IOException {
        String srcContent = "This is the same file.";
        createAndWriteFile(SRC_FILE_A, srcContent);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_FILE_A, SRC_FILE_A), System.in, System.out));
        deleteFile(SRC_FILE_A);
    }

    @Test
    void run_DirectoryToFile_Throws() throws IOException {
        createDirectory(SRC_DIR_A);
        String destContent = "This is the destination file.";
        createAndWriteFile(DEST_FILE, destContent);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_DIR_A, DEST_FILE), System.in, System.out));
        deleteDirectory(SRC_DIR_A);
        deleteFile(DEST_FILE);
    }

    @Test
    void run_NonexistentFileToFile_Throws() throws IOException {
        String destContent = "This is the destination file.";
        createAndWriteFile(DEST_FILE, destContent);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_FILE_A, DEST_FILE), System.in, System.out));
        deleteFile(DEST_FILE);
    }

    @Test
    void run_FileToNonexistentFile_CreatesNewDestFile() throws IOException, AbstractApplicationException {
        createAndWriteFile(SRC_FILE_A, SRC_CONTENT);
        new CpApplication().run(toArgs("", SRC_FILE_A, DEST_FILE), System.in, System.out);
        Path destPath = Paths.get(DEST_FILE);
        assertTrue(Files.exists(destPath));
        assertArrayEquals(SRC_CONTENT.getBytes(), Files.readAllBytes(destPath));
        deleteFile(SRC_FILE_A);
        deleteFile(DEST_FILE);
    }

    @Test
    void run_DirectoryToNonexistentFile_CreatesDirectoryWithDestNameWithSrcContent() throws IOException, AbstractApplicationException {
        String fileInSrcDirName = "file_in_src_dir.txt";
        createDirectory(SRC_DIR_A);
        String srcContent = "This is the file in the source directory.";
        createAndWriteFile(Paths.get(SRC_DIR_A, fileInSrcDirName).toString(), srcContent);
        new CpApplication().run(toArgs("r", SRC_DIR_A, DEST_DIR), System.in, System.out);

        Path destFile = Paths.get(DEST_DIR);
        assertTrue(Files.exists(destFile));
        assertTrue(Files.isDirectory(destFile));
        Path fileInDestDir = Paths.get(DEST_DIR, fileInSrcDirName);
        assertTrue(Files.exists(fileInDestDir));
        assertArrayEquals(srcContent.getBytes(), Files.readAllBytes(fileInDestDir));
        deleteDirectory(SRC_DIR_A);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_FileToEmptyDirectory_CopiesToDirectory() throws IOException, AbstractApplicationException {
        String srcContent = SRC_CONTENT;
        createAndWriteFile(SRC_FILE_A, srcContent);
        createDirectory(DEST_DIR);
        new CpApplication().run(toArgs("", SRC_FILE_A, DEST_DIR), System.in, System.out);
        Path destFile = Paths.get(DEST_DIR, SRC_FILE_A);
        assertTrue(Files.exists(destFile));
        assertArrayEquals(srcContent.getBytes(), Files.readAllBytes(destFile));
        deleteFile(SRC_FILE_A);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_FileToNonemptyDirectory_CopiesToDirectory() throws IOException, AbstractApplicationException {
        createAndWriteFile(SRC_FILE_A, SRC_CONTENT);
        String destName = DEST_DIR;
        createDirectory(destName);
        String destOrigName = "dest_orig_file.txt";
        createFile(Paths.get(destName, destOrigName).toString());
        new CpApplication().run(toArgs("", SRC_FILE_A, destName), System.in, System.out);
        assertTrue(Files.exists(Paths.get(destName, destOrigName)));
        Path destFile = Paths.get(destName, SRC_FILE_A);
        assertTrue(Files.exists(destFile));
        assertArrayEquals(SRC_CONTENT.getBytes(), Files.readAllBytes(destFile));
        deleteFile(SRC_FILE_A);
        deleteDirectory(destName);
    }

    @Test
    void run_DirectoryToDirectoryWithFlag_CopiesSrcDirectoryToDestDirectory() throws IOException, AbstractApplicationException {
        createDirectory(SRC_DIR_A);
        createDirectory(DEST_DIR);
        new CpApplication().run(toArgs("r", SRC_DIR_A, DEST_DIR), System.in, System.out);
        Path destFile = Paths.get(DEST_DIR, SRC_DIR_A);
        assertTrue(Files.exists(destFile));
        assertTrue(Files.isDirectory(destFile));
        deleteDirectory(SRC_DIR_A);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_DirectoryToDirectoryWithoutFlag_DoesNothing() throws IOException, AbstractApplicationException {
        createDirectory(SRC_DIR_A);
        createDirectory(DEST_DIR);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_DIR_A, DEST_DIR), System.in, System.out));
        deleteDirectory(SRC_DIR_A);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_DirectoryToSameDirectoryWithFlag_Throws() throws IOException {
        createDirectory(SRC_DIR_A);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("r", SRC_DIR_A, SRC_DIR_A), System.in,
                System.out));
        deleteDirectory(SRC_DIR_A);
    }

    @Test
    void run_NonexistentDirectoryToDirectoryWithFlag_Throws() throws IOException {
        String nonexistSrcName = "nonexistent_dir";
        createDirectory(DEST_DIR);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("r", nonexistSrcName, DEST_DIR), System.in, System.out));
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_FileToNonexistentDirectory_CreatesFileWithDestNameWithSrcContent() throws IOException, AbstractApplicationException {
        createAndWriteFile(SRC_FILE_A, SRC_CONTENT);
        String destName = DEST_DIR;
        new CpApplication().run(toArgs("", SRC_FILE_A, destName), System.in, System.out);
        Path destPath = Paths.get(destName);
        assertTrue(Files.exists(destPath));
        assertArrayEquals(SRC_CONTENT.getBytes(), Files.readAllBytes(destPath));
        deleteFile(SRC_FILE_A);
        deleteFile(destName);
    }

    @Test
    void run_DirectoryToNonexistentDirectoryWithoutFlag_Throws() throws IOException {
        createDirectory(SRC_DIR_A);
        String nonexistDest = "nonexistent_dir";
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_DIR_A, nonexistDest), System.in, System.out));
        deleteDirectory(SRC_DIR_A);
    }

    @Test
    void run_DirectoryToNonexistentDirectoryWithFlag_CreatesDirectoryWithSrcContent() throws IOException, AbstractApplicationException {
        createDirectory(SRC_DIR_A);
        String fileInSrcDirName = "file_in_src_dir.txt";
        Path fileInSrcDir = Paths.get(SRC_DIR_A, fileInSrcDirName);
        String srcContent = "This is the file in the source directory.";
        createAndWriteFile(fileInSrcDir.toString(), srcContent);
        new CpApplication().run(toArgs("r", SRC_DIR_A, DEST_DIR), System.in, System.out);
        Path destDir = Paths.get(DEST_DIR);
        assertTrue(Files.exists(destDir));
        assertTrue(Files.isDirectory(destDir));
        Path fileInDestDir = Paths.get(DEST_DIR, fileInSrcDirName);
        assertTrue(Files.exists(fileInDestDir));
        assertArrayEquals(srcContent.getBytes(), Files.readAllBytes(fileInDestDir));
        deleteDirectory(SRC_DIR_A);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_MissingSrcAndDestArguments_Throws() {
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs(""), System.in, System.out));
    }

    @Test
    void run_FileToMissingDestArgument_Throws() throws IOException {
        createFile(SRC_FILE_A);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_FILE_A), System.in, System.out));
        deleteFile(SRC_FILE_A);
    }

    @Test
    void run_DirectoryToMissingDestArgument_Throws() throws IOException {
        createDirectory(SRC_DIR_A);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_DIR_A), System.in, System.out));
        deleteDirectory(SRC_DIR_A);
    }

    @Test
    void run_MultipleFilesToDirectory_CopiesToDirectory() throws IOException, AbstractApplicationException {
        createFile(SRC_FILE_A);
        createFile(SRC_FILE_B);
        createDirectory(DEST_DIR);
        new CpApplication().run(toArgs("", SRC_FILE_A, SRC_FILE_B, DEST_DIR), System.in, System.out);
        Path destAFile = Paths.get(DEST_DIR, SRC_FILE_A);
        Path destBFile = Paths.get(DEST_DIR, SRC_FILE_B);
        assertTrue(Files.exists(destAFile));
        assertTrue(Files.exists(destBFile));
        deleteFile(SRC_FILE_A);
        deleteFile(SRC_FILE_B);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_MultipleFilesToFile_Throws() throws IOException {
        createFile(SRC_FILE_A);
        createFile(SRC_FILE_B);
        createFile(DEST_FILE);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_FILE_A, SRC_FILE_B, DEST_FILE),
                System.in, System.out));
        deleteFile(SRC_FILE_A);
        deleteFile(SRC_FILE_B);
        deleteFile(DEST_FILE);
    }


    @Test
    void run_MultipleDirectoriesToDirectory_CopiesToDirectory() throws IOException, AbstractApplicationException {
        createDirectory(SRC_DIR_A);
        createDirectory(SRC_DIR_B);
        createDirectory(DEST_DIR);
        new CpApplication().run(toArgs("r", SRC_DIR_A, SRC_DIR_B, DEST_DIR), System.in, System.out);

        Path destADir = Paths.get(DEST_DIR, SRC_DIR_A);
        Path destBDir = Paths.get(DEST_DIR, SRC_DIR_B);
        assertTrue(Files.exists(destADir));
        assertTrue(Files.exists(destBDir));
        assertTrue(Files.isDirectory(destADir));
        assertTrue(Files.isDirectory(destBDir));
        deleteDirectory(SRC_DIR_A);
        deleteDirectory(SRC_DIR_B);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_MultipleDirectoriesToFile_Throws() throws IOException {
        createDirectory(SRC_DIR_A);
        createDirectory(SRC_DIR_B);
        createFile(DEST_FILE);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("r", SRC_DIR_A, SRC_DIR_B, DEST_FILE), System.in, System.out));
        deleteDirectory(SRC_DIR_A);
        deleteDirectory(SRC_DIR_B);
        deleteFile(DEST_FILE);
    }

    @Test
    void run_MultipleFilesAndDirectoriesToDirectory_CopiesToDirectory() throws IOException, AbstractApplicationException {
        createFile(SRC_FILE_A);
        createDirectory(SRC_DIR_B);
        createDirectory(DEST_DIR);
        new CpApplication().run(toArgs("r", SRC_FILE_A, SRC_DIR_B, DEST_DIR), System.in, System.out);
        Path destAFile = Paths.get(DEST_DIR, SRC_FILE_A);
        Path destBFile = Paths.get(DEST_DIR, SRC_DIR_B);
        assertTrue(Files.exists(destAFile));
        assertTrue(Files.exists(destBFile));
        deleteFile(SRC_FILE_A);
        deleteDirectory(SRC_DIR_B);
        deleteDirectory(DEST_DIR);
    }

    @Test
    void run_MultipleFilesAndDirectoriesToFile_Throws() throws IOException {
        createFile(SRC_FILE_A);
        createDirectory(SRC_DIR_B);
        createFile(DEST_FILE);
        assertThrows(CpException.class, () -> new CpApplication().run(toArgs("", SRC_FILE_A, SRC_DIR_B, DEST_FILE), System.in, System.out));
        deleteFile(SRC_FILE_A);
        deleteDirectory(SRC_DIR_B);
        deleteFile(DEST_FILE);
    }
}
