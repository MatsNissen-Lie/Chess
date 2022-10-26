package ChessProject.brikker;

import ChessProject.brett.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Brikke implements BrikkeInterface{
    public final boolean isWhite;
    public final char type;

    public Brikke(boolean isWhite, char type) {
        this.isWhite = isWhite;
        this.type = type;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public char getType() {
        return type;
    }

    protected List<Tile> movesInOneDirection(Tile startTile, int xDir, int yDir, int maxSteps, Brett brett) {
        List<Tile> muligeTrekk = new ArrayList<>(); //hvordan fyller jeg inn
        boolean clearPath = true;
        int steps = 1;
        int xPos = startTile.getX() + xDir;
        int yPos = startTile.getY() + yDir;
        boolean movingPieceColor = startTile.getBrikke().isWhite();
        
        while(clearPath && steps <= maxSteps){
            if(xPos < 0 || xPos > 7 || yPos < 0 || yPos > 7){
                clearPath = false;
                continue; // dersom posisjen er utenfor brettet, stopp.
            } 
            Tile newTile = brett.getBoardTile(xPos, yPos);
            Brikke nyBrikke = newTile.getBrikke();
            if(nyBrikke == null || nyBrikke.isWhite() != movingPieceColor) muligeTrekk.add(newTile);
            else if(nyBrikke.isWhite() == movingPieceColor) clearPath = false;
            xPos += xDir;
            yPos += yDir;
            steps++; // noen brikker kan gå en begresenset antall skritt.
        }
        return muligeTrekk;
    }

    public boolean containsTile(List<Tile> muligeTrekk, Tile endTile){
        return muligeTrekk.stream().filter(obj -> obj.equals(endTile)).findFirst().isPresent();
    }
    public abstract List<Tile> getAllMoves(Brett brett, Tile start);
    
    public boolean canMove(Brett brett, Tile start, Tile slutt){
        List<Tile> muligeTrekk = getAllMoves(brett, start);
        //jeg må hente alle trekk til motspilleren og fjerne de for kongen.
        return containsTile(muligeTrekk, slutt);
    }
    public static void main(String[] args) {
        // Brikke rook = new Rook(true);

    }
}
