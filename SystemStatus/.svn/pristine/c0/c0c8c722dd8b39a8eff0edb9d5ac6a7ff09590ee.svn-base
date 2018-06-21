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
	
<script src='https://img.hcharts.cn/highstock/highstock.js'></script>
<script src='https://img.hcharts.cn/highcharts/modules/exporting.js'></script>
<script
	src='https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js'></script>	

<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<script>var base = "${base}";</script>
<title>index</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/springy.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/springyui.js"></script>
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
		<form class="form-inline" role="form" method="post" action="${pageContext.request.contextPath}/serverInfo/query">   
		      <div class="form-group">        
		        IP： <input type="text" class="form-control"  name="ip" placeholder="请输入ip" value="${form.ip }">  
		      </div>   
		     <div class="form-group">           
		       	名称： <input type="text" class="form-control" name="name"  placeholder="请输入名称" value="${form.name }">  
		     </div>  
		     <div class="form-group " >           
		       	CPU：  <input type="number" class="form-control input-sm"  name="cpuMin" value="${form.cpuMin }" style="width: 60px;">
		     </div>
		     <div class="form-group " >   
		       	 	 <input type="number" class="form-control input-sm" name="cpuMax" value="${form.cpuMax }" style="width: 60px;">  
		     </div>		     
		     <div class="form-group ">           
		       	内存： <input type="number" class="form-control input-sm"   name="memoryMin"  value="${form.memoryMin }" style="width: 60px;">
		     </div> 
		     <div class="form-group ">
		       	 	 <input type="number" class="form-control input-sm"  name="memoryMax" value="${form.memoryMax }" style="width: 60px;">  
		     </div> 
		     <div class="form-group ">           
		       	磁盘 ：<input type="number" class="form-control input-sm"   name="spaceMin" value="${form.spaceMin }" style="width: 60px;">
		     </div> 
		     <div class="form-group "> 
		       	 	 <input type="number" class="form-control input-sm" name="spaceMax"  value="${form.spaceMax }" style="width: 60px;">  
		     </div> 
		     <button type="submit" class="btn btn-default">搜索</button>  
		     
   		</form> 
		<div id="list">
			<table class="table table-responsive input-sm">
				<caption>
					数据时间:<span id="time">${time }</span>&nbsp;&nbsp;&nbsp;
					<button type="button" class="relation btn btn-xs btn-primary" >生成关系图</button>
				</caption>
				<thead>
					<tr>
					
						<th class="col-xs-1 col-sm-1 col-lg-1 "><input id="all" type="checkbox" value="checkbox"  /></th>
						<th class="col-sm-1 col-lg-1 hidden-xs">ip</th>
						<th class="col-xs-2 col-sm-2 col-lg-2">名称</th>
						<th class="col-xs-1 col-sm-1 col-lg-1">CPU</th>
						<th class="col-xs-1 col-sm-1 col-lg-1">内存</th>
						<th class="col-xs-1 col-sm-1 col-lg-1">磁盘</th>
						<th class="col-xs-2 col-sm-2 col-lg-2">端口</th>
						<th class="col-xs-2 col-sm-2 col-lg-2">操作</th>
					    <th class="col-xs-2 col-sm-1 col-lg-1">监控图</th>
						
					</tr>
				</thead>
				<tbody>
				<c:forEach var="data"  items="${list}">
					<tr class="accordion-heading">
						<td class="col-xs-1 col-sm-1 col-lg-1 "><input  type="checkbox" value="${data.id }"  /></td>
						<td class="ip col-sm-1 col-lg-1 hidden-xs">${data.ip }</td>
						<td id="${data.id}" class="name col-xs-2 col-sm-2 col-lg-2" data-toggle="modal" data-target="#myModal4">${data.name }</td>
						<td class="cpu col-xs-1 col-sm-1 col-lg-1">
						<c:if test="${data.cpu le 50 }">
							<span class="label label-success">${data.cpu }%</span>
						</c:if>
						<c:if test="${(data.cpu lt 80)&&(data.cpu gt 50)}">
							<span class="label label-warning">${data.cpu }%</span>
						</c:if>
						<c:if test="${data.cpu ge 80 }">
							<span class="label label-danger">${data.cpu }%</span>
						</c:if>
						</td>
						<td class="memory col-xs-1 col-sm-1 col-lg-1">
						<c:if test="${data.memory le 50 }">
							<span class="label label-success">${data.memory }%</span>
						</c:if>
						<c:if test="${(data.memory lt 80)&&(data.memory gt 50)}">
							<span class="label label-warning">${data.memory }%</span>
						</c:if>
						<c:if test="${data.memory ge 80 }">
							<span class="label label-danger">${data.memory }%</span>
						</c:if>
						</td>
						<td class="space col-xs-1 col-sm-1 col-lg-1">
						<c:if test="${data.spaceMax le 50 }">
							<span class="label label-success">${data.spaceMax }%</span>
						</c:if>
						<c:if test="${(data.spaceMax lt 80)&&(data.spaceMax gt 50)}">
							<span class="label label-warning">${data.spaceMax }%</span>
						</c:if>
						<c:if test="${data.spaceMax ge 80 }">
							<span class="label label-danger">${data.spaceMax }%</span>
						</c:if>
						</td>
						<td class="col-xs-2 col-sm-2 col-lg-2">${data.portStatus }</td>
						<td class="col-xs-2 col-sm-1 col-lg-1">
							<button type="button" class="detail btn btn-xs btn-primary" 
							id="${data.id }"
							ip="${data.ip }" 
							name="${data.name }"
							cpu="${data.cpu }"
							memoryUsed="${data.memoryUsed }"
							memoryTotal="${data.memoryTotal }"
							space='${data.space }'
							message="${data.message }"
							>详情</button>
						<%-- <c:if test="${data.memory ge 80 }"> --%>
							<button type="button" class="processbtn btn btn-xs btn-primary" id="${data.id }" ip="${data.ip }" >进程管理</button>
						
							<button type="button" class="memorybtn btn btn-xs btn-primary" id="${data.id }" ip="${data.ip }" >内存列表</button>
					<%-- 	</c:if> --%>
						</td>
						<td><button id="${data.id }" ip="${data.ip}"
									name="${data.name}" type="line" class="btn btn-xs btn-primary"
									data-toggle="modal" data-target="#myModal3">查看</button></td>
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
	            <div class="modal-body"></div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel2">内存列表</h4>
	            </div>
	            <div class="modal-body" style="height:25em;overflow:auto;">
	            	<table id="memory_list" width="100%" class="table table-striped table-bordered table-hover input-sm">
						<thead>
							<tr>
							    <th id="modalcheck" class="col-lg-2" style="display:none;" ></th>
								<th class="col-lg-2">PID</th>
								<th class="col-lg-2">进程名称</th>
								<th class="col-lg-2">用户</th>
								<th class="col-lg-2">内存</th>
								<th class="col-lg-2">内存占用</th>
							</tr>
						</thead>
						<tbody id="tbody"></tbody>
					</table>
	            </div>
	            <div class="modal-footer">
	                <button id="modalsub" type="button" class="btn btn-primary" style="display:none;" onclick="pc();">提交</button>
	                <button id="modalclo" type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
    <!-- 改写模态框（Modal） -->
	<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel3"></h4>
				</div>
				<div class="modal-body">
					<div id="container" style="min-width: 400px; height: 400px"></div>
				</div>
				<!--新增图表操作按钮  -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	            <!-- <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
	            </div> -->
	            
	            <div id="detailInfo" ></div>
	           
	    </div><!-- /.modal -->
	</div>
	<!-- 模态框（Modal） -->
	
