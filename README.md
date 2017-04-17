# MyNote个人笔记本

一个用 Java Web 技术搭建的个人笔记本工具站点，包含日程管理、笔记管理、财务管理的 CRUD 功能，支持简单的财务统计。

----------


### 涉及技术与简要说明：

 1. JavaWeb MVC，src 部分模型如下：
    src
    |___DAO
    |___DTO
    |___service
    |___servlet
    |___tools
    |___vo
 2. 少量的CSS3，少量的jQuery
 3. 项目初构建时采用 Bootstrap 2.0.2，目前仅 login.html 使用 Bootstrap 3 重建

----------

## 接口：以财务管理为例

两个封装，三个逻辑

- ### 两个封装：
  - 封装类（数据端） `DTO`   ----   `FinancialDTO.java`
  - 封装类（业务端）`VO`
    - `Financial_formVO()`
    - `Financial_statics_VO()`
    - `Financial_updateVO()`
    - `Financial_VO()`

- ### 三个逻辑：
  - 引流： `servlet`   ----  `FinancialServlet.java`
    - **`public void doGet()`** 常规方法
    - **`public void doPost()`** 常规方法
    - **`private void newFinancialServlet()`** 新增
    - **`private void updateFinancialServlet()`** 更新
    - **`private void deleteFinancialServlet()`** 删除
  - 业务 `service`   ----   `Financial_BO.java`
    - **`public List<Financial_formVO> financial_form()`** 财务管理列表展示
    - **`public int newFinancial(Financial_VO new_financial)`** 新增财务项
    - **`public Financial_updateVO update_show(String f_id)`** 修改财务项 - 根据id展示旧数据
    - **`public int update_update(String f_id,Financial_updateVO updatevo)`** 修改 - 根据id修改数据
    - **`public int delete_financial(String f_id)`** 删除 - 根据id删除财务项
    - **`public Financial_statics_VO financial_statics(int ask)`** 财务统计 - 总数 - 返回sum们的封装类
    - **`public List<Financial_formVO> financial_statics_form(int ask)`** 财务统计 - 列表 - 返回列表
  - 数据库操作 `DAO`   ----    `FinancialDAO.java`
    - **`public List<FinancialDTO> findAllFinancial()`** 查找 - 所有财务
    - **`public FinancialDTO findByFinancialID(String f_id)`** 查找 - 根据id查找
    - **`public List<FinancialDTO> findByFinancialType(int f_type)`** 查找 - 根据`type`查找多条数据
    - **`public long findSumByFinancialType(int f_type)`** 查找 - 根据`f_type`返回`f_amount`求和
    - **`public int createFinancial(FinancialDTO financial_dto_receive)`** 新建 - 通过封装类
    - **`public int updateFinancial(FinancialDTO financial_dto_receive)`** 更新 - 通过封装类
    - **`public int deleteByFinancialID(String f_id)`** 删除 - 通过id删除单条

---

## 运行流程（简化）

- SERVLET: **`public void doPost()`** 常规方法
  - SERVLET: **`private void updateFinancialServlet()`** 更新
    - SERVLET: 调用 VO 封装: `Financial_updateVO updatevo = new Financial_updateVO()`
    - SERVLET: 调用 BO 方法: **`public int update_update(String f_id,Financial_updateVO updatevo)`** 修改 - 根据id修改数据
      - BO: 调用 DTO 封装: `FinancialDTO financial_dto_send = new FinancialDTO()`
      - BO: 调用 DAO: `public int updateFinancial(FinancialDTO financial_dto_receive)`

---

## 运行流程（详细）

- SERVLET: **`public void doPost()`** 常规方法

  - SERVLET: **`private void updateFinancialServlet()`** 更新
    - SERVLET: 获取 `session`
    - SERVLET: 判断 `uid`
    - SERVLET: 获取用户要修改的参数
    - SERVLET: 如果参数不为空
      - SERVLET: 调用 VO 封装: `Financial_updateVO updatevo = new Financial_updateVO()`
      - SERVLET: 调用 BO 相应方法，传入 VO: **`public int update_update(String f_id,Financial_updateVO updatevo)`** 修改 - 根据id修改数据
        - BO: 获取传入的 VO 数据
        - BO: 调用 DTO 重新封装: `FinancialDTO financial_dto_send = new FinancialDTO()`
        - BO: 调用 DAO 传入 DTO: `public int updateFinancial(FinancialDTO financial_dto_receive)`
          - DAO: 连接数据库
        - BO: 更新返回值
      - SERVLET: 判断返回值


----------


### 所用插件：

- `backstretch`一个`jQuery`的页面背景切换工具
   `<script src="http://cdn.bootcss.com/jquery-backstretch/2.0.4/jquery.backstretch.min.js">`


----------


### 待修缮：

- Ajax
- 注册功能
- 页面重建