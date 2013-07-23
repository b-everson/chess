import java.util.ArrayList;
public class King extends ChessPiece{
  private static final char KING_CHAR = 'K';
  private static final int KING_VALUE = 100;
  private static final String KING_NAME = "king";
  
  public King(Player pOwner, Board bOwner){
    super(KING_CHAR, pOwner, bOwner, KING_VALUE, KING_NAME);
  }
  
  public ArrayList<Move> checkMoveAvailable(){
    ArrayList<Move> possibilities = new ArrayList<Move>();
	int x = position.getX();
	int y = position.getY();
	BoardPosition[] positions = new BoardPosition[8];
	positions[0] = gameBoard.getBoardPosition(x + 1, y);
	positions[1] = gameBoard.getBoardPosition(x - 1, y);
	positions[2] = gameBoard.getBoardPosition(x + 1, y + 1);
	positions[3] = gameBoard.getBoardPosition(x - 1, y + 1);
	positions[4] = gameBoard.getBoardPosition(x + 1, y - 1);
	positions[5] = gameBoard.getBoardPosition(x - 1, y - 1);
	positions[6] = gameBoard.getBoardPosition(x, y + 1);
	positions[7] = gameBoard.getBoardPosition(x, y - 1);
	for (int i = 0; i < positions.length; i++){
	  if(positions[i] != null){
		if(!positions[i].occupiedByFriendly(this)){
		  possibilities.add(new Move(this, positions[i]));  
		}
	  }
	}
	return possibilities;
  }
  
  public boolean move(BoardPosition position){
    if(isVulnerable(position).size() > 0){
	  System.out.println("Cannot move your king into check.");
	  return false;
	}  
	return super.move(position);
  }
}