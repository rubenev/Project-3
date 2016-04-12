
package Main.IComponents;

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
    * @return the Type
    **/
    public String getType();
    /**
    * @return the Longitude
    **/
    public double getLongitude();
    /**
    * @return the LAtitude
    **/
    public double getLatitude();

    /**
     *
     * @return Location, list of 2 doubles. Longitude , Latitude.
     */
    public double[] getLocation();
    
}
