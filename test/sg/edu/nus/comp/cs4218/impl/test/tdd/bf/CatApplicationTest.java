package tdd.bf;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
            if (file.equals("-")) {
                args.add(file);
            } else {
                args.add(Paths.get(TEMP, file).toString());
            }
        }
        return args.toArray(new String[0]);
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
        new CatApplication().run(toArgs("n", fileName1, fileName2), System.in, output);
        assertArrayEquals((text + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

}

