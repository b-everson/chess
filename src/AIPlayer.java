import java.util.ArrayList;
public class AIPlayer extends Player{
  
  public Move bestMove(){
    MoveEvaluation move = negMax(3, this, getBoard().getOtherPlayer(this));
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
  
  public MoveEvaluation negMax(int depth,Player player,Player opponent){
    ArrayList<Move> validMoves = player.validMoves();
	MoveEvaluation best = new MoveEvaluation(null,-1000);  //best starts out at minimum
	if(depth == 0 || validMoves.size() == 0){
	  return new MoveEvaluation(null, player);  //player evaluates board (opponent for odd ply, player for even)
	}
	
	for (Move move : validMoves){  //for every move that is valid
	  move.perform();
	  MoveEvaluation nextEval = negMax(depth - 1, opponent, player);
	  int score = -nextEval.getScore();
	  move.undo();
	  if(score > best.getScore()){
	    best = new MoveEvaluation(move, score);
	  }
	}
	return best;
  }
  
  public void takeTurn(){
    Game.setMessage("Computer player determining move.");
	getBoard().enabled(false);
	Move move = bestMove();

	move.getStartPiece().move(move.getEndPosition());
	getBoard().enabled(true);
	getBoard().toggleActivePlayer();
  }
}