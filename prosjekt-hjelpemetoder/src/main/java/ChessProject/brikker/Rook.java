package ChessProject.brikker;

import java.util.ArrayList;
import java.util.List;

import ChessProject.brett.Brett;
import ChessProject.brett.Tile;

public class Rook extends Brikke{
    public Rook(boolean isWhite){
        super(isWhite, 'r');
    }
    @Override
    public List<Tile> getAllMoves(Brett brett, Tile start){
        List<Tile> muligeTrekk = new ArrayList<>(); //hvordan fyller jeg inn
        muligeTrekk.addAll(movesInOneDirection(start, 0, 1, 8, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 0, -1, 8, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 1, 0, 8, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -1, 0, 8, brett));
        return muligeTrekk;
    }
}
