package wyx.practice.linkconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wyx.practice.linkconverter.service.ConverterService;

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

    @Autowired
    public RedirectController(ConverterService converterService) {
        this.converterService = converterService;
    }


    @GetMapping("/{urlCode}")
    public void parseUrl(@PathVariable String urlCode, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        StringBuilder sb = new StringBuilder(HTTP_PROTOCOL);

        response.setHeader(HEADER_LOCATION,"http://www.baidu.com");
        response.setHeader(HEADER_CONNECTION,CONNECTION_STATUS_CLOSE);

    }
}
