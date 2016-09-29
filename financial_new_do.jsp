<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Date" %>
<%@ page import="service.Financial_BO" %>
<%@ page import="vo.Financial_VO" %>
<% 
	String uid = (String)session.getAttribute("uid");
	if(uid==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<%
	request.setCharacterEncoding("UTF-8");
	
	//获取参数，生成参数
	String f_type = request.getParameter("f_type");
	String f_amount = request.getParameter("f_amount");
	String f_tip = request.getParameter("f_tip");
	String f_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	String f_id = UUID.randomUUID().toString();

	if(
		f_id!=null && !"".equals("f_id") &&
		f_type!=null && !"".equals("f_type") &&
		f_amount!=null && !"".equals("f_amount") &&
		f_tip!=null && !"".equals("f_tip") &&
		f_time!=null && !"".equals("f_time")
	){
		//封装参数
		Financial_VO new_financial = new Financial_VO();
		new_financial.setF_id(f_id);
		new_financial.setF_type(f_type);
		new_financial.setF_amount(f_amount);
		new_financial.setF_time(f_time);
		new_financial.setF_tip(f_tip);
		
		//传入BO类处理，返回结果
		Financial_BO financial_bo = new Financial_BO();
		int rtn = financial_bo.newFinancial(new_financial);
		if(rtn == 0){
			//新增失败
			out.print("<script>alert(\"新增失败！请重新操作！\");window.location='financial_new.jsp'</script>");
			//out.print("<script type='text/javascript'>document.write('<span style=\"color:red;\">网络出问题了！</span>');</script>");
		}else if(rtn == 1){
			//新增财务项成功
			response.sendRedirect("financial_management.jsp");
			
		}
	}else{
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='financial_new.jsp'</script>");
	}
%>