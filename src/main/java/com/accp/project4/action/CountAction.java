package com.accp.project4.action;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accp.project4.biz.CountBiz;
import com.accp.project4.pojo.tb_count;
import com.github.pagehelper.PageInfo;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@RestController
@RequestMapping("/api/counts")
public class CountAction {
	@Resource
	private CountBiz biz;

	@GetMapping("/count/{pageNum}/{year}/{startMonth}/{endMonth}/{positionId}/{departmentId}")
	public PageInfo<tb_count> queryByPage(@PathVariable Integer pageNum, @PathVariable String year,
			@PathVariable String startMonth, @PathVariable String endMonth, @PathVariable Integer positionId,
			@PathVariable Integer departmentId) {
		if (("null").equals(year)) {
			if (("null").equals(startMonth) && ("null").equals(endMonth)) {
				return biz.findByPage(pageNum, null, null, null, positionId, departmentId);
			} else {
				return biz.findByPage(pageNum, null, startMonth, endMonth, positionId, departmentId);
			}
		} else if (("null").equals(startMonth) && ("null").equals(endMonth)) {
			if (("null").equals(year)) {
				return biz.findByPage(pageNum, null, null, null, positionId, departmentId);
			} else {
				return biz.findByPage(pageNum, year, null, null, positionId, departmentId);
			}
		} else {
			return biz.findByPage(pageNum, year, startMonth, endMonth, positionId, departmentId);
		}
	}

	@GetMapping("/count/{startYear}/{endYear}/{positionId}/{departmentId}")
	public List<tb_count> queryByYear(@PathVariable Integer startYear, @PathVariable Integer endYear,
			@PathVariable Integer positionId, @PathVariable Integer departmentId) {
		if (startYear == 0 || endYear == 0) {
			return biz.findByYear(null, null, positionId, departmentId);
		} else {
			return biz.findByYear(startYear, endYear, positionId, departmentId);
		}
	}

	/**
	 * 查看详情
	 * 
	 * @param year
	 * @param month
	 * @param positionId
	 * @param departmentId
	 * @return
	 */
	@GetMapping("/count/{year}/{month}/{positionId}/{departmentId}/{type}")
	public List<tb_count> queryCountDetails(@PathVariable Integer year, @PathVariable Integer month,
			@PathVariable Integer positionId, @PathVariable Integer departmentId, @PathVariable Integer type) {
		return biz.findCountDetails(year, month, positionId, departmentId, type);
	}

	/**
	 * 
	 * 导出表格
	 * 
	 * @param year
	 * @param month
	 * @param positionId
	 * @param departmentId
	 * @param type
	 * @return
	 */
	@GetMapping("/count/{year}/{month}/{positionId}/{departmentId}/{type}/{tt}/{t}")
	public String getExcel(HttpSession session, @PathVariable Integer year, @PathVariable Integer month,
			@PathVariable Integer positionId, @PathVariable Integer departmentId, @PathVariable Integer type) {
		List<tb_count> data = biz.findCountDetails(year, month, positionId, departmentId, type);
		List<String[]> list = new ArrayList<String[]>();
		for (tb_count ex : data) {
			String[] str = new String[] { ex.getCountId().toString(), ex.getMoney().toString(), ex.getYear().toString(),
					ex.getMonth().toString(), ex.getEmployeeName(), ex.getDepartmentName() };
			list.add(str);
		}
		int count = this.reprotExcel(list, session, positionId);
		if (count > 0) {
			return "ok";
		} else {
			return "no";
		}
	}

	/**
	 * 创建表格
	 * 
	 * @param pageDataList
	 * @param session
	 * @return
	 */
	public Integer reprotExcel(List<String[]> pageDataList, HttpSession session, Integer positionId) {
		String realPath = session.getServletContext().getRealPath("/uploads");
		String fileName = "报销统计";
		try {
			WritableWorkbook wbook = Workbook.createWorkbook(new FileOutputStream(realPath + "/" + fileName + ".xls")); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet("导出数据", 0); // sheet名称
			WritableCellFormat cellFormatNumber = new WritableCellFormat();
			cellFormatNumber.setAlignment(Alignment.RIGHT);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK); // 定义格式、字体、粗体、斜体、下划线、颜色
			WritableCellFormat wcf = new WritableCellFormat(wf); // title单元格定义
			WritableCellFormat wcfc = new WritableCellFormat(); // 一般单元格定义
			WritableCellFormat wcfe = new WritableCellFormat(); // 一般单元格定义
			wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcfc.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			wcfc.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			wcfe.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			wsheet.setColumnView(0, 20);// 设置列宽
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 20);

			int rowIndex = 0;
			int columnIndex = 0;
			if (null != pageDataList) {
				// rowIndex++;
				columnIndex = 0;
				wsheet.setRowView(rowIndex, 500);// 设置标题行高
				wsheet.addCell(new Label(columnIndex++, rowIndex, fileName, wcf));
				wsheet.mergeCells(0, rowIndex, 5, rowIndex);// 合并标题所占单元格
				rowIndex++;
				columnIndex = 0;
				wsheet.setRowView(rowIndex, 380);// 设置项目名行高
				wsheet.addCell(new Label(columnIndex++, rowIndex, "编号", wcf));
				wsheet.addCell(new Label(columnIndex++, rowIndex, "报销金额", wcf));
				wsheet.addCell(new Label(columnIndex++, rowIndex, "年份", wcf));
				wsheet.addCell(new Label(columnIndex++, rowIndex, "月份", wcf));
				if (positionId != 3) {
					wsheet.addCell(new Label(columnIndex++, rowIndex, "部门", wcf));
				} else {
					wsheet.addCell(new Label(columnIndex++, rowIndex, "报销人", wcf));
				}
				// 开始行循环
				for (String[] array : pageDataList) { // 循环列
					rowIndex++;
					columnIndex = 0;
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[0], wcfe));
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[1], wcfe));
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[2], wcfe));
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[3], wcfe));
					if (positionId != 3) {
						wsheet.addCell(new Label(columnIndex++, rowIndex, array[5], wcfe));
					} else {
						wsheet.addCell(new Label(columnIndex++, rowIndex, array[4], wcfe));
					}
				}
				rowIndex++;
				columnIndex = 0;
			}

			wbook.write();
			if (wbook != null) {
				wbook.close();
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}
}
