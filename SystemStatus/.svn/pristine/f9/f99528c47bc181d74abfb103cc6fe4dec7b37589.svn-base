$(document).ready(function() {
	// initialize menu
	$("#side-menu").find("li").on("click",function(){
		var URL=$(this).children("a[url]").attr("url");
		if (URL) {
			$.ajax({
				url:URL,
				success:function(data){
					$("#page-wrapper").replaceWith(data);
				}
			})
		}
	})
	// .end initialize menu
})