package sg.edu.nus.comp.cs4218.impl.tdd_regression.bf;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;

public class CatApplicationTest {
    public static final String TEMP = "temp-cat";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static Deque<Path> files = new ArrayDeque<>();

    @BeforeAll
    static void createTemp() throws IOException {
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
            if ("-".equals(file)) {
                args.add(file);
            } else {
                args.add(Paths.get(TEMP, file).toString());
            }
        }
        return args.toArray(new String[0]);
    }

    @Test
    void run_SingleStdinNullStdout_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = null;
        String text = "Test line 1\nTest line 2\nTest line 3";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        assertThrows(CatException.class, () -> new CatApplication().run(toArgs(""), inputStream, output));
    }

    @Test
    void run_NullStdinNullFilesNoFlag_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = null;
        assertThrows(CatException.class, () -> new CatApplication().run(toArgs(""), inputStream, output));
    }

    @Test
    void run_NullStdinNullFilesFlag_ThrowsException() throws AbstractApplicationException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = null;
        assertThrows(CatException.class, () -> new CatApplication().run(toArgs("n"), inputStream, output));
    }

    @Test
    void run_SingleFileUnknownFlag_ThrowsException() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileE.txt";
        String text = "Test line 1\nTest line 2\nTest line 3";
        createFile(fileName, text);
        assertThrows(CatException.class, () -> new CatApplication().run(toArgs("a", fileName), System.in, output));
    }
}

