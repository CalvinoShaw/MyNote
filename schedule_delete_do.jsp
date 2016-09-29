<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Schedule_BO" %>
<% 
	String uid = (String)session.getAttribute("uid");
	if(uid==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<%
	
	//接受参数
	String s_id = request.getParameter("s_id");
	if(
		s_id!=null && !"".equals("s_id")
	){
		//调用BO方法，判断返回值
		Schedule_BO schedule_bo = new Schedule_BO();
		int rtn = schedule_bo.delete_schedule(s_id);
		
		if(rtn == 0){
			//删除失败
			out.print("<script>alert(\"删除失败！请重新操作！\");window.location='schedule_management.jsp'</script>");
		}else if(rtn == 1){
			//删除成功
			response.sendRedirect("schedule_management.jsp");
		}
	}else{
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='schedule_management.jsp'</script>");
	}
%>