
package Main;


    public class Bus implements IStations{
        
    private final String Bus_Img = null; // string naar de plek
    private double[] Location = null;// longitude, latitude
    
    public Bus (){}
    
    @Override        
    public void getStation() {}// ophalen uit class

    @Override
    public void setStation() {}// gegevens ophalen uit database zodat dit op de map komt
    
    @Override
    public void Draw(){} // tekenen

    @Override
    public void update() {}// updaten 
    
    public String getBus_Img(){return Bus_Img;}
    
    
    
   
        
    
}
