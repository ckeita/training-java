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
import java.util.Map;

/**
 * Created by chegoujk on 23/05/17.
 */
@Component
public class PageHandler {

    private static Logger logger = LoggerFactory.getLogger(PageHandler.class);

    private static final String CURRENT_PAGE = "current";
    private static final String PAGE_LIMIT = "limit";
    private static final String ORDER_COLUMN = "column";
    private static final String SORT_ORDER = "order";
    private static final String SEARCH = "search";

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_LIMIT = 10;

    private static boolean orderState = false;
    private static boolean searchState = false;
    private static String searchParam = null;
    private static String orderParam = null;

    private static long nbComputers;
    private static int nbPages;
    private static int nbLinks;
    private static int curPage;

    private static String sortPage;

    @Autowired
    private ComputerService computerService;

    /**
     * @param params to retrieve
     * @return the computers to print
     */
    public List<ComputerDTO> getPage(Map<String, String> params)  {
        int currentPage = DEFAULT_PAGE;
        int pageLimit = DEFAULT_LIMIT;
        List<ComputerDTO> computers = null;
        try {
            nbComputers = computerService.getNumberOfComputers();
            nbPages = (int) nbComputers / pageLimit;
            nbLinks = nbPages / pageLimit;
            if (params.size() != 0) {
                if (params.get(CURRENT_PAGE) != null) {
                    currentPage = Integer.parseInt(params.get(CURRENT_PAGE));
                    curPage = currentPage;
                }
                if (params.get(PAGE_LIMIT) != null) {
                    pageLimit = Integer.parseInt(params.get(PAGE_LIMIT));
                }
                if (params.get(SEARCH) != null) {
                    searchParam = params.get(SEARCH);
                    searchState = true;
                }
                if (params.get(ORDER_COLUMN) != null) {
                    orderParam = params.get(ORDER_COLUMN);
                    sortPage = params.get(SORT_ORDER);
                    orderState = true;
                }
                if (!searchState && !orderState) {
                    computers = computerService.findComputersByLimit(currentPage * pageLimit, pageLimit);
                } else if (searchState) {
                    nbComputers = computerService.getNumberOfSearchedComputers(searchParam);
                    computers = computerService.searchComputers(searchParam, currentPage * pageLimit, pageLimit);
                } else if (orderState) {
                    logger.info("[SORT] limit " + pageLimit);
                    computers = computerService.findComputersByOrder(orderParam, sortPage, currentPage * pageLimit, pageLimit);
                }
                nbPages = (int) nbComputers / pageLimit;
                nbLinks = nbPages / pageLimit;
                return computers;
            } else {
                orderState = false;
                searchState = false;
                curPage = currentPage + 1;
                return computerService.findComputersByLimit(currentPage * pageLimit, pageLimit);
            }
        } catch (DateException | DAOException | NumberFormatException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    public long getNbComputers() {
        return nbComputers;
    }

    public int getNbPages() {
        return nbPages;
    }

    public int getNbLinks() {
        return nbLinks;
    }

    public int getCurPage() {
        return curPage;
    }

    public String getSortPage() {
        return sortPage;
    }
}
