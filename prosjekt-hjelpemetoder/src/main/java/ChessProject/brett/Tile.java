package ChessProject.brett;
import ChessProject.brikker.*;

public class Tile {
    private final int[] pos;
    private final boolean isWhite;
    private boolean isOccupied = false;
    private Brikke brikke = null;

    public Tile(int[] pos, boolean isWhite) {
        if(!checkPos(pos)) throw new IllegalArgumentException("ugyldig tile posisjon.");
        this.pos = pos;
        this.isWhite = isWhite;
    }
    private boolean checkPos(int[] pos){
        int x = pos[0];
        int y = pos[1];
        if(x > 7 || x < 0 || 0 > y || y > 7) return false;
        return true;
    }    


    public boolean isOccupied() {
        return isOccupied;
    }
    public Brikke getBrikke() {
        return brikke;
    }
    public void setBrikke(Brikke brikke) {
        this.brikke = brikke;
        if(this.brikke == null){
            isOccupied = false;
        } else isOccupied = true;
    }

    public boolean getisWhite() {
        return isWhite;
    }
    
    public int getX() {
        return pos[0];
    }
    public int getY() {
        return pos[1];
    }

    public int[] getPos() {
        return pos;
    }
    
    @Override
    public String toString() {
        return "("+pos[0]+" , "+pos[1]+")";
    }
    public static void main(String[] args) {
        int[] pos = {1, 2};
        Tile tile = new Tile(pos, true);
        System.out.println(tile.toString());
    }
}
