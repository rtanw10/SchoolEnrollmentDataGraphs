package model;

import java.io.FileNotFoundException;

import csv.CSVDataParser;
import csv.CSVParser;
import csv.InvalidFileFormatException;

/**
 * Provides access to CSV data.
 * @author Foothill College, Bita M., Tanvi Waghela
 */
public class DataModel 
{    
	/**
	 * data parsed from file
	 */
	private Country[] countryModel;

	/**
	 * data parsed from file
	 */
	private CountryFemaleCompletion[] completionModel;

	/**
	 * name of x-axis
	 */
	private String xAxisName;

	/**
	 * name of y-axis
	 */
	private String yAxisName;

	/**
	 * The indicator type.
	 */
	private IndicatorType indicatorType;

	/**
	 * Creates a model given an input file.
	 * @param filenames Name of the file
	 * @throws InvalidFileFormatException 
	 * @throws FileNotFoundException 
	 */
	public DataModel(final String [] filenames) throws FileNotFoundException, InvalidFileFormatException
	{
		// Holds the data for one or more indicators 
		countryModel = null;

		for (int currentFileIndex = 0; currentFileIndex < filenames.length; currentFileIndex++)
		{
			// For debugging purposes
			System.out.println("\nParsing filename " + filenames[currentFileIndex]);

			parseCSVFile(filenames[currentFileIndex]);
		} // end of the for loop parsing the CSV file(s)

		if (indicatorType == null)
		{
			System.out.println("Indicator type not found. File is not a known type.");
			return;
		}
		
		switch(indicatorType)
		{
		case GDP_PER_CAPITA:
		case SCHOOL_ENROLLMENT_PRIMARY:
		case SCHOOL_ENROLLMENT_SECONDARY:
			xAxisName = "Year";
			yAxisName = indicatorType.getLabel();
			break;
		default:
			System.out.println("File is not a known type.");
			return;
		}
	}

	/**
	 * Creates a model given an input file.
	 * @param filenames Name of the file
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException
	 */
	public DataModel(final String filenames) throws FileNotFoundException, InvalidFileFormatException
	{
		//extra credit portion
		// Holds the data for one or more indicators
		completionModel = null;
		// For debugging purposes
		System.out.println("\nParsing filename " + filenames);

		parseCompletionCSVFile(filenames);

		if (indicatorType == null)
		{
			System.out.println("Indicator type not found. File is not a known type.");
			return;
		}

		switch(indicatorType)
		{
			case PRIMARY_COMPLETION_RATE_FEMALE:
				xAxisName = "Year";
				yAxisName = indicatorType.getLabel();
				break;
			default:
				System.out.println("File is not a known type.");
				return;
		}
	}

