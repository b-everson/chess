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
	MoveEvaluation best;
    if(player == this){
      best	= new MoveEvaluation(null,-1000);
    }else{
      best = new MoveEvaluation(null, 1000);
    }	
	if(depth == 0 || validMoves.size() == 0){
	  return new MoveEvaluation(null, this);  
	}
	
	for (Move move : validMoves){  //for every move that is valid
	  move.perform();
	  MoveEvaluation nextEval = minimax(depth - 1, opponent, player);
	  int score = nextEval.getScore();
	  move.undo();
	  if(player == this){
	    if (best.getScore() < score){     //returns moves with most favorable score for AIPlayer
		  best = new MoveEvaluation(move,score);
		}
	  }else{
	    if (best.getScore() > score ){     //returns moves with lowest favorable score to opponent
		  best = new MoveEvaluation(move,score);
		}
	  }
	}
	return best;
  }
  
  public void takeTurn(){
    Move move = bestMove();

	move.getStartPiece().move(move.getEndPosition());
	
  }
}