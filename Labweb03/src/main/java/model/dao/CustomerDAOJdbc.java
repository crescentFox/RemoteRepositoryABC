package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOJdbc implements CustomerDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "passw0rd";

	

	public static void main(String[] args){
		 CustomerDAO dao=new CustomerDAOJdbc();
		 CustomerBean res=dao.select("Alex");
		 System.out.println(res);
		 
		 System.out.println(dao.update("E".getBytes(), "Ellen@lab.com", new java.util.Date(0), "Ellen"));
	}
	private static final String SELECT = "select * from customer where custid=?";
	
	@Override
	public CustomerBean select(String custid) {
		CustomerBean result = null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
		try {
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			pstmt=conn.prepareStatement(SELECT);
			pstmt.setString(1, custid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=new CustomerBean();
				result.setCustid(rs.getString("custid"));
				result.setPassword(rs.getBytes("password"));
				result.setEmail(rs.getString("email"));
				result.setBirth(rs.getDate("birth"));
												
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String UPDATE = "update customer set password=?, email=?, birth=? where custid=?";

	@Override
	public boolean update(byte[] password, String email, java.util.Date birth,
			String custid) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setBytes(1, password);
			pstmt.setString(2, email);
			java.sql.Date temp=new java.sql.Date(birth.getTime());
			pstmt.setDate(3, temp);
			pstmt.setString(4, custid);
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}		
		return false;
	}
}
