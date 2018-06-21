<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" scope="request"><%=request.getContextPath()%></c:set>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/detail.css">
		<div class="col-lg-6 input-sm">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>软件服务详情
					</div>
				</div>
				<div class="portlet-body">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td>硬件服务器名称</td>
								<td>${hardware.name }</td>
							</tr>
							<tr>
								<td>服务名称</td>
								<td>${data.name }</td>
							</tr>
							<tr>
								<td>用途</td>
								<td>${data.yt }</td>
							</tr>
							<tr>
								<td>是否监控</td>
								<c:if test="${jk eq true }">
									<td>是</td>
								</c:if>
								<c:if test="${jk eq false }">
									<td>否</td>
								</c:if>
							</tr>
							<tr>
								<td>服务类型</td>
								<c:if test="${data.type eq 1 }">
									<td>应用服务</td>
								</c:if>
								<c:if test="${data.type eq 2 }">
									<td>数据库</td>
								</c:if>
							</tr>
							<c:forEach var="list" items="${affect }" varStatus="status">
								<tr>
									<td>外调服务${status.index + 1 }</td>
									<td>${list.servicesName }</td>
								</tr>
								<tr>
									<td>接口名称${status.index + 1 }</td>
									<td>${list.name }</td>
								</tr>
								<tr>
									<td>接口说明${status.index + 1 }</td>
									<td>${list.content }</td>
								</tr>
							</c:forEach>
							<tr>
								<td>CPU使用率阈值</td>
								<td>${data.cpuLevel }</td>
							</tr>
							<tr>
								<td>内存使用率阈值</td>
								<td>${data.memoryLevel }</td>
							</tr>
							<tr>
								<td>磁盘剩余数量阈值</td>
								<td>${data.spaceLevel }G</td>
							</tr>
							<tr>
								<td>磁盘名</td>
								<td>${data.spaceName }</td>
							</tr>
							<tr>
								<td>服务端口</td>
								<td>${data.port }</td>
							</tr>
							<tr>
								<td>重启脚本地址</td>
								<td>${data.restartBat }</td>
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
<!-- /#page-wrapper -->



