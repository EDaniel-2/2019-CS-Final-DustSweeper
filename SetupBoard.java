import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.io.*;
import java.lang.*;
public class SetupBoard extends Application{
   int mines;
   int row;
   int col;
   boolean[][] cells;
   boolean firstClick = true;
   //Image dust = new Image(getClass().getResourceAsStream("dust-cloud30.jpg"));
   Button[][] board;
   int[][] minesAround;
   Scene scene;
   GridPane panel = new GridPane();
   int[] changedMine = new int[2];

   public void start(Stage primaryStage){
      Scanner console = new Scanner(System.in);
      System.out.println("This is an adaption of minesweeper called dustsweeper.");
      System.out.print("How many rows would you like? (recommended 18) ");
      this.row = console.nextInt();
      System.out.print("How many columns would you like? (recommended 18) ");
      this.col = console.nextInt();
      System.out.print("How many mines would you like? (recommended 22) ");
      this.mines = console.nextInt();
      this.board = new Button[row][col];
      this.cells = new boolean[row][col];
      this.minesAround = new int[row][col];
      setMines(mines);
      for(int x = 0; x < row; x++){
         for(int y = 0; y < col; y++){
            createCell(x,y);    
         }
      }
      //scene.setOnMouseClicked(mouseHandler);   
      this.scene = new Scene(panel, col*50, row*50);    
      primaryStage.setTitle("DustSweeper");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
      
   public static void main(String[] args){
      launch(args);
   }
   
   public void setMines(int mines){
      for(int n = 1; n <= mines; n++){
         setSingleMine();
      }
   }
   
   public void createCell(int x, int y){
      board[x][y] = new Button();
      minesAround[x][y] =  checkAround(x,y);
      board[x][y].setText(""+minesAround[x][y]);
      //board[x][y].setGraphic(new ImageView(dust));
      board[x][y].setPrefSize(50, 50);
      this.panel.add(board[x][y],x,y);
      Cell cell = new Cell(board[x][y], x, y); 
   }
   
   public boolean accessCell(int x, int y){
      return cells[x][y];
   }
   
   public void setSingleMine(){
      int placeX = (int) (Math.random()*row);
      int placeY = (int) (Math.random()*col);
      if(this.cells[placeX][placeY] == true){setSingleMine();}
      else{
         this.cells[placeX][placeY] = true;
         changedMine[0] = placeX;
         changedMine[1] = placeY;
      }
   }
   
   public void updateAround(int x, int y, int update){
      for(int rowX = x-1; rowX < x+1; rowX++){//Goes through a 3 by three block and updates
         for(int colY = y-1; colY < y+1; colY++){
            minesAround[rowX][colY] = minesAround[rowX][colY]+update;
         }
      }
   }
   
   public int checkAround(int x, int y){
      int minesAround = 0;
      for(int startX = x-1; startX <= x+1; startX++){
         for(int startY = y-1; startY <= y+1; startY++){
            if(startX < 0 || startX >= row || startY < 0 || startY >= col){}
            else{
               if(cells[startX][startY] == true){
                  minesAround++;
               }
            }   
         }
      }
      return minesAround;
   }

   public void showEndgame(){
   
   }   
}
