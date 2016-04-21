
package Main;

import Main.IComponents.IComponents;
import Main.Stations.Bus;
import Main.Stations.Metro;
import Main.Stations.Tram;
import java.awt.Graphics;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

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
    double selected_garX;
    double selected_garY;
    
    @Override
    public void Draw(GraphicsContext gc) {
        gc.drawImage(menu_image, 5, 0);
         }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void InteractionCheckbox(CheckBox Bus , CheckBox Metro, CheckBox Tram, Stage primaryStage, Scene theScene , List<Button> MetrobList,List<Button> BusbList,List<Button>TrambList ){
    
        Bus.setLayoutX(-21); Bus.setLayoutY(200);
    if (Bus.isSelected())
        {Bus.setGraphic(new ImageView(bus_image_c));
        for (Button busbutt :  BusbList){busbutt.setVisible(true);}
        }
    else{Bus.setGraphic(new ImageView(bus_image));
    for (Button busbutt :  BusbList){busbutt.setVisible(false);}}
        
    
    
    Metro.setLayoutX(-21); Metro.setLayoutY(80);  
    if (Metro.isSelected())
        {Metro.setGraphic(new ImageView(metro_image_c)); 
        for (Button metrobutt : MetrobList){metrobutt.setVisible(true);}}
    else{Metro.setGraphic(new ImageView(metro_image));
        for (Button metrobutt : MetrobList){metrobutt.setVisible(false);}}
    
    
    Tram.setLayoutX(-21); Tram.setLayoutY(140);
    if (Tram.isSelected())
        {Tram.setGraphic(new ImageView(tram_image_c));
        for (Button trambutt : TrambList){trambutt.setVisible(true);}}
    else{Tram.setGraphic(new ImageView(tram_image));
        for (Button trambutt : TrambList){trambutt.setVisible(false);}}
    
    primaryStage.setScene( theScene );
    }
    public void setgarX(double x){
        this.selected_garX = x;
    }
    public void setgarY(double y){
        this.selected_garY = y;
    }   
    public double getgarX(){
        return this.selected_garX;
    }
    public double getgarY(){
        return this.selected_garY;
    }    
    public void paint(Graphics g, double text, double x, double y)
    {   
       String text_string = Double.toString(text);
       int x_int = (int) x;
       int y_int = (int) y;
       g.drawString(text_string, x_int, y_int);
    }
    /**
     * 
     * @param buttList lijst met buttons
     * @param X geef x coördinaat - is naar rechts
     * @param Y geef y coördinaat - is naar beneden
     */
    public void moveButtonList(double X,double Y,List<Button> buttList,List<Label> labList,List<Garage> garList,List<Metro> metList,List<Tram> traList,List<Bus> busList){
    for (Button button: buttList){
        button.setLayoutX(button.getLayoutX() + X);
        button.setLayoutY(button.getLayoutY() + Y);}
    for (Label label : labList){
    label.setLayoutX(label.getLayoutX() + X);
    label.setLayoutY(label.getLayoutY() + Y);
    }
    for (Garage garage : garList){
    garage.setPositionX(X);
    garage.setPositionY(Y);
    }
    for (Metro metro : metList){
    metro.setPositionX(X);
    metro.setPositionY(Y);
    }
    for (Tram tram : traList){
    tram.setPositionX(X);
    tram.setPositionY(Y);
    }
    for (Bus bus : busList){
    bus.setPositionX(X);
    bus.setPositionY(Y);
    }
    }
    
}
