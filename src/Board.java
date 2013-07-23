import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Board{

  private BoardPosition[][] gameBoard = new BoardPosition[8][8];
  public static final int BOARD_HEIGHT = 7;
  public static final int BOARD_WIDTH = 7;
  private Player player1 = null;
  private Player player2 = null;
  private JPanel panel;

  public JPanel getPanel(){
    return panel;
  }
  
  public void initialize(Player firstPlayer, Player secondPlayer){
    panel = new JPanel();
	panel.setLayout(new GridLayout(8,8));
	player1 = firstPlayer;
	player2 = secondPlayer;
	loadPositions();
	initializeBoard();
  }
  
  public void update(){
    update(player1);
	update(player2);
  }
  
  public void update(Player player){
    for (ChessPiece piece : player.getPieces()){
	  if(piece.getPosition() != null)
	    piece.setVulnerablePositions();
	}
  }
  
  //using array of characters
  public void drawBoard(){
    clearBoard();
	addPiecesTest();
	//loop through x from top down, print y values 
	for(int yPos = gameBoard.length - 1;yPos >= 0;yPos--){
	  for(int xPos = 0; xPos < gameBoard[yPos].length;xPos++){
	    System.out.print(gameBoard[xPos][yPos]);
	  }
	  System.out.println("    " + (yPos + 1));
	}
	System.out.println("   1      2      3      4      5      6      7      8  ");
  }
  
  private void clearBoard(){
    for (int i = 0; i < gameBoard.length;i++){
	  for (int j = 0; j < gameBoard[i].length;j++){
	    gameBoard[i][j].clearPiece();
	  }
	}
  }
  
  private void addPiecesTest(Player player){
    ArrayList<ChessPiece> playerPieces = player.getPieces();
	for (ChessPiece piece: playerPieces){
	  if(piece.getPosition() != null){
	  int xLocation = piece.getPosition().getX();
	  int yLocation = piece.getPosition().getY();
	  gameBoard[xLocation][yLocation].setPiece(piece);
	  }
	}
  }
  
  private void addPiecesTest(){
	addPiecesTest(player1);
	addPiecesTest(player2);
  }
  
  public void initializeBoard(){
    player2.initializePiece(0,getBoardPosition(0,7));
	player2.initializePiece(1,getBoardPosition(7,7));
	player2.initializePiece(2,getBoardPosition(1,7));
	player2.initializePiece(3,getBoardPosition(6,7));
	player2.initializePiece(4,getBoardPosition(2,7));
	player2.initializePiece(5,getBoardPosition(5,7));
	player2.initializePiece(6,getBoardPosition(3,7));
	player2.initializePiece(7,getBoardPosition(4,7));
	player2.initializePiece(8,getBoardPosition(0,6));
	player2.initializePiece(9,getBoardPosition(1,6));
	player2.initializePiece(10,getBoardPosition(2,6));
	player2.initializePiece(11,getBoardPosition(3,6));
	player2.initializePiece(12,getBoardPosition(4,6));
	player2.initializePiece(13,getBoardPosition(5,6));
	player2.initializePiece(14,getBoardPosition(6,6));
	player2.initializePiece(15,getBoardPosition(7,6));
	
	player1.initializePiece(0,getBoardPosition(0,0));
	player1.initializePiece(1,getBoardPosition(7,0));
	player1.initializePiece(2,getBoardPosition(1,0));
	player1.initializePiece(3,getBoardPosition(6,0));
	player1.initializePiece(4,getBoardPosition(2,0));
	player1.initializePiece(5,getBoardPosition(5,0));
	player1.initializePiece(6,getBoardPosition(3,0));
	player1.initializePiece(7,getBoardPosition(4,0));
	player1.initializePiece(8,getBoardPosition(0,1));
	player1.initializePiece(9,getBoardPosition(1,1));
	player1.initializePiece(10,getBoardPosition(2,1));
	player1.initializePiece(11,getBoardPosition(3,1));
	player1.initializePiece(12,getBoardPosition(4,1));
	player1.initializePiece(13,getBoardPosition(5,1));
	player1.initializePiece(14,getBoardPosition(6,1));
	player1.initializePiece(15,getBoardPosition(7,1));
  }
  
  public BoardPosition getBoardPosition(int x, int y){
    if(x >= 0 && y >= 0 && x <= Board.BOARD_WIDTH && y <= Board.BOARD_HEIGHT)
	  return gameBoard[x][y];
	else
	  return null;  //function returns null if not a valid BoardPosition
  }
  
  //need to loop backwards through board positions to match up with draw function
  private void loadPositions(){
    int counter = 0; 
    for(int yAxis = gameBoard.length - 1;yAxis >= 0;yAxis--){
	  for(int xAxis = 0; xAxis < gameBoard[yAxis].length;xAxis++){
	    gameBoard[xAxis][yAxis] = new BoardPosition(xAxis,yAxis);
		BoardPosition nextPosition = gameBoard[xAxis][yAxis];
		JButton button = new JButton();
		  
		if(counter % 2 == 0){
		  button.setBackground(Color.black);
		}
		else{
		  button.setBackground(Color.red);
		}
		counter++;
		nextPosition.setButton(button);
		panel.add(nextPosition.getButton());
	  }
	  counter++;
	}
  }
  
  public Player getOtherPlayer(Player thisPlayer){
    if(thisPlayer.equals(player2))
	  return player1;
	else
      return player2;	
  }
  

  
  /* loop through each player's pieces
  get the position of piece and representative character- set
  private void addPieces(){
    for(int xAxis = 0; xAxis <= Board.BOARD_LENGTH;xAxis++){
	  
	}
  }*/
}