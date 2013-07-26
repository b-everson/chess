import java.util.ArrayList;
public class Queen extends ChessPiece{
  private static final char QUEEN_CHAR = 'Q';
  private static final int QUEEN_VALUE = 9;
  private static final String QUEEN_NAME = "queen";
  
  public Queen(Player pOwner, Board bOwner){
    super(QUEEN_CHAR, pOwner, bOwner, QUEEN_VALUE, QUEEN_NAME);
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
	

	//Add each available position extending y++, x++  check position add empty, enemy then break, break on friendly
	//Do this until position isn't valid board position
	boolean done = false;
	int x = position.getXCoord() + 1;
	int y = position.getYCoord() + 1;
	do{
	  BoardPosition checkPosition; 
	  if( x > Board.BOARD_WIDTH || y > Board.BOARD_HEIGHT )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(new Move(this, checkPosition));
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(new Move(this, checkPosition));
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
	x = position.getXCoord() - 1;
	y = position.getYCoord() + 1;
	do{
	  BoardPosition checkPosition; 
	  if( x < 0 || y > Board.BOARD_HEIGHT )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(new Move(this, checkPosition));
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(new Move(this, checkPosition));
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
	x = position.getXCoord() - 1;
	y = position.getYCoord() - 1;
	do{
	  BoardPosition checkPosition; 
	  if( x < 0 || y < 0 )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(new Move(this, checkPosition));
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(new Move(this, checkPosition));
		  done = true;
	    }else //position must be on the board, empty, occupied by enemy, or only remaining option is occupied by friendly
	    done = true;
	    x -= 1; 
	    y -= 1; 
	  }
	} while (!done);//Add each available position extending y--, x++  check position add empty, enemy then break, break on friendly
	//Do this until position isn't valid board position
	done = false;
	x = position.getXCoord() + 1;
	y = position.getYCoord() - 1;
	do{
	  BoardPosition checkPosition; 
	  if( y < 0 || x > Board.BOARD_WIDTH )
	    done = true;
	  else {
	    checkPosition = gameBoard.getBoardPosition(x,y);
		if(!checkPosition.isOccupied()){
	      possibilities.add(new Move(this, checkPosition));
	    }else if (checkPosition.occupiedByEnemy(this)){
	      possibilities.add(new Move(this, checkPosition));
		  done = true;
	    }else //position must be on the board, empty, occupied by enemy, or only remaining option is occupied by friendly
	    done = true;
	    x += 1; 
	    y -= 1; 
	  }
	} while (!done);
	
	return possibilities;
  }
  
}