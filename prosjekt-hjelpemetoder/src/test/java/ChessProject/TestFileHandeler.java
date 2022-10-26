package ChessProject;

import ChessProject.brett.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;


public class TestFileHandeler {
    private static FileHandeler fh;
    private Game game;
    private Brett brett;
    private String testSave = "savedChessGameTest";
    // private String saveName = "savedChessGame";


	@BeforeEach
	public void setUp() {
        fh = new FileHandeler();
		game = new Game();
		brett = game.getBoard();
        brett.makeCoolBoard();
	}
	//cant find files. Testene funket ikke i mtp path så jeg gjorde det i main.
    @Test
	public void testLoad() {
		Game savedNewGame = new Game(); // Required to ignore Eclipse warning
		try {
			//pathen funker ikke i junit. Veldig snodig. Jeg prøvde å bruke getFile. Da fungerte det i junit, men ikke i appen.
			fh.loadFile(testSave, savedNewGame);
		} catch (FileNotFoundException e) {
			fail(e);
			return;
		}
		assertEquals(game.getBoard().toString(), savedNewGame.getBoard().toString());
		assertFalse(game.isBlackWin());
		assertFalse(game.isWhiteWin());
		assertTrue(game.isGameOn());
		assertTrue(game.isWhiteTurn());
	}
    @Test
	public void testLoadNonExistingFile() {
		assertThrows(
				FileNotFoundException.class,
				() -> fh.loadFile("poop", game),
				"FileNotFoundException should be thrown when file does not exist!");
	}

	@Test
	public void testLoadInvalidFile() {
		assertThrows(
				Exception.class,
				() -> fh.loadFile("feilformat", game),
				"An exception should be thrown if loaded file is invalid!");
	}

	@Test
	public void testSave() {
		try {
			//pathen funker ikke i junit. Veldig snodig. Jeg prøvde å bruke getFile. Da fungerte det i junit, men ikke i appen.
			fh.saveFileTest("test-save-new", game);
		} catch (IOException e) {
			fail(e);
			return;
		}

		byte[] testFile = null, newFile = null;

		try {
			testFile = Files.readAllBytes(fh.getFile(testSave).toPath());
		} catch (IOException e) {
			fail(e);
			return;
		}

		try {
			newFile = Files.readAllBytes(fh.getFile("test-save-new").toPath());
		} catch (IOException e) {
			fail(e);
			return;
		}
		assertNotNull(testFile);
		assertNotNull(newFile);
		assertTrue(Arrays.equals(testFile, newFile));
	}

	@AfterAll
	static void teardown() {
		File newTestSaveFile = fh.getFile("test-save-new");
		newTestSaveFile.delete();
	}
}