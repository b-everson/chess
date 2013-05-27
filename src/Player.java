import java.util.ArrayList;
public abstract class Player{
  private static boolean player1 = true;
  protected ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>(16);
  public abstract void takeTurn();
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
  
  public String getDescription(){
    return playerDescription;
  }
  
  public ArrayList<ChessPiece> getPieces(){
    return pieces;
  }
  
  public Player(){
    	if(player1){
	  playerDescription = "(p1)";
	}else{
	  playerDescription = "(p2)";
	}
	r1 = new Rook(this);
	r2 = new Rook(this);
	b1 = new Bishop(this);
	b2 = new Bishop(this);
	k1 = new Knight(this);
	k2 = new Knight(this);
	q = new Queen(this);
	k = new King(this);
	p1 = new Pawn(this);
	p2 = new Pawn(this);
	p3 = new Pawn(this);
	p4 = new Pawn(this);
	p5 = new Pawn(this);
	p6 = new Pawn(this);
	p7 = new Pawn(this);
	p8 = new Pawn(this);
	
	pieces.add(0,r1);
	pieces.add(1,r2);
	pieces.add(2,b1);
	pieces.add(3,b2);
	pieces.add(4,k1);
	pieces.add(5,k2);
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

	player1 = false;
  }
  
  public void initializePiece(int piece,int x, int y){
    ChessPiece initPiece = pieces.get(piece);
	initPiece.initialize(x,y);
  }
  
}