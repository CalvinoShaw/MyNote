<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import=" vo.Financial_formVO" %>
<%@ page import=" vo.Financial_statics_VO" %>
<%@ page import=" service.Financial_BO" %>
<%@ page import=" java.util.*" %>
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
		<title>财务统计 - 沙漏 v1.0</title>
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
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="#">沙漏  v1.0</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li class="active">
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
							
							<li>
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
							<li class="active">
								<a href="financial_statics_plus.jsp?ask=3"><i class="icon-cog"></i> 财务统计</a>
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
							<li>
								<a href="note_type.jsp"><i class="icon-stop"></i> 笔记分类</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="span9">
					<h1>
						财务统计 - 支出
					</h1>
<% 
	String ask_str = request.getParameter("ask");
	if(ask_str==null && "".equals("ask_str")){
		out.print("<script>alert(\"出现异常！请重新操作！\");window.location='financial_statics_plus.jsp?ask=3'</script>");
	}
	int ask = Integer.valueOf(ask_str);
	String type = null;
	long money = 0;
	Financial_BO financial_bo1 = new Financial_BO();
	Financial_statics_VO financial_statics = financial_bo1.financial_statics(ask);
	if(ask == 1){
		type = "收入";
		money = financial_statics.getTotalin();
	}else if(ask == 2){
		type = "支出";
		money = financial_statics.getTotalout();
	}
%>
					<div class="hero-unit">
						<h1>
							你的<%=type %>为<%=money %>元
						</h1>
						<div class="row">
						<div class="span2">
							<ul>
								<li><a href="financial_statics.jsp?ask=1">收入统计明细</a></li>
								<li><a href="financial_statics.jsp?ask=2">支出统计明细</a></li>
								
							</ul>
						</div>
						
						</div>
					</div>
					
					<h2>
						详情
					</h2>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>
									类型
								</th>
								<th>
									数额
								</th>
								<th>
									时间
								</th>
							</tr>
						</thead>
<%
	Financial_BO financial_bo2 = new Financial_BO();
	List<Financial_formVO> list = financial_bo2.financial_statics_form(ask);
	Iterator<Financial_formVO> it = list.iterator();
	while(it.hasNext()){
		Financial_formVO financial_static = it.next();
%>
						<tbody>
							<tr>
								<td>
									<%=financial_static.getF_type() %>
								</td>
								<td>
									<%=financial_static.getF_amount() %>
								</td>
								<td>
									<%=financial_static.getF_time() %>
								</td>
							</tr>
<% 
	}
%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/site.js"></script>
	</body>
</html>