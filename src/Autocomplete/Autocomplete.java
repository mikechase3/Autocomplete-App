package Autocomplete;

import java.util.Comparator;

/**In this part, you will implement a data type that provides autocomplete functionality
for a given set of string and weights, 
using Term and BinarySearchDeluxe. 
To do so, sort the terms in lexicographic order; 
use binary search to find the all query strings 
that start with a given prefix; and sort the matching terms in descending order by weight. 
constructor:
	merge sort + byPrefixOrder(int r)
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
	{
		int length = terms.length;
		mergesort(terms,terms.begin,terms.end,byPrefixOrder(length));
	}

	//Member Methods
    /**
     * Returns all terms that start with the given prefix, in descending order of weight
     * @param prefix
     * @return
     */
    public Term[] allMatches(String prefix){
    	return terms;
    }
    
    private void mergesort(Term[] terms,int begin,int end, Comparator camp)
    {
    	if(begin>end) return;
    	int mid = begin+(end-begin)/2;
    	mergesort(terms,begin,mid,camp);
    	mergesort(terms,mid+1,end,camp);
    	merge(terms,begin,mid,end,camp);
    }
    private void merge(Term[] terms,int begin,int mid,int end,Comparator<T> camp)
    {
    	Term[] aux;
    	for(int i = begin;i<=end;i++) aux[i] = terms[i];    
    	int i = begin,j = mid,k = begin;
    	while(i<=mid&&j<=end&&k<end)
    	{
    		if(camp.compare(a[i], a[j])>0) terms[k++] = aux[j++];//a[i]>a[j],aux[k] = a[j]
    		else terms[k++] = aux[i++];
    	}
    	while(i<=mid) terms[k++] = aux[i++];
    	//j part has already there
    }
}

//>>>>>>> 63e9b4bd3fbde97c6d41eabf20ce2386a5671654