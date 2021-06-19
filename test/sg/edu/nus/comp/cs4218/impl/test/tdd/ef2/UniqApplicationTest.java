package tdd.ef2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.impl.app.UniqApplication;
import sg.edu.nus.comp.cs4218.exception.UniqException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class UniqApplicationTest {
    public static final Path CURR_PATH = Paths.get(Environment.currentDirectory);
    public static Deque<Path> files = new ArrayDeque<>();

    private static final String testInput = "Hello World" + STRING_NEWLINE +
            "Hello World" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Bob" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Bob" + STRING_NEWLINE;

    private static final String withoutFlagOutput = "Hello World" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Bob" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Bob" + STRING_NEWLINE;

    private static final String withCountFlagOutput = "2 Hello World" + STRING_NEWLINE +
            "2 Alice" + STRING_NEWLINE +
            "1 Bob" + STRING_NEWLINE +
            "1 Alice" + STRING_NEWLINE +
            "1 Bob" + STRING_NEWLINE;

    private static final String withDuplicateFlagOutput = "Hello World" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE;

    private static final String withAllDuplicateFlagOutput = "Hello World" + STRING_NEWLINE +
            "Hello World" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE;

    private static final String withCountAndDuplicateFlagsOutput = "2 Hello World" + STRING_NEWLINE +
            "2 Alice" + STRING_NEWLINE;

    private static final String withCountAndAllDuplicateFlagsOutput = "2 Hello World" + STRING_NEWLINE +
            "2 Hello World" + STRING_NEWLINE +
            "2 Alice" + STRING_NEWLINE +
            "2 Alice" + STRING_NEWLINE;

    @AfterEach
    void deleteTemp() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
    }

    private Path createFile(String name) throws IOException {
        Path path = CURR_PATH.resolve(name);
        Files.createFile(path);
        files.push(path);
        return path;
    }

    private void writeToFile(Path path, String content) throws IOException {
        Files.write(path, content.getBytes());
    }

    @Test
    void run_NoFilesWithCountAndAllDuplicateFlags_ReadsFromInputAndDisplaysCountOfRepeatedAdjacentLinesRepeatedly() {
        String[] args = {"-c", "-D"};
        InputStream stdin = new ByteArrayInputStream(testInput.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> {
            new UniqApplication().run(args, stdin, outputStream);
            assertEquals(withCountAndAllDuplicateFlagsOutput, outputStream.toString());
        });
    }
}
