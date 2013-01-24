package com.dpg.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dpg.bean.Gcib;
import com.dpg.dao.GcibDAO;
import com.opensymphony.xwork2.ActionSupport;

public class GridJsonAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(GridJsonAction.class);
	
	// results showed in grid.
	private List<Gcib> gridModel;
	// get how many rows we want to have into the grid - rowNum attribute in the grid
	private Integer rows = 0;
	// Get the requested page. By default grid sets this to 1.
	private Integer page = 1;
	// Limit the result when using local data, value form attribute rowTotal
	private Integer totalrows;
	// Your Total Pages
	private Integer total = 0;
	// All Records
	private Integer records = 0;	
	// get index row - i.e. user click to sort.
	private String sidx;
	// sorting order - asc or desc
	private String sord;
	// Search Field
	private String searchField;
	// The Search String
	private String searchString;
	// The Search Operation
	// ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String searchOper;	
	
	public String execute() throws Exception {
	    log.info("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
	    log.info("Search :" + searchField + " " + searchOper + " " + searchString);	
	    GcibDAO gcibDao = new GcibDAO();	    
	    int to = (rows * page);
	    int from = to - rows;
	    
	    records = gcibDao.getTotal();
	    // Calculate total Pages
	    total = (int) Math.ceil((double) records / (double) rows);
	    
	    Map<String, Object> prmMap = new HashMap<String, Object>();
	    prmMap.put("sidx", sidx);
	    prmMap.put("sord", sord);

	    gridModel = gcibDao.getGcibList(prmMap, from, to);
		
		return SUCCESS;
	}
	
	public List<Gcib> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<Gcib> gridModel) {
		this.gridModel = gridModel;
	}
	
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Integer getTotalrows() {
		return totalrows;
	}

	public void setTotalrows(Integer totalrows) {
		this.totalrows = totalrows;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}
}
