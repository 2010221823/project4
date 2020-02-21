package project4;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accp.project4.biz.LeaveBiz;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-ctx.xml" })
public class LeaveTest {
	@Resource
	private LeaveBiz biz;

	/**
	 * 根据不同职位查询请假
	 */
	@Test
	public void query2() {
		/*
		 * System.out.println(JSON.toJSON(biz.findLeaveBy(5, 2, 1005, 1, "2012-12-12",
		 * "2020-12-20").getList()));
		 * 
		 * System.out.println(JSON.toJSON(biz.findLeaveBy(5, 3, 1006, 1, null,
		 * null).getList()));
		 */
		System.out.println(JSON.toJSON(biz.findLeaveBy(4, 1, 1001, 1, null, null).getList()));

		/*
		 * System.out.println(JSON.toJSON(biz.findLeaveBy(2, 4, 1004, 1, null,
		 * null).getList()));
		 */
	}

	@Test
	public void query() {
		System.out.println(JSON.toJSON(biz.findByleaveId(1)));
	}
}
