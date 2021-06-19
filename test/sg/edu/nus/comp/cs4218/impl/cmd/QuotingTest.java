package sg.edu.nus.comp.cs4218.impl.cmd;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;


public class QuotingTest {
    ApplicationRunner appRunner = new ApplicationRunner();
    private static final String NEWLINE = System.lineSeparator();

    private String getEchoOutput(String stringToEcho) throws AbstractApplicationException, ShellException {
        Command command = CommandBuilder.parseCommand("echo " + stringToEcho, appRunner);
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        command.evaluate(System.in, byteArrayOS);
        return byteArrayOS.toString();
    }

    @Test
    void quoting_WithDoubleQuotes_DoubleQuotesShouldNotAppear() throws Exception {
        String echoInput = "\"hello world\"";
        String echoOutput = getEchoOutput(echoInput);
        assertEquals("hello world" + NEWLINE, echoOutput);
    }

    @Test
    void quoting_WithSingleQuotes_SingleQuotesShouldNotAppear() throws Exception {
        String echoInput = "'hello world'";
        String echoOutput = getEchoOutput(echoInput);
        assertEquals("hello world" + NEWLINE, echoOutput);
    }

    @Test
    void quoting_WithBackQuotes_ThrowsInvalidApp() {
        String echoInput = "`hello world`";
        assertThrows(ShellException.class, () -> getEchoOutput(echoInput));
    }

    @Test
    void quoting_SingleQuotesWithSpecialCharacters_SpecialCharactersShouldAppear() throws Exception {
        String echoInput = "'\\t * \" ` | < > ;'";
        assertEquals("\\t * \" ` | < > ;" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_SingleQuotesWithBackQuotes_BackQuotesShouldAppear() throws Exception {
        String echoInput = "'This is space:`echo \" \"`.'";
        assertEquals("This is space:`echo \" \"`." + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_DoubleQuotesWithSpecialCharacters_SpecialCharactersShouldAppear() throws Exception {
        String echoInput = "\"\\t * ' | < > ;\"";
        assertEquals("\\t * ' | < > ;" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_DoubleQuotesWithBackQuotes_BackQuotesShouldNotBeDisabled() throws Exception {
        String echoInput = "\"This is space:`echo \" \"`.\"";
        assertEquals("This is space: ." + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_OuterDoubleQuotesInnerSingleQuotesWithBackQuotes_SingleQuoteDisabledBackQuoteNotDisabled() throws Exception {
        String echoInput = "\"'This is space `echo \" \"`'\"";
        assertEquals("'This is space  '" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_OuterSingleQuotesInnerDoubleQuotesWithBackQuotes_SingleQuoteDisablesAllQuotes() throws Exception {
        String echoInput = "'\"This is space `echo \" \"`\"'";
        assertEquals("\"This is space `echo \" \"`\"" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_DoubleQuotesWithUnclosedBackQuote_EverythingFromBackQuoteOnwardsIgnored() throws Exception {
        String echoInput = "\"hello`bye\"";
        assertEquals("hello" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_SingleQuotesWithUnclosedBackQuote_EverythingShouldAppear() throws Exception {
        String echoInput = "'hello`bye'";
        assertEquals("hello`bye" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_DoubleQuotesWithUnclosedDoubleQuote_ThrowsShellException() {
        String echoInput = "\"hello\"bye\"";
        assertThrows(ShellException.class, () -> getEchoOutput(echoInput));
    }

    @Test
    void quoting_SingleQuotesWithUnclosedSingleQuote_ThrowsShellException() {
        String echoInput = "'hello'bye'";
        assertThrows(ShellException.class, () -> getEchoOutput(echoInput));
    }

    @Test
    void quoting_MultipleDoubleQuoting_EachQuoteTreatedAsOneArg() throws Exception {
        String echoInput = "\"he\"\"ll\"\"o\"";
        assertEquals("hello" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_DoubleQuoteMultipleArgsWithSomeArgUnquoted_AllQuotedAndUnquotedArgsShouldAppear() throws Exception {
        String echoInput = "\" hello\" bye\"bye\"";
        assertEquals(" hello byebye" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_MultipleSingleQuoting_EachQuoteTreatedAsOneArg() throws Exception {
        String echoInput = "'he''ll''o'";
        assertEquals("hello" + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_SingleQuoteMultipleArgsWithSomeArgUnquoted_AllQuotedAndUnquotedArgsShouldAppear() throws Exception {
        String echoInput = "' hello' bye'bye' ''";
        assertEquals(" hello byebye " + NEWLINE, getEchoOutput(echoInput));
    }

    @Test
    void quoting_MultipleSingleAndDoubleQuotesWithBackQuotes_BackQuoteEvaluatedAndEverythingElseAppear() throws Exception {
        String echoInput = "\"hello\" 'Let us' '`echo our love`' \"`echo for cs4218!`\"";
        assertEquals("hello Let us `echo our love` for cs4218!" + NEWLINE, getEchoOutput(echoInput));
    }
}