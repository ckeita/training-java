/**
 * 
 */
package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.PreparedStatement;

import Utils.Util;
import model.Company;
import model.Computer;

/**
 * @author ckeita
 *
 */
public class CompanyDAO {
	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
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
			logger.info("Selected one element from database");
			return comp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param offset: Offset of the first row
	 * @param max: number of rows
	 * @return
	 */
	public ResultSet findByLimit (int offset, int max) {
			
		try {
			//Create the sql query to find companies
			String query = "SELECT * FROM company LIMIT ?,?";
			
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Set the parameters of preparedStatement
			pstm.setInt(1,offset); 
			pstm.setInt(2,max); 
			//Execute the query
			ResultSet result = pstm.executeQuery();
			
			logger.info("Selected: "+result.getFetchSize()+" elements from database");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return result: the resultSet of all companies
	 * @throws SQLException 
	 */
	public ResultSet findAll() {
		
		try {	
			//Create the sql query to find company
			String query = "SELECT * FROM company";
			
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Execute the query
			ResultSet result = pstm.executeQuery();
		
			logger.info("Selected: "+result.getFetchSize()+" elements from database");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
