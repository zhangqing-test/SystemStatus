<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<script>var base = "${base}";</script>
<style type="text/css">

#hidebg { 
   			position:absolute;top:0px;
	      	background-color:#000;
	     	width:100%;  /*宽度设置为100%，这样才能使隐藏背景层覆盖原页面*/
			height:100%;
	      	opacity:0.4;  /*非IE浏览器下设置透明度为60%*/
	      	z-Index:2;
      } 
      #hold{
      		width: 50px;
      		height:50px;
  			position: absolute;
  			left: 0;
            right: 0;
            bottom: 0;
            top:0;
            margin: auto;
  			border-radius:60px;   
  			box-shadow:0px 1px 2px 1px aqua,
	           inset 0px 1px 1px rgba(255,255,255,0.7);
  			z-Index:3;
  			cursor:pointer;
      }
</style>

<div id="page-wrapper" >
	<div class="row">
		<div class="col-lg-12">
			<h4 class="page-header">更新客户端</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		 <div class="form-inline" role="form"  >    	     
		     <div class="form-group">           
		       		IP：<input type="text" class="form-control clientIp" id="clientIp" >  
		     </div>  
		     <div class="form-group">  		    
		     	<button id="btn" class="btn btn-default">更新</button> 
		     </div> 
		     <div class="form-group">  		    
		     	<button id="btn2" class="btn btn-default">一键更新</button> 
		     </div>
		      <div class="form-group">  		    
		     	<button id="btn3" class="btn btn-default">更新失败记录</button> 
		     </div>  
		  </div> 
		  <div id="list">
			  <table id="client_list" width="50%" class="table table-responsive input-sm">
					<thead>
						<tr>
							<th class="col-lg-1">IP</th>
							<th class="col-lg-1">名称</th>
							<th class="col-lg-1">服务状态</th>
							<th class="col-lg-1">构建状态</th>
							<th class="col-lg-1">重启状态</th>
						</tr>
					</thead>
					<tbody id="tbody"></tbody>
			 </table>
		</div> 
   	</div> 
	
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->
<script src="${pageContext.request.contextPath}"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#btn").on("click",function(){
	var ip=$(".clientIp").val();
	if(ip==""){
		alert("IP地址为空！");
		return;
	}
	if(confirm("IP:"+ip+"  确认更新吗？")){
		startHoldOn();
		$.ajax({
			url:base + "/client/update",
			data:{clientIp:ip,flag:0},
			dataType: 'text',
			success:function(data) {
				stopHoldOn();
				alert(data);

			}
					
		});	 	
	}
		
		
		
	});
	$("#btn2").on("click",function(){
		if(confirm("确认一键更新吗？")){
		startHoldOn();
		$.ajax({
			url:base + "/client/update",
			data:{clientIp:0,flag:1},
			dataType: 'text',
			success:function(data) {
				stopHoldOn();			
				alert(data);
			}
					
		});		
	}
		
	});
	
	$("#btn3").on("click",function(){
		$("#tbody").html("");
		$.ajax({
			url:base + "/client/bad",
			data:{},
			success:function(data) {
				stopHoldOn();			
				var table="";
				for ( var i = 0; i < data.length; i++) {
						table+="<tr class='accordion-heading'>" +
								"<td class='col-lg-1'>"+data[i].ip+"</td>" +
								"<td class='col-lg-1'>"+data[i].name+"</td>" +
								"<td class='col-lg-1'>"+data[i].serverStatus+"</td>" +
								"<td class='col-lg-1'>"+data[i].buildStatus+"</td>" +
								"<td class='col-lg-1'>"+data[i].restartStatus+"</td>" +
								"</tr>";
					}
				$("#tbody").append(table);
	
			}
					
		});		
	});
	
	
});
 function startHoldOn(){
	 var alertDiv="<img id='hold' alt='' src='${pageContext.request.contextPath}/image/hold.gif'><div id='hidebg'></div>";
	 $("html").append(alertDiv);
 }
 function stopHoldOn(){
	 $("#hold").remove();
	 $("#hidebg").remove();
 }
</script>


