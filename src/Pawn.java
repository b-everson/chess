public class Pawn extends ChessPiece{
  private static final char PAWN_CHAR = 'p';
  
  public Pawn(Player pOwner){
    super(PAWN_CHAR, pOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>;
	possibilities.add(new BoardPosition(1,1));
	return possibilities;
  }
  
  public void move(){
  
  }
}