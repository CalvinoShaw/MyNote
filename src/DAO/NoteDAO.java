package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.NoteDTO;

public class NoteDAO {
	
	/*
	 * 查找 - 笔记总数
	 */
	public int findAllNoteCount(){
		String sql = "select count(*) from note";
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
	 * 查找 - 所有笔记
	 */
	public List<NoteDTO> findAllNote(){
		List<NoteDTO> dto_list = new ArrayList<NoteDTO>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from note";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				NoteDTO note_form_dto = new NoteDTO();
				note_form_dto.setN_id(rs.getString("n_id"));
				note_form_dto.setN_name(rs.getString("n_name"));
				note_form_dto.setN_type(rs.getInt("n_type"));
				note_form_dto.setN_uptime(rs.getString("n_uptime"));
				note_form_dto.setN_content(rs.getString("n_content"));
				dto_list.add(note_form_dto);
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
	 * 查找 - 根据id查找单条数据
	 */
	public NoteDTO findByNoteID(String n_id){
		//新建DTO
		NoteDTO dto = null;
		
		//连接数据库
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from note where n_id='"+n_id+"'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			//封装该id对应的数据进DTO
			if(rs.next()){
				dto = new NoteDTO();
				dto.setN_id(rs.getString("n_name"));
				dto.setN_name(rs.getString("n_name"));
				dto.setN_type(rs.getInt("n_type"));
				dto.setN_uptime(rs.getString("n_name"));
				dto.setN_content(rs.getString("n_name"));
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
		return dto;
	}
	
	
	/*
	 * 查找 - 根据type查找多条数据
	 */
	public List<NoteDTO> findByNoteType(int n_type){
		//新建list
		List<NoteDTO> dto_list = new ArrayList<NoteDTO>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from note where n_type="+n_type;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			//封装进DTO
			while(rs.next()){
				NoteDTO dto = new NoteDTO();
				dto.setN_id(rs.getString("n_id"));
				dto.setN_name(rs.getString("n_name"));
				dto.setN_type(rs.getInt("n_type"));
				dto.setN_uptime(rs.getString("n_uptime"));
				dto.setN_content(rs.getString("n_content"));
				dto_list.add(dto);
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
	 * 新建 - 通过封装类
	 */
	public int createNote(NoteDTO note_dto_receive){
		int rtn = 0;
		String sql = "insert into note values ('"
				+note_dto_receive.getN_id()+"','"
				+note_dto_receive.getN_name()+"','"
				+note_dto_receive.getN_type()+"','"
				+note_dto_receive.getN_uptime()+"','"
				+note_dto_receive.getN_content()+"')";
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
	public int updateNote(NoteDTO note_dto_receive){
		int rtn = 0;
		
		//连接数据库
		Connection con = null;
		Statement st = null;
		String sql = "update note set n_name='"
				+note_dto_receive.getN_name()+"', n_type="
				+note_dto_receive.getN_type()+", n_content='"
				+note_dto_receive.getN_content()+"' where n_id='"
				+note_dto_receive.getN_id()+"'";
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
	public int deleteByNoteID(String n_id){
		int rtn = 0;
		String sql = "delete from note where n_id='"+n_id+"'";
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
}
