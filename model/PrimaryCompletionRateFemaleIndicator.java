package model;

//extra credit portion

/**
 * An indicator type class that extends indicator which stores information if a file has a primary completion for female indicator
 *
 * @author Foothill College, Tanvi Waghela
 */
public class PrimaryCompletionRateFemaleIndicator extends Indicator {

    private double primaryCompletionRateFemale;

    /**
     * Initializes a PrimaryCompletionRateFemaleIndicator object that stores information of the file
     *
     * @param year A year that is between the year range of the file
     */
    public PrimaryCompletionRateFemaleIndicator(int year) {
        super(year);
        primaryCompletionRateFemale = INVALID_DATA;
    }

    /**
     * Initializes a PrimaryCompletionRateFemaleIndicator object that stores information of the file if the indicator is primary completion rate for female
     *
     * @param year A year that is between the year range of the file
     */
    public PrimaryCompletionRateFemaleIndicator(int year, double primaryCompletionRateFemale) {
        super(year);
        this.primaryCompletionRateFemale = primaryCompletionRateFemale;
    }

    /**
     * Stores the data given
     *
     * @param data Data from the file
     */
    public void setData(double[] data) {
        primaryCompletionRateFemale = data[0];
    }

    /**
     * Gives the user the data
     *
     * @return Returns a piece of data
     */
    public double[] getData() {
        return new double[]{primaryCompletionRateFemale};
    }

    /**
     * Creates a table representation of the PrimaryCompletionRateFemaleIndicator object
     *
     * @return Returns a string representation of this object
     */
    public String toString() {
        if (primaryCompletionRateFemale == INVALID_DATA) {
            return String.format("%15s", "()");
        } else {
            return String.format("%15s", "(" + String.format("%.2f", primaryCompletionRateFemale) + ")");
        }
    }
}
