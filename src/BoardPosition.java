import javax.swing.JButton;
public class BoardPosition {
  private int xCoord;
  private int yCoord;
  private ChessPiece currentPiece = null;
  private String contents = "       ";
  private JButton button;
  
  public JButton getButton(){
    return button;
  }
  
  public void setButton(JButton button){
    this.button = button;
  }
  
  public BoardPosition(int x, int y){
    xCoord = x;
	yCoord = y;
  }
  
  public int getX(){
    return xCoord;
  }
  
  public int getY(){
    return yCoord;
  }
  
  public boolean isOccupied(){
	return (currentPiece != null);
  }
  
  public boolean occupiedByFriendly(ChessPiece incoming){
    boolean occupied = false;
	if(currentPiece != null){
	  if(incoming.getOwner() == currentPiece.getOwner())
	    occupied = true;
	}	
	return occupied;
  }
  
  public boolean occupiedByEnemy(ChessPiece incoming){   /*for a move, a piece must know if target is occupied by piece, valid moves cannot including moving one piece to a friendly piece*/
    boolean occupied = false;
	if(currentPiece != null){
	  if(incoming.getOwner() != currentPiece.getOwner())
	    occupied = true;
	}
	return occupied;
  }
  
  public void setPiece(ChessPiece piece){
    if(currentPiece != null)
	  currentPiece.setInactive();
	currentPiece = piece;
	if(currentPiece != null){
	  contents = currentPiece.toString();
	  //button.setIcon(currentPiece.getIcon());
	}  
	else{
	  contents = "       ";
	  //button.setIcon(null);
	}  
  }
  
  public void clearPiece(){
    currentPiece = null;
	contents = "       ";
  }
  
  public ChessPiece getPiece(){
    return currentPiece;
  }
 
  public String toString(){
    return contents;
  }
  
  public void update(){
    if(this.currentPiece == null){
	  this.button.setIcon(null);
	}
	else{
	  this.button.setIcon(currentPiece.getIcon());
	}
  }
  
}