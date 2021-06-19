package sg.edu.nus.comp.cs4218.impl.app.args;

import sg.edu.nus.comp.cs4218.exception.UniqException;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;

public class UniqArguments {
    public static final char CHAR_CASE_COUNT = 'c';
    public static final char CHAR_IS_REPEATED = 'd';
    public static final char CHAR_ALL_REPEATED = 'D';
    private String inputFileName;
    private String outputFileName;
    private boolean count, repeated, allRepeated;

    public UniqArguments() {
        this.inputFileName = null;
        this.outputFileName = null;
        this.count = false;
        this.repeated = false;
        this.allRepeated = false;
    }

    /**
     * Handles argument list parsing for the `uniq` application.
     *
     * @param args Array of arguments to parse
     * @throws Exception
     */
    public void parse(String ... args) throws Exception {
        if (args == null) {
            throw new Exception(ERR_NULL_ARGS);
        }
        boolean parsingFlag = true;
        boolean inputFileFlag = true;
        boolean outputFileFlag = false;
        for (String arg: args) {
            if (arg.isEmpty()) {
                continue;
            }
            if (parsingFlag && arg.charAt(0) == CHAR_FLAG_PREFIX) {
                if(arg.equals(CHAR_FLAG_PREFIX + "" + CHAR_CASE_COUNT)) {
                    count = true;
                } else if (arg.equals(CHAR_FLAG_PREFIX + "" + CHAR_IS_REPEATED)) {
                    repeated = true;
                } else if (arg.equals(CHAR_FLAG_PREFIX + "" + CHAR_ALL_REPEATED)) {
                    allRepeated = true;
                } else {
                    parsingFlag = false;
                    inputFileFlag = false;
                    inputFileName = arg.trim();
                    outputFileFlag = true;
                }
            } else if (inputFileFlag) {
                parsingFlag = false;
                inputFileName = arg.trim();
                inputFileFlag = false;
                outputFileFlag = true;
            } else if(outputFileFlag) {
                outputFileName = arg.trim();
                outputFileFlag = false;
            }
        }
    }

    public boolean isCount() {
        return count;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public boolean isAllRepeated() {
        return allRepeated;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }
}
