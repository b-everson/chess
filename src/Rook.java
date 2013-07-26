import java.util.ArrayList;
public class Rook extends ChessPiece{
  private static final char ROOK_CHAR = 'r';
  private static final int ROOK_VALUE = 5;
  private static final String ROOK_NAME = "rook";
  
  public Rook(Player pOwner, Board bOwner){
    super(ROOK_CHAR,pOwner, bOwner, ROOK_VALUE, ROOK_NAME);
  }
  
  public String getType(){
    return ROOK_NAME;
  }
 
  public ArrayList<Move> checkMoveAvailable(){  
	ArrayList<Move> possibilities = new ArrayList<Move>();
	//Add each available position extending x --            check position add empty, add enemy then break,   break on friendly
	for(int x = position.getXCoord() - 1; x >= 0; x--){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(x, position.getYCoord());
	  if (!checkPosition.isOccupied()){
	     possibilities.add(new Move(this, checkPosition));
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(new Move(this, checkPosition));
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	
	//Add each available position extending x ++            check position add empty, add enemy then break,   break on friendly
	for(int x = position.getXCoord() + 1; x <= Board.BOARD_WIDTH; x++){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(x, position.getYCoord());
	  if (!checkPosition.isOccupied()){
	     possibilities.add(new Move(this, checkPosition));
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(new Move(this, checkPosition));
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	
		//Add each available position extending y --            check position add empty, add enemy then break,   break on friendly
	for(int y = position.getYCoord() - 1; y >= 0; y--){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(position.getXCoord(), y);
	  if (!checkPosition.isOccupied()){
	     possibilities.add(new Move(this, checkPosition));
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(new Move(this, checkPosition));
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	
	//Add each available position extending y ++            check position add empty, add enemy then break,   break on friendly
	for(int y = position.getYCoord() + 1; y <= Board.BOARD_HEIGHT; y++){
	  BoardPosition checkPosition = gameBoard.getBoardPosition(position.getXCoord(), y);
	  if (!checkPosition.isOccupied()){
	     possibilities.add(new Move(this, checkPosition));
	  }else if (checkPosition.occupiedByEnemy(this)){
	    possibilities.add(new Move(this, checkPosition));
		break;  //break after adding first enemy piece
	  }else{
	    break;  //break on friendly piece 
	  }
	}  
	

	return possibilities;
  }
  
}