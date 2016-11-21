<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Schedule_BO" %>
<%@ page import="vo.Schedule_updateVO" %>
<% 
	String uid = (String)session.getAttribute("uid");
	if(uid==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]--><!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]--><!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]--><!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]--><!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en"><!--<![endif]--> 
	<head>
		<meta charset="utf-8">
		<title>修改日程 - 沙漏 V1.0</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="css/site.css" rel="stylesheet">
		<link href="css/mycss.css" rel="stylesheet">
		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	</head>
	<body>
		<div class="container">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="#">沙漏 v1.0</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li>
									<a href="index.jsp">欢迎</a>
								</li>
								<li>
									<a href="credit.jsp">账户设置</a>
								</li>
								<li>
									<a href="">帮助</a>
								</li>
								<li class="dropdown">
									<a href="" class="dropdown-toggle" data-toggle="dropdown">最近 <b class="caret"></b></a>
									<ul class="dropdown-menu">
										
										<li class="nav-header">
											日程
										</li>
										<li>
											<a href="">8月2日  新增笔记 《大教堂与集市》</a>
										</li>
										<li>
											<a href="">8月5日  新增财务  支出</a>
										</li>
										<li class="divider">
										</li>
										<li class="nav-header">
											笔记
										</li>
										<li>
											<a href="">8月2日  新增笔记 《大教堂与集市》</a>
										</li>
										<li>
											<a href="">7月20日  修改笔记  《数据分析》</a>
										</li>
										<li class="divider">
										</li>
										<li class="nav-header">
											财务
										</li>
										<li>
											<a href="">8月5日  新增1项支出</a>
										</li>
										<li>
											<a href="">8月4日  新增1项支出</a>
										</li>
									</ul>
								</li>
							</ul>
							<form class="navbar-search pull-left" action="">
								<input type="text" class="search-query span2" placeholder="搜索" />
							</form>
							<ul class="nav pull-right">
<%
	int count = listener.sessionListener.getCount();
	//out.println("在线人数："+count);
%>
								<li>
									<a href="">当前在线人数：<%=count %></a>
								</li>
								<li>
									<a href="">@卡尔维诺</a>
								</li>
								<li>
									<a href="">退出</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span3">
					<div class="well" style="padding: 8px 0;">
						<ul class="nav nav-list">
							<li class="nav-header">
								日程
							</li>
							<li>
								<a href="schedule_waterfall.jsp"><i class="icon-white icon-home"></i> 时间瀑布</a>
							</li>
							
							<li class="active">
								<a href="schedule_management.jsp"><i class="icon-check"></i> 日程管理</a>
							</li>
							
							<li class="divider">
							</li>

							<li class="nav-header">
								财务
							</li>
							<li>
								<a href="financial_new.jsp"><i class="icon-user"></i> 新增财务</a>
							</li>
							<li>
								<a href="financial_management.jsp"><i class="icon-user"></i> 财务管理</a>
							</li>
							<li>
								<a href="financial_statics.jsp?ask=3"><i class="icon-cog"></i> 财务统计</a>
							</li>
							
							<li class="divider">
							</li>

							<li class="nav-header">
								笔记
							</li>
							<li>
								<a href="note_new.jsp"><i class="icon-picture"></i> 新建笔记</a>
							</li>
							<li>
								<a href="note_management.jsp"><i class="icon-stop"></i> 笔记管理</a>
							</li>
							
						</ul>
					</div>
				</div>
				<div class="span9">
					<h1>
						修改日程
					</h1>
					<div class="well blank-slate ">
<% 
	String s_id = request.getParameter("s_id");
	if(s_id==null && "".equals("s_id")){
		out.print("<script>alert(\"参数缺失！请重新操作！\");window.location='schedule_management.jsp'</script>");
	}
	
	Schedule_BO schedule_bo = new Schedule_BO();
	Schedule_updateVO updatevo = schedule_bo.update_show(s_id);
%>
					<form id="schedule_update_form" class="form-horizontal" action="schedule_update_do.do?s_id=<%=s_id %>&flag=updateschedule" method="POST">
					  
					  <div class="control-group">
					    <label class="control-label form-label" for="inputEmail">日程：</label>
					    <div class="controls form-inputs">
					      <input name="s_name" value="<%=updatevo.getS_name() %>" class="input-block-level" type="text" placeholder="日程">
					    </div>
					  </div>
					  
					  <div class="control-group">
					    <label class="control-label form-label" for="inputEmail">预完成日期：</label>
					    <div class="controls form-inputs">
					      <input name="s_deadline" value="<%=updatevo.getS_deadline() %>" class="input-block-level" type="text" id="inputEmail" placeholder="预完成日期">
					    </div>
					  </div>

					  <div class="control-group">
					    <label class="control-label form-label" for="inputPassword">类型：</label>
					    <div class="controls form-inputs">
					      <select name="s_type" class="form-selected">
