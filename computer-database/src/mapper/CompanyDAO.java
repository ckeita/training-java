/**
 * 
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import model.Company;
import persistence.Persistence;

/**
 * @author ckeita
 *
 */
public class CompanyDAO {
	
	/**
	 * @param id: the id of company to find
	 * @return the company founded
	 * @throws SQLException 
	 */
	public Company findById(int id) {
		
		try {
			//create a new Company
			Company comp = new Company();
			//Create the sql query to find company by id
			String query = "SELECT * FROM company WHERE id=?";
			
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Set the parameter id through the preparedStatement
			pstm.setInt(1,id); 
			//Execute the query
			ResultSet result = pstm.executeQuery();
			
			//Fetching data from database
			while(result.next()) {
				comp.setId(result.getInt(1));
				comp.setName(result.getString(2));
			}
			return comp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return list: the list of companies
	 * @throws SQLException 
	 */
	public List<Company> findAll() {
		
		try {
			//create a list of Company
			List<Company> list = new ArrayList<Company>();
			//Create the sql query to find company
			String query = "SELECT * FROM company";
			
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Execute the query
			ResultSet result = pstm.executeQuery();
			
			//Fetching data from database
			while(result.next()) {
				Company comp = new Company();
				comp.setId(result.getInt(1));
				comp.setName(result.getString(2));
				list.add(comp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
