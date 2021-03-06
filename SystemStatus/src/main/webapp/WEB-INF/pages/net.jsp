<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-switch/bootstrap-switch.min.css">
<!-- jquery -->
<script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-switch/bootstrap-switch.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/net.js"></script>	
<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<script>var base = "${base}";</script>
<title>index</title>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- navbar-header start -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/index"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<!-- /.navbar-header end -->

			<!-- navbar-collapse start -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class=""><a href="${pageContext.request.contextPath}/serverInfo/query">服务</a></li>
					<li class=""><a href="${pageContext.request.contextPath}/net">网络</a></li>
					<li class=""><a href="${pageContext.request.contextPath}/updateHistory">更新升级记录</a></li>
<%-- 					<li class=""><a href="${pageContext.request.contextPath}/deadlock">机构死锁</a></li>
 --%><!-- 					<li><a href="#">Link test</a></li> -->
<!-- 					<li class="dropdown"><a href="#" class="dropdown-toggle" -->
<!-- 						data-toggle="dropdown" role="button" aria-haspopup="true" -->
<!-- 						aria-expanded="false">Dropdown test<span class="caret"></span></a> -->
<!-- 						<ul class="dropdown-menu"> -->
<!-- 							<li><a href="#">Action test</a></li> -->
<!-- 							<li><a href="#">Another action</a></li> -->
<!-- 							<li><a href="#">Something else here</a></li> -->
<!-- 							<li role="separator" class="divider"></li> -->
<!-- 							<li><a href="#">Separated link</a></li> -->
<!-- 							<li role="separator" class="divider"></li> -->
<!-- 							<li><a href="#">One more separated link</a></li> -->
<!-- 						</ul></li> -->
				</ul>
				<!-- 根据服务器名称模糊搜索记录 -->
				<!-- <form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="输入名称关键字">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form> -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/control">后台</a></li>
					<li><a href="${pageContext.request.contextPath}/offLogin">退出</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		</nav>
		<form class="form-inline" role="form" method="post" action="${pageContext.request.contextPath}/net">   
		      <div class="form-group">        
		                        源IP：<input type="text" class="form-control"  name="ipName" placeholder="请输入名称" value="${form.ipName }">  
		      </div>   
		     <div class="form-group">           
		       	状态 ：<select class="form-control" title="大魔王" name="netStatus" >
		       			<option value="0" >请选择</option>
		       			<option value="1" ${form.netStatus==1?'selected':'' }>ping</option>
		       			<option value="2" ${form.netStatus==2?'selected':'' }>port</option>
		       			<option value="3" ${form.netStatus==3?'selected':'' }>miss</option>
					</select>  
		     </div>  
		    <div class="form-group">           
		       	目标IP：<input type="text" class="form-control" name="targetName"  placeholder="请输入名称" value="${form.targetName }">  
		     </div>
		     <button type="submit" class="btn btn-default">搜索</button>  
		  </form>
		<div id="list">
			<table class="table table-condensed	table-striped input-sm">
				<caption>
					数据时间:<span id="time">
					<c:if test="${!empty time}">${time }</c:if>
					</span>
				</caption>
				<thead>
					<tr>
						<th class="col-xs-4 col-sm-4 col-lg-4">源ip</th>
						<th class="col-xs-2 col-sm-2 col-lg-2">状态</th>
						<th class="col-xs-4 col-sm-4 col-lg-4">目标ip</th>
						<th class="col-xs-2 col-sm-2 col-lg-2">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="data" items="${list }">
					<tr>
						<td class="col-xs-4 col-sm-4 col-lg-4">
							<strong>${data.ipName }</strong>
						<c:if test="${((data.one_pingStatus == false)&&(data.one_avg == 1000)) || ((data.two_pingStatus == false)&&(data.two_avg == 400))}">
							<span class="glyphicon glyphicon-remove-sign" style="color: rgb(178, 34, 34);"></span>
						</c:if>
						</td>
						<td class="col-xs-2 col-sm-2 col-lg-2">
						<c:if test="${data.one_pingStatus == true}">
							<c:if test="${data.one_pingMiss > 0 }">
							<span class="glyphicon glyphicon-arrow-right" style="color: rgb(255, 215, 0);"></span>
							</c:if>
							<c:if test="${data.one_pingMiss == 0 }">
							<span class="glyphicon glyphicon-arrow-right" style="color: rgb(0, 255, 0);"></span>
							</c:if>
						</c:if>
						<c:if test="${data.one_pingStatus == false}">
							<span class="glyphicon glyphicon-remove" style="color: rgb(178, 34, 34);"></span>
						</c:if>
						<c:if test="${data.two_pingStatus == true}">
							<c:if test="${data.two_pingMiss > 0 }">
							<span class="glyphicon glyphicon-arrow-left" style="color: rgb(255, 215, 0);"></span>
							</c:if>
							<c:if test="${data.two_pingMiss == 0 }">
							<span class="glyphicon glyphicon-arrow-left" style="color: rgb(0, 255, 0);"></span>
							</c:if>
						</c:if>
						<c:if test="${data.two_pingStatus == false}">
							<span class="glyphicon glyphicon-remove" style="color: rgb(178, 34, 34);"></span>
						</c:if>
						</td>
						<td class="col-xs-4 col-sm-4 col-lg-4">
							<strong>${data.targetIpName }</strong>
						<c:if test="${((data.one_pingStatus == false)&&(data.one_avg == 400)) || ((data.two_pingStatus == false)&&(data.two_avg == 1000))}">
							<span class="glyphicon glyphicon-remove-sign" style="color: rgb(178, 34, 34);"></span>
						</c:if>							
<%-- 							(${data.pingMiss }%) --%>
						</td>
						<td class="col-xs-2 col-sm-2 col-lg-2">
						<%-- <c:forEach var="map" items="${data.map }">
						<c:if test="${map.value == true}">
							<p class="text-left"><span class="glyphicon glyphicon-ok" style="color: rgb(106, 212, 64);"></span><strong>${map.key }</strong></p>
						</c:if>
						<c:if test="${map.value == false}">
							<p class="text-left"><span class="glyphicon glyphicon-remove" style="color: rgb(212, 106, 64);"></span><strong>${map.key }</strong></p>
						</c:if>
						</c:forEach> --%>
						<button type="button" class="detail btn btn-xs btn-primary" 
							id="${data.id }"
							ip="${data.ip }"
							targetIp="${data.targetIp }"
							ipName="${data.ipName }"
							targetIpName="${data.targetIpName }"
							types="${data.type }"
							message="${data.message }"
							one_pingStatus="${data.one_pingStatus }"
							one_portStatus="${data.one_portStatus }"
							one_pingMiss="${data.one_pingMiss }"
							one_min="${data.one_min }"
							one_max="${data.one_max }"
							one_avg="${data.one_avg }"
							two_pingStatus="${data.two_pingStatus }"
							two_portStatus="${data.two_portStatus }"
							two_pingMiss="${data.two_pingMiss }"
							two_min="${data.two_min }"
							two_max="${data.two_max }"
							two_avg="${data.two_avg }"
							>详情</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
	            </div>
	            <div class="modal-body">在这里添加一些文本</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	</div>	
</body>
</html>