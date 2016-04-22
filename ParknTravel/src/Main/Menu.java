
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


public class Menu implements IComponents {
    
    public ImageView M_image = new ImageView("Images/M-location_imagen.png");
    public ImageView T_image = new ImageView("Images/T-location_imagen.png");
    public ImageView B_image = new ImageView("Images/B-location_imagen.png");  
    
    
    Image menu_image = new Image("Images/menuimg.png");
    ImageView metro_image = new ImageView("Images/metrolabeln.png");
    ImageView tram_image = new ImageView("Images/tramlabeln.png");
    ImageView tram_image_c = new ImageView("Images/tramlabel_checkn.png");
    ImageView bus_image_c = new ImageView("Images/buslabel_checkn.png");
    ImageView metro_image_c = new ImageView("Images/metrolabel_checkn.png");
    ImageView bus_image = new ImageView("Images/buslabeln.png");
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
    public void InteractionCheckbox(CheckBox Bus , CheckBox Metro, CheckBox Tram, Stage primaryStage, Scene theScene , List<Button> MetrobList,List<Button> BusbList,List<Button>TrambList ,List<Label>MetrolList, List<Label> BuslList,List<Label> TramlList){
    
        Bus.setLayoutX(-21); Bus.setLayoutY(200);
    if (Bus.isSelected())
        {Bus.setGraphic(bus_image_c);
        for (Button busbutt :  BusbList){busbutt.setVisible(true);}
        }
    else{Bus.setGraphic(bus_image);
    for (Button busbutt :  BusbList){busbutt.setVisible(false);for (Label buslab :  BuslList){buslab.setVisible(false);}}}
    
        
    
    
    Metro.setLayoutX(-21); Metro.setLayoutY(80);  
    if (Metro.isSelected())
        {Metro.setGraphic(metro_image_c); 
        for (Button metrobutt : MetrobList){metrobutt.setVisible(true);}}
    else{Metro.setGraphic(metro_image);
        for (Button metrobutt : MetrobList){metrobutt.setVisible(false);}for (Label metlab :  MetrolList){metlab.setVisible(false);}}
    
    
    Tram.setLayoutX(-21); Tram.setLayoutY(140);
    if (Tram.isSelected())
        {Tram.setGraphic(tram_image_c);
        for (Button trambutt : TrambList){trambutt.setVisible(true);}}
    else{Tram.setGraphic(tram_image);
        for (Button trambutt : TrambList){trambutt.setVisible(false);}for (Label tralab :  TramlList){tralab.setVisible(false);}}
    
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
