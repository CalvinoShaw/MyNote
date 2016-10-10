package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.FinancialDTO;

public class FinancialDAO {
	
	/*
	 * 查找 - 财务总数
	 */
	public int findAllFinancialCount(){
		String sql = "select count(*) from financial";
		int total = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
			return total;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return total;
	}
	
	
	/*
	 * 查找 - 所有财务
	 */
	public List<FinancialDTO> findAllFinancial(){
		List<FinancialDTO> dto_list = new ArrayList<FinancialDTO>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from financial";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				FinancialDTO financial_form_dto = new FinancialDTO();
				financial_form_dto.setF_id(rs.getString("f_id"));
				financial_form_dto.setF_type(rs.getInt("f_type"));
				financial_form_dto.setF_amount(rs.getString("f_amount"));
				financial_form_dto.setF_time(rs.getString("f_time"));
				financial_form_dto.setF_tip(rs.getString("f_tip"));
				dto_list.add(financial_form_dto);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto_list;
	}
	
	
	/*
	 * 查找 - 根据id查找
	 */
	public FinancialDTO findByFinancialID(String f_id){
		FinancialDTO dto = null;
		
		//连接数据库
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from financial where f_id='"+f_id+"'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			//封装该id对应的数据
			if(rs.next()){
				dto = new FinancialDTO();
				dto.setF_type(rs.getInt("f_type"));
				dto.setF_amount(rs.getString("f_amount"));
				dto.setF_time(rs.getString("f_time"));
				dto.setF_tip(rs.getString("f_tip"));
				dto.setF_id(rs.getString("f_id"));
			}
			return dto;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}
	
	
	/*
	 * 新建 - 通过封装类
	 */
	public int createFinancial(FinancialDTO financial_dto_receive){
		int rtn = 0;
		String sql = "insert into financial values ('"
				+financial_dto_receive.getF_id()+"','"
				+financial_dto_receive.getF_type()+"','"
				+financial_dto_receive.getF_amount()+"','"
				+financial_dto_receive.getF_time()+"','"
				+financial_dto_receive.getF_tip()+"')";
		//连接数据库
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			st.executeUpdate(sql);
			rtn = 1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return rtn;
		} catch (SQLException e) {
			e.printStackTrace();
			return rtn;
		}finally{
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rtn;
	}
	
	
	/*
	 * 更新 - 通过封装类
	 */
	public int updateFinancial(FinancialDTO financial_dto_receive){
		int rtn = 0;
		
		//连接数据库
		Connection con = null;
		Statement st = null;
		String sql = "update financial set f_type='"
				+financial_dto_receive.getF_type()+"',f_amount='"
				+financial_dto_receive.getF_amount()+"',f_time='"
				+financial_dto_receive.getF_time()+"',f_tip='"
				+financial_dto_receive.getF_tip()+"' where f_id='"
				+financial_dto_receive.getF_id()+"'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			st.executeUpdate(sql);
			rtn = 1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return rtn;
		} catch (SQLException e) {
			e.printStackTrace();
			return rtn;
		}finally{
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rtn;
	}
	
	
	/*
	 * 删除 - 通过id删除单条
	 */
	public int deleteByFinancialID(String f_id){
		int rtn = 0;
		String sql = "delete from financial where f_id='"+f_id+"'";
		//连接数据库
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			st.executeUpdate(sql);
			rtn = 1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return rtn;
		} catch (SQLException e) {
			e.printStackTrace();
			return rtn;
		}finally{
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rtn;
	}
	
	
	/*
	 * 查找 - 根据type查找多条数据
	 */
	public List<FinancialDTO> findByFinancialType(int f_type){
		//新建list
		List<FinancialDTO> dto_list = new ArrayList<FinancialDTO>();
		
		//连接数据库
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from financial where f_type="+f_type;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				FinancialDTO dto = new FinancialDTO();
				dto.setF_id(rs.getString("f_id"));
				dto.setF_type(rs.getInt("f_type"));
				dto.setF_amount(rs.getString("f_amount"));
				dto.setF_time(rs.getString("f_time"));
				dto.setF_tip(rs.getString("f_tip"));
				dto_list.add(dto);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto_list;
	}
	
	
	/*
	 * 查找 - 根据f_type返回f_amount求和
	 */
	public long findSumByFinancialType(int f_type){
		long total = 0;
		//连接数据库
		String sql_totalin = "select sum(f_amount) from financial where f_type="+f_type;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql_totalin);
			if(rs.next()){
				total = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return total;
	}
}
