package model;

/**
 * An enum that indicates what the indicator type of the file is
 *
 * @author Foothill College, Tanvi Waghela
 */

public enum IndicatorType{
    SCHOOL_ENROLLMENT_PRIMARY ("School Enrollment In Primary (% net)"),
    SCHOOL_ENROLLMENT_SECONDARY ("School Enrollment In Secondary (% net)"),
    GDP_PER_CAPITA ("GDP per capita (current US$)"),
    INVALID ("Invalid Data"),
    SCHOOL_ENROLLMENT("School Enrollment In Primary and Secondary (% net)"),
    PRIMARY_COMPLETION_RATE_FEMALE("Primary completion rate female (% of relevant age group)");

    /**
     * Adds a label to the conditions
     * @param label
     */
    IndicatorType(String label){
        this.label = label;
    }

    private String label;

    /**
     * Gives the user the label
     * @return Returns the label
     */
    public String getLabel(){
        return label;
    }
}