<% 
	if(updatevo.getS_type() == 1){
%>
					        <option value="1" selected>工作安排</option>
							<option value="2">学习管理</option>
<%
	}else if(updatevo.getS_type() == 2){
%>
					        <option value="1">工作安排</option>
							<option value="2" selected>学习管理</option>
<%
	}
%>
					      </select>
					    </div>
					  </div>

					  <div class="control-group">
					    <label class="control-label form-label" for="inputPassword">状态：</label>
					    <div class="controls form-inputs">
					      <select name="s_status" class="form-selected">
<% 
	if(updatevo.getS_status() == 0){
%>
					      	<option value="0" selected>已设置</option>
							<option value="1">置顶</option>
							<option value="2">进行中</option>
							<option value="3">已完成</option>
							<option value="4">已取消</option>
<%
	}else if(updatevo.getS_status() == 1){
%>
					      	<option value="0">已设置</option>
							<option value="1" selected>置顶</option>
							<option value="2">进行中</option>
							<option value="3">已完成</option>
							<option value="4">已取消</option>
<%
	}else if(updatevo.getS_status() == 2){
%>
					      	<option value="0">已设置</option>
							<option value="1">置顶</option>
							<option value="2" selected>进行中</option>
							<option value="3">已完成</option>
							<option value="4">已取消</option>
<%
	}else if(updatevo.getS_status() == 3){
%>
					      	<option value="0">已设置</option>
							<option value="1">置顶</option>
							<option value="2">进行中</option>
							<option value="3" selected>已完成</option>
							<option value="4">已取消</option>
<%
	}else{
%>
					      	<option value="0">已设置</option>
							<option value="1">置顶</option>
							<option value="2">进行中</option>
							<option value="3">已完成</option>
							<option value="4" selected>已取消</option>
<%
	}
%>
					      </select>
					    </div>
					  </div>
					  
					  <div class="control-group">
					    <label class="control-label form-label" for="inputPassword">进度：</label>
					    <div class="controls form-inputs">
					      <select name="s_progress" class="form-selected">
<% 
	if(updatevo.getS_progress() == 0){
%>
					      	<option value="0" selected>0%</option>
							<option value="1">25%</option>
							<option value="2">50%</option>
							<option value="3">75%</option>
							<option value="4">100%</option>
<%
	}else if(updatevo.getS_progress() == 1){
%>
					      	<option value="0">0%</option>
							<option value="1" selected>25%</option>
							<option value="2">50%</option>
							<option value="3">75%</option>
							<option value="4">100%</option>
<%
	}else if(updatevo.getS_progress() == 2){
%>
					      	<option value="0">0%</option>
							<option value="1">25%</option>
							<option value="2" selected>50%</option>
							<option value="3">75%</option>
							<option value="4">100%</option>
<%
	}else if(updatevo.getS_progress() == 3){
%>
					      	<option value="0">0%</option>
							<option value="1">25%</option>
							<option value="2">50%</option>
							<option value="3" selected>75%</option>
							<option value="4">100%</option>
<%
	}else{
%>
					      	<option value="0">0%</option>
							<option value="1">25%</option>
							<option value="2">50%</option>
							<option value="3">75%</option>
							<option value="4" selected>100%</option>
<%
	}
%>
					      </select>
					    </div>
					  </div>

					  <div class="control-group">
					    <label class="control-label form-label" for="inputEmail">内容</label>
					    <div class="controls form-inputs">
					      <textarea name="s_content" class="input-block-level" placeholder="内容..." rows="10"><%=updatevo.getS_content() %></textarea> 
					    </div>
					  </div>
					</form>
						<a id="schedule_update_sure_btn" href="javascript:updatesubmit()" class="btn btn-primary"><i class="icon-plus icon-white"></i> 保存</a>
						<a href="schedule_management.jsp" class="btn btn-primary"><i class="icon-plus icon-white"></i> 返回</a>
					</div>
				</div>
			</div>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/site.js"></script>
		<script type="text/javascript">
			var form = document.getElementById("schedule_update_form");
			var btn = document.getElementById("schedule_update_sure_btn");
			btn.onclick = function updatesubmit(){
				form.submit();
			};
		</script>
	</body>
</html>
