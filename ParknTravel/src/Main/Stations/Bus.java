
package Main.Stations;

import Main.IComponents.IStations;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


    public class Bus implements IStations{
    private String Name = null;
    private String Description = null;
    private String Type = null;
    private Image Bus_Img = null; // string naar de plek invullen blijft hetzelfde voor alle trams
    double Position_x;// longitude, latitude
    double Position_y;
    
    public Bus (Image image, String Name, String Description,String Type, double longitude,double latitude)
    {
    this.Name = Name;
    this.Description = Description;
    this.Type = Type;
    this.Position_x = longitude;
    this.Position_y = latitude;
    this.Bus_Img = image;
    
    }
    
    public Image getTram_Img(){return Bus_Img;}
    
    @Override        
    public String[] getStation() {
        // return een lijst met alle atributen in String
        String[] StationList = new String[5];
        StationList[0] = this.Name;
        StationList[1] = this.Description;
        StationList[2] = this.Type;
        
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
    public String getType() {
     return Type;}
    
    @Override
    public double getPositionX(){return this.Position_x;}
    
    @Override
    public double getPositionY(){return this.Position_y;}
   
        
    
}
