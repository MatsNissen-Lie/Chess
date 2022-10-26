package ChessProject;

import java.io.FileNotFoundException;
import java.io.IOException;

import ChessProject.brett.*;
import ChessProject.brikker.Brikke;

import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChessController {
	private Game game = new Game();
	private Brett brett = game.getBoard();

	@FXML private GridPane rutenett;
	@FXML private Pane textPane;
	@FXML private Label textLable;

	private Integer fraX, fraY;
	private Text whiteWon = new Text(), blackWon = new Text();
	private FileHandeler fh = new FileHandeler();
	private String fileName = "savedChessGame";

	@FXML
	public void initialize() {
		drawBoard();
	}

	private void drawBoard(){
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				rutenett.add(addPane(brett.getBoardTile(x, y).getisWhite()), x,y);
				if (brett.getBoardTile(x, y).getBrikke() != null) {
					rutenett.add(getImageForPiece(brett.getBoardTile(x, y).getBrikke()), x, y);
				}
			}
		}
		if (game.isBlackWin()) {
			whiteWon.setText("Black wins \n brother");
			whiteWon.setStyle("-fx-font-size: 100px");
			whiteWon.setTranslateX(90.0);
			whiteWon.setTranslateY(200.0);
			whiteWon.setFill(Color.GOLD);
			rutenett.getChildren().add(whiteWon);
		} else if (game.isWhiteWin()) {
			blackWon.setText("White wins \n brother!");
			blackWon.setStyle("-fx-font-size: 100px");
			blackWon.setTranslateX(90.0);
			blackWon.setTranslateY(200.0);
			blackWon.setFill(Color.GOLD);
			rutenett.getChildren().add(blackWon);
		}
	}
	private void displayText(String txt){
		textLable.setFont(new Font("Arial", 24));
		textLable.setText(txt);

	}

	private Pane addPane(boolean isWhite) {
		Pane tile = new Pane();
		if(isWhite) tile.setStyle("-fx-background-color: #769656;");
		else tile.setStyle("-fx-background-color: #eeeed2;");
		return tile;
	}
	private ImageView getImageForPiece(Brikke brikke) {
		try {
			Image image;
			if (brikke.getType() == 'r' && brikke.isWhite()==true) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_white_rook.png"));
			else if (brikke.getType() == 'n' && brikke.isWhite()==true) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_white_knight.png"));
			else if (brikke.getType() == 'b' && brikke.isWhite()==true) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_white_bishop.png"));
			else if (brikke.getType() == 'q' && brikke.isWhite()==true) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_white_queen.png"));
			else if (brikke.getType() == 'k' && brikke.isWhite()==true) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_white_king.png"));
			else if (brikke.getType() == 'p' && brikke.isWhite()==true) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_white_pawn.png"));
			else if (brikke.getType() == 'r' && brikke.isWhite()==false) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_black_rook.png"));
			else if (brikke.getType() == 'n' && brikke.isWhite()==false) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_black_knight.png"));
			else if (brikke.getType() == 'b' && brikke.isWhite()==false) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_black_bishop.png"));
			else if (brikke.getType() == 'q' && brikke.isWhite()==false) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_black_queen.png"));
			else if (brikke.getType() == 'k' && brikke.isWhite()==false) image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_black_king.png"));
			else image = new Image(getClass().getResourceAsStream("/ChessProject/bilder/chess_black_pawn.png"));
			ImageView imageView = new ImageView();
			imageView.setFitWidth(70);
			imageView.setFitHeight(70);
			imageView.setImage(image);
			return imageView;
		} catch (NullPointerException e) {
			displayText(e.getMessage());
	    	return new ImageView();
		}
	}
	@FXML
	public void handleMoveFromTile(MouseEvent event) { // lagrer koordinatene til der brikken flyttes fra. 
		if(!game.isGameOn()) return;
		Node feltFrom = event.getPickResult().getIntersectedNode();
	    fraX = GridPane.getColumnIndex(feltFrom);
	    fraY = GridPane.getRowIndex(feltFrom);
		displayText(" ");
	}
	@FXML
	public void handleMoveToTile(MouseEvent event){
		if(!game.isGameOn()) return;
		try{
			Node feltTo = event.getPickResult().getIntersectedNode();
			 if(feltTo == null) throw new IllegalArgumentException("Velg en rute på brettet a!"); // Denne feilen kommer som følge av javafx
			 Integer x = GridPane.getColumnIndex(feltTo);
			 Integer y = GridPane.getRowIndex(feltTo);
			 if (x == null || y == null) throw new IllegalArgumentException("Hold Brikkene på brettet!"); // Denne feilen kommer som følge av javafx
			 game.makeMove(brett.getBoardTile(fraX, fraY), brett.getBoardTile(x, y));
		 } catch(IllegalArgumentException e) {
			displayText(e.getMessage());
		 }
		 drawBoard();
	}
    @FXML
	public void saveToFile() {
		try {
			fh.saveFile(fileName, game);
		} catch (IOException e) {
			displayText(e.getMessage());
		}
	}
	@FXML
	public void loadFromFile() {
		try {
			fh.loadFile(fileName, game);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		drawBoard();
	}
}
