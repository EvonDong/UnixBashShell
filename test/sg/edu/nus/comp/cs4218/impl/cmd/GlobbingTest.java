package sg.edu.nus.comp.cs4218.impl.cmd;
import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;

public class GlobbingTest {

    private static final String CURR_DIR = Environment.currentDirectory;
    private static ByteArrayOutputStream outRes;
    public ShellImpl shell = new ShellImpl();
    ArrayList<String> emptyList;
    ApplicationRunner appRunner;
    ArgumentResolver argResolver;

    private static final String NEW_LINE = System.lineSeparator();
    private static final String LS_SPACING = NEW_LINE + NEW_LINE;
    private static final String FILE_ONE = "aafIle-OnE";
    private static final String FILE_TWO = "afIle-2Two2";
    private static final String FILE_THREE = "bFIle Thr3e3";
    private static final String FILE_ONE_CONTENT = "I love" + NEW_LINE + "CS4218";
    private static final String FILE_TWO_CONTENT = "~!@#$%+";
    private static final String FILE_THREE_CONT = "THIS IS FILE" + NEW_LINE + "THREE" + "WITH 3 lin3s";
    private static final String FOLDER_TEST_ONE = "testFolder1";
    private static final String FOLDER_TEST_TWO = "testFolder2";
    private static final String HUMAN_INPUT_ONE = "I love CS4218" + NEW_LINE;

    private static final String CAT_DIR_MSG = "cat: This is a directory";
    private static final String CD_MANY_ARGS_MSG = "cd: Too many arguments";
    private static final String IS_DIR_MSG = "Is a directory";
    private static final String WC_NUM_FORMAT = " %7d";
    private static final String WC_DIR_MSG = "wc: This is a directory";
    private static final String WC_TOTAL_POSTFIX = " total";

    static void createFile(String fileName) throws IOException {
        String updatedFileName = Environment.currentDirectory + CHAR_FILE_SEP + fileName;
        File myFile = new File(updatedFileName);
        myFile.createNewFile();
    }

    static void createDirectory(String fileName) throws IOException {
        String updatedFileName = Environment.currentDirectory + "\\" + fileName;
//        File myFile = new File(updatedFileName);
//        myFile.createNewFile();
        Files.createDirectory(IOUtils.resolveFilePath(updatedFileName));
    }

    static void writeFile(String fileName, String content) throws IOException {
        String updatedFileName = Environment.currentDirectory + "\\" + fileName;
        try (FileWriter myWriter = new FileWriter(updatedFileName)) {
            myWriter.write(content);
        }
    }

    static void deleteFile(String fileName) {
        String updatedFileName = Environment.currentDirectory + "\\" + fileName;
        File myFile = new File(updatedFileName);
        myFile.delete();
    }

    static void deleteDirectory(String fileName) throws IOException {
        String updatedFileName = Environment.currentDirectory + "\\" + fileName;
//        File myFile = new File(updatedFileName);
//        myFile.delete();
        Files.deleteIfExists(IOUtils.resolveFilePath(updatedFileName));
    }

    static String readFile(String fileName) throws IOException {
        String updatedFileName = Environment.currentDirectory + "\\" + fileName;
        byte[] fileBytes = Files.readAllBytes(IOUtils.resolveFilePath(updatedFileName));
        return new String(fileBytes);
    }

    @BeforeAll
    static void setupAll() {
        Environment.currentDirectory = CURR_DIR + CHAR_FILE_SEP + "testFolders" + CHAR_FILE_SEP + "app" + CHAR_FILE_SEP + "globbingTestFolder";
    }

    @AfterAll
    static void terminate() {
        Environment.currentDirectory = CURR_DIR;
    }

    @BeforeEach
    void setUp() throws IOException {
        outRes = new ByteArrayOutputStream();
        emptyList = new ArrayList<>();
        appRunner = new ApplicationRunner();
        argResolver = new ArgumentResolver();
        createFile(FILE_ONE);
        writeFile(FILE_ONE, FILE_ONE_CONTENT);
        createFile(FILE_TWO);
        writeFile(FILE_TWO, FILE_TWO_CONTENT);
        createFile(FILE_THREE);
        writeFile(FILE_THREE, FILE_THREE_CONT);
        createDirectory(FOLDER_TEST_ONE);
        createDirectory(FOLDER_TEST_TWO);

    }

    @AfterEach
    void tearDown() throws IOException {
        deleteFile(FILE_ONE);
        deleteFile(FILE_TWO);
        deleteFile(FILE_THREE);
        deleteDirectory(FOLDER_TEST_ONE);
        deleteDirectory(FOLDER_TEST_TWO);
    }

