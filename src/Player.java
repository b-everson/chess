import java.util.Scanner;
import java.util.ArrayList;
public class Player{
  private static Scanner input = new Scanner(System.in);
  private ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
  
  public void takeTurn(){
    ChessPiece chosenPiece = null;
	do{
	  chosenPiece = selectPiece();	  //select piece
	  if(chosenPiece == null){
	    System.out.println("You don't have a piece at this location, please try again.");
	  }
	} while(chosenPiece == null);
    int xPos = selectPosition('x');	
	int yPos = selectPosition('y');
  }
  
  private int selectPosition(char axis){
    int position = -1;
	int upperLimit = -1;
	if(axis == 'x'){
	  upperLimit = Board.BOARD_WIDTH;
	}else{
	  upperLimit = Board.BOARD_HEIGHT;
	}
	do{
	  System.out.println("What is the " + axis + " coordinate of the location you would like to move to?");
	  position = input.nextInt() - 1; //set 1 based value to zero based index
	  String junk = input.nextLine();
	  if(position > upperLimit || position < 0){
	    System.out.println("Invalid coordinate, please enter valid coordinate");
	  }
	}while(position > upperLimit || position < 0);
	return position;
  }
  
  private ChessPiece selectPiece(){
    ChessPiece selectedPiece = null;
	String junk;
	int x = -1;
	int y = -1;
    do{	
	  System.out.println("What is the x coordinate of the piece you would like to move?");
	  x = input.nextInt();
	  if(x < 0 || x > Board.BOARD_WIDTH){
	    System.out.println("Invalid x coordinate, please enter a valid one.");
	  }
	}while (x < 0 || x > Board.BOARD_WIDTH);  //get x
	
	do{
	  System.out.println("What is the y coordinate of the piece you would like to move?");
	  y = input.nextInt();
      if(y < 0 || y > Board.BOARD_HEIGHT){
        System.out.println("Invalid y coordinate, please enter a valid one.");
      }	 	  
	} while (y < 0 || y > Board.BOARD_HEIGHT);  //get y
	
	for (ChessPiece piece: pieces){
	  if(piece.getX() == x && piece.getY() == y){
	    selectedPiece = piece;
	  }
	}
	return selectedPiece;
  }//selectPiece()
}