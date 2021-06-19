package sg.edu.nus.comp.cs4218.impl.cmd;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.APP_CAT;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.APP_ECHO;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.APP_GREP;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.APP_LS;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.APP_WC;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

class PipeCommandTest {

    private static final String CURR_DIR = Environment.currentDirectory;
    private PipeCommand pipeCommand;
    private static ByteArrayOutputStream outRes;
    private CallCommand echoCommand;
    private CallCommand echoCommand1;
    private CallCommand echoCommand2;
    private CallCommand lsCommand;
    private CallCommand wcCommand;
    private CallCommand catCommand;
    private CallCommand grepCommand;
    private CallCommand grep1Command;
    private CallCommand grep2Command;
    private CallCommand grep3Command;
    private CallCommand emptyCommand;
    public ShellImpl shell = new ShellImpl();

    @BeforeAll
    static void setupAll() {
        Environment.currentDirectory = CURR_DIR + CHAR_FILE_SEP + "testFolders" + CHAR_FILE_SEP + "app" + CHAR_FILE_SEP + "pipeTestFolder";
    }

    @AfterAll
    static void terminate() {
        Environment.currentDirectory = CURR_DIR;
    }

    @BeforeEach
    void setUp() throws ShellException {
        pipeCommand = null;
        outRes = new ByteArrayOutputStream();
        ArrayList<String> emptyList = new ArrayList<>();
        ApplicationRunner appRunner = new ApplicationRunner();
        ArgumentResolver argResolver = new ArgumentResolver();
        lsCommand = new CallCommand(Arrays.asList(APP_LS), appRunner, argResolver);
        wcCommand = new CallCommand(Arrays.asList(APP_WC, "-w"), appRunner, argResolver);
        catCommand = new CallCommand(Arrays.asList(APP_CAT, "CatGrepTest.txt"), appRunner, argResolver);
        grepCommand = new CallCommand(Arrays.asList(APP_GREP, "dir"), appRunner, argResolver);
        grep1Command = new CallCommand(Arrays.asList(APP_GREP, "blue"), appRunner, argResolver);
        grep2Command = new CallCommand(Arrays.asList(APP_GREP, ".txt"), appRunner, argResolver);
        grep3Command = new CallCommand(Arrays.asList(APP_GREP, "Cat"), appRunner, argResolver);
        echoCommand = new CallCommand(Arrays.asList(APP_ECHO), appRunner, argResolver);
        echoCommand1 = new CallCommand(Arrays.asList(APP_ECHO, "cool"), appRunner, argResolver);
        echoCommand2 = new CallCommand(Arrays.asList(APP_ECHO, "cool like this"), appRunner, argResolver);
        emptyCommand = new CallCommand(emptyList, appRunner, argResolver);
    }

    @Test
    public void evaluate_emptyArg_ExceptionThrown() throws ShellException {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(emptyCommand);
        list.add(echoCommand);
        pipeCommand = new PipeCommand(list);
        Exception err = assertThrows(ShellException.class, () -> pipeCommand.evaluate(System.in, outRes));
        assert(err.getMessage().contains(ERR_SYNTAX));
    }

    @Test
    public void getCallCommands_TestCommandList_AssertionNotThrown() {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(emptyCommand);
        list.add(echoCommand);
        pipeCommand = new PipeCommand(list);
        List<CallCommand> resList = pipeCommand.getCallCommands();
        assertEquals(list.size(), resList.size());
    }

    @Test
    public void terminate_testTermination_AssertionNotThrown() {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(lsCommand);
        list.add(grepCommand);
        pipeCommand = new PipeCommand(list);
        assertDoesNotThrow(() -> pipeCommand.terminate());
    }

    @Test
    public void evaluate_EchoWcCommands_ReturnTrue() throws AbstractApplicationException, ShellException {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(echoCommand2);
        list.add(wcCommand);

        pipeCommand = new PipeCommand(list);
        outRes = new ByteArrayOutputStream();
        pipeCommand.evaluate(System.in, outRes);
        assertEquals(String.format(" %7d", 3) + STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void evaluate_CatGrepCommands_ReturnTrue() throws AbstractApplicationException, ShellException {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(catCommand);
        list.add(grep1Command);

        pipeCommand = new PipeCommand(list);
        outRes = new ByteArrayOutputStream();
        pipeCommand.evaluate(System.in, outRes);
        assertEquals("The sky is blue." + STRING_NEWLINE + "What blue 8391" + STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void evaluate_NonMatchingCatGrepCommands_ReturnTrue() throws AbstractApplicationException, ShellException {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(catCommand);
        list.add(grepCommand);

        pipeCommand = new PipeCommand(list);
        outRes = new ByteArrayOutputStream();
        pipeCommand.evaluate(System.in, outRes);
        assertEquals("", outRes.toString());
    }

    @Test
    public void evaluate_TwoEchoCommands_OnlyLastEchoOutput() throws AbstractApplicationException, ShellException {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(echoCommand1);
        list.add(echoCommand2);
        pipeCommand = new PipeCommand(list);
        pipeCommand.evaluate(System.in, outRes);
        String actualRes = outRes.toString();
        assertTrue(actualRes.contains("cool like this"));
    }

    @Test
    public void evaluate_MultipleCommands_ReturnTrue() throws AbstractApplicationException, ShellException {
        ArrayList<CallCommand> list = new ArrayList<>();
        list.add(lsCommand);
        list.add(grep2Command);
        list.add(grep3Command);

        pipeCommand = new PipeCommand(list);
        outRes = new ByteArrayOutputStream();
        pipeCommand.evaluate(System.in, outRes);
        String actualRes = outRes.toString();
        assertEquals("CatGrepTest.txt" + STRING_NEWLINE, actualRes);
    }

    @Test
    public void shellImpl_MultipleCommands_ReturnTrue() throws AbstractApplicationException, ShellException {
        outRes = new ByteArrayOutputStream();
        shell.parseAndEvaluate(" ls | grep *.txt | grep Cat ", outRes);
        String actualRes = outRes.toString();
        assertEquals("CatGrepTest.txt" + STRING_NEWLINE, actualRes);
    }


}