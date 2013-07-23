import javax.swing.*;
import java.awt.*;
public class Game{
  public static void main(String[] args){
	boolean playing = true;
	JFrame frame = new JFrame("Chess");	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Board chessBoard = new Board();
	HumanPlayer testPlayer1 = new HumanPlayer(chessBoard);
	AIPlayer testPlayer2 = new AIPlayer(chessBoard);
	//HumanPlayer testPlayer2 = new HumanPlayer(chessBoard);
	chessBoard.initialize((Player)testPlayer1,(Player)testPlayer2);
	chessBoard.drawBoard();
	Player nextPlayer = testPlayer1;
	boolean checkMate = false;
	JPanel parent = new JPanel();
	JPanel p2 = new JPanel();
	parent.setLayout(new BorderLayout());
	p2.add(chessBoard.getPanel());
	parent.add(p2, BorderLayout.CENTER);
	parent.add(new JLabel("Messages Go Here"), BorderLayout.SOUTH);
	frame.add(parent);
	frame.pack();
	frame.setMinimumSize(new Dimension(660,500));
	frame.setVisible(true);
	do{
	nextPlayer.takeTurn();
	chessBoard.drawBoard();
	if (nextPlayer == testPlayer1){
	  nextPlayer = testPlayer2;
	}else{
	  nextPlayer = testPlayer1;
	}
	checkMate = nextPlayer.evaluateCheckMate();
	} while (!checkMate);
	String winner;
	String loser;
	if(nextPlayer == testPlayer1){
	  winner = "Player 2";
	  loser = "Player 1";
	}else{
	  winner = "Player 1";
	  loser = "Player 2";
	}
	System.out.println("Checkmate " + loser + ", Congratulations " + winner);
  }
}