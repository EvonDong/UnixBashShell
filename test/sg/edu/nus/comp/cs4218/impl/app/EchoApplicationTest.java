package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;

import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.exception.EchoException;


class EchoApplicationTest {

    private final ByteArrayOutputStream outRes = new ByteArrayOutputStream();
    private final EchoApplication echoApp = new EchoApplication();

    @Test
    public void run_OneInput_ReturnTrue() throws EchoException {
        String[] args = {"one"};
        echoApp.run(args, System.in, outRes);
        assertEquals("one" + STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void run_MulitpleInput_ReturnTrue() throws EchoException {
        String[] args = {"two", "threeee", "3", "4"};
        echoApp.run(args, System.in, outRes);
        assertEquals("two threeee 3 4" + STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void run_EchoInput_ReturnTrue() throws EchoException {
        String[] args = {"echo", "echo"};
        echoApp.run(args, System.in, outRes);
        assertEquals("echo echo" + STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void run_SpaceInput_ReturnTrue() throws EchoException {
        String[] args = {"      "};
        echoApp.run(args, System.in, outRes);
        assertEquals("      " + STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void run_SymbolInput_ReturnTrue() throws EchoException {
        String[] args = {"[]%&%&#("};
        echoApp.run(args, System.in, outRes);
        assertEquals("[]%&%&#(" + STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void run_EmptyInput_ReturnTrue() throws EchoException {
        String[] args = new String[0];
        echoApp.run(args, System.in, outRes);
        assertEquals(STRING_NEWLINE, outRes.toString());
    }

    @Test
    public void constructResult_NullInput_ExceptionThrown() {
        String[] args = null;
        Exception err = assertThrows(EchoException.class, () -> echoApp.constructResult(args));
        assert(err.getMessage().contains(ERR_NULL_ARGS));
    }

    @Test
    public void run_nullOutStream_ExceptionThrown() {
        String[] args = {"nullOut"};
        Exception err = assertThrows(EchoException.class, () -> echoApp.run(args, System.in, null));
        assert(err.getMessage().contains(ERR_NO_OSTREAM));
    }

    @Test
    public void run_IOException_ExceptionThrown() throws IOException {
        OutputStream out = null;
        String[] args = {"cool"};
        try {
            out = new PipedOutputStream();
            echoApp.run(args, System.in, out);
        } catch (EchoException e) {
            assert(e.getMessage().contains(ERR_IO_EXCEPTION));
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }

    // TDD Tests cases
    @Test
    public void run_SingleArgument_OutputsArgument() throws EchoException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new EchoApplication().run(new String[] {"A*B*C"}, System.in, output);
        assertArrayEquals(("A*B*C" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    public void run_MultipleArgument_SpaceSeparated() throws EchoException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new EchoApplication().run(new String[] {"A", "B", "C"}, System.in, output);
        assertArrayEquals(("A B C" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    public void run_MultipleArgumentWithSpace_SpaceSeparated() throws EchoException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new EchoApplication().run(new String[] {"A B", "C D"}, System.in, output);
        assertArrayEquals(("A B C D" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    public void run_ZeroArguments_OutputsNewline() throws EchoException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new EchoApplication().run(new String[] {}, System.in, output);
        assertArrayEquals(STRING_NEWLINE.getBytes(), output.toByteArray());
    }

}