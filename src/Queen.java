import java.util.ArrayList;
public class Queen extends ChessPiece{
  private static final char QUEEN_CHAR = 'Q';
  
  public Queen(Player pOwner, Board bOwner){
    super(QUEEN_CHAR, pOwner, bOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
	return possibilities;
  }
  
  public boolean move(BoardPosition position){
    boolean canMove = true;
	String message = "";
	
	if(position != null){                        //move invalid if position already has player's piece in it
	  if (position.occupiedByFriendly(this)) {
	  canMove = false;
	  message = "Cannot move into a space occupied by your own piece.";
	  }
	}
	
	if(!checkMoveAvailable().contains(position) )  //move invalid if not in list of available moves 
	{
	  canMove = false;
	  message = "Not a valid move.";
	}
		
	if(canMove){
	  this.setPosition(position); //set BoardPosition reference to position 
	  position.setPiece(this);   //set position's piece reference to this object
	  }  
	else
	  System.out.println(message);
	return canMove;
  }
}