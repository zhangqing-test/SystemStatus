<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<script>var base = "${base}";</script>
<div id="page-wrapper" >
	<div class="row">
		<div class="col-lg-12">
			<h4 class="page-header">密码修改</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	
	</div>
	<!-- /.row -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">密码修改</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="form-horizontal" role="form" >  
	            		<input type="hidden" class="userid">
	            		<input type="hidden" class="userPwd">
					       <div class="form-group">   
					        <label for="firstname" class="col-sm-2 control-label">工号</label>   
					        <div class="col-sm-9">  
					           <input type="text" class="form-control userCode" name="userCode"  
					              placeholder="请输入工号" disabled="disabled">  
					       </div>  
					       </div>   
					      <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">姓名</label>   
					        <div class="col-sm-9">  
					          <input type="text" class="form-control userName" name="userName" 
					              placeholder="请输入姓名" disabled="disabled">  
					         </div>  
					       </div>  
					       <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">原密码</label>   
					        <div class="col-sm-9">  
					          <input type="password" class="form-control userOld" name="userOld" 
					              placeholder="请输入原密码">  
					         </div>  
					       </div>
					       <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">新密码</label>   
					        <div class="col-sm-9">  
					          <input type="password" class="form-control userNew" name="userNew" 
					              placeholder="请输入新密码">  
					         </div>  
					       </div>
					       <div class="form-group">   
					        <label for="lastname" class="col-sm-2 control-label">确认密码</label>   
					        <div class="col-sm-9">  
					          <input type="password" class="form-control userConfirm" name="userConfirm" 
					              placeholder="请确认密码">  
					         </div>  
					       </div>
					    
					</div> 
	            	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary submit" onclick="checkAndUp();">修改</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
</div>
<!-- /#page-wrapper -->
<script src="${pageContext.request.contextPath}/js/user.js"></script>
<script type="text/javascript">

	$(function () { 
		userUpdate();		
	});
	function checkAndUp(){
		var id=$(".userid").val();
		var userPwd=$(".userPwd").val();
		var userOld=$(".userOld").val();
		var userNew=$(".userNew").val();
		var userConfirm=$(".userConfirm").val();
	
		if(userOld==""){
			alert("原密码不允许为空！");
			return false;
		}
		if(userNew==""){
			alert("新密码不允许为空！");
			return false;
		}
		if(userConfirm==""){
			alert("确认密码不允许为空！");
			return false;
		}
		if(userPwd!=userOld){
			alert("原密码不正确！");
			return false;
		}
		if(userNew!=userConfirm){
			alert("新密码不一致！");
			return false;
		}
		if(confirm("确认修改吗？")){
			$.ajax({
					url:base + "/user/update",
					data:{id:id,userPwd:userConfirm},
					dataType: 'text',
					success:function(data) {
						if (data == "success") {
							alert("修改成功！");
							$("#myModal").modal("hide");
						} else {
							alert("修改失败！");
						}
					}
				});
			}
	}
	function userUpdate() {
		$.ajax({
			url:base + "/user/sessiondetail",
			data:{},
			success:function(data) {
				$(".userid").val(data.id);
				$(".userCode").val(data.userCode);
				$(".userName").val(data.userName);
				$(".userPwd").val(data.userPwd);
			 var options = {
				backdrop:"static",
				keyboard:false
			};
				$("#myModal").modal(options); 
			}
		});
	}
	

</script>


