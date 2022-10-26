package ChessProject.brikker;

import java.util.ArrayList;
import java.util.List;

import ChessProject.brett.Brett;
import ChessProject.brett.Tile;

public class Pawn extends Brikke{
    public Pawn(boolean isWhite){
        super(isWhite, 'p');
    }
    private boolean checkCoords(int x,int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) return false;
        return true;
    }
    @Override
    public List<Tile> getAllMoves(Brett brett, Tile start){
        List<Tile> muligeTrekk = new ArrayList<>(); //hvordan fyller jeg inn
        boolean firstMove = isWhite && start.getY() == 6 || isWhite == false && start.getY() == 1; //hvit er på topp
        int yDir = (isWhite) ? -1:1;
        if(checkCoords(start.getX(), start.getY()+yDir)){
            Tile foran = brett.getBoardTile(start.getX(), start.getY()+yDir);
            if(foran.getBrikke() == null) muligeTrekk.add(foran);
        }
        if(checkCoords(start.getX()-1, start.getY()+yDir)){
            Tile foranV = brett.getBoardTile(start.getX()-1, start.getY()+yDir);
            if(foranV.getBrikke() != null && foranV.getBrikke().isWhite() != isWhite) muligeTrekk.add(foranV);
        }
        if(checkCoords(start.getX()+1, start.getY()+yDir)){
            Tile foranH = brett.getBoardTile(start.getX()+1, start.getY()+yDir);
            if(foranH.getBrikke() != null && foranH.getBrikke().isWhite() != isWhite) muligeTrekk.add(foranH);
        }
        
        if(firstMove && checkCoords(start.getX(), start.getY()+2*yDir)){
            Tile toForan = brett.getBoardTile(start.getX(), start.getY()+2*yDir);
            if(toForan.getBrikke() == null) muligeTrekk.add(toForan);
        }
        return muligeTrekk;
    }
}
