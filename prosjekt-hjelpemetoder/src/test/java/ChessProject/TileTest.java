package ChessProject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ChessProject.brett.*;
import ChessProject.brikker.*;

public class TileTest {
    private Tile tile;
    private Brikke king;
	
	@BeforeEach
	public void setUp() {
        int[] pos = {2,2};
		tile = new Tile(pos, true);
        king = new King(true);
	}
    
    @Test
    @DisplayName("konstruktør")
    public void TestKonstruktor(){
        int[] pos = {-1,1};
        int[] pos1 = {5,8};
		assertThrows(IllegalArgumentException.class, () ->
			new Tile(pos, true)
			,"feilmelding");
		assertThrows(IllegalArgumentException.class, () ->
			new Tile(pos1, true)
			,"feilmelding");
    }
    @Test
    @DisplayName("test set brikke")
    public void TestTileOcupant(){
        tile.setBrikke(king);
        assertEquals(King.class, tile.getBrikke().getClass());
        tile.setBrikke(null);
        assertEquals(null, tile.getBrikke());
    }
}