<script type="text/javascript">
function pc(){
	var id;
	var check = $("#tbody :checked");
	var datas = new Array();
	if(check.length == 0){
		alert("请选择需要监控进程！");
		return;
	}
	if(check.length>=1){
		check.each(function(){
			id= $(this).attr("id");
			var va = $(this).val().replace(/%/g,"%25");
			datas.push(va);
	});
 }else{
	 datas.push(check.val());
 }
	$.ajax({
		url:base + "/OA/services/processmap",
		data:{info:datas.join(","),
			  id:id},
		dataType: 'text',
		success:function(data) {
			console.log(data);
			if(data == "success"){
				$("#modalclo").click();
			}else{
				alert("提交出错！");
			}
		}
	}) 
 }

$('#myModal4').on('show.bs.modal', function(event) {
	
	var td = $(event.relatedTarget); 
	var a  = td.attr("id");
	console.log(a);
	$.ajax({
		url:base + "/OA/services/detailinfo",
		data:{id:a},
		success:function(data) {
			console.log(data);
			document.getElementById("detailInfo").innerHTML = data;
/* 			$("#detailInfo").replaceWith(data);
 */		}
	}); 
})

		$('#myModal3').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); //触发事件的按钮  
			var modalid = button.attr('id');
			var modalip = button.attr('ip'); //解析出内容
			var modalname = button.attr('name');
			var modal = $(this); //获得模态框本身
			modal.find('.modal-title').text(modalname + '(' + modalip + ')');
			$.ajax({
				url : base + "/serverInfo/getServiceAllMsg",
				data : {
					id : modalid
				},
				async : false,
				success : function(data) {
					cpudat = ArrayToInt(data.data1);
					ncdat  = ArrayToInt(data.data2);
					cpdata = ArrayToInt(data.data3);
					chartshow(cpudat,ncdat,cpdata);
					$(".highcharts-credits").hide();
				}
			})
		});
		
		function ArrayToInt(strarray) {
			for (var i = 0; i < strarray.length; i++) {
				strarray[i][0] = parseInt(strarray[i][0]) + 8 * 60 * 60 * 1000;
				strarray[i][1] = parseInt(strarray[i][1]);
			}
			return strarray;
		}

function chartshow(dt1,dt2,dt3) {
    var seriesOptions = [],
        seriesCounter = 0,
        names = ['CPU', '内存', '磁盘'],
        dt =[dt1,dt2,dt3],
        // create the chart when all data is loaded
        createChart = function () {
            $('#container').highcharts('StockChart', {
                rangeSelector: {
                    selected:4
                },
                legend: {
                    enabled: true,
                    align: 'right',
                    borderColor: 'grey',
                    borderWidth: 2,
                    layout: 'vertical',
                    verticalAlign: 'top',
                    margin:10,
                    y: 100,
                    shadow: false
                },
                /* legend: {
                    enabled: true,
                    align: 'right',
                    backgroundColor: '#FCFFC5',
                    borderColor: 'black',
                    borderWidth: 2,
                    layout: 'vertical',
                    verticalAlign: 'top',
                    y: 100,
                    shadow: true
                }, */
                title : {
					text : 'CPU/内存/磁盘的使用率（%）'
				},
                plotOptions: {
                    series: {
                    	showInLegend : true
                    }
                },
                tooltip: {
                    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b>',
                },
                series: seriesOptions
            });
        };
    $.each(names, function (i, name) {
    	console.log(names.length);
    	console.log(dt[i]);
            seriesOptions[i] = {
                name: name,
                data: dt[i]
            };
          
            seriesCounter += 1;
            if (seriesCounter === names.length) {
            	console.log(seriesOptions);
                createChart();
            }
        });
    };

</script>
</body>
</html>