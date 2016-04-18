
package Main;

import Main.IComponents.IComponents;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Sam
 */
public class Menu implements IComponents {
    
/*    Image menu_image = new Image("file:Images/menuimg.png");
    Image metro_image = new Image("file:Images/metrolabel.png");
    Image tram_image = new Image("file:Images/tramlabel.png");
    Image tram_image_c = new Image("file:Images/tramlabel_check.png");
    Image bus_image_c = new Image("file:Images/buslabel_check.png");
    Image metro_image_c = new Image("file:Images/metrolabel_check.png");
    Image bus_image = new Image("file:Images/buslabel.png");
    Image looptijd_image = new Image("file:Images/looptijd.png");
    Image loopafstand_image = new Image("file:Images/loopafstand.png");  */
    
    Image menu_image = new Image("Images/menuimg.png");
    Image metro_image = new Image("Images/metrolabeln.png");
    Image tram_image = new Image("Images/tramlabeln.png");
    Image tram_image_c = new Image("Images/tramlabel_checkn.png");
    Image bus_image_c = new Image("Images/buslabel_checkn.png");
    Image metro_image_c = new Image("Images/metrolabel_checkn.png");
    Image bus_image = new Image("Images/buslabeln.png");
    Image looptijd_image = new Image("Images/looptijd.png");
    Image loopafstand_image = new Image("Images/loopafstand.png");  

    @Override
    public void Draw(GraphicsContext gc) {
        gc.drawImage(menu_image, 5, 0);
         }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void InteractionCheckbox(CheckBox Bus , CheckBox Metro, CheckBox Tram, Stage primaryStage, Scene theScene ){
    Bus.setLayoutX(-21); Bus.setLayoutY(200);
    if (Bus.isSelected()){Bus.setGraphic(new ImageView(bus_image_c));}else{Bus.setGraphic(new ImageView(bus_image));}
     Metro.setLayoutX(-21); Metro.setLayoutY(80);
    if (Metro.isSelected()){Metro.setGraphic(new ImageView(metro_image_c));}else{Metro.setGraphic(new ImageView(metro_image));}
     Tram.setLayoutX(-21); Tram.setLayoutY(140);
    if (Tram.isSelected()){Tram.setGraphic(new ImageView(tram_image_c));}else{Tram.setGraphic(new ImageView(tram_image));}
    
    primaryStage.setScene( theScene );
    }
    
}
