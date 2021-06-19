package sg.edu.nus.comp.cs4218.impl.ef2;

import org.apache.commons.io.input.BrokenInputStream;
import org.apache.commons.io.output.BrokenOutputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.RmException;
import sg.edu.nus.comp.cs4218.impl.app.RmApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RmApplicationTest {

    private RmApplication rmAppUnderTest;
    public static final String TEMP = "temp";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static Deque<Path> files = new ArrayDeque<>();
    public static final String RM_WARNING_PREFIX = "rm: cannot remove '";

    private static final String NEW_LINE = System.lineSeparator();

    public static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public static final PrintStream ORIGINAL_OUT = System.out;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @BeforeAll
    static void createTemp() throws IOException {
        Files.createDirectory(TEMP_PATH);
    }

    @AfterAll
    static void deleteTemp() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
        Files.delete(TEMP_PATH);
    }

    private Path createFile(String name) throws IOException {
        return createFile(name, TEMP_PATH);
    }

    private Path createDirectory(String folder) throws IOException {
        return createDirectory(folder, TEMP_PATH);
    }

    // added
    private Path createDirectoryInCurrFolder(String folder) throws IOException {
        Path path = Files.createDirectory(IOUtils.resolveFilePath(folder));
        files.push(path);
        return path;
    }

    private Path createFile(String name, Path inPath) throws IOException {
        Path path = inPath.resolve(name);
        Files.createFile(path);
        files.push(path);
        return path;
    }

    private Path createDirectory(String folder, Path inPath) throws IOException {
        Path path = inPath.resolve(folder);
        Files.createDirectory(path);
        files.push(path);
        return path;
    }

    private String[] toArgs(String flag, String... files) {
        List<String> args = new ArrayList<>();
        if (!flag.isEmpty()) {
            args.add("-" + flag);
        }
        for (String file : files) {
            args.add(Paths.get(TEMP, file).toString());
        }
        return args.toArray(new String[0]);
    }

    private String[] toArgsCurrDir(String flag, String... files) {
        List<String> args = new ArrayList<>();
        if (!flag.isEmpty()) {
            args.add("-" + flag);
        }
        for (String file : files) {
            args.add(Paths.get(file).toString());
        }
        return args.toArray(new String[0]);
    }

    // ** squaretest generated tests **
    @Test
    void testRun_EmptyStdin_throwsException() throws Exception {
        // Setup
        final InputStream stdin = InputStream.nullInputStream();
        final OutputStream stdout = new ByteArrayOutputStream();

        // Run the test
        rmAppUnderTest = new RmApplication();
        rmAppUnderTest.run(new String[]{"value"}, stdin, stdout);

        // Verify the results
    }

    @Test
    void testRun_BrokenStdin_throwsException() {
        // Setup
        final InputStream stdin = new BrokenInputStream();
        final OutputStream stdout = new ByteArrayOutputStream();

        // Run the test
        rmAppUnderTest = new RmApplication();
        assertThrows(AbstractApplicationException.class, () -> rmAppUnderTest.run(new String[]{"value"}, stdin, stdout));
    }

    @Test
    void testRun_BrokenStdout_throwsException() {
        // Setup
        final InputStream stdin = new ByteArrayInputStream("content".getBytes());
        final OutputStream stdout = new BrokenOutputStream();
        rmAppUnderTest = new RmApplication();
        // Run the test
        assertThrows(AbstractApplicationException.class, () -> rmAppUnderTest.run(new String[]{"value"}, stdin, stdout));
    }

