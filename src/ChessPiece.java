import java.util.ArrayList;
public abstract class ChessPiece{
  private Player owner;
  protected Board gameBoard;
  private char pieceChar;
  private String playerInfo;
  protected BoardPosition position = null;
  protected ArrayList<BoardPosition> vulnerablePositions;
  private int value;
  
  public ChessPiece(char symbol, Player pOwner, Board bOwner, int pieceValue){
    pieceChar = symbol;
	owner = pOwner;
	playerInfo = pOwner.getDescription();
	gameBoard = bOwner;
	vulnerablePositions = new ArrayList<BoardPosition>();
	value = pieceValue;
  }
  
  public ArrayList<BoardPosition> getVulnerablePositions(){
    return vulnerablePositions;
  }
  
  public ArrayList<Move> checkValidMoves(){
    ArrayList<Move> moves = new ArrayList<Move>();
	if(position != null){
	  for (Move iMove: checkMoveAvailable()){ // add positions from checkmove available to other arraylist
	    moves.add(iMove);
	  }
	  
	  for(int i = moves.size()-1; i >= 0; i--){
	    if(!testMove(moves.get(i))){ //if moving to this position results in test = false
	      moves.remove(i);   
	    }
	  }
	}  
	moves.trimToSize();
	return moves;
  }
  
  public void setVulnerablePositions(){
    vulnerablePositions.clear();
	for (Move iMove : checkMoveAvailable()){
	  vulnerablePositions.add(iMove.getEndPosition());
	}
  }
  
  public int getValue(){
    return value;
  }
  
  public abstract ArrayList<Move> checkMoveAvailable(); // return array of possible moves 
  
  public boolean move(BoardPosition position){
	boolean canMove = true;
	String message = "";
	
	if(this.position != null){                        //move invalid if position already has player's piece in it
	  if (position.occupiedByFriendly(this)) {
	    canMove = false;
	    message = "Cannot move into a space occupied by your own piece.";
	  }
      
	  boolean validMove = false;
	  for(Move nextMove : checkValidMoves()){
	    if(nextMove.getEndPosition() == position){
		  validMove = true;
		}
	  }
	  
	  if(!validMove){
	    canMove = false;
	    message = "Cannot make a move that puts your king into check.";
	  }	  
	  
	  	  boolean moveFound = false;
	  for(Move iMove : checkMoveAvailable()){
        if(iMove.getEndPosition() == position){
		  moveFound = true;
		}
      }	  
	  
	  if(!moveFound)  //move invalid if not in list of available moves 
	  {
	    canMove = false;
	    message = "Not a valid move.";
	  }
	}

	if(canMove){
	  Move myMove = new Move(this, position);
	  myMove.perform();	  
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
	position.setPiece(this);
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
      if(piece.getPosition() != null){
	    for(Move thisMove : piece.checkMoveAvailable()){
		  if(boardPosition == thisMove.getEndPosition()){
            vitalPieces.add(piece);
		  }
		}  
	  }	
	}
	return vitalPieces;
  }
  
  
  /*
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
  }*/
  
  protected boolean testMove(Move tMove){
    tMove.perform();
	gameBoard.update();
	boolean goodMove = !owner.evaluateCheck();
	tMove.undo();
	gameBoard.update();
	return goodMove;
  }
  
  public boolean isActive(){
    return position != null;
  }
}