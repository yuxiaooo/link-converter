package wyx.practice.linkconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wyx.practice.linkconverter.entity.SimpleResponse;
import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.service.ConverterService;

import java.util.regex.Pattern;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
@RestController
public class KeywordController {



    private final
    ConverterService converterService;

    @Autowired
    public KeywordController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @PostMapping("/shorter/link")
    public SimpleResponse<UrlEntity> convert2ShortLink(@RequestParam("link") String link) {
        return new SimpleResponse<>(converterService.generateUrlCode(link));
    }

    @PostMapping("/custom/keyword")
    public SimpleResponse<UrlEntity> customKeyWord(@RequestParam("link") String link,@RequestParam("keyword") String keyword){
        return new SimpleResponse<>(converterService.generateUrlCode(link,keyword));
    }

}
