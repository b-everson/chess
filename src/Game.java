public class Game{
  public static void main(String[] args){
    HumanPlayer testPlayer1 = new HumanPlayer();
	HumanPlayer testPlayer2 = new HumanPlayer();
	Board chessBoard = new Board(testPlayer1,testPlayer2);
	chessBoard.drawBoard();
  }
}