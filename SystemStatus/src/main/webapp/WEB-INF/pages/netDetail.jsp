<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<script>
	var base = "${base}";
</script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h4 class="page-header">
				<button type="button" id="return" class="btn btn-link">
					<p class="fa fa-angle-double-left">返回列表</p>
				</button>
			</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6 input-sm">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>网络详情
					</div>
				</div>
				<div class="portlet-body">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td>线路名称</td>
								<td>${data.netName }</td>
							</tr>
							<tr>
								<td>主ip名称</td>
								<td>${data.ipName }</td>
							</tr>
							<tr>
								<td>主ip地址</td>
								<td>${data.ip }</td>
							</tr>
							<tr>
								<td>副ip名称</td>
								<td>${data.targetIpName }</td>
							</tr>
							<tr>
								<td>副ip地址</td>
								<td>${data.targetIp }</td>
							</tr>
							<tr>
								<td>网络策略</td>
								<td><c:if test="${data.type eq 1 }">
		  	双向
		  </c:if> <c:if test="${data.type eq 2 }">
		  	主到副
		  </c:if> <c:if test="${data.type eq 3 }">
		  	副到主
		  </c:if></td>
							</tr>
							<tr>
								<td>主ip端口</td>
								<td>${data.port }</td>
							</tr>
							<tr>
								<td>副ip端口</td>
								<td>${data.targetPort }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-6 input-sm">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>网络负责人
					</div>
				</div>
				<div class="portlet-body">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td>一级责任人</td>
								<td>${one.managerName }</td>
							</tr>
							<tr>
								<td>一级责任人职位</td> <td>${one.managerJob }</td>
							</tr>
							<tr>
								<td>一级责任人电话</td> <td>${one.managerPhone }</td>
							</tr>
							<tr>					
									<td>二级责任人</td> <td>${two.managerName }</td>
							</tr>
							<tr>
								<td>二级责任人职位</td> <td>${two.managerJob }</td>
							</tr>
							<tr>
								<td>二级责任人电话</td> <td>${two.managerPhone }</td>
							</tr>
							<tr>
								<td>三级责任人</td> <td> <c:if
										test="${!empty three}">
			${three.managerName }	      
	      </c:if>
								</td>
							</tr>
							<tr>
								<td>三级责任人职位</td> <td> <c:if
										test="${!empty three}">
			${three.managerJob }	      
	      </c:if>
								</td>
							</tr>
							<tr>
								<td>三级责任人电话</td> <td> <c:if
										test="${!empty three}">
			${three.managerPhone }	      
	      </c:if>
								</td>
							</tr>
							<tr>
								<td>备注说明</td> <td>${data.comments }</td>
							</tr>
				</div>
				<!-- /.row -->
			</div>
			<!-- /#page-wrapper -->
			<script type="text/javascript">
				$("#return").on("click", function() {
					$.ajax({
						url : base + "/OA/net",
						success : function(data) {
							$("#page-wrapper").replaceWith(data);
						}
					})
				})
			</script>