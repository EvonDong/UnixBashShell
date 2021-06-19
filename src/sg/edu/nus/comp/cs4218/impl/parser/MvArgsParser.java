package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.ArrayList;
import java.util.List;

public class MvArgsParser extends ArgsParser {
    private final static char NO_OVERWRITE_FLAG = 'n';
    private List<String> sourceList;

    public MvArgsParser() {
        super();
        legalFlags.add(NO_OVERWRITE_FLAG);
        sourceList = new ArrayList<>();
    }

    public Boolean isOverwrite() {
        return !flags.contains(NO_OVERWRITE_FLAG);
    }

    public String[] getSourceOperands() {
        sourceList = nonFlagArgs.subList(0, nonFlagArgs.size() - 1);
        String[] result = new String[sourceList.size()];
        for (int i = 0; i < sourceList.size(); i++) {
            result[i] = sourceList.get(i).trim();
        }

        return result;
    }

    public String getTargetFolderOrFile() {
        return nonFlagArgs.get(nonFlagArgs.size() - 1);
    }
}
