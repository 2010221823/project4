<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/project4/css/style.css" rel="stylesheet" type="text/css">
<style>
.img {
	width: 40px;
	height: 40px;
	cursor: pointer;
}

.weight {
	font-weight: bold;
}
</style>
</head>
<body>
	<form id="myForm" name="myForm" method="post">
		<div class="action  divaction">
			<div class="t">查看报销单</div>
			<div class="pages">
				<!--增加报销单 区域 开始-->
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base">
					<caption>基本信息</caption>
					<tbody>
						<tr>
							<td>编&nbsp;&nbsp;号：<label id="reimburseId"></label></td>
							<td>填&nbsp;写&nbsp;人：<label id="employeeName" ></label></td>
							<td>部&nbsp;&nbsp;门：<label id="departmentName"></label></td>
							<td>职&nbsp;&nbsp;位：<label id="positionName"></label></td>
						</tr>
						<tr>
							<td>总金额：<label id="totalCount"></label></td>
							<td>填报时间：<label id="createTime"></label></td>
							<td>状态：<label id="statusName"></label></td>
							<td>待处理人：<label id="nextDealMans"></label></td>
						</tr>
						<tr>
							<td colspan="4"><p>-------------------------------------------------------------------------------</p></td>
						</tr>
					</tbody>
				</table>
				<p>&nbsp;</p>
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base">
					<thead>
						<tr>
							<td>项目说明</td>
							<td>项目金额</td>
							<td>票据截图</td>
						</tr>
					</thead>
					<tbody id="box">
					</tbody>
				</table>
				<p>&nbsp;</p>
				<p>-------------------------------------------------------------------------------</p>
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base" style="margin: 20px 0;">
					<thead>
						<tr>
							<td width="10%">审查人</td>
							<td width="30%">审查意见</td>
							<td width="30%">审查时间</td>
							<td width="25%">审查结果</td>
						</tr>
					</thead>
					<tbody id="box1">

					</tbody>
				</table>
				<table style="margin-top: 15px;" width="90%" border="0"
					cellspacing="0" cellpadding="0" class="addform-base">
					<thead>
					</thead>
					<tbody>
					</tbody>
				</table>
				<c:if test="${param.type==2 }">
					<p>
						&nbsp;审批建议:
						<textarea name="checkComment" rows="5" cols="45"></textarea>
					</p>
					<p>&nbsp;</p>
					<p>
						&nbsp;<input type="button" value="通过" data-status="11"
							class="submit_01 status">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="打回" data-status="7"
							class="submit_01 status">
						<c:if test="${user.positionId==3}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="拒绝" data-status="4"
								class="submit_01 status">
						</c:if>
						<c:if test="${user.positionId==2}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="拒绝" data-status="4"
								class="submit_01 status">
						</c:if>
					</p>
				</c:if>
				<p>&nbsp;</p>
				<p>
					&nbsp;<input type="button" value="返回"
						onclick="window.history.go(-1)" class="submit_01">
				</p>
				<!--增加报销单 区域 结束-->
			</div>
		</div>
	</form>
</body>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
var reimburseId='${param.reimburseId}';
var positionId="${user.positionId}";
var reimburse={};
var check={};
$(".status").click(function(){
	reimburse["createMan"]=$("#employeeName").attr("data-id");
	reimburse["departmentId"]=$("#departmentName").attr("data-id");
	reimburse["reimburseId"]=$("#reimburseId").text();
	reimburse["statusId"]=$(this).attr("data-status");
	reimburse["totalCount"]=$("#totalCount").text(); 
	reimburse["positionId"]=positionId;
	check["checkMan"]="${user.employeeId}";
	check["checkComment"]=$("[name=checkComment]").val();
	reimburse["check"]=check;
	$.ajax("/project4/api/reimburses/reimburse", {
		type : "put",
		dataType : "json",
	    contentType : "application/json",//非常重要
		//序列化
		data : JSON.stringify(reimburse), //json字符串 
		success : function(res) {
			if (res == "ok") {
				alert("提交成功");
				reimburse={};
				location.href="ckbx?type=2";	
			}
		}
	});
});
/*如果报销id不为空就主详查询*/
if(reimburseId!=""){
	$.getJSON(`/project4/api/reimburses/reimburse/${"${reimburseId}"}`,function(res){
		$("#reimburseId").text(res.reimburseId);
		$("#employeeName").text(res.employeeName);
		$("#employeeName").attr("data-id",res.employeeId);
		$("#departmentName").text(res.departmentName);
		$("#departmentName").attr("data-id",res.departmentId);
		$("#positionName").text(res.positionName);
		$("#totalCount").text(res.totalCount);
		$("#createTime").text(res.createTime);
		$("#statusName").text(res.statusName);
		$("#nextDealMans").text(res.nextDealMans);
		$("[name=event]").val(res.event);
		$.each(res.dlist,function(i,obj){
			let tr=$(`<tr><td width="30%">${"${obj.des}"}</td><td width="30%">${"${obj.subtotal}"}</td>
					<td width="30%"><img src='${"${obj.pictruePath}"}' width=50 height=50/></td>
					<td width="10%"></tr>`);
			 $("#box").append(tr);
		});
		var checkResult=null;
		$.each(res.rlist,function(i,obj){
			if(obj.checkResult==1){
				    checkResult="通过";
               }else if(obj.checkResult==2){
					checkResult="打回";
			   }else if(obj.checkResult==3){
				     checkResult="拒绝";
				}
			let td=$(`<tr><td>${"${obj.employeeName}"}</td><td>${"${obj.checkComment}"}</td>
		       <td>${"${obj.checkTime}"}</td><td style="font-weight: bold;">${"${checkResult}"}</td></tr>`);
	     $("#box1").append(td);
		});
	});
}
</script>
</html>
