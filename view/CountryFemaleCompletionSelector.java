package view;

import java.util.Random;

import model.Country;
import model.CountryFemaleCompletion;
import model.LinkedList;

//extra credit portion

/**
 * Randomly builds a LinkedList of CountryFemaleCompletion objects to be used in the graph
 * @author Foothill College, Bita M., Tanvi Waghela
 */
public class CountryFemaleCompletionSelector
{
    private LinkedList<CountryFemaleCompletion> selectedCountries;

    /**
     * Builds a linked list of random countries
     * @param allCountries      An array of CountryFemaleCompletion objects
     * @param requestedSize	The requested number of elements.
     */
    public CountryFemaleCompletionSelector(CountryFemaleCompletion[] allCountries, int requestedSize)
    {
        // Build the list out of a random selection of countries.
        Random random = new Random();

        // A singly linked list of country data.
        selectedCountries = new LinkedList<CountryFemaleCompletion>();
        for (int i = 0; i < requestedSize; i++)
        {
            int selectedIndex = random.nextInt(allCountries.length);
            selectedCountries.add(allCountries[selectedIndex]);
        }
    }

    /**
     * Accessor method for randomly selected list of CountryFemaleCompletion objects.
     * @return LinkedList of CountryFemaleCompletion objects.
     */
    public LinkedList<CountryFemaleCompletion> selectCountries()
    {
        return this.selectedCountries;
    }

}

