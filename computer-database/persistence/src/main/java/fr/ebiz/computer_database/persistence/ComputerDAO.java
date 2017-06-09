package fr.ebiz.computer_database.persistence;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.util.Util;
import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.model.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author ckeita
 */
@Repository
public class ComputerDAO {
    private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @param id of the computer
     * @return the string of the object
     * @throws DateException .
     * @throws DAOException .
     */
    public Computer findById(int id) throws DAOException {
        Computer computer = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
            criteria.add(Restrictions.eq("id", id));
            computer =  (Computer) criteria.uniqueResult();
            logger.info("[findById] Found element from database");
            return computer;
        } catch (HibernateException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> findAll() throws DAOException {
        List<Computer> computers = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
            computers = criteria.list();
            logger.info("[findAll] Found " + computers.size() + " elements from database");
            return computers;
        } catch (HibernateException e) {
            logger.info("[findAll] " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @return number of computers in database
     * @throws DAOException .
     */
    public long getNumberOfComputers() throws DAOException {
        long number = 0;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
            criteria.setProjection(Projections.rowCount());
            number = (long) criteria.uniqueResult();
            logger.info("[getNumberOfComputers] get number of computers");
            return number;
        } catch (HibernateException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param name to search
     * @return number of searched computers in database
     * @throws DAOException .
     */
    public long getNumberOfSearchedComputers(String name) throws DAOException {
        long number = 0;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
            criteria.add(Restrictions.like(Util.PARAM_NAME, "%" + name + "%"));
            criteria.setProjection(Projections.rowCount());
            number = (long) criteria.uniqueResult();
            logger.info("[getNumberOfComputers] get number of searched computers");
            return number;
        } catch (HibernateException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param current of the first row
     * @param limit number of rows
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> findByLimit(int current, int limit) throws DAOException {
        List<Computer> computers = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
            criteria.setFirstResult(current);
            criteria.setMaxResults(limit);
            computers = criteria.list();
            logger.info("------------------------------------------" + computers);
            logger.info("[findByLimit] Found " + computers.size() + " elements from database");
            return computers;
        } catch (HibernateException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param order for ordering
     * @param orderBy for ordering
     * @param current for ordering
     * @param limit for ordering
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> findByOrder(String orderBy, String order, int current, int limit) throws DAOException, DateException {
        List<Computer> computers = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
            criteria.setFirstResult(current);
            criteria.setMaxResults(limit);
            if (order.equals(Util.ASC)) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
            computers = criteria.list();
            logger.info("[findByOrder] Found " + computers.size() + " elements from database");
            return computers;
        } catch (HibernateException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param name of computer to search
     * @param current of the first row
     * @param limit number of rows
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> searchByLimit(String name, int current, int limit) throws DAOException, DateException {
        List<Computer> computers = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
            criteria.setFirstResult(current);
            criteria.setMaxResults(limit);
            criteria.add(Restrictions.like(Util.PARAM_NAME, "%" + name + "%"));
            computers = criteria.list();
            logger.info("[findByOrder] Found " + computers.size() + " elements from database");
            return computers;
        } catch (HibernateException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void create(Computer comp) throws DAOException {
        sessionFactory.getCurrentSession().persist(comp);
    }

    /**
     * @param compId the ID of computer to delete from database
     * @throws SQLException
     * @exception DAOException .
     */
    public void delete(int compId) throws DAOException {
        Computer computer = null;
        try {
            computer = findById(compId);
            sessionFactory.getCurrentSession().delete(computer);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }

    }

    /**
     * @param comp the computer to update
     * @throws SQLException
     * @exception DAOException .
     */
    public void update(Computer comp) throws DAOException {
        sessionFactory.getCurrentSession().merge(comp);
    }
}
