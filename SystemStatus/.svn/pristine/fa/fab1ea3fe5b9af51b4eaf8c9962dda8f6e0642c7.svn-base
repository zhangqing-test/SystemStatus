/**
 * Created by yang on 2017/9/6.
 */
$(document).ready(function() {
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	$('#services_list').DataTable({
		paging: true,//分页
		ordering:false,//是否启用排序
		searching:false,//搜索
		//开启服务器模式
	    serverSide: true,
		autoWidth:true,
		language: {
			lengthMenu: '<select class="form-control input-xsmall">' + '<option value="1">1</option>' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option></select>'
			+'&nbsp;&nbsp;&nbsp;服务名称：<input type="text" class="form-control"  id="fwName" >&nbsp;&nbsp;&nbsp;IP：<input type="text" class="form-control"  id="fwIP" >&nbsp;&nbsp;&nbsp;&nbsp;端口：<input type="text" class="form-control"  id="fwPort" ><br/><br/>	是否监控： <input value="true"  name="sfjk" type="radio" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input value="false"  name="sfjk" type="radio" >否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务类型 ：<input value="1"  name="fwType" type="radio" checked="checked">应用服务&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+'<input value="2"  name="fwType" type="radio" >数据库&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="btn" class="btn btn-default">搜索</button>',//左上角的分页大小显示。
			search: '<span class="label label-success">搜索：</span>',//右上角的搜索文本，可以写html标签
			paginate: {//分页的样式内容。
				previous: "上一页",
				next: "下一页",
				first: "第一页",
				last: "最后"
			},
			zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
			//下面三者构成了总体的左下角的内容。
			info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
			infoEmpty: "0条记录",//筛选为空时左下角的显示。
			infoFiltered: ""//筛选之后的左下角筛选提示，
	    },
	    pagingType: "full_numbers",//分页样式的类型
		ajax:{
			url:'OA/services/query',
			type:'POST',
			data: function(d){
				d.fwType = "1";
			    d.sfjk = "true";
			}
		}
	});
	$("#btn").on("click",function(){
		//按钮 点击事件触发table重新请求服务器
		var fwName = $('#fwName').val();
	    var fwip = $('#fwIP').val();
	    var fwport = $('#fwPort').val();
	    var sfjk = $("input[name='sfjk']:checked").val(); 
	    var fwType = $("input[name='fwType']:checked").val();
	    var param = {"fwName":fwName,"fwip":fwip,"fwport":fwport,"sfjk":sfjk,"fwType":fwType};
		var dt = $("#services_list").dataTable();
		dt.fnSettings().ajax.data = param;
		//dt.api().ajax.reload();
		dt.fnDraw(true);
//		$("#services_list").dataTable().fnDraw(true);
	});
	
});
//var datas = null;
//function searchClick(d) {
//	console.log(d);
//    var fwName = $('#fwName').val();
//    var fwip = $('#fwIP').val();
//    var fwport = $('#fwPort').val();
//    var sfjk = $("input[name='sfjk']:checked").val(); 
//    var fwType = $("input[name='fwType']:checked").val();
//    //添加额外的参数传给服务器
//    d.fwName = fwName;
//    d.sfjk=sfjk;
//    d.fwType=fwType;
//    d.fwip = fwip;
//    d.fwport = fwport;
//    return d;
//}