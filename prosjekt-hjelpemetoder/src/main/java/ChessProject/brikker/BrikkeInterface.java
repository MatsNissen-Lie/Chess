package ChessProject.brikker;

import ChessProject.brett.*;
import java.util.List;

public interface BrikkeInterface{
    public boolean isWhite();
	public char getType();
    public List<Tile> getAllMoves(Brett Brett, Tile start);
    public boolean canMove(Brett brett, Tile start, Tile slutt);
}
