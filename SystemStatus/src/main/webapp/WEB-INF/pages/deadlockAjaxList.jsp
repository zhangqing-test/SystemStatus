<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<table class="table">
  <caption>信息</caption>
  <thead>
    <tr>
      <th>spid</th>
      <th>blockedSpid</th>
      <th>entityString</th>
      <th>操作</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="data" items="${list }">
  	<tr>
		<td>${data.spid }</td>
		<td>${data.blockedSpid }</td>
		<td>${data.entityString }</td>
		<td><button spid="${data.spid}" code="${data.code }" class="kill btn btn-xs btn-primary" type="button">删除</button></td>
	</tr>
	</c:forEach>
  </tbody>
</table>
<c:if test="${list == null || fn:length(list) == 0 }">
<script type="text/javascript">
	$.notify({
		// options
		message: '没有查到记录'
	},{
		// settings
		element: 'body',
		type: "info",
		allow_dismiss: true,
		newest_on_top: false,
		placement: {
			from: "top",
			align: "center"
		},
		offset: 20,
		spacing: 10,
		z_index: 1031,
		delay: 500,
		timer: 500,
		animate: {
			enter: 'animated fadeInDown',
			exit: 'animated fadeOutUp'
		}
	});
	
	$(".kill").on("click",function(){
		var spid = $(this).attr("spid");
		var code = $(this).attr("code");
		$.ajax({
			url:"deadlock/kill",
			data:{spid:spid,code:code},
			success:function(data) {
				if (data == "success") {
					$.notify({
						// options
						message: '成功，刷新页面'
					},{
						// settings
						element: 'body',
						type: "success",
						allow_dismiss: true,
						newest_on_top: false,
						placement: {
							from: "top",
							align: "center"
						},
						offset: 20,
						spacing: 10,
						z_index: 1031,
						delay: 500,
						timer: 500,
						animate: {
							enter: 'animated fadeInDown',
							exit: 'animated fadeOutUp'
						}
					});
				} else {
					$.notify({
						// options
						message: '失败，刷新页面'
					},{
						// settings
						element: 'body',
						type: "danger",
						allow_dismiss: true,
						newest_on_top: false,
						placement: {
							from: "top",
							align: "center"
						},
						offset: 20,
						spacing: 10,
						z_index: 1031,
						delay: 500,
						timer: 500,
						animate: {
							enter: 'animated fadeInDown',
							exit: 'animated fadeOutUp'
						}
					});
				}
				
				setTimeout(function(){
					$("#query").click();
				},2000); 
				
			}
		})
	});
	
</script>
</c:if>


