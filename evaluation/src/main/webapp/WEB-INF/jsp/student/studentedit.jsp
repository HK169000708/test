<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   	
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
<meta charset="UTF-8">
<title>学生信息修改</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="stylesheet" href="../css/font.css">
<link rel="stylesheet" href="../css/xadmin.css">
<script type="text/javascript" src="../lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/xadmin.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/jquery-3.4.0.min.js"></script>


</head>

   
    <body>
       <div class="layui-fluid">
		<div class="layui-row">
			<form class="layui-form" id="updatestu">
				<input type="hidden" id="studentid" name="studentid"
					value="${stu.studentid }">
				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label"> <span
						class="x-red">*</span>学号
					</label>
					<div class="layui-input-inline">
						<input type="text" id="" studentnumber"" name="studentnumber"
							required="" lay-verify="stunumber" autocomplete="off"
							class="layui-input" value="${stu.studentnumber }">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>学生唯一标识
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_username" class="layui-form-label"> <span
						class="x-red">*</span>姓名
					</label>
					<div class="layui-input-inline">
						<input type="text" id="name" name="name" required=""
							lay-verify="nikename" autocomplete="off" class="layui-input"
							value="${stu.name }">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_pass" class="layui-form-label"> <span
						class="x-red">*</span>班级
					</label>
					<div class="layui-input-inline">
					
					<select id="classselect" name="classid">
					 <c:forEach items="${clas}" var="item">
				         <option value="${item.classid}">${item.classname}</option>
				     </c:forEach>	 			
					</select>
				    </div>
</div>
					<div class="layui-form-item">
						<label for="L_repass" class="layui-form-label"></label>
						<button class="layui-btn" lay-filter="update" lay-submit="">修改</button>
					</div>
			</form>
			<div id="classname" style="display:none">${stu.cla.classname}</div>
			
		</div>
	</div>
       
	
	<script>
		layui.use(
					[ 'form', 'layer' ],
					function() {
							$ = layui.jquery;
							var form = layui.form, layer = layui.layer;
							//监听提交
							form.on(
								'submit(update)',
									function(data) {
												$.ajax({
															type : "post",
															url  : "${pageContext.request.contextPath}/student/updatestucontroll",
															data : $('#updatestu').serialize(),
															success : function(data) {
																if (data.flag == 1) {
																	layer
																			.alert(
																					"修改成功",
																					{
																						icon : 1
																					},
																					function() {
																						//关闭当前frame
																						xadmin
																								.close();

																						// 可以对父窗口进行刷新 
																						xadmin
																								.father_reload();
																					});
																} else {
																	layer
																			.alert(
																					"修改失败",
																					{
																						icon : 2
																					},
																					function() {
																						//关闭当前frame
																						xadmin
																								.close();

																						// 可以对父窗口进行刷新 
																						xadmin
																								.father_reload();
																					});
																}
															}
														})

												return false;
											});

						});
	</script>
	<script>
	//获取学生班级，设置为select默认选中值
 (function(){	
	 var classvalue=$("#classname").text();
	 var aa=$("#classselect option");	
	 for(i=0;i<aa.length;i++){		 
		if(aa[i].text==classvalue){		
		aa[i].selected=true;			
		}			
	 }
 })();


</script>
</body>

           
  
    


</html>