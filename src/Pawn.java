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
    BoardPosition pos1 = gameBoard.getBoardPosition(position.getX(),position.getY() + 1 * direction);
    if(pos1 != null){	
	if (!pos1.isOccupied()){
	    possibilities.add(pos1);
	  }  
	}  
	BoardPosition pos2 = gameBoard.getBoardPosition(position.getX(), position.getY() + 2 * direction);
	if(pos2 != null){  
	  if(firstMove){
	    if(!pos2.isOccupied())
	      possibilities.add(pos2);
	  }
	}
    BoardPosition pos3 = gameBoard.getBoardPosition(position.getX() + 1, position.getY() + 1 * direction);
    if(pos3 != null){ 	
	  if(pos3.occupiedByEnemy(this))
	    possibilities.add(pos3);
	}  
	BoardPosition pos4 = gameBoard.getBoardPosition(position.getX() -1, position.getY() + 1 * direction);
	if(pos4 != null){
	  if(pos4.occupiedByEnemy(this))
	    possibilities.add(pos4);
    }	
	return possibilities;
  }
  
  public void setPosition(BoardPosition position){
    this.position = position;
  }
  
  public boolean move(BoardPosition position){
	boolean canMove = true;
	String message = "";
	
	if(position != null){
	  if (position.occupiedByFriendly(this)) {
	  canMove = false;
	  message = "Cannot move into a space occupied by your own piece.";
	  }
	}
	
	if(!checkMoveAvailable().contains(position) )
	{
	  canMove = false;
	  message = "Not a valid move.";
	}
		
	if(canMove){
	  this.setPosition(position);
	  position.setPiece(this);
	  firstMove = false;
	}  
	else
	  System.out.println(message);
	return canMove;
  }
}