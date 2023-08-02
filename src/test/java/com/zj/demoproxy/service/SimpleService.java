package com.zj.demoproxy.service;

import com.zj.demoproxy.annotation.Scan;
import com.zj.demoproxy.annotation.Sub;

@Scan
public interface SimpleService {

    @Sub
    String subs(int a, int b, int c);
}
