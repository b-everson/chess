import java.util.ArrayList;
public abstract class ChessPiece{
  protected int xPos;
  protected int yPos;
  private Player owner;
  protected Board gameBoard;
  private char pieceChar;
  private String playerInfo;
  
  public ChessPiece(char symbol, Player pOwner){
    pieceChar = symbol;
	owner = pOwner;
	playerInfo = pOwner.getDescription();
  }
  
  public abstract ArrayList<BoardPosition> checkMoveAvailable(); // return array of possible moves 
  public abstract void move();
  
  public int getX(){
    return xPos;
  }
  
  public int getY(){
    return yPos;
  }
  
  //get piece Owner to determine if piece is friendly or not
  public Player getOwner(){
    return owner;
  }
  //game piece's visual representation
  public String toString(){
    return " " + Character.toString(pieceChar) + playerInfo + " ";
  }
  
  //
  public char getCharacter(){
    return pieceChar;
  }
  
  //function called only when adding a piece to the board
  public void initialize(int x, int y){
    xPos = x;
	yPos = y;
  }
  
}