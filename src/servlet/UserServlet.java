package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.User_BO;
import vo.Login_rtnVO;
import vo.User_LoginVO;

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if(flag.equals("login")){
			loginServlet(request,response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}

	/*
	 * 登录处理
	 */
	private void loginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//接受参数，写入 LoginVO
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(
			username!=null && !"".equals("username") &&
			password!=null && !"".equals("password")
		){
			User_LoginVO login_input = new User_LoginVO();
			login_input.setUsername(username);
			login_input.setPassword(password);
			
			//调用 Login_BO的 doLogin 方法
			User_BO login_bo = new User_BO();
			Login_rtnVO login_rtn = login_bo.doLogin(login_input);
			String admin_id = String.valueOf(login_rtn.getAdmin_id());
			int rtn = login_rtn.getRtn();
			
			if(rtn == 0){
				//返回值0，帐号密码无匹配
				response.sendRedirect("login.jsp");
			}else if(rtn == 1){
				//登录成功
				HttpSession session = request.getSession();
				session.setAttribute("uid", admin_id);
				response.sendRedirect("index.jsp");
			}else{
				//帐号密码为空
				response.sendRedirect("login.jsp");
			}
		}else{
			response.sendRedirect("login.jsp");
		}
	}
}
