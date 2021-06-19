package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_REDIR_INPUT;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_REDIR_OUTPUT;

public class IORedirectionHandler {
    private final List<String> argsList;
    private final ArgumentResolver argumentResolver;
    private final InputStream origInputStream;
    private final OutputStream origOutputStream;
    private List<String> noRedirArgsList;
    private InputStream inputStream;
    private OutputStream outputStream;
    public final static String ERR_FILE_COUNT = "Too many files provided for redirection";

    public IORedirectionHandler(List<String> argsList, InputStream origInputStream,
                                OutputStream origOutputStream, ArgumentResolver argumentResolver) {
        this.argsList = argsList;
        this.inputStream = origInputStream;
        this.origInputStream = origInputStream;
        this.outputStream = origOutputStream;
        this.origOutputStream = origOutputStream;
        this.argumentResolver = argumentResolver;
    }

    public void extractRedirOptions() throws AbstractApplicationException, ShellException {
        if (argsList == null || argsList.isEmpty()) {
            throw new ShellException(ERR_SYNTAX);
        }
        noRedirArgsList = new LinkedList<>();
        boolean isRedirFileRead = false;
        ListIterator<String> argsIterator = argsList.listIterator(); // extract redirection operators (with their corresponding files) from argsList
        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (!isRedirOperator(arg)) { // leave the other args untouched
                if (isRedirFileRead) {
                    closeStreams();
                    throw new ShellException(ERR_FILE_COUNT);
                }
                noRedirArgsList.add(arg);
                continue;
            }
            if (!argsIterator.hasNext()) { // if current arg is < or >, fast-forward to the next arg to extract the specified file
                closeStreams();
                throw new ShellException(ERR_MISSING_ARG);
            }
            String file = argsIterator.next();
            isRedirFileRead = true;
            if (isRedirOperator(file)) {
                closeStreams();
                throw new ShellException(ERR_SYNTAX);
            }
            List<String> fileSegment = argumentResolver.resolveOneArgument(file); // handle quoting + globing + command substitution in file arg
            if (fileSegment.size() > 1) {
                closeStreams();
                throw new ShellException(ERR_SYNTAX); // ambiguous redirect if file resolves to more than one parsed arg
            }
            file = fileSegment.get(0);
            if (arg.equals(String.valueOf(CHAR_REDIR_INPUT))) { // replace existing inputStream / outputStream
                IOUtils.closeInputStream(inputStream);
                if (!inputStream.equals(origInputStream)) { // Already have a stream
                    closeStreams();
                    throw new ShellException(ERR_MULTIPLE_STREAMS);
                }
                inputStream = IOUtils.openInputStream(file);
            } else if (arg.equals(String.valueOf(CHAR_REDIR_OUTPUT))) {
                IOUtils.closeOutputStream(outputStream);
                if (!outputStream.equals(origOutputStream)) { // Already have a stream
                    closeStreams();
                    throw new ShellException(ERR_MULTIPLE_STREAMS);
                }
                outputStream = IOUtils.openOutputStream(file);
            }
        }
    }

    public void closeStreams() throws ShellException {
        try {
            if (inputStream != null && !inputStream.equals(origInputStream)) {
                inputStream.close();
            }
            if (outputStream != null && !outputStream.equals(origOutputStream)) {
                outputStream.close();
            }
        } catch (IOException e) {
            throw new ShellException(ERR_IO_EXCEPTION);
        }
    }

    public List<String> getNoRedirArgsList() {
        return noRedirArgsList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    private boolean isRedirOperator(String str) {
        return str.equals(String.valueOf(CHAR_REDIR_INPUT)) || str.equals(String.valueOf(CHAR_REDIR_OUTPUT));
    }
}
