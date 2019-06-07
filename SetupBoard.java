import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage; 
import java.util.ArrayList;
public class SetupBoard extends Application{
   public void start(Stage primaryStage){
      Button[][] board = new Button[10][10];
      GridPane panel = new GridPane();
      //Image dust = new Image;
      for(int x = 0; x < 10; x++){
         for(int y = 0; y < 10; y++){
            board[x][y] = new Button();
            board[x][y].setPrefSize(50, 50);    
            panel.add(board[x][y],x,y);     
         }
      }   
      Scene scene = new Scene(panel, 500, 500);    
      primaryStage.setTitle("DustSweeper");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   public static void main(String[] args) {    
            launch(args);
   }    
}
