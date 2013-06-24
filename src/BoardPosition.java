public class BoardPosition{
  private int xCoord;
  private int yCoord;
  private ChessPiece currentPiece = null;
  private String contents = "       ";
  
  public BoardPosition(int x, int y){
    xCoord = x;
	yCoord = y;
  }
  
  public int getX(){
    return xCoord;
  }
  
  public int getY(){
    return yCoord;
  }
  
  public boolean isOccupied(){
	return (currentPiece != null);
  }
  
  public boolean occupiedByFriendly(ChessPiece incoming){
    boolean occupied = false;
	if(currentPiece != null){
	  if(incoming.getOwner() == currentPiece.getOwner())
	    occupied = true;
	}	
	return occupied;
  }
  
  public boolean occupiedByEnemy(ChessPiece incoming){   /*for a move, a piece must know if target is occupied by piece, valid moves cannot including moving one piece to a friendly piece*/
    boolean occupied = false;
	if(currentPiece != null){
	  if(incoming.getOwner() != currentPiece.getOwner())
	    occupied = true;
	}
	return occupied;
  }
  
  public void setPiece(ChessPiece piece){
    if(currentPiece != null)
	  currentPiece.setInactive();
	currentPiece = piece;
	if(currentPiece != null)
	  contents = currentPiece.toString();
	else
	  contents = "       ";
  }
  
  public void clearPiece(){
    currentPiece = null;
	contents = "       ";
  }
  
  public ChessPiece getPiece(){
    return currentPiece;
  }
 
  public String toString(){
    return contents;
  }
  
}