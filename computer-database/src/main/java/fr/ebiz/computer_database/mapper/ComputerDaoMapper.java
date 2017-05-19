package fr.ebiz.computer_database.mapper;

import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.model.Computer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by ebiz on 19/05/17.
 */
public class ComputerDaoMapper implements RowMapper<Computer> {
	@Override
	public Computer mapRow(ResultSet resultSet, int i) throws SQLException {
		Computer computer;
		try {
			computer = new Computer.ComputerBuilder(resultSet.getString("name"))
					.id(resultSet.getInt("id"))
					.introduced(resultSet.getString("introduced"))
					.discontinued(resultSet.getString("discontinued"))
					.companyName(resultSet.getString("companyName"))
					.companyId(resultSet.getInt("companyId")).build();
			return computer;
		} catch (DateException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
}
