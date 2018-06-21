<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.min.css" type="text/javascript"></script>
<script src="https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.min.js" type="text/javascript"></script>
<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<script>var base = "${base}";</script>
<div id="page-wrapper" >
	<div class="row">
		<div class="col-lg-12">
			<h4 class="page-header">用户表单</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	
	<table id="user_list" width="100%" class="table table-striped table-bordered table-hover input-sm">
	<!-- <div class="form-inline" role="form"  >   
		      <div class="form-group">        
		        	工号：<input type="text" class="form-control"  id="userCode" >  
		      </div>   
		     
		     <div class="form-group">           
		       		姓名：<input type="text" class="form-control" id="userName" >  
		     </div>  		    
		     <button id="btn" class="btn btn-default">搜索</button> 
		     <button id="btnNew" class="btn btn-default">新增用户</button>  
	</div> --> 
   	
   		
		<thead>
			<tr>
				<th class="col-lg-1">工号</th>
				<th class="col-lg-1">姓名</th>
				<th class="col-lg-1">权限</th>
				<th class="col-lg-1">部门</th>
				<th class="col-lg-2">电话</th>
				<th class="col-lg-2">邮箱</th>
				<th class="col-lg-2">创建时间</th>
				<th class="col-lg-3">操作</th>
			</tr>
		</thead>
	</table>
	</div>
	<!-- /.row -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="form-horizontal" role="form" >  
	            		<input type="hidden" class="userid">
					       <div class="form-group">   
					        <label for="firstname" class="col-sm-2 control-label">工号</label>   
					        <div class="col-sm-9">  
					           <input type="text" class="form-control userCode" name="userCode"  
					              placeholder="请输入工号">  
					       </div>  
					       </div>   
					      <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">姓名</label>   
					        <div class="col-sm-9">  
					          <input type="text" class="form-control userName" name="userName" 
					              placeholder="请输入姓名">  
					         </div>  
					       </div>  
					        <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">权限</label>   
					        <div class="col-sm-9">  
					           <select class="selectpicker form-control userAuth" default="admin">
					           <option value="admin" >管理员</option>
					           <option value="common">普通用户</option>
					           </select>
					  
					         </div>  
					       </div>  
					       <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">部门</label>   
					        <div class="col-sm-9">  
					          <input type="text" class="form-control userDept" name="userDept" 
					              placeholder="请输入部门">  
					         </div>  
					       </div>
					       <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">电话</label>   
					        <div class="col-sm-9">  
					          <input type="text" class="form-control phone" name="phone" 
					              placeholder="请输入电话">  
					         </div>  
					       </div>
					       <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">邮箱</label>   
					        <div class="col-sm-9">  
					          <input type="text" class="form-control userEmail" name="userEmail" 
					              placeholder="请输入邮箱">  
					         </div>  
					       </div>
					    
					</div> 
	            	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary submit" onclick="checkAndGo();">提交</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
</div>
<!-- /#page-wrapper -->
<script src="${pageContext.request.contextPath}/js/user.js"></script>
<script type="text/javascript">

	function checkAndGo(){
		var userCode=$(".userCode").val();
		var userName=$(".userName").val();
		var userAuth=$(".userAuth").val();
		var userDept=$(".userDept").val();
		var phone=$(".phone").val();
		var userEmail=$(".userEmail").val();
	
	
		if(userCode==""){
			alert("工号不允许为空！");
			return false;
		}
		if(userName==""){
			alert("姓名不允许为空！");
			return false;
		}
		if(confirm("确认添加吗？")){
			$.ajax({
					url:base + "/user/add",
					data:{userCode:userCode,userName:userName,userDept:userDept,phone:phone,userEmail:userEmail,auth:userAuth},
					dataType: 'text',
					success:function(data) {
						if (data == "success") {
							alert("提交成功！初始密码0000！");
							$("#myModal").modal("hide");
							$("#user_list").dataTable().fnDraw(false);
						} else {
							alert("提交失败！");
						}
					}
				});
			}
			
	}
	function checkAndUp(){
		var id=$(".userid").val();
		var userCode=$(".userCode").val();
		var userName=$(".userName").val();
		var userDept=$(".userDept").val();
		var auth=$(".userAuth").val();
		var phone=$(".phone").val();
		var userEmail=$(".userEmail").val();
	
	
		if(userCode==""){
			alert("工号不允许为空！");
			return false;
		}
		if(userName==""){
			alert("姓名不允许为空！");
			return false;
		}
		if(confirm("确认修改吗？")){
			$.ajax({
					url:base + "/user/update",
					data:{id:id,userCode:userCode,userName:userName,userDept:userDept,phone:phone,userEmail:userEmail,auth:auth},
					dataType: 'text',
					success:function(data) {
						if (data == "success") {
							alert("修改成功！");
							$("#myModal").modal("hide");
							$("#user_list").dataTable().fnDraw(false);
						} else {
							alert("修改失败！");
						}
					}
				});
			}
	}
	function userUpdate(a) {
		$.ajax({
			url:base + "/user/detail",
			data:{id:a},
			success:function(data) {
				console.log(data);
				$(".userid").val(data.id);
				$(".userCode").val(data.userCode);
				$(".userCode").attr("disabled","disabled");
				$(".userName").val(data.userName);
				$(".userAuth").val(data.auth);
				$(".userDept").val(data.userDept);
				$(".phone").val(data.phone);
				$(".userEmail").val(data.userEmail);
				$("#myModalLabel").empty().text("用户详情");
				$(".submit").text("修改");
				$(".submit").attr("onclick","checkAndUp()");
			var options = {
				backdrop:"static",
				keyboard:false
			};
				$("#myModal").modal(options);
			}
		});
	}
	
	function userDelete(a) {
		var flag = confirm("确认删除?");
		if (flag) {
			$.ajax({
				url:base + "/user/delete",
				data:{id:a},
				dataType: 'text',
				success:function(data) {
					if (data == "success") {
						$("#user_list").dataTable().fnDraw(false);
					} else {
						alert("删除失败！");
					}
				}
			});
		}
	}
</script>


