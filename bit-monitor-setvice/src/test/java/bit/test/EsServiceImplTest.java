package bit.test;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.bit.monitor.action.EsServiceImpl;
import com.bit.monitor.action.MonitorLog;
import com.bit.monitor.action.StatisticsVo;

public class EsServiceImplTest extends BasiceTest {
	
	@Test
	public void queryTest(){
		EsServiceImpl service = getBean(EsServiceImpl.class);
		List<MonitorLog> list = service.queryLog(1l, null, null);
		System.out.println(list.size());
	}
	

	@Test
	public void getStatisticsTest(){
		EsServiceImpl service = getBean(EsServiceImpl.class);
		List<StatisticsVo> list = service.getStatistics(null, null, "bit.test.service.UserServiceImpl");
		System.out.println(JSON.toJSONString(list));
	}
}
