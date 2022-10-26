package ChessProject;

import ChessProject.brett.Tile;
import ChessProject.brikker.Brikke;

public class Move { // bruker ikke. Denne klassen er i tilfelle jeg har lyst til å lage passant.
    private Tile start;
    private Tile end;
    private Brikke pieceMoved;
    private Brikke pieceKilled;
    private boolean castlingMove = false;

    public Move(Tile start, Tile end){
        this.start = start;
        this.end = end;
        pieceMoved = start.getBrikke();
        pieceKilled = end.getBrikke();
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public Brikke getPieceKilled() {
        return pieceKilled;
    }

    public Brikke getPieceMoved() {
        return pieceMoved;
    }

    public Tile getEnd() {
        return end;
    }
    public Tile getStart() {
        return start;
    }
    
}
