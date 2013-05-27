public class BoardPosition{
  private int xCoord;
  private int yCoord;
  private ChessPiece currentPiece = null;
  private char contents = ' ';
  
  public BoardPosition(int x, int y){
    xCoord = x;
	yCoord = y;
  }
  
  public int getXCoord(){
    return xCoord;
  }
  
  public int getYCoord(){
    return yCoord;
  }
  
  public boolean isOccupied(){
	return (currentPiece != null);
  }
  
  public boolean occupiedByEnemy(ChessPiece incoming){   /*for a move, a piece must know if target is occupied by piece, valid moves cannot including moving one piece to a friendly piece*/
    return(incoming.getOwner() != currentPiece.getOwner());
  }
  
  public void clearPosition(){
    currentPiece = null;
	contents = ' ';
  }
  
  public String toString(){
    return Character.toString(contents);
  }
  
}