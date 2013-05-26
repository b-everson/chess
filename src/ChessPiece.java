public abstract class ChessPiece{
   
  protected int xPos;
  protected int yPos;
  
  public abstract int[][] checkMoveAvailable();
  public abstract void move();
  
  public int getX(){
    return xPos;
  }
  
  public int getY(){
    return yPos;
  }
  
}