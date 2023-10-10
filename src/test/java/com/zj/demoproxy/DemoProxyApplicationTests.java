package com.zj.demoproxy;

import com.zj.demoproxy.mapper.MyTestMapper;
import com.zj.dmsproxy.entity.DmsTestDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class DemoProxyApplicationTests {

    @Autowired
    private MyTestMapper myTestMapper;

    @Test
    public void beanTest() {
        Class<DmsTestDo> typeClz = myTestMapper.getTypeClz();
    }

}
