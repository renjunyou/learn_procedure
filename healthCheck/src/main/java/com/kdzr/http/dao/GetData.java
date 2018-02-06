package com.kdzr.http.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kdzr.http.beans.Clinks;
import com.kdzr.http.beans.OldClinks;
import com.kdzr.http.beans.OldResultInfo;
import com.kdzr.http.beans.ResultInfo;
import com.kdzr.utils.DBConnection;
import com.kdzr.utils.DateUtils;

public class GetData {
	
	private static Logger logger = Logger.getLogger(GetData.class);
	
	public static List<Clinks> getLinks(int flag) {

		List<Clinks> linklist = new ArrayList<Clinks>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t.Lid,t.Lcode,t.Lname,t.Ltitle,t.Lurl,t.Lflag from c_links t where t.Lflag= " + flag;

		DBConnection db = new DBConnection();
		conn = db.getConnection();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Clinks links = new Clinks();
				links.setLid(rs.getInt(1));
				links.setLcode(rs.getString(2));
				links.setLname(rs.getString(3));
				links.setLtitle(rs.getString(4));
				links.setLurl(rs.getString(5));

				linklist.add(links);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// System.out.println(linklist);

		return linklist;
	}
	
	
	public static List<OldClinks> getWebsiteLinks() {

		List<OldClinks> linklist = new ArrayList<OldClinks>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t.Lid,t.Lname,t.Lurl,t.Lparams from links t";

		DBConnection db = new DBConnection();
		conn = db.getConnection();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				OldClinks links = new OldClinks();
				links.setLid(rs.getInt(1));
				links.setLname(rs.getString(2));
				links.setLurl(rs.getString(3));

				linklist.add(links);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// System.out.println(linklist);

		return linklist;
	}

	public static int saveData(List<ResultInfo> infoList) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = -1;// 更新结果 0代表更新成功
		String sql = "insert into c_results(Lcode,Result,RcreateTime) values(?,?,?)";

		DBConnection db = new DBConnection();
		conn = db.getConnection();

		for (ResultInfo rif : infoList) {

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, rif.getLcode());
				ps.setInt(2, rif.getResult());
				ps.setTimestamp(3, rif.getCreateTime());

