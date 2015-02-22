import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
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

			Scanner scan3 = new Scanner(contribs2);

			while (scan3.hasNext()) {
				contribAmountStrings.add(scan3.nextLine());
			}

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

		int A = 0;
		int B = 0;
		int C = 0;
		int D = 0;
		int E = 0;
		int F = 0;
		int G = 0;
		int H = 0;
		int J11 = 0;
		int J12 = 0;
		int J22 = 0;
		int J21 = 0;
		int J51 = 0;
		int J52 = 0;
		int J61 = 0;
		int J62 = 0;
		int J712 = 0;
		int J715 = 0;
		int J72 = 0;
		int J73 = 0;
		int J74 = 0;
		int J75 = 0;
		int J8 = 0;
		int JD1 = 0;
		int JD2 = 0;
		int JE3 = 0;
		int JH1 = 0;
		int Z = 0;
		int L = 0;
		int K12 = 0;
		int K0 = 0;
		int K2 = 0;
		int M = 0;
		int T = 0;
		int X = 0;
		int Y = 0;

		int a = 0;

		while (a < myContribs.size()) {
			if (myContribs.get(a).startsWith("A")) {
				A += contribAmount.get(a);
			}

			else if (myContribs.get(a).startsWith("B")) {
				B += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("C")) {
				C += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("D")) {
				D += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("E")) {
				E += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("F")) {
				F += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("G")) {
				G += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("H")) {
				H += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J11")) {
				J11 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J12")) {
				J12 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J22")) {
				J22 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J21")) {
				J21 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J51")) {
				J51 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J52")) {
				J52 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J61")) {
				J61 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J62")) {
				J62 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J712")) {
				J712 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J715")) {
				J715 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J72")) {
				J72 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J73")) {
				J73 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J74")) {
				J74 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J75")) {
				J75 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("J8")) {
				J8 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JD1")) {
				JD1 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JD2")) {
				JD2 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JE3")) {
				JE3 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("JH1")) {
				JH1 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("Z")) {
				Z += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("L")) {
				L += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K12")) {
				K12 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K0")) {
				K0 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("K2")) {
				K2 += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("M")) {
				M += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("T")) {
				T += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("X")) {
				X += contribAmount.get(a);
			} else if (myContribs.get(a).startsWith("Y")) {
				Y += contribAmount.get(a);
			}
			a++;
		}

		int[] conts = new int[36];

		conts[0] = A;
		conts[1] = B;
		conts[2] = C;
		conts[3] = D;
		conts[4] = E;
		conts[5] = F;
		conts[6] = G;
		conts[7] = H;
		conts[8] = J11;
		conts[9] = J12;
		conts[10] = J21;
		conts[11] = J22;
		conts[12] = J51;
		conts[13] = J52;
		conts[14] = J61;
		conts[15] = J62;
		conts[16] = J712;
		conts[17] = J715;
		conts[18] = J72;
		conts[19] = J73;
		conts[20] = J74;
		conts[21] = J75;
		conts[22] = J8;
		conts[23] = JD1;
		conts[24] = JD2;
		conts[25] = JE3;
		conts[26] = JH1;
		conts[27] = K0;
		conts[28] = K12;
		conts[29] = K2;
		conts[30] = L;
		conts[31] = M;
		conts[32] = T;
		conts[33] = X;
		conts[34] = Y;
		conts[35] = Z;

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
		// Scanner input2 = new Scanner(System.in);
		// System.out.println("Enter CRP code location: ");
		// System.out.println("---Example: \"src/CRPs_IDs.csv\"---");
		// String s2 = input2.next();
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
		// Scanner input3 = new Scanner(System.in);
		// System.out.println("Enter targeted congresspeople location: ");
		// System.out.println("---Example: \"src/CongressPeopleCRP.csv\"---");
		// String s3 = input3.next();
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
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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

		for (String z : chosenPeople) {
			System.out.println(z);
		}

		int[][] conts = new int[congressCRPs.size()][36];

		for (int h = 0; h < congressCRPs.size(); h++) {
			for (int p = 0; p < 36; p++) {
				conts[h][p] = ContributersByIndustry.contributors(congressCRPs
						.get(h))[p];
			}
		}

		int l = 0;

		for (int t = 0; t < congressCRPs.size(); t++) {
			for (int e = 0; e < 36; e++) {

				System.out.print(industry.get(e) + ": ");
				System.out.println(conts[t][e]);
			}
		}

		try {
			PrintWriter writer = new PrintWriter(
					"src/CongresspeopleContribs.csv");
			for (int t = 0; t < congressCRPs.size(); t++) {
				for (int e = 0; e < 36; e++) {

					writer.print(industry.get(e) + ", ");
					writer.println(conts[t][e]);
				}
			}

			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
