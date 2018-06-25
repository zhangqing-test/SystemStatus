<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<script>var base = "${base}";</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<!-- MetisMenu CSS -->
<link href="${pageContext.request.contextPath}/css/sb-admin/metisMenu/metisMenu.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/sb-admin/dist/sb-admin-2.min.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/css/sb-admin/font-awesome/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- jquery -->
<script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/js/sb-admin/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/js/sb-admin/dist/sb-admin-2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>

<!-- DataTables JavaScript -->
<link href="${base}/css/datatables/dataTables.bootstrap.min.css"
	type="text/css" rel="stylesheet">
<script src="${base}/js/datatables/jquery.dataTables.min.js	"
	type="text/javascript" charset="utf8"></script>
<script src="${base}/js/datatables/dataTables.bootstrap.min.js"
	type="text/javascript" charset="utf8"></script>
<%-- <script src="${base}/js/datatables/dataTables.responsive.js" --%>
<!-- 	type="text/javascript" charset="utf8"></script> -->
<title>index</title>
</head>
<body style="position: relative;">
<div id="wrapper">
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	        </button>
	        <a class="navbar-brand" href="${pageContext.request.contextPath}/control">监控运维管理后台</a>
	    </div><!-- /.navbar-header -->
		
	    <ul class="nav navbar-top-links navbar-right">
	       <!--  <li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	                <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
	            </a>
	            <ul class="dropdown-menu dropdown-messages">
	                <li>
	                    <a href="#">
	                        <div>
	                            <strong>John Smith</strong>
	                            <span class="pull-right text-muted">
	                                        <em>Yesterday</em>
	                                    </span>
	                        </div>
	                        <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <strong>John Smith</strong>
	                            <span class="pull-right text-muted">
	                                        <em>Yesterday</em>
	                                    </span>
	                        </div>
	                        <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <strong>John Smith</strong>
	                            <span class="pull-right text-muted">
	                                        <em>Yesterday</em>
	                                    </span>
	                        </div>
	                        <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a class="text-center" href="#">
	                        <strong>Read All Messages</strong>
	                        <i class="fa fa-angle-right"></i>
	                    </a>
	                </li>
	            </ul>
	            /.dropdown-messages
	        </li>
	        /.dropdown
	        <li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	                <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
	            </a>
	            <ul class="dropdown-menu dropdown-tasks">
	                <li>
	                    <a href="#">
	                        <div>
	                            <p>
	                                <strong>Task 1</strong>
	                                <span class="pull-right text-muted">40% Complete</span>
	                            </p>
	                            <div class="progress progress-striped active">
	                                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40"
	                                     aria-valuemin="0" aria-valuemax="100" style="width: 40%">
	                                    <span class="sr-only">40% Complete (success)</span>
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <p>
	                                <strong>Task 2</strong>
	                                <span class="pull-right text-muted">20% Complete</span>
	                            </p>
	                            <div class="progress progress-striped active">
	                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
	                                     aria-valuemin="0" aria-valuemax="100" style="width: 20%">
	                                    <span class="sr-only">20% Complete</span>
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <p>
	                                <strong>Task 3</strong>
	                                <span class="pull-right text-muted">60% Complete</span>
	                            </p>
	                            <div class="progress progress-striped active">
	                                <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
	                                     aria-valuemin="0" aria-valuemax="100" style="width: 60%">
	                                    <span class="sr-only">60% Complete (warning)</span>
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <p>
	                                <strong>Task 4</strong>
	                                <span class="pull-right text-muted">80% Complete</span>
	                            </p>
	                            <div class="progress progress-striped active">
	                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
	                                     aria-valuemin="0" aria-valuemax="100" style="width: 80%">
	                                    <span class="sr-only">80% Complete (danger)</span>
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a class="text-center" href="#">
	                        <strong>See All Tasks</strong>
	                        <i class="fa fa-angle-right"></i>
	                    </a>
	                </li>
	            </ul>
	            /.dropdown-tasks
	        </li>
	        /.dropdown
	        <li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	                <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
	            </a>
	            <ul class="dropdown-menu dropdown-alerts">
	                <li>
	                    <a href="#">
	                        <div>
	                            <i class="fa fa-comment fa-fw"></i> New Comment
	                            <span class="pull-right text-muted small">4 minutes ago</span>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <i class="fa fa-twitter fa-fw"></i> 3 New Followers
	                            <span class="pull-right text-muted small">12 minutes ago</span>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <i class="fa fa-envelope fa-fw"></i> Message Sent
	                            <span class="pull-right text-muted small">4 minutes ago</span>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <i class="fa fa-tasks fa-fw"></i> New Task
	                            <span class="pull-right text-muted small">4 minutes ago</span>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a href="#">
	                        <div>
	                            <i class="fa fa-upload fa-fw"></i> Server Rebooted
	                            <span class="pull-right text-muted small">4 minutes ago</span>
	                        </div>
	                    </a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                    <a class="text-center" href="#">
	                        <strong>See All Alerts</strong>
	                        <i class="fa fa-angle-right"></i>
	                    </a>
	                </li>
	            </ul>
	            /.dropdown-alerts
	        </li> -->
	        <!-- /.dropdown -->
