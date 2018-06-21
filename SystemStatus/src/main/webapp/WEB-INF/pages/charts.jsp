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
	src="${pageContext.request.contextPath}/js/status.js"></script>	
<script
	src="${pageContext.request.contextPath}/js/echarts/echarts.min.js"></script>		
<title>index</title>
</head>
<body>
	<div class="container">
		<!-- navbar start -->
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
					<li class=""><a href="${pageContext.request.contextPath}/net">网络</a></li>
<!-- 					<li><a href="#">Link test</a></li> -->
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
			</div>
			<!-- /.navbar-collapse -->
		</div>
		</nav>
		<!-- /.navbar end -->
		
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		<div class="col-xs-12 col-sm-12 col-lg-5">
   		<div id="main" style="height:400px;"></div>
   		
   		</div>
   		<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        
        var data = $.parseJSON("${data}");
        
        var times = $.parseJSON('${time}');
        

        // 指定图表的配置项和数据
        var option = {
        		baseOption:{
        			 title: {
             	        text: 'CPU占用率'
             	    },
             	    tooltip: {
             	        trigger: 'axis'
             	    },
             	    legend: {
             	        data:['数据库80']
             	    },
             	    toolbox: {
             	        feature: {
             	            saveAsImage: {}
             	        }
             	    },
             	    xAxis: {
             	        type: 'category',
             	        boundaryGap: false,
             	        data:times,
             	    },
             	    yAxis: {
             	        type: 'value'
             	    },
             	    series: [
             	        {
             	            name:'数据库80',
             	            type:'line',
             	            stack: '总量',
             	            data:data
             	        }
             	    ]
        		},
        	};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
	</div>
</body>
</html>