import java.util.ArrayList;
import javax.swing.*;
public abstract class ChessPiece{
  private Player owner;
  protected Board gameBoard;
  private char pieceChar;
  private String playerInfo;
  protected BoardPosition position = null;
  protected ArrayList<BoardPosition> vulnerablePositions;
  private int value;
  private ImageIcon pieceIcon;
  private String type;
  
  public ImageIcon getIcon(){
    return pieceIcon;
  }
  
  public String getType(){
    return type;
  }
  
  public Board getBoard(){
    return gameBoard;
  }
  
  public ChessPiece(char symbol, Player pOwner, Board bOwner, int pieceValue, String type){
    pieceIcon = new ImageIcon(".\\chesspieces\\" + pOwner.getType() + type + ".png");
	this.type = type;
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
  
  private void setAttack(BoardPosition position){
    position.addAttackingPiece(this);
  }
  
  private void removeAttack(BoardPosition position){
    position.removeAttackingPiece(this);
  }
  
  private void clearVulnerablePositions(){
    for (int i = vulnerablePositions.size() - 1; i >= 0; i--){
	  removeAttack(vulnerablePositions.get(i));
	  vulnerablePositions.remove(i);
	}
  }
  
  public void setVulnerablePositions(){
    clearVulnerablePositions();
	for (Move iMove : checkMoveAvailable()){
	  vulnerablePositions.add(iMove.getEndPosition());
	  setAttack(iMove.getEndPosition());
	}
  }
  
  public int getValue(){
    return value;
  }
  
  public abstract ArrayList<Move> checkMoveAvailable(); // return array of possible moves 
  
  public boolean move(BoardPosition position){
	boolean canMove = true;
	String message = "";
	Move chosenMove = null;
	
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
		  chosenMove = iMove;
		}
      }	  
	  
	  if(!moveFound)  //move invalid if not in list of available moves 
	  {
	    canMove = false;
	    message = "Not a valid move.";
	  }
	}

	if(canMove){
	  Move myMove = chosenMove;
	  Game.setMessage(myMove.toString());
	  myMove.perform();	 
      //Game.setMessage(myMove.toString());	  
	}  
	else
	  Game.setMessage(message);
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
	clearVulnerablePositions();
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