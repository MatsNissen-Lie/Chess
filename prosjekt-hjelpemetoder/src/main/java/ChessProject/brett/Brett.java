package ChessProject.brett;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ChessProject.brikker.*;

public class Brett {
    private final Tile[][] brett;

    public Brett() {
        brett = new Tile[8][8];
        for (int y = 0; y < brett.length; y++) {
            for (int x = 0; x < brett.length; x++) {
                boolean whiteTile = false;
                if((x+y) % 2 == 0) whiteTile = true;
                int[] pos = {x,y};
                brett[y][x] = new Tile(pos, whiteTile);
            }
        }
        // makeCoolBoard();
        resetBoard();
    }
    public void resetBoard() {
        for (int y = 0; y < brett.length; y++) {
            for (int x = 0; x < brett.length; x++) {
                brett[y][x].setBrikke(null);
            }
        }
        brett[0][0].setBrikke(new Rook(false));
		brett[0][7].setBrikke(new Rook(false));
		brett[7][0].setBrikke(new Rook(true));
		brett[7][7].setBrikke(new Rook(true));
		
		brett[0][1].setBrikke(new Knight(false));
		brett[0][6].setBrikke(new Knight(false));
		brett[7][1].setBrikke(new Knight(true));
		brett[7][6].setBrikke(new Knight(true));
		
		brett[0][2].setBrikke(new Bishop(false));
		brett[0][5].setBrikke(new Bishop(false));
		brett[7][2].setBrikke(new Bishop(true));
		brett[7][5].setBrikke(new Bishop(true));
		
		brett[0][4].setBrikke(new King(false));
		brett[7][4].setBrikke(new King(true));
		brett[0][3].setBrikke(new Queen(false));
		brett[7][3].setBrikke(new Queen(true));
        for (int x = 0; x < 8; x++) {
			brett[1][x].setBrikke(new Pawn(false));
			brett[6][x].setBrikke(new Pawn(true));
		}
    }

    public void makeCoolBoard() {
        for (int y = 0; y < brett.length; y++) {
            for (int x = 0; x < brett.length; x++) {
                brett[y][x].setBrikke(null);
            }
        }
        brett[0][0].setBrikke(new Rook(false));
		brett[0][4].setBrikke(new Rook(false));
		brett[7][0].setBrikke(new Rook(true));
		brett[7][4].setBrikke(new Rook(true));
		
		brett[1][3].setBrikke(new Bishop(false));
		brett[3][6].setBrikke(new Bishop(true));
		
		brett[2][5].setBrikke(new Knight(false));
		brett[5][2].setBrikke(new Knight(true));
		
		brett[1][6].setBrikke(new King(false));
		brett[7][5].setBrikke(new King(true));
		brett[0][3].setBrikke(new Queen(false));
		brett[5][6].setBrikke(new Queen(true));
        
        brett[1][7].setBrikke(new Pawn(false));
        brett[1][5].setBrikke(new Pawn(false));
        brett[2][6].setBrikke(new Pawn(false));
        brett[2][0].setBrikke(new Pawn(false));
        brett[3][1].setBrikke(new Pawn(false));
        brett[3][2].setBrikke(new Pawn(false));
        brett[4][0].setBrikke(new Pawn(true));
        brett[6][1].setBrikke(new Pawn(true));
        brett[5][1].setBrikke(new Pawn(true));
        brett[5][3].setBrikke(new Pawn(true));
        brett[6][7].setBrikke(new Pawn(true));
        brett[6][6].setBrikke(new Pawn(true));
    }

    public Tile getBoardTile(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) throw new IllegalArgumentException("Felt finnes ikke. Ugyldige koordinater!");
		return brett[y][x];
	}
    // public List<Tile> getALLTiles() { //relevant dersom jeg skal lage noe konge i sjakk funksjon.
    //     List<Tile> allMoves = new ArrayList<>();
    //     for (int y = 0; y < brett.length; y++) {
    //         for (int x = 0; x < brett.length; x++) {
    //             allMoves.add(brett[y][x]);
    //         }
    //     }
	// 	return allMoves;
	// }

    public char[][] lagCharBrett () { //Denne testes i filhåndetring.
        char[][] charBrett = new char[8][8];
        for (int y = 0; y < brett.length; y++) {
            for (int x = 0; x < brett.length; x++) {
                Tile tile = brett[y][x];
                char symbol = '*';
                if(tile.isOccupied()){
                    symbol = tile.getBrikke().getType();
                    if(tile.getBrikke().isWhite()) symbol = Character.toUpperCase(symbol);
                }   
                
                charBrett[y][x] = symbol;
            }
        }
        return charBrett;
    }
    public void print2D() { // bare for testing i main metoden.
        char[][] charBrett = lagCharBrett();
        for (char[] rad : charBrett) System.out.println(Arrays.toString(rad));
    }

    public List<String> printMoves(List<Tile> moves){ // også bare relvenat for testing  main.
        List<String> koordinater = new ArrayList<>();
        for (Tile tile : moves){
            koordinater.add(tile.toString());
        }
        System.out.println(koordinater);
        return koordinater;
    }

    @Override
    public String toString() {
        char[][] charBrett = lagCharBrett();
        return Arrays.deepToString(charBrett);
    }
    public static void main(String[] args) {
        Brett brett = new Brett();
        // Brett newBoard = copy(brett);
        // System.out.println(brett.toString());
        brett.resetBoard();
        brett.makeCoolBoard();
        brett.print2D();
        // Tile tile = brett.getBoardTile(4, 7);

        Tile myTile = brett.getBoardTile(0, 4);

        // whitePawn = brett.getBoardTile(0, 4);

        List<Tile> muligTrekk = myTile.getBrikke().getAllMoves(brett, myTile);
        System.out.println(muligTrekk);


    }
}
