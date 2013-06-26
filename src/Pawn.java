import java.util.ArrayList;
public class Pawn extends ChessPiece{
  private static final char PAWN_CHAR = 'p';
  private int direction = -1;
  boolean firstMove = true;
  
  public int getDirection(){
    return direction;
  }
  
  protected void setAvailableMoves(){
    ArrayList<BoardPosition> possibilities =  new ArrayList<BoardPosition>();
	BoardPosition pos1 = gameBoard.getBoardPosition(position.getX() + 1, position.getY() + 1 * direction); //check 1 position forward based on direction, one to right on board orientation
    if(pos1 != null){ 	
	  possibilities.add(pos1);
	}  
	BoardPosition pos2 = gameBoard.getBoardPosition(position.getX() -1, position.getY() + 1 * direction); //check 1 position forward based on direction, one to left on board orientation
	if(pos2 != null){
	  possibilities.add(pos2);
    }	
	vulnerablePositions = possibilities;
  }
  
  public Pawn(Player pOwner, Board bOwner){
    super(PAWN_CHAR, pOwner, bOwner);
	if(pOwner.getDescription().equals("(p1)")){
	  direction = 1;
	}
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){   //get list of board positions 
	ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
    BoardPosition pos1 = gameBoard.getBoardPosition(position.getX(),position.getY() + 1 * direction); //check one position forward of pawn based on direction
    if(pos1 != null){	//if position is not within array values of board positions gameBoard.getBoardPosition returns null     
	if (!pos1.isOccupied()){ //only add this position if space 1 position forward is empty
	    possibilities.add(pos1);
	  }
	}  
	BoardPosition pos2 = gameBoard.getBoardPosition(position.getX(), position.getY() + 2 * direction); //check 2 positions forward of pawn based on position if pawn's first move
	if(pos2 != null){  
	  if(firstMove && !pos2.isOccupied()){  // only add this position if space 2 positions forward is empty and it is pawn's first move
	    possibilities.add(pos2);
	  }
	}
    BoardPosition pos3 = gameBoard.getBoardPosition(position.getX() + 1, position.getY() + 1 * direction); //check 1 position forward based on direction, one to right on board orientation
    if(pos3 != null){ 	
	  if(pos3.occupiedByEnemy(this)) //only add if this position occupied by enemy
	    possibilities.add(pos3);
	}  
	BoardPosition pos4 = gameBoard.getBoardPosition(position.getX() -1, position.getY() + 1 * direction); //check 1 position forward based on direction, one to left on board orientation
	if(pos4 != null){
	  if(pos4.occupiedByEnemy(this)) //only add if this position occupied by enemy
	    possibilities.add(pos4);
    }	
	return possibilities;
  }
  
  public boolean move(BoardPosition position){
    boolean moveTrue = super.move(position);
	if(moveTrue){
	  firstMove = false;
	  setAvailableMoves();
	}
	return moveTrue;
  }
  
}