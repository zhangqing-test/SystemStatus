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
			<h4 class="page-header">硬件申请表单</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	<table id="hardware_list" width="100%" class="table table-striped table-bordered table-hover input-sm">
	<!-- <div class="form-inline" role="form"  >   
		      <div class="form-group">        
		        	名称：<input type="text" class="form-control"  id="hName" >  
		      </div>   
		     <div class="form-group">           
		       	属性 ：<select class="form-control" id="hSx" >
		       			<option value="" >请选择</option>
		       			<option value="0" >虚拟机</option>
		       			<option value="1" >实体机</option>     			
					</select>  
		     </div> 
		     <div class="form-group">           
		       		IP：<input type="text" class="form-control" id="hIp" >  
		     </div>  		    
		     <button id="btn" class="btn btn-default">搜索</button>  
		     </div> 
   		</div> -->
		<thead>
			<tr>
				<th class="col-lg-2">名称</th>
				<th class="col-lg-2">服务器属性</th>
				<th class="col-lg-2">ip</th>
				<th class="col-lg-2">宿主机ip</th>
				<th class="col-lg-2">操作系统</th>
				<th class="col-lg-2">mac</th>
				<th class="col-lg-2">操作</th>
			</tr>
		</thead>
	</table>
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->
<script src="${pageContext.request.contextPath}/js/OAHardware.js"></script>
<script type="text/javascript">
	function hardwareClick(a) {
		$.ajax({
			url:base + "/OA/hardware/detail",
			data:{id:a},
			success:function(data) {
				$("#page-wrapper").replaceWith(data);
			}
		})
	}
	
	function hardwareDelete(a) {
		var flag = confirm("确认删除?");
		if (flag) {
			$.ajax({
				url:base + "/OA/hardware/delete",
				data:{id:a},
				dataType: 'text',
				success:function(data) {
					if (data == "success") {
						$.ajax({
							url:base + "/OA/hardware",
							success:function(data) {
								$("#page-wrapper").replaceWith(data);
							}
						})
					} else {
						alert("该硬件存在绑定的软件服务未删除，删除操作执行失败！");
					}
				}
			})
		}
	}
</script>


