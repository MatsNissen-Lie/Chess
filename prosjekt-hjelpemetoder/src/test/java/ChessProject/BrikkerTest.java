package ChessProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ChessProject.brett.Brett;
import ChessProject.brett.Tile;

public class BrikkerTest {
    private Game game;
    private Brett brett;
    private Tile whiteBishop;
    private Tile blackQueen;
    private Tile blackRook;
    private Tile whiteRook;
    private Tile leftTop;
    private Tile whiteKnight;
    private Tile blackKing;
    private Tile startPawn;
    private Tile whitePawn;
    


	@BeforeEach
	public void setUp() {
		game = new Game();
		brett = game.getBoard();
        brett.makeCoolBoard();
        whiteBishop = brett.getBoardTile(6, 3);
        blackQueen = brett.getBoardTile(4, 0);
        blackRook = brett.getBoardTile(4, 0);
        leftTop = brett.getBoardTile(0, 0);
        whiteRook = brett.getBoardTile(4, 7);
        whiteKnight = brett.getBoardTile(4, 7);
        blackKing = brett.getBoardTile(6, 1);

        startPawn = brett.getBoardTile(7, 6);
        whitePawn = brett.getBoardTile(0, 4);
	}
    @Test
    @DisplayName("test capture.")
	public void testLoad() {
        // white rook capture 
        game.makeMove(whiteRook, blackRook);
        assertEquals(null, whiteRook.getBrikke());
        assertTrue( blackRook.getBrikke().isWhite);
        // Tester at jeg ikke kan ta samme farge.
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(blackQueen, leftTop));
	}
    
    @Test
    @DisplayName("test capture.")
	public void testCapture() {
        // white rook capture black
        game.makeMove(whiteRook, blackRook);
        assertEquals(null, whiteRook.getBrikke()); // gammelt felt er null
        assertTrue( blackRook.getBrikke().isWhite); // felttet der det stod en black rook står det nå en white rook.
        // Tester at jeg ikke kan ta samme farge.
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(blackQueen, leftTop));
	}
    @Test
    @DisplayName("Rook moves")
    public void testRook() {
        List<Tile> muligTrekk = whiteRook.getBrikke().getAllMoves(brett, whiteRook);
        List<String> trekkStr = Arrays.asList("(4 , 6)", "(4 , 5)", "(4 , 4)", "(4 , 3)", "(4 , 2)", "(4 , 1)", "(4 , 0)", "(3 , 7)", "(2 , 7)", "(1 , 7)");
        assertTrue(trekkStr.toString().equals(brett.printMoves(muligTrekk).toString()));
    }
    
    @Test
    @DisplayName("Bishop moves")
    public void testBishop() {
        List<Tile> muligTrekk = whiteBishop.getBrikke().getAllMoves(brett, whiteBishop);
        List<String> trekkStr = Arrays.asList("(7 , 4)", "(5 , 2)", "(4 , 1)", "(3 , 0)", "(5 , 4)", "(4 , 5)", "(3 , 6)", "(2 , 7)", "(7 , 2)");
        assertTrue(trekkStr.toString().equals(brett.printMoves(muligTrekk).toString()));
    }
    @Test
    @DisplayName("Queen moves")
    public void testQueen() {
        List<Tile> muligTrekk = blackQueen.getBrikke().getAllMoves(brett, blackQueen);
        List<String> trekkStr = Arrays.asList("(4 , 1)", "(4 , 2)", "(4 , 3)", "(4 , 4)", "(4 , 5)", "(4 , 6)", "(4 , 7)", "(5 , 0)", "(6 , 0)", "(7 , 0)");
        assertTrue(trekkStr.toString().equals(brett.printMoves(muligTrekk).toString()));
    }
    @Test
    @DisplayName("Knight moves")
    public void testKnight() {
        List<Tile> muligTrekk = whiteKnight.getBrikke().getAllMoves(brett, whiteKnight);
        List<String> trekkStr = Arrays.asList("(4 , 6)", "(4 , 5)", "(4 , 4)", "(4 , 3)", "(4 , 2)", "(4 , 1)", "(4 , 0)", "(3 , 7)", "(2 , 7)", "(1 , 7)");
        assertTrue(trekkStr.toString().equals(brett.printMoves(muligTrekk).toString()));
    }

    @Test
    @DisplayName("king moves")
    public void testKing() {
        List<Tile> muligTrekk = blackKing.getBrikke().getAllMoves(brett, blackKing);
        List<String> trekkStr = Arrays.asList("(7 , 2)", "(5 , 0)", "(7 , 0)", "(6 , 0)");
        assertTrue(trekkStr.toString().equals(brett.printMoves(muligTrekk).toString()));
    }

    @Test
    @DisplayName("pawn moves")
    public void testpawn() {
        List<Tile> muligTrekk = whitePawn.getBrikke().getAllMoves(brett, whitePawn);
        List<String> trekkStr = Arrays.asList("(0 , 3)","(1 , 3)");
        assertTrue(trekkStr.toString().equals(brett.printMoves(muligTrekk).toString()));

        //pawn i start posisjon kan gå to skritt frem?
        List<Tile> muligTrekk2 = startPawn.getBrikke().getAllMoves(brett, startPawn);
        List<String> trekkStr2 = Arrays.asList("(7 , 5)", "(7 , 4)");
        assertTrue(trekkStr2.toString().equals(brett.printMoves(muligTrekk2).toString()));
    }
}
