package Main.Stations;

import Main.IComponents.IStations;


/**
 *
 * @author Sam
 */
public class Metro implements IStations{
    private String Name = null;
    private String Description = null;
    private String Type = null;
    private final String Metro_Img = null; // string naar de plek invullen blijft hetzelfde voor alle trams
    private double[] Location = new double[2];// longitude, latitude
    
    public Metro (String Name, String Description,String Type, double longitude,double latitude)
    {
    this.Name = Name;
    this.Description = Description;
    this.Type = Type;
    this.Location[0] = longitude;
    this.Location[1]= latitude;
    
    }
    
    public String getMetro_Img(){return Metro_Img;}
    
    @Override        
    public String[] getStation() {
        // return een lijst met alle atributen in String
        String[] StationList = new String[5];
        StationList[0] = this.Name;
        StationList[1] = this.Description;
        StationList[2] = this.Type;
        StationList[3] = Double.toString(this.Location[0]) ;
        StationList[4] = Double.toString(this.Location[1]) ;
        
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
    public void Draw(){}

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
    public double getLongitude(){return this.Location[0];}
    
    @Override
    public double getLatitude(){return this.Location[1];}
    
    @Override
    public double[] getLocation(){return this.Location;}
    
}