	/**
	 * Reads and parses a csv file
	 * @param filename Name of the file
	 * @throws FileNotFoundException
	 * @throws InvalidFileFormatException
	 */
	private void parseCSVFile(final String filename) throws FileNotFoundException, InvalidFileFormatException
	{
		CSVParser parser;
		
		// Could potentially throw an exception:
		// - if the file is not found or 
		// - if the format is invalid
		parser = new CSVParser(filename);

		// Provides the indicator read from the input file.
		indicatorType = parser.getIndicatorType();

		// NOTE: You can assume that the format of all CSV input files are the same with the same:
		//       Number of countries
		//       Name of countries
		//       Year labels
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		

		// Check if the model has been initialized by a previous iteration.
		if (countryModel == null)
		{
			// An array of Country objects.
			countryModel = new Country[countryNames.length];
		}

		// Reference to a Country object
		Country foundCountry;

		// Used to check whether this Country object has been seen before.
		// If it has been send, then we will get a reference to the previous
		// Indicator object stored and update it.
		boolean initializingCountry;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < countryModel.length; countryIndex++)
		{
			// Assume the current Country has not been seen before
			initializingCountry = false;
			
			foundCountry = findCountry(countryNames[countryIndex], countryModel);

			if (foundCountry == null)
			{
				// Checks if the Country has been seen before.
				// Note: The findCountry() method will return a non-null object if a previous
				//       iteration of the outer for-loop (i.e. using the counter currentFileIndex)
				//       has parsed data for the current country name.
				foundCountry = new Country(countryNames[countryIndex]);
				initializingCountry = true;
			}

			// Get the parsed array of doubles for the current Country object
			double [] dataForAllYears = parsedTable[countryIndex];

			// Go through each year of data read from the CSV file.
			for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
			{
				Indicator dataForOneYear = null;
				if (!initializingCountry)
				{
					// Returns the Indicator data for a specific year.
					// Note: Assume the CSV files are well formed with same number of years
					//       and that the years are consecutive.
					dataForOneYear = foundCountry.getIndicatorForYear(yearLabels[yearIndex]);
				}

				// Recall that each CSV input file we read will provide us one data value.
				switch(indicatorType)
				{
				case GDP_PER_CAPITA:
					// if true then we have not seen this Country before
					if (dataForOneYear == null)
					{
						// TODO: Create the child class GDPIndicator to hold the data.
						dataForOneYear = new GDPIndicator(yearLabels[yearIndex]);
					}
					// The overriden method of the abstact class requires an one dimensional array
					double data[] = {dataForAllYears[yearIndex]};
					// Uses the overriden method to set the data
					((GDPIndicator)dataForOneYear).setData(data);
					break;
				case SCHOOL_ENROLLMENT_PRIMARY:
					if (dataForOneYear == null)
					{
						// TODO: Create the child class SchoolEnrollmentIndicator to hold the data.
						dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
					}
					((SchoolEnrollmentIndicator)dataForOneYear).setPrimaryEnrollment(dataForAllYears[yearIndex]);
					break;
				case SCHOOL_ENROLLMENT_SECONDARY:
					if (dataForOneYear == null)
					{
						// TODO: Create the child class SchoolEnrollmentIndicator to hold the data.
						dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
					}
					((SchoolEnrollmentIndicator)dataForOneYear).setSecondaryEnrollment(dataForAllYears[yearIndex]);
					break;
				default:
					break; 
				}

				// initialize the year
				foundCountry.addIndicator(dataForOneYear);		
			}

			// add the newly created country to the 1D array
			countryModel[countryIndex] = foundCountry;
		} // end of for loop traversing the array of Country objects		
	}

	/**
	 * Reads and parses a csv file
	 * @param filename Name of the file
	 * @throws FileNotFoundException
	 * @throws InvalidFileFormatException
	 */
	private void parseCompletionCSVFile(final String filename) throws FileNotFoundException, InvalidFileFormatException
	{
		//extra credit portion
		CSVDataParser parser;

		// Could potentially throw an exception:
		// - if the file is not found or
		// - if the format is invalid
		parser = new CSVDataParser(filename);

		// Provides the indicator read from the input file.
		indicatorType = parser.getIndicatorType();

		// NOTE: You can assume that the format of all CSV input files are the same with the same:
		//       Number of countries
		//       Name of countries
		//       Year labels
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();

		// Check if the model has been initialized by a previous iteration.
		if (completionModel == null)
		{
			// An array of Country objects.
			completionModel = new CountryFemaleCompletion[countryNames.length];
		}

		// Reference to a Country object
		CountryFemaleCompletion foundCountry;

		// Used to check whether this Country object has been seen before.
		// If it has been send, then we will get a reference to the previous
		// Indicator object stored and update it.
		boolean initializingCountry;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < completionModel.length; countryIndex++)
		{
			// Assume the current Country has not been seen before
			initializingCountry = false;

			foundCountry = findCountry(countryNames[countryIndex], completionModel);

			if (foundCountry == null)
			{
				// Checks if the Country has been seen before.
				// Note: The findCountry() method will return a non-null object if a previous
				//       iteration of the outer for-loop (i.e. using the counter currentFileIndex)
				//       has parsed data for the current country name.
				foundCountry = new CountryFemaleCompletion(countryNames[countryIndex]);
				initializingCountry = true;
			}

			// Get the parsed array of doubles for the current Country object
			double [] dataForAllYears = parsedTable[countryIndex];

			// Go through each year of data read from the CSV file.
			for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
			{
				Indicator dataForOneYear = null;
				if (!initializingCountry)
				{
					// Returns the Indicator data for a specific year.
					// Note: Assume the CSV files are well formed with same number of years
					//       and that the years are consecutive.
					dataForOneYear = foundCountry.getIndicatorForYear(yearLabels[yearIndex]);
				}

				// Recall that each CSV input file we read will provide us one data value.
				switch(indicatorType)
				{
					case PRIMARY_COMPLETION_RATE_FEMALE:
						// if true then we have not seen this Country before
						if (dataForOneYear == null)
						{
							// TODO: Create the child class GDPIndicator to hold the data.
							dataForOneYear = new PrimaryCompletionRateFemaleIndicator(yearLabels[yearIndex]);
						}
						// The overriden method of the abstact class requires an one dimensional array
						double data[] = {dataForAllYears[yearIndex]};
						// Uses the overriden method to set the data
						((PrimaryCompletionRateFemaleIndicator)dataForOneYear).setData(data);
						break;
					default:
						break;
				}

				// initialize the year
				foundCountry.addIndicator(dataForOneYear);
			}

			// add the newly created country to the 1D array
			completionModel[countryIndex] = foundCountry;
		} // end of for loop traversing the array of Country objects
	}

	/**
	 * Performs a linear search of the array of Country objects based on the name of the Country.
	 * @param requestedCountryName  Name of the Country object to search for
	 * @param countries	One-dimensional array of Country objects.
	 * @return	The Country object if found. Otherwise, null.
	 */
	private static Country findCountry(String requestedCountryName, Country[] countries)
	{
		for (int i = 0; i < countries.length; i++)
		{				
			if (countries[i] != null && countries[i].equals(requestedCountryName))
			{
				return countries[i];
			}
		}
		return null;
	}

	/**
	 * Performs a linear search of the array of CountryFemaleCompletion objects based on the name of the country.
	 * @param requestedCountryName  Name of the CountryFemaleCompletion object to search for
	 * @param countries	One-dimensional array of CountryFemaleCompletion objects.
	 * @return	The CountryFemaleCompletion object if found. Otherwise, null.
	 */
	private static CountryFemaleCompletion findCountry(String requestedCountryName, CountryFemaleCompletion[] countries)
	{
		//extra credit portion
		for (int i = 0; i < countries.length; i++)
		{
			if (countries[i] != null && countries[i].equals(requestedCountryName))
			{
				return countries[i];
			}
		}
		return null;
	}

	/**
	 * Returns the parsed model.
	 * @return parsed model
	 */
	public Country[] getModel() {
		return countryModel;
	}

	/**
	 * Returns the parsed model.
	 * @return parsed model
	 */
	public CountryFemaleCompletion[] getCompletionModel() {
		//extra credit portion
		return completionModel;
	}

	/**
	 * Returns the indicator type.
	 * @return the indicator type
	 */
	public IndicatorType getIndicatorType()
	{	return this.indicatorType; }

	/**
	 * Returns x-axis name.
	 * @return x-axis name
	 */
	public String getXAxisName() {
		return xAxisName;
	}

	/**
	 * Returns y-axis name.
	 * @return y-axis name
	 */
	public String getYAxisName() {
		return yAxisName;
	}

}
