import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.io.*;

public class ContributersByIndustry {
	public static int[] contributors(String s) {
		List<String> allContributions = new ArrayList<String>();
		List<String> CRPs = new ArrayList<String>();
		List<String> myContribs = new ArrayList<String>();
		List<String> contribAmountStrings = new ArrayList<String>();
		List<Integer> contribAmount = new ArrayList<Integer>();
		List<Integer> myContribAmount = new ArrayList<Integer>();
		File contribs = new File("src/AllContribs.csv");
		File contribs2 = new File("src/ContributionsAmounts.csv");
		try {
			Scanner myScanner = new Scanner(contribs);
			myScanner.useDelimiter(",");
			boolean choose = true;
			while (myScanner.hasNext()) {
				if (choose == true) {
					myScanner.next();
					choose = false;
				}
				else {
					allContributions.add(myScanner.nextLine());
					choose = true;
				}
			}
			myScanner.close();
			Scanner myScanner2 = new Scanner(contribs);
			myScanner2.useDelimiter(",");
			boolean choose2 = true;
			while (myScanner2.hasNext()) {
				if (choose2 == true) {
					CRPs.add(myScanner2.next());
					choose2 = false;
				}
				else {
					myScanner2.nextLine();
					choose2 = true;
				}
			}
			CRPs.remove(0);
			for (int r = 0; r < allContributions.size(); r++) {
				allContributions.set(r, allContributions.get(r).substring(1));
			}
			allContributions.remove(0);
			myScanner2.close();
			Scanner scan3 = new Scanner(contribs2);
			while (scan3.hasNext()) {
				contribAmountStrings.add(scan3.nextLine());
			}
			scan3.close();
			contribAmountStrings.remove(0);
			for (int l = 0; l < contribAmountStrings.size(); l++) {
				contribAmount
						.add(Integer.parseInt(contribAmountStrings.get(l)));
			}
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
		int[] conts = new int[36];
		for(int i : conts)
		{
			conts[i] = 0;
		}
		int a = 0;
		while (a < myContribs.size()) {
			if (myContribs.get(a).startsWith("A")) {
				conts[0] += contribAmount.get(a);
			}
			else if (myContribs.get(a).startsWith("B")) {
				conts[1] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("C")) {
				conts[2] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("D")) {
				conts[3] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("E")) {
				conts[4] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("F")) {
				conts[5] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("G")) {
				conts[6] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("H")) {
				conts[7] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J11")) {
				conts[8] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J12")) {
				conts[9] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J22")) {
				conts[10] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J21")) {
				conts[11] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J51")) {
				conts[12] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J52")) {
				conts[13] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J61")) {
				conts[14] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J62")) {
				conts[15] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J712")) {
				conts[16] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J715")) {
				conts[17] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J72")) {
				conts[18] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J73")) {
				conts[19] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J74")) {
				conts[20] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J75")) {
				conts[21] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J8")) {
				conts[22] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JD1")) {
				conts[23] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JD2")) {
				conts[24] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JE3")) {
				conts[25] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JH1")) {
				conts[26] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("Z")) {
				conts[27] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("L")) {
				conts[28] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K12")) {
				conts[29] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K0")) {
				conts[30] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K2")) {
				conts[31] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("M")) {
				conts[32] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("T")) {
				conts[33] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("X")) {
				conts[34] += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("Y")) {
				conts[35] += contribAmount.get(a);
			}
			a++;
		}
		return conts;
	}
	public static void main(String[] args) {
		// User enters the location of the sector codes file
		// Scanner input = new Scanner(System.in);
		// System.out.println("Enter sector codes location: ");
		// System.out.println("---Example: \"src/Sector_Codes.csv\"---");
		// String s = input.next();
		File sectorCodes = new File("src/Sector_Codes.csv");
		// Creates ArrayLists for the sector codes and their corresponding
		// industries
		List<String> codes = new ArrayList<String>();
		List<String> industry = new ArrayList<String>();
		// Adds sector codes and industries to their respective ArrayLists
		try {
			Scanner scan = new Scanner(sectorCodes);
			scan.useDelimiter(",");
			boolean pick = true;
			while (scan.hasNext()) {
				if (pick == true) {
					codes.add(scan.next());
					pick = false;
				}
				else {
					industry.add(scan.next());
					pick = true;
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Removes the header in the CSV from both ArrayLists
		codes.remove(0);
		industry.remove(0);
		// User enters the location of the CRP code file
		File crp = new File("src/CRPs_IDs.csv");
		// Creates ArrayLists for the CRP IDs and their corresponding
		// congresspeople
		List<String> IDs = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		// Adds CRP IDs and congresspeople to their respective ArrayLists
		try {
			Scanner scanner = new Scanner(crp);
			scanner.useDelimiter(",   ");
			boolean pick = true;
			while (scanner.hasNext()) {
				if (pick == true) {
					IDs.add(scanner.next());
					pick = false;
				}
				else {
					names.add(scanner.nextLine());
					pick = true;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Removes header from first element of the ID ArrayList
		IDs.set(0, IDs.get(0).substring(12));
		// Removes excess spaces and commas from each of the ArrayList elements
		// in the name ArrayList
		for (int i = 0; i < names.size(); i++) {
			names.set(i, names.get(i).substring(5, names.get(i).length() - 1));
		}
		// User enters the location of the targeted congress people file
		File CRP = new File("src/CongressPeopleCRP.csv");
		// Creates an ArrayList to hold the CRP IDs of the targeted
		// congresspeople
		List<String> congressCRPs = new ArrayList<String>();
		// Adds the CRP codes of the targeted congress people to the ArrayList
		try {
			Scanner scanner = new Scanner(CRP);
			while (scanner.hasNext()) {
				congressCRPs.add(scanner.next());
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Adds the names of the chosen congresspeople CRPs to an ArrayList
		int i = 0;
		int x = 0;
		List<String> chosenPeople = new ArrayList<String>();
		while (i < IDs.size() && x < congressCRPs.size()) {
			if (IDs.get(i).equals(congressCRPs.get(x))) {
				chosenPeople.add(names.get(i));
				x += 1;
				i = 0;
			}
			i++;
		}
		//Prints out the names of the chosen congresspeople
		for (String z : chosenPeople) {
			System.out.println(z);
		}
		/*Adds the contribution amount by industry for the previously selected congresspeople to a 
		two-dimensional array by repeatedly calling the method defined prior to the main method*/
		int[][] conts = new int[congressCRPs.size()][36];
		for (int h = 0; h < congressCRPs.size(); h++) {
			for (int p = 0; p < 36; p++) {
				conts[h][p] = ContributersByIndustry.contributors(congressCRPs
						.get(h))[p];
			}
		}
		//Prints out contribution amounts by industry for each of the selected congresspeople
		for (int t = 0; t < congressCRPs.size(); t++) {
			for (int e = 0; e < 36; e++) {
				System.out.print(industry.get(e) + ": ");
				System.out.println(conts[t][e]);
			}
		}
		//Writes the contribution by industry data to a csv file
		try {
			PrintWriter writer = new PrintWriter(
					"src/CongresspeopleContribs.csv");
			writer.print("Sector, ");
			for (int e = 0; e < 36; e++) {
				writer.print(industry.get(e) + ", ");
			}
			writer.println();
			for (int t = 0; t < congressCRPs.size(); t++) {
				writer.print("Contribution_Amount, ");
				for (int e = 0; e < 36; e++) {
					writer.print(conts[t][e] + ", ");
				}
				writer.println();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
