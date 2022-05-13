package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.globalin.service.SampleTxService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleTxServiceTest {
	
	@Autowired
	private SampleTxService service;
	
	
	@Test
	public void testAddData() {
		
		String data = "그대 기억이 지난 사랑이\r\n 내 안을 파고 드는 가시가 되어/r/n 제발 가라고 아주 가라고/r/n 애써도 나를 괴롭히는데" ;
		
		System.out.println(data.getBytes().length);
		service.addData(data);
		
		
		
		
		
	}
}
