package beerapp;

import java.util.ArrayList;
import java.util.List;


public class Recipe {
	String name;
	List<String> ingredients;
	public Recipe(String name, List<String> ingredients){
		this.name = name;
		this.ingredients = ingredients;
	}
	
	public void print(){
		System.out.println(name);
		for (int i=0; i<ingredients.size();i++){
			System.out.println(ingredients.get(i));
		}
	}

}
