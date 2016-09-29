<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Date" %>
<%@ page import="service.Note_BO" %>
<%@ page import="vo.Note_VO" %>
<% 
	String uid = (String)session.getAttribute("uid");
	if(uid==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<%
	//System.out.println(1);
	request.setCharacterEncoding("UTF-8");
	
	//获取参数，生成参数
	String n_name = request.getParameter("n_name");
	int n_type = Integer.valueOf(request.getParameter("n_type"));
	String n_content = request.getParameter("n_content");
	String n_uptime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	String n_id = UUID.randomUUID().toString();
	
	
	if(
		n_name!=null && !"".equals("n_name") &&
		n_content!=null && !"".equals("n_content") &&
		n_uptime!=null && !"".equals("n_uptime") &&
		n_id!=null && !"".equals("n_id") &&
		n_type!= 0
	){
		//封装参数
		Note_VO new_note = new Note_VO();
		new_note.setN_id(n_id);
		new_note.setN_name(n_name);
		new_note.setN_type(n_type);
		new_note.setN_uptime(n_uptime);
		new_note.setN_content(n_content);
		
		//传入BO类处理，返回结果
		Note_BO note_bo = new Note_BO();
		int rtn = note_bo.newNote(new_note);
		if(rtn == 0){
			//新增失败
			out.print("<script>alert(\"新增失败！请重新操作！\");window.location='note_new.jsp'</script>");
			//out.print("<script type='text/javascript'>document.write('<span style=\"color:red;\">网络出问题了！</span>');</script>");
		}else if(rtn == 1){
			//新增成功
			response.sendRedirect("note_management.jsp");
		}
	}else{
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='note_new.jsp'</script>");
	}
%>