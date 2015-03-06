import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.io.*;

public class ContributersByIndustry implements Runnable {
	private static List<String> allContributions = new ArrayList<String>();
	private static List<String> CRPs = new ArrayList<String>();
	private static List<String> myContribs = new ArrayList<String>();
	private static List<String> contribAmountStrings = new ArrayList<String>();
	private static List<Integer> contribAmount = new ArrayList<Integer>();
	private static List<Integer> myContribAmount = new ArrayList<Integer>();
	private static List<String> congressCRPs = new ArrayList<String>();
	private static List<String> chosenPeople = new ArrayList<String>();
	private static HashMap<String, String> industryAndCodes = new HashMap<>();
	private static HashMap<String, String> crpsAndNames = new HashMap<>();

	public void run() {
		// User enters the location of the sector codes file
		File sectorCodes = new File("src/Sector_Codes.csv");
		// Adds sector codes and industries to their respective ArrayLists
		try {
			Scanner scan = new Scanner(sectorCodes);
			Scanner scan2 = new Scanner(sectorCodes);
			scan2.useDelimiter(",");
			String abc = scan2.next();
			scan.useDelimiter(",");
			boolean pick = true;
			while (scan.hasNext() && scan2.hasNext()) {
				if (pick == true) {
					industryAndCodes.put(scan.next(), scan2.next());
					pick = false;
				}
				else {
					abc = scan.next();
					String abc2 = scan2.next();
					pick = true;
				}
			}
			scan.close();
			scan2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int[] contributors(String s) {
		// Clears previous entries in candidate-specific arraylists
		myContribs.clear();
		myContribAmount.clear();
		// Location of file containing all contributions
		File contribs = new File("src/AllContribs.csv");
		// Location of file containing all contribution amounts
		File contribs2 = new File("src/ContributionsAmounts.csv");
		try {

			if (allContributions.isEmpty()) {
				// Adds contributions from file to arraylist
				Scanner myScanner = new Scanner(contribs);
				myScanner.useDelimiter(",");
				boolean choose = true;
				while (myScanner.hasNext()) {
					if (choose == true) {
						myScanner.next();
						choose = false;
					} else {
						allContributions.add(myScanner.nextLine());
						choose = true;
					}
				}
				myScanner.close();
				// Removes header from the csv file that got transferred to the
				// arraylist
				allContributions.remove(0);
				// Removes unnecessary commas from arraylist containing all
				// contributions
				for (int r = 0; r < allContributions.size(); r++) {
					if (allContributions.get(r).length() > 4)
					allContributions.set(r, allContributions.get(r)
					.substring(1));
					else {
						allContributions.remove(r);
						r--;
					}
				}
			}
			/*
			* If the arraylist containing all the CRP codes for contribution
			* recipients is empty, then it will be added from the contributions
			* file
			*/
			if (CRPs.isEmpty()) {
				// Adds CRPs for contributions from file
				Scanner myScanner2 = new Scanner(contribs);
				myScanner2.useDelimiter(",");
				boolean choose2 = true;
				while (myScanner2.hasNext()) {
					if (choose2 == true) {
						CRPs.add(myScanner2.next());
						choose2 = false;
					} else {
						myScanner2.nextLine();
						choose2 = true;
					}
				}
				// Removes header from csv that got transferred to the arraylist
				CRPs.remove(0);
				myScanner2.close();
			}
			/*
			* If the arraylist containing the contribution amounts is empty,
			* then it will be added from the contribution amounts file
			*/
			if (contribAmountStrings.isEmpty()) {
				// Adds contribution amounts to arraylist from file
				Scanner scan3 = new Scanner(contribs2);
				while (scan3.hasNext()) {
					contribAmountStrings.add(scan3.nextLine());
				}
				scan3.close();
				// Removes header transferred from csv file to the arraylist
				contribAmountStrings.remove(0);
				// Converts the contribution amounts from strings to integers,
				// and adds them to a new arraylist
				for (int l = 0; l < contribAmountStrings.size(); l++) {
					contribAmount.add(Integer.parseInt(contribAmountStrings
					.get(l)));
				}
			}
			//Places the contributions and contribution amounts corresponding to the chosen candidates
			//in it's own arraylists
			int i = 0;
			while (i < CRPs.size()) {
				if (CRPs.get(i).equals(s)) {
					myContribs.add(allContributions.get(i));
					myContribAmount.add(contribAmount.get(i));
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Adds up the total contributions in dollars by sector to the chosen candidates
		int[] conts = new int[35];
		for (int i : conts) {
			conts[i] = 0;
		}
		int a = 0;
		while (a < myContribs.size()) {
			if (myContribs.get(a).startsWith("A")) {
				conts[0] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("B")) {
				conts[1] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("C")) {
				conts[2] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("D")) {
				conts[3] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("E")) {
				conts[4] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("F")) {
				conts[5] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("G")) {
				conts[6] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("H")) {
				conts[7] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J11")) {
				conts[8] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J12")) {
				conts[9] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J21")) {
				conts[10] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J22")) {
				conts[11] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J51")) {
				conts[12] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J52")) {
				conts[13] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J61")) {
				conts[14] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J62")) {
				conts[15] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J712")) {
				conts[16] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J715")) {
				conts[17] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J72")) {
				conts[18] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J73")) {
				conts[19] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J74")) {
				conts[20] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J75")) {
				conts[21] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("J8")) {
				conts[22] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("JD1")) {
				conts[23] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("JD2")) {
				conts[24] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("JE3")) {
				conts[25] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("JH1")) {
				conts[26] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("K0")) {
				conts[27] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("K12")) {
				conts[28] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("K2")) {
				conts[29] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("L")) {
				conts[30] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("M")) {
				conts[31] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("T")) {
				conts[32] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("X")) {
				conts[33] += Math.abs(myContribAmount.get(a));
			} else if (myContribs.get(a).startsWith("Y")) {
				conts[34] += Math.abs(myContribAmount.get(a));
			}
			a++;
		}
		return conts;
	}
	public static void main(String[] args) {
		(new Thread(new ContributersByIndustry())).start();
		// User enters the location of the CRP code file
		File crp = new File("src/CRPs_IDs.csv");
		// Adds CRP IDs and congresspeople to their respective ArrayLists
		try {
			Scanner scanner = new Scanner(crp);
			Scanner scanner2 = new Scanner(crp);
			scanner.useDelimiter(",");
			scanner2.useDelimiter(",");
			scanner2.next();
			boolean pick = true;
			while (scanner.hasNext() && scanner2.hasNext()) {
				if (pick == true) {
					crpsAndNames.put(scanner.next(), scanner2.nextLine());
					pick = false;
				} else {
					String meh = scanner.nextLine();
					String otherMeh = scanner2.next();
					pick = true;
				}
			}
			scanner.close();
			scanner2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Removes excess spaces and commas from each of the ArrayList elements
		// in the name ArrayList
		for(Map.Entry<String, String> entry : crpsAndNames.entrySet())
		{
			crpsAndNames.put(entry.getKey(), entry.getValue().substring(1));
		}
		// User enters the location of the targeted congress people file
		File CRP = new File("src/transfer.txt");

		// Adds the CRP codes of the targeted congress people to the ArrayList
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			congressCRPs.add(scanner.next());
		}
		scanner.close();
		//Adds the names of the chosen congresspeople CRPs to an ArrayList

		int i = 0;
		while(i < congressCRPs.size())
		{
			for(Map.Entry<String, String> entry : crpsAndNames.entrySet())
			{
				if(entry.getKey().equals(congressCRPs.get(i)))
				{
					chosenPeople.add(entry.getValue());
				}
			}
			i++;
		}

		/*
		* Adds the contribution amount by industry for the previously selected
		* congresspeople to a two-dimensional array by repeatedly calling the
		* method defined prior to the main method
		*/
		int[][] conts = new int[congressCRPs.size()][36];
		for (int h = 0; h < congressCRPs.size(); h++) {
			for (int p = 0; p < 35; p++) {
				conts[h][p] = ContributersByIndustry.contributors(congressCRPs
				.get(h))[p];
			}
		}
		ArrayList<String> set = new ArrayList(industryAndCodes.keySet());
		Collections.sort(set);
		// Prints out contribution amounts by industry for each of the selected
		// congresspeople
		// Writes the contribution by industry data to a csv file
		System.out.print("Sector, ");
		for (int e = 0; e < 35; e++) {
			System.out.print(industryAndCodes.get(set.get(e)) + ", ");
		}
		System.out.println();
		for (int t = 0; t < congressCRPs.size(); t++) {
			System.out.print(chosenPeople.get(t).substring(chosenPeople.get(t).indexOf(",") + 2) +
			" " + chosenPeople.get(t).substring(0, chosenPeople.get(t).indexOf(","))  +
			",");
			for (int e = 0; e < 35; e++) {
				System.out.print(conts[t][e] + ", ");
			}
			System.out.println();
		}
	}
}
