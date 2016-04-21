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
            this.exitButton.setLayoutX(canvas_y - 80);
            this.exitButton.setLayoutY(10);
            this.exitButton.setGraphic(new ImageView("Images/exit_fullscreen.png"));
            this.exitButton.setStyle("-fx-background-color: transparent");
            this.exitButton.setOnAction(e -> { 
               if(this.checked)
               {
               this.exitButton.setGraphic(new ImageView("Images/enter_fullscreen.png"));
               primaryStage.setFullScreen(false);
               primaryStage.setWidth(1500);
               primaryStage.setHeight(1000);
               this.canvas_y = 1500;
               this.canvas_x = 1000;
               down.setLayoutY((this.canvas_x-115));
               right.setLayoutX(this.canvas_y-102);
               this.exitButton.setLayoutX(this.canvas_y - 100);
               this.checked=false;
               }
               else
               {
               this.exitButton.setGraphic(new ImageView("Images/exit_fullscreen.png"));
               primaryStage.setFullScreen(true);
               this.canvas_y = screenSize.getWidth();
               this.canvas_x = screenSize.getHeight();
               down.setLayoutY((this.canvas_x-75));
               right.setLayoutX(this.canvas_y-82);
               this.exitButton.setLayoutX(this.canvas_y - 80);
               this.checked=true;
               }

            right.setLayoutY((this.canvas_x/2)-20);
            down.setLayoutX((this.canvas_y/2)-20);           
            left.setLayoutY((this.canvas_x/2)-20);
            up.setLayoutX((this.canvas_y/2)-20);       
             });
            

}
        public Button getExitbutton(){
         return this.exitButton;
        }
       

}