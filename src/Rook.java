import java.util.ArrayList;
public class Rook extends ChessPiece{
  private static final char ROOK_CHAR = 'r';
  
  public Rook(Player pOwner, Board bOwner){
    super(ROOK_CHAR,pOwner, bOwner);
  }
  
 /* public boolean move(BoardPosition position){
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
  }*/
  
  public ArrayList<BoardPosition> checkMoveAvailable(){  
	ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
	//Add each available position extending x --            check position add empty, add enemy then break,   break on friendly
	for(int x = position.getX() - 1; x >= 0; x--){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(x, position.getY());
	  if (!checkPosition.isOccupied()){
	     possibilities.add(checkPosition);
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(checkPosition);
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	
	//Add each available position extending x ++            check position add empty, add enemy then break,   break on friendly
	for(int x = position.getX() + 1; x <= Board.BOARD_WIDTH; x++){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(x, position.getY());
	  if (!checkPosition.isOccupied()){
	     possibilities.add(checkPosition);
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(checkPosition);
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	
		//Add each available position extending y --            check position add empty, add enemy then break,   break on friendly
	for(int y = position.getY() - 1; y >= 0; y--){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(position.getX(), y);
	  if (!checkPosition.isOccupied()){
	     possibilities.add(checkPosition);
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(checkPosition);
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	
	//Add each available position extending y ++            check position add empty, add enemy then break,   break on friendly
	for(int y = position.getY() + 1; y <= Board.BOARD_HEIGHT; y++){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(position.getX(), y);
	  if (!checkPosition.isOccupied()){
	     possibilities.add(checkPosition);
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(checkPosition);
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	

	return possibilities;
  }
  
}