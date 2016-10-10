package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DTO.UserDTO;

public class UserDAO {
	
	/*
	 * 新建用户 - 通过封装类
	 */
	public void createUser(UserDTO userDTO){
		
	}
	
	/*
	 * 更新用户 - 通过封装类
	 */
	public void updateUser(UserDTO userDTO){
		
	}
	
	/*
	 * 删除用户 - 通过主键
	 */
	public void removeByPrimaryKey(String userId){
		
	}
	
	/*
	 * 查找用户 - 通过主键
	 */
	public UserDTO findByPrimaryKey(String userId){

		return null;
	}
	
	/*
	 * 查找所有用户
	 */
	public List<UserDTO> findAllUser(){
		return null;
		
	}
	
	/*
	 * 分批查询用户
	 */
	public List<UserDTO> findAllUserInBatch(int page, int per){
		return null;
		
	}
	
	/*
	 * 查找用户总数
	 */
	public int findAllUserCount(){
		return 0;
		
	}
	
	public UserDTO findUserID_ByUserName(String username){
		String sql = "select * from admin where admin_username = '"+username+"'";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		UserDTO user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				user = new UserDTO();
				user.setAdmin_id(rs.getInt("admin_id"));
				user.setAdmin_username(rs.getString("admin_username"));
				user.setAdmin_password(rs.getString("admin_password"));
				return user;
			}else{
				return null;
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
		return user;
	}
}
