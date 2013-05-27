public abstract class ChessPiece{
   
  protected int xPos;
  protected int yPos;
  private Player owner;
  protected Board gameBoard;
  private char pieceChar;
  
  public ChessPiece(char symbol, Player pOwner){
    pieceChar = symbol;
	owner = pOwner;
  }
  
  public abstract ArrayList<BoardPosition> checkMoveAvailable(); // return array of possible moves 
  public abstract void move();
  
  public int getX(){
    return xPos;
  }
  
  public int getY(){
    return yPos;
  }
  
  public Player getOwner(){
    return owner;
  }
  
  public String toString(){
    return Character.toString(pieceChar);
  }
  
}