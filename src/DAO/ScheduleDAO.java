package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.ScheduleDTO;

public class ScheduleDAO {
	
	/*
	 * 查找 - 日程总数
	 */
	public int findAllScheduleCount(){
		String sql = "select count(*) from schedule";
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
	 * 查找 - 查找所有 - 非置顶
	 */
	public List<ScheduleDTO> findAllSchedule_notop(){
		List<ScheduleDTO> dto_list = new ArrayList<ScheduleDTO>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from schedule where s_status != 1";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				ScheduleDTO schedule_form_dto = new ScheduleDTO();
				schedule_form_dto.setS_id(rs.getString("s_id"));
				schedule_form_dto.setS_name(rs.getString("s_name"));
				schedule_form_dto.setS_type(rs.getInt("s_type"));
				schedule_form_dto.setS_content(rs.getString("s_content"));
				schedule_form_dto.setS_status(rs.getInt("s_status"));
				schedule_form_dto.setS_deadline(rs.getString("s_deadline"));
				schedule_form_dto.setS_progress(rs.getInt("s_progress"));
				dto_list.add(schedule_form_dto);
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
	 * 查找 - 查找所有 - 置顶
	 */
	public List<ScheduleDTO> findAllSchedule_top(){
		List<ScheduleDTO> dto_list = new ArrayList<ScheduleDTO>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from schedule where s_status = 1";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				ScheduleDTO schedule_form_dto = new ScheduleDTO();
				schedule_form_dto.setS_id(rs.getString("s_id"));
				schedule_form_dto.setS_name(rs.getString("s_name"));
				schedule_form_dto.setS_type(rs.getInt("s_type"));
				schedule_form_dto.setS_content(rs.getString("s_content"));
				schedule_form_dto.setS_status(rs.getInt("s_status"));
				schedule_form_dto.setS_deadline(rs.getString("s_deadline"));
				schedule_form_dto.setS_progress(rs.getInt("s_progress"));
				dto_list.add(schedule_form_dto);
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
	public ScheduleDTO findByScheduleID(String s_id){
		//新建DTO
		ScheduleDTO dto = null;
		
		//连接数据库
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from schedule where s_id='"+s_id+"'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			//封装该id对应的数据进DTO
			if(rs.next()){
				dto = new ScheduleDTO();
				dto.setS_content(rs.getString("s_content"));
				dto.setS_deadline(rs.getString("s_deadline"));
				dto.setS_name(rs.getString("s_name"));
				dto.setS_progress(rs.getInt("s_progress"));
				dto.setS_status(rs.getInt("s_status"));
				dto.setS_type(rs.getInt("s_type"));
			}
			//返回DTO
			return dto;
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
		return null;
	}
	
	
	/*
	 * 新建 - 通过封装类
	 */
	public int createSchedule(ScheduleDTO schedule_dto_receive){
		int rtn = 0;
		schedule_dto_receive.getS_id();
		String sql = "insert into schedule values ('"
				+schedule_dto_receive.getS_id()+"','"
				+schedule_dto_receive.getS_name()+"','"
				+schedule_dto_receive.getS_type()+"','"
				+schedule_dto_receive.getS_content()+"','"
				+schedule_dto_receive.getS_status()+"','"
				+schedule_dto_receive.getS_deadline()+"','"
				+schedule_dto_receive.getS_progress()+"')";
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
		} finally{
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
	public int updateSchedule(ScheduleDTO schedule_dto_receive){
		int rtn = 0;
		System.out.println(schedule_dto_receive.getS_name());
		//连接数据库
		Connection con = null;
		Statement st = null;
		String sql = "update schedule set s_name='"
				+schedule_dto_receive.getS_name()+"', s_deadline='"
				+schedule_dto_receive.getS_deadline()+"', s_content='"
				+schedule_dto_receive.getS_content()+"', s_type="
				+schedule_dto_receive.getS_type()+", s_status="
				+schedule_dto_receive.getS_status()+", s_progress="
				+schedule_dto_receive.getS_progress()+" where s_id='"
				+schedule_dto_receive.getS_id()+"'";
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
		} finally{
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
	public int deleteBySchduleID(String s_id){
		int rtn = 0;
		String sql = "delete from schedule where s_id='"+s_id+"'";
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
		} finally{
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
}
