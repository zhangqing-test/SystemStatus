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
			<h4 class="page-header">服务器账号</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	
	<table id="account_list" width="100%" class="table table-striped table-bordered table-hover input-sm">
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
				<th class="col-lg-1">服务器名称</th>
				<th class="col-lg-1">软件服务名称</th>
				<th class="col-lg-1">硬件账号名</th>
				<th class="col-lg-1">软件账号名</th>
				<th class="col-lg-2">使用期限</th>
				<th class="col-lg-2">新增账号时间</th>
			</tr>
		</thead>
	</table>
	</div>
	<!-- /.row -->
	<!-- 模态框（Modal） -->
	
</div>
<!-- /#page-wrapper -->
<script src="${pageContext.request.contextPath}/js/account.js"></script>


