package ChessProject;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.Scanner;

import ChessProject.brett.Brett;
import ChessProject.brett.Tile;
import ChessProject.brikker.*;

public class FileHandeler implements FileInterface{


    private final static String filepath = "prosjekt-hjelpemetoder/src/main/java/ChessProject/saves/";
    // private final static String fileName = "savedChessGame";

	private void setPieceByChar(Tile tile, char symbol) {
		boolean isWhite = symbol == Character.toUpperCase(symbol);
		char c = Character.toLowerCase(symbol);
		if(c == '*') tile.setBrikke(null);
		else if(c == 'p') tile.setBrikke(new Pawn(isWhite));
		else if(c == 'b') tile.setBrikke(new Bishop(isWhite));
		else if(c == 'n') tile.setBrikke(new Knight(isWhite));
		else if(c == 'r') tile.setBrikke(new Rook(isWhite));
		else if(c == 'k') tile.setBrikke(new King(isWhite));
		else if(c == 'q') tile.setBrikke(new Queen(isWhite));
		// System.out.println(tile.getX() + " " + tile.getY() + " " + tile.getBrikke() + " = " +c);
	}

    @Override
    public void saveFile(String fileName, Game game) throws FileNotFoundException{
		if (game == null || fileName == null) throw new IllegalArgumentException("Definer game a.");
		System.out.println("file name: "+filepath + fileName + ".txt");
		// System.out.println(getFile(fileName));

		try (PrintWriter writer = new PrintWriter(filepath + fileName + ".txt")){
		// try (PrintWriter writer = new PrintWriter(getFile(fileName))){
			Brett brett = game.getBoard();
			char[][] charbrett = brett.lagCharBrett();
			for (char[] cs : charbrett) {
				writer.println(cs);
			}
			writer.println(game.isGameOn());
			writer.println(game.isWhiteTurn());
			writer.println(game.isBlackWin());
			writer.println(game.isWhiteWin());
			writer.close();
		}
    }

	//denne bruker getFile, det funker med junit. Jeg måtte endre det for at det skulle fungere i Junit.
    public void saveFileTest(String fileName, Game game) throws FileNotFoundException{
		if (game == null || fileName == null) throw new IllegalArgumentException("Definer game a.");
		System.out.println("file name: "+filepath + fileName + ".txt");
		System.out.println(getFile(fileName));
		// try (PrintWriter writer = new PrintWriter(filepath + fileName + ".txt")){
		try (PrintWriter writer = new PrintWriter(getFile(fileName))){
			Brett brett = game.getBoard();
			char[][] charbrett = brett.lagCharBrett();
			for (char[] cs : charbrett) {
				writer.println(cs);
			}
			writer.println(game.isGameOn());
			writer.println(game.isWhiteTurn());
			writer.println(game.isBlackWin());
			writer.println(game.isWhiteWin());
			writer.close();
		}
    }

    @Override
    public void loadFile(String fileName, Game game) throws FileNotFoundException {
		System.out.println("file name: "+filepath + fileName + ".txt");
		// System.out.println(getFile(fileName));
		// try(Scanner scanner = new Scanner(getFile(fileName))) {
			try(Scanner scanner = new Scanner(new File(filepath + fileName + ".txt"))) {
			for (int y = 0; y < 8; y++) {
				String row = scanner.nextLine();
				// System.out.println(row);
				char[] chars = row.toCharArray();
				// System.out.println(chars.length);
				if(chars.length != 8) throw new IllegalStateException("feil format" + chars.length);

				for (int x = 0; x < 8; x++) {
					// System.out.println(x+ " "+y+" :" + chars[x]);
					Tile tile = game.getBoard().getBoardTile(x, y);
					setPieceByChar(tile, chars[x]);
				}
			}
			String isGameOn = scanner.nextLine();
			// System.out.println(isGameOn);
			// if(isGameOn != "false" || isGameOn != "true") throw new IllegalStateException("feil format isGameOn " + isGameOn);
			game.setGameOn( Boolean.valueOf(isGameOn));

			String isWhiteTurn = scanner.nextLine();
			// if(isWhiteTurn != "false" && isWhiteTurn != "true") throw new IllegalStateException("feil format " + isWhiteTurn);
			// System.out.println(isWhiteTurn);
			game.setWhiteTurn( Boolean.valueOf(isWhiteTurn));

			String isBlackWin = scanner.nextLine();
			// if(isBlackWin != "false" && isBlackWin != "true") throw new IllegalStateException("feil format" + isBlackWin);
			// System.out.println(isBlackWin);
			game.setBlackWin( Boolean.valueOf(isBlackWin));
			// game.

			String isWhiteWin = scanner.nextLine();
			// if(isWhiteWin != "false" && isWhiteWin != "true") throw new IllegalStateException("feil format"+isWhiteWin);
			// System.out.println(isWhiteWin);
			game.setWhiteWin( Boolean.valueOf(isWhiteWin));

		}
		
    }
	public File getFile(String filename) {
		// URL resource = getClass().getClassLoader().getResource(filename+".txt");
		// System.out.println(resource);
		return new File(FileHandeler.class.getResource("saves2/").getFile() + filename + ".txt");
	}

	public static void main(String[] args) {
		// testet i konstruktør.
		System.out.println("–––––––––––––––––––––––––");
		FileHandeler fh = new FileHandeler();
		Game game = new Game();

		// String testSave = "savedChessGameTest";

		String testFile = "savedChessGameTest";
		String fileName = "savedChessGame";

		try {
			fh.loadFile(testFile, game);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		game.getBoard().print2D();
		// load funker

		Game saveNewGame = new Game();
		saveNewGame.getBoard().makeCoolBoard();

		try {
			fh.saveFile(fileName, saveNewGame);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// funker dette også!

		// System.out.println(fh.getFile(testFile).toPath());
	}
	// private final static String filepath = "prosjekt-hjelpemetoder/src/main/resources/ChessProject/saves2/";
    
}
