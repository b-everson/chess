import java.util.ArrayList;
public class Queen extends ChessPiece{
  private static final char QUEEN_CHAR = 'Q';
  
  public Queen(Player pOwner, Board bOwner){
    super(QUEEN_CHAR, pOwner, bOwner);
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
	

	//Add each available position extending y++, x++  check position add empty, enemy then break, break on friendly
	//Do this until position isn't valid board position
	boolean done = false;
	int x = position.getX() + 1;
	int y = position.getY() + 1;
	do{
	  BoardPosition checkPosition; 
	  if( x > Board.BOARD_WIDTH || y > Board.BOARD_HEIGHT )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(checkPosition);
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(checkPosition);
		  done = true;
	    }else //position must be on the board, empty, occupied by enemy, or only remaining option is occupied by friendly
	    done = true;
	    x += 1; 
	    y += 1; 
	  }
	} while (!done);
	
	//Add each available position extending y++, x--  check position add empty, enemy then break, break on friendly
	//Do this until position isn't valid board position
	done = false;
	x = position.getX() - 1;
	y = position.getY() + 1;
	do{
	  BoardPosition checkPosition; 
	  if( x < 0 || y > Board.BOARD_HEIGHT )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(checkPosition);
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(checkPosition);
		  done = true;
	    }else //position must be on the board, empty, occupied by enemy, or only remaining option is occupied by friendly
	    done = true;
	    x -= 1; 
	    y += 1; 
	  }
	} while (!done);
	
	//Add each available position extending y--, x--  check position add empty, enemy then break, break on friendly
	//Do this until position isn't valid board position
	done = false;
	x = position.getX() - 1;
	y = position.getY() - 1;
	do{
	  BoardPosition checkPosition; 
	  if( x < 0 || y < 0 )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(checkPosition);
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(checkPosition);
		  done = true;
	    }else //position must be on the board, empty, occupied by enemy, or only remaining option is occupied by friendly
	    done = true;
	    x -= 1; 
	    y -= 1; 
	  }
	} while (!done);//Add each available position extending y--, x++  check position add empty, enemy then break, break on friendly
	//Do this until position isn't valid board position
	done = false;
	x = position.getX() + 1;
	y = position.getY() - 1;
	do{
	  BoardPosition checkPosition; 
	  if( y < 0 || x > Board.BOARD_WIDTH )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(checkPosition);
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(checkPosition);
		  done = true;
	    }else //position must be on the board, empty, occupied by enemy, or only remaining option is occupied by friendly
	    done = true;
	    x += 1; 
	    y -= 1; 
	  }
	} while (!done);
	
	
	
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