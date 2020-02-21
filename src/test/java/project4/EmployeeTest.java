package project4;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accp.project4.biz.EmployeeBiz;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-ctx.xml" })
public class EmployeeTest {
	@Resource
	private EmployeeBiz biz;

	/**
	 * 员工登录
	 */
	@Test
	public void queryLogin() {
		System.out.println(JSON.toJSON(biz.findLogin(1006, "1234")));
	}
	/**
	 * 查询上级
	 */
	@Test
	public void query1() {
		System.out.println(JSON.toJSON(biz.findByPositionId(3,5)));
	}
	
}
