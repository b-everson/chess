import java.util.ArrayList;
public class King extends ChessPiece{
  private static final char KING_CHAR = 'K';
  
  public King(Player pOwner, Board bOwner){
    super(KING_CHAR, pOwner, bOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
	possibilities.add(new BoardPosition(1,1));
	return possibilities;
  }
  
  public boolean move(BoardPosition position){
    return false;
  }
}