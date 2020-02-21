<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
/*角标 */
.ii {
	display: none;
	background: #f00;
	border-radius: 50%;
	width: 20px;
	height: 20px;
	top: 5px;
	right: 0px;
	position: absolute;
	text-align: center;
	color: #FFF;
	z-index: 99999;
}

.action {
	background-color: #fff;
	float: right;
	width: 759px;
	padding-bottom: 69px;
	background: url(/project4/images/right.gif) no-repeat 0 bottom;
}

* {
	margin: 0;
	padding: 0;
}

body {
	font: 12px 宋体;
}

div {
	display: block;
}

img {
	border: 0px;
}

a {
	color: #000;
	text-decoration: none;
}

.global-width {
	width: 971px;
	margin: 0 auto;
}

.top {
	background: url("/project4/images/Top_bg.gif") repeat-x;
	width: 100%;
	height: 88px;
}

.top .logo {
	margin: 17px 0 0 0;
}

.status {
	width: 100%;
	height: 36px;
	background: url(/project4/images/Top_bg.gif) repeat-x 0 bottom;
	line-height: 36px;
}

.usertype {
	color: #2357E7;
	margin-right: 30px;
}

.main {
	background: #4E93BB;
	height: 100%;
	overflow: hidden;
	padding: 14px 0;
}

.nav {
	float: left;
	width: 191px;
	padding-bottom: 69px;
	background: url(../images/left_bg.gif) no-repeat -382px bottom;
}

.nav .t {
	height: 51px;
	background: url(/project4/images/left_bg.gif) no-repeat;
}

dt {
	display: block;
}

.nav dl {
	background: url(/project4/images/left_bg.gif) -191px 0 repeat-y;
	line-height: 22px;
	padding-left: 20px;
}

.nav dl.open dt {
	background-position: 0 0;
}

.nav dl dt {
	background: url(/project4/images/ico.gif) no-repeat 0 -23px;
	padding-left: 32px;
}

.nav dl.open dd {
	display: block;
}

.nav dl dd {
	background: url(/project4/images/ico.gif) no-repeat 18px -51px;
	padding-left: 36px;
	display: none;
}

.action {
	float: right;
	width: 759px;
	padding-bottom: 69px;
	background: url(/project4/images/right.gif) no-repeat 0 bottom;
}

.copyright {
	text-align: center;
	font: 12px Arial;
	margin: 20px 0;
	color: #000;
}

#loginOut {
	color: #2357E7;
	margin: 0 10px;
}
</style>
</head>

<body>
	<div class="top">
		<div class="global-width">
			<img class="logo" src="/project4/images/logo.gif">
		</div>
	</div>
	<div id="vmList" class="status" id="JiaZaiZy">
		<div class="global-width">
			<span class="usertype">【登录角色：${user.positionName}】<a
				id="loginOut" href="JavaScript:void(0);">退出登录</a></span>
			${user.employeeName}你好！欢迎访问青鸟办公管理系统！

		</div>
	</div>
	<div class="main">
		<div class="global-width">
			<div class="nav" id="nav">
				<div class="t"></div>
				<dl>
					<dt
						onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">报销单管理</dt>
					<dd>
						<a target="rightFrame" href="xzbx">新增报销单</a>
					</dd>
					<dd>
						<a target="rightFrame" href="ckbx?type=1">查看报销单</a>
					</dd>
					<c:if test="${user.positionId!=2}">
						<dd>
							<a target="rightFrame" href="ckbx?type=2">审批报销单</a>
						</dd>
					</c:if>
				</dl>
				<dl>
					<dt
						onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">请假管理</dt>

					<dd>
						<a target="rightFrame" href="qj">请假</a>
					</dd>

					<dd>
						<a target="rightFrame" href="qjList">查看请假</a>

					</dd>
				</dl>
				<c:if test="${user.positionId==3}">
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">统计报表</dt>
						<dd>
							<a target="rightFrame" href="ydb">报销单月度统计</a>
						</dd>
						<dd>
							<a target="rightFrame" href="ndb">报销单年度统计</a>
						</dd>
					</dl>
				</c:if>
				<c:if test="${user.positionId==1}">
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">统计报表</dt>
						<dd>
							<a target="rightFrame" href="ydb">报销单月度统计</a>
						</dd>
						<dd>
							<a target="rightFrame" href="ndb">报销单年度统计</a>
						</dd>
					</dl>
				</c:if>
				<c:if test="${user.positionId==5}">
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">统计报表</dt>
						<dd>
							<a target="rightFrame" href="ydb">报销单月度统计</a>
						</dd>
						<dd>
							<a target="rightFrame" href="ndb">报销单年度统计</a>
						</dd>
					</dl>
				</c:if>
				<dl>
					<dt
						onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
						信息中心<span id="msgNum1" class="ii">4</span>
					</dt>
					<dd>
						<a target="rightFrame" href="">信心收件箱<span id="msgNum2"
							class="ii">4</span></a>
					</dd>
					<dd>
						<a target="rightFrame" href="">信心发件箱<span id="msgNum3"
							class="ii">4</span></a>
					</dd>
				</dl>
			</div>

			<div class="action">
				<!--  onload="setIframeHeight(this)"  scrolling="no"-->
				<iframe name="rightFrame" style="WIDTH: 100%; HEIGHT: 600px;"
					src="welcome" frameborder="0" id="iframepage"></iframe>
			</div>
		</div>
	</div>
	<div class="copyright">Copyright &nbsp; ? &nbsp; 北大青鸟</div>
</body>
<script type="text/javascript" src="/project4/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="/project4/js/vue.js"></script>
<script type="text/javascript" src="/project4/js/jq_ajax_config.js"></script>
<script type="text/javascript">
	$("#loginOut").click(function() {
		$.get("/project4/c/employee/loginOut", function(res) {
			if (res == "ok") {
				location.href = "index";
			}
		})
	});
	setInterval(autoplay,1000);
	function autoplay(){

	}
</script>
</html>