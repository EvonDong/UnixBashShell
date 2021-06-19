package tdd.bf;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class GrepApplicationTest {
    private static final String TEMP = "temp-grep";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static final byte[] BYTES_A = "First line\nSecond line\nThird line\nFourth line\n".getBytes();
    public static final byte[] BYTES_B = "Fifth line\nSixth line\nSeventh line\nEighth line\n".getBytes();
    public static Deque<Path> files = new ArrayDeque<>();

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
        Path path = TEMP_PATH.resolve(name);
        Files.createFile(path);
        files.push(path);
        return path;
    }

    private Path createDirectory(String folder) throws IOException {
        Path path = TEMP_PATH.resolve(folder);
        Files.createDirectory(path);
        files.push(path);
        return path;
    }

    private String[] toArgs(String flags, String pattern, String... files) {
        List<String> args = new ArrayList<>();
        if (!flags.isEmpty()) {
            args.add("-" + flags);
        }
        args.add(pattern);
        for (String file : files) {
            if (file.equals("-")) {
                args.add(file);
            } else {
                args.add(Paths.get(TEMP, file).toString());
            }
        }
        return args.toArray(new String[0]);
    }

    private Path getRelativePath(String name) {
        return Paths.get(TEMP, name);
    }

    @Test
    void run_MultipleFilesCountLinesCaseInsensitive_PrintsCountWithNames() throws AbstractApplicationException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile("s.txt"), BYTES_A);
        Files.write(createFile("t.txt"), BYTES_B);
        new GrepApplication().run(toArgs("ci", "th", "s.txt", "t.txt"), System.in, output);
        String expected = getRelativePath("s.txt") + ": 2" + STRING_NEWLINE +
                getRelativePath("t.txt") + ": 4" + STRING_NEWLINE;
        assertArrayEquals(expected.getBytes(), output.toByteArray());
    }

}