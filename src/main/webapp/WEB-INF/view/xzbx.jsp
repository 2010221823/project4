<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
.img {
	cursor: pointer;
	width: 40px;
	height: 40px;
}
</style>

</head>
<body>
	<div class="action  divaction">
		<div class="t">报销单更新</div>
		<div class="pages">
			<form id="claimVoucher_updateClaimVoucher_action" name="claimForm"
				enctype="multipart/form-data" action="../jboa/page/addReim"
				method="post">
				<input type="hidden" name="statusId" value="1" id="status">
				<input type="hidden" id="rowNumber" name="rowNumber" value="0">
				<input type="hidden" id="claimId" name="claimVoucher.id" value="133">
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base">
					<caption>基本信息</caption>
					<tbody>
						<tr>
							<td>编&nbsp;&nbsp;号：<label id="reimburseId">${user.employeeId}</label></td>
							<td>填&nbsp;写&nbsp;人：${user.employeeName }</td>
							<td>部&nbsp;&nbsp;门：${user.departmentName }</td>
							<td>职&nbsp;&nbsp;位：${user.positionName }</td>
						</tr>
						<tr>
							<td>总金额： <input type="text" name="totalCount" value="0"
								readonly="readonly" id="totalAccount"></td>
						</tr>
						<tr>
							<td colspan="4"><p>-------------------------------------------------------------------------------</p></td>
						</tr>
					</tbody>
				</table>
				<table id="myTable" width="90%" border="0" cellspacing="0"
					cellpadding="0" class="addform-base">
					<thead>
						<tr>
							<td width="30%">项目说明</td>
							<td width="30%">项目金额</td>
							<td width="30%">凭据截图</td>
							<td width="10%">操作</td>
						</tr>
					</thead>
					<tbody id="box">

					</tbody>
				</table>
				<table id="detailTable" width="90%" border="0" cellspacing="0"
					cellpadding="0" class="addform-base">
					<tbody>
						<tr>
							<td width="30%"><input type="text" id="item"></td>
							<td width="30%"><input type="text"
								name="claimVoucherDetail.account" id="account"></td>
							<td width="30%"><input type="file" name="imgFile"
								id="imgFile"></td>
							<td width="10%"><img src="images/add.gif" width="16"
								height="16" id="AddRow"></td>
						</tr>
					</tbody>
				</table>
				<table>
					<tbody>
						<tr>
							<td>*事由：</td>
							<td colspan="3"><textarea name="event" cols="66" rows="5"
									id="event"></textarea></td>
						</tr>
						<tr align="center" colspan="4">
							<td>&nbsp;</td>
							<td><c:if test="${param.reimburseId==null}">
									<input type="button" id="1" data-status="1" value="保存"
										class="submit_01 submit_1">
								</c:if> <input type="button" id="2" data-status="2" value="提交"
								class="submit_01 submit_1"> <input type="button"
								value="返回" onclick="window.history.go(-1)" class="submit_01"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>

<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
base64Data1="";
var totalcount=0;
var reimburse_detail=[];//详表集合
var detail={};//详表对象
var reimburse={};//主表对象
var reimburseId='${param.reimburseId}';
var l=0;
/*如果报销id不为空就主详查询*/
if(reimburseId!=""){
	$.getJSON(`/project4/api/reimburses/reimburse/${"${reimburseId}"}`,function(res){
		totalcount=res.totalCount;
		$("#totalAccount").val(res.totalCount);
		$("[name=event]").val(res.event);
		$("#reimburseId").text(res.reimburseId);
		$(".submit_1").attr("data-status",res.statusId);
		$.each(res.dlist,function(i,obj){
			detail["des"]=obj.des;
			detail["subtotal"]=obj.subtotal;
			detail["pictruePath"]=obj.pictruePath;
			reimburse_detail.push(detail);
			let tr=$(`<tr><td width="30%">${"${obj.des}"}</td><td width="30%">${"${obj.subtotal}"}</td>
					<td width="30%"><img src='${"${obj.pictruePath}"}' width=50 height=50/></td>
					<td width="10%" data-i="${"${i}"}" class="del"><img src=/project4/images/delete.gif width=16 height=16/></td></tr>`);
		 $("#box").append(tr);
		 detail={};
		});
	});
}
/*根据报销id为不为空执行不同操作  */
$(".submit_1").click(function(){
	reimburse["departmentId"]='${user.departmentId}';
	reimburse["createMan"]='${user.employeeId }';   
	reimburse["positionId"] ='${user.positionId }'; 
	reimburse["totalCount"]=$("[name=totalCount]").val(); 
	reimburse["event"]=$.trim($("[name=event]").val());
	var statusId=$.trim($(this).attr("data-status"));
	if(statusId>2){
		reimburse["statusId"]=2;
	}else{
		reimburse["statusId"]=statusId;
	}
	reimburse["list"]=reimburse_detail;
	if(reimburseId==""){
	$.ajax("/project4/api/reimburses/reimburse", {
		type : "post",
		dataType : "json",
		contentType : "application/json",//非常重要
		//序列化
		data : JSON.stringify(reimburse), //json字符串
		success : function(res) {
			if(res=="ok"){
                 alert("提交成功");
                 location.href="ckbx?type=1";
             }
		}
	}); 
	}else{
		console.info(reimburse);
		reimburse["reimburseId"]=$("#reimburseId").text();
		$.ajax("/project4/api/reimburses/reimburse", {
			type : "put",
			dataType : "json",
			contentType : "application/json",//非常重要
			//序列化
			data : JSON.stringify(reimburse), //json字符串
			success : function(res) {
				if(res=="ok"){
	                 alert("提交成功");
	                 location.href="ckbx?type=1";
	             }
			}
		}); 
  }
});
$(document).on("click",".del",function(){
	var j=$(this).attr("data-i");
	delete reimburse_detail[j];
	var th=this;
	totalcount-=eval($.trim($(th).prev().prev().text()));
	$("#totalAccount").val(totalcount);
	$(th).parents("tr").remove();
});

$("#AddRow").click(function(){
		detail["des"]=$.trim($("#item").val());
		detail["subtotal"]=$.trim($("#account").val());
		detail["pictruePath"]=base64Data1;
		reimburse_detail.push(detail);
		totalcount+=eval($.trim($("#account").val()));
		$("#totalAccount").val(totalcount);
		$("#box").append(`<tr><td width="30%">${"${detail['des']}"}</td><td width="30%">${"${detail['subtotal']}"}</td>
				<td width="30%"><img src='${"${detail['pictruePath']}"}' width=50 height=50/></td>
				<td width="10%" data-i="${"${l}"}" class="del"><img src=/project4/images/delete.gif width=16 height=16/></td></tr>`);
		detail={};
		$("#item").val("");
		$("#account").val("");
		$("#imgFile").val("");
		l++;
	});
$(function() {
	//选择预览图片
	$("#imgFile").on(
			"change",
			function(e) {
				var fr = new FileReader();//读取文件
				var file = this.files[0];//选择第一个文件
				var _temp = file.name.match(/\.jpg|\.png|\.gif|\.bmp/i);
				if (!_temp) {
					this.value = "";
					alert("目前只支持jpg,png,bmp,gif格式图片文件");
					return false;
				} else if (file.size > (1024 * 1024)) {
					this.value = "";
					alert("目前只支持小于1M大小图片文件");
					return false;
				}
				fr.readAsDataURL(file);//读取文件
				//操作文件事件
				fr.onload = function() {
					base64Data1 = this.result;//获得base编码字符串格式
				};
			});
      });
</script>
</html>

