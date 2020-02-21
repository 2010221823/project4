<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/project4/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="action  divaction">
		<div class="t">查看请假</div>
		<div class="pages">
			<!--增加报销单 区域 开始-->
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
				class="addform-base">
				<caption>基本信息</caption>
				<tbody>
					<tr>
						<td width="36%">姓名:${list.employeeName }</td>
						<td width="64%">部门：${list.departmentName }</td>
					</tr>
					<tr>
						<td>开始时间：<ftm:formatDate value="${list.startTime}"
								pattern="yyyy-MM-dd" type="date" />
						</td>
						<td>结束时间：<ftm:formatDate value="${list.endTime}"
								pattern="yyyy-MM-dd" type="date" />
						</td>
					</tr>
					<tr>
						<td>请假天数：${list.totalCount}</td>
						<td>请假事由：${list.event }</td>
					</tr>
					<tr>
						<td>审批状态：${list.statusName}</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<p>
				-------------------------------------------------------------------------------
			</p>
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
				<tbody>
					<c:forEach items="${list.list}" var="s">
						<tr>
							<td>${s.checkMan}</td>
							<td>${s.checkComment}</td>
							<td><ftm:formatDate value="${s.checkTime}"
									pattern="yyyy-MM-dd HH:mm:ss" type="date" /></td>
							<td style="font-weight: bold;">${s.checkResult}</td>
						</tr>
					</c:forEach>
				</tbody>

				<c:if test="${type==2}">
					<tr>
						<td>审批意见： <textarea name="checkComment" rows="5" cols="45"></textarea>
						</td>
					</tr>
					<tr>
						<c:if test="${user.positionId==4}">
							<td colspan="4" class="submit"><input type="button"
								value="存档" class="submit1 submit_01" data-type="1"></td>
						</c:if>
						<c:if test="${user.positionId==3}">
							<td colspan="4" class="submit"><input type="button"
								value="通过" class="submit1 submit_01" data-type="1"></td>
							<td colspan="4" class="submit"><input type="button"
								value="拒绝" class="submit1 submit_01" data-type="3"></td>
						</c:if>
						<c:if test="${user.positionId==1}">
							<td colspan="4" class="submit"><input type="button"
								value="通过" class="submit1 submit_01" data-type="1"></td>
							<td colspan="4" class="submit"><input type="button"
								value="拒绝" class="submit1 submit_01" data-type="3"></td>
						</c:if>
					</tr>
				</c:if>
				<!--表单提交行-->
				<tr>
					<td colspan="4" class="submit"><input type="button" value="返回"
						onclick="window.history.go(-1)" class="submit_01"></td>
				</tr>
			</table>
			<!--增加报销单 区域 结束-->
		</div>
	</div>
</body>
<script type="text/javascript" src="/project4/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(".submit1").click(function() {
		var positionId = "${user.positionId}";
		var checkResult = $(this).attr("data-type");
		var checkComment = $("[name=checkComment]").val();
		var checkMan = "${user.employeeId}";
		var bizId = "${list.leaveId}";
		$.post("/project4/c/leave/updateLeave", {
			"bizId" : bizId,
			"checkMan" : checkMan,
			"checkComment" : checkComment,
			"checkResult" : checkResult,
			"positionId" : positionId
		}, function(res) {
			if (res == "ok") {
				location.href ="/project4/qjList"; 
			}
		});
	});
</script>
</html>
