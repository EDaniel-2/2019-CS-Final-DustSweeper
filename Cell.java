import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.io.*;
import java.lang.*;
public class Cell extends PlayGame{
   Button tile;
   int x;
   int y;
   int rows;
   int cols; 
   //boolean isMine;
      
   public Cell(Button tile, int x, int y){
      this.tile = tile;
      this.x = x;
      this.y = y;
      this.rows = rows;
      this.cols = cols;
      //this.isMine = isMine;
      tile.setOnMouseClicked(mouseHandler);
      }
   EventHandler<javafx.scene.input.MouseEvent> mouseHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
 
        @Override
        public void handle(javafx.scene.input.MouseEvent mouseEvent) {
            onClick(mouseEvent);
            System.out.println("hi");
         }
      };


   public void onClick(javafx.scene.input.MouseEvent e) {
   System.out.println("0");
      if (e.getButton() == javafx.scene.input.MouseButton.PRIMARY) {//is left click
      System.out.println("1");
      //System.out.println(row);
         /*for(int placeX = 0; placeX < rows; placeX++){
         System.out.println("2");
            for(int placeY = 0; placeY < cols; placeY++){
            System.out.println("3");*/
               if(firstClick == true){//This checks if its the first click which is protected
                  if(cells[x][y]){//So, if the persons first click is on mine, it places a new mine
                     setSingleMine();
                     updateAround(x,y,-1);
                     cells[changedMine[0]][changedMine[1]] = true;
                     updateAround(changedMine[0],changedMine[1], 1);
                     cells[x][y] = false;
                     System.out.println("6");
                     //can use minesAround array 
                     //set mines around for cells around to minesAround-1
                     //set number of mines for cells around array
                     //can use setText to check if this works
                  }
                  firstClick = false;   
               }
            }
            
      if(cells[x][y]) {
         System.out.println("u done");
         endgame();   
      }
      // Right Click
      else {
         
         //afterButtonClick();
      }
   }
   public void revealMineNumber(int placeX, int placeY){
      if(minesAround[placeX][placeY] == 0){
         //Dont Show anything 
      }
      else{
         //shownumber and 
      
      }
   }
}
