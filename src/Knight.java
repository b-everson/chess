import java.util.ArrayList;
public class Knight extends ChessPiece{
  private static final char KNIGHT_CHAR = 'k';
  
  public Knight(Player pOwner, Board bOwner){
    super(KNIGHT_CHAR, pOwner, bOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
	
	return possibilities;
  }
  
  public boolean move(BoardPosition position){
    return false;
  }
}