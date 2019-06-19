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
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public class SetupBoard extends Application{
   private static int mines;
   private static int size;
   private static boolean[][] isMine;
   private static boolean firstClick = true;
   //Image dust = new Image(getClass().getResourceAsStream("dust-cloud30.jpg"));
   //Button[][] board;
   private static int[][] minesAround;
   Scene scene;
   GridPane panel = new GridPane();
   //int[] changedMine = new int[2];
   private static Stage main;
   private static VBox vbox = new VBox();
   private static Cell[][] cells;
   public static int btnSize = 50;

   @Override
   public void start(Stage primaryStage){
      Scanner console = new Scanner(System.in);
      main = primaryStage;
      System.out.println("This is an adaption of minesweeper called dustsweeper.");
      System.out.print("How many rows / columns would you like? (recommended 9) ");
      size = console.nextInt();
      System.out.print("How many mines would you like? (recommended 8) ");
      mines = console.nextInt();
      cells = new Cell[size][size];
      //this.board = new Button[rows][cols];
      isMine = new boolean[size][size];
      minesAround = new int[size][size];
      setMines(mines);
      //System.out.println(Arrays.deepToString(isMine).replace("], ", "]\n"));
      for(int row = 0; row < size; row++){
         for(int col = 0; col < size; col++){
            // System.out.println("checkAround("+row+","+col+")");
            int mines = checkAround(row, col);
            // System.out.println("found "+mines+" mines");
            minesAround[row][col]=mines;
            // System.out.println("minesaround saved");
         }
      }
      
      vbox.getChildren().addAll(setupCells());
      //this.scene = new Scene(panel, cols*btnSize, rows*btnSize);    
      scene = new Scene(vbox);
      main.setTitle("DustSweeper");
      main.setScene(scene);
      main.setResizable(false);
      main.sizeToScene();
      main.show();
   }
      
   public static void main(String[] args){
      launch(args);
   }
   
   public void setMines(int mines){
      for(int n = 1; n <= mines; n++){
         setSingleMine();
      }
   }

   public void setSingleMine(){
      int placeRow = (int) (Math.random()*size);
      int placeCol = (int) (Math.random()*size);
      if(isMine[placeRow][placeCol] == true){setSingleMine();}
      else{
         isMine[placeRow][placeCol] = true;
         // changedMine[0] = placeX;
         // changedMine[1] = placeY;
      }
   }

 //   public void createCell(int x, int y){
//       board[x][y] = new Button();
//       minesAround[x][y] =  checkAround(x,y);
//       board[x][y].setText(""+minesAround[x][y]);
//       //board[x][y].setGraphic(new ImageView(dust));
//       board[x][y].setPrefSize(50, 50);
//       this.panel.add(board[x][y],x,y);
//       Cell cell = new Cell(board[x][y], x, y); 
//    }
   
   // public boolean accessCell(int x, int y){
   //    return isMine[x][y];
   // }
   
   
   // public void updateAround(int x, int y, int update){
   //    for(int rowX = x-1; rowX < x+1; rowX++){//Goes through a 3 by three block and updates
   //       for(int colY = y-1; colY < y+1; colY++){
   //          minesAround[rowX][colY] = minesAround[rowX][colY]+update;
   //       }
   //    }
   // }
   
   public int checkAround(int row, int col){
      // System.out.println("checkAround("+row+","+col+")  rows="+rows+"  cols="+cols);
      int minesAround = 0;
      boolean rowValid;
      boolean colValid;
      boolean notSameValid;
      for(int checkRow = row-1; checkRow <= row+1; checkRow++){
         for(int checkCol = col-1; checkCol <= col+1; checkCol++){
            // System.out.println("check ("+checkRow+", "+checkCol+ ")");
            rowValid = (0 <= checkRow && checkRow < size);
            colValid = (0 <= checkCol && checkCol < size);
            notSameValid = (checkRow!=row || checkCol!=col);
            // System.out.println("rowValid:"+rowValid+", colValid:"+colValid+", notSameValid:"+notSameValid);
            if(rowValid && colValid  && notSameValid){
               // System.out.println("checkAround("+row+","+col+") isMine["+checkRow+"]["+checkCol+"]");
               // System.out.println("isMine["+checkRow+"]["+checkCol+"]="+isMine[checkRow][checkCol]);
               if(isMine[checkRow][checkCol] == true){
                  minesAround++;
               }
            }   
         }
      }
      return minesAround;
   }

   private static Parent setupCells(){ //
      Pane root = new Pane();
      root.setPrefSize(size*btnSize, size*btnSize);
      for(int row = 0; row < size; row++){
         for(int col = 0; col < size; col++){
            Cell cell = new Cell(row,col,isMine[row][col],minesAround[row][col],btnSize);
            cells[row][col] = cell;
            root.getChildren().add(cell);  
         }
      }
      return root;
   }
   public void showEndgame(){
   
   }   
}
