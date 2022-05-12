package model;

/**
 * An indicator type class that extends indicator which stores information if a file has a gdp indicator
 *
 * @author Foothill College, Tanvi Waghela
 */
public class GDPIndicator extends Indicator{

    private double gdpPerCapita;

    /**
     * Initializes a GDPIndicator object that stores information of the file
     * @param year A year that is between the year range of the file
     */
    public GDPIndicator(int year){
        super(year);
        gdpPerCapita = INVALID_DATA;
    }

    /**
     * Initializes a GDPIndicator object that stores information of the file if the indicator is gdp
     * @param year A year that is between the year range of the file
     */
    public GDPIndicator(int year, double gdpPerCapita) {
        super(year);
        this.gdpPerCapita = gdpPerCapita;
    }

    /**
     * Stores the data given
     * @param data Data from the file
     */
    public void setData(double[] data) {
        gdpPerCapita = data[0];
    }

    /**
     * Gives the user the data
     * @return Returns a piece of data
     */
    public double[] getData() {
        return new double[]{gdpPerCapita};
    }

    /**
     * Creates a table representation of the GDPIndicator object
     * @return Returns a string representation of this object
     */
    public String toString() {
        if(gdpPerCapita == INVALID_DATA){
            return String.format("%15s", "()");
        }
        else{
            return String.format("%15s", "(" + String.format("%.2f", gdpPerCapita) + ")");
        }
    }
}