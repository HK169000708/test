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
<script type="text/javascript">
	/*修改*/
	function update(studentid) {
		location.href = "${pageContext.request.contextPath}/student/studentedit?studentid="
				+ studentid;
		
	}
</script>
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
					<div class="layui-card-body ">
						<form class="layui-form layui-col-space5"
							action="${pageContext.request.contextPath}/student/mselect">
							<div class="layui-inline layui-show-xs-block">
								<input type="text" name="name" placeholder="请输入学生名"
									autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline layui-show-xs-block">
								<button type="submit" id="mlike" class="layui-btn" lay-submit=""
									lay-filter="sreach">
									<i class="layui-icon">&#xe615;</i>
								</button>
							</div>
						</form>
					</div>


					<div class="layui-card-header">
						<button class="layui-btn layui-btn-danger" onclick="delAll()">
							<i class="layui-icon"></i>批量删除
						</button>
						<button class="layui-btn"
							onclick="xadmin.open('添加用户','${pageContext.request.contextPath}/student/studentadd',600,400)">
							<i class="layui-icon"></i>添加
						</button>
					</div>
					<div class="layui-card-body layui-table-body layui-table-main">
						<table class="layui-table" lay-filter="mylist" lay-size="lg">
							<thead>
								<tr>
									<th lay-data="{type:'checkbox',fixed:'left'}"><input
										type="checkbox" lay-filter="checkall" name="checkall"
										lay-skin="primary"></th>
									<th lay-data="{field:'number', align:'center',width:120}">学号</th>
									<th lay-data="{field:'name', align:'center',width:90}">姓名</th>
									<th lay-data="{field:'time',align:'center', Width:40}">性别</th>
									<th lay-data="{field:'tel',align:'center', Width:120}">电话号码</th>
									<th lay-data="{field:'class',align:'center', Width:60}">班级</th>

									<th lay-data="{field:'birthday',align:'center', Width:120}">生日</th>
									<th lay-data="{field:'age',align:'center', Width:60}">年龄</th>
									<th
										lay-data="{field:'option',align:'center',width:280,fixed: 'right'}">操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${students}" var="item">
									<tr>

										<td><input type="checkbox" name="" lay-skin="primary">
										</td>
										<td>${item.studentnumber}</td>
										<td>${item.name}</td>
										<td>${item.sex}</td>
										<td>${item.tel}</td>
										<td>${item.cla.classname}</td>
										<td id="bir${item.studentid}" name="bird"><fmt:formatDate
												value="${item.birthday }" pattern="yyyy-MM-dd" />
										</td>
										<td id="b${item.studentid}" name="nl"></td>

										<td class="td-manage">
											<button class="layui-btn layui-btn "
												onclick="xadmin.open('编辑用户','${pageContext.request.contextPath}/student/studentedit?studentid=${item.studentid}',600,400 )"
												href="javascript:;">
												<i class="layui-icon">&#xe642;</i>修改
											</button>
											<button class="layui-btn-warm layui-btn layui-btn-xs"  onclick="resetpwd('${item.studentid}')" title="重置密码" href="javascript:;">       
                                        <i class="layui-icon">&#xe631;</i>重置密码
                                      </button>
											<button class="layui-btn-danger layui-btn"
												onclick="member_del(this,'${item.studentid}')"
												href="javascript:;">
												<i class="layui-icon">&#xe640;</i>删除
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
	$(function() {
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
	//生日转换
  	
    	//监听头工具栏事件
		/* table.on('toolbar(mylist)', function(obj){
			var checkStatus = table.checkStatus(obj.config.id)
			,data = checkStatus.data; //获取选中的数据 
			switch(obj.event){ 
			 case 'add': 
				
			break;
			case 'update':
				
			break;
			case 'delete':
				
			break; 
			};
		}); */
   
    //生日转换

 
	function dateFormat(date, format) {
		date = new Date(date);
		date.setHours(date.getHours() - 14);
		var o = {
			'M+' : date.getMonth() + 1, //month
			'd+' : date.getDate(), //day
			'H+' : date.getHours(), //hour
			'm+' : date.getMinutes(), //minute
			's+' : date.getSeconds(), //second
			'q+' : Math.floor((date.getMonth() + 3) / 3),
			'S' : date.getMilliseconds()
		//millisecond
		};

		if (/(y+)/.test(format))
			format = format.replace(RegExp.$1, (date.getFullYear() + '')
					.substr(4 - RegExp.$1.length));

		for ( var k in o)
			if (new RegExp('(' + k + ')').test(format))
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ('00' + o[k]).substr(('' + o[k]).length));
		return format;
	}

	//计算年龄函数
	function jsageall() {
		var ids = [];
		// 获取选中的id 
		$("td[name='bird']").each(function(index, el) {
			var stunumber = $(this).html();
			ids.push(stunumber)
		});
		var aa = ids.toString();

		var aa1 = new Array();
		aa1 = aa.split(",");
		for (i = 0; i < aa1.length; i++) {
			var s_time = dateFormat(aa1[i], 'yyyy-MM-dd');
			var csrq = s_time;
			var age = '';
			var d = new Date();
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			var day = d.getDate();
			if (month < 10) {
				month = '0' + month;
			}
			if (day < 10) {
				day = '0' + day;
			}
			var now = year + '-' + month + '-' + day;
			if (now.substring(0, 4) >= csrq.substring(0, 4)
					&& now.substring(5, 7) >= csrq.substring(5, 7)
					&& now.substring(8, 10) >= csrq.substring(8, 10)) {
				age = year - parseInt(csrq.substring(0, 4));
			} else {
				age = year - parseInt(csrq.substring(0, 4)) - 1;
			}

			var s = i + 1;			
			$("#b" + s).html(age);
		}


      };
      /*用户-删除*/
      function member_del(obj,studentid){
    	
               layer.confirm('确认要删除吗？',function(index){
    	        var url="${pageContext.request.contextPath}/student/studentdel?studentid="+studentid;
        	   	$.post(url,function(data){
    	   		if(data.flag==1){
    	   			layer.closeAll();
    	   			location.reload();
    	   			
    				}
   	   	})
    	   	
          });
      }



	layui.use([ 'laydate', 'form' ], function() {
		var laydate = layui.laydate;
		var form = layui.form;
		// 监听全选
		form.on('checkbox(checkall)', function(data) {


			if (data.elem.checked) {
				$('tbody input').prop('checked', true);
			} else {
				$('tbody input').prop('checked', false);
			}
			form.render('checkbox');
		});

		//执行一个laydate实例
		laydate.render({
			elem : '#start' //指定元素
		});

		//执行一个laydate实例
		laydate.render({
			elem : '#end' //指定元素
		});
	});
	/*用户-删除*/
	function member_del(obj, studentid) {

		layer
				.confirm(
						'确认要删除吗？',
						function(index) {
							var url = "${pageContext.request.contextPath}/student/studentdel?studentid="
									+ studentid;
							$.post(url, function(data) {
								if (data.flag == 1) {
									layer.closeAll();
									location.reload();

								}
							})

						});
	}
	
</script>
<script>
//学生批量删除
	function delAll(argument) {
		var ids = [];
		// 获取选中的id 
		
		$("tbody input").each(function(index, el) {
			var stunumber =$(this).parent().next();
			
			if($(this).prop('checked')) {
				ids.push(stunumber.html())
			}
		});		
		var aa =ids.toString();
		alert(aa);
		layer.confirm('确认要删除吗？' + aa, function(index) {
			//捉到所有被选中的，发异步进行删除
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/student/studentall",
				data : {
					"aa" : aa
				},
				success : function(data) {
					if (data.flag == 1) {
						layer.alert("删除成功", {
							icon : 1
						}, function() {
							xadmin.father_reload();
						});
					} else {
						alert("删除失败")
					}
				}
			})
		});
	}

	
   //模糊查询
      function selectm(){
     	 var name = $("#mlike").val();
     	 alert(name);
     	 $.ajax({
     		 type:"post",
     		 url:"${pageContext.request.contextPath}/student/mselect",
     		 data:{"name":name},
     		 success:function(data){
     			 if(data.flag == 1){
          			layer.alert("查询成功", {
                          icon: 1
                      },function(){
                      	xadmin.father_reload();
                      });
          		}else{
          			layer.alert("查询失败", {
                         icon: 1
                     },function(){
                     	xadmin.father_reload();
                     });
          		}
     		 }
     	 })
      }
      //重置密码
         function resetpwd(teacherid){
    	  var result=confirm("是否重置?");
    	  if(result){
    		  var url="${pageContext.request.contextPath}/student/resetpwd";
    		  var param={teacherid:teacherid};
    		  $.post(url,param,function(data){
    			  alert(data.content);
    			  if(data.flag==1){
    				  location.reload();
    			  }
    		  });  
    	  }
    	}
      
    </script>
<script type="text/javascript">
//调用年龄函数
window.onload = function() {
	jsageall();
}
</script>

</html>