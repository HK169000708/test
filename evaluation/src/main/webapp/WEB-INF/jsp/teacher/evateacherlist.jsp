<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
<meta charset="UTF-8">
<title>欢迎页面-X-admin2.2</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../css/font.css">
<link rel="stylesheet" href="../css/xadmin.css">
<script src="../lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/xadmin.js"></script>
<script type="text/javascript" src="../js/jquery.min.js" charset="utf-8"></script>

<style type="text/css">
.layui-table td, .layui-table th {
	min-width: 10px;
}
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">演示</a>
			<a> <cite>导航元素</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			onclick="location.reload()" title="刷新"> <i
			class="layui-icon layui-icon-refresh" style="line-height: 30px"></i></a>
	</div>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
									<div class="layui-card-body layui-table-body layui-table-main">
						<table class="layui-table" lay-filter="mylist" lay-size="lg">
							<thead>
								<tr>
									
									<th lay-data="{field:'21', align:'center',width:90}">教师编号</th>
									<th lay-data="{field:'name', align:'center',width:90}">姓名</th>
									
									<th
										lay-data="{field:'option',align:'center',width:180,fixed: 'right'}">操作
									</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${evatea}" var="item">
									<tr>
										<td>${item.teachernumber}</td>
										<td>${item.name}</td>
									
								    	<td class="td-manage">
											<button class="layui-btn layui-btn "
												onclick="xadmin.open('评价','${pageContext.request.contextPath}/teacher/questions?teacherid=${teacherid}&teachingid=${item.teaching.teachingid}',1400,700 )"
												href="javascript:;">
												<i class="layui-icon">&#xe642;</i>评价
											</button>
										
										</td>
									</tr>

								</c:forEach> 
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/* $(function() {
		layui.use('table', function() {
			var table = layui.table;
			//转换静态表格
			table.init('mylist', {
				height : '460' //高度最大化减去差值,也可以自己设置高度值：如 height:300
				,
				count : 50 //数据总数 服务端获得
				,
				limit : 7 //每页显示条数 注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致    	           
				,
				page : true //开启分页    	           	          
				,
				limits : [ 7, 10, 20, 30, 40, 50 ]
			//分页显示每页条目下拉选择    	          
			});
		});
	});
	 */
  	
   
   
	
</script>

</html>