package ChessProject;

import ChessProject.brett.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Brett brett;

	@BeforeEach
	public void setUp() {
		brett = new Brett();
	}
	
    @Test
    @DisplayName("tester get tile")
    public void testGetBoardTile(){
        assertThrows(IllegalArgumentException.class, () -> brett.getBoardTile(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> brett.getBoardTile(1, -1));
        assertThrows(IllegalArgumentException.class, () -> brett.getBoardTile(8, 1));
        assertThrows(IllegalArgumentException.class, () -> brett.getBoardTile(1, 8));
    }

    // jeg kunne testet at brukkene stod på riktig sted osv, men der ser man lett ut ifra aplikasjonen. Derfor har jeg valgt å ikke teste det.
}
