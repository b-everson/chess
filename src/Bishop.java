import java.util.ArrayList;
public class Bishop extends ChessPiece{
  private static final char BISHOP_CHAR = 'b';
  
  public Bishop(Player pOwner, Board bOwner){
    super(BISHOP_CHAR, pOwner, bOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
	possibilities.add(new BoardPosition(1,1));
	return possibilities;
  }
  
  public void move(BoardPosition position){
  
  }
}