package com.gr.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCouponApplication.class, args);
    }

}


/*
* 1）、命名空间：配置隔离
*     默认  public(保留空间);默认新增所有的配置都在public
*     1、开发，测试，生产环境。在boostrap.properties中可以选择环境使用不同的配置文件
*     2、每一个微服务中隔离配置，每一个微服务都可以创建自己的命名空间，只加载自己的配置
* 2）、配置集：所有的配置集合
* 3）、配置集id: 类似于文件名
* 4）、配置分组：
*       默认所有的配置集都输入default_group
* */
