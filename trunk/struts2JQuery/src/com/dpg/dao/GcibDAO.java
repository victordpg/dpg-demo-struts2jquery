package com.dpg.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dpg.bean.Gcib;
import com.dpg.util.DBConnection;

/**
 * Manage the GCIB object.
 * @author Victor(DIAO,PIGANG)
 */
public class GcibDAO {
	/**
	 * Get the search results with pagination.
	 * @param paraMap
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Gcib> getGcibList(Map<String,Object> paraMap,int start, int end) {
		List<Gcib> folderList = new ArrayList<Gcib>();
		String sql = "select id,name,filetype from gcib where 1=1 ";
		//grid order.
		String sortname = (String)paraMap.get("sidx");
		String sortorder = (String)paraMap.get("sord");
		String orderSQL = "";	 //record order statement.
		if(!this.isEmpty(sortname)&&!this.isEmpty(sortorder)){
			orderSQL= " order by " + sortname+" "+sortorder;
		}
		sql = sql + orderSQL;
		sql = sql + " limit "+start+","+end+"";
		Connection connect = DBConnection.getConnection();
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Gcib gcib = new Gcib();
				Integer idInt = rs.getInt("id");
				gcib.setId(idInt);
				gcib.setName(rs.getString("name"));
				gcib.setFiletype(rs.getString("filetype"));
				String url = "预览";
				gcib.setUrl(url);
				folderList.add(gcib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return folderList;
	}
	
	/**
	 * Get the file in byte[].
	 * @param id
	 * @return
	 */
	public byte[] getDBFile(int id) {
		String sqlFind = "select file from gcib where id = "+id;
		byte[] pdf = null;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				pdf = rs.getBytes("file");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return pdf;
	}

	/**
	 * Get the file in Blob.
	 * @param id
	 * @return
	 */
	public Blob getDBBlobFile(int id) {
		String sqlFind = "select file from gcib where id = "+id;
		Blob pdf = null;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				pdf = rs.getBlob("file");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return pdf;
	}	
	
	/**
	 * Get the total of the records.
	 * @return
	 */
	public int getTotal() {
		int total  = 0;
		String sqlFind = "select count(*) from gcib ";
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * Get the file type of the stream for download and preview the file. 
	 * @param id
	 * @return
	 */
	public String getFileType(Integer id) {
		String sqlFind = "select fileType from gcib where id = "+id;
		String result = null;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				result = rs.getString("fileType");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Get the name of the stream for download and preview the file. 
	 * @param id
	 * @return
	 */
	public String getFileName(Integer id) {
		String sqlFind = "select name from gcib where id = "+id;
		String result = null;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				result = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Insert new record into database.
	 * @param gcib
	 * @return
	 */
	public int addGCIB(Gcib gcib){
		String sqlAdd = "insert into gcib(name,filetype) values('"+gcib.getName()+"','"+gcib.getFiletype()+"')";
		int rs = 0 ;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeUpdate(sqlAdd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Update the record.
	 * @param gcib
	 * @return
	 * @throws FileNotFoundException 
	 */
	public int editGCIB(Gcib gcib) throws FileNotFoundException{
		String sqlEdit = "update gcib set name='"+gcib.getName()+"',file=?,filetype='"+gcib.getFiletype()+"' where id="+gcib.getId()+"";
		int rs = 0;
		InputStream in = null;
		PreparedStatement stmt = null;
		Connection conn = DBConnection.getConnection();
		try {
			if(gcib.getFile()!=null){
				in = new BufferedInputStream(new FileInputStream(gcib.getFile()));
				stmt = conn.prepareStatement(sqlEdit);
				stmt.setBinaryStream(1, in, gcib.getFile().length());
			}else{
				sqlEdit = "update gcib set name='"+gcib.getName()+"',filetype='"+gcib.getFiletype()+"' where id="+gcib.getId()+"";
			}
			stmt = conn.prepareStatement(sqlEdit);
			//update blob into database.
			stmt.execute();
			rs = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Delete the record.
	 * @param gcib
	 * @return
	 */
	public int delGCIB(Integer id){
		String sqlEdit = "delete from gcib where id="+id+"";
		int rs = 0;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeUpdate(sqlEdit);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return rs;
	}	
	
	/**
	 * To check the string whether is empty.
	 * @param para
	 * @return
	 */
	private boolean isEmpty(String para){
		return null==para || para.length()==0;
	}	
}
