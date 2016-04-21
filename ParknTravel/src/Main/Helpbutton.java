/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Classes.Images;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author mac_f
 */
public class Helpbutton {
    boolean help_open = false;
    Label looptijdtext = new Label();
    Label loopafstandtext = new Label();
    Button helpbutton = new Button();
    ImageView helpscreen = new ImageView((Images.help_image));
    public Helpbutton(){
        this.helpscreen.setLayoutX(350);
        this.helpscreen.setLayoutY(7);
        this.helpscreen.setVisible(false);
        this.helpbutton.setLayoutX(275);
        this.helpbutton.setLayoutY(2);
        this.helpbutton.setGraphic(new ImageView(Images.helpbutton_image));
        this.helpbutton.setStyle("-fx-background-color: transparent");
        this.helpbutton.setOnMouseClicked(e -> {
            if(this.help_open)
               {
                this.helpscreen.setVisible(true);
                   this.help_open = false;
               }else{
                this.helpscreen.setVisible(false);
                this.help_open = true;
            }
        });
        this.loopafstandtext.setLayoutX(190);
        this.loopafstandtext.setLayoutY(250);
        this.loopafstandtext.setTextFill(Color.web("#FFFFFF"));
        this.looptijdtext.setLayoutX(190);
        this.looptijdtext.setLayoutY(310);       
        this.looptijdtext.setTextFill(Color.web("#FFFFFF"));  
}
    public Button getHelpbutton(){
        return this.helpbutton;
    }
    public ImageView gethelpscreen(){
        return this.helpscreen;
    }
    public Label getloopafstandtext(){
        return this.loopafstandtext;
    }
    public Label getlooptijdtext(){
        return this.looptijdtext;
    }    
}