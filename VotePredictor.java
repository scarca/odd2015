import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.io.*;
public class VotePredictor {
	
	//Tai, I made these HashMaps for you. Feel free to change the names, but make sure you know what each of them hold
	
	private static HashMap<String, Integer> incomeLevel = new HashMap<>();
	private static HashMap<String, Integer> education = new HashMap<>();
	private static HashMap<String, Integer> genderDist = new HashMap<>();
	private static HashMap<String, Integer> industry = new HashMap<>();
	private static HashMap<String, Integer> age = new HashMap<>();
	private static HashMap<String, Integer> race = new HashMap<>();
	private static HashMap<String, Integer> popdensity = new HashMap<>();
	
	private static HashMap<String, HashMap<String, Integer>> incomeByDistrict = new HashMap<>();
	private static HashMap<String, HashMap<String, Integer>> educationByDistrict = new HashMap<>();
	private static HashMap<String, HashMap<String, Integer>> genderByDistrict = new HashMap<>();
	private static HashMap<String, HashMap<String, Integer>> indutryByDistrict = new HashMap<>();
	private static HashMap<String, HashMap<String, Integer>> ageByDistrict = new HashMap<>();
	private static HashMap<String, HashMap<String, Integer>> raceByDistrict = new HashMap<>();
	private static HashMap<String, HashMap<String, Integer>> popdensityByDistrict = new HashMap<>();
	
	//These HashMaps are for my processing algorithm
	private static HashMap<String, Double> incomeWeights = new HashMap<>();
	private static HashMap<String, Double> educationWeights = new HashMap<>();
	private static HashMap<String, Double> genderWeights = new HashMap<>();
	private static HashMap<String, Double> industryWeights = new HashMap<>();
	private static HashMap<String, Double> ageWeights = new HashMap<>();
	private static HashMap<String, Double> raceWeights = new HashMap<>();
	private static HashMap<String, Double> popdensityWeights = new HashMap<>();

	public static void main(String[] args) {
		
		//Tai, add your code here before mine
		
		
		
		
		/*
		 * The processing algorithm for income default for weighing a republican vote and a democratic vote is 50-50
		 * Based on the demographics, it will adjust this weight accordingly, with weights closer to 0
		 * being more conservative, and weights closer to 1 being more liberal
		 * A richer population creates a more conservative weight, while a poorer population creates a more liberal weight
		 */
		for(Map.Entry<String, HashMap<String, Integer>> entry : incomeByDistrict.entrySet())
		{
			//Temporarily holds current value of HashMap being iterated over
			HashMap<String, Integer> temp = entry.getValue();
			ArrayList<Integer> incomes = new ArrayList<>();
			
			//Adds each of the household income numbers to an ArrayList
			for(Map.Entry<String, Integer> entry2 : temp.entrySet())
			{
				incomes.add(entry2.getValue());
			}
			
			//Sorts household income numbers into three bracekts: poor, middle, and rich
			int poorHouseholds = incomes.get(0) + incomes.get(1) + incomes.get(2);
			int middleHouseholds = incomes.get(3) + incomes.get(4) + incomes.get(5);
			int richHouseholds = incomes.get(6) + incomes.get(7) + incomes.get(8) + incomes.get(9);
			
			//The total number of households in the district
			int total = poorHouseholds + middleHouseholds + richHouseholds;
			
			//Calculates the percent of poor, middle, and rich households in the district
			double poorPercent = poorHouseholds / total;
			double middlePercent = middleHouseholds / total;
			double richPercent = richHouseholds / total;
			
			//Finds the bracket that makes up the largest percentage of the district
			double max = (poorPercent > middlePercent) ? poorPercent : middlePercent;
			max = (max > richPercent) ? max : richPercent;
			
			//Weighs a district with mostly middle class as 50-50 conservative-liberal
			if(max == middlePercent)
			{
				max = 0.5 - 0.25*richPercent + 0.25*poorPercent;
			}
			//Weighs a district with mostly poor people as leaning towards liberal
			else if(max == poorPercent)
			{
				max = 0.5 + poorPercent - 0.25*richPercent;
				if(max > 1)
				{
					max = 1;
				}
			}
			//Weighs a district with mostly rich people as leaning towards conservative
			else
			{
				max = 0.5 - richPercent + 0.25*poorPercent;
			}
			
			//Adds weight to the HashMap with the income weights
			incomeWeights.put(entry.getKey(), max);
			
		}
		
		/*
		 * The processing algorithm for education default for weighing a republican vote and a democratic vote is 50-50
		 * Based on the demographics, it will adjust this weight accordingly, with weights closer to 0
		 * being more conservative, and weights closer to 1 being more liberal
		 * A less educated population creates a more conservative weight, while more education creates a 
		 * more liberal weight
		 */
		for(Map.Entry<String, HashMap<String, Integer>> entry : educationByDistrict.entrySet())
		{
			//Temporarily holds current value of HashMap being iterated over
			HashMap<String, Integer> temp = entry.getValue();
			ArrayList<Integer> education = new ArrayList<>();
			
			//Adds each of the household income numbers to an ArrayList
			for(Map.Entry<String, Integer> entry2 : temp.entrySet())
			{
				education.add(entry2.getValue());
			}
			
			//Sorts education levels into three brackets: no high school diploma, high school diploma, and college degree
			int noHighSchool = education.get(0) + education.get(1);
			int onlyHighSchool = education.get(2) + education.get(3);
			int collegeDegree = education.get(4) + education.get(5) + education.get(6);
			
			//Computes the total numbers of residents in the district
			int total = noHighSchool + onlyHighSchool + collegeDegree;
			
			//Calculates the percent of no high school, high school, and college people in the district
			double noHighPercent = noHighSchool / total;
			double highPercent = onlyHighSchool / total;
			double degreePercent = collegeDegree / total;
			
			//Finds the bracket that makes up the largest percentage of the district
			double max = (noHighPercent > highPercent) ? noHighPercent : highPercent;
			max = (max > degreePercent) ? max : degreePercent;
			
			//Weighs a district with mostly high school diplomas as somewhat split between liberal and conservative
			if(max == highPercent)
			{
				max = 0.5 - 0.25*noHighPercent + 0.25*degreePercent;
			}
			//Weighs a district with mostly college degrees as leaning towards liberal
			else if(max == degreePercent)
			{
				max = 0.5 + degreePercent - 0.25*noHighPercent;
				if(max > 1)
				{
					max = 1;
				}
			}
			//Weighs a district with mostly non-high school graduates as leaning towards conservative
			else
			{
				max = 0.5 - noHighPercent + 0.25*degreePercent;
			}
			
			//Adds weight to the HashMap with the education weights
			educationWeights.put(entry.getKey(), max);
			
		}
	}

}
