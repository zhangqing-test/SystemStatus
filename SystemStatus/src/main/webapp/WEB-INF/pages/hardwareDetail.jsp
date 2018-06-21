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
						<i class="icon-cogs"></i>硬件服务器详情
					</div>
				</div>
				<div class="portlet-body">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td>服务器名称</td>
								<td>${data.name }</td>
							</tr>
							<tr>
								<td>主机名</td>
								<td>${data.zjm }</td>
							</tr>
							<tr>
								<td>服务器属性</td>
								<td><c:if test="${data.fwqsx eq 0 }">虚拟机</c:if> <c:if
										test="${data.fwqsx eq 1 }">实体机 </c:if></td>
							</tr>
							<tr>
								<td>用途</td>
								<td>${data.yt }</td>
							</tr>
							<tr>
								<td>域</td>
								<td>${data.yu }</td>
							</tr>
							<tr>
								<td>服务器型号</td>
								<td>${data.fwqxh}</td>
							</tr>
							<tr>
								<td>所属机房</td>
								<td>${data.ssjf }</td>
							</tr>
							<tr>
								<td>机柜</td>
								<td>${data.jg}</td>
							</tr>
							<tr>
								<td>U位</td>
								<td>${data.uw }</td>
							</tr>
							<tr>
								<td>CPU配置</td>
								<td>${data.cpuConfig }</td>
							</tr>
							<tr>
								<td>内存配置</td>
								<td>${data.memoryConfig }</td>
							</tr>
							<tr>
								<td>硬盘</td>
								<td>${data.yp }</td>
							</tr>
							<tr>
								<td>网卡</td>
								<td>${data.wk }</td>
							</tr>
							<tr>
								<td>光纤卡</td>
								<td>${data.gxk }</td>
							</tr>
							<tr>
								<td>电源</td>
								<td>${data.dy}</td>
							</tr>
							<tr>
								<td>存储</td>
								<td>${data.cc}</td>
							</tr>
							<tr>
								<td>序列号</td>
								<td>${data.xlh }</td>
							</tr>
							<tr>
								<td>购买时间</td>
								<td><fmt:formatDate value="${data.gmsj }" type="both" /></td>
							</tr>
							<tr>
								<td>过保时间</td>
								<td><fmt:formatDate value="${data.gbsj }" type="both" /></td>
							</tr>
							<tr>
								<td>选填使用年限</td>
								<td>${data.synx }</td>
							</tr>
							<tr>
								<td>杀毒软件</td>
								<td>${data.sdrj}</td>
							</tr>
							<tr>
								<td>所属机构</td>
								<td>${data.ssjg }</td>
							</tr>
							<tr>
								<td>宿主机IP</td>
								<td>${data.szjip}</td>
							</tr>
							<tr>
								<td>运行系统</td>
								<td>${data.yxxt }</td>
							</tr>
							<tr>
								<td>IP地址</td>
								<td>${data.ip }</td>
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
						<i class="icon-cogs"></i>服务负责人
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
								<td>一级责任人职位</td>
								<td>${one.managerJob }</td>
							</tr>
							<tr>
								<td>一级责任人电话</td>
								<td>${one.managerPhone }</td>
							</tr>
							<tr>
								<td>二级责任人</td>
								<td>${two.managerName }</td>
							</tr>
							<tr>
								<td>二级责任人职位</td>
								<td>${two.managerJob }</td>
							</tr>
							<tr>
								<td>二级责任人电话</td>
								<td>${two.managerPhone }</td>
							</tr>
							<tr>
								<td>三级责任人</td>
								<td><c:if test="${!empty three}">${three.managerName }</c:if></td>
							</tr>
							<tr>
								<td>三级责任人职位</td>
								<td><c:if test="${!empty three}">${three.managerJob }</c:if></td>
							</tr>
							<tr>
								<td>三级责任人电话</td>
								<td><c:if test="${!empty three}">${three.managerPhone }</c:if></td>
							</tr>
							<tr>
								<td>备注说明</td>
								<td>${data.comments }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /#page-wrapper -->
<script type="text/javascript">
	$("#return").on("click", function() {
		$.ajax({
			url : base + "/OA/hardware",
			success : function(data) {
				$("#page-wrapper").replaceWith(data);
			}
		})
	})
</script>


