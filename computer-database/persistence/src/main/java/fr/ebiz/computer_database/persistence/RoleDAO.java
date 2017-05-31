package fr.ebiz.computer_database.persistence;

import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.Role;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ebiz on 31/05/17.
 */
public class RoleDAO {
	private static Logger logger = LoggerFactory.getLogger(RoleDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public List<Role> findAll() {
		List<Role> roles = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
			roles = criteria.list();
			return roles;
		}  catch (HibernateException e) {
			logger.info("[findAll] " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
