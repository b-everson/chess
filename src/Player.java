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
  protected boolean check = false;
  protected ArrayList<ChessPiece> vitalEnemies;
  
  public Board getBoard(){
    return this.board;
  }
  
  public String getDescription(){
    return playerDescription;
  }
  
  public ArrayList<ChessPiece> getPieces(){
    return pieces;
  }
  
  public Player(Board owner){
    this.board = owner;
	if(player1){
	  playerDescription = "(p1)";
	}else{
	  playerDescription = "(p2)";
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
	  if (piece.getPosition().getX() == xPos && piece.getPosition().getY() == yPos){
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
    setVitalEnemies();  //set all enemies that can move to kings position
	ArrayList<BoardPosition> moves = new ArrayList<BoardPosition>();
	//loop through each pieces moves, if a move 
	for(ChessPiece piece : pieces){
	  for (BoardPosition position : piece.checkValidMoves() ){
	    for (ChessPiece enemy : getVitalEnemies()){
	      if (enemy.getPosition() == position){
		    vitalEnemies.remove(piece);
		    System.out.println(vitalEnemies.size());
		  }  
	    }
	  }
	}

	return vitalEnemies.size() > 0;
  }
  
  //need to see if position is vulnerable
  public boolean evaluateCheck(){
    setVitalEnemies();
    ArrayList<ChessPiece> enemies = vitalEnemies;
    if (enemies.size() > 0){
	  check = true;
	  vitalEnemies = enemies;
	}else{
	  check = false;
	  vitalEnemies.clear();	  
	}
	return check;
  }
  
  public void initializePiece(int piece,BoardPosition position){
    ChessPiece initPiece = pieces.get(piece);
	initPiece.initialize(position);
  }
  
}