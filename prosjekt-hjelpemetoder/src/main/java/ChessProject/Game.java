package ChessProject;

import java.util.ArrayList;
import java.util.List;
import ChessProject.brett.*;
import ChessProject.brikker.*;

public class Game {
    private Brett brett;
    private boolean isWhitetrun = true;
    private boolean gameOn = true;
    private boolean whiteWin = false;
    private boolean blackWin = false;
    private List<Move> movesPlayed = new ArrayList<>(); // dette er i tilfelle jeg har lyst til å innføre passant. Da må jeg huske tidligere moves
    public Game(){
        brett = new Brett();
    }

    public void makeMove(Tile start, Tile end){
        Brikke movingPiece = start.getBrikke();
        Brikke endPiece = end.getBrikke();

        if(!gameOn) return;
        else if( movingPiece == null) throw new IllegalArgumentException("velg en brikke a");
        else if(movingPiece.isWhite() != isWhitetrun) throw new IllegalArgumentException("Ikke din tur, kjekken");
        else if(!movingPiece.canMove(brett, start, end)) throw new IllegalArgumentException("Ugyldig trekk");
        if(endPiece instanceof King){ //sjekker om noen dreper kongen.
            gameOn = false;
            if(endPiece.isWhite()) blackWin = true;
            else whiteWin = true;
        }
        
        movesPlayed.add(new Move(start, end));
        start.setBrikke(null);
        end.setBrikke(movingPiece);
        isWhitetrun = !isWhitetrun;//bytter tur
    }
    
    public boolean isBlackWin() {
        return blackWin;
    }
    public boolean isWhiteTurn(){
        return isWhitetrun;
    }
    public boolean isWhiteWin() {
        return whiteWin;
    }
    public boolean isGameOn() {
        return gameOn;
    }
    public Brett getBoard(){
        return brett;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }
    public void setBlackWin(boolean blackWin) {
        this.blackWin = blackWin;
    }
    public void setWhiteTurn(boolean isWhitetrun){
        this.isWhitetrun = isWhitetrun;
    }
    public void setWhiteWin(boolean whiteWin) {
        this.whiteWin = whiteWin;
    }
}
