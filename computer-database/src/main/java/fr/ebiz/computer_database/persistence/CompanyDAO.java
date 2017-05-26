package fr.ebiz.computer_database.persistence;

import java.util.List;

import fr.ebiz.computer_database.mapper.CompanyDaoMapper;
import fr.ebiz.computer_database.util.Util;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author ckeita
 */
@Repository
public class CompanyDAO {

    private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    private static final String COMPANIES = "SELECT * FROM company";

	@Autowired
    private DataSource dataSource;

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
    private SessionFactory sessionFactory;
    /**
     * @param id of the Company
     * @return the ResultSet of the query
     * @exception DAOException .
     */
    public Company findById(int id) throws DAOException {
        Company company = null;
//        try {
//            company = this.jdbcTemplate.queryForObject(
//                Util.COMPANIES_BY_ID,
//                new Object[]{id},
//                new CompanyDaoMapper());
//            logger.info("[findById] Found element from database");
//            return company;
//        } catch (DataAccessException e) {
//            logger.info(e.getMessage());
//            throw new DAOException(e.getMessage());
//        }
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class);
        return (Company) criteria.uniqueResult();
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
            companies = this.jdbcTemplate.query(
                Util.COMPANIES_BY_LIMIT,
                new Object[]{offset, max},
                new CompanyDaoMapper());
            logger.info("[findByLimit] Found" + companies.size() + "elements from database");
            return companies;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @exception DAOException .
     * @return resultSet
     */
    public List<Company> findAll() throws DAOException {
        List<Company> companies = null;
//        try {
//            companies = this.jdbcTemplate.query(
//                    COMPANIES,
//                    new CompanyDaoMapper());
//            logger.info("[findAll] Found " + companies.size() + " elements from database");
//            return companies;
//        } catch (DataAccessException e) {
//            logger.info(e.getMessage());
//            throw new DAOException(e.getMessage());
//        }
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class);
        companies = criteria.list();
        logger.info("[COMPANIES] " + companies);
        return companies;
    }
}
