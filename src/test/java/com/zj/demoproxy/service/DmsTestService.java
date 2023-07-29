package com.zj.demoproxy.service;

import com.zj.demoproxy.annotation.Scan;
import com.zj.demoproxy.model.DmsTestDo;
import com.zj.demoproxy.template.BaseTemplate;

/**
 * @author 19242
 */
@Scan("dms.zj-demo")
public interface DmsTestService extends BaseTemplate<DmsTestDo> {
}
