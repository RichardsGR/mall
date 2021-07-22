package com.gr.mallthirdparty;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
class MallThirdPartyApplicationTests {


    @Autowired
    private OSSClient oss;

    @Test
    public void upload() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("\u202AC:\\Users\\13496\\Pictures\\2.png");
        oss.putObject("gr-mall","test",inputStream);
        System.out.println("上传成功。。。");
    }

}
