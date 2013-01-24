package com.dpg.action;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dpg.bean.Gcib;
import com.dpg.dao.GcibDAO;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Using this action to implement the Edit Grid.
 * @author victor
 *
 */
public class GridEditAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(GridEditAction.class);
	
	private String oper = "";
	private String id;
	private String name;
	private String filetype;
	private File url;

	public String execute() throws Exception {
		Gcib gcib;
		GcibDAO gcibDao = new GcibDAO();
		
		log.info(oper+" a record and the information of the record as below:");
		log.info("  id   is " + id);
		log.info("  name is " + name);
		log.info("  type is " + filetype);
		log.info("  url  is " + url);
		
		if(oper.equalsIgnoreCase("add")){
			gcib = new Gcib();
			gcib.setName(name);
			gcib.setFiletype(filetype);
			//add record into database.
			if(gcibDao.addGCIB(gcib) == 0){
				log.warn("ADD RECORD WRONG.");
			};
		}
		if(oper.equalsIgnoreCase("edit")){
			gcib = new Gcib();
			gcib.setId(new Integer(id));
			gcib.setName(name);
			gcib.setFiletype(filetype);
			gcib.setFile(url);
			//update record.
			if(gcibDao.editGCIB(gcib) == 0){
				log.warn("EDIT RECORD WRONG.");
			};
		}
		if(oper.equalsIgnoreCase("del")){
			//delete the record.
			if(gcibDao.delGCIB(new Integer(id)) == 0){
				log.warn("DEL RECORD WRONG.");
			};
		}
		
		return SUCCESS;
	}	
	
	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public File getUrl() {
		return url;
	}

	public void setUrl(File url) {
		this.url = url;
	}
}
