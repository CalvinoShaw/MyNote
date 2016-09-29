<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.User_LoginVO" %>
<%@ page import="service.User_BO" %>
<%@ page import="vo.Login_rtnVO" %>
<%
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
		
		//System.out.println("admin_id"+admin_id);
		//System.out.println("rtn"+rtn);
		
		if(rtn == 0){
			//返回值0，帐号密码无匹配
			response.sendRedirect("login.jsp");
		}else if(rtn == 1){
			//登录成功
			session.setAttribute("uid", admin_id);
			response.sendRedirect("index.jsp");
		}else{
			//帐号密码为空
			response.sendRedirect("login.jsp");
		}
	}else{
		response.sendRedirect("login.jsp");
	}
	

%>