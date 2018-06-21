<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>欢迎登录监控系统</title>
		<link rel="icon" href="${pageContext.request.contextPath}/image/favicon.ico" type="image/x-icon" />
  		<link rel="shortcut icon" href="${pageContext.request.contextPath}/image/favicon.ico" type="image/x-icon" />
		<meta charset="utf-8">
				<style type="text/css">
			body {
				background-color: #326696;
				margin: 0px;
				overflow: hidden;
			}
				</style>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css" type="text/css"></link></head>
	<body>
	<div class="wrapper">
        <div class="header"><span>Monitoring System</span></div>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <ul>
                <li>
                    <div class="text">
                        <span class="yonghu"></span><input type="text" name="userName" placeholder="用户名">
                    </div>
                </li>
                <li>
                    <div class="password">
                        <span class="mima"></span><input type="password" name="userPwd" placeholder="••••••">
                    </div>
                </li>
                <li style="text-align:center;"><span style="color: red;">${msg}</span></li>
                <li>
                    <input type="submit" value="LOGIN">
                </li>
            </ul>
        </form>
        <div class="footer">
           
        </div>
    </div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/three.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/Detector.js"></script>


		
			</body>
</html>
