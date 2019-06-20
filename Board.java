import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public class Board extends Application{//board saves many properties of the grid, such as arrays and arraylists. It also has behaviors for for the entire board 
   public static int mines;
   private static int size;
   private static boolean[][] isMine;
   private static boolean firstClick = true;
   private static int[][] minesAround;
   Scene scene;
   GridPane panel = new GridPane();
   private static Stage main;
   private static VBox vbox = new VBox();
   private static Cell[][] cells;
   public static int btnSize = 50;
   public static int minesFlagged = 0;
   public static Image suprise = new Image("big_spider.jpg");
   public static ImageView bigSpider = new ImageView(suprise);
   public static Image party = new Image("cake.jpg");
   public static ImageView cake = new ImageView(party);
   public static int flags = 0;

   @Override
   public void start(Stage primaryStage){//this sets up the panel, asking the user about the size and sets up mines and arrays
      Scanner console = new Scanner(System.in);
      main = primaryStage;
      System.out.println("This is an adaption of minesweeper called dustsweeper.");
      System.out.print("How many rows / columns would you like? (recommended 12, between 2 and 20): ");
      size = console.nextInt();
      if(size>20){size=20;}
      if(size<2){size=2;}
      int recMines = (size*size)/5;
      int maxMines = (size*size)/2;
      System.out.print("How many spiders would you like? (recommended "+recMines+" between 1 and " + maxMines+"): ");
      mines = console.nextInt();
      if(mines>maxMines){mines=maxMines;}
      if(mines<1){mines=1;}//ensures user does not select too many mines
      cells = new Cell[size][size];
      isMine = new boolean[size][size];
      minesAround = new int[size][size];
      setMines(mines);
      for(int row = 0; row < size; row++){
         for(int col = 0; col < size; col++){
            int minesAroun = checkAround(row, col);
            minesAround[row][col]=minesAroun;
         }
      }
      
      vbox.getChildren().addAll(setupCells());
      scene = new Scene(vbox);
      main.setTitle("DustSweeper");
      main.setScene(scene);
      main.setResizable(false);
      main.sizeToScene();
      main.show();
   }
      
   public static void main(String[] args){//launches the start method
      launch(args);
   }
   
   public void setMines(int mines){//loops to set all the mines
      for(int n = 1; n <= mines; n++){
         setSingleMine();
      }
   }

   public void setSingleMine(){//sets a single mine in the array
      int placeRow = (int) (Math.random()*size);
      int placeCol = (int) (Math.random()*size);
      if(isMine[placeRow][placeCol] == true){setSingleMine();}//uses recursion to place mines where they have not been placed
      else{
         isMine[placeRow][placeCol] = true;
      }
   }

   
   public int checkAround(int row, int col){//checks the cells around the cell specified by the parameters to 
      int minesAround = 0;
      boolean rowValid;
      boolean colValid;
      boolean notSameValid;
      for(int checkRow = row-1; checkRow <= row+1; checkRow++){
         for(int checkCol = col-1; checkCol <= col+1; checkCol++){
            rowValid = (0 <= checkRow && checkRow < size);
            colValid = (0 <= checkCol && checkCol < size);
            notSameValid = (checkRow!=row || checkCol!=col);
            if(rowValid && colValid  && notSameValid){
               if(isMine[checkRow][checkCol] == true){
                  minesAround++;
               }
            }   
         }
      }
      return minesAround;
   }

   private static Parent setupCells(){ //creates array of cells and puts them in the pane
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
   public static void showEndgame(){//reveals all the locations of spiders, opens an informational popup window informing the user they lost, and ends the program
      for(int checkRow = 0; checkRow < size; checkRow++){
         for(int checkCol = 0; checkCol < size; checkCol++){
            if(cells[checkRow][checkCol].isMine){
               cells[checkRow][checkCol].showSpider();
            }
         }
      }
      javafx.scene.control.Alert lose = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "You Clicked A Spider.");
      lose.setTitle("You Got Bit!");
      lose.setGraphic(bigSpider);
      lose.showAndWait();
      main.hide();
   }  
   
   public static void victory(){//opens an informational popup window informing the user they won, and ends the program
      javafx.scene.control.Alert win = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "You Found All The Spiders.");
      win.setTitle("You Win!");
      win.setGraphic(cake);
      win.showAndWait();
      main.hide();
   }
   
   public static void flagMine(int change){//keeps track of the number of mines that have been sucessfully flagged, and triggers victory if all mines are flagged
      minesFlagged = minesFlagged + change;
      if(minesFlagged == mines){
         victory();
      }
   } 
   
   public static void checkCellsAround(int row, int col){//Checks cells around the clicked cell to see if they should be revealed
      boolean rowValid;
      boolean colValid;
      boolean notSameValid;
      for(int checkRow = row-1; checkRow <= row+1; checkRow++){
         for(int checkCol = col-1; checkCol <= col+1; checkCol++){
            rowValid = (0 <= checkRow && checkRow < size);
            colValid = (0 <= checkCol && checkCol < size);
            notSameValid = (checkRow!=row || checkCol!=col);
            if(rowValid && colValid  && notSameValid){
               if(cells[checkRow][checkCol].minesAround == 0 && !cells[checkRow][checkCol].revealed && !cells[checkRow][checkCol].isMine){//checks that cell should be revealed AND should continue revealing cells around it
                  cells[checkRow][checkCol].showNumber();
                  checkCellsAround(checkRow, checkCol);
               }
               else if(cells[checkRow][checkCol].minesAround > 0 && !cells[checkRow][checkCol].revealed && !cells[checkRow][checkCol].isMine){
                  cells[checkRow][checkCol].showNumber();
               }
            }
         }
      }
   }
}