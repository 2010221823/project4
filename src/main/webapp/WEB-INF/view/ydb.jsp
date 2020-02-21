<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/project4/css/style.css" rel="stylesheet" type="text/css">
<body>
	<div class="action  divaction">
		<div class="t">月度统计列表</div>
		<div class="pages">
			<form id="claimVoucherStatistics_getDeptStatisticsByMonth_action"
				name="queryForm"
				action="../jboa/statistics/monthList?pageNum=1&pageSize=10"
				method="get">
				<label for="time">年份</label> <select name="year"
					id="claimVoucherStatistics_getDeptStatisticsByMonth_action_year">
					<option value="null">无</option>
					<option value="2016">2016年</option>
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
					<option value="2020">2020年</option>
				</select> <label for="time">开始月份</label> <select name="startMonth"
					id="claimVoucherStatistics_getDeptStatisticsByMonth_action_startMonth">
					<option value="null">无</option>
					<option value="1">1月份</option>
					<option value="2">2月份</option>
					<option value="3">3月份</option>
					<option value="4">4月份</option>
					<option value="5">5月份</option>
					<option value="6">6月份</option>
					<option value="7">7月份</option>
					<option value="8">8月份</option>
					<option value="9">9月份</option>
					<option value="10">10月份</option>
					<option value="11">11月份</option>
					<option value="12">12月份</option>
				</select> <label for="end-time">结束月份</label> <select name="endMonth"
					id="claimVoucherStatistics_getDeptStatisticsByMonth_action_endMonth">
					<option value="null">无</option>
					<option value="1">1月份</option>
					<option value="2">2月份</option>
					<option value="3">3月份</option>
					<option value="4">4月份</option>
					<option value="5">5月份</option>
					<option value="6">6月份</option>
					<option value="7">7月份</option>
					<option value="8">8月份</option>
					<option value="9">9月份</option>
					<option value="10">10月份</option>
					<option value="11">11月份</option>
					<option value="12">12月份</option>
				</select>
				<!-- <input type="hidden" name="year" value="2013"/> -->
				<input type="button"
					id="claimVoucherStatistics_getDeptStatisticsByMonth_action_0"
					value="查询" class="submit_01 submit1">
			</form>
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
				class="list items">
				<thead>
					<tr class="even">
						<td>编号</td>
						<td>总计</td>
						<td>年份</td>
						<td>月份</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7" align="center">
							<div class="page-bar">
								<a href="JavaScript:void(0);" class="page" id="first">首页</a>&nbsp;&nbsp;
								&nbsp;&nbsp; <a href="JavaScript:void(0);" class="page"
									id="prev">上一页</a> &nbsp;&nbsp; &nbsp;&nbsp; <a
									href="JavaScript:void(0);" class="page" id="next">下一页</a>
								&nbsp;&nbsp; <a href="JavaScript:void(0);" class="page"
									id="last">末页</a> &nbsp;&nbsp; &nbsp;&nbsp; <span id="pages">第1页/共3页&nbsp;&nbsp;共21条记录</span>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript" src="/project4/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="/project4/js/common.js"></script>
<script type="text/javascript">
var positionId = "${user.positionId}";
var departmentId = "${user.departmentId}";
    query(1,null,null,null,positionId,departmentId);
	$(".submit1").click(function(){
	    var year=$("[name=year]").val();
	    var startMonth=$("[name=startMonth]").val();
	    var endMonth=$("[name=endMonth]").val();
	   query(1,year,startMonth,endMonth,positionId,departmentId);
	});
	$(".page").click(function(){
	    var pageNum=$(this).attr("data-page");
	    if(pageNum!=0){
	    	query(pageNum,null,null,null,positionId,departmentId);
	     }
	});
	function query(pageNum,year,startMonth,endMonth,positionId,departmentId) {
		$.getJSON(`/project4/api/counts/count/${"${pageNum}"}/${"${year}"}/${"${startMonth}"}/${"${endMonth}"}/${"${positionId}"}/${"${departmentId}"}`,
				function(res) {
			 $("tbody").empty();
				$("#first").attr("data-page",res.firstPage);
				$("#last").attr("data-page",res.prePage);
				$("#prev").attr("data-page",res.nextPage);
				$("#next").attr("data-page",res.lastPage);
				$("#pages").html("第"+res.pageNum+"页/共"+res.pages+"页&nbsp;&nbsp;共"+res.total+"条记录");
			$.each(res.list,function(i,obj){
			let tr=$(`<tr><td>${"${obj.countId}"}</td><td>￥${"${obj.money}"}</td><td>${"${obj.year}"}年</td><td>${"${obj.month}"}月</td>
					<td><a target="rightFrame" href="ydbxq?year=${"${obj.year}"}&month=${"${obj.month}"}"> <img
							src="/project4/images/search.gif" width="16" height="15">
					</a></td>
				</tr>`);
			$("tbody").append(tr);
			});
		});
	}
</script>
</head>
</html>
