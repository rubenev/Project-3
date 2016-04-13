/*
 * Project 3 - Halfvol
 * Hogeschool Rotterdam
 * INF1G
 */
package Main;

import Main.IComponents.IStations;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Sam
 */
public class Garage implements IStations {
    private String Name = null;
    private String Description = null;
    private String Type = null;
    private Image Garage_Img = null; // string naar de plek invullen blijft hetzelfde voor alle trams
    double Position_x;// longitude, latitude
    double Position_y;
    List<Garage> GarageList = new ArrayList();
    
    public Garage (Image Garage_Img, String Name, String Description,String Type, double longitude,double latitude)
    {
    this.Garage_Img = Garage_Img;
    this.Name = Name;
    this.Description = Description;
    this.Type = Type;
    this.Position_x = longitude;
    this.Position_y = latitude;
    
    }
    @Override
    public String[] getStation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStation(String Code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return this.Description; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setPositionX(double map_x){
        this.Position_x = this.Position_x + map_x;
    }
    public void setPositionY(double map_y){
        this.Position_y = this.Position_y + map_y;
       
    }
    public void setGarageList(List<Garage> list){
        this.GarageList = list;
    }
    
    @Override
    public double getPositionX() {
        return this.Position_x; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getPositionY() {
        return this.Position_y; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Draw(GraphicsContext gc) {
        gc.drawImage(this.Garage_Img,this.Position_x,this.Position_y);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
