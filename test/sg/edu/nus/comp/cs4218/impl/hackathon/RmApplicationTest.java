package sg.edu.nus.comp.cs4218.impl.hackathon;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.jupiter.api.*;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.app.RmApplication;

class RmApplicationTest {
    public static final String TEMP = "temp";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static Deque<Path> files = new ArrayDeque<>();

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

    private Path createDirectory(String folder) throws IOException {
        return createDirectory(folder, TEMP_PATH);
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

    private String[] toArgs(String[] flags, String... files) {
        List<String> args = new ArrayList<>();
        if (flags.length != 0) {
            for (String flag: flags) {
                args.add("-" + flag);
            }
        }
        for (String file : files) {
            args.add(Paths.get(TEMP, file).toString());
        }
        return args.toArray(new String[0]);
    }

    @Test
    void run_rmNonEmptyDirectoryWithRFlagAndDFlag_DeletesDirectory() throws IOException, AbstractApplicationException {
        Path directory = createDirectory("directory");
        createFile("dwf.txt", directory);
        createFile("dwf2.txt", directory);
        new RmApplication().run(toArgs(new String[]{"r", "d"}, "directory"), System.in, System.out);
        assertTrue(Files.notExists(directory));
    }
}