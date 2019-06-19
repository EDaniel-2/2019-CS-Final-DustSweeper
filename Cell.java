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

public class Cell extends StackPane{
   Button tile = new Button();
   int row;
   int col;
   boolean isMine;
   int minesAround;
      
   public Cell(int row, int col, boolean isMine, int minesAround, int size){
      this.row = row;
      this.col = col;
      this.isMine = isMine;
      this.minesAround = minesAround;
      tile.setOnMouseClicked(e -> {onClick(e);});
      tile.setMinHeight(size);
      tile.setMinWidth(size);
      getChildren().addAll(tile);
      setTranslateX(row * size);
		setTranslateY(col * size);
      revealMinesAround(); // change this later
   }
   
   private void onClick(MouseEvent e) {
      System.out.println("In onClick");
      if (e.getButton() == MouseButton.PRIMARY) {
         //is left click
         System.out.println("Primary (" + row + ", " + col + ")");
      }
      else {
         // Right Click
         System.out.println("Right");
         
         //afterButtonClick();
      }
   }

   public void revealMinesAround()
   {
      if(!isMine){
         tile.setText(""+minesAround);
      }
   }
//    public void revealMineNumber(int placeX, int placeY){
//       if(minesAround[placeX][placeY] == 0){
//          //Dont Show anything 
//       }
//       else{
         //shownumber and 
      
//       }
//    }
}
