public class Game{
  public static void main(String[] args){
	boolean playing = true;
	Board chessBoard = new Board();
	HumanPlayer testPlayer1 = new HumanPlayer(chessBoard);
	HumanPlayer testPlayer2 = new HumanPlayer(chessBoard);
	chessBoard.initialize((Player)testPlayer1,(Player)testPlayer2);
	chessBoard.drawBoard();
	Player nextPlayer = testPlayer1;
	do{
	nextPlayer.takeTurn();
	chessBoard.drawBoard();
	if (nextPlayer == testPlayer1){
	  nextPlayer = testPlayer2;
	}else{
	  nextPlayer = testPlayer1;
	}
	} while (playing);
  }
}