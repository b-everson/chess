import javax.swing.JButton;
import java.util.ArrayList;
public class BoardPosition extends JButton {
  private int xCoord;
  private int yCoord;
  private ChessPiece currentPiece = null;
  private String contents = "       ";
  private ArrayList<ChessPiece> attackingPieces;
  
  public boolean underAttack(Player player){
    if (player.getDescription().equals("Player 1")){
	  for(ChessPiece piece : attackingPieces){
	    if(piece.getOwner().getDescription().equals("Player 2")){
		  return true;
		}
	  }
	}else{
	  for(ChessPiece piece : attackingPieces){
	    if(piece.getOwner().getDescription().equals("Player 1")){
		  return true;
		}
	  }	
	}
	return false;
  }
  
  public void addAttackingPiece(ChessPiece piece){
    attackingPieces.add(piece);
  }
  
  public void removeAttackingPiece(ChessPiece piece){
    attackingPieces.remove(piece);
  }
  
  public BoardPosition(int x, int y){
    attackingPieces = new ArrayList<ChessPiece>();
	xCoord = x;
	yCoord = y;
  }
    
  public int getXCoord(){
    return xCoord;
  }
  
  public int getYCoord(){
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
	  this.setIcon(null);
	}
	else{
	  this.setIcon(currentPiece.getIcon());
	}
  }
    
}