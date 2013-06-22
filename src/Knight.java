import java.util.ArrayList;
public class Knight extends ChessPiece{
  private static final char KNIGHT_CHAR = 'k';
  
  public Knight(Player pOwner, Board bOwner){
    super(KNIGHT_CHAR, pOwner, bOwner);
  }
  
  //need positions 1 up 2 left, 1 up 2 right, 2 up 1 left, 2 up 1 right, 1 down 2 left, 1 down 2 right, 2 down 1 left, 2 down 1 right
  public ArrayList<BoardPosition> checkMoveAvailable(){
    ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
	BoardPosition[] positions = new BoardPosition[8];
	positions[0] = gameBoard.getBoardPosition(position.getX() - 2, position.getY() + 1); //1 up 2 left 
	positions[1] = gameBoard.getBoardPosition(position.getX() + 2, position.getY() + 1); //1 up 2 right
	positions[2] = gameBoard.getBoardPosition(position.getX() - 1, position.getY() + 2); //2 up 1 left
	positions[3] = gameBoard.getBoardPosition(position.getX() + 1, position.getY() + 2); //2 up 1 right
	positions[4] = gameBoard.getBoardPosition(position.getX() - 2, position.getY() - 1); //1 down 2 left
	positions[5] = gameBoard.getBoardPosition(position.getX() + 2, position.getY() - 1); //1 down 2 right
    positions[6] = gameBoard.getBoardPosition(position.getX() - 1, position.getY() - 2); //2 down 1 left
	positions[7] = gameBoard.getBoardPosition(position.getX() + 1, position.getY() - 2); //2 down 1 right
	for (int i = 0; i < positions.length; i++){
	  if (positions[i] != null ){
	    if(!positions[i].occupiedByFriendly(this)){
	      possibilities.add(positions[i]);
	    }
	  }
	}
	return possibilities;
  }
  
}