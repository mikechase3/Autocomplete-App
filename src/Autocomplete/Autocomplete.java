package Autocomplete;

public class Autocomplete {

    //Member Variables
	Term[] terms;
	int[] someArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8}; //TODO Delete

	//Constructor
    /**
     * Initializes the data structure from the given array of terms
     * @param terms
     */
	public Autocomplete(Term[] terms)
	{
		this.terms=terms;
	}

	//Member Methods
    /**
     * Returns all terms that start with the given prefix, in descending order of weight
     * @param prefix
     * @return
     */
    public Term[] allMatches(String prefix){

    }

}

//>>>>>>> 63e9b4bd3fbde97c6d41eabf20ce2386a5671654