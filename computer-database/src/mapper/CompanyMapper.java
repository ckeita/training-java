/**
 * 
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import persistence.CompanyDAO;

/**
 * @author ckeita
 *
 */
public class CompanyMapper {
	
	private CompanyDAO companyDAO = new CompanyDAO();
	
	/**
	 * @return the list of companies
	 */
	public List<String> getByPage (int offset, int max) {
		
		ResultSet resultSet = companyDAO.findByLimit(offset, max);
		List<String> list = new ArrayList<>();
		
		try {
			while(resultSet.next()) {
				Company comp = new Company();
				comp.setId(resultSet.getInt(1));
				comp.setName(resultSet.getString(2));
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
