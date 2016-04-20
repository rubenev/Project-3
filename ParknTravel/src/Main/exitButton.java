/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
    
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author mac_f
 */
public class exitButton {
         Button exitButton = new Button();
         double canvas_y;
         double canvas_x;
         boolean checked = true;
         public exitButton(Group root, double canvas_y, double canvas_x,Stage primaryStage, Button up, Button down, Button left, Button right ){
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
         exitButton.setLayoutX(canvas_y - 80);
         exitButton.setLayoutY(10);
         exitButton.setGraphic(new ImageView("Images/exit_fullscreen.png"));
         exitButton.setStyle("-fx-background-color: transparent");
         exitButton.setOnAction(e -> { 
            if(checked){
            exitButton.setGraphic(new ImageView("Images/enter_fullscreen.png"));
            primaryStage.setFullScreen(false);
            primaryStage.setWidth(1500);
            primaryStage.setHeight(1000);
            this.canvas_y = 1500;
            this.canvas_x = 1000;
            down.setLayoutY((this.canvas_x-115));
            right.setLayoutX(this.canvas_y-102);
            exitButton.setLayoutX(this.canvas_y - 100);
            checked=false;}else{
            exitButton.setGraphic(new ImageView("Images/exit_fullscreen.png"));
            primaryStage.setFullScreen(true);
            this.canvas_y = screenSize.getWidth();
            this.canvas_x = screenSize.getHeight();
            down.setLayoutY((canvas_x-75));
            right.setLayoutX(canvas_y-82);
            exitButton.setLayoutX(canvas_y - 80);
            checked=true;}

        right.setLayoutY((canvas_x/2)-20);
        down.setLayoutX((canvas_y/2)-20);           
        left.setLayoutY((canvas_x/2)-20);
        up.setLayoutX((canvas_y/2)-20);       
         });

}
        public Button getExitbutton(){
         return this.exitButton;
        }
}