<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Note_BO" %>
<% 
	String uid = (String)session.getAttribute("uid");
	if(uid==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<%
	
	//接受参数
	String n_id = request.getParameter("n_id");
	if(
		n_id!=null && !"".equals("n_id")
	){
		//调用BO方法，判断返回值
		Note_BO note_bo = new Note_BO();
		int rtn = note_bo.delete_note(n_id);
		
		if(rtn == 0){
			//删除失败
			out.print("<script>alert(\"删除失败！请重新操作！\");window.location='note_management.jsp'</script>");
		}else if(rtn == 1){
			//删除成功
			response.sendRedirect("note_management.jsp");
		}
	}else{
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='note_management.jsp'</script>");
	}
%>