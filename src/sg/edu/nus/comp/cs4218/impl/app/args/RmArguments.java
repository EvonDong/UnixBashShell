package sg.edu.nus.comp.cs4218.impl.app.args;

import sg.edu.nus.comp.cs4218.exception.RmException;

import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;

public class RmArguments {
    public static final char RM_RECURS_OPT = 'r';
    public static final char RM_EMPTY_DIR_OPT = 'd';

    private final List<String> files;
    private boolean removeRecursive, removeEmpty;

    public RmArguments() {
        this.removeRecursive = false;
        this.removeEmpty = false;
        this.files = new ArrayList<>();
    }

    /**
     * Handles argument list parsing for the `wc` application.
     *
     * @param args Array of arguments to parse
     */
    public void parse(String... args) throws RmException {
        boolean parsingFlag = true, isRemoveRecursive = false, isRemoveEmpty = false;
        // Parse arguments
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.isEmpty()) {
                    continue;
                }
                // `parsingFlag` is to ensure all flags come first, followed by files.
                if (parsingFlag && arg.charAt(0) == CHAR_FLAG_PREFIX && arg.length() != 1) {
                    for (char c : arg.toCharArray()) {
                        if (c == CHAR_FLAG_PREFIX) {
                            continue;
                        }
                        if (c == RM_RECURS_OPT) {
                            isRemoveRecursive = true;
                            continue;
                        }
                        if (c == RM_EMPTY_DIR_OPT) {
                            isRemoveEmpty = true;
                            continue;
                        }
                        throw new RmException(ERR_INVALID_FLAG);
                    }
                } else {
                    parsingFlag = false;
                    this.files.add(arg.trim());
                }
            }
        }
        if (isRemoveRecursive || isRemoveEmpty) {
            this.removeRecursive = isRemoveRecursive;
            this.removeEmpty = isRemoveEmpty;
        }
    }

    public boolean isRecursive() {
        return removeRecursive;
    }

    public boolean isEmptyFolder() {
        return removeEmpty;
    }

    public List<String> getFiles() {
        return files;
    }

}
