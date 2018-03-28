package wyx.practice.linkconverter.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wyx.practice.linkconverter.config.UrlCodeProperties;
import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.entity.dto.UrlDao;
import wyx.practice.linkconverter.exception.BizException;
import wyx.practice.linkconverter.repository.ConverterRepository;
import wyx.practice.linkconverter.service.ConverterService;
import wyx.practice.linkconverter.service.CountService;
import wyx.practice.linkconverter.utils.BeanUtils;
import wyx.practice.linkconverter.utils.RandomKeyword;
import wyx.practice.linkconverter.utils.Validator;

import java.time.LocalDateTime;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
@Service
public class ConverterServiceImpl implements ConverterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterServiceImpl.class);

    private final ConverterRepository converterRepository;

    private final UrlCodeProperties urlCodeProperties;

    private static final Integer RETRY_TIMES=3;

    private final CountService countService;

    @Autowired
    public ConverterServiceImpl(ConverterRepository converterRepository, UrlCodeProperties urlCodeProperties, CountService countService) {
        this.converterRepository = converterRepository;
        this.urlCodeProperties = urlCodeProperties;
        this.countService = countService;
    }

    @Override
    @Cacheable(value = "urlEntity",key = "#urlCode.trim()")
    public UrlEntity findByUrlCode(String urlCode) throws BizException{

        if(StringUtils.isBlank(urlCode)){
            throw new  BizException("url can't be empty");
        }
        LOGGER.info("===== query url keyword: {} =====",urlCode);

        UrlDao data = this.converterRepository.findByKeywordAndDeleted(urlCode, 0);

        if(data!=null){
            LOGGER.info("===== keyword:{},url:{} =====",urlCode,data.getUrl());
        }else{
            LOGGER.info("there are no url matches keyword {}",urlCode);
        }

        return BeanUtils.convert(data);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,BizException.class})
    public UrlEntity generateUrlCode(String url) throws BizException{

        if(StringUtils.isBlank(url)){
           throw new BizException(BizException.URL_EMPTY);
        }
        if(!Validator.isUrl(url)){
            throw new BizException(BizException.ILLEGAL_URL);
        }

        String keyword = RandomKeyword.randomKeyword(urlCodeProperties.getUrlCodeLength(),urlCodeProperties.getCharacterSet());
        int i = 0;
        //重试次数3次
        while(this.converterRepository.findByKeywordAndDeleted(keyword,0)!=null && i< RETRY_TIMES){
            keyword = RandomStringUtils.randomAlphanumeric(urlCodeProperties.getUrlCodeLength());
            i++;
        }
        if(i==RETRY_TIMES && this.converterRepository.findByKeywordAndDeleted(keyword,0)!=null){
            LOGGER.error(BizException.BUSY_SERVER);
            throw new BizException(BizException.BUSY_SERVER);
        }
        UrlDao exsited = this.converterRepository.findByUrl(url);
        if(exsited!=null){
            LOGGER.error(BizException.DUPLICATED_KEYWORD);
            throw new BizException(BizException.DUPLICATED_URL);
        }
        UrlDao dao = generateUrlDao(url,keyword);
        dao = this.converterRepository.save(dao);

        //创建访问记录
        countService.saveUrlAccess(dao.getId());
        return BeanUtils.convert(dao);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,BizException.class})
    public UrlEntity generateUrlCode(String url, String customKeyword) throws BizException{
        if(StringUtils.isBlank(url)){
            throw new BizException(BizException.URL_EMPTY);
        }
        if(!Validator.isUrl(url)){
            throw new BizException(BizException.ILLEGAL_URL);
        }
        //查询重复url,keyword
        UrlDao dao = this.converterRepository.findByKeywordAndDeleted(customKeyword,0);
        if(dao!=null){
            throw new BizException(BizException.DUPLICATED_KEYWORD);
        }
        dao = this.converterRepository.findByUrl(url);
        if(dao!=null){
            throw new BizException(BizException.DUPLICATED_URL);
        }
        dao = generateUrlDao(url,customKeyword);
        dao = this.converterRepository.save(dao);
        countService.saveUrlAccess(dao.getId());
        return BeanUtils.convert(dao);
    }

    private UrlDao generateUrlDao(String url,String keyword){
        if(StringUtils.isBlank(url) || StringUtils.isBlank(keyword)){
            throw new BizException(BizException.INVALID_ARGS);
        }
        UrlDao dao = new UrlDao();
        dao.setUrl(url);
        dao.setType(0);
        dao.setKeyword(keyword);
        dao.setDeleted(0);
        dao.setCreatedTime(LocalDateTime.now());
        dao.setUpdatedTime(dao.getCreatedTime());
        return dao;
    }
}
