package com.qmms;

import com.qmms.sevice.SysUserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootShiroApplicationTests {
    @Resource
    SysUserInfoService sysUserInfoService;
	@Test
	public void contextLoads() {
	}

    @Test
    public void testFind(){

    }

}
