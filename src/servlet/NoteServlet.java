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

import service.Note_BO;
import vo.Note_VO;
import vo.Note_updateVO;

public class NoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//一进来首先设置request的编码格式，否则以下方法可能出现乱码
		request.setCharacterEncoding("utf-8");
		
		//判断旗标，进入相应的方法
		String flag = request.getParameter("flag");
		if(flag.equals("newnote")){
			newNoteServlet(request,response);
		}
		if(flag.equals("updatenote")){
			updateNoteServlet(request,response);
		}
		if(flag.equals("deletenote")){
			deleteNoteServlet(request,response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/*
	 * 增加
	 */
	private void newNoteServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}

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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"新增失败！请重新操作！\");window.location='note_new.jsp'</script>");
				//out.print("<script type='text/javascript'>document.write('<span style=\"color:red;\">网络出问题了！</span>');</script>");
			}else if(rtn == 1){
				//新增成功
				response.sendRedirect("note_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='note_new.jsp'</script>");
		}
	}
	
	/*
	 * 更新
	 */
	private void updateNoteServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}

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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"更新失败！请重新操作！\");window.location='note_management.jsp'</script>");
			}else if(rtn == 1){
				//更新成功
				response.sendRedirect("note_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='note_management.jsp'</script>");
		}
	}
	
	/*
	 * 删除
	 */
	private void deleteNoteServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.jsp");
			return;
		}

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
				//获取Writer
				PrintWriter out = response.getWriter();
				out.print("<script>alert(\"删除失败！请重新操作！\");window.location='note_management.jsp'</script>");
			}else if(rtn == 1){
				//删除成功
				response.sendRedirect("note_management.jsp");
			}
		}else{
			//获取Writer
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='note_management.jsp'</script>");
		}
	}

}
