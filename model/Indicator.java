package model;

/**
 * An abstract class that indicats what type of indicated file was read
 *
 * @author Foothill College, Tanvi Waghela
 */

public abstract class Indicator {
    private int year;
    public final int INVALID_DATA = -1;

    /**
     * Initializes an Indicator object that stores the files data and years
     * @param year A year that is between the year range of the file
     */
    public Indicator(int year){
        this.year = year;
    }

    /**
     * Gives the user the year
     * @return Returns the year
     */
    public int getYear(){
        return year;
    }

    /**
     * Stores the data given
     * @param data Data from the file
     */
    public abstract void setData(double[] data);

    /**
     * Gives the user the data
     * @return Returns the data
     */
    public abstract double[] getData();

    /**
     * Creates and returns a string representation of this class
     * @return Returns a string representation of the object
     */
    public abstract String toString();
}
