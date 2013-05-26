public class Board{

  public static final int BOARD_HEIGHT = 7;
  public static final int BOARD_WIDTH = 7;
  char[][] gameBoard = new char[8][8];
  
  //using array of characters
  public void drawBoard(){
    clearBoard();
	addPiecesTest();
	//loop through x from top down, print y values 
	for(int xPos = gameBoard.length - 1;xPos >= 0;xPos--){
	  for(int yPos = 0; yPos < gameBoard[xPos].length;yPos++){
	    System.out.print(gameBoard[xPos][yPos]);
	  }
	  System.out.println("");
	}
  }
  
  private void clearBoard(){
    for (int i = 0; i < gameBoard.length;i++){
	  for (int j = 0; j < gameBoard[i].length;j++){
	    gameBoard[i][j] = ' ';
	  }
	}
  }
  
  private void addPiecesTest(){
    gameBoard[7][0] = 'r';
	gameBoard[7][1] = 'k';
	gameBoard[7][2] = 'b';
	gameBoard[7][3] = 'K';
	gameBoard[7][4] = 'Q';
	gameBoard[7][5] = 'b';
	gameBoard[7][6] = 'k';
	gameBoard[7][7] = 'r';
	gameBoard[0][0] = 'r';
	gameBoard[0][1] = 'k';
	gameBoard[0][2] = 'b';
	gameBoard[0][3] = 'K';
	gameBoard[0][4] = 'Q';
	gameBoard[0][5] = 'b';
	gameBoard[0][6] = 'k';
	gameBoard[0][7] = 'r';
  }
  
  /* loop through each player's pieces
  get the position of piece and representative character- set
  private void addPieces(){
  
  }*/
}