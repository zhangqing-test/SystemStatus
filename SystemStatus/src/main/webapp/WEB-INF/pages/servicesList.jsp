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
			<h4 class="page-header">服务申请表单</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	<table id="services_list" width="100%" class="table table-striped table-bordered table-hover input-sm">
	<!-- <div class="form-inline" role="form"  >   
		      <div class="form-group">        
		        	服务名称：<input type="text" class="form-control"  id="fwName" >  
		      </div>   
		     <div class="form-group">           
		       	是否监控： <select class="form-control" id="sfjk" >
		       			<option value="" >请选择</option>
		       			<option value="true" >是</option>
		       			<option value="false" >否</option>     			
					</select>  
		     </div> 
		     <div class="form-group">           
		       	服务类型 ：<select class="form-control" id="fwType" >
		       			<option value="" >请选择</option>
		       			<option value="2" >数据库</option>
		       			<option value="1" >应用服务</option>     			
					</select>  
		     </div>  		    
		     <button id="btn" class="btn btn-default">搜索</button>  
		     </div> 
   		</div> -->
		<thead>
			<tr>
			<th  class="col-lg-2">IP</th>
				<th class="col-lg-2">服务名称</th>
				<th class="col-lg-2">硬件服务器名称</th>
				<th class="col-lg-2">端口</th>
				<th class="col-lg-2">是否监控</th>
				<th class="col-lg-2">服务类型</th>
				<th class="col-lg-2">操作</th>
			</tr>
		</thead>
	</table>
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->
<script src="${pageContext.request.contextPath}/js/OAServices.js"></script>
<script type="text/javascript">
	function servicesClick(a) {
		$.ajax({
			url:base + "/OA/services/detail",
			data:{id:a},
			success:function(data) {
				$("#page-wrapper").replaceWith(data);
			}
		})
	}
	
	function servicesDelete(a) {
		var flag = confirm("确认删除?");
		if (flag) {
			$.ajax({
				url:base + "/OA/services/delete",
				data:{id:a},
				dataType: 'text',
				success:function(data) {
					alert(data)
					if (data == "success") {
						$.ajax({
							url:base + "/OA/services",
							success:function(data) {
								$("#page-wrapper").replaceWith(data);
							}
						})
					} else {
						alert("删除失败");
					}
				}
			})
		}
	}
</script>


