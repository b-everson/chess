public class Move{
  private BoardPosition startingPosition;
  private BoardPosition endingPosition;
  private ChessPiece startingPiece;
  private ChessPiece endingPiece;
  
  public BoardPosition getEndPosition(){
    return endingPosition;
  }
  
  public BoardPosition getStartPosition(){
    return startingPosition;
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
	//
  }
  
  public void undo(){
    if(startingPiece != null){
	  startingPosition.setPiece(startingPiece);
	  startingPiece.setPosition(startingPosition);
	}  
	if(endingPiece != null){
	  endingPosition.setPiece(endingPiece);
	  endingPiece.setPosition(endingPosition);
	}  
  }
}