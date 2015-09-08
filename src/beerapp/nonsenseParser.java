package beerapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class nonsenseParser {
	
	List<String> foodWords = Arrays.asList("spicy", "savoury", "mexican", "asian", "spiced",
			"shellfish", "fish", "chicken", "beef", "grilled", "chinese", "curried", "thai", "burgers",
			"salmon", "pork", "mushrooms", "venison", "gruyere", "lamb");
	String nonSense;
	List<String> allStrings;
	
	public void nonSenseParser( String nonSense){
		this.nonSense = nonSense;
	}
	
	public List<String> getSearchTerms(String nonSense){
		allStrings = new ArrayList<String>();
		String[] result = nonSense.split("\\s");
		 String newString = null;
		for(int i = 0; i<result.length; i++){
			newString = result[i];
			//newString = newString.replaceAll(".", "");
			newString = newString.replaceAll(",", "");
			newString = newString.replaceAll("/", "");
			allStrings.add(newString);
		}	
		
		return getFoodWords(allStrings);
		
	}
	private List<String> getFoodWords(List<String> strings){
		ArrayList<String> words = new ArrayList<String>();
		for(int i = 0; i<strings.size();i++){
			if(foodWords.contains(strings.get(i).toLowerCase())){
				words.add(strings.get(i).toLowerCase());
			}
		}//hello
		System.out.println(words);
		return words;
		
	}

}
