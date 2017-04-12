/**
 * 
 */
package ui;

import java.util.List;
import model.Company;

/**
 * @author ebiz
 *
 */
public class CompanyUI {
	
	public void viewCompany (Company cmp) {
		
	}
	
	public void viewCompany (List<Company> list) {
		System.out.println("****List of Companies****");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
