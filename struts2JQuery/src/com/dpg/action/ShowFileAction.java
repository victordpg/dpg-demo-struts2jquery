package com.dpg.action;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.dpg.dao.GcibDAO;
import com.opensymphony.xwork2.ActionSupport;
/**
 * This class show how to download file in Struts2 framework.
 * First, to configure result type to "stream" in struts.xml file.
 * Second, to overwrite the parameters in the actions by referenced getMethod();
 * @author victor
 *
 */
public class ShowFileAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ShowFileAction.class);
	private String contentType = "";
	private String contentDisposition = "";
	private String fileID;

	/**
	 * To overwrite the parameter fileStream.
	 * getFileStream() method return the "inputName" fileStream which configured in strutsJQuery.xml.
	 * reference to: <param name="inputName">fileStream</param>
	 * @return InputStream
	 * @throws SQLException
	 * @throws UnsupportedEncodingException 
	 */
	public InputStream getFileStream() throws SQLException, UnsupportedEncodingException {
		log.info("The fileID is "+ fileID);
		GcibDAO dao = new GcibDAO();
		Blob blob = dao.getDBBlobFile(new Integer(fileID));
		BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
		
		log.info("The size of file is "+ blob.length()/1000 + "KB.");
		String fileName = "FileName";
		fileName = dao.getFileName(new Integer(fileID));
		contentType = dao.getFileType(new Integer(fileID));
		fileName = fileName + contentType;
		
		if (contentType.contains("pdf")) {
			contentType = "application/pdf;charset=UTF-8";
		}
		if (contentType.contains("jpg") || contentType.contains("jpeg")) {
			contentType = "image/*;charset=UTF-8";
		}
		log.info("The ContentType of file is "+ contentType);
		
		//IMPORTANT: different browser different ways to deal file download and preview. 
		String agent = ServletActionContext.getRequest().getHeader("USER-AGENT");
		if(agent.indexOf("MSIE")==-1){
			String enableName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			//IMPORTANT: attachment mean download the file；inline mean open file in browser.
			contentDisposition = "inline; filename=" + enableName ;
		}else{
			contentDisposition = "inline; filename=" + java.net.URLEncoder.encode(fileName,"UTF-8");
		}		
		return in;
	}

	/**
	 * To overwrite the parameter contentType. 
	 * reference to: <param name="contentType">image/jpeg</param>
	 * @return
	 */
	public String getContentType(){
		return contentType;
	}
	
	/**
	 * To overwrite the parameter contentDisposition. 
	 * reference to: <param name="contentDisposition">attachment;filename="document.pdf"</param>
	 * @return
	 */
	public String getContentDisposition() {
		return contentDisposition;
	}	
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}	
}
