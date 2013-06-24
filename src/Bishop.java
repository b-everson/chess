import java.util.ArrayList;
public class Bishop extends ChessPiece{
  private static final char BISHOP_CHAR = 'b';
  
  public Bishop(Player pOwner, Board bOwner){
    super(BISHOP_CHAR, pOwner, bOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
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
  
}