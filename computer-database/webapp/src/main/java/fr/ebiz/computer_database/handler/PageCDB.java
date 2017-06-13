package fr.ebiz.computer_database.handler;

import fr.ebiz.computer_database.model.ComputerDTO;

import java.util.List;

/**
 * Created by chegoujk on 13/06/17.
 */
public class PageCDB {

    private long nbComputers;
    private int nbPages;
    private int nbLinks;
    private int curPage;
    private String sortPage;
    private int limit;
    private List<ComputerDTO> computers;

    public PageCDB() {
    }

    public PageCDB(long nbComputers, int nbPages, int nbLinks, int curPage, String sortPage, int limit) {
        this.nbComputers = nbComputers;
        this.nbPages = nbPages;
        this.nbLinks = nbLinks;
        this.curPage = curPage;
        this.sortPage = sortPage;
        this.limit = limit;
    }

    public long getNbComputers() {
        return nbComputers;
    }

    public void setNbComputers(long nbComputers) {
        this.nbComputers = nbComputers;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public int getNbLinks() {
        return nbLinks;
    }

    public void setNbLinks(int nbLinks) {
        this.nbLinks = nbLinks;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public String getSortPage() {
        return sortPage;
    }

    public void setSortPage(String sortPage) {
        this.sortPage = sortPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<ComputerDTO> getComputers() {
        return computers;
    }

    public void setComputers(List<ComputerDTO> computers) {
        this.computers = computers;
    }
}
