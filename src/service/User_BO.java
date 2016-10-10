package service;

import vo.Login_rtnVO;
import vo.User_LoginVO;
import vo.User_TotalcountVO;

import DAO.FinancialDAO;
import DAO.NoteDAO;
import DAO.ScheduleDAO;
import DAO.UserDAO;
import DTO.UserDTO;

public class User_BO {
	
	//登录功能
	public Login_rtnVO doLogin(User_LoginVO login_input){
		
		int admin_id = 0;
		int rtn = 0;
		Login_rtnVO login_rtn = null;
		
		String username = login_input.getUsername();
		String password = login_input.getPassword();
		
		System.out.println(username);
		System.out.println(password);
		
		if(username!=null && password!=null && !"".equals(username) && !"".equals(password)){
			UserDAO user_dao = new UserDAO();
			UserDTO userback_dto = user_dao.findUserID_ByUserName(username);
			if(userback_dto == null){
				//不存在该数据表示用户名或密码错误，返回admin_id为0，rtn为0
				login_rtn = new Login_rtnVO();
				login_rtn.setAdmin_id(admin_id);
				login_rtn.setRtn(rtn);
				return login_rtn;
			}else{
				//存在该数据表示输入正确，改写admin_id，并获取数据库传来的密码
				admin_id = userback_dto.getAdmin_id();
				String admin_password_data = userback_dto.getAdmin_password();		
				
				//判断密码，如果密码正确，则返回admin_id和返回值1
				if(admin_password_data.equals(password)){
					rtn = 1;
					login_rtn = new Login_rtnVO();
					login_rtn.setAdmin_id(admin_id);
					login_rtn.setRtn(rtn);
					return login_rtn;
				}
			}
		}
		//数据为空，则返回2
		rtn = 2;
		login_rtn = new Login_rtnVO();
		login_rtn.setAdmin_id(admin_id);
		login_rtn.setRtn(rtn);
		return login_rtn;
	}
	
	
	//首页数据查询
	public User_TotalcountVO queryIndexStatic(){
		//通过三个DAO分别获取三个表的总数
		ScheduleDAO schedule_dao = new ScheduleDAO();
		int schedule_total = schedule_dao.findAllScheduleCount();
		FinancialDAO financial_dao = new FinancialDAO();
		int financial_total = financial_dao.findAllFinancialCount();
		NoteDAO note_dao = new NoteDAO();
		int note_total = note_dao.findAllNoteCount();
		
		//封装入VO
		User_TotalcountVO indexCount = new User_TotalcountVO();
		indexCount.setSchedule_total(schedule_total);
		indexCount.setFinancial_total(financial_total);
		indexCount.setNote_total(note_total);
		//返回
		return indexCount;
	}
}
