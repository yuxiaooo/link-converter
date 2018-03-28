package wyx.practice.linkconverter.service;

import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.exception.BizException;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
public interface ConverterService {

    /**
     * @param urlCode
     * @return
     * @throws BizException
     */
    UrlEntity findByUrlCode(String urlCode) throws BizException;

    /**
     * 生成短码
     * @param url
     * @return 长链接及对应短码
     */
    UrlEntity generateUrlCode(String url) throws BizException;

    /**
     * @param url
     * @param customKeyword
     * @return
     */
    UrlEntity generateUrlCode(String url,String customKeyword);
}
