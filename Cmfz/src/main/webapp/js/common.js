$(function(){

	$('.J_zhongwen').click(function(){
		if($(this).html()=='中文'){
			$(this).html('English');
			if(window.localStorage){
				localStorage.setItem("lang",'c');
			}
			$('.J_phoneNav').hide();
			$('body').css('overflow','auto');
		}else{
			$(this).html('中文');
			if(window.localStorage){
				localStorage.setItem("lang",'e')
			}
			$('.J_phoneNav').hide();
			$('body').css('overflow','auto');
		}
	})

	var w=document.documentElement?document.documentElement.clientWidth:document.body.clientWidth;//先获取窗口宽度
	if(w>=768){
		var J_loginHeight=$(document).height()-$('.J_partfw').outerHeight();
		$('.J_login').css('min-height',J_loginHeight);
	}

	if (isPC()) {
		var contentHeight = document.body.scrollHeight;

		var winHeight;
		if (window.innerHeight != undefined) {
			winHeight = window.innerHeight;
		} else if (document.documentElement.clientHeight != 0) {
			//IE 8
			winHeight = document.documentElement.clientHeight;
		} else {
			//IE6,7
			winHeight = document.body.clientHeight;
		}
		if (!(contentHeight > winHeight)) {
			$(".J_partfw").addClass("fixed");
		} else {
			$(".J_partfw").removeClass("fixed");
		}
	}

})

function isPC() {
	var userAgentInfo = navigator.userAgent;
	var Agents = ["Android", "iPhone",
		"SymbianOS", "Windows Phone",
		"iPad", "iPod"];
	var flag = true;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = false;
			break;
		}
	}
	return flag;
}