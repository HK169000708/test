<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="x-admin-sm">
    <head>
       <meta charset="UTF-8">
<title>教师得分</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="../X-admin/css/font.css">
        <link rel="stylesheet" href="../X-admin/css/xadmin.css">
        <script src="../X-admin/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="../X-admin/js/xadmin.js"></script>
       
</head>
<body>
	        <div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">得分管理</a>
            <a>
              <cite>得分列表</cite></a>
          </span>
          <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                         <div class="layui-card-body ">
                           <form class="layui-form layui-col-space5" > 
                               
                            </form>
                        </div> 
                       
                        <div class="layui-card-body layui-table-body layui-table-main">
                            <table class="layui-table layui-form">
                                <thead>
                              
                                </thead>
                                <tbody>       
                                
                                  <tr style="text-align: center; background-color: red;"><td colspan="12">得分</td></tr>
                                   <tr>                   
                                    <th>概念讲解</th>  
                                    <th>重点难点</th> 
                                    <th>逻辑条理</th>
                                    <th>趣味生动</th>
                                    <th>板书</th>  
                                    <th>辅导</th> 
                                    <th>作业批改</th> 
                                    <th>能力培养</th>  
                                    <th>教书育人</th>
                                    <th>为人师表</th>
                                    <th>总得分</th>   
                                    </tr>
                                  <tr>
                                    <td>${Tv[0] } </td>  
                                    <td>${Tv[1] }</td>
                                    <td>${Tv[2] } </td>
                                    <td>${Tv[3] }</td>
                                    <td>${Tv[4] }</td>
                                    <td>${Tv[5] } </td>
                                    <td>${Tv[6] } </td>
                                    <td>${Tv[7] }</td>
                                    <td>${Tv[8] } </td>
                                    <td>${Tv[9] } </td>
                                    <td>${Tv[10] } </td>                       
                                      
                                </tbody>
                            </table>
                        </div>
                        
                   <!--     <div class="layui-card-body ">
                            <div class="page">
                                <div>
                                  <a class="prev" href="">&lt;&lt;</a>
                                  <a class="num" href="">1</a>
                                  <span class="current">2</span>
                                  <a class="num" href="">3</a>
                                  <a class="num" href="">489</a>
                                  <a class="next" href="">&gt;&gt;</a>
                                </div>
                            </div>
                        </div> -->  
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
    
   function sum(){
// 	 var sum=  ${item.question0}+${item.question1}+${item.question2}+${item.question3}+${item.question4}+${item.question5}
// 	 +${item.question6}+${item.question7}+${item.question8}+${item.question9}
	 alert("112");
    }
      layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;


        // 监听全选
        form.on('checkbox(checkall)', function(data){

          if(data.elem.checked){
            $('tbody input').prop('checked',true);
          }else{
            $('tbody input').prop('checked',false);
          }
          form.render('checkbox');
        }); 
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });


      });

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*查看得分*/
      function member_select(obj,id){  
          var url="${pageContext.request.contextPath}/teacher/score";
          var classid=id;
          var param={classid:classid};
          $.post(url,param,function(data){
      		var result=confirm(classid);
      		if(result){
      			//删除用户
      			alert(data.msg);
      			location.reload();
      		}
      		
      	});
      }
      
   // 用户-修改
//       function xadmin.open(title,url,id,w,h) {
//           x_admin_show(title,url+"?majorid="+id,w,h); 
         
//       }


  /*批量删除*/
      function delAll (argument) {
        var ids = "";

        // 获取选中的id 
        $('tbody input').each(function(index, el) {
        	var scoreid = $(this).parent().next();
            if($(this).prop('checked')){
               //ids.push($(this).val())
            ids+=scoreid.html()+",";
            }
        });
        ids = ids.substring(0,ids.length-1);
  		
  
        layer.confirm('确认要删除吗？'+ids.toString(),function(index){
            //捉到所有被选中的，发异步进行删除
            layer.closeAll();      
            $.ajax({
          	  type:"post",
            	  url:"${pageContext.request.contextPath}/score/delallscore", 
            	  data:{"ids":ids},
            	  success:function(data){
          		if(data.flag == 1){
          			layer.alert("删除成功", {
                          icon: 1
                      },function(){
                      	xadmin.father_reload();
                      });
          		}
          	}            	  
            })
        });
      }
 
 
      //模糊查询
      function selectm(){
     	 var id = $("#mlike").val();
     	 alert(name);
     	 $.ajax({
     		 type:"post",
     		 url:"${pageContext.request.contextPath}/score/mselect",
     		 data:{"teachername":teachername},
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
    </script>
</html>