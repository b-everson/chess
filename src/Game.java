public class Game{
  public static void main(String[] args){
	Board chessBoard = new Board();
	HumanPlayer testPlayer1 = new HumanPlayer(chessBoard);
	HumanPlayer testPlayer2 = new HumanPlayer(chessBoard);
	chessBoard.initialize((Player)testPlayer1,(Player)testPlayer2);
	chessBoard.drawBoard();
	testPlayer1.takeTurn();
	testPlayer2.takeTurn();
	chessBoard.drawBoard();
  }
}