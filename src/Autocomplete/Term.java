package Autocomplete;

import java.util.Comparator;

public class Term implements Comparable<Term> {
	
	String query;
	static long weight;
	/* Initializes a term with the given query string and weight*/
	public Term(String query, long weight )
	{
		this.query= query;
		this.weight= weight;
	}
	
	/* Compares the two terms in descending order by weight*/
	public static Comparator<Term> byReverseWeightOrder()
	{
		return Comparator.reverseOrder();
		//return null;
		
	}
	/* Compares the two terms in lexicographic order but using only the first
	 * r  characters of each query
	 */
	public static Comparator<Term> byPrefixOrder(int r)
	{
		return Comparator.naturalOrder();//byPrefixOrder(r);
	}
	
	/* Compares the two terms in lexicographic order by query */
	public int compareTo(Term that)
	{
		for(int i=0;i<query.length() && i<that.query.length();i++)
		{
			char thisCurChar= query.charAt(i);
			char thatCurChar= query.charAt(i);
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
      //what about the equal situation?......................................................................................

	// Returns a string representation of this in the following format:
	// weight (i.e., ??.toString()), followed by a tab, followed by query
	public String toString()
	{
		return Long.toString(weight) +"	"+ query;
	}
}
