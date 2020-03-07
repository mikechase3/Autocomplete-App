package Autocomplete;

import java.rmi.NoSuchObjectException;
import java.util.*;  
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

/**In this part, you will implement a data type that provides autocomplete functionality
for a given set of string and weights, 
using Term and BinarySearchDeluxe. 
To do so, sort the terms in lexicographic order; 
use binary search to find the all query strings 
that start with a given prefix; and sort the matching terms in descending order by weight. 
constructor:
	merge sort + byPrefixOrder()
allMatches:
	BinarySearchDeluxe->first & last;
	sort(Terms,first,last,byReverseWeightOrder());
	Mterms = Terms[first:last];
	return Mterms;
*/
//where to use the byPrefixOrder(int r)
public class Autocomplete {

    //Member Variables
	Term[] terms;
	//Constructor
    /**
     * Initializes the data structure from the given array of terms
     * @param terms
     */
	public Autocomplete(Term[] terms)
	{	//check if any of the entries in its argument array are null.
		this.terms = terms;
		if(terms == null) throw new java.lang.NullPointerException();
		this.terms = terms;
		int length = terms.length;
   	 //Arrays.parallelSort(terms, 0, length-1, Term.byPrefixOrder(length));
   	 mergesort(terms,0,length-1,Term.byPrefixOrder(length)); 
   	 //have to be this version,but something goes wrong with the comp.

	}

	//Member Methods
    /**
     * Returns all terms that start with the given prefix, in descending order of weight
     * @param prefix
     * @return
     * @throws NoSuchObjectException 
     */
	/*public Term[] allMatches(String prefix)
{
Comparator comp = Term.byPrefixOrder(prefix.length());
Term key = new Term(prefix, ??);
int begin = BinarySearchDeluxe.firstIndexOf(terms, key, comp);
if (..) return ..; // no matches
int end = ¡­;
Term[] match = new Term[end ¨C begin + 1];
¡­ // merge sort array match byReverseWeightOrder();
return match;
}*/
	// I guess Key is a type that hasn't specified.
    public Term[] allMatches(String prefix) throws NoSuchObjectException{
    	//Confirming correct input
    	if(prefix == null) throw new java.lang.NullPointerException();
		// Set-Up Local Variables
    	 Term term = new Term(prefix,(long)0);
    	 Term[] NoMatch = new Term[1];
    	 Term M = new Term("no match",0);
    	 NoMatch[0] = M;
    	 // Confirm required inputs for local variables
    	 if(term == null) throw  new java.lang.NullPointerException();
    	 if(NoMatch == null) throw  new java.lang.NullPointerException();
    	 NoMatch[0].query = "No match";
    	 term.query = prefix;
    	 int length = prefix.length();
    	 // Find first instance of the prefix in the given file
    	 int first = BinarySearchDeluxe.firstIndexOf(terms, term, Term.byPrefixOrder(length));
    	 // Test code
    	 //System.out.println("first: "+first);
    	 // Check to see if it should end early
    	 if(first == -1) return NoMatch;
    	 // Find the last index of the prefix in the given file
    	 int last = BinarySearchDeluxe.lastIndexOf(terms, term, Term.byPrefixOrder(length));
    	 // Creates the array to return with required space
    	 Term[] Mterms = new Term[last-first+1];
    	 //It's the matched terms:copies the terms from the file array to the return array
    	 for(int i = first;i<=last;i++) Mterms[i-first] = terms[i];
    	 //Arrays.sort(Mterms);
    	 //Arrays.parallelSort(Mterms, 0, Mterms.length-1, Term.byReverseWeightOrder());
    	 //sorts the return array in reverse weight order 
    	mergesort(Mterms,0,Mterms.length-1,Term.byReverseWeightOrder());
    	return Mterms;
    }
    // I guess Key is a type that hasn't specified.
    private void mergesort(Term[] terms,int begin,int end, Comparator comp)
    {
    	if(begin>=end) return;
    	int mid = begin+(end-begin)/2;
    	mergesort(terms,begin,mid,comp); //Merge sort with the first half
    	mergesort(terms,mid+1,end,comp); //Merge sort, break down second half
    	merge(terms,begin,mid,end,comp); //Merges the two halves together. 
    }
    private void merge(Term[] terms,int begin,int mid,int end,Comparator<Term> comp)
    {
    	Term[] aux = new Term[terms.length];
    	for(int i = begin;i<=end;i++) aux[i] = terms[i];    
    	int i = begin,j = mid+1,k = begin;// It should be j = mid+1, otherwise out of range.
    	while(i<=mid&&j<=end&&k<=end)
    	{
    		if(comp.compare(terms[i], terms[j])>0) terms[k++] = aux[j++];//a[i]>a[j],aux[k] = a[j]
    		else terms[k++] = aux[i++];
    	}
    	while(i<=mid) terms[k++] = aux[i++];
    	//j part has already there
    }
}