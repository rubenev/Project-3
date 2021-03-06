
package Main.Stations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


    public class Bus implements IStations{
    private String Name = null;
    private String Description = null;
    private Image Bus_Img = null; // string naar de plek invullen blijft hetzelfde voor alle trams
    double Position_x;// longitude, latitude
    double Position_y;
    public boolean isClicked = false ;
    private double loopafstand;
    private double looptijd;
    
    public Bus (Image image, String Name, String Description, double longitude,double latitude)
    {
    this.Name = Name;
    this.Description = Description;
    this.Position_x = longitude;
    this.Position_y = latitude;
    this.Bus_Img = image;
    
    }
    
    public Image getTram_Img(){return Bus_Img;}
    
    @Override        
    public String[] getStation() {
        String[] StationList = new String[5];
        StationList[0] = this.Name;
        StationList[1] = this.Description;
        
        return StationList;}
    
    @Override
    public void setStation(String Code) {

    
    }
    @Override
    public void Draw(GraphicsContext gc){
        gc.drawImage(this.Bus_Img,this.Position_x,this.Position_y);
    }
    @Override
    public void update() {}

    @Override
    public String getName() {
        return Name;}

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
   
    public void setAfstand(double loopafstand){
        this.loopafstand = (int)(loopafstand * 1.25);
    }    
    public void setTijd(double looptijd){
        this.looptijd = (int)(looptijd / 66);
    }  
    public double getAfstand(){
        return this.loopafstand;
    }    
    public double getTijd(){
        return this.looptijd;
    }   
}
