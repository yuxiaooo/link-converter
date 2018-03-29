package wyx.practice.linkconverter.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wyx.practice.linkconverter.entity.SimpleResponse;
import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.exception.BizException;
import wyx.practice.linkconverter.service.ConverterService;
import wyx.practice.linkconverter.utils.Validator;

import javax.servlet.http.HttpServletResponse;

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
    public SimpleResponse<UrlEntity> convert2ShortLink(@RequestParam("link")
                                                       @ApiParam("待转换长链接")
                                                               String link, HttpServletResponse response) {
        if (!Validator.isUrl(link)) {
            throw new BizException(BizException.ILLEGAL_URL);
        }

        UrlEntity entity = converterService.generateUrlCode(link);
        return new SimpleResponse<>(entity);


    }

    @PostMapping("/custom/keyword")
    public SimpleResponse<UrlEntity> customKeyWord(@RequestParam("link") @ApiParam("待转换长链接") String link
            , @RequestParam("keyword") @ApiParam("自定义的短链接") String keyword) {
        if (!Validator.isUrl(link) || !Validator.isAlphaNumber(keyword)) {
            throw new BizException(BizException.INVALID_ARGS);
        }
        return new SimpleResponse<>(converterService.generateUrlCode(link, keyword));
    }

}
