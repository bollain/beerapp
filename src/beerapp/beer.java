package beerapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class beer {

	public static void main(String[]args)
	{
		
		//Initial Greeting
		System.out.println("Enter name of beer:");
		
		String foodPairing = null;
		
		do{
		if (foodPairing != null){
			if (foodPairing.equals(""))
				System.out.println("Not found. Please try again:");
		}
		
		//String input by keyboard
		java.util.Scanner keyboardReader;
		keyboardReader = new java.util.Scanner(System.in);

		//set s1 to be the input
		String s1 = keyboardReader.nextLine();

		foodPairing = findBeerID(s1);
		
		System.out.println(foodPairing);
		
		}
		while (foodPairing.equals("") || foodPairing == null);
		
		//Prompt user to see if he wants recipes
		System.out.println("Would you like a recipe based on these pairings?");
		
		//String input by keyboard
		java.util.Scanner keyboardReader;
		keyboardReader  = new java.util.Scanner(System.in);
		
		//set s1 to be the input
		String s1 = keyboardReader.nextLine();
		
		if(s1.toLowerCase().equals("y")){
			//do the parsing
			nonsenseParser p = new nonsenseParser();
			List<String> s = p.getSearchTerms(foodPairing);
			parseRecipe pr = new parseRecipe();
			Recipe r = pr.recipeParse(s);
			r.print();
		} else {
			System.out.println("Thanks for using the service!");
		}
		
		
	}

	private static String findBeerID(String name) {

		name = name.replaceAll("\\s","%20");
		name = name.toLowerCase();

		String baseURL = "http://api.brewerydb.com/v2/search?q=";

		String URL = baseURL + name + "&type=beer&format=json&key=cbb8d2ac80296ac3fd5c99451245df9f";

		//System.out.println(URL);
		String foodPairing = "";

		try {
			String json = callBreweryDB(URL);
			//System.out.println(json.substring(0, 20));
			JSONObject object = new JSONObject(json);
			
			JSONArray data = object.getJSONArray("data");
			
			JSONObject beer;
			
			for (int i = 0; i < data.length(); i++){
				beer = data.getJSONObject(i);			
				
				String jsonString = beer.names().toString();

				if (jsonString.contains("foodPairings")){
					String foundName = beer.getString("name");
					System.out.println("We matched your search to: " + foundName + ". Is this correct? (Y/N)");
					java.util.Scanner keyboardReader;
					keyboardReader = new java.util.Scanner(System.in);
					String s1 = keyboardReader.nextLine();
					if (s1.toLowerCase().equals("y")){
						foodPairing = beer.getString("foodPairings");
						break;
					}
				}			
			}

		} catch (IOException e) {
			e.getStackTrace();
			
		} catch (JSONException e) {
			e.getStackTrace();
		}

		return foodPairing;

	}

	private static String callBreweryDB(String httpRequest) throws MalformedURLException, IOException {

		URL url = new URL(httpRequest);
		HttpURLConnection client = (HttpURLConnection) url.openConnection();
		InputStream in = client.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String returnString = br.readLine();

		client.disconnect();

		return returnString;
	}

}