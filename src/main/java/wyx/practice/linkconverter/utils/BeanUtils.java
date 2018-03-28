package wyx.practice.linkconverter.utils;

import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.entity.dto.UrlDao;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
public class BeanUtils {

    public static UrlEntity convert(UrlDao source){
        if(source!=null){
            UrlEntity entity = new UrlEntity();
            entity.setKeyword(source.getKeyword());
            entity.setUrl(source.getUrl());
            entity.setId(source.getId());
            return entity;
        }else{
            return null;
        }
    }
}
