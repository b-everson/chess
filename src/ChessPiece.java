import java.util.ArrayList;
public abstract class ChessPiece{
  private Player owner;
  protected Board gameBoard;
  private char pieceChar;
  private String playerInfo;
  protected BoardPosition position = null;
  protected ArrayList<BoardPosition> vulnerablePositions;
  
  public ChessPiece(char symbol, Player pOwner, Board bOwner){
    pieceChar = symbol;
	owner = pOwner;
	playerInfo = pOwner.getDescription();
	gameBoard = bOwner;
	vulnerablePositions = new ArrayList<BoardPosition>();
  }
  
  public ArrayList<BoardPosition> getVulnerablePositions(){
    return vulnerablePositions;
  }
  
  public ArrayList<BoardPosition> checkValidMoves(){
    ArrayList<BoardPosition> moves = new ArrayList<BoardPosition>();
	for (BoardPosition iPosition: checkMoveAvailable()){ // add positions from checkmove available to other arraylist
	  moves.add(iPosition);
	}
    
	for(int i = 0; i < moves.size(); i++){
	  if(!testMove(moves.get(i))){ //if moving to this position results in test = false
	    moves.set(i,null);
	  }
	}
	moves.trimToSize();
	return moves;
  }
  
  public void setVulnerablePositions(){
    vulnerablePositions = checkMoveAvailable();
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
	
	if(!checkValidMoves().contains(position)){
	  canMove = false;
	  message = "Cannot move your king into check.";
	}
	if(canMove){
	  position.setPiece(this);   //set position's piece reference to this object
	  this.setPosition(position); //set BoardPosition reference to position 
	  setVulnerablePositions();
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
	vulnerablePositions.clear();
  }
  
  public void setPosition(BoardPosition position){
    if(this.position != null)
	  this.position.setPiece(null);
	position.setPiece(this);
	this.position = position;
	setVulnerablePositions();
  }
  
  protected ArrayList<ChessPiece> isVulnerable(BoardPosition boardPosition){   
	ArrayList<ChessPiece> vitalPieces = new ArrayList<ChessPiece>();
	ArrayList<ChessPiece> pieces = gameBoard.getOtherPlayer(owner).getPieces();   //loop through other players pieces, if they 
	for (ChessPiece piece: pieces){   	//can move to this position it is vulnerable	   
      if (piece.getVulnerablePositions().contains(boardPosition)){ 
        vitalPieces.add(piece);
	  }	
	}
	return vitalPieces;
  }
  
  //this function not working as intended, position still has old piece in it when evaluateCheck() is called
  protected boolean testMove(BoardPosition position){
    ChessPiece testChessPiece = position.getPiece();   //save chesspiece in position intending to move to
	BoardPosition originalPosition = this.getPosition();  //save original position to undo  test
	position.setPiece(this); //set piece of position to this piece
	this.setPosition(position); //set position of this piece to position
	gameBoard.update();
	
	//if move results in player going into check then move is invalid
	boolean goodMove = !owner.evaluateCheck();
	if(testChessPiece != null){                     
	  position.setPiece(testChessPiece);   //return testchesspiece to position
	  testChessPiece.setPosition(position); 
	}  
	originalPosition.setPiece(this);   //return this piece to orinal position
	this.setPosition(originalPosition);
	gameBoard.update();
	owner.evaluateCheck(); 
	return goodMove;
  }
  
}