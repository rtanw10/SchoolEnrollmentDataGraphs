package model;

import java.util.Iterator;

//extra credit portion

/**
 * A CountryFemaleCompletion object stores the data, name of country, and the year labels for a single country
 *
 * @author Foothill College, Tanvi Waghela
 */
public class CountryFemaleCompletion {
    private String name;
    private LinkedList<Indicator> indicators;
    private int minYear;
    private int maxYear;

    /**
     * Initializes a single CountryFemaleCompletion object that will store the name of country
     * @param name
     */
    public CountryFemaleCompletion(String name){
        this.name = name;
        minYear = Integer.MAX_VALUE;
        maxYear = 0;
        indicators = new LinkedList<Indicator>();
    }

    /**
     * Adds a piece of data stored in an indicator object to the linked list
     * @param newData A piece of data stored in an indicator object
     */
    public void addIndicator(Indicator newData){
        if(newData.getYear() < minYear){
            minYear = newData.getYear();
        }
        else if(newData.getYear() > maxYear){
            maxYear = newData.getYear();
        }
        indicators.add(newData);
    }

    /**
     * Checks whether a CountryFemaleCompletion object is equal to another CountryFemaleCompletion object
     * @param countryName  The name of the other country
     * @return  Returns true if the countries are equal and false otherwise
     */
    public boolean equals(Object countryName){
        if(countryName instanceof String){
            if(name.equals((String)(countryName))){
                return true;
            }
            return false;
        }
        else if(countryName instanceof Country){
            if(name.equals(((Country) countryName).getName())){
                return true;
            }
            return false;
        }
        return false;

    }

    /**
     * Gives the user the name of the country
     * @return Returns the name of the country
     */
    public String getName(){
        return name;
    }

    /**
     * Gives the user the start year of the data
     * @return  Returns the start year
     */
    public int getStartYear(){
        return minYear;
    }

    /**
     * Gives the user the end year of the data
     * @return  Returns the end year
     */
    public int getEndYear(){
        return maxYear;
    }

    /**
     * Gives the user the indicator that contains the year wanted
     * @param wantedYear  The year the user wanted the indicator for
     * @return  Returns an indicator that contains the year wanted
     * @throws IllegalArgumentException Throws IllegalArgumentException if the year wanted is invalid
     */
    public Indicator getIndicatorForYear(int wantedYear) throws IllegalArgumentException {
        Iterator<Indicator> iterator = indicators.iterator();
        while(iterator.hasNext()){
            if (iterator.next() == null) {
                return null;
            }
            else if(iterator.next().getYear() == wantedYear){
                return iterator.next();
            }
        }
        throw new IllegalArgumentException("Invalid year");
    }

    /**
     * Sets the indicator to have the data that the user wants to store in the indicator that contains the requested year
     * @param requestedYear  The year that the user wants to change the data for
     * @param data The new data that the user would like to replace the old data with
     */
    public void setIndicatorForYear(int requestedYear, Indicator data){
        Iterator<Indicator> iterator = indicators.iterator();
        int index = 0;
        while (iterator.hasNext()){
            if (iterator.next() == null) {
                addIndicator(data);
                return;
            }
            else if(iterator.next().getYear() == requestedYear){
                indicators.insertAtIndex(data, index);
                return;
            }
            index++;
        }
        throw new IllegalArgumentException("Invalid Year");
    }

    /**
     * Gives the user the range of indicators according to the start and end year requested by the user
     * @param requestedStartYear  The start year of the requested year range
     * @param requestedEndYear  The end year of the requested year range
     * @return  Returns an array of Indicators that contain the year range requested by the user
     */
    public Indicator[] getIndicatorForPeriod(int requestedStartYear, int requestedEndYear){
        Iterator<Indicator> iterator = indicators.iterator();
        int index = 0;
        if(requestedEndYear < requestedStartYear){
            throw new IllegalArgumentException("Inverted requested years");
        }
        else if(((requestedStartYear < getStartYear() && requestedEndYear < getStartYear()) || (requestedStartYear > getEndYear() && requestedEndYear > getEndYear())) || (requestedStartYear < getStartYear() && requestedEndYear > getEndYear())){
            throw new IllegalArgumentException("Requested years are out of range");
        }
        else {
            int startingYear = getStartYear();
            int endingYear = getEndYear();
            int startYearIndex = 0;
            int endYearIndex = 0;
            if((requestedStartYear >= startingYear && requestedStartYear < endingYear) && requestedEndYear > endingYear){
                String errorMessage1 = "Invalid request of " + requestedStartYear + ", " + requestedEndYear + ". ";
                requestedEndYear = endingYear;
                System.out.println(errorMessage1 + "Using valid subperiod for " + name + " is " + requestedStartYear + " to " + requestedEndYear + ". ");
            }
            else if(requestedStartYear < startingYear && requestedEndYear == startingYear){
                String errorMessage1 = "Invalid request of " + requestedStartYear + ", " + requestedEndYear + ". ";
                System.out.println(errorMessage1 + "Using valid subperiod for " + name + " is " + requestedEndYear + ". ");
                Indicator[] indicators = new Indicator[1];
                indicators[0] = getIndicatorForYear(requestedEndYear);
                return indicators;
            }
            else if(requestedEndYear > endingYear && requestedStartYear == endingYear){
                String errorMessage1 = "Invalid request of " + requestedStartYear + ", " + requestedEndYear + ". ";
                System.out.println(errorMessage1 + "Using valid subperiod for " + name + " is " + requestedStartYear + ". ");
                Indicator[] indicators = new Indicator[1];
                indicators[0] = getIndicatorForYear(requestedStartYear);
                return indicators;
            }
            else if((requestedEndYear > startingYear && requestedEndYear <= endingYear) && requestedStartYear < startingYear){
                String errorMessage1 = "Invalid request of " + requestedStartYear + ", " + requestedEndYear + ". ";
                requestedStartYear = startingYear;
                System.out.println(errorMessage1 + "Using valid subperiod for " + name + " is " + requestedStartYear + " to " + requestedEndYear + ". ");
            }
            Indicator[] wantedData = new Indicator[(requestedEndYear - requestedStartYear) + 1];
            while(iterator.hasNext()){
                Indicator next = iterator.next();
                if(next.getYear() == requestedStartYear){
                    startYearIndex = index;
                }
                else if(next.getYear() == requestedEndYear){
                    endYearIndex = index;
                }
                index++;
            }
            for (int i = startYearIndex, j = 0; i < endYearIndex+1; i++, j++) {
                wantedData[j] = indicators.getIndex(j);
            }
            return wantedData;
        }
    }

    /**
     * Gives the user a row of data containing the country name and its data
     * @return Returns a string representation of the CountryFemaleCompletion Object
     */
    public String toString(){
        String tableOutput = "";
        tableOutput += String.format("%55s", name);
        for (Indicator data : indicators) {
            for (double enrollmentData : data.getData()) {
                if(enrollmentData == -1.0){
                    tableOutput += String.format("%20s", "()");
                }
                else{
                    tableOutput += String.format("%20s", "(" + String.format("%.2f", enrollmentData) + ")");
                }
            }
        }
        return tableOutput + "\n";
    }

    /**
     * Gives the user the entire data for all years
     * @return Returns the entire data for all years
     */
    public LinkedList<Indicator> getIndicators(){
        return indicators;
    }
}

