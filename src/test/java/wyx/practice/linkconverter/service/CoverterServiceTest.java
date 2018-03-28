package wyx.practice.linkconverter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CoverterServiceTest {
    @Autowired
    ConverterService converterService;

    @Test
    public void testAdd(){
        String url = "www.baidu.com";
        converterService.generateUrlCode(url);
    }
}
