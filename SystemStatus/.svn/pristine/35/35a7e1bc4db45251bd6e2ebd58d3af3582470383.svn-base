$(document).ready(function() {
	// query btn start
	$("#query").on("click",function(){
		var select =$("#orgs").val();
		if (select == 0) {
			$("#orgs").parent().addClass("has-error");
			return false;
		}
		$("#orgs").parent().removeClass("has-error");
		$.ajax({
			url:"deadlock/query",
			data:{code:select},
			success:function(data) {
				$("#ajaxList").empty().append(data);
			}
		})
	})
	// query btn end
	
})