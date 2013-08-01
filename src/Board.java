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
  private Player activePlayer;

  public JPanel getPanel(){
    return panel;
  }
  
  public void enabled(boolean enable){
    for (int i = 0; i <= BOARD_HEIGHT; i++){
	  for (int j = 0; j <= BOARD_WIDTH; j++){
	    gameBoard[i][j].setEnabled(enable);
	  }
	}
  }
  
  public Player getActivePlayer(){
    return activePlayer;
  }
  
  public void toggleActivePlayer(){
    activePlayer = getOtherPlayer(activePlayer);
  }
  
  public void initialize(Player firstPlayer, Player secondPlayer){
    panel = new JPanel();
	panel.setLayout(new GridLayout(8,8));
	player1 = firstPlayer;
	player2 = secondPlayer;
	loadPositions();
	initializeBoard();
	activePlayer = player1;
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
	addPieces();
  }
  
  private void clearBoard(){
    for (int i = 0; i < gameBoard.length;i++){
	  for (int j = 0; j < gameBoard[i].length;j++){
	    gameBoard[i][j].clearPiece();
		gameBoard[i][j].update();
	  }
	}
  }
  
  private void addPieces(Player player){
    ArrayList<ChessPiece> playerPieces = player.getPieces();
	for (ChessPiece piece: playerPieces){
	  if(piece.getPosition() != null){
	  int xLocation = piece.getPosition().getXCoord();
	  int yLocation = piece.getPosition().getYCoord();
	  gameBoard[xLocation][yLocation].setPiece(piece);
	  gameBoard[xLocation][yLocation].update();
	  }
	}
  }
  
  private void addPieces(){
	addPieces(player1);
	addPieces(player2);
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
		//nextPosition.addActionListener((HumanPlayer)player1);
		//JButton button = new JButton();		  
		if(counter % 2 == 0){
		  nextPosition.setBackground(Color.black);
		}
		else{
		  nextPosition.setBackground(Color.red);
		}
		counter++;
		//nextPosition.setButton(button);
		panel.add(nextPosition);
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