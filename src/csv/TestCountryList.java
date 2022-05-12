package csv;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *  Tests the CountryList class, which holds a linked list of CountryNode objects.
 *
 * @author Foothill College, Tanvi Waghela
 */
public class TestCountryList
{	
	// Sets the Scanner object to the beginning of the input stream
	// Since we will be reading user input in more than one place.
	// To avoid incorrectly closing/reopening closing the stream,
	// the reference to the stream is set as a class attribute.
	/*public static Scanner keyboard = new Scanner(System.in);

	/**
	 * Builds a linked list of nodes where the data is a Country object.
	 * The size of the list depends on the user input. The countries added
	 * to the list are randomly selected.
	 * @param allCountries      An array of Country objects
	 */
	/*private static LinkedList<Country> createRandomListOfCountries(Country [] allCountries)
	{
		int requestedSize;
		do
		{
			// Prompts the user for the number of elements they want to add to the list.
			System.out.println("How many countries do you want to add to the list?");
			requestedSize = Integer.parseInt( keyboard.nextLine() );
		} while (requestedSize < 1);

		// Used to generate random numbers
		Random random = new Random();

		// TODO: Define the CountryNode class to hold the data for one Country object 
		//       and a reference to the next node.
		//       Define the CountryList class to be a singly list list of CountryNode objects.
		LinkedList<Country> selectedCountries = new LinkedList<>();

		for (int i = 0; i < requestedSize; i++)
		{
			// Selects a random index of the Country data array
			int selectedIndex = random.nextInt(allCountries.length);

			// TODO: Define the add() method in class such that it adds the randomly
			//       selected Country to the *end* of the list.
			Country countryToAdd = allCountries[selectedIndex];
			System.out.printf("Adding country with name %s to the end of the list.\n", countryToAdd.getName());
			selectedCountries.add(countryToAdd);
		}

		// TODO: Provide the String representation of the linked list by traversing
		//       all the nodes. The result should be the data at each node.
		System.out.println(selectedCountries.toString());
		
		System.out.println("Done with creating random linked list of countries.\n");
		return selectedCountries;
	}

	/**
	 * Displays the names of countries. 
	 * @param countries  array of Country objects
	 */
	/*protected static void displayCountryNames(Country [] countries)
	{
		String countryNames = "";
		int counter = 0;

		for (int i = 0; i < countries.length; i++)
		{
			// Concatenates the name of countries
			countryNames += " " + countries[i].getName();
			// uses a ternary operator to prettify the output
			countryNames += (counter+1) % 50 == 0 ? "\n" : ",";
			counter++;
		}
		System.out.println("\nCountry names:\n" + countryNames + "\n");
	}

	/**
	 * Display the entire data table of the country such that it is readable.
	 * Note: This requires that you experiment with the formatting of the output
	 * @param countries  array of Country objects
	 */
	/*protected static void displayCountries(Country [] countries)
	{
		StringBuilder countryInfo = new StringBuilder();
		int startingYear = countries[0].getStartYear();
		int numYears = countries[0].getEndYear()-startingYear+1;

		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			// appends year header
			if (countryIndex % 5 == 0)
			{
				countryInfo.append(String.format("\n%20s", "Country Name"));
				for(int yearIndex = 0; yearIndex < numYears; yearIndex++)
					countryInfo.append(String.format("%18d  ", (startingYear+yearIndex)));
				countryInfo.append("\n");
			}
			// appends indicator data
			countryInfo.append(countries[countryIndex].toString());
		}

		System.out.println("\nCountries:\n" + countryInfo + "\n");
	}

	/**
	 * Performs a linear search of the array of Country objects based on the name of the Country.
	 * @param requestedCountryName  Name of the Country object to search for
	 * @param countries	One-dimensional array of Country objects.
	 * @return	The Country object if found. Otherwise, null.
	 */
	/*private static Country findCountry(String requestedCountryName, Country[] countries)
	{
		for (int i = 0; i < countries.length; i++)
		{				
			// TODO: In class Country override the equals method such that it returns
			//       true if the current object has the same name.
			if (countries[i] != null && countries[i].equals(requestedCountryName))
			{
				return countries[i];
			}
		}
		return null;
	}

	/**
	 * Prints out the contents of a double array
	 * @param requestedEnrollmentPeriod   one-dimensional array of enrollment data.
	 */
	/*private static void displayEnrollmentPeriod(String countryName, Indicator[] requestedEnrollmentPeriod)
	{
		System.out.printf("%20s ", countryName);
		for(int i = 0; i < requestedEnrollmentPeriod.length; i++)
		{
			if (requestedEnrollmentPeriod[i] == null)
				System.out.printf("     ," + "   ");
			else
				System.out.printf("%s   ", requestedEnrollmentPeriod[i].toString());
		}
		System.out.println("");
	}

	/**
	 * Displays the menu option for different indicators.
	 * Note: Update the menu option if you add additional input files or categories.
	 */
	/*private static int printMenu()
	{	
		int selection = -1;
		while(true)
		{
			String userRequest = "";
			try
			{
				System.out.println("Select an indicator to parse. Enter a number between 0 to 4.");
				System.out.println("Available indicators are:");
				System.out.println("0. Invalid data (for debugging)");
				System.out.println("1. GDP per Capita (short for debugging)");
				System.out.println("2. School Enrollment (short for debugging)");
				System.out.println("3. GDP per Capita");
				System.out.println("4. School Enrollment");
				userRequest = keyboard.nextLine();
				selection = Integer.parseInt(userRequest);
				if (selection >= 0 && selection <=4)
					break;
			}
			catch (NumberFormatException ne) 
			{
				System.out.printf("Invalid input %s. ", userRequest);
			}
		}
		return selection;
	}

	/**
	 * Tests the contains() method of the CountryList class. 
	 * @param selectedCountryList  list of countries to search
	 */
	/*private static void testSearchInList(LinkedList<Country> selectedCountryList)
	{		
		// Check if the name of a country is in the list.
		// If the country is found, print the details.
		// Otherwise output not found.
		System.out.println("\nWhat is the name of the country you want to search for?");
		String countryNameToFind = keyboard.nextLine();

		// TODO: Define a Country constructor which will create a dummy Country object
		//       that only holds only the name of the Country.
		Country tmpCountry = new Country(countryNameToFind);
		
		// TODO: In class CountryList define the contains() method so that it
		//       traverses the list and checks whether a CountryNode exists whose
		//       data is equal to the argument being passed in.
		// NOTE: In order to call contains() method in our linked list,
		//       we need to define a constructor for our Country class
		//       that takes in a String. This Country object will have
		//       no other useful information, specifically no Indicator data.
		Country foundCountry = selectedCountryList.contains(tmpCountry);
		if (foundCountry != null)
		{
			System.out.println("Country " + countryNameToFind + " found:\n" + foundCountry);
		}
		else
		{
			System.out.println("Country " + countryNameToFind + " not found.");	
		}
		System.out.println("Done with searching for a country name.\n");
	}

	/**
	 * Tests the getIndicatorForPeriod() method of the Country class.
	 * @param selectedCountryList  list of countries to search
	 */
	/*private static void testFindingIndicatorDataInList(LinkedList<Country> selectedCountryList)
	{
		System.out.printf("\nWhat is the index of the country you want data on? (Enter a index between 0 and %d)\n", 
				selectedCountryList.size()-1);
		System.out.println("Available countries are:");
		System.out.println(selectedCountryList.toString());
		
		String userRequest = "";
		int selectedIndex = -1;
		
		try 
		{
			userRequest = keyboard.nextLine();
			selectedIndex = Integer.parseInt(userRequest);
			Country foundCountry = selectedCountryList.getIndex(selectedIndex);

			System.out.printf("\nWhat period are you interested in? Available years are from %d to %d.\n", 
					foundCountry.getStartYear(), foundCountry.getEndYear());
			System.out.println("Enter [starting year],[end year].");
			String period = keyboard.nextLine();
			String [] tokens = period.split(",");
			int requestedStartYear = Integer.parseInt(tokens[0]);
			int requestedEndYear = Integer.parseInt(tokens[1]);

			// Stores data between requested start year and end year
			Indicator [] requestedEnrollmentPeriod;
			requestedEnrollmentPeriod = foundCountry.getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(foundCountry.getName(),requestedEnrollmentPeriod);
		}
		catch (NumberFormatException ne) 
		{
			System.out.printf("Invalid input %s. ", userRequest);
		}
		catch (IndexOutOfBoundsException exc)
		{
			System.err.printf("ERROR: Requested index %d is out of range. ", selectedIndex);
			System.err.printf("Valid element positions are (index >= 0 && index < %d).", selectedCountryList.size());
		}
	}

	/**
	 * Creates an object of type CSVParser which parses a CSV file. If
	 * the format of the CSVParser is valid, creates a Country object
	 * from each parsed line. 
	 * Creates a CountryList object. Tests the linked list by adding 
	 * random Country object(s) to the list. Tests the created list by:
	 * - searching the list for a Country by name and 
	 * - by searching for available Indicator data by a year range.
	 * @param args  Not used.
	 */
	/*public static void main(String[] args)
	{
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).

		// Example of invalid input file
		final String [] INVALID_INPUT= {"resources/childrenEnrolled_invalid.csv"};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT_SHORT = { "resources/gdpPerCapita_short_12years.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT_SHORT = { "resources/childrenEnrolledInPrimary_short_12years.csv", 
				"resources/childrenEnrolledInSecondary_short_12years.csv"
		};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT = { "resources/gdpPerCapita.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT = { "resources/childrenEnrolledInPrimary.csv", 
				"resources/childrenEnrolledInSecondary.csv"
		};

		// Prompts the user and asks for a selection.
		int selection = printMenu();
		final String [] filenames;

		// Determines the input file pathname.
		IndicatorType selectedIndicator;
		if (selection == 1 || selection == 3)
		{
			selectedIndicator = IndicatorType.GDP_PER_CAPITA;
			filenames = selection == 1 ? GDP_INPUT_SHORT : GDP_INPUT;
		}
		else if (selection == 2 || selection == 4)
		{
			// Note: alternatively we can set the selected indicator as SCHOOL_ENROLLMENT_SECONDARY
			selectedIndicator = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
			filenames = selection == 2 ? ENROLLMENT_INPUT_SHORT : ENROLLMENT_INPUT;
		}
		else 
		{
			selectedIndicator = IndicatorType.INVALID;
			filenames = INVALID_INPUT;
		}
		
		// For debugging purposes
		if (selectedIndicator.equals(IndicatorType.SCHOOL_ENROLLMENT_PRIMARY) || selectedIndicator.equals(IndicatorType.SCHOOL_ENROLLMENT_SECONDARY)) 
		{
			System.out.println("The selected indicator is SCHOOL_ENROLLMENT");
		} 
		else 
		{
			System.out.println("The selected indicator is " + selectedIndicator);
		}

		// TODO: Create the class Country to hold the data for one or more indicators 
		Country [] countries = null;

		for (int currentFileIndex = 0; currentFileIndex < filenames.length; currentFileIndex++)
		{
			// Parse the CSV data file *based* on the input filename.
			CSVParser parser;
			try 
			{
				parser = new CSVParser(filenames[currentFileIndex]);
				// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
				// 			School Enrollment In Primary (% net) updated at 7/25/18
				//
				// Given the gdpPerCapita_short_12years.csv file, the output is:
				// 			GDP per capita (current US$) updated at 8/28/18

			} 
			catch (FileNotFoundException e) 
			{
				System.err.printf("File name %s not found.", filenames[currentFileIndex]);
				return;
			}
			catch (InvalidFileFormatException e)
			{
				System.err.printf("Invalid file format %s\n", e.getMessage());
				return;
			}

			// For debugging purposes
			System.out.println("\nParsing filename " + filenames[currentFileIndex]);

			// Provides the indicator read from the input file.
			IndicatorType indicatorType = parser.getIndicatorType();

			// NOTE: You can assume that the format of all CSV input files are the same with the same:
			//       Number of countries
			//       Name of countries
			//       Year labels
			String [] countryNames = parser.getCountryNames();
			int [] yearLabels = parser.getYearLabels();
			double [][] parsedTable = parser.getParsedTable();		

			// Check if the array of countries has been initialized by a previous iteration.
			if (countries == null)
			{
				// An array of Country objects.
				// NOTE: Here we are no longer using the two dimensional array from EnrollmentData class.
				//       Instead, each country will hold it's own data.
				//       So, we no longer need the EnrollmentData class.
				countries = new Country[countryNames.length];				 
			}

			// Note: We are moving away from the two dimensional array of Indicator objects
			//       to a single dimensional array of Country objects where each
			//       Country object stores a single dimensional array of Indicators.
			// Refers to a Country object
			Country foundCountry;

			// Go through each country name parsed from the CSV file.
			// If the country name has not bee seen before, create a new object of
			// type Country to hold an array of Indicator objects.
			for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
			{
				int numberOfYears = yearLabels.length;   // alternatively numberOfYears = dataTable[countryIndex].length;

				// Checks if the Country has been seen before.
				// Note: The findCountry() method will return a non-null object if a previous
				//       iteration of the outer for-loop (i.e. using the counter currentFileIndex)
				//       has parsed data for the current country name.
				foundCountry = findCountry(countryNames[countryIndex], countries);

				if (foundCountry == null)
				{
					// Create a Country object
					// TODO: Create a class constructor which takes two arguments:
					//       1) a String for the name of the country
					//       2) an integer for the number of data for each country
					// NOTE: Similar to the previous project we'll assume the data is well formed
					//       with the same number of years of data for all countries.
					foundCountry = new Country(countryNames[countryIndex], numberOfYears);
				}

				// Get the parsed array of doubles for the current Country object
				double [] dataForAllYears = parsedTable[countryIndex];

				// Go through each year of data read from the CSV file.
				for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
				{
					// Refers to the current indicator
					Indicator dataForOneYear = null;

					// Checks if a previous input file has already added the current year.
					// Note: Assume the CSV files are well formed with same number of years
					//       and that the years are consecutive.
					dataForOneYear = foundCountry.getIndicatorForYear(yearLabels[yearIndex]);

					// Recall that each CSV input file we read will provide us one data value.
					switch(indicatorType)
					{
					case GDP_PER_CAPITA:
						// When true then we have not seen this Country before.
						if (dataForOneYear == null)
						{
							dataForOneYear = new GDPIndicator(yearLabels[yearIndex]);
						}
						// The overridden method of the abstract class requires a one dimensional array.
						// Note: Curly braces specify that we are creating and initializing an array in place.
						double data[] = {dataForAllYears[yearIndex]};
						// Uses the overriden method to set the data
						((GDPIndicator)dataForOneYear).setData(data);
						break;
					case SCHOOL_ENROLLMENT_PRIMARY:
						if (dataForOneYear == null)
						{
							dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						}
						((SchoolEnrollmentIndicator)dataForOneYear).setPrimaryEnrollment(dataForAllYears[yearIndex]);
						break;
					case SCHOOL_ENROLLMENT_SECONDARY:
						if (dataForOneYear == null)
						{
							// TODO: Update the child class SchoolEnrollmentIndicator to hold a second 
							//       piece data for the netSecondary.
							dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						}
						((SchoolEnrollmentIndicator)dataForOneYear).setSecondaryEnrollment(dataForAllYears[yearIndex]);
						break;
					default:
						break; 
					}
					// Initializes the year.
					foundCountry.setIndicatorForYear(yearLabels[yearIndex], dataForOneYear);	
				}

				// Add the newly created country to the one dimensional array.
				countries[countryIndex] = foundCountry;
			} // End of for loop traversing the array of Country objects
		} // End of the for loop parsing the CSV file(s)

		// Output the names of the countries added to the list
		displayCountryNames(countries);
		// Example:
		// Names of countries in list:
		// 0 United States, 1 Argentina, 2 Egypt, Arab Rep., 
		
		// The following test methods assume we are given the childrenEnrolledIn*_short_12years.csv file(s).
		// Also, assume the user selects to add 3 countries to the list. 
		// The following serve as examples. Yours may look different from below. 

		// Tests the CountryList class by adding a random number of entries
		// from the array of Country objects.
		LinkedList<Country> selectedCountryList = createRandomListOfCountries(countries);
		// Example:
		// How many countries do you want to add to the list?
		// 3
		// Adding country with name United States to the end of the list.
		// Adding country with name Argentina to the end of the list.
		// Adding country with name Egypt, Arab Rep. to the end of the list.

		// Tests searching the LinkedList
		testSearchInList(selectedCountryList);
		// Index         Country Name              2006                2007                2008                2009                2010                2011                2012                2013                2014                2015                2016  
		//     0        United States             (,88.29)            (,88.39)            (,88.56)            (,87.94)            (,86.70)            (,87.63)            (,87.33)            (,88.12)            (,88.90)            (,90.83)                  ()                  ()  
		//     1            Argentina             (,79.11)            (,79.20)            (,80.35)            (,82.41)            (,84.00)            (,85.22)            (,87.32)            (,88.16)            (,88.25)            (,88.55)                  ()                  ()  
		//     2     Egypt, Arab Rep.                   ()                  ()                  ()                  ()                  ()                  ()                  ()                  ()            (,79.09)                  ()            (,81.49)                  ()  
		// What is the name of the country you want to search for?
		// Egypt, Arab Rep.
		// Country Egypt, Arab Rep. found:
		//     Egypt, Arab Rep.                   ()                  ()                  ()                  ()                  ()                  ()                  ()                  ()            (,79.09)                  ()            (,81.49)                  ()  

		// Tests retrieving indicator data in the list.
		testFindingIndicatorDataInList(selectedCountryList);
		// Note: Omitting table output. 
		// What is the index of the country you want data on? (Enter a index between 0 and 2)
		// 0
		//
		// What period are you interested in? (Enter [starting year],[end year].)
		// 2010,2017
		//         Country Name            2010             2011             2012             2013             2014             2015             2016             2017  
		//        United States        (,86.70)         (,87.63)         (,87.33)         (,88.12)         (,88.90)         (,90.83)               ()               ()  

		//
		// TODO: For full credit, include test cases for two different lists of different sizes. 
		//       Your test case must show that you can:
		//       - add to the end of the list,
		//       - find a country name that does exist in the list,
		//       - handle searching for a country name that does not exist in the list
		//       - get Indicator data for a period (Hint: Reuse your previous implementation).
		//
		// TODO: Test your implementation such that it can handle a variety of input files and Indicators.

		System.out.println("\nDone with TestCountryList.\n");
	}
*/
}
