package wyx.practice.linkconverter.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wyx.practice.linkconverter.config.UrlCodeProperties;
import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.entity.dto.UrlDao;
import wyx.practice.linkconverter.exception.BizException;
import wyx.practice.linkconverter.repository.ConverterRepository;
import wyx.practice.linkconverter.service.ConverterService;
import wyx.practice.linkconverter.utils.BeanUtils;

import java.time.LocalDateTime;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
@Service
public class ConverterServiceImpl implements ConverterService {

    private final ConverterRepository converterRepository;

    private final UrlCodeProperties urlCodeProperties;

    private static final Integer RETRY_TIMES=3;

    @Autowired
    public ConverterServiceImpl(ConverterRepository converterRepository, UrlCodeProperties urlCodeProperties) {
        this.converterRepository = converterRepository;
        this.urlCodeProperties = urlCodeProperties;
    }

    @Override
    @Cacheable(value = "urlEntity",key = "#urlCode")
    public UrlEntity findByUrlCode(String urlCode) {
        UrlDao data = this.converterRepository.findByKeywordAndDeleted(urlCode, 0);
        return BeanUtils.convert(data);
    }

    @Override
    public UrlEntity generateUrlCode(String url) {

        if(StringUtils.isBlank(url)){
           throw new BizException("url不能为空");
        }

        if(!url.startsWith("http://")){
            //TODO append http://
        }

        String keyword = RandomStringUtils.randomAlphanumeric(urlCodeProperties.getUrlCodeLength());
        int i = 0;
        //重试次数3次
        while(this.converterRepository.findByKeywordAndDeleted(keyword,0)!=null && i< RETRY_TIMES){
            keyword = RandomStringUtils.randomAlphanumeric(urlCodeProperties.getUrlCodeLength());
            i++;
        }
        if(i==RETRY_TIMES && this.converterRepository.findByKeywordAndDeleted(keyword,0)!=null){
            throw new BizException("服务繁忙,请稍后再试");
        }
        UrlDao dao = new UrlDao();
        dao.setUrl(url);
        dao.setType(0);
        dao.setKeyword(keyword);
        dao.setDeleted(0);
        dao.setCreatedTime(LocalDateTime.now());
        dao.setUpdatedTime(dao.getCreatedTime());
        this.converterRepository.save(dao);
        return null;
    }

    @Override
    public UrlEntity generateUrlCode(String url, String customKeyword) {
        return null;
    }
}
