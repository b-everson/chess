import java.util.ArrayList;
public class Pawn extends ChessPiece{
  private static final char PAWN_CHAR = 'p';
  private int direction = -1;
  boolean firstMove = true;
  private static final int PAWN_VALUE = 1;
  private static final String PAWN_NAME = "pawn";
  
  public int getDirection(){
    return direction;
  }
  
  public void setVulnerablePositions(){
    ArrayList<BoardPosition> possibilities =  new ArrayList<BoardPosition>();
	BoardPosition pos1 = gameBoard.getBoardPosition(position.getXCoord() + 1, position.getYCoord() + 1 * direction); //check 1 position forward based on direction, one to right on board orientation
    if(pos1 != null){ 	
	  possibilities.add(pos1);
	}  
	BoardPosition pos2 = gameBoard.getBoardPosition(position.getXCoord() -1, position.getYCoord() + 1 * direction); //check 1 position forward based on direction, one to left on board orientation
	if(pos2 != null){
	  possibilities.add(pos2);
    }	
	vulnerablePositions = possibilities;
  }
  
  public Pawn(Player pOwner, Board bOwner){
    super(PAWN_CHAR, pOwner, bOwner, PAWN_VALUE, PAWN_NAME);
	if(pOwner.getDescription().equals("(p1)")){
	  direction = 1;
	}
  }
  
  public ArrayList<Move> checkMoveAvailable(){   //get list of board positions 
	ArrayList<Move> possibilities = new ArrayList<Move>();
    BoardPosition pos1 = gameBoard.getBoardPosition(position.getXCoord(),position.getYCoord() + 1 * direction); //check one position forward of pawn based on direction
    if(pos1 != null){	//if position is not within array values of board positions gameBoard.getBoardPosition returns null     
	if (!pos1.isOccupied()){ //only add this position if space 1 position forward is empty
	    possibilities.add(new Move(this,pos1));
	  }
	}  
	BoardPosition pos2 = gameBoard.getBoardPosition(position.getXCoord(), position.getYCoord() + 2 * direction); //check 2 positions forward of pawn based on position if pawn's first move
	if(pos2 != null){  
	  if(firstMove && !pos2.isOccupied() && !pos1.isOccupied()){  // only add this position if space 2 positions forward is empty and it is pawn's first move
		possibilities.add(new Move(this, pos2));
	  }
	}
    BoardPosition pos3 = gameBoard.getBoardPosition(position.getXCoord() + 1, position.getYCoord() + 1 * direction); //check 1 position forward based on direction, one to right on board orientation
    if(pos3 != null){ 	
	  if(pos3.occupiedByEnemy(this)) //only add if this position occupied by enemy
	    possibilities.add(new Move(this, pos3));
	}  
	BoardPosition pos4 = gameBoard.getBoardPosition(position.getXCoord() -1, position.getYCoord() + 1 * direction); //check 1 position forward based on direction, one to left on board orientation
	if(pos4 != null){
	  if(pos4.occupiedByEnemy(this)) //only add if this position occupied by enemy
	    possibilities.add(new Move(this,pos4));
    }	
	return possibilities;
  }
  
  //pawn's vulnerable positions are unique in that they differ from regular available moves
  public boolean move(BoardPosition position){
    boolean moveTrue = super.move(position);  
	if(moveTrue){
	  firstMove = false;
	  setVulnerablePositions();  
	}
	return moveTrue;
  }
  
  public boolean isDoubled(){
    boolean doubled = false;
	int column = getPosition().getXCoord();
	for (int i = 0; i < Board.BOARD_HEIGHT; i++){
	  BoardPosition nextPosition = gameBoard.getBoardPosition(column,i);
	  if(nextPosition == this.getPosition()){
	    continue;
	  }else{
	    if (nextPosition.getPiece() instanceof Pawn){
          if(nextPosition.occupiedByFriendly(this)){
		    doubled = true;
		  }
        }		
	  }
	}
	return doubled;
  }
  
  public boolean isBackward(){
    boolean backward = false;
	BoardPosition nextPosition = gameBoard.getBoardPosition(getPosition().getXCoord(), getPosition().getYCoord() + 1 * direction);
	if(nextPosition != null){
	  if(this.isVulnerable(nextPosition).size() > 0){
	    backward = true;
	  }
	}
	return backward;
  }
  
  /*
    if there are no friendly pawns on either column next to the pawn then it is
	considered isolated
  */
  public boolean isIsolated(){
    boolean isolated = true;
	for(int i = 0; i < Board.BOARD_HEIGHT; i++){
	  BoardPosition leftSide = gameBoard.getBoardPosition(getPosition().getXCoord()-1,i);
	  if(leftSide != null){  
		if(leftSide.occupiedByFriendly(this)){
	      if(leftSide.getPiece() instanceof Pawn){
	        isolated = false;
		    break;
	      }
	    }
	  }	
	}
    
	for(int j = 0; j < Board.BOARD_HEIGHT; j++){
	  BoardPosition rightSide = gameBoard.getBoardPosition(getPosition().getXCoord()+1,j);  
	  if(rightSide != null){  
		if(rightSide.occupiedByFriendly(this)){
	      if(rightSide.getPiece() instanceof Pawn){
		    isolated = false;
		    break;
		  }
	    }
	  }
	}
	return isolated;
  }
  
}