//    ** square test generated end **

    @Test
    void run_SingleFile_DeletesFile() throws IOException, AbstractApplicationException {
        Path fileA = createFile("a.txt");
        Path fileB = createFile("bobby");
        new RmApplication().run(toArgs("", "a.txt"), System.in, System.out);
        assertTrue(Files.notExists(fileA));
        assertTrue(Files.exists(fileB));
    }

    @Test
    void run_SpaceInName_DeletesFile() throws IOException, AbstractApplicationException {
        Path fileC = createFile("c   c");
        new RmApplication().run(toArgs("","c   c"), System.in, System.out);
        assertTrue(Files.notExists(fileC));
    }

    @Test
    void run_MultipleFiles_DeletesFiles() throws IOException, AbstractApplicationException {
        Path fileD = createFile("d.txt");
        Path fileE = createFile("eerie");
        new RmApplication().run(toArgs("","d.txt", "eerie"), System.in, System.out);
        assertTrue(Files.notExists(fileD));
        assertTrue(Files.notExists(fileE));
    }

    @Test
    void run_EmptyDirectory_DeletesDirectory() throws IOException, AbstractApplicationException {
        Path folder = createDirectory("folder");
        new RmApplication().run(toArgs("d", "folder"), System.in, System.out);
        assertTrue(Files.notExists(folder));
    }

    @Test
    void run_MultipleFilesEmptyDirectories_DeletesAll() throws IOException, AbstractApplicationException {
        Path fileG = createFile("g.txt");
        Path fileH = createFile("high");
        Path directoryA = createDirectory("directoryA");
        Path directoryB = createDirectory("directoryB");
        new RmApplication().run(toArgs("d", "g.txt", "high", "directoryA", "directoryB"), System.in, System.out);
        assertTrue(Files.notExists(fileG));
        assertTrue(Files.notExists(fileH));
        assertTrue(Files.notExists(directoryA));
        assertTrue(Files.notExists(directoryB));
    }

    @Test
    void run_DirectoryWithFiles_DeletesDirectory() throws IOException, AbstractApplicationException {
        Path directory = createDirectory("directory");
        createFile("dwf.txt", directory);
        createFile("dwf2.txt", directory);
        new RmApplication().run(toArgs("r", "directory"), System.in, System.out);
        assertTrue(Files.notExists(directory));
    }

    @Test
    void run_DirectoryInDirectory_DeletesDirectory() throws IOException, AbstractApplicationException {
        Path directoryC = createDirectory("directoryC");
        createFile("did.txt", directoryC);
        Path directory = createDirectory("directoryDid", directoryC);
        Path inner = createDirectory("directoryDid", directory);
        createFile("did.txt", inner);
        createFile("did2.txt", inner);
        new RmApplication().run(toArgs("r", "directoryC"), System.in, System.out);
        assertTrue(Files.notExists(directoryC));
    }

    @Test
    void run_MultipleFilesDirectories_DeletesAll() throws IOException, AbstractApplicationException {
        Path directoryD = createDirectory("directoryD");
        createFile("mfd.txt", directoryD);
        Path directory = createDirectory("directoryMfd", directoryD);
        Path inner = createDirectory("directoryMfd", directory);
        createFile("mfd.txt", inner);
        createFile("mfd2.txt", inner);
        Path empty = createDirectory("empty");
        Path fileI = createFile("ii");
        Path fileJ = createFile("jar");
        new RmApplication().run(toArgs("r", "directoryD", "empty", "ii", "jar"), System.in, System.out);
        assertTrue(Files.notExists(directoryD));
        assertTrue(Files.notExists(empty));
        assertTrue(Files.notExists(fileI));
        assertTrue(Files.notExists(fileJ));
    }

    @Test
    void run_AbsolutePath_DeletesDirectory() throws IOException, AbstractApplicationException {
        Path directory = createDirectory("directoryAbs");
        createDirectory("innerAbs", directory);
        new RmApplication().run(new String[]{"-r", TEMP_PATH.resolve("directoryAbs").toString()}, System.in, System.out);
        assertTrue(Files.notExists(directory));
    }

    @Test
    void run_NoFlagNoFiles_DisplayWarningMessage() throws Exception {
        outContent.reset();
        String expected = "rm: missing operand" + NEW_LINE;
        new RmApplication().run(toArgsCurrDir(""), System.in, System.out);
        assertEquals(expected, outContent.toString());
    }

    @Test
    void run_EmptyDirFlagNoFiles_DisplayWarningMessage() throws Exception {
        outContent.reset();
        String expected = "rm: missing operand" + NEW_LINE;
        new RmApplication().run(toArgsCurrDir("d"), System.in, System.out);
        assertEquals(expected, outContent.toString());
    }

    @Test
    void run_UnknownFlag_Throws() throws IOException {
        Path fileK = createFile("kick");
        assertThrows(RmException.class, () -> new RmApplication().run(toArgs("x", "kick"), System.in, System.out));
        assertTrue(Files.exists(fileK));
    }

    // rm non existing file []
    @Test
    void run_NonExistingFileWithoutFlag_DisplaysWarning() throws Exception {
        outContent.reset();
        String nonExistFile = "Nope";
        String expected = RM_WARNING_PREFIX + nonExistFile + "': No such file or directory" + NEW_LINE;
        new RmApplication().run(toArgsCurrDir("", nonExistFile), System.in, System.out);
        assertEquals(expected, outContent.toString());
    }

    // rm dir without flags, should not delete, displays warning
    @Test
    void run_DirectoryWithoutFlag_DoesNotDeleteDisplaysWarning() throws Exception {

        outContent.reset();
        String expected = RM_WARNING_PREFIX + TEMP + "': Is a directory" + NEW_LINE;
        new RmApplication().run(toArgsCurrDir("", TEMP), System.in, System.out);
        assertEquals(expected, outContent.toString());
        assertTrue(Files.exists(TEMP_PATH));
    }

    @Test
    void run_EmptyDirectoryFlagOnNonEmptyDirectory_DoesNotDeleteDisplaysWarning() throws Exception {
        outContent.reset();
        createFile("content", TEMP_PATH);
        String expected = RM_WARNING_PREFIX + TEMP + "': Directory not empty" + NEW_LINE;
        new RmApplication().run(toArgsCurrDir("d", TEMP), System.in, System.out);
        assertEquals(expected, outContent.toString());
        assertTrue(Files.exists(TEMP_PATH));
    }

    @Test
    void run_OneEmptyDirOneNonEmptyDirWithEmptyDirectoryFlag_DeleteSomeDisplaysWarning() throws Exception {
        outContent.reset();
        String nonEmptyDir = "nonEmptyDirToRm";
        Path nonEmptyDirPath = createDirectoryInCurrFolder(nonEmptyDir);
        createFile("content", nonEmptyDirPath);
        String emptyDir = "emptyDirToRm";
        createDirectoryInCurrFolder(emptyDir);
        String expected = RM_WARNING_PREFIX + TEMP + "': Directory not empty" + NEW_LINE;
        new RmApplication().run(toArgsCurrDir("d", TEMP), System.in, System.out);
        assertEquals(expected, outContent.toString());
        assertTrue(Files.exists(TEMP_PATH));
    }

}
