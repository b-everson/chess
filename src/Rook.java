import java.util.ArrayList;
public class Rook extends ChessPiece{
  private static final char ROOK_CHAR = 'r';
  private static final int ROOK_VALUE = 5;
  
  public Rook(Player pOwner, Board bOwner){
    super(ROOK_CHAR,pOwner, bOwner, ROOK_VALUE);
  }
  
 
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