package ChessProject.brikker;

import java.util.ArrayList;
import java.util.List;

import ChessProject.brett.Brett;
import ChessProject.brett.Tile;

public class King extends Brikke{
    // private boolean rokadeFerdig = false; //hvis jeg ønsker å lage det. Det blir ork.
    public King(boolean isWhite){
        super(isWhite, 'k');
    }
    @Override
    public List<Tile> getAllMoves(Brett brett, Tile start){
        List<Tile> muligeTrekk = new ArrayList<>(); //hvordan fyller jeg inn
        muligeTrekk.addAll(movesInOneDirection(start, 1, 1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -1, -1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -1, 1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 1, -1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 0, 1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 0, -1, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, 1, 0, 1, brett));
        muligeTrekk.addAll(movesInOneDirection(start, -1, 0, 1, brett));
        return muligeTrekk;
    }

    // @Override
    // public boolean canMove(Brett brett, Tile start, Tile slutt){ //hvis jeg har lyst til å hindre sjakk
    //     List<Tile> muligeTrekk = getAllMoves(brett, start);
    //     //jeg må hente alle trekk til motspilleren og fjerne de for kongen.
    //     return containsTile(muligeTrekk, slutt);
    // }
    
}
