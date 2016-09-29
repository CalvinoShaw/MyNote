<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Financial_BO" %>
<%
	
	String uid = (String)session.getAttribute("uid");
	if(uid==null){
		response.sendRedirect("login.jsp");
		return;
	}
	
	//接受参数
	String f_id = request.getParameter("f_id");
	if(
		f_id!=null && !"".equals("f_id")
	){
		//调用BO方法，判断返回值
		Financial_BO financial_bo = new Financial_BO();
		int rtn = financial_bo.delete_financial(f_id);
		
		if(rtn == 0){
			//删除失败
			out.print("<script>alert(\"删除失败！请重新操作！\");window.location='financial_management.jsp'</script>");
		}else if(rtn == 1){
			//删除成功
			response.sendRedirect("financial_management.jsp");
		}
	}else{
		out.print("<script>alert(\"财务id缺失！请重新操作！\");window.location='financial_management.jsp'</script>");
	}
%>