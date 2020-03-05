/**
 * Last updated March 4, 2020 at 10:24 PM
 *
 * March 4, 2020 at 10:24 PM: Updated required corner cases for "Term."
 */

package Autocomplete;

import java.util.Comparator;

public class Term implements Comparable<Term> {

	String query;
	long weight;
	/* Initializes a term with the given query string and weight*/
	public Term(String query, long weight ) throws NullPointerException, IllegalArgumentException //Corner Cases
	{
		this.query= query;
		this.weight= weight;

		//Required corner cases that the constructor will throw if things go wrong. TODO Give to Josh.
		if (query.equals(null)) throw new NullPointerException("Term.Term line ~16. Error: Query is null"); //TODO Remove? inefficient but on ASGN Page as "Corner Case?"
		if (weight < 0) throw new IllegalArgumentException("Term.Term line ~17. Error: Weight is negative.");
	}
	private static class ReverseWeightOrder implements Comparator<Term>
	{
		public int compare(Term o1,Term o2)
		{
			if(o1.weight>o2.weight)
			{
				return 1;
			}
			if(o1.weight<o2.weight)
			{
				return -1;
			}
			return 0;
		}
	}
	/* Compares the two terms in descending order by weight*/
	public static Comparator<Term> byReverseWeightOrder()
	{
		return new ReverseWeightOrder();
//return null;
	}

	/* Compares the two terms in lexicographic order but using only the first
	 * r  characters of each query
	 */
	public static Comparator<Term> byPrefixOrder(int r) throws IllegalArgumentException //TODO: Josh, Update.
	{
		if (r < 0){
			throw new IllegalArgumentException(); //TODO: Josh, Update.
		}
		return new PrefixOrder(r);
//return null;
	}
	private static class PrefixOrder implements Comparator<Term>
	{
		private int r;
		public PrefixOrder(int r)
		{
			this.r=r;
		}

		public int compare(Term o1,Term o2)
		{
			if(r > o1.query.length() || r>o2.query.length()){
				return o1.compareTo(o2);
			}

			Term t1= new Term(o1.query.subSequence(0, r).toString(),o1.weight);
			Term t2= new Term(o2.query.subSequence(0, r).toString(),o2.weight);
			return t1.compareTo(t2);
		}
	}

	/* Compares the two terms in lexicographic order by query */
	public int compareTo(Term that)
	{
		for(int i=0;i<query.length() && i<that.query.length();i++)
		{
			char thisCurChar= query.charAt(i);
			char thatCurChar= that.query.charAt(i);
			if(thisCurChar >thatCurChar)
			{
				return 1;
			}
			if(thisCurChar <thatCurChar)
			{
				return -1;
			}
		}
		if(query.length()>that.query.length())
		{
			return 1;
		}
		if(query.length()<that.query.length())
		{
			return -1;
		}
		return 0;

	}

	// Returns a string representation of this in the following format:
// weight (i.e., ??.toString()), followed by a tab, followed by query
	public String toString()
	{
		return Long.toString(weight) +"	"+ query;
	}
}