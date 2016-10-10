package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Schedule_BO;
import vo.Schedule_VO;
import vo.Schedule_updateVO;

public class ScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//一进来首先设置request的编码格式，否则以下方法可能出现乱码
		request.setCharacterEncoding("utf-8");
		
		//判断旗标，进入相应的方法
		String flag = request.getParameter("flag");
		if(flag.equals("newschedule")){
			newScheduleServlet(request,response);
		}
		if(flag.equals("updateschedule")){
			updateScheduleServlet(request,response);
		}
		if(flag.equals("deleteschedule")){
			deleteScheduleServlet(request,response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	/*
	 * 增加日程
	 */
	private void newScheduleServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//获取session
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		
		//获取参数/生成参数
		String s_name = request.getParameter("s_name");
		String s_deadline = request.getParameter("s_deadline");
		String s_content = request.getParameter("s_content");
		int s_type = Integer.valueOf(request.getParameter("s_type"));
		int s_status = Integer.valueOf(request.getParameter("s_status"));
		int s_progress = Integer.valueOf(request.getParameter("s_progress"));
		String s_id = UUID.randomUUID().toString();
		
		if(
			s_name!=null && !"".equals("s_name") &&
			s_deadline!=null && !"".equals("s_deadline") &&
			s_content!=null && !"".equals("s_content") &&
			s_type!=0 &&
			s_status!=0 &&
			s_progress!=0
		){
			//封装参数
			Schedule_VO new_schedule = new Schedule_VO();
			new_schedule.setS_id(s_id);
			new_schedule.setS_name(s_name);
			new_schedule.setS_deadline(s_deadline);
			new_schedule.setS_type(s_type);
			new_schedule.setS_progress(s_progress);
			new_schedule.setS_content(s_content);
			new_schedule.setS_status(s_status);
			
			//传入BO类处理，返回结果
			Schedule_BO schedule_bo = new Schedule_BO();
			int rtn = schedule_bo.newSchedule(new_schedule);
			if(rtn == 0){
				//新增失败
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"新增失败！请重新操作！\");window.location='schedule_management.jsp'</script>");
				//out.print("<script type='text/javascript'>document.write('<span style=\"color:red;\">网络出问题了！</span>');</script>");
			}else if(rtn == 1){
				//新增财务项成功
				response.sendRedirect("schedule_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='schedule_management.jsp'</script>");
		}
	}
	
	
	/*
	 * 更新日程
	 */
	private void updateScheduleServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{

		HttpSession session = request.getSession(); 
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}

		//获取参数
		String s_id = request.getParameter("s_id");
		
		String s_name = request.getParameter("s_name");
		System.out.println(s_name);
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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"更新失败！请重新操作！\");window.location='schedule_management.jsp'</script>");
			}else if(rtn == 1){
				//更新成功
				response.sendRedirect("schedule_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='schedule_management.jsp'</script>");
		}
	}
	
	
	/*
	 * 删除日程
	 */
	private void deleteScheduleServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{

		HttpSession session = request.getSession(); 
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}

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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"删除失败！请重新操作！\");window.location='schedule_management.jsp'</script>");
			}else if(rtn == 1){
				//删除成功
				response.sendRedirect("schedule_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='schedule_management.jsp'</script>");
		}
	}
}
