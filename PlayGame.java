import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage; 
import java.util.ArrayList;
public class PlayGame extends Application{
   
   Button[][] board;
   
   public PlayGame(){
      board = new Button[10][10];
   }
   
   public void start(Stage primaryStage){
      Button[][] board = new Button[10][10];
      GridPane panel = new GridPane();
      //Image dust = new Image;
      for(int x = 0; x < 10; x++){
         for(int y = 0; y < 10; y++){
            /*TextField tf = new TextField();
            tf.setPrefHeight(50);
            tf.setPrefWidth(50);
            //tf.setAlignment(alignment.CENTER);
            tf.setEditable(false);
            tf.setText("?");
            root.setRowIndex(tf,y);
            root.setColumnIndex(tf,x);    
            root.getChildren().add(tf);*/
            board[x][y] = new Button();
            //board[x][y].setGraphic(new ImageView());
            board[x][y].setPrefSize(50, 50); 
            //board[x][y].setText("?");
            //can set to image later   
            panel.add(board[x][y],x,y);
            //panel.add(new Button(), x, y)   ;      
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

