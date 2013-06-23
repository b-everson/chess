import java.util.ArrayList;
public class King extends ChessPiece{
  private static final char KING_CHAR = 'K';
  
  public King(Player pOwner, Board bOwner){
    super(KING_CHAR, pOwner, bOwner);
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
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
		//  if(!isVulnerable(positions[i]))   
		    possibilities.add(positions[i]);
		}
	  }
	}
	return possibilities;
  }
  
  public boolean move(BoardPosition position){
    if(isVulnerable(position)){
	  System.out.println("Cannot move your king into check.");
	  return false;
	}  
	return super.move(position);
  }
}