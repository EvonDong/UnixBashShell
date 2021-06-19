package sg.edu.nus.comp.cs4218.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

/*
 This class provide functionalities for common files and directory IO operations across all tests such as create, write,
 delete, and comparison.
 */
public final class FilesUtils {

    private FilesUtils() {
    }

    public static void createFile(String fileName) throws IOException {
        File myFile = new File(fileName);
        myFile.createNewFile();
    }

    public static void writeFile(String fileName, String content) throws IOException {
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(content);
        myWriter.close();
    }

    public static void createAndWriteFile(String fileName, String content) throws IOException {
        createFile(fileName);
        writeFile(fileName, content);
    }

    public static void deleteFile(String fileName) {
        File myFile = new File(fileName);
        myFile.delete();
    }

    // checks if two file's content are equal. File name need not be equal
    public static boolean isFilesEqual(String file1, String file2) throws IOException {
        File file1Node = IOUtils.resolveFilePath(file1).toFile();
        File file2Node = IOUtils.resolveFilePath(file2).toFile();
        return FileUtils.contentEquals(file1Node, file2Node);
    }

    public static void createDirectory(String directoryName) {
        File file = new File(directoryName);
        file.mkdirs();
    }

    public static void deleteDirectory(String directoryName) throws IOException {
        FileUtils.deleteDirectory(new File(directoryName)); // recursive
    }

    // checks if two directory's contents and structures are recursively equal.
    // All inner content's name must be equal. File and Directory inclusive.
    public static boolean isDirectoryEqual(String dName1, String dName2) throws IOException {
        File directory1 = IOUtils.resolveFilePath(dName1).toFile();
        File directory2 = IOUtils.resolveFilePath(dName2).toFile();

        if (!directory1.isDirectory() || !directory2.isDirectory()) {
            return false;
        }

        File[] directory1Files = directory1.listFiles();
        File[] directory2Files = directory2.listFiles();
        Arrays.sort(directory1Files);
        Arrays.sort(directory2Files);

        if (directory1Files.length != directory2Files.length) {
            return false;
        }

        for (int i = 0; i < directory1Files.length; i++) {
            File file1 = directory1Files[i];
            File file2 = directory2Files[i];

            if (!file1.getName().equals(file2.getName())) {
                return false;
            }

            if (file1.isDirectory() && file2.isDirectory()) {
                if (!isDirectoryEqual(file1.toString(), file2.toString())) {
                    return false;
                }
            } else if (file1.isFile() && file2.isFile()) {
                if (!FileUtils.contentEquals(file1, file2)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
