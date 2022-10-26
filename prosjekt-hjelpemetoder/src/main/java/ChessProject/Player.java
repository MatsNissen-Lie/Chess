package ChessProject;

import java.util.ArrayList;
import java.util.List;

import ChessProject.brett.*;

public class Player { // denne klassen er laget for å sjekke om kongen til en spiller er i sjakk, men jeg har valgt å unnlate dette fra prosjketet.
    private List<Move> movesPlayed = new ArrayList<>();
    Brett brett;
    private final boolean isWhite;

    public Player(boolean isWhite, Brett brett){
        this.isWhite = isWhite;
        this.brett = brett;
    }
    public boolean isWhite(){
        return isWhite;
    }
    public List<Move> getMovesPlayed() {
        List<Move> copy = new ArrayList<>(movesPlayed);
        return copy;
    }

    public List<Tile> getPosibleMoves(){
        
        List<Tile> PosibleMoves = new ArrayList<>();

        return PosibleMoves;
    }

}
