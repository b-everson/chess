public class Bishop extends ChessPiece{
  private static final char BISHOP_CHAR = 'B';
  
  public Bishop(Player pOwner){
    super(BISHOP_CHAR, pOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>;
	possibilities.add(new BoardPosition(1,1));
	return possibilities;
  }
  
  public void move(){
  
  }
}