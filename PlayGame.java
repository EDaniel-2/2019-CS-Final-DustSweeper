import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.io.*;
import java.lang.*;
public class PlayGame extends SetupBoard{

   public void endgame(){
      showEndgame();
   }
   
   public int checkAround(int x, int y){
   
   return 1; //temporary
   }
   
   public void afterButtonClick(int minesAround){
   
   }

}