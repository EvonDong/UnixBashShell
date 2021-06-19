package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;

class CdApplicationTest {
    CdApplication cdApplication;
    CdApplication cdApp;

    private static final String SRC_LITERAL = "src";
    private static final String INITIAL_DIRECTORY = Paths.get(Environment.currentDirectory).toString();
    private static final String SOURCE_DIRECTORY = Paths.get(Environment.currentDirectory).resolve(SRC_LITERAL).toString();

    @BeforeEach
    void setUp() {
        cdApplication = new CdApplication();
        cdApp = spy(CdApplication.class);
        Environment.currentDirectory = INITIAL_DIRECTORY;
    }

    @Test
    void run_NullArgs_ThrowsException() {
        assertThrows(CdException.class, () -> cdApplication.run(null, System.in, System.out));
    }

    @Test
    void run_NoArg_RemainsInCurrentDirectory() throws CdException {
        String currentDirectory = Environment.currentDirectory;
        cdApplication.run(new String[0], System.in, System.out);
        assertEquals(currentDirectory, Environment.currentDirectory);
    }

    @Test
    void run_MultipleArgs_ThrowsException() throws CdException {
        assertThrows(CdException.class, () -> cdApplication.run(new String[]{SRC_LITERAL, "out"}, System.in, System.out));
    }

    @Test
    void changeToDirectory_AnyValidPath_ChangesDirectory() throws CdException {
        when(cdApp.getNormalizedAbsolutePath(SRC_LITERAL)).thenReturn(SOURCE_DIRECTORY);
        cdApp.changeToDirectory(SRC_LITERAL);
        assertEquals(SOURCE_DIRECTORY, Environment.currentDirectory);
    }

    @Test
    void getNormalizedAbsolutePath_NullPath_ThrowsException() {
        assertThrows(CdException.class, () -> cdApplication.getNormalizedAbsolutePath(null));
    }

    @Test
    void getNormalizedAbsolutePath_BlankPath_ThrowsException() {
        assertThrows(CdException.class, () -> cdApplication.getNormalizedAbsolutePath(""));
    }

    @Test
    void getNormalizedAbsolutePath_NonAbsolutePath_ReturnsAbsolutePath() throws CdException {
        assertEquals(SOURCE_DIRECTORY, cdApplication.getNormalizedAbsolutePath(SRC_LITERAL));
    }

    @Test
    void getNormalizedAbsolutePath_NonExistingPath_ThrowsCdException() {
        assertThrows(CdException.class, () -> cdApplication.getNormalizedAbsolutePath("noSuchPath"));
    }

    @Test
    void getNormalizedAbsolutePath_NonExistingPathInMiddle_ThrowsCdException() {
        assertThrows(CdException.class, () -> cdApplication.getNormalizedAbsolutePath("src/noSuchPath/.."));
    }

    @Test
    void getNormalizedAbsolutePath_NonDirectoryPath_ThrowsCdException() {
        assertThrows(CdException.class, () -> cdApplication.getNormalizedAbsolutePath("PMD.Rules.xml"));
    }

    @Test
    void getNormalizedAbsolutePath_AbsolutePath_ReturnsAbsolutePath() throws CdException {
        assertEquals(SOURCE_DIRECTORY, cdApplication.getNormalizedAbsolutePath(SOURCE_DIRECTORY));
    }

    @Test
    void getNormalizedAbsolutePath_DoubleDots_ReturnsAbsolutePathToParentDirectory() throws CdException {
        assertEquals(INITIAL_DIRECTORY, cdApplication.getNormalizedAbsolutePath("src/.."));
    }

    @Test
    void getNormalizedAbsolutePath_SingleDot_ReturnsAbsolutePathToCurrentDirectory() throws CdException {
        assertEquals(INITIAL_DIRECTORY, cdApplication.getNormalizedAbsolutePath("."));
    }

    @Test
    void getNormalizedAbsolutePath_TripleDots_ReturnsAbsolutePathToParentDirectory() throws CdException {
        assertThrows(CdException.class, () -> cdApplication.getNormalizedAbsolutePath("src/..."));
    }

    @Test
    void getNormalizedAbsolutePath_DoubleDotSingleDot_ReturnsAbsolutePathToParentDirectory() throws CdException {
        assertEquals(INITIAL_DIRECTORY, cdApplication.getNormalizedAbsolutePath("src/../."));
    }

    @Test
    void getNormalizedAbsolutePath_SlashBehindValidDirectory_ReturnsAbsolutePath() throws CdException {
        assertEquals(SOURCE_DIRECTORY, cdApplication.getNormalizedAbsolutePath("src/"));
    }

    @Test
    void getNormalizedAbsolutePath_SlashesBehindValidDirectory_ReturnsAbsolutePath() throws CdException {
        assertEquals(SOURCE_DIRECTORY, cdApplication.getNormalizedAbsolutePath("src//////"));
    }

    @Test
    void getNormalizedAbsolutePath_SlashesCombinedWithDots_ReturnsAbsolutePath() throws CdException {
        assertEquals(INITIAL_DIRECTORY, cdApplication.getNormalizedAbsolutePath("src////.//sg/..///../"));
    }
}