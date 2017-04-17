### 2016年9月1日

------

经过3天左右的开发，从页面草稿搭建开始，一致到在 MyEclipse 中真正开始建构，MyNote（沙漏 v1.0）重点功能基本做完，涵盖登录以及日程管理、财务管理、笔记管理的增删改查操作，以及财务的简单统计。

但同时缺的东西也多，距离预期效果还有差距：

- 时间瀑布页面：未完成，计划形式：瀑布流、动画效果，动态展示日程线，类似“时光穿梭机”

- 其他未完成功能：

  - 分页实现
  - 置顶功能
  - 日程备注：为了使表格清洁，表格中是没有返回 `s_content`的，但是同时有查看的需要。计划做一个`hove`
  - 财务统计：进一步完善，增加统计指标，`js`图表
  - 笔记分类：使目前链接失效（目前是没有`ask`参数传递的，点了也没用），做一个下拉列表，在二级菜单中的链接加参数，从而实现正确跳转
  - 笔记查看：看情况新增数据表（或者字段），如“代码如诗”下还包括`javascript`、`java`等分类，同时，文章查看页面的右侧边栏：近期文章、相关文章的实现
  - 页面顶部菜单栏：“最近”下拉列表的实现
  - 注册功能
  - 个人信息设置功能
  - 一些根据需要，对`Bootstrap`中`CSS`样式的小修补：如“置顶”、“已完成”等标签的样式、按钮样式、按钮图标、侧边栏图标

同时有几个值得注意的点：

- JavaWeb 的页面跳转：

  - ` out.print(“<script>alert(\”参数缺失！请重新操作！\”);window.location=’schedule_management.jsp'</script>”);`
  - `out.print**(“<script type=’text/javascript’>**document.write**(‘<span style=\”color:red;\”>网络出问题了！</span>’);</script>”);`
  - `JSP`的重定向：`response.sendRedirect(“schedule_management.jsp”);`
  - `JSP`的转发：`getRequestDispatcher(“/xxxx.jsp”).forward(request,response);`
  - `RequestDispatcher.forward()`是服务器端跳转，跳转是同一个`request`
  - `response.sendRedirect()`是客户端跳转，是告诉浏览器重新发送请求的地址，浏览器会重新发送一个`request`

- JavaScript操作`a`标签提交表格

因为刚开始无法用 js 操作`form`提交，即：

写了`action`和`method`方法，用`getElementById`得到按钮和表格元素，然后 js 操纵表格提交，事实证明不行。

到 stackoverflow 问了，小哥跟我说`form`不能这样提交，叫我忘掉js……

![img](https://mmbiz.qlogo.cn/mmbiz_png/uF4QsM5aOUUKjCs4Rf2a5dw53hzCtsDly1y983vno9MibHB2XkPuSU5SYm5foFZK3LjKUE2Lx0QqgBVVXyUJHKw/0?wx_fmt=png)

但实际上这个问题还是可以解决的。下面是我的回答：

I hava found how to solve it. It has been proved that js can be used in submitting a form, even if tag is beyond the . By following that:

1. set the ‘s `method` and `action` attribute, it is important
2. change the ‘s `attribute` `href` from certain link to `javascript:functionname()`,like:`javascript:submitform()`
3. and write your own javascript, like: 
   `var form = document.getElementById(“note_new_form”);`
   `var btn = document.getElementById(“note_new_form_btn”);`
   `btn.onclick = function submitform(){ form.submit(); };`

It worked.



### 2016年9月2日

------

完成：

- 参数非空判断、登录状态判断
- JDBC 操作重大隐患修补（没有`close()`）

### 2016年9月28日

------

完成：

- 分页

- 置顶功能

  - 主要思路：首先在数据库中设置字段，如`f_status`，`0`为非置顶，`1`为置顶……在原表格中分两层，上层获取标志为置顶的数据，下层获取其他数据。效果如下：

![img](https://mmbiz.qlogo.cn/mmbiz_png/uF4QsM5aOUVVEGv3ibEAwry9rEJstiaUTPRjqI0LQg9Z3lTG8upZbqRC6wXn1Or1iaKcSibLlBXQeibfhBK41WrFDjQ/0?wx_fmt=png)

- 遗留问题：分页功能不完善，组件是自己写的，当第一次进入页面或者刷新的时候，并不能按指定的“每页个数”进行分页，而会列出所有的记录。（当然，点了下一页之后分页效果就可以显示了）

### 2016年9月30日

------

完成：

- `servlet`升级，至此基本完成 MVC 架构

- - 注意几个问题：不传参，则不需要把 JSP 转 Servlet
  - //一进来首先设置`request`的编码格式，否则以下方法可能出现乱码：`request.setCharacterEncoding(“utf-8”);`
  - //`session`报错，要获取`session：HttpSession session = request.getSession();`
  - //`out`报错，要获取Writer：`PrintWriter out = response.getWriter();`

- 目前src文件目录：

![img](https://mmbiz.qlogo.cn/mmbiz_png/uF4QsM5aOUVVEGv3ibEAwry9rEJstiaUTP4HCP2JWrlmFx1cHeAy7Lib2vpJtEibJvkUVB2At8YbNgpia8vblBrQMKg/0?wx_fmt=png)

### 2017年4月17日

------

- 经历半年，重新捡起这个项目，哇，简直不忍直视
- 增加过滤器 `filter`
- Mynote 注册页面：

![Mynote注册页面](http://ook21am6y.bkt.clouddn.com/Mynote%E7%99%BB%E5%BD%95.png)
  
- Mynote 登录页面：

![Mynote登录页面](http://ook21am6y.bkt.clouddn.com/Mynote%E6%B3%A8%E5%86%8C.png)