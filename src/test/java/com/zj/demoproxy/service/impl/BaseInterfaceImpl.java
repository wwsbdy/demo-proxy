package com.zj.demoproxy.service.impl;

import com.zj.demoproxy.service.BaseInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseInterfaceImpl implements BaseInterface {
    @Value("${zj.name}")
    private String value;


    @Override
    public String getValue() {
        return value;
    }
}
