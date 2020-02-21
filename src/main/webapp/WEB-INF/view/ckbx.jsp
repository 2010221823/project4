<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/project4/css/style.css" rel="stylesheet" type="text/css">
<link href="/project4/js/My97DatePicker/SKIN/WdatePicker.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div class="action  divaction">
		<div class="t">报销单列表</div>
		<div class="pages">
			<div class="forms">
				<form id="myForm" name="queryForm"
					action="../jboa/page/findList?pageNum=1&pageSize=10" method="get">
					<label>报销单状态</label> <select name="statusId" id="statusId">
						<option value="0">全部</option>
						<option value="1">新创建</option>
						<option value="2">待审批</option>
						<option value="3">已审批</option>
						<option value="5">审批中</option>
						<option value="4">拒绝</option>
						<option value="8">已付款</option>
						<option value="7">已打回</option>
						<option value="6">已存档</option>
					</select> <label for="time">开始时间</label> <input type="text" name="sTime"
						value="" id="startDate" class="nwinput"> <label
						for="end-time">结束时间</label> <input type="text" name="eTime"
						value="" id="endDate" class="nwinput"> <input
						type="button" id="claimVoucher_searchClaimVoucher_action_0"
						value="查询" class="submit_01 submit1">
				</form>
			</div>
			<!--增加报销单 区域 开始-->
			<form id="claimVoucher_searchClaimVoucher_action"
				name="claimVoucherForm"
				action="jsp/claim/claimVoucher_searchClaimVoucher.action"
				method="post">
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="list items">
					<thead>
						<tr class="even">
							<td>编号</td>
							<td>填报日期</td>
							<td>填报人</td>
							<td>总金额</td>
							<td>状态</td>
							<td>待处理人</td>
							<td width=20%>操作</td>
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
			</form>
			<!--增加报销单 区域 结束-->
		</div>
	</div>
</body>
<script type="text/javascript" src="/project4/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="/project4/js/common.js"></script>
<script type="text/javascript"
	src="/project4/js/My97DatePicker/WdatePicker.js"></script>
<script>
	var type = "${param.type}";
	var employeeId="${user.employeeId}";
	var positionId="${user.positionId}";
	var departmentId="${user.departmentId}";
	query(employeeId,type,1,null,null,null,positionId,departmentId);
	$(".submit1").click(function(){
          var status=$("#statusId").val();
          var startTime=$("#startDate").val();
          var endTime=$("#endDate").val();
          if(status==0){
        	  query(employeeId,type,1,null,startTime,endTime,positionId,departmentId)
           }else{
              query(employeeId,type,1,status,startTime,endTime,positionId,departmentId)
           }
	});
	$(".page").click(function(){
        var pageNum=$(this).attr("data-page");
        if(pageNum!=0){
        	query(employeeId,type,pageNum,null,null,null,positionId,departmentId);
         }
	});
	var reimburse={};
	$(document).on("click",".update",function(){
		if(confirm("确定提交吗？[提交后不能修改]")){
		reimburse["reimburseId"]=$(this).attr("data-id");
		reimburse["statusId"]=2;
		$.ajax("/project4/api/reimburses/reimburse", {
			type : "put",
			dataType : "json",
		contentType : "application/json",//非常重要
			//序列化
			data : JSON.stringify(reimburse), //json字符串 
			success : function(res) {
				if (res == "ok") {
					alert("提交成功");
					query(employeeId,type,1,null,null,null,positionId,departmentId);
				}
			}
		});
		}
	});
	$(document).on("click",".del",function(){
		if(confirm("确定删除吗？")){
			var th=this;
			var reimburseId=$(this).attr("data-id");
			$.ajax(`/project4/api/reimburses/reimburse/${"${reimburseId}"}`, {
				type : "delete",
				dataType : "json", 
				success : function(res) {
					if (res == "ok") {
						alert("删除成功");
						$(th).parents("tr").remove();
					}
				}
			});
			}
		});
	function query(employeeId,type,pageNum,status,startTime,endTime,positionId,departmentId) {
		$.getJSON(`/project4/api/reimburses/reimburse/${"${employeeId}"}/${"${type}"}/${"${pageNum}"}/${"${status}"}/${"${startTime}"}/${"${endTime}"}/${"${positionId}"}/${"${departmentId}"}`,
				function(res) {
			        $("tbody").empty();
					$("#first").attr("data-page",res.firstPage);
					$("#last").attr("data-page",res.prePage);
					$("#prev").attr("data-page",res.nextPage);
					$("#next").attr("data-page",res.lastPage);
					$("#pages").html("第"+res.pageNum+"页/共"+res.pages+"页&nbsp;&nbsp;共"+res.total+"条记录");
					var statusId=null;
					var reimburseId=null;
					var j=null;
					$.each(res.list, function(i, obj) {
						statusId=obj.statusId;
						j=i;
						let tr=$(`<tr><td><a href="javascript:void(0)">${"${obj.reimburseId}"}</a></td>
								<td>${"${obj.createTime}"}</td>
								<td>${"${obj.employeeName}"}</td>
								<td>${"${obj.totalCount}"}</td>
								<td>${"${obj.statusName}"}</td>
								<td>${"${obj.nextDealMans}"}</td>
								<td>
								<a target="rightFrame" href='JavaScript:void(0);'data-id="${"${obj.reimburseId}"}"id="update${"${i}"}" class="update">
								<a target='rightFrame' href="ckbx2?reimburseId=${"${obj.reimburseId}"}&type=1">
								<img src='/project4/images/search.gif' title='查看详情' width='16' height='16'></a>
								<a target="rightFrame" href="xzbx?reimburseId=${"${obj.reimburseId}"}" id="query${"${i}"}">
								<a target="rightFrame" href='JavaScript:void(0);'data-id="${"${obj.reimburseId}"}"id="del${"${i}"}" class='del'>
								<a href="ckbx2?reimburseId=${"${obj.reimburseId}"}&type=2" id="aa${"${i}"}"> 
								</a>
								</td></tr>`);
						$("tbody").append(tr);
					 	if(statusId==7){
					 		if(employeeId==obj.nextDealMan){
							$("#query"+j).append(`<img src="/project4/images/edit.gif" title="编辑" width="16" height="16">`);
					 		}
						      }else 
					    if(statusId==1){
					    	$("#query"+j).append(`<img src="/project4/images/edit.gif" title="编辑" width="16" height="16">`);
							$("#update"+j).append(`<img src="/project4/images/save.gif" title="提交" width="16" height="16">`);					
							$("#del"+j).append(`<img src="/project4/images/delete.gif" title="删除" width="16" height="16">`);
						   } 
					var positionId="${user.positionId}";
					if(positionId!=2){
					 	if(employeeId==obj.nextDealMan){
							$("#aa"+j).append("<img src='/project4/images/sub.gif' width='16' height='15'>");
						}
					}
					});
				});
	}
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
</html>
