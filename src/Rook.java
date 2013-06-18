import java.util.ArrayList;
public class Rook extends ChessPiece{
  private static final char ROOK_CHAR = 'r';
  
  public Rook(Player pOwner, Board bOwner){
    super(ROOK_CHAR,pOwner, bOwner);
  }
  
  public void move(BoardPosition position){
    
  }
  
  public ArrayList<BoardPosition> checkMoveAvailable(){  
    
	//rook can move in straight line on either x or y axis
	//xPos >= 0, xPos <= Board.BOARD_WIDTH
	for(int i = 0; i <= Board.BOARD_WIDTH;i++){
	 /* if(!(position.blocked)){
	    boardRep[0][] = 
	  }*/
	}
	
	
	//yPos >= 0, yPos < Board.BOARD_HEIGHT
	ArrayList<BoardPosition> possibilities = new ArrayList<BoardPosition>();
	possibilities.add(new BoardPosition(1,1));
	return possibilities;
  }
  
  /*  generateMovePath()
    check each position on check move available  
	if there is a piece in any position except target then it is blocked,
	if target position is occupied by friendly piece it is blocked
  
  */
}