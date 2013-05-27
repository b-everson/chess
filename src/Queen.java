public class Queen extends ChessPiece{
  private static final char QUEEN_CHAR = 'Q';
  
  public Queen(Player pOwner){
    super(QUEEN_CHAR, pOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>;
	possibilities.add(new BoardPosition(1,1));
	return possibilities;
  }
  
  public void move(){
  
  }
}