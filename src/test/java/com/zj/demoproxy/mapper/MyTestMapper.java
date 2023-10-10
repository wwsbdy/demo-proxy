package com.zj.demoproxy.mapper;

import com.zj.dmsproxy.annotation.DmsScan;
import com.zj.dmsproxy.entity.DmsTestDo;
import com.zj.dmsproxy.interfaces.DmsBaseMapper;

@DmsScan(url = "my-test", infoName = "ENTERPRISE", value = "111")
//@CustomScan("enterprise")
//@Scan("111")
public interface MyTestMapper extends DmsBaseMapper<DmsTestDo> {
}
