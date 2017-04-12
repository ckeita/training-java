/**
 * 
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import Utils.Util;
import model.Computer;
import persistence.Persistence;

/**
 * @author ckeita
 *
 */
public class ComputerDAO {
	
	/**
	 * @param the id of the computer
	 * @return the string of the object
	 * @throws SQLException 
	 */
	public String findById(int id) {
		try {
			//create a new Computer
			Computer comp = new Computer();
			//Create the sql query to find Computer by id
			String query = "SELECT * FROM computer WHERE id=?";
			
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
				if(result.getString(3) != null) {
					comp.setDiscontinued(LocalDateTime.parse(result.getString(3), Util.FORMATTER));
				}
				if (result.getString(4) != null) {
					comp.setDiscontinued(LocalDateTime.parse(result.getString(4), Util.FORMATTER));
				}
				comp.setCompany_id(result.getInt(5));
			}
			
			//Find company name if it's defined
			if (comp.getCompany_id() != 0) {
				//Create the sql query to find Computer by id
				query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id=?";
				//Initialize a preparedStatement
				pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
				//Set the parameter id through the preparedStatement
				pstm.setInt(1,id); 
				//pstm.setInt(2,comp.getCompany_id()); 
				//Execute the query
				result = pstm.executeQuery();
				
				String comp_str = null;
				//Fetching data from database
				//System.out.println(result.si);
				while (result.next()) {
					comp_str = comp.toString()+" "+result.getString(7);
				}
				return comp_str;
			}
			return comp.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return the list all computers
	 * @throws SQLException 
	 */
	public List<Computer> findAll() {
		
		try {
			//create a list of computer
			List<Computer> list = new ArrayList<Computer>();
			//Create the sql query to find computer
			String query = "SELECT * FROM computer";
			
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Execute the query
			ResultSet result = pstm.executeQuery();
			
			//Fetching data from database
			while(result.next()) {
				//create a new computer
				Computer comp = new Computer();
				//initialize the computer fields by data from database
				comp.setId(result.getInt(1));
				comp.setName(result.getString(2));
				if (result.getString(3) != null) {
					comp.setIntroduced(LocalDateTime.parse(result.getString(3), Util.FORMATTER));
				}
				if (result.getString(4) != null) {				
					comp.setDiscontinued(LocalDateTime.parse(result.getString(4), Util.FORMATTER));
				}
				comp.setCompany_id(result.getInt(5));
				
				//Add new computer to the list
				list.add(comp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param comp: the computer to put on database
	 * @throws SQLException 
	 */
	public void create(Computer comp) {
		
		try {
			String query = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Set the parameter name through the preparedStatement
			pstm.setString(1,comp.getName());
			if (comp.getIntroduced() != null) {
				pstm.setString(2,comp.getIntroduced().toString());
			} else {
				pstm.setNull(2, Types.TIMESTAMP);
			}
			if (comp.getDiscontinued() != null) {
				pstm.setString(3,comp.getDiscontinued().toString());
			}else {
				pstm.setNull(3, Types.TIMESTAMP);
			}
			pstm.setInt(4, comp.getCompany_id());
			//Execute the query
			System.out.println(pstm.executeUpdate());
		} catch (MySQLIntegrityConstraintViolationException ex) {
			System.out.println("Impossible to insert: The company is not found in database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param comp: the computer to delete from database
	 * @throws SQLException 
	 */
	public void delete(Computer comp) {
		try {
			String query = "DELETE FROM computer WHERE id=?";
			
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Set the parameter name through the preparedStatement
			pstm.setInt(1, comp.getId());
			//Execute the query
			System.out.println(pstm.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param comp: the computer to update
	 * @throws SQLException
	 */
	public void update(Computer comp) {
		
		try {
			String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
			
			//Initialize a preparedStatement
			PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
			//Set the parameter name through the preparedStatement
			pstm.setString(1,comp.getName());
			if (comp.getIntroduced() != null) {
				pstm.setString(2,comp.getIntroduced().toString());
			} else {
				pstm.setNull(2, Types.TIMESTAMP);
			}
			if (comp.getDiscontinued() != null) {
				pstm.setString(3,comp.getDiscontinued().toString());
			}else {
				pstm.setNull(3, Types.TIMESTAMP);
			}
			pstm.setInt(4, comp.getCompany_id());
			pstm.setInt(5, comp.getId());
			//Execute the query
			System.out.println(pstm.executeUpdate());
		} catch (MySQLIntegrityConstraintViolationException ex) {
			System.out.println("Impossible to update: The company is not found in database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
