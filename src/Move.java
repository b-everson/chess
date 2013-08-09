public class Move{
  protected BoardPosition startingPosition;
  protected BoardPosition endingPosition;
  protected ChessPiece startingPiece;
  protected ChessPiece endingPiece;
  
  public BoardPosition getEndPosition(){
    return endingPosition;
  }
  
  public BoardPosition getStartPosition(){
    return startingPosition;
  }
  
  public ChessPiece getStartPiece(){
    return startingPiece;
  }
  
  public ChessPiece getEndPiece(){
    return endingPiece;
  }
  
  public Move(ChessPiece piece, BoardPosition end){
    this(piece.getPosition(), end);
  }
  
  public Move(BoardPosition start, BoardPosition end){
	startingPosition = start;
	endingPosition = end;
	startingPiece = start.getPiece();    
	if(endingPosition.getPiece() != null){
	  endingPiece = endingPosition.getPiece();
	}  
  }
  
  public void perform(){
    //clear startingPosition piece, set endingPosition piece to startingPosition piece
	startingPosition.clearPiece();
	startingPiece.setPosition(endingPosition);
	startingPiece.setVulnerablePositions();
	//
  }
  
  public void undo(){
    if(startingPiece != null){
	  startingPosition.setPiece(startingPiece);
	  startingPiece.setPosition(startingPosition);
	  startingPiece.setVulnerablePositions();
	}  
	if(endingPiece != null){
	  endingPosition.setPiece(endingPiece);
	  endingPiece.setPosition(endingPosition);
	  startingPiece.setVulnerablePositions();
	}  
  }
  
  //want to return Player 1 or Player 2 moves (piece name) from (position x coord,
  //position y coord) to (position x coord, position y coord)
  public String toString(){
	String player = startingPosition.getPiece().getOwner().getDescription();
	String pieceName = startingPosition.getPiece().getType();
	String startingCoords = "(" + (startingPosition.getXCoord() + 1) + ", " + (startingPosition.getYCoord() + 1) + ")";
	String endingCoords = "(" + (endingPosition.getXCoord() + 1) + ", " + (endingPosition.getYCoord() + 1) + ")";
	return player + " moves " + pieceName + " from " + startingCoords + " to " + endingCoords;
  }
  
  //A move is the same if a piece moves from one position to another, resulting 
  //in the same piece being removed
  public boolean equals(Move other){
    return this.endingPosition == other.getEndPosition() &&
	    this.startingPosition == other.getStartPosition() &&
		this.endingPiece == other.getEndPiece() &&
		this.startingPiece == other.getStartPiece();
  }
}