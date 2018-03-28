package wyx.practice.linkconverter.utils;

import org.apache.commons.lang3.RandomStringUtils;
import wyx.practice.linkconverter.config.CharacterSet;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
public class RandomKeyword {


    /**
     * 根据字符集和自定义长度生成短码
     * @param length
     * @param characterSet
     * @return
     */
    public static String randomKeyword(int length, CharacterSet characterSet){
        if(length<=2 || length>=10){
            length = 6;
        }
        if(characterSet==null || characterSet.equals(CharacterSet.ALPHA_NUM)){
            return RandomStringUtils.randomAlphanumeric(length);
        }
        if(characterSet.equals(CharacterSet.ALPHA)){
            return RandomStringUtils.randomAlphabetic(length);
        }
        if(characterSet.equals(CharacterSet.NUM)){
            return RandomStringUtils.randomNumeric(length);
        }
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
