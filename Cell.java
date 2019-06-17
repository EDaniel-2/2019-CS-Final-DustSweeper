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
   boolean isMine;
      
   public Cell(Button tile, int x, int y, boolean isMine){
      this.tile = tile;
      this.x = x;
      this.y = y;
      this.isMine = isMine;
      }
   
   public void onClick(javafx.scene.input.MouseEvent e) {
   System.out.println("0");
      if (e.getButton() == javafx.scene.input.MouseButton.PRIMARY) {//is left click
      System.out.println("1");
         for(int placeX = 0; placeX < row; placeX++){
         System.out.println("2");
            for(int placeY = 0; placeY <col; placeY++){
            System.out.println("3");
               if((!board[placeX][placeY].isDisabled()) && placeY == col){//This checks if any button in board is disabled, if not it continues
               System.out.println("5");
                  if(isMine = true){//So, if the persons first click is on mine, it places a new mine
                     setSingleMine();
                     updateAround(x,y,-1);
                     updateAround(changedMine[0],changedMine[1], 1);
                     System.out.println("6");
                     //can use minesAround array 
                     //set mines around for cells around to minesAround-1
                     //set number of mines for cells around array
                     //can use setText to check if this works
                  }   
               }
            }
         }
      }
      board[x][y].setDisable(false);

      if (isMine == true) {
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

