
package Main.Stations;

import Main.IComponents.IComponents;

/**
 *
 * @author Halfvol
 */
public interface IStations extends IComponents{
    /**
     *
     * @return a list of the attributes as String
     */
    public String[] getStation();
    /**
     * gets attributes from the database and sets an instance.
     * @param Code the unique code in Database
     * 
     */
    public void setStation(String Code);
    /**
    * @return the Name
    **/
    public String getName();
    /**
    * @return the Description
    **/
    public String getDescription();

    /**
    * @return the Longitude
    **/
    public double getPositionX();
    /**
    * @return the LAtitude
    **/
    public double getPositionY();

    public void setPositionX(double map_x);
    
    public void setPositionY(double map_y);
    
}
