public class MoveEvaluation{
  private Move myMove;
  private int myScore;
  
  public MoveEvaluation(Move move, Player player){
    myMove = move;
	myScore = player.evaluateBoard();
  }
  
  public MoveEvaluation(Move move, int score){
    myMove = move;
	myScore = score;
  }
  
  public Move getMove(){
    return myMove;
  }
  
  public int getScore(){
    return myScore;
  }
}