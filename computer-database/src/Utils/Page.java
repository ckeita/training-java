/**
 * 
 */
package Utils;

import java.util.List;
import java.util.Scanner;

/**
 * @author ckeita
 * @param <T>
 *
 */
public class Page<T> {
	
	private int numberOfRow;
	private int indexStart;
	private int indexEnd;
	private boolean finish;
	private List<T> list;
	
	
	
	/**
	 * @param <T>
	 * @param numberOfRow
	 * @param list
	 */
	public Page(int numberOfRow, List<T> list) {
		this.numberOfRow = numberOfRow;
		this.list = list;
		finish = false;
		indexStart = 0;
		indexEnd = 0;
	}
	
	
	public void paging () {
		
		while (!finish) {
			Scanner input = new Scanner(System.in);
			String choice; 
			
			System.out.println("****List of Computers****");
			System.out.println("0 => Show next Page "+numberOfRow+" Computers\n1 => Back to previous Page\n2 => Return to previous menu");
			choice = input.next();
			switch (choice) {
			case "0":
				//List next numberOfRow Computers
				int savedIndexEnd = indexEnd;
				if ((indexEnd+=numberOfRow) <= list.size()) {
					indexEnd+=numberOfRow;
				} else {
					int remain = list.size() - indexEnd;
					indexEnd += remain;
				}
				if (indexStart != 0) {
					indexStart+=savedIndexEnd;
				}
				printElements();
				break;
			case "1":
				//List previous numberOfRow Computers
				
				break;
			case "2":
				//Set 'finish' to go back to main menu
				finish = true;
				break;
			default:
				System.out.println("Please choose a number between 0 and 2");
				break;
			}
		}
	}
	
	private void printElements () {
		for (int i = indexStart; i < indexEnd; i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