				flag = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return flag;
		
	}
	
	public static int saveWebsiteData(List<OldResultInfo> riList2) {

		Connection conn = null;
		PreparedStatement ps = null;
		int flag = -1;// 更新结果 true代表更新成功
		String sql = "insert into results(Lid,Result,RcreateTime) values(?,?,?)";

		DBConnection db = new DBConnection();
		conn = db.getConnection();

		for (OldResultInfo rif : riList2) {
			flag = -1;// 更新结果 0代表更新成功

			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rif.getLid());
				if (rif.getResult().equals("成功")) {
					ps.setInt(2, 0);
				} else {
					ps.setInt(2, 1);
				}
				ps.setTimestamp(3, rif.getCreateTime());

				flag = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return flag;
	}

	public static List<ResultInfo> getResultInfo(Date date, String str) {
		
		List<ResultInfo> rsList = new ArrayList<ResultInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT t.Lcode,t.Lname,t.Lurl,g.Result,g.RcreateTime "
				+ "FROM c_links t , c_results g "
				+ "WHERE t.Lcode = g.Lcode "
				+ "and g.RcreateTime BETWEEN " 
				+ "'" + DateUtils.getFormatDate3(date, -1) + "'"
				+ " and " 
				+ "'" +  DateUtils.getFormatDate3(date, 0) + "'"
				+ " and t.Lcode like"
				+ "'" + str + "%" + "'";
		logger.info(sql);
		
		DBConnection db = new DBConnection();
		conn = db.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ResultInfo rf = new ResultInfo();
				rf.setLcode(rs.getString(1));//jdbc rs 从1开始
				rf.setLinkName(rs.getString(2));
				rf.setLinkUrl(rs.getString(3));
				rf.setResult(rs.getInt(4));
				rf.setCreateTime(rs.getTimestamp(5));
				
				rsList.add(rf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsList;
		
	}
	
	public static List<OldResultInfo> getOldResultInfo(Date date) {
		
		List<OldResultInfo> rsList = new ArrayList<OldResultInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT t.Lid,t.Lname,t.Lurl,g.Result,g.RcreateTime "
				+ "FROM links t , results g "
				+ "WHERE t.Lid = g.Lid "
				+ "and g.RcreateTime BETWEEN " 
				+ "'" + DateUtils.getFormatDate3(date, -1) + "'"
				+ " and " 
				+ "'" +  DateUtils.getFormatDate3(date, 0) + "'";
		
		logger.info(sql);
		
		DBConnection db = new DBConnection();
		conn = db.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				OldResultInfo rf = new OldResultInfo();
				rf.setLid(rs.getInt(1));//jdbc rs 从1开始
				rf.setLinkName(rs.getString(2));
				rf.setLinkUrl(rs.getString(3));
				if(rs.getInt("Result") == 0){
					rf.setResult("成功");
				}else{
					rf.setResult("失败");
				}
				
				rf.setCreateTime(rs.getTimestamp(5));
				
				rsList.add(rf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsList;
		
	}
	
	
	//固定时间段，手动发送邮件
	public static List<OldResultInfo> getOldResultInfoByFixTimes(String startTime,String endTime) {
		
		List<OldResultInfo> rsList = new ArrayList<OldResultInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT t.Lid,t.Lname,t.Lurl,g.Result,g.RcreateTime "
				+ "FROM links t , results g "
				+ "WHERE t.Lid = g.Lid "
				+ "and g.RcreateTime BETWEEN " 
				+ "'" + startTime + "'"
				+ " and " 
				+ "'" +  endTime + "'";
		
		logger.info(sql);
		
		DBConnection db = new DBConnection();
		conn = db.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				OldResultInfo rf = new OldResultInfo();
				rf.setLid(rs.getInt(1));//jdbc rs 从1开始
				rf.setLinkName(rs.getString(2));
				rf.setLinkUrl(rs.getString(3));
				if(rs.getInt("Result") == 0){
					rf.setResult("成功");
				}else{
					rf.setResult("失败");
				}
				
				rf.setCreateTime(rs.getTimestamp(5));
				
				rsList.add(rf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		logger.info(rsList);
		
		return rsList;
		
	}
	

	public static List<ResultInfo> getAllResult(Date date) {
		
		List<ResultInfo> rsList = new ArrayList<ResultInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT t.Lcode,t.Lname,t.Lurl,g.Result,g.RcreateTime "
				+ "FROM c_links t , c_results g "
				+ "WHERE t.Lcode = g.Lcode "
				+ "and g.RcreateTime BETWEEN " 
				+ "'" + DateUtils.getFormatDate3(date, -1) + "'"
				+ " and " 
				+ "'" +  DateUtils.getFormatDate3(date, 0) + "'";
		logger.info(sql);
		
		DBConnection db = new DBConnection();
		conn = db.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ResultInfo rf = new ResultInfo();
				rf.setLcode(rs.getString(1));//jdbc rs 从1开始
				rf.setLinkName(rs.getString(2));
				rf.setLinkUrl(rs.getString(3));
				rf.setResult(rs.getInt(4));
				rf.setCreateTime(rs.getTimestamp(5));
				
				rsList.add(rf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsList;
		
	}
	
	
	public static List<OldResultInfo> getWebsiteDate(String startTime,String endTime){
		List<OldResultInfo> rsList = new ArrayList<OldResultInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT t.Lid,t.Lname,t.Lurl,g.Result,g.RcreateTime "
				+ "FROM links t , results g "
				+ "WHERE t.Lid = g.Lid "
				+ "and g.RcreateTime BETWEEN " 
				+ "'" + startTime + "'"
				+ " and " 
				+ "'" + endTime + "'";
		System.out.println(sql);
		
		DBConnection db = new DBConnection();
		conn = db.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				OldResultInfo rf = new OldResultInfo();
				rf.setLid(rs.getInt(1));
				rf.setLinkName(rs.getString(2));
				rf.setLinkUrl(rs.getString(3));
				if(rs.getInt("Result") == 0){
					rf.setResult("成功");
				}else{
					rf.setResult("失败");
				}
				rf.setCreateTime(rs.getTimestamp(5));
				
				rsList.add(rf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsList;
	}

	public static List<ResultInfo> getLinksiteDate(String startTime, String endTime) {
		
		List<ResultInfo> rsList = new ArrayList<ResultInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT t.Lcode,t.Lname,t.Lurl,g.Result,g.RcreateTime "
				+ "FROM c_links t , c_results g "
				+ "WHERE t.Lcode = g.Lcode "
				+ "and g.RcreateTime BETWEEN " 
				+ "'" + startTime + "'"
				+ " and " 
				+ "'" +  endTime + "'";
		logger.info(sql);
		
		DBConnection db = new DBConnection();
		conn = db.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ResultInfo rf = new ResultInfo();
				rf.setLcode(rs.getString(1));//jdbc rs 从1开始
				rf.setLinkName(rs.getString(2));
				rf.setLinkUrl(rs.getString(3));
				rf.setResult(rs.getInt(4));
				rf.setCreateTime(rs.getTimestamp(5));
				
				rsList.add(rf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsList;
		
	}

}
