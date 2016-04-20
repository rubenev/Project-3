/*
 * Project 3 - Halfvol
 * Hogeschool Rotterdam
 * INF1G
 */
package Main.Stations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Halfvol
 */
 public class Tram implements IStations{
    private String name = null;
    private String Description = null;
    //private String Type = null;
    private Image Tram_Img = null; // string naar de plek invullen blijft hetzelfde voor alle trams
    private double Position_x;
    private double Position_y;// longitude, latitude
    public boolean isClicked = false;
    
    public Tram (Image image, String name, String Description, double longitude,double latitude)
    {
    this.name = name;
    this.Description = Description;
    //this.Type = Type;
    this.Position_x = longitude;
    this.Position_y = latitude;
    this.Tram_Img = image;
    
    }
    
    public Image getTram_Img(){return Tram_Img;}
    
    @Override        
    public String[] getStation() {
        // return een lijst met alle atributen in String
        String[] StationList = new String[5];
        StationList[0] = this.name;
        StationList[1] = this.Description;
        //StationList[2] = this.Type;

        
        return StationList;}
    
    @Override
    public void setStation(String Code) {
    /**
     * gegevens ophalen uit database en hieraan toepassen
    this.Name = Uit database Name;
    this.Description = uit database Description;
    this.Type = uit database Type;
    this.Location[0] = uit database longitude;
    this.Location[1]= uit database latitude;
    */
    
    }
    @Override
    public void Draw(GraphicsContext gc){
    gc.drawImage(this.Tram_Img,this.Position_x,this.Position_y);}

    @Override
    public void update() {}

    @Override
    public String getName() {
        return name;}

    @Override
    public String getDescription() {
       return Description;}

    @Override
    public void setPositionX(double map_x){
        this.Position_x = this.Position_x + map_x;
    }
    @Override
    public void setPositionY(double map_y){
        this.Position_y = this.Position_y + map_y;
       
    }
    @Override
    public double getPositionX(){return this.Position_x;}
    
    @Override
    public double getPositionY(){return this.Position_y;}


  
    

 }
 
    
    