package fr.ebiz.computer_database.mapper;

import fr.ebiz.computer_database.model.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ebiz on 19/05/17.
 */
public class CompanyDaoMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet resultSet, int i) throws SQLException {
		Company comp = new Company();
		comp.setId(resultSet.getInt("id"));
		comp.setName(resultSet.getString("name"));
		return comp;
	}
}