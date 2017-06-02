package fr.ebiz.computer_database.persistence;

import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.User;
import fr.ebiz.computer_database.util.Util;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ebiz on 31/05/17.
 */
@Repository
public class UserDAO {
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findByUsername (String username) {
		User user = null;
		user = this.jdbcTemplate.queryForObject(
				"select * from user where username = ?",
				new Object[]{username},
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setUsername(rs.getString("username"));
						user.setPassword(rs.getString("password"));
						user.setAuthorities(rs.getString("authorities"));
						return user;
					}
				});
		logger.info("---------------------[findByUsername]--------------------- ");
		return user;
	}

//	public void addUser(User user) {
//		sessionFactory.getCurrentSession().save(user);
//	}
}
