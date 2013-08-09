public class SpecialMove extends Move{
  private BoardPosition specialStartPosition;
  private BoardPosition specialEndPosition;
  private ChessPiece specialPiece;
 
 public BoardPosition getSpecialStartPosition(){
   return specialStartPosition;
 }
 
  public BoardPosition getSpecialEndPosition(){
   return specialEndPosition;
 }
 
 public ChessPiece getSpecialPiece(){
   return specialPiece;
 }
  
  public SpecialMove(ChessPiece piece, BoardPosition end, ChessPiece otherPiece, BoardPosition otherEnd){
     super(piece,end);
	specialPiece = otherPiece;
	specialStartPosition = otherPiece.getPosition();
	specialEndPosition = otherEnd;  
  }
  
  public SpecialMove(BoardPosition start, BoardPosition end, BoardPosition otherStart, BoardPosition otherEnd){
    super(start,end);
	specialStartPosition = otherStart;
	specialPiece = otherStart.getPiece();
	specialEndPosition = otherEnd;	
  }
  
  public void perform(){
    specialStartPosition.clearPiece();
	specialPiece.setPosition(specialEndPosition);
	super.perform();
  }
  
  public void undo(){
	  specialEndPosition.clearPiece();
	  specialPiece.setPosition(specialStartPosition);
	  super.undo();
  }
}