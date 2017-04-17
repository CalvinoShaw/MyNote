<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Schedule_BO" %>
<%@ page import="vo.Schedule_formVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]--><!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]--><!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]--><!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]--><!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en"><!--<![endif]--> 
	<head>
		<meta charset="utf-8">
		<title>日程管理 - 沙漏 v1.0</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="css/site.css" rel="stylesheet">
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
						日程管理
					</h1>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>
									日程
								</th>
								<th>
									类型
								</th>
								<th>
									状态
								</th>
								<th>
									死线
								</th>
								<th>
									进度
								</th>
								
								<th>
									修改
								</th>
								<th>
									删除
								</th>
							</tr>
						</thead>
						<tbody>
<%
	Schedule_BO schedule_bo_top = new Schedule_BO();
	List<Schedule_formVO> list_top = schedule_bo_top.schedule_form_top();
	Iterator<Schedule_formVO> it_top = list_top.iterator();
	while(it_top.hasNext()){
		Schedule_formVO schedule_top = it_top.next();
%>
							<tr>
								<td>
									<%=schedule_top.getS_name() %>
								</td>
<%
	int type = schedule_top.getS_type();
	String ty = "";
	if(type == 1){
		ty = "工作安排";
	}else if(type == 2){
		ty = "学习管理";
	}else{
		ty = "";
	}
%>
								<td>
									<%=ty %>
								</td>
<%
	int status = schedule_top.getS_status();
	String sta = "";
	if(status == 1){
		sta = "置顶";
	}
%>
								<td>
									<span class="label label-important"><%=sta %></span>
								</td>
								<td>
									<span class="label label-important"><%=schedule_top.getS_deadline() %></span>
								</td>
<%
	int progress = schedule_top.getS_progress();
	String pro = "0%";
	if(progress == 0){
		pro = "0%";
	}else if(progress == 1){
		pro = "25%";
	}else if(progress == 2){
		pro = "50%";
	}else if(progress == 3){
		pro = "75%";
	}else if(progress == 4){
		pro = "100%";
	}else{
		pro = "0%";
	}
%>
								<td>
									<div class="progress">
										<div class="bar" style="width: <%=pro %>;"></div>
									</div>
								</td>
								<td>
									<a href="schedule_update.jsp?s_id=<%=schedule_top.getS_id() %>">修改</a>
								</td>
								<td>
									<!-- schedule_delete_do.jsp?s_id=<%=schedule_top.getS_id() %> -->
								
									<a class="deletelink" href="javascript:void(0);" onclick="javascript:confirmdelete('<%=schedule_top.getS_id() %>');">删除</a>
								</td>
							</tr>
<%
	}
%>
						
<%
	Schedule_BO schedule_bo = new Schedule_BO();
	List<Schedule_formVO> list = schedule_bo.schedule_form_notop();
	Iterator<Schedule_formVO> it = list.iterator();
	while(it.hasNext()){
		Schedule_formVO note = it.next();
%>
							<tr>
								<td>
									<%=note.getS_name() %>
								</td>
<%
	int type = note.getS_type();
	String ty = "";
	if(type == 1){
		ty = "工作安排";
	}else if(type == 2){
		ty = "学习管理";
	}else{
		ty = "";
	}
%>
								<td>
									<%=ty %>
								</td>
<%
	int status = note.getS_status();
	String sta = "";
	if(status == 0){
		sta = "已设置";
	}else if(status == 1){
		sta = "置顶";
	}else if(status == 2){
		sta = "进行中";
	}else if(status == 3){
		sta = "已完成";
	}else if(status == 4){
		sta = "已取消";
	}
%>
								<td>
									<span class="label label-default"><%=sta %></span>
								</td>
								<td>
									<span class="label label-default"><%=note.getS_deadline() %></span>
								</td>
<%
	int progress = note.getS_progress();
	String pro = "0%";
	if(progress == 0){
		pro = "0%";
	}else if(progress == 1){
		pro = "25%";
	}else if(progress == 2){
		pro = "50%";
	}else if(progress == 3){
		pro = "75%";
	}else if(progress == 4){
		pro = "100%";
	}else{
		pro = "0%";
	}
%>
								<td>
									<div class="progress">
										<div class="bar" style="width: <%=pro %>;"></div>
									</div>
								</td>
								<td>
									<a href="schedule_update.jsp?s_id=<%=note.getS_id() %>">修改</a>
								</td>
								<td>
									<!-- schedule_delete_do.jsp?s_id=<%=note.getS_id() %> -->
								
									<a class="deletelink" href="javascript:void(0);" onclick="javascript:confirmdelete('<%=note.getS_id() %>');">删除</a>
								</td>
							</tr>
<%
	}
%>
						</tbody>
					</table>
					
					<div id="pagination" class="pagination">
					</div>
					
					<a class="toggle-link" href="#new-project"><i class="icon-plus"></i> 新增日程</a>
					<form id="new-project" action="schedule_new_do.do?flag=newschedule" method="POST" class="form-horizontal hidden">
						<fieldset>
							<legend>新增日程</legend>
							<div class="control-group">
								<label class="control-label" for="input01">日程名称：</label>
								<div class="controls">
									<input name="s_name" type="text" class="input-xlarge" id="input01" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input01">完成日期：</label>
								<div class="controls">
									<input name="s_deadline" type="text" class="input-xlarge" id="input01" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="select01">日程类型：</label>
								<div class="controls">
									<select name="s_type" id="select01">
										<option value="1">工作安排</option>
										<option value="2">学习管理</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="select01">日程进度：</label>
								<div class="controls">
									<select name="s_progress" id="select01">
										<option value="0">0%</option>
										<option value="1">25%</option>
										<option value="2">50%</option>
										<option value="3">75%</option>
										<option value="4">100%</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="select01">日程状态：</label>
								<div class="controls">
									<select name="s_status" id="select01">
										<option value="0">已设置</option>
										<option value="1">置顶</option>
										<option value="2">进行中</option>
										<option value="3">已完成</option>
										<option value="4">已取消</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="textarea">内容：</label>
								<div class="controls">
									<textarea name="s_content" class="input-xlarge" id="textarea" rows="3"></textarea>
								</div>
							</div>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">创建</button>
								<button class="btn">取消</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/site.js"></script>
		<script type="text/javascript">
			function confirmdelete(delete_id){
				if(confirm("你确定要删除该日程吗？")){
					window.location="schedule_delete_do.do?flag=deleteschedule&s_id=" + delete_id;
				};
			};
		</script>
		<script src="js/pagination.js" type="text/javascript"></script>
	</body>
</html>
