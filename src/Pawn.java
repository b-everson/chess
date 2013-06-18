import java.util.ArrayList;
public class Pawn extends ChessPiece{
  private static final char PAWN_CHAR = 'p';
  private int direction = -1;
  boolean firstMove = true;
  
  public Pawn(Player pOwner, Board bOwner){
    super(PAWN_CHAR, pOwner, bOwner);
	if(!pOwner.getDescription().equals("(p1)")){
	  direction = 1;
	}
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
    possibilities.add(gameBoard.getBoardPosition(position.getX(),position.getY() + 1 * direction));
	if(firstMove)
	  possibilities.add(gameBoard.getBoardPosition(position.getX(),position.getY() + 2 * direction));
	return possibilities;
  }
  
  public void setPosition(BoardPosition position){
    this.position = position;
  }
  
  public void move(BoardPosition position){
    if(checkMoveAvailable().contains(position))
	{
	  this.setPosition(position);
	}
  }
}