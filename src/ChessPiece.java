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
  
  public void setInactive(){
    this.position = null;
  }
  
  public void setPosition(BoardPosition position){
    this.position = position;
  }
  
  protected boolean isVulnerable(BoardPosition boardPosition){   
	boolean vulnerable = false;
	ArrayList<ChessPiece> pieces = gameBoard.getOtherPlayer(owner).getPieces();   //loop through other players pieces, if they 
	for (ChessPiece piece: pieces){                                               //can move to this position it is vulnerable
	  if (piece.checkMoveAvailable().contains(boardPosition)){ 
	    vulnerable = true;
		break;
	  }	
	}
	return vulnerable;
  }
  
}