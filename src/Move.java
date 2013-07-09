public class Move{
  private BoardPosition startingPosition;
  private BoardPosition endingPosition;
  private ChessPiece startingPiece;
  private ChessPiece endingPiece;
  
  private int score = 0;
  
  public int getScore(){
    return score;
  }
  
  public BoardPosition getEndPosition(){
    return endingPosition;
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
	  score = endingPosition.getPiece().getValue();
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