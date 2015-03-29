package beerapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;


public class parseRecipe {
	String searchUrl = "http://food2fork.com/api/search?key=fbfe8a829a1f175ff8ac84d6f423a134&q=";
	
	String getUrl = "http://food2fork.com/api/get?key=fbfe8a829a1f175ff8ac84d6f423a134&rId=";
	List<String> searchTerms;
	
	
	
	public Recipe recipeParse(List<String> searchTerms){
		String s1 = searchTerms.get(0);
		String s2 = searchTerms.get(1);
		int rId = searchParser(s1, s2);
		String name;
		List<String> ingredients = new ArrayList();
		Recipe r = null;
		String ingredient = "";
		JSONTokener tokener = new JSONTokener(jSONgetter(getUrl + rId));
		try {
			JSONObject obj = new JSONObject(tokener);
			obj = obj.getJSONObject("recipe");
			JSONArray array = obj.getJSONArray("ingredients");
			for(int i = 0; i<array.length();i++){
				ingredient = array.getString(i);
				ingredients.add(ingredient);							
			}
			name = obj.getString("title");
			r = new Recipe(name, ingredients);			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return r;
	}
	
	
	
	
	
	
	private int searchParser(String s1, String s2){
		int rId = 0;
		JSONTokener tokener = new JSONTokener(jSONgetter(getSearchUrl(s1,s2)));
		try {
			JSONObject obj = new JSONObject(tokener);
			JSONArray array = obj.getJSONArray("recipes");
			obj = array.getJSONObject(0);
			rId = obj.getInt("recipe_id");			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return rId;
	}
	
	private String getSearchUrl(String s1, String s2){
		
		return searchUrl + s1+ "," + s2;
	}
	
	private String jSONgetter(String urlTry){
		String returnString = "";
		try{
		URL url = new URL(urlTry);
        HttpURLConnection client = (HttpURLConnection) url.openConnection();
        java.io.InputStream in = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        returnString = br.readLine();
        client.disconnect();
		}catch (IOException e){
			e.printStackTrace();
		}
        return returnString;
	}

}