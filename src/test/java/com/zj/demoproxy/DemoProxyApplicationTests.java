package com.zj.demoproxy;

import com.zj.demoproxy.model.DmsTestDo;
import com.zj.demoproxy.service.DmsTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class DemoProxyApplicationTests {

    @Autowired
    private DmsTestService dmsTestService;

    @Test
    public void beanTest() {
        String dmsUrl = dmsTestService.getDmsUrl();
        Class<DmsTestDo> typeClz = dmsTestService.getTypeClz();
        List<DmsTestDo> list = dmsTestService.getList(2, () -> {
            DmsTestDo dmsTestDo = new DmsTestDo();
            dmsTestDo.setName("zjiddd");
            return dmsTestDo;
        });
        System.out.println(dmsUrl);
    }

    @Test
    public void customAnnotation() {
        dmsTestService.soutSome();
        System.out.println(dmsTestService.concat("aa", "bb"));
        System.out.println(dmsTestService.sub(4, 3, new BigDecimal(1.5)));
    }

}
