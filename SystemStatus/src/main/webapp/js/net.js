$(document).ready(function() {
	// modal btn start
	$(".detail").on("click",function(){
		var $temp = $(this);
		var id=$temp.attr("id");
		var ip = "<p class=\"text-left\"><strong>" + $temp.attr("ipName") + ":</strong>" + $temp.attr("ip") + "</p>";
		var targetIp = "<p class=\"text-left\"><strong>" + $temp.attr("targetIpName") + ":</strong>" + $temp.attr("targetIp") + "</p>";
		
		var one_title = "<h5>"+$temp.attr("ipName");
		if ($temp.attr("one_pingStatus") == "true") {
			if ($temp.attr("one_pingMiss") > 0) {
				one_title += "&nbsp;<span class=\"glyphicon glyphicon-arrow-right\" style=\"color: rgb(225, 215, 0);\"></span>&nbsp;";
			} else {
				one_title += "&nbsp;<span class=\"glyphicon glyphicon-arrow-right\" style=\"color: rgb(0, 255, 0);\"></span>&nbsp;";
			}
			one_title += $temp.attr("targetIpName") + "</h5>";
		} else {
			one_title += "&nbsp;<span class=\"glyphicon glyphicon-arrow-right\" style=\"color: rgb(178, 34, 34);\"></span>&nbsp;";
			one_title += $temp.attr("targetIpName") + "</h5>";
		}
		var one_pingMiss = "<p class=\"text-left\">丢包率:" + $temp.attr("one_pingMiss") + "%</p>";
		var one_min = "<p class=\"text-left\">最短:" + $temp.attr("one_min") + "ms</p>";
		var one_max = "<p class=\"text-left\">最长:" + $temp.attr("one_max") + "ms</p>";
		var one_avg = "<p class=\"text-left\">平均:" + $temp.attr("one_avg") + "ms</p>";
		var one_type = "";
		if ($temp.attr("one_avg") == 1000) {
			one_type = "<p class=\"text-left\">类型:"+$temp.attr("ipName")+"接口不通</p>";
		} else if ($temp.attr("one_avg") == 400) {
			// 400
			one_type = "<p class=\"text-left\">类型:"+$temp.attr("targetIpName")+"网络不通</p>";
		}
		
		var one_port = "";
		if ($temp.attr("one_portStatus") != "") {
			var temp = $temp.attr("one_portStatus").replace(/'/g,"\"");
			console.log(temp);
			var json = $.parseJSON(temp);
			
			$.each(json,function(index, obj){
				one_port += "<p class=\"text-left\">" + index + "端口:" +obj + "</p>";
			});
		}
	
		var two_title = "<h5>"+$temp.attr("ipName");
		if ($temp.attr("two_pingStatus") == "true") {
			if ($temp.attr("two_pingMiss") > 0) {
				two_title += "&nbsp;<span class=\"glyphicon glyphicon-arrow-left\" style=\"color: rgb(225, 215, 0);\"></span>&nbsp;";
			} else {
				two_title += "&nbsp;<span class=\"glyphicon glyphicon-arrow-left\" style=\"color: rgb(0, 255, 0);\"></span>&nbsp;";
			}
			two_title += $temp.attr("targetIpName") + "</h5>";
		} else {
			two_title += "&nbsp;<span class=\"glyphicon glyphicon-arrow-left\" style=\"color: rgb(178, 34, 34);\"></span>&nbsp;";
			two_title += $temp.attr("targetIpName") + "</h5>";
		}
		var two_pingMiss = "<p class=\"text-left\">丢包率:" + $temp.attr("two_pingMiss") + "%</p>";
		var two_min = "<p class=\"text-left\">最短:" + $temp.attr("two_min") + "ms</p>";
		var two_max = "<p class=\"text-left\">最长:" + $temp.attr("two_max") + "ms</p>";
		var two_avg = "<p class=\"text-left\">平均:" + $temp.attr("two_avg") + "ms</p>";
		var two_type = "";
		if ($temp.attr("two_avg") == 1000) {
			two_type = "<p class=\"text-left\">类型:"+$temp.attr("targetIpName")+"接口不通</p>";
		} else if ($temp.attr("two_avg") == 400) {
			// 400
			two_type = "<p class=\"text-left\">类型:"+$temp.attr("ipName")+"网络不通</p>";
		}
		
		var two_port = "";
		if ($temp.attr("two_portStatus") != "") {
			var json2 = $.parseJSON($temp.attr("two_portStatus"));
			
			$.each(json2,function(index, obj){
				two_port += "<p class=\"text-left\">" + index + "端口:" +obj + "</p>";
			});
		}
		
		
		
		var msgFlag = $temp.attr("message");
		var message;
		if (msgFlag == "true") {
			message = "<p class=\"text-left\"><strong>短信通知:</strong><input type=\"checkbox\" name=\"message\" checked  data-size=\"mini\"/></p>";
		} else {
			message = "<p class=\"text-left\"><strong>短信通知:</strong><input type=\"checkbox\" name=\"message\" data-size=\"mini\"/></p>";
		}
		
		var options = {
				backdrop:"static",
				keyboard:false
			}
		
		$("#myModalLabel").empty().text("详情");
		$("#myModal").find(".modal-body").empty().append(ip).append(targetIp).append(message);
		
		if ($temp.attr("types") == 1 || $temp.attr("types") == 2) {
			$("#myModal").find(".modal-body").append(one_title).append(one_pingMiss).append(one_min).append(one_max).append(one_avg).append(one_port).append(one_type);
		}
		
		if ($temp.attr("types") == 1 || $temp.attr("types") == 3) {
			$("#myModal").find(".modal-body").append(two_title).append(two_pingMiss).append(two_min).append(two_max).append(two_avg).append(two_port).append(two_type);
		}

		$.ajax({
			url:base + "/queryTypeUpdateInfo",
			data:{id:id,type:2},
			success:function(data) {
				var table="<div id='list'>" +
				"<table class='table table-responsive input-sm'>" +
				"<caption>"+
				"更新记录"+
				"</caption>"+
				"<thead><tr>" +
				"<th class='col-xs-1 col-sm-1 col-lg-1'>姓名</th>" +
				"<th class='col-xs-2 col-sm-2 col-lg-2'>时间</th>" +
				"<th class='col-xs-2 col-sm-2 col-lg-2'>更新内容</th>" +
				"<th class='col-xs-2 col-sm-2 col-lg-2'>影响服务</th>" +
				"</tr></thead>";
				for ( var i = 0; i < data.length; i++) {
					table+="<tr class='accordion-heading'>" +
							"<td class='col-xs-1 col-sm-1 col-lg-1'>"+data[i].name+"</td>" +
							"<td class='col-xs-2 col-sm-2 col-lg-2'>"+data[i].time+"</td>" +
							"<td class='col-xs-2 col-sm-2 col-lg-2'>"+data[i].updateInfo+"</td>" +
							"<td class='col-xs-2 col-sm-2 col-lg-2'>"+data[i].fluence+"</td>" +
							"</tr>";
				}
				table+="<tbody></table></div>";
				$("#myModal").find(".modal-body").append(table);
			}		
			
		});
		$("[name='message']").bootstrapSwitch({
			 onText:'ON',  
            offText:'OFF',
       	 onColor:"success",  
            offColor:"danger",  
            size:"mini", 
            onSwitchChange:function(event,state){ 
           	 var datas = {};
           	 datas.id=$temp.attr("id");
                if(state==true){  
               	 datas.message=true;
                }else{  
               	 datas.message=false;
                }  
//                console.log(data);
                $.post("net/changeNetMessage", datas, function(data,status){
               	if (data) {
               		$temp.attr("message",datas.message)
					} else {
						console.log(datas.id + ":" + datas.message + ":修改失败")
					}
                });
                
            } 
		});
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
	
})