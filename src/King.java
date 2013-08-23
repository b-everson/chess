import java.util.ArrayList;
public class King extends ChessPiece{
  private static final char KING_CHAR = 'K';
  private static final int KING_VALUE = 100;
  private static final String KING_NAME = "king";
  private BoardPosition initialPosition;
  private boolean firstMove = true;
  
    //function called only when adding a piece to the board
  public void initialize(BoardPosition position){
    this.initialPosition = position;
	super.initialize(position);
  }
  
  public boolean firstMove(){
    return initialPosition.equals(position) && firstMove;
  }
  
  public King(Player pOwner, Board bOwner){
    super(KING_CHAR, pOwner, bOwner, KING_VALUE, KING_NAME);
  }
  
  
   /*  1.The king has not previously moved.
    2.The chosen rook has not previously moved.
    3.There are no pieces between the king and the chosen rook.
    4.The king is not currently in check.
    5.The king does not pass through a square that is under attack by an enemy piece.[2]
    6.The king does not end up in check (true of any legal move).
    7.The king and the chosen rook are on the first rank of the player (rank 1 for White, rank 8 for Black */
  
  private ArrayList<Move> castleMoves(){
    ArrayList<Move> moves = new ArrayList<Move>(0);
	if(firstMove() ){  //if it is king's first move     1
	  ArrayList<Rook> rooks = getOwner().getRooks();
	  for (int j = 0; j <= rooks.size() - 1; j++){
	    if(rooks.get(j).firstMove() && rooks.get(j).isActive()){ // if it is rooks first move    2
	      boolean spaceClear = true;
		  //if rook position.X coord > king position. x coord
		  if(rooks.get(j).getPosition().getXCoord() > this.getPosition().getXCoord() ){
		    for (int i = rooks.get(j).getPosition().getXCoord() - 1; i > this.getPosition().getXCoord(); i--){
			  BoardPosition nextPosition = getBoard().getBoardPosition(i,getPosition().getYCoord());
			  if(nextPosition.isOccupied()){  // 3
			    spaceClear = false;
			  }
			}  
			if(spaceClear){
			  //add SpecialMove( this, position moving to, rook, rooks destination)
              BoardPosition kingDestination = getBoard().getBoardPosition(getPosition().getXCoord() + 2, getPosition().getYCoord());
			  BoardPosition rookDestination = getBoard().getBoardPosition(getPosition().getXCoord() + 1, getPosition().getYCoord());
			  moves.add(new SpecialMove(this, kingDestination, rooks.get(j), rookDestination));
            }			  
			
		  }else{
		    for (int i = this.getPosition().getXCoord() - 1; i > rooks.get(j).getPosition().getXCoord(); i--){
			  BoardPosition nextPosition = getBoard().getBoardPosition(i,getPosition().getYCoord());
			  if(nextPosition.isOccupied()){
			    spaceClear = false;
			  }
			}  
			if(spaceClear){
			//add SpecialMove( this, position moving to, rook, rooks destination)
			//TODO: get proper coordinates for movement.
			  BoardPosition kingDestination = getBoard().getBoardPosition(getPosition().getXCoord() - 2, getPosition().getYCoord());
			  BoardPosition rookDestination = getBoard().getBoardPosition(getPosition().getXCoord() - 1, getPosition().getYCoord());
			  moves.add(new SpecialMove(this, kingDestination, rooks.get(j), rookDestination));
		    }
			
		  }
	    }
	  }
	}
	return moves;
  }
  
  public ArrayList<Move> checkMoveAvailable(){
    ArrayList<Move> possibilities = new ArrayList<Move>();
	int x = position.getXCoord();
	int y = position.getYCoord();
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
	
	possibilities.addAll(castleMoves());
	
	return possibilities;
  }
  
  public boolean move(BoardPosition position){
    if(isVulnerable(position).size() > 0){
	  Game.setMessage("Cannot move your king into check.");
	  return false;
	}  
	
	boolean acceptedMove = super.move(position);
	if (acceptedMove){
	  firstMove = false;
	}
	
	return acceptedMove;
  }
}