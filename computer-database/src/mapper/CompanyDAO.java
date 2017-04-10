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
import model.Computer;
import persistence.Persistence;

/**
 * @author ckeita
 *
 */
public class CompanyDAO {
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Company findById(int id) throws SQLException {
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
	}
	
	/**
	 * @return
	 * @throws SQLException 
	 */
	public List<Company> findAll() throws SQLException {
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
	}
	
	/**
	 * @param comp
	 * @throws SQLException 
	 */
	public void create(Company comp) throws SQLException {
		
		String query = "INSERT INTO company(name) VALUES(?)";
		
		//Initialize a preparedStatement
		PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
		//Set the parameter name through the preparedStatement
		pstm.setString(1,comp.getName()); 
		//Execute the query
		System.out.println("Inserted "+pstm.executeUpdate());
	}
}
