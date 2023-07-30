package com.zj.demoproxy.service;

import com.zj.demoproxy.annotation.Scan;
import com.zj.demoproxy.template.BaseTemplate;

@Scan("null")
public interface CkTestService extends BaseTemplate<String>, BaseInterface {
}
