package com.gr.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 远程调用的步骤
 * 1、引入openfeign
 * 2、编写接口，告诉cloud此接口需要远程调用
 * 3、
 *
 */
@EnableFeignClients(basePackages = "com.gr.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class MallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMemberApplication.class, args);
    }

}
