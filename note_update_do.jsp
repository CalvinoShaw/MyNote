<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Note_BO" %>
<%@ page import="vo.Note_updateVO" %>
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
	String n_id = request.getParameter("n_id");
	String n_type_str = request.getParameter("n_type");
	String n_name = request.getParameter("n_name");
	String n_content = request.getParameter("n_content");
	
	if(
		n_id!=null && !"".equals("n_id") &&
		n_type_str!=null && !"".equals("n_type_str") &&
		n_name!=null && !"".equals("n_name") &&
		n_content!=null && !"".equals("n_content")
	){
		int n_type = Integer.valueOf(n_type_str);
		
		Note_updateVO updatevo = new Note_updateVO();
		updatevo.setN_name(n_name);
		updatevo.setN_type(n_type);
		updatevo.setN_content(n_content);
		
		//调用BO类方法
		Note_BO note_bo = new Note_BO();
		int rtn = note_bo.update_update(n_id, updatevo);
		if(rtn == 0){
			//更新失败
			out.print("<script>alert(\"更新失败！请重新操作！\");window.location='note_management.jsp'</script>");
		}else if(rtn == 1){
			//更新成功
			response.sendRedirect("note_management.jsp");
		}
	}else{
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='note_management.jsp'</script>");
	}
%>