<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]--><!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]--><!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]--><!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]--><!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en"><!--<![endif]--> 
	<head>
		<meta charset="utf-8">
		<title>新建笔记 - 沙漏 V1.0</title>
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
							<li>
								<a href="financial_statics_plus.jsp?ask=3"><i class="icon-cog"></i> 财务统计</a>
							</li>
							
							<li class="divider">
							</li>

							<li class="nav-header">
								笔记
							</li>
							<li class="active">
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
						新建笔记
					</h1>
					<div class="well blank-slate ">

					<form id="note_new_form" class="form-horizontal" action="note_new_do.do?flag=newnote" method="POST">
					  
					  <div class="control-group">
					    <label class="control-label form-label" for="inputEmail">名称：</label>
					    <div class="controls form-inputs">
					      <input name="n_name" id="note_new_n_name" class="input-block-level" type="text" placeholder="名称">
					      <div id="note_new_n_name_check"><span>笔记名称不能为空！</span></div>
					    </div>
					  </div>

					  <div class="control-group">
					    <label class="control-label form-label" for="inputPassword">归类：</label>
					    <div class="controls form-inputs">
					      <select name="n_type" id="note_new_n_type" class="form-selected">
					        <option value="请选择" selected="selected">请选择</option>
					        <option value='1'>代码如诗</option>
					        <option value='2'>一灯如豆</option>
					      </select>
					      <div id="note_new_n_type_check"><span>请选择类型！</span></div>
					    </div>
					  </div>

					  <div class="control-group">
					    <label class="control-label form-label" for="inputEmail">内容：</label>
					    <div class="controls form-inputs">
					      <textarea name="n_content" id="note_new_n_content" class="input-block-level" placeholder="内容..." rows="10"></textarea> 
					    </div>
					  </div>
					  
					  <a id="note_new_form_btn" href="javascript:submitform()" class="btn btn-primary"><i class="icon-plus icon-white"></i> 创建为新笔记</a>
					</form>
						
					</div>
				</div>
			</div>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/site.js"></script>
		<script type="text/javascript">
			var form = document.getElementById("note_new_form");
			var btn = document.getElementById("note_new_form_btn");
			btn.onclick = function submitform(){
				form.submit();
			};
		</script>
	</body>
</html>
