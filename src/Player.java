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
  

  
  public void initializePiece(int piece,BoardPosition position){
    ChessPiece initPiece = pieces.get(piece);
	initPiece.initialize(position);
  }
  
}