<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/project4/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/project4/js/common.js"></script>
<script type="text/javascript" src="/project4/js/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="/project4/js/My97DatePicker/WdatePicker.js"></script>
<link href="/project4/js/My97DatePicker/SKIN/WdatePicker.css"
	rel="stylesheet" type="text/css">
<script>
	$(function() {
		//日期选择控件
		$("#startDate").click(function() {
			WdatePicker({
				dateFmt : 'yyyy-MM-dd',
				maxDate : '#F{$dp.$D(\'endDate\')}',
				isShowClear : true,
				readOnly : true
			});
		});
		$("#endDate").click(function() {
			WdatePicker({
				dateFmt : 'yyyy-MM-dd',
				minDate : '#F{$dp.$D(\'startDate\')}',
				isShowClear : true,
				readOnly : true
			});
		});
	});
</script>
</head>
<body>
	<div class="action  divaction">
		<div class="t">请假列表</div>
		<div class="pages">
			<div class="forms">
				<form id="leave_searchLeave_action" name="queryForm"
					action="ckqj.jsp" method="get">
					<label for="time">开始时间</label> <input type="text" name="sTime"
						value="" id="startDate" class="nwinput"> <label
						for="end-time">结束时间</label> <input type="text" name="eTime"
						value="" id="endDate" class="nwinput"> <input
						type="hidden" name="pageNo" value="1"> <input
						type="hidden" name="pageSize" value="5"> <input
						type="button" id="leave_searchLeave_action_0" value="查询"
						class="submit_01">

				</form>
			</div>
			<!--增加报销单 区域 开始-->
			<table id="vmLeave" width="90%" border="0" cellspacing="0"
				cellpadding="0" class="list items">
				<thead>
					<tr class="even">
						<td>编号</td>
						<td>名称</td>
						<td>发起时间</td>
						<td>审批时间</td>
						<td>审批意见</td>
						<td>待处理人</td>
						<td>审批状态</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8" align="center">
							<div class="page-bar">
								<a href="JavaScript:void(0);" class="page" id="first">首页</a>&nbsp;&nbsp;
								&nbsp;&nbsp; <a href="JavaScript:void(0);" class="page"
									id="prev">上一页</a> &nbsp;&nbsp; &nbsp;&nbsp; <a
									href="JavaScript:void(0);" class="page" id="next">下一页</a>
								&nbsp;&nbsp; <a href="JavaScript:void(0);" class="page"
									id="last">末页</a> &nbsp;&nbsp; &nbsp;&nbsp;
								<span id="pages">第1页/共3页&nbsp;&nbsp;共21条记录</span>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!--请假 区域 结束-->
		</div>
	</div>
</body>
<script type="text/javascript" src="/project4/js/vue.js"></script>
<script type="text/javascript" src="/project4/js/jq_ajax_config.js"></script>
<script type="text/javascript">
	var departmentId ="${user.departmentId}";
	var positionId = "${user.positionId}";
	var employeeId = "${user.employeeId}";
	query(departmentId, positionId, employeeId, null, null, 1);
	$(".submit_01").click(function() {
		var startTime = $("#startDate").val();
		var endTime = $("#endDate").val();
		query(departmentId, positionId, employeeId, startTime, endTime, 1);
	});
	$(".page").click(function(){
         var pageNum=$(this).attr("data-page");
         if(pageNum!=0){
        	 query(departmentId, positionId, employeeId, null, null,pageNum);
          }
	});
	function query(departmentId, positionId, employeeId, startTime, endTime,
			pageNum) {
		$.post("/project4/c/leave/qjlists", {
			"departmentId" : departmentId,
			"positionId" : positionId,
			"employeeId" : employeeId,
			"pageNum" : pageNum,
			"startTime" : startTime,
			"endTime" : endTime
		}, function(res) {
			$("tbody").empty();
			$("#first").attr("data-page",res.firstPage);
			$("#last").attr("data-page",res.prePage);
			$("#prev").attr("data-page",res.nextPage);
			$("#next").attr("data-page",res.lastPage);
			$("#pages").html("第"+res.pageNum+"页/共"+res.pages+"页&nbsp;&nbsp;共"+res.total+"条记录");
			var j=null;
			$.each(res.list, function(i, temp) {
				j=i;
                let tr=`<tr v-for="(temp,index) in pageinfo.list">
					<td><a href="javascript:void(0)">${"${temp.leaveId}"}</a></td>
					<td><span>${"${temp.employeeName}"}</span>请假<span>${"${temp.totalCount}"}</span>天</td>
					<td>${"${temp.createTime}"}</td>
					<td>${"${temp.checkTime==null?'':temp.checkTime}"}</td>
					<td>${"${temp.checkComment}"}</td>
					<td>${"${temp.nextDealMan==''?'无':temp.nextDealMan}"}</td>
					<td>${"${temp.statusName}"} <input id="checkId" type="hidden"
						value="temp.checkId" />
					</td>
					<td><a href="/project4/c/leave/qjxq?leaveId=${"${temp.leaveId}"}&type=1"> <img
							src="/project4/images/search.gif" width="16" height="15">
					</a> 
					<a href="/project4/c/leave/qjxq?leaveId=${"${temp.leaveId}"}&type=2" id="aa${"${i}"}"> 
						</a></td></tr>`;
				$("tbody").append(tr);
		if(employeeId==temp.nextDealMa){
				  $("#aa"+j).append("<img src='/project4/images/sub.gif' width='16' height='15'>");
				}
			});
		}, "json");
	}
	 /* var app3 = new Vue( {el: ' #app-3',data: {seen: true}}) */
</script>
</html>
