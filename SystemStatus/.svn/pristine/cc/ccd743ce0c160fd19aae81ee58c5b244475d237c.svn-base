$(document).ready(function() {
	// init
	init();
	
	// modal btn start
	$(".detail").on("click",function(){
		// 获取内容
		var $temp = $(this);
		var id=$temp.attr("id");
		var name=$temp.attr("name");
		var updateContent=$temp.attr("updateContent");
		var ip=$temp.attr("ip");
		var level=$temp.attr("level");
		var planStartTime=$temp.attr("planStartTime");
		var planUsedTime=$temp.attr("planUsedTime");
		var updateStepContent=$temp.attr("updateStepContent");
		var rollback=$temp.attr("rollback");
		var startTime=$temp.attr("startTime");
		var endTime=$temp.attr("endTime");
		var comments=$temp.attr("comments");
		var datetime=$temp.attr("datetime");
		var restartService=$temp.attr("restartService");
		var netOff=$temp.attr("netOff");
		var his=$temp.attr("his");
		var sis=$temp.attr("sis");
		var nis=$temp.attr("nis");
		
		
		$("#myModalLabel").empty().text("详情");
		// 构建body内容
		$("#modal_name").empty().text(name);
		$("#modal_updateContent").empty().text(updateContent);
		$("#modal_ip").empty().text(ip);
		if (level == 0) {
			$("#modal_level").empty().text("正常");
		} else if(level == 1) {
			$("#modal_level").empty().text("重要");
		} else {
			$("#modal_level").empty().text("紧急");
		}
		$("#modal_planStartTime").empty().text(planStartTime);
		$("#modal_planUsedTime").empty().text(planUsedTime+"分钟");
		$("#modal_updateStepContent").empty().text(updateStepContent);
		$("#modal_rollback").empty().text(rollback);
		$("#modal_startTime").empty().text(startTime);
		$("#modal_endTime").empty().text(endTime);
		$("#modal_comments").empty().text(comments);
		$("#modal_his").empty().text(his)
		$("#modal_sis").empty().text(sis)
		$("#modal_nis").empty().text(nis)
		
		var type=$("#_type");
		if (type.val() == 1 || type.val() == 3) {
			$("#modal_restart").parent().find("label").empty().text("是否需要断网");
			if (netOff == "true") {
				$("#modal_restart").empty().text("需要");
			} else {
				$("#modal_restart").empty().text("不需要");
			}
		}
		if (type.val() == 2) {
			$("#modal_restart").parent().find("label").empty().text("是否需要重启服务");
			if (restartService == "true") {
				$("#modal_restart").empty().text("需要");
			} else {
				$("#modal_restart").empty().text("不需要");
			}
		}
		
		
		var options = {
				backdrop:"static",
				keyboard:false
			}
		
		
		$("#myModal").modal(options);
	})
	// modal btn end
	
	
	
	// 解决modal弹窗居中 start
	$('#myModal').on('show.bs.modal', function (e) {  
		var $this = $(this);
        var $modal_dialog = $this.find('.modal-dialog');
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
        $this.css('display', 'block');
        $modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2) });
    });  
	// 解决modal弹窗居中 end
	
	// 搜索按钮start
	$("#searchBtn").on("click",function(){
		var type = $("#updateType").val();
		if (type == 0) {
			 $("#updateType").parent().addClass("has-error");
			 return false;
		}
		
		var name = $("#name").val();
		var ip = $("#ip").val();
		
		var url = $("#searchForm").attr("action");
		
		url += "?type=" + type;
		if (name) {
			url += "&name=" + name;
		}
		if (ip) {
			url += "&ip=" + ip;
		}
		window.location.href = url;
	})
	// 搜索按钮end
	

	// name搜索补全 start
	$("#name").typeahead({
		source: function (query, process) {
			var type = $("#updateType").val();
			if (type == 0) {
				 $("#updateType").parent().addClass("has-error");
				 process("[]");
				 return false;
			}
			
			$("#updateType").parent().removeClass("has-error");
			if (type == 1) {
				//query是输入值
				jQuery.getJSON('autoComplete/hname', { "name": query }, function (data) {
					process(data);
				});
			}
			
			if (type == 2) {
				//query是输入值
				jQuery.getJSON('autoComplete/sname', { "name": query }, function (data) {
					process(data);
				});
			}
			
			if (type == 3) {
				//query是输入值
				jQuery.getJSON('autoComplete/nname', { "name": query }, function (data) {
					process(data);
				});
			}
			
		},
		items: 8, //显示8条
		delay: 500 //延迟时间
	});
	// name搜索补全 end
	
	// ip搜索补全 start
	$("#ip").typeahead({
		source: function (query, process) {
			var type = $("#updateType").val();
			if (type == 0) {
				 $("#updateType").parent().addClass("has-error");
				 process("[]");
				 return false;
			}
			
			$("#updateType").parent().removeClass("has-error");
			if (type == 1) {
				//query是输入值
				jQuery.getJSON('autoComplete/serverip', { "ip": query }, function (data) {
					process(data);
				});
			}
			
			if (type == 2) {
				//query是输入值
				jQuery.getJSON('autoComplete/netip', { "ip": query }, function (data) {
					process(data);
				});
			}
			
		},
		items: 8, //显示8条
		delay: 500 //延迟时间
	});
	// ip搜索补全 end
	
	
})

function init() {
	var type=$("#_type");
	if (type.val()) {
		$("#updateType").val(type.val());
	} else {
		$("#updateType").val("0");
	}
	
	var name=$("#_name");
	if (name) {
		$("#name").val(name.val());
	}
	var ip=$("#_ip");
	if (ip) {
		$("#ip").val(ip.val());
	}
}
