import java.util.ArrayList;
public class Queen extends ChessPiece{
  private static final char QUEEN_CHAR = 'Q';
  
  public Queen(Player pOwner, Board bOwner){
    super(QUEEN_CHAR, pOwner, bOwner);
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