package wyx.practice.linkconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import wyx.practice.linkconverter.config.UrlCodeProperties;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
@SpringBootApplication
@EnableConfigurationProperties(UrlCodeProperties.class)
@EnableCaching
public class LinkConverterApplication {
    public static void main(String[] args){
        SpringApplication.run(LinkConverterApplication.class,args);
    }
}

