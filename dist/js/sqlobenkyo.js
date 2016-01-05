$(function(){
	// SQL実行
	$('#action').click(function(){
		$.post('/SQLObenkyo/exec', {
			sql: $('#statement').val()
			}, function(data) {
			$("#result").html(data);
		})
	});
	
	// テーブル一覧
	$('#tablelist').click(function(){
		$.get('/SQLObenkyo/tablist', function(data){
			$("#sqlstatement").removeClass('active');
			$("#opti").removeClass('active');
			$("#tablelist").addClass('active');
			$(".display-sql *").remove();
			$("#result *").remove();
			$(".display-sql").html(data);
			var prop = {
				"height": "300px",
				"overflow-y": "scroll"
			}
			$(".display-sql").css(prop);
			
			// テーブル詳細表示
			$('.display-sql a').click(function(){
			$.get('/SQLObenkyo/tabdetail', {
				table: $(this).text()
				}, function(data){
					$("#result").html(data);
				})
			});
		})
	});
	
	// navbar上のサインイン押下でサインイン画面をmodalで表示
	$('#signin').on('click', function () {
		$('#signinModal').modal();
	});
	
	// session情報チェック
	$.get("/SQLObenkyo/sessionck", function(data){
		var nm = data.user;
		console.log(nm);
		if(nm != 0) {
			$("nav").removeClass("navbar-inverse");
			$("nav").addClass("navbar-default");
			$("button#action").after(
				"<button class=\"btn btn-success\" id=\"commit\">" +
				"<span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span>" +
				"</button>" +
				"<button class=\"btn btn-danger\" id=\"rollback\">" +
				"<span class=\"glyphicon glyphicon-backward\" aria-hidden=\"true\"></span>" +
				"</button>"
			);
			$("button#signin").remove();
			$("div.navbar-right").remove();
			$(".navbar-header").after(
				"<ul class=\"nav navbar-nav navbar-right\">" +
				"<li class=\"dropdown\">" + 
				"<a href=\"#\" class=\"dropdown-toggle\" " +
					"data-toggle=\"dropdown\" role=\"button\" " +
					"aria-haspopup=\"true\" aria-expanded=\"false\">" +
					nm +
					"<span class=\"caret\"></span></a>" +
				"<ul class=\"dropdown-menu\">" +
				"<li id = \"changepw\"><a href=\"#\">パスワード変更</a></li>" +
				"<li role=\"separator\" class=\"divider\"></li>" +
				"<li><a href=\"/SQLObenkyo/signout\">サインアウト</a></li>" +
				"</ul></li></ul>"
				
			);
			
			// パスワード変更
			$('#changepw').on('click', function () {
				$('#changePasswdModal').modal();
			});
			
			// コミットボタン
			$(document).on('click', '#commit', function () {
				$.post('/SQLObenkyo/exec', {
					sql: $('#statement').val(),
					commit: 1
					}, function(data) {
					$("#result").html(data);
				})
			});
			
			// ロールバックボタン
			$(document).on('click', '#rollback', function () {
				$("#result *").remove();
			});
		}
	});

});
