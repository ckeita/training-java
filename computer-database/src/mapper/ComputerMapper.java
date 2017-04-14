/**
 * 
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSetMetaData;

import Utils.Util;
import model.Computer;
import persistence.ComputerDAO;

/**
 * @author ckeita
 *
 */
public class ComputerMapper {
	
	private ComputerDAO computerDAO;
	
	public ComputerMapper () {
		computerDAO = new ComputerDAO();
	}
	
	/**
	 * @param id: the id of the computer 
	 * @return tthe string of the object
	 */
	public String getById (int id) {
		
		ResultSet resultSet = computerDAO.findById(id);
		Computer comp = new Computer();
		
		try {
			while(resultSet.next()) {
				comp.setId(resultSet.getInt(1));
				comp.setName(resultSet.getString(2));
				if(resultSet.getString(3) != null) {
					comp.setDiscontinued(LocalDateTime.parse(resultSet.getString(3), Util.FORMATTER));
				}
				if (resultSet.getString(4) != null) {
					comp.setDiscontinued(LocalDateTime.parse(resultSet.getString(4), Util.FORMATTER));
				}
				comp.setCompany_id(resultSet.getInt(5));
				ResultSetMetaData rsm = resultSet.getMetaData();
				if(rsm.getColumnCount() > 5) {//Join was did
					return comp.toString(resultSet.getString(7));
				} else {
					return comp.toString();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param offset: Offset of the first row
	 * @param max: number of rows
	 * @return
	 */
	public List<String> getByPage (int offset, int max) {
		
		ResultSet resultSet = computerDAO.findByLimit(offset, max);
		List<String> list = new ArrayList<>();
		
		try {
			while(resultSet.next()) {
				//create a new computer
				Computer comp = new Computer();
				//initialize the computer fields by data from database
				comp.setId(resultSet.getInt(1));
				comp.setName(resultSet.getString(2));
				if (resultSet.getString(3) != null) {
					comp.setIntroduced(LocalDateTime.parse(resultSet.getString(3), Util.FORMATTER));
				}
				if (resultSet.getString(4) != null) {				
					comp.setDiscontinued(LocalDateTime.parse(resultSet.getString(4), Util.FORMATTER));
				}
				comp.setCompany_id(resultSet.getInt(5));
				
				//Add new computer to the list
				list.add(comp.toString());
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
