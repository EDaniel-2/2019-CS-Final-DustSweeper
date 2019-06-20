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
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Cell extends StackPane{//cell saves the properties of each cell such as if it is a mine, and it also controls behaviors for the single cell
   Button tile = new Button();
   int row;
   int col;
   boolean isMine;
   int minesAround;
   int size;
   boolean revealed = false;
   boolean isFlagged = false;
   Image niceFlag = new Image("small_flag.jpg");
   ImageView freedom = new ImageView(niceFlag);
   Image dust = new Image("dust.jpg");
   ImageView dustImage = new ImageView(dust);
   Image scarySpider = new Image("spider.jpg");
   ImageView spider = new ImageView(scarySpider);
      
   public Cell(int row, int col, boolean isMine, int minesAround, int size){//constructer initializes all the properties of each cell
      this.row = row;
      this.col = col;
      this.isMine = isMine;
      this.minesAround = minesAround;
      this.size = size;
      tile.setOnMouseClicked(e -> {onClick(e);});
      tile.setMinHeight(size);
      tile.setMinWidth(size);
      getChildren().addAll(tile);
      setTranslateX(row * size);
		setTranslateY(col * size);
      tile.setGraphic(dustImage);
   }
   

   private void onClick(MouseEvent e) {//this handles each mouse click
      if (e.getButton() == MouseButton.PRIMARY) {//left click
         if(isMine){
            SetupBoard.showEndgame();
         }
         else{ //not a mine
            showNumber();
            if(minesAround == 0){
               SetupBoard.checkCellsAround(row, col);
            }
         }
      }
      else {
         // Right Click
         toggleFlag();
      }
   }
   
   public void toggleFlag(){//this method toggles if the flag is on or off and calls other methods to control properties of the flags
      if(isFlagged){//if it is flagged and it is clicked, the flag is taken away, total number of flags decremented
         SetupBoard.flags--;
         tile.setGraphic(dustImage);
         isFlagged = false;
         if(isMine){
            SetupBoard.flagMine(-1);
         } 

      }
      else{
         if(SetupBoard.flags < SetupBoard.mines){//if user is placing a mine, this doesnt allow more flags than mines
            SetupBoard.flags++;
            tile.setGraphic(freedom);
            isFlagged = true;
            if(isMine){
               SetupBoard.flagMine(1);
            }
         } 
      }
   }
   
   public void showSpider(){//shows the graphic of a spider
      tile.setGraphic(spider);   
   }

   public void showNumber()//reveals mines around, sets status to revealed, erases graphic
   {
      if(!isFlagged){
         tile.setDisable(true); // disable the button so they can't click on it anymore
         tile.setOpacity(1.0);
         this.revealed = true;
         if(!isMine){
            tile.setGraphic(null);
            if(minesAround == 0){}
            else{
               tile.setText(""+minesAround);
            }
         }
      }
   }
}