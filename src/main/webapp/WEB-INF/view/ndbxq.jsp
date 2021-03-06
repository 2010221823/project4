<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="action  divaction">
		<div class="t">年度统计详情</div>
		<div class="pages">
			<form id="compYearStatistics_getDetailExcel_action" name="queryForm"
				action="../jboa/statistics/yearExcel" method="get">
				<label for="time">年份:</label> 2018 <input type="hidden"
					name="currYear" value="2018"
					id="compYearStatistics_getDetailExcel_action_currYear"> <input
					type="submit" id="compYearStatistics_getDetailExcel_action_0"
					value="导出报表" class="submit_01">
			</form>
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
				class="list items">
				<thead>
					<tr class="even">

						<c:if test="${user.positionId!=3}">
							<td>部门编号</td>
							<td>部门</td>
						</c:if>
						<c:if test="${user.positionId==3}">
							<td>员工编号</td>
							<td>员工</td>
						</c:if>
						<td>报销总额</td>
						<td>年份</td>
					</tr>

				</thead>
				<tbody>


				</tbody>
				<tfoot>
					<tr>
						<td></td>
						<td bgcolor="yellow">总计</td>
						<td bgcolor="yellow" id="totalMoney">￥502883.0</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- <span style="display: none;"><iframe id="downloadIframe" src=""
				style="width: 0px; height: 0px;"></iframe></span>
		增加报销单 区域 结束 -->
	</div>
	<div id="echartsDom"
		style='width: 702px; heigth: 400px; background: #fff; clear: both; padding-top: 20px; padding-left: 40px;'>

	</div>
	<div style="width: 542px; background: #fff; padding-left: 200px;">
		<input type="button" class="submit_01" value="查看柱状图"
			onclick="initEcharts('bar')" /> <input type="button"
			class="submit_01" value="查看饼图" onclick="initEcharts('pie')" /> <input
			type="button" class="submit_01" value="查看曲线图"
			onclick="initEcharts('line')" />
	</div>

</body>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/echarts.js" charset="UTF-8"></script>
<script type="text/javascript">
var year = "${param.year}";
var month = 1;
var positionId = "${user.positionId}";
var departmentId = "${user.departmentId}";
var departmentName=null;
var type=2;
var totalMoney=null;
var data=[];
$.getJSON(`/project4/api/counts/count/${"${year}"}/${"${month}"}/${"${positionId}"}/${"${departmentId}"}/${"${type}"}`,
				function(res) {
	            $("tbody").empty();
	            var name=null;
	            var id=null;
	            $.each(res,function(i,obj){
	            	if(positionId==3){
	            		departmentName=obj.departmentName;
	            		name=obj.employeeName;
	            		id=obj.employeeId;
	            		 var da={};
	            		da["money"]=obj.money;
	            		da["name"]=obj.employeeName;
	        		}else{
	        			departmentName="公司";
	        			name=obj.departmentName;
	        			id=obj.departmentId;
	        			 var da={};
	        			da["money"]=obj.money;
	            		da["name"]=obj.departmentName;
	        			}
	            	data.push(da);
	            	totalMoney+=obj.money;
                let tr=$(`<tr>
						<td>${"${id}"}</td>
						<td>${"${name}"}</td>
						<td>${"${obj.money}"}</td>
						<td>${"${obj.year}"}年</td>
					</tr>`);
				$("#totalMoney").text(totalMoney);
               $("tbody").append(tr);
               initEcharts('pie');
	      });
	});
function initEcharts(type) {
	var xAxis = new Array();
	var legend = new Array();
	for (var i = 0; i < data.length; i++) {
		xAxis[i] = data[i].name;
		legend[i] = data[i].money;
	}
	var option = null;
	if (type == 'bar') {
		option = {
			title : {
				text : year + '年' + month + '月' + departmentName
						+ '月度报销统计图'
			},
			tooltip : {},
			legend : {
				data : [ '报销金额' ]
			},
			xAxis : {
				data : xAxis
			},
			yAxis : {},
			series : [ {
				name : '报销金额',
				type : 'bar',
				barWidth : 30,
				data : legend
			} ]
		};
	} else if (type == "pie") {
		option = {
			title : {
				text : year + '年' + month + '月' + departmentName
						+ '月度报销统计图'
			},
			tooltip : {},
			series : [ {
				name : '报销金额',
				type : 'pie',
				radius : '60%',
				label : {
					normal : {
						formatter : '{b}: ({d}%)',
						textStyle : {
							fontWeight : 'normal',
							fontSize : 15
						}
					}
				}
			} ],
			dataset : {
				source : data
			}
		};
	} else {
		option = {
			title : {
				text : year + '年' + month + '月' + departmentName
						+ '月度报销统计图'
			},
			tooltip : {},
			legend : {
				data : [ '报销金额' ]
			},
			xAxis : {
				data : xAxis
			},
			yAxis : {},
			series : [ {
				name : '报销金额',
				type : 'line',
				barWidth : 30,
				data : legend
			} ]
		};
	}
	var dom = document.getElementById('echartsDom');
	$("#echartsDom").height('400px');
	var myChart = echarts.init(dom);
	if (myChart != null && myChart != "" && myChart != undefined) {//关键
		myChart.dispose();
	}
	var myChart = echarts.init(dom);
	myChart.setOption(option);
}
</script>
</html>
