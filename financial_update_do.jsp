<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Financial_BO" %>
<%@ page import="vo.Financial_updateVO" %>
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
	String f_id = request.getParameter("f_id");
	int f_type = Integer.parseInt(request.getParameter("f_type"));
	String f_amount = request.getParameter("f_amount");
	String f_time = request.getParameter("f_time");
	String f_tip = request.getParameter("f_tip");
	
	if(
		f_id!=null && !"".equals("f_id") &&
		f_amount!=null && !"".equals("f_amount") &&
		f_tip!=null && !"".equals("f_tip") &&
		f_time!=null && !"".equals("f_time") &&
		f_type!=0
	){
		Financial_updateVO updatevo = new Financial_updateVO();
		updatevo.setF_type(f_type);
		updatevo.setF_amount(f_amount);
		updatevo.setF_time(f_time);
		updatevo.setF_tip(f_tip);
		
		//调用BO类方法
		Financial_BO financial_bo = new Financial_BO();
		int rtn = financial_bo.update_update(f_id,updatevo);
		if(rtn == 0){
			//更新失败
			out.print("<script>alert(\"更新失败！请重新操作！\");window.location='financial_management.jsp'</script>");
		}else if(rtn == 1){
			//更新成功
			response.sendRedirect("financial_management.jsp");
		}
	}else{
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='financial_management.jsp'</script>");
	}
%>