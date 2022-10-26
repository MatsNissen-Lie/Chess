package ChessProject.brikker;

import java.util.ArrayList;
import java.util.List;

import ChessProject.brett.Brett;
import ChessProject.brett.Tile;
public class Knight extends Brikke {
    public Knight(boolean isWhite){
        super(isWhite, 'n');
    }
    @Override
    public List<Tile> getAllMoves(Brett brett, Tile start){
        List<Tile> muligeTrekk = new ArrayList<>(); //hvordan fyller jeg inn
        muligeTrekk.addAll(movesInOneDirection(start, 1, 2, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -1, 2, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 1, -2, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -1, -2, 1, brett));

        muligeTrekk.addAll(movesInOneDirection(start, 2, 1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -2, -1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 2, -1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -2, 1, 1, brett));
        return muligeTrekk;
    }
}