<!-- 	        <li class="dropdown"> -->
<!-- 	            <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!-- 	                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i> -->
<!-- 	            </a> -->
<!-- 	            <ul class="dropdown-menu dropdown-user"> -->
<!-- 	                <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a> -->
<!-- 	                </li> -->
<!-- 	                <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a> -->
<!-- 	                </li> -->
<!-- 	                <li class="divider"></li> -->
<%-- 	                <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out fa-fw"></i> Logout</a> --%>
<!-- 	                </li> -->
<!-- 	            </ul> -->
<!-- 	            /.dropdown-user -->
<!-- 	        </li> -->
	        <!-- /.dropdown -->
	        <li><a href="${pageContext.request.contextPath}/index">首页</a></li> 
	    </ul><!-- /.navbar-top-links -->
	
	
	    <div class="navbar-default sidebar" role="navigation">
	        <div class="sidebar-nav navbar-collapse">
	            <ul class="nav" id="side-menu">
<!-- 	                <li> -->
<!-- 	                    <a href="#" url="content/institution/code"><i class="fa fa-barcode fa-fw"></i> 机构编码</a> -->
<!-- 	                </li> -->
	                <li>
	                    <a href="#"><i class="fa fa-desktop fa-fw"></i> OA申请表单<span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                        <li>
	                            <a href="javaScript:void(0)" url="${pageContext.request.contextPath}/OA/hardware"> 硬件服务器</a>
	                        </li>
	                         <li>
	                            <a href="javaScript:void(0)" url="${pageContext.request.contextPath}/OA/services"> 服务监控</a>
	                        </li>
	                         <li>
	                            <a href="javaScript:void(0)" url="${pageContext.request.contextPath}/OA/net"> 网络监控</a>
	                        </li>
	                    </ul>
	                    <!-- /.nav-second-level -->
	                </li>
	                 <li>
	                    <a href="#"><i class="fa fa-user fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                    <c:if test="${flag==true}">
	                        <li>
	                            <a href="javaScript:void(0)" url="${pageContext.request.contextPath}/user/users"> 用户表单</a>
	                        </li>
	                    </c:if>
	                         <li>
	                            <a href="javaScript:void(0)" url="${pageContext.request.contextPath}/user/updatepwd"> 密码修改</a>
	                        </li>
	                    </ul>
	                    <!-- /.nav-second-level -->
	                </li>
	                 <c:if test="${flag==true}">
		                <li>
		                    <a href="#"><i class="fa fa-laptop fa-fw"></i> 客户端管理<span class="fa arrow"></span></a>
		                    <ul class="nav nav-second-level">
		                   
		                        <li>
		                            <a href="javaScript:void(0)" url="${pageContext.request.contextPath}/client/toclient"> 客户端更新</a>
		                        </li>
		                                        
		                    </ul>
		                    <!-- /.nav-second-level -->
		                </li>
	                </c:if>	
	                <c:if test="${flag==true}">
		                <li>
		                    <a href="javaScript:void(0)" url="${pageContext.request.contextPath}/OA/account"><i class="fa fa-laptop fa-fw"></i> 服务器账号管理</a>
		                   
		                    <!-- /.nav-second-level -->
		                </li>
	                </c:if>	   
	            </ul><!-- /.sidebar-collapse -->
	        </div><!-- /.sidebar-collapse -->
	    </div>
	    <!-- /.navbar-static-side -->
	</nav>
	
	<!-- #body-page-wrapper -->
	<div id="page-wrapper">
	    <div class="panel-body">
	    
	    </div>
	    <!-- /.panel-body -->
	</div>
	<!-- /#body-page-wrapper -->
</div>
</body>
</html>