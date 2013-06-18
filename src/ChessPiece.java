import java.util.ArrayList;
public abstract class ChessPiece{
  private Player owner;
  protected Board gameBoard;
  private char pieceChar;
  private String playerInfo;
  protected BoardPosition position = null;

  
  public ChessPiece(char symbol, Player pOwner, Board bOwner){
    pieceChar = symbol;
	owner = pOwner;
	playerInfo = pOwner.getDescription();
	gameBoard = bOwner;
  }
  
  public abstract ArrayList<BoardPosition> checkMoveAvailable(); // return array of possible moves 
  public abstract void move(BoardPosition position);
  
  public BoardPosition getPosition(){
    return position;
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
  public void initialize(BoardPosition position){
    this.position = position;
  }
  
}