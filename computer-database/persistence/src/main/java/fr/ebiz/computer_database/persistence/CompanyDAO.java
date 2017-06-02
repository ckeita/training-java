package fr.ebiz.computer_database.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ckeita
 */
@Repository
public class CompanyDAO {

    private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	@Autowired
    private SessionFactory sessionFactory;
    /**
     * @param id of the Company
     * @return the ResultSet of the query
     * @exception DAOException .
     */
    public Company findById(int id) throws DAOException {
        Company company = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class);
            criteria.add(Restrictions.eq("id", id));
            company =  (Company) criteria.uniqueResult();
        } catch (HibernateException e) {
            logger.info("[findById] " + e.getMessage());
        }
        return company;
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @exception DAOException .
     * @return resultSet
     */
    public List<Company> findByLimit(int offset, int max) throws DAOException {
        List<Company> companies = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class);
            criteria.setFirstResult(offset);
            criteria.setMaxResults(max);
            companies = criteria.list();
            logger.info("[findByLimit] Found" + companies.size() + "elements from database");
        } catch (HibernateException e) {
            logger.info("[findByLimit] " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return companies;
    }

    /**
     * @exception DAOException .
     * @return resultSet
     */
    public List<Company> findAll() throws DAOException {
        List<Company> companies = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class);
            companies = criteria.list();
        } catch (HibernateException e) {
            logger.info("[findAll] " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return companies;
    }
}
