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
	private static List<String> IDs = new ArrayList<String>();
	private static List<String> names = new ArrayList<String>();
	private static List<String> congressCRPs = new ArrayList<String>();
	private static List<String> chosenPeople = new ArrayList<String>();
	private static List<String> codes = new ArrayList<String>();
	private static List<String> industry = new ArrayList<String>();

	public void run() {
		// User enters the location of the sector codes file
		File sectorCodes = new File("src/Sector_Codes.csv");
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
		int[] conts = new int[36];
		for (int i : conts) {
			conts[i] = 0;
		}
		int a = 0;
		while (a < myContribs.size()) {
			if (myContribs.get(a).startsWith("A")) {
				conts[0] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("B")) {
				conts[1] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("C")) {
				conts[2] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("D")) {
				conts[3] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("E")) {
				conts[4] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("F")) {
				conts[5] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("G")) {
				conts[6] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("H")) {
				conts[7] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J11")) {
				conts[8] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J12")) {
				conts[9] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J22")) {
				conts[10] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J21")) {
				conts[11] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J51")) {
				conts[12] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J52")) {
				conts[13] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J61")) {
				conts[14] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J62")) {
				conts[15] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J712")) {
				conts[16] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J715")) {
				conts[17] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J72")) {
				conts[18] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J73")) {
				conts[19] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J74")) {
				conts[20] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J75")) {
				conts[21] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J8")) {
				conts[22] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JD1")) {
				conts[23] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JD2")) {
				conts[24] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JE3")) {
				conts[25] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JH1")) {
				conts[26] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("Z")) {
				conts[27] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("L")) {
				conts[28] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K12")) {
				conts[29] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K0")) {
				conts[30] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K2")) {
				conts[31] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("M")) {
				conts[32] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("T")) {
				conts[33] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("X")) {
				conts[34] += myContribAmount.get(a);
			} else if (myContribs.get(a).startsWith("Y")) {
				conts[35] += myContribAmount.get(a);
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
			scanner.useDelimiter(",   ");
			boolean pick = true;
			while (scanner.hasNext()) {
				if (pick == true) {
					IDs.add(scanner.next());
					pick = false;
				} else {
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

		// Adds the CRP codes of the targeted congress people to the ArrayList
		try {
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNext()) {
				congressCRPs.add(scanner.next());
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Adds the names of the chosen congresspeople CRPs to an ArrayList

		int i = 0;
		int x = 0;
		while (i < IDs.size() && x < congressCRPs.size()) {
			if (IDs.get(i).equals(congressCRPs.get(x))) {
				chosenPeople.add(names.get(i));
				x += 1;
				i = 0;
			}
			i++;
		}
		// Prints out the names of the chosen congresspeople
		for (String z : chosenPeople) {
			System.err.println(z);
		}

		/*
		 * Adds the contribution amount by industry for the previously selected
		 * congresspeople to a two-dimensional array by repeatedly calling the
		 * method defined prior to the main method
		 */
		int[][] conts = new int[congressCRPs.size()][36];
		for (int h = 0; h < congressCRPs.size(); h++) {
			for (int p = 0; p < 36; p++) {
				conts[h][p] = ContributersByIndustry.contributors(congressCRPs
						.get(h))[p];
			}
		}
		// Prints out contribution amounts by industry for each of the selected
		// congresspeople
		for (int t = 0; t < congressCRPs.size(); t++) {
			for (int e = 0; e < 36; e++) {
				//System.err.print(industry.get(e) + ": ");
				//System.err.println(conts[t][e]);
			}
		}
		// Writes the contribution by industry data to a csv file
		try {
			System.out.print("Sector, ");
			for (int e = 0; e < 36; e++) {
				System.out.print(industry.get(e) + ", ");
			}
			System.out.println();
			for (int t = 0; t < congressCRPs.size(); t++) {
				System.out.print(chosenPeople.get(t).replace(",", " ") + ",");
				for (int e = 0; e < 36; e++) {
					System.out.print(conts[t][e] + ", ");
				}
				System.out.println();
			}
			System.out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
