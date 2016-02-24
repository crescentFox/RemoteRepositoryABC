package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.ProductBean;
import model.ProductDAO;

public class ProductDAOJdbc implements ProductDAO {
//	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
//	private static final String USERNAME = "sa";
//	private static final String PASSWORD = "passw0rd";
private DataSource datasource;
public ProductDAOJdbc(){
	try {
		Context ctx=new InitialContext();
		datasource=(DataSource)ctx.lookup("java:comp/env/jdbc/xxx");
	} catch (NamingException e) {
		e.printStackTrace();
	}
}
	
	
	public static void main(String[] args) {
		ProductDAO dao = new ProductDAOJdbc();
		//select����
		// ProductBean pb = dao.select(1);
		// System.out.println(pb);
		
		//selectAll����
//		List<ProductBean> pbs=dao.select();
//		for(ProductBean pb:pbs){
//			System.out.println(pb);
//		}
		

		//update����
//		 java.sql.Date date = java.sql.Date.valueOf("2016-06-06");
//		 ProductBean pb = dao.update("beer",15,date,20,11);
//		 System.out.println(pb);

//insert����		
//		ProductBean t1 = new ProductBean();
//		t1.setId(11);
//		t1.setName("Choco");
//		t1.setPrice(22);
//		t1.setMake(java.sql.Date.valueOf("2013-09-04"));
//		t1.setExpire(20);
//		ProductBean bean = dao.insert(t1);
//		System.out.println(bean);
		
		//delete����
//		System.out.println(dao.delete(11));
		

	}

	private static final String SELECT_BY_ID = "select * from product where id=?";

	/* (non-Javadoc)
	 * @see modal.dao.ProductDAO#select(int)
	 */
	@Override
	public ProductBean select(int id) {
		ProductBean res = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		//	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				res = new ProductBean();
				res.setId(rs.getInt("id"));
				res.setName(rs.getString("name"));
				res.setPrice(rs.getDouble("price"));
				res.setMake(rs.getDate("make"));
				res.setExpire(rs.getInt("expire"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	private static final String SELECT_ALL = "select * from product";
	
	/* (non-Javadoc)
	 * @see modal.dao.ProductDAO#select()
	 */
	@Override
	public List<ProductBean> select() {
		List<ProductBean> result = null;
		ProductBean bean=null;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
//			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			result=new ArrayList<ProductBean>();
			while(rs.next()){
				bean=new ProductBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setPrice(rs.getDouble(3));
				bean.setMake(rs.getDate(4));
				bean.setExpire(rs.getInt(5));
				result.add(bean);
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
		
		return result;
	} 

	private static final String UPDATE = "update product set name=?, price=?, make=?, expire=? where id=?";

	/* (non-Javadoc)
	 * @see modal.dao.ProductDAO#update(java.lang.String, double, java.util.Date, int, int)
	 */
	@Override
	public ProductBean update(String name, double price, java.util.Date make,
			int expire, int id) {
		ProductBean res = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		//	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, name);
			pstmt.setDouble(2, price);
			java.sql.Date temp = new java.sql.Date(make.getTime());
			pstmt.setDate(3, temp);
			pstmt.setInt(4, expire);
			pstmt.setInt(5, id);
			pstmt.executeUpdate();
			res = new ProductBean();
			res = select(id);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	private static final String INSERT = "insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?)";

	/* (non-Javadoc)
	 * @see modal.dao.ProductDAO#insert(model.ProductBean)
	 */
	@Override
	public ProductBean insert(ProductBean pb) {
		ProductBean res = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
	//		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, pb.getId());
			pstmt.setString(2, pb.getName());
			pstmt.setDouble(3, pb.getPrice());
			if (pb.getMake() != null) {
				java.sql.Date temp = new java.sql.Date(pb.getMake().getTime());
				pstmt.setDate(4, temp);
			}
			pstmt.setInt(5, pb.getExpire());
			pstmt.executeUpdate();

			res = new ProductBean();
			res = select(pb.getId());

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	private static final String DELETE = "delete from product where id=?";

	/* (non-Javadoc)
	 * @see modal.dao.ProductDAO#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
		//	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate()==1){
				return true;
			}else{
				return false;
			}		
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
