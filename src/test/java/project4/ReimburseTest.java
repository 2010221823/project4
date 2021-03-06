package project4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accp.project4.biz.RdetailBIz;
import com.accp.project4.biz.ReimburseBiz;
import com.accp.project4.pojo.reimburse_detail;
import com.accp.project4.pojo.tb_reimburse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-ctx.xml" })
public class ReimburseTest {
	@Resource
	private ReimburseBiz biz;
	@Resource
	private RdetailBIz rbiz;

	@Test
	public void query3() {
		System.err.println(1900+new Date().getYear());
		System.err.println(new Date().getMonth()+1);
	}
	@Test
	public void query2() {
		 System.out.println(JSON.toJSON(biz.findByPage(1, 1002, 2, null, null, null,5,null)));
	};

	/*
	 * @Test public void insert() { List<reimburse_detail> list = new
	 * ArrayList<reimburse_detail>(); list.add(new reimburse_detail(null, null,
	 * 20.0f, "车费", null,
	 * "data:image/jpeg;base64,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\r\n"
	 * + "")); tb_reimburse r = new tb_reimburse(null, null, 1, new Date(), 1, 2,
	 * "afsdfsd", 32.0f, 1, list); int i = biz.addReimburse(r); if (i > 0) {
	 * System.out.println(r.getReimburseId()); } }
	 * 
	 * @Test public void insert2() { List<reimburse_detail> list = new
	 * ArrayList<reimburse_detail>(); list.add(new reimburse_detail(null, null,
	 * 20.0f, "车费", null,
	 * "data:image/jpeg;base64,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\r\n"
	 * + "")); tb_reimburse r = new tb_reimburse(null, null, 1, new Date(), 1, 2,
	 * "afsdfsd", 32.0f, 1, list); int i = biz.addReimburse(r); if (i > 0) { for
	 * (reimburse_detail detail : r.getList()) {
	 * detail.setMainId(r.getReimburseId()); rbiz.addRdetail(detail); } } }
	 */
}
