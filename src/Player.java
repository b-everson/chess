import java.util.ArrayList;
public abstract class Player{
  private static boolean player1 = true;
  protected ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>(16);
  public abstract void takeTurn();
  private Board board;
  private Rook r1;
  private Rook r2;
  private Bishop b1;
  private Bishop b2;
  private Knight k1;
  private Knight k2;
  private Queen q;
  private King k;
  private Pawn p1;
  private Pawn p2;
  private Pawn p3;
  private Pawn p4;
  private Pawn p5;
  private Pawn p6;
  private Pawn p7;
  private Pawn p8;
  private String playerDescription;
  protected ArrayList<ChessPiece> vitalEnemies;
  private String playerName;
  
  public Board getBoard(){
    return this.board;
  }
  
  public String getDescription(){
    return playerDescription;
  }
  
  public String getType(){
    return playerName;
  }
  
  public ArrayList<ChessPiece> getPieces(){
    return pieces;
  }
  
  public Player(Board owner){
    this.board = owner;
	if(player1){
	  playerDescription = "(p1)";
	  playerName = "white";
	}else{
	  playerDescription = "(p2)";
	  playerName = "black";
	}
	r1 = new Rook(this, board);
	r2 = new Rook(this, board);
	b1 = new Bishop(this, board);
	b2 = new Bishop(this, board);
	k1 = new Knight(this, board);
	k2 = new Knight(this, board);
	q = new Queen(this, board);
	k = new King(this, board);
	p1 = new Pawn(this, board);
	p2 = new Pawn(this, board);
	p3 = new Pawn(this, board);
	p4 = new Pawn(this, board);
	p5 = new Pawn(this, board);
	p6 = new Pawn(this, board);
	p7 = new Pawn(this, board);
	p8 = new Pawn(this, board);
	
	pieces.add(0,r1);
	pieces.add(1,r2);
	pieces.add(2,k1);
	pieces.add(3,k2);
	pieces.add(4,b1);
	pieces.add(5,b2);
	pieces.add(6,q);
	pieces.add(7,k);
	pieces.add(8,p1);
	pieces.add(9,p2);
	pieces.add(10,p3);
	pieces.add(11,p4);
	pieces.add(12,p5);
	pieces.add(13,p6);
	pieces.add(14,p7);
	pieces.add(15,p8);
    
	vitalEnemies = new ArrayList<ChessPiece>();
	player1 = false;
  }
  
  protected ChessPiece getPiece(int xPos, int yPos){
    ChessPiece returnPiece = null;
	for(ChessPiece piece : pieces){
	  if (piece.getPosition().getXCoord() == xPos && piece.getPosition().getYCoord() == yPos){
	    returnPiece = piece;
		break;
	  }
	}
	return returnPiece;
  }
  
  public void setVitalEnemies(){
    vitalEnemies.clear();  //clear list
	ArrayList<ChessPiece> enemies = k.isVulnerable(k.getPosition()); // any enemy that can move to kings position
	for (ChessPiece piece : enemies)
	{
	  vitalEnemies.add(piece);   //if enemy can move to kings position add to list
	}
  }//set vital enemies
  
  public ArrayList<ChessPiece> getVitalEnemies(){
    return vitalEnemies;
  }
   
  public boolean evaluateCheckMate(){
    boolean checkMate = true;
	if(vitalEnemies.size() == 0){
	  checkMate = false;
	}else{
	
	//loop through each piece, looking for a valid move
	  for (ChessPiece piece : pieces){
	    if(piece.checkValidMoves().size() > 0)
	      checkMate = false;
	  }
	}
 
	return checkMate;
  }
  
  //need to see if position is vulnerable
  public boolean evaluateCheck(){
    boolean check = false;
	setVitalEnemies();
    if (vitalEnemies.size() > 0){
	  check = true;
	}
	return check;
  }
  
  public void initializePiece(int piece,BoardPosition position){
    ChessPiece initPiece = pieces.get(piece);
	initPiece.initialize(position);
  }
  
  public ArrayList<Move> validMoves(){
    ArrayList<Move> myMoves = new ArrayList<Move>();
	for (ChessPiece piece : pieces ){
	  for(Move pieceMove : piece.checkValidMoves()){
	    myMoves.add(pieceMove);
	  }
	}
	return myMoves;
  }
  
  public int evaluateBoard(){
    float score = 0;
	int playersMoves = 0;
	for (ChessPiece piece : pieces){
	  if (piece.isActive()){
	    playersMoves += piece.checkValidMoves().size();
	    score += piece.getValue() * 2;
	/*	if(piece.isVulnerable(piece.getPosition()).size() > 0){
		  score -= piece.getValue() * 1.5;
		}*/
		if(piece instanceof Pawn){
		  if(((Pawn)piece).isDoubled()){
		    score -= 1;
		  }
		  if(((Pawn)piece).isBackward()){
		    score -= 1;
		  }
		  if(((Pawn)piece).isIsolated()){
		    score -= 1;
		  }
		}
	  }
	}
	
	score += playersMoves * .2;
	//doubling value of current pieces creates higher value for active pieces than vulnerable pieces
	//this is so that putting pieces under attack is valued, but not as much as actually removing them.
	int enemysMoves = 0;
	for (ChessPiece enemy : board.getOtherPlayer(this).getPieces()){
	  if(enemy.isActive()){
	    enemysMoves += enemy.checkValidMoves().size();
	    score -= enemy.getValue() * 2;  
	/*      if (enemy.isVulnerable(enemy.getPosition()).size() > 0){ 
		    score += enemy.getValue() * 1.5;
	      }*/
		  if(enemy instanceof Pawn){
		    if(((Pawn)enemy).isDoubled()){
		      score -= 1;
		    }
		    if(((Pawn)enemy).isBackward()){
		      score -= 1;
		    }
		    if(((Pawn)enemy).isIsolated()){
		      score -= 1;
		    }
		  }
	  }                                 
	}
	
	score -= enemysMoves * .2;
	
	return (int)score;
  }
  
}