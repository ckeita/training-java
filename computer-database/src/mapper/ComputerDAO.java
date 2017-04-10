/**
 * 
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import model.Computer;
import persistence.Persistence;

/**
 * @author ckeita
 *
 */
public class ComputerDAO {
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Computer findById(int id) throws SQLException {
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
		}
		return comp;
	}
	
	/**
	 * @return
	 * @throws SQLException 
	 */
	public List<Computer> findAll() throws SQLException {
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
			Computer comp = new Computer();
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
	public void create(Computer comp) throws SQLException {
		
		String query = "INSERT INTO computer(name) VALUES(?)";
		
		//Initialize a preparedStatement
		PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection().prepareStatement(query);
		//Set the parameter name through the preparedStatement
		pstm.setString(1,comp.getName()); 
		//Execute the query
		pstm.executeUpdate();
	}

}
