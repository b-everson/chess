import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class Game{

  private static JLabel message;
  public static Random random;
  
  public static void setMessage(String text){
    message.setText(text);
  }

  public static void main(String[] args){
	random = new Random();
	boolean playing = true;
	JFrame frame = new JFrame("Chess");	
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	message = new JLabel("Welcome");
	Board chessBoard = new Board();
	//AIPlayer testPlayer1 = new AIPlayer(chessBoard);
	HumanPlayer testPlayer1 = new HumanPlayer(chessBoard);
	AIPlayer testPlayer2 = new AIPlayer(chessBoard);
	//HumanPlayer testPlayer2 = new HumanPlayer(chessBoard);
	chessBoard.initialize((Player)testPlayer1,(Player)testPlayer2);
	chessBoard.drawBoard();
	Player nextPlayer = testPlayer1;
	boolean checkMate = false;
	boolean staleMate = false;
	JPanel parent = new JPanel();
	JPanel p2 = new JPanel();
	parent.setLayout(new BorderLayout());
	p2.add(chessBoard.getPanel());
	parent.add(p2, BorderLayout.CENTER);
	
	parent.add(message, BorderLayout.SOUTH);
	frame.add(parent);
	frame.pack();
	frame.setMinimumSize(new Dimension(660,500));
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	do{
	  nextPlayer.takeTurn();
	  chessBoard.update();
	  chessBoard.drawBoard();
	  if (nextPlayer == testPlayer1){
	    nextPlayer = testPlayer2;
	  }else{
	    nextPlayer = testPlayer1;
	  }
	  checkMate = nextPlayer.evaluateCheckMate();
	  staleMate = nextPlayer.evaluateStaleMate();
	} while (!checkMate && !staleMate);
	String winner;
	String loser;
	if(nextPlayer == testPlayer1){
	  winner = "Player 2";
	  loser = "Player 1";
	}else{
	  winner = "Player 1";
	  loser = "Player 2";
	}
	if (checkMate){
	  setMessage("Checkmate " + loser + ", Congratulations " + winner);
	}else{
      setMessage("Stalemate " + loser + ", Congratulations " + winner);
    }	
  }
}