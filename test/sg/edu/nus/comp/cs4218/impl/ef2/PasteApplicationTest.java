package sg.edu.nus.comp.cs4218.impl.ef2;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.impl.app.PasteApplication;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class PasteApplicationTest {
    public static final String TEMP = "temp-paste";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static Deque<Path> files = new ArrayDeque<>();
    public static final String NON_EXIST_FILE = "notExist";
    public static final String LINE1 = "Test line 1";
    public static final String LINE2 = "Test line 2";
    public static final String LINE3 = "Test line 3";

    @BeforeAll
    static void createTemp() throws IOException {
        Files.deleteIfExists(TEMP_PATH);
        Files.createDirectory(TEMP_PATH);
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
        Files.delete(TEMP_PATH);
    }

    private Path createFile(String name, String text) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.write(path, text.getBytes());
        files.push(path);
        return path;
    }

    private String[] toArgs(String flag, String... files) {
        List<String> args = new ArrayList<>();
        if (!flag.isEmpty()) {
            args.add("-" + flag);
        }
        for (String file : files) {
            if (("-").equals(file)) {
                args.add(file);
            } else {
                args.add(Paths.get(TEMP, file).toString());
            }
        }
        return args.toArray(new String[0]);
    }


    //-------------------------- Original TDD Test Cases --------------------------------------------------------

    @Test
    void testGetAbsolutePath_NonExistFile_ThrowsPasteException() {
        assertThrows(PasteException.class, () -> new PasteApplication().getAbsolutePath(NON_EXIST_FILE));
    }

    @Test
    void testMergeFile_NotExistFile_ThrowsException() {
        assertThrows(Exception.class, () -> new PasteApplication().mergeFile(false, NON_EXIST_FILE));
    }

    @Test
    void testMergeFile_SerialNotExistFile_ThrowsException() {
        assertThrows(Exception.class, () -> new PasteApplication().mergeFile(true, NON_EXIST_FILE));
    }

    @Test
    void testMergeFileAndStdin_EmptyFilesArgNotSerial_AssertionThrown() {
        assertThrows(PasteException.class, () -> new PasteApplication().mergeFileAndStdin(false, System.in, new String[]{}));
    }

    @Test
    void testMergeFileAndStdin_NullFile_ThrowsPasteException() {
        assertThrows(PasteException.class, () -> new PasteApplication().mergeFileAndStdin(true, System.in, null));
    }

    @Test
    void testMergeFile_NullFile_ThrowsPasteException() {
        assertThrows(PasteException.class, () -> new PasteApplication().mergeFile(true, null));
    }

    @Test
    void testMergeFile_EmptyFileSerial_ThrowsPasteException() {
        assertThrows(PasteException.class, () -> new PasteApplication().mergeFile(true, new String[]{}));
    }

    @Test
    void testRun_NullStdin_AssertionThrown() {
        final InputStream stdin = new ByteArrayInputStream("content".getBytes());
        final OutputStream stdout = new ByteArrayOutputStream();
        assertThrows(PasteException.class, () -> new PasteApplication().run(new String[]{null, "-"}, stdin, stdout));
    }

    @Test
    void testRun_EmptyStdin_ReturnResult() throws PasteException, IOException {
        final InputStream stdin = InputStream.nullInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new PasteApplication().run(toArgs("","-"), stdin, output);
        assertArrayEquals(("").getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleStdinNullArg_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE3;
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        assertThrows(PasteException.class, () -> new PasteApplication().run(null, inputStream, output));
    }


    @Test
    void run_SingleFileMultipleStdinDashNoFlag_DisplaysNonParallelMergedFileStdinContents() throws IOException,
            AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileText = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3";
        String fileName = "fileO.txt";
        Path filePath = createFile(fileName, fileText);
        File file = new File(filePath.toString());
        String stdinText = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2" + STRING_NEWLINE + "Test line 2.3" + STRING_NEWLINE + "Test line 2.4";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String expectedText = "Test line 2.1" + CHAR_TAB + "Test line 1.1" + CHAR_TAB + "Test line 2.2" + STRING_NEWLINE + "Test line 2.3" + CHAR_TAB + "Test line 1.2" + CHAR_TAB + "Test line 2.4" + STRING_NEWLINE + CHAR_TAB + "Test line 1.3" + STRING_NEWLINE;
        new PasteApplication().run(toArgs("", "-", fileName, "-"), inputStream, output);
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
        assertEquals(expectedText, output.toString());
        Files.deleteIfExists(filePath);
    }

    @Test
    void run_SingleFileMultipleStdinDashWithFlag_DisplaysSerialMergedFileStdinContents() throws IOException,
            AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileText = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3";
        String fileName = "fileO.txt";
        Path filePath = createFile(fileName, fileText);
        File file = new File(filePath.toString());
        String stdinText = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2" + STRING_NEWLINE + "Test line 2.3"
                + STRING_NEWLINE + "Test line 2.4";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String expectedText = "Test line 2.1" + CHAR_TAB + "Test line 2.2" + CHAR_TAB + "Test line 2.3" + CHAR_TAB + "Test line 2.4" + STRING_NEWLINE
                + "Test line 1.1" + CHAR_TAB + "Test line 1.2" + CHAR_TAB + "Test line 1.3";
        new PasteApplication().run(toArgs("s", "-", fileName, "-"), inputStream, output);
        assertArrayEquals((expectedText.trim()).getBytes(), output.toString().trim().getBytes());
        assertEquals(expectedText + STRING_NEWLINE, output.toString());
        Files.deleteIfExists(filePath);
    }

    @Test
    void run_MultipleFileSingleStdinDashNoFlag_DisplaysNonParallelMergedFileStdinContents() throws IOException,
            AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileText = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3" + STRING_NEWLINE + "Test line 1.4";
        String fileName = "fileO.txt";
        Path filePath = createFile(fileName, fileText);
        File file = new File(filePath.toString());
        String stdinText = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2" + STRING_NEWLINE + "Test line 2.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String expectedText = "Test line 1.1" + CHAR_TAB + "Test line 2.1" + CHAR_TAB + "Test line 1.1" + STRING_NEWLINE
                + "Test line 1.2" + CHAR_TAB + "Test line 2.2" + CHAR_TAB + "Test line 1.2" + STRING_NEWLINE
                + "Test line 1.3" + CHAR_TAB + "Test line 2.3" + CHAR_TAB + "Test line 1.3" + STRING_NEWLINE
                + "Test line 1.4"  + CHAR_TAB + CHAR_TAB + "Test line 1.4" + STRING_NEWLINE;
        new PasteApplication().run(toArgs("", fileName, "-", fileName), inputStream, output);
        assertEquals(expectedText, output.toString());
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
        Files.deleteIfExists(filePath);
    }

    @Test
    void run_MultipleFileSingleStdinDashWithFlag_DisplaysSerialMergedFileStdinContents() throws IOException,
            AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileText = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3" + STRING_NEWLINE + "Test line 1.4";
        String fileName = "fileO.txt";
        Path filePath = createFile(fileName, fileText);
        File file = new File(filePath.toString());
        String stdinText = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2" + STRING_NEWLINE + "Test line 2.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String expectedText = "Test line 1.1" + CHAR_TAB + "Test line 1.2" + CHAR_TAB + "Test line 1.3" + CHAR_TAB + "Test line 1.4" + STRING_NEWLINE
                + "Test line 2.1" + CHAR_TAB + "Test line 2.2" + CHAR_TAB + "Test line 2.3" + STRING_NEWLINE
                + "Test line 1.1" + CHAR_TAB + "Test line 1.2" + CHAR_TAB + "Test line 1.3" + CHAR_TAB + "Test line 1.4";

        new PasteApplication().run(toArgs("s", fileName, "-", fileName), inputStream, output);
        assertEquals(expectedText, output.toString());
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
    }


    //-------------------------- Given TDD Test Cases with some modifications --------------------------------------------------------


    @Test
    void run_SingleStdinNullStdout_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = null;
        String text = LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE3;
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs(""), inputStream, output));
    }

    @Test
    void run_NullStdinNullFilesNoFlag_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = null;
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs(""), inputStream, output));
    }

    @Test
    void run_NullStdinNullFilesFlag_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = null;
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("n"), inputStream, output));
    }

    //mergeStdin cases
    @Test
    void run_SingleStdinNoFlag_DisplaysStdinContents() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE3;
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs(""), inputStream, output);
        assertArrayEquals((text).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleStdinFlag_DisplaysNonParallelStdinContents() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE3;
        String expectedText = "Test line 1\tTest line 2\tTest line 3";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs("s"), inputStream, output);
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleStdinDashNoFlag_DisplaysStdinContents() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE3;
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs("", "-"), inputStream, output);
        assertArrayEquals((text).getBytes(), output.toByteArray());
    }

    @Test
    void run_MultipleStdinDashNoFlag_DisplaysStdinContents() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "Test line 1" + STRING_NEWLINE + "Test line 2" + STRING_NEWLINE + "Test line 3";
        String expectedText = "Test line 1" + CHAR_TAB + "Test line 2" + STRING_NEWLINE + "Test line 3" + STRING_NEWLINE;
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs("", "-", "-"), inputStream, output);
        assertEquals(expectedText, output.toString());
        assertArrayEquals(expectedText.getBytes(), output.toByteArray());
    }

    @Test
    void run_ManyStdinDashNoFlag_DisplaysStdinContents() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "Test line 1" + STRING_NEWLINE + "Test line 2" + STRING_NEWLINE + "Test line 3" + STRING_NEWLINE
                + "Test line 4" + STRING_NEWLINE + "Test line 5" + STRING_NEWLINE + "Test line 6";
        String expectedText = "Test line 1" + CHAR_TAB + "Test line 2" + CHAR_TAB + "Test line 3" + CHAR_TAB
                + "Test line 4" + STRING_NEWLINE + "Test line 5" + CHAR_TAB + "Test line 6";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs("", "-", "-", "-", "-"), inputStream, output);
        assertTrue(output.toString().contains(expectedText));
    }

    @Test
    void run_SingleStdinDashFlag_DisplaysNonParallelStdinContents() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "Test line 1\nTest line 2\nTest line 3";
        String expectedText = "Test line 1\tTest line 2\tTest line 3";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs("s", "-"), inputStream, output);
        assertEquals(expectedText, output.toString());
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
    }

    @Test
    void run_MultipleStdinDashFlag_DisplaysNonParallelStdinContents() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "Test line 1" + STRING_NEWLINE + "Test line 2" + STRING_NEWLINE + "Test line 3";
        String expectedText = "Test line 1\tTest line 2\tTest line 3" + STRING_NEWLINE;
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs("s", "-", "-", "-"), inputStream, output);
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleEmptyStdinNoFlag_DisplaysEmpty() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs(""), inputStream, output);
        assertArrayEquals((text).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleEmptyStdinFlag_DisplaysEmpty() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        new PasteApplication().run(toArgs("s"), inputStream, output);
        assertArrayEquals((text).getBytes(), output.toByteArray());
    }

    //mergeFiles cases
    @Test
    void run_NonexistFileNoFlag_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String nonexistFileName = "nonexist_file.txt";
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("", nonexistFileName),
                System.in, output));
    }

    @Test
    void run_DirectoryNoFlag_ThrowsException() throws AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String directoryName = "nonexist_file.txt";
        Path path = Paths.get(Environment.currentDirectory, directoryName);
        Files.createDirectory(path);
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("", directoryName),
                System.in, output));
        Files.delete(path);
    }

    @Test
    void run_SingleFileNoFlag_DisplaysFileContents() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileA.txt";
        String threeLineText = "Test line 1" + STRING_NEWLINE + "Test line 2" + STRING_NEWLINE + "Test line 3" + STRING_NEWLINE;
        Path filePath = createFile(fileName, threeLineText);
        File file = new File(filePath.toString());
        new PasteApplication().run(toArgs("", fileName), System.in, output);
        assertArrayEquals((threeLineText).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileFlag_DisplaysNonParallelFileContents() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileB.txt";
        String threeLineText = "Test line 1\nTest line 2\nTest line 3";
        String expectedText = "Test line 1\tTest line 2\tTest line 3";
        createFile(fileName, threeLineText);
        new PasteApplication().run(toArgs("s", fileName), System.in, output);
        assertEquals(expectedText, output.toString());
    }

    @Test
    void run_SingleEmptyFileNoFlag_DisplaysEmpty() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileC.txt";
        String text = "";
        Path filePath = createFile(fileName, text);
        File file = new File(filePath.toString());
        new PasteApplication().run(toArgs("", fileName), System.in, output);
        assertArrayEquals((text).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleEmptyFileFlag_DisplaysEmpty() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileD.txt";
        String text = "";
        createFile(fileName, text);
        new PasteApplication().run(toArgs("s", fileName), System.in, output);
        assertArrayEquals((text).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileUnknownFlag_Throws() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileE.txt";
        String text = "Test line 1\nTest line 2\nTest line 3";
        createFile(fileName, text);
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("a", fileName), System.in, output));
    }

    @Test
    void run_MultipleFilesNoFlag_DisplaysMergedFileContents() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileF.txt";
        String fileName2 = "fileG.txt";
        String text1 = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3";
        String text2 = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2";
        String expectedText = "Test line 1.1" + CHAR_TAB + "Test line 2.1" + STRING_NEWLINE + "Test line 1.2" + CHAR_TAB + "Test line 2.2" + STRING_NEWLINE + "Test line 1.3" + STRING_NEWLINE;;
        Path filePath1 = createFile(fileName1, text1);
        File file1 = new File(filePath1.toString());
        Path filePath2 = createFile(fileName2, text2);
        File file2 = new File(filePath2.toString());
        new PasteApplication().run(toArgs("", fileName1, fileName2), System.in, output);
        assertEquals(expectedText, output.toString());
    }

    @Test
    void run_MultipleFilesFlag_DisplaysNonParallelMergedFileContents() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileH.txt";
        String fileName2 = "fileI.txt";
        String text1 = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3";
        String text2 = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2";
        String expectedText = "Test line 1.1" + CHAR_TAB + "Test line 1.2" + CHAR_TAB + "Test line 1.3" + STRING_NEWLINE + "Test line 2.1" + CHAR_TAB + "Test line 2.2";
        Path filePath1 = createFile(fileName1, text1);
        File file1 = new File(filePath1.toString());
        Path filePath2 = createFile(fileName2, text2);
        File file2 = new File(filePath2.toString());
        new PasteApplication().run(toArgs("s", fileName1, fileName2), System.in, output);
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
        assertEquals(expectedText, output.toString());
    }

    @Test
    void run_MultipleEmptyFilesNoFlag_DisplaysEmpty() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileJ.txt";
        String fileName2 = "fileK.txt";
        String text = "";
        Path filePath1 = createFile(fileName1, text);
        File file1 = new File(filePath1.toString());
        Path filePath2 = createFile(fileName2, text);
        File file2 = new File(filePath2.toString());
        new PasteApplication().run(toArgs("", fileName1, fileName2), System.in, output);
        assertArrayEquals((text).getBytes(), output.toByteArray());
        assertEquals(text, output.toString());
    }

    @Test
    void run_MultipleEmptyFilesFlag_DisplaysEmpty() throws IOException, AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileL.txt";
        String fileName2 = "fileM.txt";
        String text = "";
        Path filePath1 = createFile(fileName1, text);
        File file1 = new File(filePath1.toString());
        Path filePath2 = createFile(fileName2, text);
        File file2 = new File(filePath2.toString());
        new PasteApplication().run(toArgs("s", fileName1, fileName2), System.in, output);
        assertArrayEquals((STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    //mergeFilesAndStdin cases
    @Test
    void run_SingleStdinNonexistFileNoFlag_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String nonexistFileName = "nonexist_file.txt";
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("", nonexistFileName),
                inputStream, output));
    }

    @Test
    void run_SingleStdinDirectoryNoFlag_ThrowsException() throws AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String directoryName = "nonexist_file.txt";
        Path path = Paths.get(Environment.currentDirectory, directoryName);
        Files.createDirectory(path);
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("", directoryName),
                inputStream, output));
        Files.delete(path);
    }

    @Test
    void run_EmptyStdinStdoutDirectoryNoFlag_ThrowsException() throws AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String directoryName = "dir1";
        Path path = Paths.get(Environment.currentDirectory, directoryName);
        Files.createDirectory(path);
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("", directoryName),
                inputStream, output));
        Files.delete(path);
    }

    @Test
    void run_OneArgNoFlag_ThrowsException() throws AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String directoryName = "dir1";
        Path path = Paths.get(Environment.currentDirectory, directoryName);
        Files.createDirectory(path);
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs(directoryName),
                inputStream, output));
        Files.delete(path);
    }

    @Test
    void run_StdinStdoutDirectoryNoFlag_ThrowsException() throws AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String directoryName = "dir1";
        Path path = Paths.get(Environment.currentDirectory, directoryName);
        Files.createDirectory(path);
        assertThrows(PasteException.class, () -> new PasteApplication().run(toArgs("-", directoryName),
                inputStream, output));
        Files.delete(path);
    }

    @Test
    void run_SingleStdinDashSingleFileNoFlag_DisplaysMergedStdinFileContents() throws IOException,
            AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String fileName = "fileN.txt";
        String fileText = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2";
        Path filePath = createFile(fileName, fileText);
        File file = new File(filePath.toString());
        String expectedText = "Test line 1.1\tTest line 2.1" + STRING_NEWLINE + "Test line 1.2\tTest line 2.2" + STRING_NEWLINE + "Test line 1.3" + STRING_NEWLINE;;
        new PasteApplication().run(toArgs("", "-", fileName), inputStream, output);
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
    }

    @Test
    void run_SingleFileSingleStdinDashNoFlag_DisplaysNonParallelMergedFileStdinContents() throws IOException,
            AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileText = "Test line 1.1" + STRING_NEWLINE + "Test line 1.2" + STRING_NEWLINE + "Test line 1.3";
        String fileName = "fileO.txt";
        Path filePath = createFile(fileName, fileText);
        File file = new File(filePath.toString());
        String stdinText = "Test line 2.1" + STRING_NEWLINE + "Test line 2.2";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes());
        String expectedText = "Test line 1.1" + CHAR_TAB + "Test line 2.1" + STRING_NEWLINE + "Test line 1.2" + CHAR_TAB + "Test line 2.2" + STRING_NEWLINE + "Test line 1.3" + STRING_NEWLINE;
        new PasteApplication().run(toArgs("", fileName, "-"), inputStream, output);
        assertArrayEquals((expectedText).getBytes(), output.toByteArray());
        assertEquals(expectedText, output.toString());
    }

}