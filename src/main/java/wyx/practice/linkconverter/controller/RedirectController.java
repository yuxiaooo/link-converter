package wyx.practice.linkconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.service.ConverterService;
import wyx.practice.linkconverter.service.CountService;
import wyx.practice.linkconverter.utils.Validator;

import javax.servlet.http.HttpServletResponse;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
@Controller
public class RedirectController {

    private static final String HTTP_PROTOCOL = "http";
    private static final String HEADER_LOCATION="Location";
    private static final String HEADER_CONNECTION="Connection";
    private static final String CONNECTION_STATUS_CLOSE="close";

    private final ConverterService converterService;
    private final CountService countService;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    public RedirectController(ConverterService converterService, CountService countService, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.converterService = converterService;
        this.countService = countService;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }


    @GetMapping("/{urlCode}")
    public void parseUrl(@PathVariable String urlCode, HttpServletResponse response){
        if(!Validator.isAlphaNumber(urlCode)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        UrlEntity urlEntity = converterService.findByUrlCode(urlCode);

        if(urlEntity != null){
            threadPoolTaskExecutor.submit(()->{
               countService.count(urlEntity.getId());
            });
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader(HEADER_LOCATION,urlEntity.getUrl());
            response.setHeader(HEADER_CONNECTION,CONNECTION_STATUS_CLOSE);
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }


    }
}
