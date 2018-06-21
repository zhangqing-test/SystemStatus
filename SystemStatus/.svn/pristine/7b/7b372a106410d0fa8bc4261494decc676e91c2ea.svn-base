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
			<h4 class="page-header">网络申请表单</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	<table id="net_list" width="100%" class="table table-striped table-bordered table-hover input-sm">
	<!-- <div class="form-inline" role="form"  >   
		      <div class="form-group">        
		        	主ip名称：<input type="text" class="form-control"  id="netName" >  
		      </div> 
		      <div class="form-group">        
		        	主ip地址：<input type="text" class="form-control"  id="netIp" >  
		      </div>    
		     <div class="form-group">           
		       	网络策略： <select class="form-control" id="netType" >
		       			<option value="" >请选择</option>
		       			<option value="1" >双向</option>
		       			<option value="2" >主到副</option>
		       			<option value="3" >副到主</option>     			
					</select>  
		     </div>  		    
		     <button id="btn" class="btn btn-default">搜索</button>  
		     </div> 
   		</div> -->
		<thead>
			<tr>
				<th class="col-lg-3">线路名称</th>
				<th class="col-lg-2">主ip名称</th>
				<th class="col-lg-1">主ip地址</th>
				<th class="col-lg-2">副ip名称</th>
				<th class="col-lg-1">副ip地址</th>
				<th class="col-lg-1">网络策略</th>
				<th class="col-lg-2">操作</th>
			</tr>
		</thead>
	</table>
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->
<script src="${pageContext.request.contextPath}/js/OANet.js"></script>
<script type="text/javascript">
	function netClick(a) {
		$.ajax({
			url:base + "/OA/net/detail",
			data:{id:a},
			success:function(data) {
				$("#page-wrapper").replaceWith(data);
			}
		})
	}
	
	function netDelete(a) {
		var flag = confirm("确认删除?");
		if (flag) {
			$.ajax({
				url:base + "/OA/net/delete",
				data:{id:a},
				dataType: 'text',
				success:function(data) {
					if (data == "success") {
						$.ajax({
							url:base + "/OA/net",
							data:{id:a},
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


