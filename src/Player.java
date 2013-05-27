import java.util.ArrayList;
public class Player{
  protected ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
  //public abstract void takeTurn();
  
  public ArrayList<ChessPiece> getPieces(){
    return pieces;
  }
  
}