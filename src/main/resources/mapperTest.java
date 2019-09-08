import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinyi.dao.XinyiUserMapper;

public class mapperTest {
	@Autowired
	private XinyiUserMapper xinyiUserMapper;
	
	@Test
	void test() {
		xinyiUserMapper.selectByPrimaryKey(5);
	}
}
