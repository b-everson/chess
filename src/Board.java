import java.util.ArrayList;
public class Board{

  BoardPosition[][] gameBoard = new BoardPosition[8][8];
  public static final int BOARD_HEIGHT = 7;
  public static final int BOARD_WIDTH = 7;
  private Player player1 = null;
  private Player player2 = null;

  public Board(Player firstPlayer, Player secondPlayer){
    player1 = firstPlayer;
	player2 = secondPlayer;
	loadPositions();
	initializeBoard();
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
	  System.out.println("");
	}
  }
  
  private void clearBoard(){
    for (int i = 0; i < gameBoard.length;i++){
	  for (int j = 0; j < gameBoard[i].length;j++){
	    gameBoard[i][j].clearPosition();
	  }
	}
  }
  
  private void addPiecesTest(Player player){
    ArrayList<ChessPiece> playerPieces = player.getPieces();
	for (ChessPiece piece: playerPieces){
	  int xLocation = piece.getX();
	  int yLocation = piece.getY();
	  gameBoard[xLocation][yLocation].setPosition(piece);
	}
  }
  
  private void addPiecesTest(){
	addPiecesTest(player1);
	addPiecesTest(player2);
  }
  
  public void initializeBoard(){
    player1.initializePiece(0,0,7);
	player1.initializePiece(1,7,7);
	player1.initializePiece(2,1,7);
	player1.initializePiece(3,6,7);
	player1.initializePiece(4,2,7);
	player1.initializePiece(5,5,7);
	player1.initializePiece(6,4,7);
	player1.initializePiece(7,3,7);
	player1.initializePiece(8,0,6);
	player1.initializePiece(9,1,6);
	player1.initializePiece(10,2,6);
	player1.initializePiece(11,3,6);
	player1.initializePiece(12,4,6);
	player1.initializePiece(13,5,6);
	player1.initializePiece(14,6,6);
	player1.initializePiece(15,7,6);
	
	player2.initializePiece(0,0,0);
	player2.initializePiece(1,7,0);
	player2.initializePiece(2,1,0);
	player2.initializePiece(3,6,0);
	player2.initializePiece(4,2,0);
	player2.initializePiece(5,5,0);
	player2.initializePiece(6,3,0);
	player2.initializePiece(7,4,0);
	player2.initializePiece(8,0,1);
	player2.initializePiece(9,1,1);
	player2.initializePiece(10,2,1);
	player2.initializePiece(11,3,1);
	player2.initializePiece(12,4,1);
	player2.initializePiece(13,5,1);
	player2.initializePiece(14,6,1);
	player2.initializePiece(15,7,1);
  }
  
  private void loadPositions(){
    for(int xAxis = 0; xAxis <= Board.BOARD_WIDTH;xAxis++){
	  for(int yAxis = 0; yAxis <= Board.BOARD_HEIGHT; yAxis++){
	    gameBoard[xAxis][yAxis] = new BoardPosition(xAxis,yAxis);
	  }
	}
  }
  
  /* loop through each player's pieces
  get the position of piece and representative character- set
  private void addPieces(){
    for(int xAxis = 0; xAxis <= Board.BOARD_LENGTH;xAxis++){
	  
	}
  }*/
}