<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/project4/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function submitSearch() {
		if (document.queryForm.endYear.value < document.queryForm.startYear.value) {
			alert("请输入正确的年份区间");
			return false;
		}
		document.queryForm.submit();

	}
</script>
</head>
<body>
	<div class="action  divaction">
		<div class="t">年度统计列表</div>
		<div class="pages">
			<form id="compYearStatistics_getList_action" name="queryForm"
				action="../jboa/statistics/yearList?pageNum=1&pageSize=10"
				method="get">
				<label for="time">开始年份</label> <select name="startYear"
					id="startYear" class="nwinput">
					<option value="0">无</option>
					<option value="2016">2016年</option>
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
					<option value="2020">2020年</option>
				</select> <label for="end-time">结束年份</label> <select name="endYear"
					id="endYear" class="nwinput">
					<option value="0">无</option>
					<option value="2016">2016年</option>
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
					<option value="2020">2020年</option>
				</select> <input type="button" value="提交" class="submit_01 submit1"
					onclick="submitSearch()">
			</form>
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
				class="list items">
				<thead>
					<tr class="even">
						<td>编号</td>
						<td>总计</td>
						<td>年份</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
var positionId = "${user.positionId}";
var departmentId = "${user.departmentId}";
	query(0, 0,positionId,departmentId);
	$(".submit1").click(function(){
	    var startYear=$("[name=startYear]").val();
	    var endYear=$("[name=endYear]").val();
	    query(startYear, endYear,positionId,departmentId);
	});
	function query(startYear, endYear,positionId,departmentId) {
		$.getJSON(
				`/project4/api/counts/count/${"${startYear}"}/${"${endYear}"}/${"${positionId}"}/${"${departmentId}"}`,
				function(res) {
					$.each(res, function(i, obj) {
						let tr=$(`<tr><td>${"${obj.countId}"}</td><td>￥${"${obj.money}"}</td>
								<td>${"${obj.year}"}年</td><td><a href="ndbxq?year=${"${obj.year}"}" target="rightFrame"> <img
										src="/project4/images/search.gif" width="16" height="15">
								</a></td>
							</tr>`);
						$("tbody").append(tr);
					});
				});
	}
</script>
</html>
