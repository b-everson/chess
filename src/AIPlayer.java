import java.util.ArrayList;
public class AIPlayer extends Player{
  
  public Move bestMove(){
    MoveEvaluation move = minimax(3, this, getBoard().getOtherPlayer(this));
	return move.getMove();
  }
  
  public AIPlayer(Board board){
    super(board);
  }
  
  public MoveEvaluation minimax(int depth,Player player,Player opponent){
    ArrayList<Move> validMoves = player.validMoves();
	MoveEvaluation best = new MoveEvaluation(null,0);
	if(depth == 0 || validMoves.size() == 0){
	  return new MoveEvaluation(null, player.evaluateBoard());
	}
	
	for (Move move : validMoves){
	  move.perform();
	  validMoves = player.validMoves();
	  int score = player.evaluateBoard();
	  MoveEvaluation nextEval = minimax(depth - 1, opponent, player);
	  move.undo();
	  if(player == this){
	    if (best.getScore() <= score){
		  best = new MoveEvaluation(move,score);
		}
	  }else{
	    if (best.getScore() >= score ){
		  best = new MoveEvaluation(move,score);
		}
	  }
	}
	
	return best;
  }
  
  public void takeTurn(){
    Move move = bestMove();
	move.perform();
	
  }
}