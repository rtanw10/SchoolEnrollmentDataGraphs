package model;

/**
 * An indicator type class that extends indicator which stores information if a file has a primary or secondary indicator
 *
 * @author Foothill College, Tanvi Waghela
 */

public class SchoolEnrollmentIndicator extends Indicator{
    private double netPrimary;
    private double netSecondary;

    /**
     * Constructs a SchoolEnrollmentIndicator object that stores data about the file if the file
     * @param year A year that is between the year range of the file
     */
    public SchoolEnrollmentIndicator(int year) {
        super(year);
        netPrimary = INVALID_DATA;
        netSecondary = INVALID_DATA;
    }

    /**
     * Constructs a SchoolEnrollmentIndicator object that stores data about the file if the file if the file is primary or secondary
     * @param year a year that is between the year range of the file
     * @param netPrimary data that is stored if the file is a primary indicator
     */
    public SchoolEnrollmentIndicator(int year, double netPrimary, double netSecondary) {
        super(year);
        this.netPrimary = netPrimary;
        this.netSecondary = netSecondary;
    }

    /**
     * Sets enrollment data in the object
     * @param data A piece of enrollment data
     */
    public void setData(double[] data) {
        netPrimary = data[0];
        netSecondary = data[1];
    }

    /**
     * Sets enrollment data in the object
     * @param netPrimary net primary enrollment data
     */
    public void setPrimaryEnrollment(double netPrimary){
        this.netPrimary = netPrimary;
    }

    /**
     * Sets enrollment data in the object
     * @param netSecondary net secondary enrollment data
     */
    public void setSecondaryEnrollment(double netSecondary){
        this.netSecondary = netSecondary;
    }

    /**
     * Gives the user the net primary data
     * @return Returns the net primary data
     */
    public double getPrimaryEnrollment(){
        return netPrimary;
    }

    /**
     * Gives the user the net secondary data
     * @return Returns the net secondary data
     */
    public double getSecondaryEnrollment(){
        return netSecondary;
    }


    /**
     * Gives the user the data
     * @return Returns a piece of data
     */
    public double[] getData() {
        return new double[]{netPrimary, netSecondary};
    }

    /**
     * Creates a table representation of the SchoolEnrollmentIndicator object
     * @return Returns a string representation of the SchoolEnrollmentIndicator object
     */
    public String toString() {
        if((netPrimary == INVALID_DATA) || (netSecondary == INVALID_DATA)){
            return String.format("%15s", "()");
        }
        else{
            return String.format("%15s", "(" + String.format("%.2f", netPrimary) + ")");
        }
    }
}