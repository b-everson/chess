import java.util.ArrayList;
public class Bishop extends ChessPiece{
  private static final char BISHOP_CHAR = 'b';
  private static final int BISHOP_VALUE = 3;
  private static final String BISHOP_NAME = "bishop";
  
  public Bishop(Player pOwner, Board bOwner){
    super(BISHOP_CHAR, pOwner, bOwner, BISHOP_VALUE, BISHOP_NAME);
  }
  
  public ArrayList<Move> checkMoveAvailable(){
    ArrayList<Move> possibilities = new ArrayList<Move>();
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