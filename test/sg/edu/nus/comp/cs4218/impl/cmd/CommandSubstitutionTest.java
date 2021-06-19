package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.createFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.deleteFile;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.writeFile;

public class CommandSubstitutionTest {
    ApplicationRunner appRunner = new ApplicationRunner();
    public static final String NEW_LINE = System.lineSeparator();
    private static final String FILE_ONE = "file-one.txt";
    private static final String FILE_ONE_CONTENT = "\" ` ' | * ! < > ; \"" + System.lineSeparator() + "this is a "
            + "test" + System.lineSeparator()  + "file for command sub!";

    private String getCommandOutput(String commandString) throws AbstractApplicationException, ShellException {
        Command command = CommandBuilder.parseCommand(commandString, appRunner);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        command.evaluate(System.in, outputStream);
        return outputStream.toString();
    }

    @BeforeAll
    public static void initialize() throws Exception {
        createFile(FILE_ONE);
        writeFile(FILE_ONE, FILE_ONE_CONTENT);
    }

    @AfterAll
    public static void terminate() throws Exception {
        deleteFile(FILE_ONE);
    }

    @Test
    public void cmdSub_CatWithCmdSubEchoingFileName_ShouldOutputContentOfFile() throws Exception {
        String cmd = "cat `echo " + FILE_ONE + "`";
        assertEquals(FILE_ONE_CONTENT + NEW_LINE, getCommandOutput(cmd));
    }

    @Test
    public void cmdSub_echoWithCmdSubCat_ShouldOutputContentOfFileInOneLineSpaceSeparated() throws Exception {
        String cmd = "echo `cat " + FILE_ONE + "`";
        assertEquals("\" ` ' | * ! < > ; \" this is a test file for command sub!" + NEW_LINE, getCommandOutput(cmd));
    }

    @Test
    public void cmdSub_DoubleNestedSubstitution_ShouldOutputEchoResult() throws Exception {
        String cmd = "echo `echo `echo hello``";
        assertEquals("echo hello" + NEW_LINE, getCommandOutput(cmd));
    }

    @Test
    public void cmdSub_TripleNestedSubstitution_ShouldOutputEchoResult() throws Exception {
        String cmd = "echo `echo `echo `echo hello` `echo bye```";
        assertEquals("echo hello bye" + NEW_LINE, getCommandOutput(cmd));
    }

    @Test
    public void cmdSub_EchoWithSpecialCharacters_CharactersNotInterpretedAsSpecialCharacter() throws Exception {
        String cmd = "echo `echo \"'I love CS4218 Software Testing :)'\"`";
        assertEquals("'I love CS4218 Software Testing :)'" + NEW_LINE, getCommandOutput(cmd));
    }

    @Test
    public void cmdSub_CatWithMultipleCmdSubsEchoingFileName_ShouldOutputContentOfFile() throws Exception {
        String cmd = "cat `echo " + FILE_ONE + "` `echo " + FILE_ONE + "`";
        assertEquals(FILE_ONE_CONTENT + NEW_LINE + FILE_ONE_CONTENT + NEW_LINE, getCommandOutput(cmd));
    }

    @Test
    public void cmdSub_GrepWithCmdSubEchoingPatternAndFileName_ShouldOutputLinesThatMatchPattern() throws Exception {
        String cmd = "grep `echo !` `echo " + FILE_ONE + "`"; // grep ! FILE_ONE
        String expected = "\" ` ' | * ! < > ; \"" + System.lineSeparator() + "file for command sub!" + System.lineSeparator();
        assertEquals(expected, getCommandOutput(cmd));
    }
}