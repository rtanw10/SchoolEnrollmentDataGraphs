package csv;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import model.IndicatorType;

/**
 * Reads a csv file with enrollment data and parses the information that it read from the file
 *
 * @author Foothill College, Tanvi Waghela
 */
public class CSVParser {

    private String[] countryNames;
    private int[] yearLabels;
    private double[][] datatable;
    private int numberOfCountries;
    private IndicatorType indicator;
    final int numberIndex = 6;
    final int lineNumberForCountryNum = 3;
    final int lineNumberForYears = 4;
    final int countryIndex = 7;

    /**
     * Reads a csv file containing the enrollment data and initializes different array with information parsed from the csv file
     * @param path  Path to find the file to read
     * @throws FileNotFoundException  Error thrown if the file was not found
     * @throws InvalidFileFormatException  Error thrown if the file has something missing
     */
    public CSVParser(String path) throws FileNotFoundException, InvalidFileFormatException {
        Scanner scan = new Scanner(new File(path));
        int nextIndex = 0;
        int lineNumber = 0;
        boolean numberOfCountriesExist = false;
        boolean yearLabelsExist = false;
        int yearLength = 0;
        int countryLength = 0;
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(lineNumber > 0){
                if(line.substring(0,5).equals("Indic")){
                    if(line.substring(10, 24).equals("GDP per capita")){
                        indicator = IndicatorType.GDP_PER_CAPITA;
                    }
                    else if(line.substring(10, 38).equals("School Enrollment In Primary")){
                        indicator = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
                    }
                    else{
                        indicator = IndicatorType.SCHOOL_ENROLLMENT_SECONDARY;
                    }
                }
                else if(line.substring(0, numberIndex).equals("Number")){
                    String[] countryNum = line.split(",");
                    numberOfCountries = Integer.parseInt(countryNum[1]);
                    countryLength = numberOfCountries;
                    numberOfCountriesExist = true;
                }
                else if((!line.substring(0, numberIndex).equals("Number")) && lineNumber == lineNumberForCountryNum){
                    throw new InvalidFileFormatException("\"Number of Countries\" \nError on line " + lineNumber + ": " + line, path);
                }
                else if(line.substring(0, countryIndex).equals("Country")){
                    String[] labels = line.split(",");
                    yearLength = labels.length-1;
                    yearLabelsExist = true;
                }
                else if((!line.substring(0, countryIndex).equals("Country")) && lineNumber == lineNumberForYears){
                    throw new InvalidFileFormatException("\"Year Labels\" \nError on line " + lineNumber + ": " + line, path);
                }
            }
            lineNumber++;
        }
        countryNames = new String[countryLength];
        yearLabels = new int[yearLength];
        datatable = new double[countryNames.length][yearLabels.length];
        scan = new Scanner(new File(path));
        lineNumber = 0;
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(lineNumber > 3){
                if(line.substring(0, countryIndex).equals("Country")){
                    String[] labels = line.split(",");
                    for (int i = 1; i < labels.length; i++) {
                        yearLabels[i-1] = Integer.parseInt(labels[i]);
                    }
                }
                else{
                    String[] labels = line.split(",");
                    int begin = 1;
                    boolean changeLabels = false;
                    if(labels[0].substring(0,1).equals("\"")) {
                        begin = 2;
                        changeLabels = true;
                    }
                    for (int i = begin; i < labels.length; i++) {
                        if(labels[i].equals("") || labels[i].equals(" ")){
                            datatable[nextIndex][i-begin] = -1;
                        }
                        else{
                            datatable[nextIndex][i-begin] = Double.parseDouble(labels[i]);
                        }

                    }
                    if (changeLabels) {
                        countryNames[nextIndex] = labels[0] + "," + labels[1];
                    }
                    else {
                        countryNames[nextIndex] = labels[0];
                    }
                    nextIndex++;
                }
            }
            lineNumber++;
        }
    }

    /**
     * Gives the user an array with all the country names
     * @return Returns the array of country names
     */
    public String[] getCountryNames(){
        return countryNames;
    }

    /**
     * Gives the user an array with the year labels
     * @return Returns the array of year labels
     */
    public int[] getYearLabels(){
        return yearLabels;
    }

    /**
     * Gives the user an array with all the enrollment data
     * @return Returns the array of enrollment data
     */
    public double[][] getParsedTable(){
        return datatable;
    }

    /**
     * Gives the user a number that represents the number of countries
     * @return Returns the number of countries
     */
    public int getNumberOfYears(){
        return yearLabels.length;
    }

    /**
     * Gives the user the indicator type which determines which type of file was read
     * @return Returns the indicator type of the file
     */
    public IndicatorType getIndicatorType(){ return indicator; }
}

