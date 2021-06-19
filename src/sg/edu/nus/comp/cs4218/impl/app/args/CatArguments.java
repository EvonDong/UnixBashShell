package sg.edu.nus.comp.cs4218.impl.app.args;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.comp.cs4218.exception.CatException;


public class CatArguments {

    private final List<String> inputs;
    private boolean displayLineNum;
    private InputType inputType;

    public enum InputType {
        FILE_ONLY, STDIN_ONLY, FILE_AND_STDIN;
    }

    public CatArguments() {
        this.displayLineNum = false;
        this.inputType = InputType.FILE_ONLY;
        this.inputs = new ArrayList<>();
    }

    /**
     * Handles argument list parsing for the `cat` application.
     *
     * @param args Array of arguments to parse
     */
    public void parse(String... args) throws CatException {
        boolean parsingOptions = true;

        // Command format: cat [-n] [FILES]…
        if (args != null && args.length > 0) {
            // possible args: *.txt, myfile.txt, -n, -, -k (invalid)
            for (String arg : args) {
                if (arg.isEmpty()) {
                    continue;
                }
                if (parsingOptions && arg.charAt(0) == CHAR_FLAG_PREFIX && arg.length() != 1) {
                    if (arg.substring(1).equals("n")) {
                        this.displayLineNum = true;
                        parsingOptions = false;
                    } else {
                        throw new CatException(ERR_INVALID_FLAG);
                    }
                } else {
                    parsingOptions = false;
                    this.inputs.add(arg.trim());
                }
            }
        }
        this.inputType = determineInputType();
    }

    /**
     * Returns the type of input for the `cat` application.
     */
    private InputType determineInputType() {
        boolean hasFile = false, hasStdin = false;

        if (this.inputs.isEmpty()) {
            hasStdin = true;
        } else {
            for (String input : this.inputs) {
                if (("-").equals(input)) {
                    hasStdin = true;
                } else {
                    hasFile = true;
                }
            }
        }

        if (hasFile && !hasStdin) {
            return InputType.FILE_ONLY;
        } else if (!hasFile && hasStdin) {
            return InputType.STDIN_ONLY;
        } else {
            return InputType.FILE_AND_STDIN;
        }
    }

    public boolean isDisplayLineNum() {
        return displayLineNum;
    }

    public InputType getInputType() {
        return this.inputType;
    }

    public List<String> getInputs() {
        return inputs;
    }
}
