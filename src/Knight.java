public class Knight extends ChessPiece{
  private static final char KNIGHT_CHAR = 'k';
  
  public Knight(Player pOwner){
    super(KNIGHT_CHAR, pOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>;
	possibilities.add(new BoardPosition(1,1));
	return possibilities;
  }
  
  public void move(){
  
  }
}