    @Test
    public void evaluate_catCommandGlobbingAll_ReturnsStringWithContentAndWarningMessages() throws AbstractApplicationException, ShellException {
        CallCommand catCommand = new CallCommand(Arrays.asList(APP_CAT, "*"), appRunner, argResolver);
        List<String> output = new ArrayList<>();
        output.add(FILE_ONE_CONTENT);
        output.add(FILE_TWO_CONTENT);
        output.add(FILE_THREE_CONT);
        output.add(CAT_DIR_MSG);
        output.add(CAT_DIR_MSG);
        String expected = String.join(NEW_LINE, output);
        expected = expected + NEW_LINE;
        catCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_catCommandGlobbingStartingWithA_ReturnsStringWithContent() throws AbstractApplicationException, ShellException {
        CallCommand catCommand = new CallCommand(Arrays.asList(APP_CAT, "a*"), appRunner, argResolver);
        List<String> output = new ArrayList<>();
        output.add(FILE_ONE_CONTENT);
        output.add(FILE_TWO_CONTENT);
        String expected = String.join(NEW_LINE, output);
        expected = expected + NEW_LINE;
        catCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_catCommandGlobbingStartingWithT_ReturnsStringWithWarning() throws AbstractApplicationException, ShellException {
        CallCommand catCommand = new CallCommand(Arrays.asList(APP_CAT, "t*"), appRunner, argResolver);
        List<String> output = new ArrayList<>();
        output.add(CAT_DIR_MSG);
        output.add(CAT_DIR_MSG);
        String expected = String.join(NEW_LINE, output);
        expected = expected + NEW_LINE;
        catCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_echoCommandGlobbingAll_ReturnsString() throws AbstractApplicationException, ShellException {
        CallCommand echoCommand = new CallCommand(Arrays.asList(APP_ECHO, "*"), appRunner, argResolver);
        String expected = FILE_ONE + " " + FILE_TWO + " " + FILE_THREE + " " + FOLDER_TEST_ONE + " " + FOLDER_TEST_TWO;
        expected = expected + NEW_LINE;
        echoCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_echoCommandGlobbingStartWithT_ReturnsString() throws AbstractApplicationException, ShellException {
        CallCommand echoCommand = new CallCommand(Arrays.asList(APP_ECHO, "t*"), appRunner, argResolver);
        String expected = FOLDER_TEST_ONE + " " + FOLDER_TEST_TWO;
        expected = expected + NEW_LINE;
        echoCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_cdCommandGlobbingAll_ThrowsTooManyArgsException() throws ShellException {
        CallCommand cdCommand = new CallCommand(Arrays.asList(APP_CD, "*"), appRunner, argResolver);
        Exception exception = assertThrows(Exception.class, () -> cdCommand.evaluate(System.in, outRes));
        assertEquals(exception.getMessage(), CD_MANY_ARGS_MSG);
    }

    @Test
    public void evaluate_cdCommandGlobbingStartWithTMultipleDir_ThrowsTooManyArgsException() throws ShellException {
        CallCommand cdCommand = new CallCommand(Arrays.asList(APP_CD, "t*"), appRunner, argResolver);
        Exception exception = assertThrows(Exception.class, () -> cdCommand.evaluate(System.in, outRes));
        assertEquals(exception.getMessage(), CD_MANY_ARGS_MSG);
    }

    @Test
    public void evaluate_lsCommandGlobbingAll_ReturnsString() throws AbstractApplicationException, ShellException {
        CallCommand lsCommand = new CallCommand(Arrays.asList(APP_LS, "*"), appRunner, argResolver);
        String expected = FILE_ONE + LS_SPACING + FILE_TWO + LS_SPACING + FILE_THREE + LS_SPACING +
                FOLDER_TEST_ONE + ":" + LS_SPACING + FOLDER_TEST_TWO + ":" + NEW_LINE;
        lsCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_lsCommandGlobbingStartWithA_ReturnsString() throws AbstractApplicationException, ShellException {
        CallCommand lsCommand = new CallCommand(Arrays.asList(APP_LS, "a*"), appRunner, argResolver);
        String expected = FILE_ONE + LS_SPACING + FILE_TWO + NEW_LINE;
        lsCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_teeCommandGlobbingAll_ReturnsString() throws AbstractApplicationException, ShellException, IOException {
        CallCommand teeCommand = new CallCommand(Arrays.asList(APP_TEE, "*"), appRunner, argResolver);
        List<String> output = new ArrayList<>();
        output.add(HUMAN_INPUT_ONE);
        String expected = String.join(NEW_LINE, output);
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes()));
        teeCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
        assertEquals(HUMAN_INPUT_ONE, readFile(FILE_ONE));
        assertEquals(HUMAN_INPUT_ONE, readFile(FILE_TWO));
        assertEquals(HUMAN_INPUT_ONE, readFile(FILE_THREE));
    }

    @Test
    public void evaluate_teeCommandGlobbingStartWithA_ReturnsString() throws AbstractApplicationException, ShellException, IOException {
        CallCommand teeCommand = new CallCommand(Arrays.asList(APP_TEE, "a*"), appRunner, argResolver);
        List<String> output = new ArrayList<>();
        output.add(HUMAN_INPUT_ONE);
        String expected = String.join(NEW_LINE, output);
        System.setIn(new ByteArrayInputStream(HUMAN_INPUT_ONE.getBytes()));
        teeCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
        assertEquals(HUMAN_INPUT_ONE, readFile(FILE_ONE));
        assertEquals(HUMAN_INPUT_ONE, readFile(FILE_TWO));
    }

    @Test
    public void evaluate_wcCommandGlobbingAll_ReturnStringsOfWordCountAndWarning() throws ShellException, AbstractApplicationException {
        CallCommand wcCommand = new CallCommand(Arrays.asList(APP_WC, "*"), appRunner, argResolver);
        long[] fileOneCount = new long[]{2,3};
        String fileOneBytes = String.format(WC_NUM_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        long[] fileTwoCount = new long[]{1,1};
        String fileTwoBytes = String.format(WC_NUM_FORMAT, FILE_TWO_CONTENT.getBytes().length);
        long[] fileThreeCount = new long[]{2,6};
        String fileThreeBytes = String.format(WC_NUM_FORMAT, FILE_THREE_CONT.getBytes().length);
        long[] totalCount = new long[]{5,10};
        String totalBytes = String.format(WC_NUM_FORMAT,(FILE_ONE_CONTENT + FILE_TWO_CONTENT + FILE_THREE_CONT).getBytes().length);
        List<String> output = new ArrayList<>();
        output.add(String.format(WC_NUM_FORMAT,fileOneCount[0]) + String.format(WC_NUM_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        output.add(String.format(WC_NUM_FORMAT,fileTwoCount[0]) + String.format(WC_NUM_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        output.add(String.format(WC_NUM_FORMAT,fileThreeCount[0]) + String.format(WC_NUM_FORMAT,fileThreeCount[1]) + fileThreeBytes + " " + FILE_THREE);
        output.add(WC_DIR_MSG);
        output.add(String.format(WC_NUM_FORMAT, 0) + String.format(WC_NUM_FORMAT, 0) + String.format(WC_NUM_FORMAT, 0) + " " + FOLDER_TEST_ONE);
        output.add(WC_DIR_MSG);
        output.add(String.format(WC_NUM_FORMAT, 0) + String.format(WC_NUM_FORMAT, 0) + String.format(WC_NUM_FORMAT, 0) + " " + FOLDER_TEST_TWO);
        output.add(new StringBuilder().append(String.format(WC_NUM_FORMAT, totalCount[0])).append(String.format(WC_NUM_FORMAT, totalCount[1])).append(totalBytes).append(WC_TOTAL_POSTFIX).toString());
        String expected = String.join(NEW_LINE, output);
        expected = expected.concat(NEW_LINE);
        wcCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_wcCommandsGlobbingFilesStartingWith1_ReturnStringsOfWordCount() throws ShellException, AbstractApplicationException {
        CallCommand wcCommand = new CallCommand(Arrays.asList(APP_WC, "a*"), appRunner, argResolver);
        long[] fileOneCount = new long[]{2,3};
        String fileOneBytes = String.format(WC_NUM_FORMAT, FILE_ONE_CONTENT.getBytes().length);
        long[] fileTwoCount = new long[]{1,1};
        String fileTwoBytes = String.format(WC_NUM_FORMAT, FILE_TWO_CONTENT.getBytes().length);
        long[] totalCount = new long[]{3,4};
        String totalBytes = String.format(WC_NUM_FORMAT, (FILE_ONE_CONTENT + FILE_TWO_CONTENT).getBytes().length);
        List<String> output = new ArrayList<>();
        output.add(String.format(WC_NUM_FORMAT,fileOneCount[0]) + String.format(WC_NUM_FORMAT,fileOneCount[1]) + fileOneBytes + " " + FILE_ONE);
        output.add(String.format(WC_NUM_FORMAT,fileTwoCount[0]) + String.format(WC_NUM_FORMAT,fileTwoCount[1]) + fileTwoBytes + " " + FILE_TWO);
        output.add(new StringBuilder().append(String.format(WC_NUM_FORMAT, totalCount[0])).append(String.format(WC_NUM_FORMAT, totalCount[1])).append(totalBytes).append(WC_TOTAL_POSTFIX).toString());
        String expected = String.join(NEW_LINE, output);
        expected = expected.concat(NEW_LINE);
        wcCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

    @Test
    public void evaluate_grepCommandGlobbingStartingWithA_ReturnsString() throws AbstractApplicationException, ShellException {
        CallCommand grepCommand = new CallCommand(Arrays.asList(APP_GREP, "love", "a*"), appRunner, argResolver);
        List<String> output = new ArrayList<>();
        output.add(FILE_ONE + ": I love");
        String expected = String.join(NEW_LINE, output);
        expected = expected + NEW_LINE;
        grepCommand.evaluate(System.in, outRes);
        assertEquals(expected, outRes.toString());
    }

}
