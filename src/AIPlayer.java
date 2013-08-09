import java.util.ArrayList;
public class AIPlayer extends Player{

  private Move lastMove;
  private Move secondToLastMove;
  private int moveCounter = 0;
  
  private void setMoveList(Move thisMove){
    secondToLastMove = lastMove;
	lastMove = thisMove;
  }
  
  private boolean repeatMove(Move thisMove){
    if (secondToLastMove != null ){
	  return secondToLastMove.equals(thisMove);
	}
	else 
	  return true;
  }
  
  public Move bestMove(){
    MoveEvaluation move = alphabeta(3, this, getBoard().getOtherPlayer(this), -1000, 1000);
    //MoveEvaluation move = negMax(3, this, getBoard().getOtherPlayer(this));
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
  
  public MoveEvaluation alphabeta(int depth,Player player,Player opponent, int low, int high){
    ArrayList<Move> validMoves = new ArrayList<Move>();
	validMoves.addAll(player.validMoves());
	MoveEvaluation best = new MoveEvaluation(null,-1000);  //best starts out at minimum
	if(depth == 0 || validMoves.size() == 0){
	  return new MoveEvaluation(null, player);  //player evaluates board (opponent for odd ply, player for even)
	}
	
	for (Move move : validMoves){  //for every move that is valid
	  move.perform();
	  MoveEvaluation nextEval = alphabeta(depth - 1, opponent, player, -high, -low);
	  int score = nextEval.getScore();
	  move.undo();
	  if(-score > best.getScore()){
	    low = -score;
	    best = new MoveEvaluation(move, low);
		if (low >= high){
		  return best;
		}
	  }
	}
	return best;
  }
  
  private Move selectMove(){
    Move move; 
	int roll = Game.random.nextInt(101);
/*	if (roll <= 30){
	  move = bestMove();
	}else*/ if (roll <= 90){
	  move = goodMove(true);
	}else{
	  move = goodMove(false);
	}
	return move;
  }
  
  private Move goodMove(boolean good){
    ArrayList<Move> moves = validMoves();
	for (int i = moves.size() - 1; i >= 0; i --){//negative iteration to avoid concurrent modification
	  //perform move, get score, undo move. if move is negative remove from list
	  int preScore = evaluateBoard();
	  Move move = moves.get(i);
	  move.perform();
	  int postScore = evaluateBoard();
	  move.undo();
	  if(good){
	    if(preScore <= postScore){
		  moves.remove(i);
		}
	  }else{
	    if(preScore > postScore ){
		  moves.remove(i);
		}
	  }
	}
	moves.trimToSize();
	Move selectedMove = null;
	int selection;
	if(moves.size() < 1 ){
	  moves = validMoves(); //repopulate if there are 
	                        //no availalble moves to increase/decrease score
	}
	if(moves.size() > 1){
	  selection = Game.random.nextInt(moves.size() - 1);
	}else{
      selection = 0;
    }	
	selectedMove = moves.get(selection);
	return selectedMove;
  }
  
  public void takeTurn(){
    Game.setMessage("Computer player determining move.");
	getBoard().enabled(false);
	Move move;
    
	 /*want to select move, then test it to see if it is a repeat. 
	After move is proven to be not a repeat it can be performed and added 
	to the list of last couple moves*/
	do{
	  move = selectMove();
	} while (!repeatMove(move));
	move.getStartPiece().move(move.getEndPosition());
	
	getBoard().enabled(true);
	getBoard().toggleActivePlayer();
  }
}