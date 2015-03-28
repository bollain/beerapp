package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Driver {

	public static void main(String[]args)
	  {
	    //Initial Greeting
	    System.out.println("Enter name of beer:");
	    
	    //String input by keyboard
	    java.util.Scanner keyboardReader;
	    keyboardReader = new java.util.Scanner(System.in);
	    
	    //set s1 to be the input
	    String s1 = keyboardReader.nextLine();
	    
	    findBeerID(s1);
	    
	    
	    //Beer input = new Beer(s1);
	    
	    //
	    
	  }

	private static void findBeerID(String name) {
		
		String baseURL = "http://api.brewerydb.com/v2/search?q=";

		String URL = baseURL + name + "&type=beer&format=json&key=cbb8d2ac80296ac3fd5c99451245df9f";
		
		try {
			String json = callBreweryDB(URL);
			JSONObject object = new JSONObject(json);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
