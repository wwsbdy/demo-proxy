package com.zj.demoproxy;

import com.zj.demoproxy.annotation.EnableAutoProxyInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 19242
 */
@SpringBootApplication
@EnableAutoProxyInterface
public class DemoProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProxyApplication.class, args);
    }

}
