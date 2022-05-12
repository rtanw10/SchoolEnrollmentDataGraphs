project folder: tanviwaghela-project06/

Brief description of submitted files:

src/csv/CSVParser.java
    - One object of class CSVParser reads a csv file containing enrollment data and parses it into different arrays

src/csv/CSVDataParser.java
    - One object of class CSVDataParser reads a csv file containing female completion rate data and parses it into different arrays

src/csv/InvalidFileFormatException.java
    - One object of class InvalidFileFormatException makes an error message for when there is an invalid file format error

src/csv/TestCountryList.java
    - The main application.
    - Includes main() for running the application.

src/csv/TestGenericList.java
    - The main application.
    - Includes main() for running the application.

src/model/GDPIndicator.java
    - One object of class GDPIndicator stores information about a file, especially if its indicator is gdp

src/model/SchoolEnrollmentIndicator.java
    - One object of class SchoolEnrollmentIndicator stores information about a file, especially if its indicator is primary or secondary

src/model/PrimaryCompletionRateFemaleIndicator.java
    - One object of class PrimaryCompletionRateFemaleIndicator stores information about a file, especially if its indicator is primary female completion rate

src/model/Indicator.java
    - One object of class Indicator creates methods that its sub class can define

src/model/IndicatorType.java
    - One object of class Indicator defines the indicator type of the file

src/model/Country.java
    - One object of class Country stores info about a single country

src/model/CountryFemaleCompletion.java
    - One object of class CountryFemaleCompletion stores info about a single country

src/model/LinkedList.java
    - One object of class LinkedList is a list that contains multiple CountryNode objects
    - One object of class Node is a object that contains data for an object
    - One object of class ListIterator can iterate through a LinkedList

src/model/DataModel.java
    - One object of class DataModel models data for an object

resources/childrenEnrolledInPrimary.csv
    - A CSV (Comma Separated Value) file.
    - First five rows contain the number of countries, number of years, the data source, and indicator
    - Lines 6 to EOF (end of file) contain the country name and the enrollment data for each year in the format:
      country name, data, data,... etc.

resources/childrenEnrolledInPrimary_short_12years.csv
    - A CSV (Comma Separated Value) file.
    - First five rows contain the number of countries, number of years, the data source, and indicator
    - Lines 6 to EOF (end of file) contain the country name and the enrollment data for each year in the format:
      country name, data, data,... etc.

resources/childrenEnrolledInSecondary_short_12years.csv
    - A CSV (Comma Separated Value) file.
    - First five rows contain the number of countries, number of years, the data source, and indicator
    - Lines 6 to EOF (end of file) contain the country name and the enrollment data for each year in the format:
      country name, data, data,... etc.

resources/childrenEnrolledInSecondary.csv
    - A CSV (Comma Separated Value) file.
    - First five rows contain the number of countries, number of years, the data source, and indicator
    - Lines 6 to EOF (end of file) contain the country name and the enrollment data for each year in the format:
      country name, data, data,... etc.

resources/gdpPerCapita.csv
    - A CSV (Comma Separated Value) file.
    - First five rows contain the number of countries, number of years, the data source, and indicator
    - Lines 6 to EOF (end of file) contain the country name and the enrollment data for each year in the format:
      country name, data, data,... etc.


resources/gdpPerCapita_short_12years.csv
    - A CSV (Comma Separated Value) file.
    - First five rows contain the number of countries, number of years, the data source, and indicator
    - Lines 6 to EOF (end of file) contain the country name and the enrollment data for each year in the format:
      country name, data, data,... etc.

resources/childrenEnrolled_invalid.csv
    - A CSV (Comma Separated Value) file.
    - First three rows contain the data source and indicator
    - Lines 4 to EOF (end of file) contain the country name and the enrollment data for each year in the format:
      country name, data, data,... etc.

resources/Graph_Of_Primanry_Female_Completion_Rate.png.csv
    - Displayed graph of Primary Completion Rate of Females

src/view/ChartGraph.java
    - The main application.
    - Includes main() for running the application.

src/view/CountrySelector.java
    - One object of class CountrySelector randomly builds a LinkedList of Country objects to be used in the graph

src/view/CountryFemaleCompletionSelector.java
    - One object of class CountryFemaleCompletionSelector randomly builds a LinkedList of CountryFemaleCompletion objects to be used in the graph

src/view/GraphView.java
    - One object of class GraphView creates and updates a series of Country objects

resources/RUN.txt
    - console output of TestGenericList.

README.txt
    - description of submitted files

