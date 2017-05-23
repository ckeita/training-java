package fr.ebiz.computer_database.handler;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chegoujk on 23/05/17.
 */
@Component
public class PageHandler {

    private static Logger logger = LoggerFactory.getLogger(PageHandler.class);

    private int current;
    private int limit;
    private String search;
    private String order;
    private String orderBy;

    @Autowired
    private ComputerService computerService;

    public PageHandler() {}

    public PageHandler(int current, int limit, String search, String orderBy, String order) {
        this.current = current;
        this.limit = limit;
        this.search = search;
        this.orderBy = orderBy;
        this.order = order;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<ComputerDTO> getPage()  {
        try {
            if(this.search != null) {

                return computerService.searchComputers(this.search, this.current, this.limit);
            } else if(this.orderBy != null){
                return computerService.findComputersByOrder(this.orderBy, this.order);
            } else {
                return computerService.findComputersByLimit(this.current, this.limit);
            }
        } catch (DateException | DAOException e) {
            logger.info(e.getMessage());
        }
        return null;
    }
}
