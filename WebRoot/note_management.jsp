<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.Note_BO" %>
<%@ page import="vo.Note_formVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
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
		<title>笔记管理 - 沙漏 v1.0</title>
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
							<li>
								<a href="note_new.jsp"><i class="icon-picture"></i> 新建笔记</a>
							</li>
							<li class="active">
								<a href="note_management.jsp"><i class="icon-stop"></i> 笔记管理</a>
							</li>
							<li>
								<a href="note_type.jsp"><i class="icon-stop"></i> 笔记分类</a>
							</li>
						</ul>
					</div>·
				</div>
				<div class="span9">
					<h1>
						笔记管理
					</h1>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>
									笔记
								</th>
								<th>
									分类
								</th>
								<th>
									修改日期
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
	Note_BO note_bo = new Note_BO();
	List<Note_formVO> list = note_bo.note_form();
	Iterator<Note_formVO> it = list.iterator();
	while(it.hasNext()){
		Note_formVO note = it.next();
%>
							<tr>
								<td>
									<a href="note_view.jsp?n_id=<%=note.getN_id() %>" class="view-link"><%=note.getN_name() %></a>
								</td>
<% 
	int note_typeid = note.getN_type();
	String note_typename = null;
	if(note_typeid == 1){
		note_typename = "一灯如豆";
	}else if(note_typeid == 2){
		note_typename = "代码如诗";
	}
%>
								<td>
									<a href="note_type.jsp?n_type=<%=note_typeid %>" class="view-link"><%=note_typename %></a>
								</td>
								<td>
									<%=note.getN_uptime() %>
								</td>
								<td>
									<a href="note_update.jsp?n_id=<%=note.getN_id() %>" class="view-link">修改</a>
								</td>
								<td>
									<a href="note_delete_do.do?flag=deletenote&n_id=<%=note.getN_id() %>" class="view-link">删除</a>
								</td>
							</tr>
<%
	}
%>
						</tbody>
					</table>				
					<div id="pagination" class="pagination">
						
					</div>
				</div>
			</div>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/site.js"></script>
		<script type="text/javascript">
			var pageContainer = document.getElementById('pagination');
			var array = [];
			var page = document.getElementsByClassName('page');
			var items = document.getElementsByTagName('tr');
			
			//定义一个数组，剔除了第一行（表格栏目行），存储剩余的元素
			var item = [];
			for(var i=1,len=items.length; i<len; i++){
				item.push(items[i]);
			}
			
			var totalItem = item.length;
			var eachPage = 5;
			var totalPage = Math.ceil(totalItem/eachPage);
			var currentPage = 1;
			
			if(currentPage == 1){
				var currentBeginItem = eachPage * (currentPage-1);
				//显示当前页应该显示的item
				for(var d = currentBeginItem; d < currentBeginItem + eachPage; d++){
					item[d].style.display = "table-row";
				}
			}
			var a = 1;
			
			if (currentPage == 1) {
				array[array.length] = "<ul><li><a class=\"page\" href=\"#\" id=\"unclick\">上一页</a></li>";
			} else {
				array[array.length] = "<ul><li><a class=\"page\" href=\"#\" >上一页</a></li>";
			}
	
			for (var a = 1; a <=totalPage; a++) {
				
				array[a] = "<li><a class=\"page\" href=\"#\">" + a +"</a></li>";
			}
	
			if (currentPage == totalPage) {
				array[array.length] = "<li><a class=\"page\" href=\"#\" id=\"unclick\">下一页</a></li></ul>";
			} else {
				array[array.length] = "<li><a class=\"page\" href=\"#\" >下一页</a></li></ul>";
			}
			pageContainer.innerHTML = array.join("");

			for (var b = 0; b < page.length; b++) {//对于每一个页码
	
				page[b].onclick = function() {//当页码被点击时			
					currentPage = this.innerHTML;//当前页即为被被点击的页码
					var currentBeginItem = eachPage*(currentPage-1);//当前页最先开始显示的 item
					for (var c = 0; c < item.length; c++) {//清除掉所有item的显示
						item[c].style.display = "none";
					}
					for(var d=currentBeginItem; d<currentBeginItem+eachPage; d++){//显示当前页应该显示的item
						item[d].style.display = "table-row";
					}
				};
	
				page[page.length-1].onclick=function(){
					
					if(currentPage!=totalPage){
						currentPage++;//当前页即为被被点击的页码
						var currentBeginItem = eachPage*(currentPage-1);//当前页最先开始显示的 item
						for (var c = 0; c < item.length; c++) {//清除掉所有item的显示
							item[c].style.display = "none";
						}
						for(var d=currentBeginItem; d<currentBeginItem+eachPage; d++){//显示当前页应该显示的item
							item[d].style.display = "table-row";
						}
					}else{
						alert('已经是最后一页');
					}
				};
	
				page[0].onclick=function(){
					// alert(0);
					if(currentPage!=1){
						currentPage--;//当前页即为被被点击的页码
						var currentBeginItem = eachPage*(currentPage-1);//当前页最先开始显示的 item
						for (var c = 0; c < item.length; c++) {//清除掉所有item的显示
							item[c].style.display = "none";
						}
						for(var d=currentBeginItem; d<currentBeginItem+eachPage; d++){//显示当前页应该显示的item
							item[d].style.display = "table-row";
						}
					}else{
						alert('已经是第一页');
					}
				};
			}
		</script>
	</body>
</html>
