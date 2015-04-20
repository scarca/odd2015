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
			int poorPercent = poorHouseholds / total;
			int middlePercent = middleHouseholds / total;
			int richPercent = richHouseholds / total;
			
			//Finds the bracket that makes up the largest percentage of the district
			double max = (poorPercent > middlePercent) ? poorPercent : middlePercent;
			max = (max > richPercent) ? max : richPercent;
			
			//Weighs a district with mostly middle class as 50-50 conservative-liberal
			if(max == middlePercent)
			{
				max = 0.5;
			}
			//Weighs a district with mostly poor people as leaning towards liberal
			else if(max == poorPercent)
			{
				max = 0.5 + poorPercent;
				if(max > 1)
				{
					max = 1;
				}
			}
			
			//Adds weight to the HashMap with the income weights
			incomeWeights.put(entry.getKey(), max);
			
		}

	}

}
