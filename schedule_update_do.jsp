<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Schedule_BO" %>
<%@ page import="vo.Schedule_updateVO" %>
<% 
	String uid = (String)session.getAttribute("uid");
	if(uid==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<%
	request.setCharacterEncoding("utf-8");
	
	//获取参数
	String s_id = request.getParameter("s_id");
	String s_name = request.getParameter("s_name");
	String s_deadline = request.getParameter("s_deadline");
	String s_content = request.getParameter("s_content");
	int s_type = Integer.valueOf(request.getParameter("s_type"));
	int s_status = Integer.valueOf(request.getParameter("s_status"));
	int s_progress = Integer.valueOf(request.getParameter("s_progress"));
	
	if(
		s_name!=null && !"".equals("s_name") &&
		s_deadline!=null && !"".equals("s_deadline") &&
		s_content!=null && !"".equals("s_content") &&
		s_type!=0 &&
		s_status!=0 &&
		s_progress!=0
	){
		Schedule_updateVO updatevo = new Schedule_updateVO();
		updatevo.setS_name(s_name);
		updatevo.setS_deadline(s_deadline);
		updatevo.setS_content(s_content);
		updatevo.setS_status(s_status);
		updatevo.setS_progress(s_progress);
		updatevo.setS_type(s_type);
		
		//调用BO类方法
		Schedule_BO schedule_bo = new Schedule_BO();
		int rtn = schedule_bo.update_update(s_id, updatevo);
		if(rtn == 0){
			//更新失败
			out.print("<script>alert(\"更新失败！请重新操作！\");window.location='schedule_management.jsp'</script>");
		}else if(rtn == 1){
			//更新成功
			response.sendRedirect("schedule_management.jsp");
		}
	}else{
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='schedule_management.jsp'</script>");
	}
%>