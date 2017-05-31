package fr.ebiz.computer_database.persistence;

import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.User;
import fr.ebiz.computer_database.util.Util;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ebiz on 31/05/17.
 */
@Repository
public class UserDAO {
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public User findByUsername (String username) {
		User user = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
			criteria.add(Restrictions.like(Util.PARAM_NAME, "%" + username + "%"));
			user = (User)criteria.uniqueResult();
			return user;
		} catch (HibernateException e) {
			logger.info(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
}
