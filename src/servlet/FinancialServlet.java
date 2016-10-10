package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Financial_BO;
import vo.Financial_VO;
import vo.Financial_updateVO;

public class FinancialServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//一进来首先设置request的编码格式，否则以下方法可能出现乱码
		request.setCharacterEncoding("utf-8");
		
		//判断旗标，进入相应的方法
		String flag = request.getParameter("flag");
		if(flag.equals("newfinancial")){
			newFinancialServlet(request,response);
		}
		if(flag.equals("updatefinancial")){
			updateFinancialServlet(request,response);
		}
		if(flag.equals("deletefinancial")){
			deleteFinancialServlet(request,response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/*
	 * 增加
	 */
	private void newFinancialServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}

		//获取参数，生成参数
		int f_type = Integer.parseInt(request.getParameter("f_type"));
		String f_amount = request.getParameter("f_amount");
		String f_tip = request.getParameter("f_tip");
		String f_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		String f_id = UUID.randomUUID().toString();

		if(
			f_id!=null && !"".equals("f_id") &&
			f_amount!=null && !"".equals("f_amount") &&
			f_tip!=null && !"".equals("f_tip") &&
			f_time!=null && !"".equals("f_time") &&
			f_type != 0
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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"新增失败！请重新操作！\");window.location='financial_new.jsp'</script>");
				//out.print("<script type='text/javascript'>document.write('<span style=\"color:red;\">网络出问题了！</span>');</script>");
			}else if(rtn == 1){
				//新增财务项成功
				response.sendRedirect("financial_management.jsp");
				
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='financial_new.jsp'</script>");
		}
	}
	
	/*
	 * 更新
	 */
	private void updateFinancialServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}

		//获取参数
		String f_id = request.getParameter("f_id");
		int f_type = Integer.valueOf(request.getParameter("f_type"));
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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"更新失败！请重新操作！\");window.location='financial_management.jsp'</script>");
			}else if(rtn == 1){
				//更新成功
				response.sendRedirect("financial_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='financial_management.jsp'</script>");
		}
	}
	
	/*
	 * 删除
	 */
	private void deleteFinancialServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"删除失败！请重新操作！\");window.location='financial_management.jsp'</script>");
			}else if(rtn == 1){
				//删除成功
				response.sendRedirect("financial_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"财务id缺失！请重新操作！\");window.location='financial_management.jsp'</script>");
		}
	}
}
