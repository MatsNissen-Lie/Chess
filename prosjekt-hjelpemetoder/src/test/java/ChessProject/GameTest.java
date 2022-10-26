package ChessProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ChessProject.brett.Brett;
import ChessProject.brett.Tile;

public class GameTest {
    private Game game;
    private Brett brett;
    private Tile blackQueen;
    private Tile blackRook;
    private Tile whiteRook;
    private Tile leftTop;
    private Tile blackKing;

    @BeforeEach
	public void setUp() {
		game = new Game();
		brett = game.getBoard();
        brett.makeCoolBoard();


        blackQueen = brett.getBoardTile(4, 0);
        blackRook = brett.getBoardTile(4, 0);
        leftTop = brett.getBoardTile(0, 0);
        whiteRook = brett.getBoardTile(4, 7);

        blackKing = brett.getBoardTile(6, 1);
	}
    @Test
    @DisplayName("test turns.")
	public void testTrun() {
        assertTrue(game.isWhiteTurn());
        game.makeMove(whiteRook, blackRook);
        assertFalse(game.isWhiteTurn());
        //tester å flytte samme brikke to ganger
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(blackRook, whiteRook));
	}

    @Test
    @DisplayName("test move.")
	public void testMove() {
        //tester gyldig trekk
        game.makeMove(whiteRook, blackRook);
        assertEquals(null, whiteRook.getBrikke());
        assertTrue( blackRook.getBrikke().isWhite);

        //tester å flytte en tom tile
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(whiteRook, leftTop));
        //tester at det ikke går an å flytte til samme tile
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(blackQueen, blackQueen));
	}

    @Test
    @DisplayName("test move.")
	public void testWinBlack() {
        game.setWhiteTurn(false);
        //tester gyldig trekk
        game.makeMove(blackRook, whiteRook);
        game.setWhiteTurn(false);
        Tile whiteKing = brett.getBoardTile(5, 7);
        game.makeMove(whiteRook, whiteKing);

        assertFalse(game.isWhiteWin());
        assertTrue(game.isBlackWin());
        assertFalse(game.isGameOn());
	}

    @Test
    @DisplayName("test move.")
	public void testWhiteWin() {
        game.makeMove(whiteRook, blackRook);

        Tile behindKing = brett.getBoardTile(6, 0);
        game.setWhiteTurn(true);
        game.makeMove(blackRook, behindKing);

        game.setWhiteTurn(true);
        game.makeMove(behindKing, blackKing);

        assertTrue(game.isWhiteWin());
        assertFalse(game.isGameOn());
        assertFalse(game.isBlackWin());
	}
    
}
