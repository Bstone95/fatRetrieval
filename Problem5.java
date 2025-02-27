import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;



public class Problem5 {

	public static void main(String[] args) {

		//scanner//
		Scanner scan = new Scanner(System.in);

		//arraylist for root table//
		ArrayList<Integer> roots = new ArrayList<Integer>();

		//prompts user to enter values of root table and parses through with space//
		System.out.println("Enter values of root table with space seperation, press Enter when finished: ");
		String input = scan.nextLine();
		String[] inpArray = input.split(" ");
		for (String numStr : inpArray) {
			try{
				int num = Integer.parseInt(numStr);

				roots.add(num);
			} catch (NumberFormatException x) {
				System.err.print("Invalid input: " + numStr + " is not an Integer");
			}
		}

		//prompts user to enter values for first row of FAT table and uses space as parse//
		System.out.println("Enter values of FAT table first row with "
				+ "space seperation, press Enter when finished: ");

		String FAT1 = scan.nextLine();
		String[] FAT1Array = FAT1.split(" ");
		Integer[] firstRow = new Integer[FAT1Array.length];
		for (int i=0; i<FAT1Array.length; i++) {
			try{
				firstRow[i] = Integer.parseInt(FAT1Array[i]);
			} catch (NumberFormatException x) {
				System.err.print("Invalid input: " + FAT1Array[i] + " is not an Integer");
			}
		}

		//prompts user to enter values for second row of FAT table and uses space as parse//
		System.out.println("Enter values of FAT table second row with "
				+ "space seperation, press Enter when finished: ");

		String FAT2 = scan.nextLine();
		String[] FAT2Array = FAT2.split(" ");
		Integer[] secondRow = new Integer[FAT2Array.length];
		for (int i=0; i<FAT2Array.length; i++) {
			try{
				secondRow[i] = Integer.parseInt(FAT2Array[i]);
			} catch (NumberFormatException x) {
				System.err.print("Invalid input: " + FAT2Array[i] + " is not an Integer");
			}
		}



		//uses method to trace deleted file back to root//
		ArrayList<Integer> result = traceBackToZero(roots, firstRow, secondRow);

		//shows deleted cluster file//
		System.out.println("Cluster for the deleted file: " + result);

		//closes scanner//
		scan.close();
	}



	//method that takes root list and FAT table as arguments and traces file back to root//
	public static ArrayList<Integer> traceBackToZero(ArrayList<Integer> roots, Integer[] FAT1, Integer[] FAT2) {
		ArrayList<Integer> head = new ArrayList<Integer>(FAT2.length);

		ArrayList<Integer> result = new ArrayList<Integer>(FAT2.length);

		ArrayList<Integer> fin = new ArrayList<Integer>(FAT2.length);

		//iterates through FAT table looking for corresponding zero//
		for(int i = 0; i<FAT2.length; i++) {

			if (FAT2[i]==0) {
				head.add(FAT1[i]);	
			}
		}
		//finds corresponding trace for traced file//
		for(int j = 0; j<FAT2.length; j++) {
			if(head.contains(FAT2[j])) {
				result.add(FAT1[j]);
			}
		}
		//finds last corresponding value
		for(int k=0; k<FAT1.length; k++) {
			if(result.contains(FAT2[k])) {
				fin.add(FAT1[k]);
			}
		}
		//removes known files//
		fin.removeAll(roots);

		//traces back to zero to find and return full file that was deleted//
		for(int y = 0; y<FAT2.length; y++) {	
			if(fin.contains(FAT1[y])) {
				if(FAT2[y]==0) {
					break;
				}
				fin.add(FAT2[y]);
			}
		}
		return fin;

	}
}



