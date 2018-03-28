package wyx.practice.linkconverter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
@ConfigurationProperties("link.converter")
public class UrlCodeProperties {

    private CharacterSet characterSet = CharacterSet.ALPHA_NUM;
    private Integer urlCodeLength;

    public CharacterSet getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(CharacterSet characterSet) {
        this.characterSet = characterSet;
    }

    public Integer getUrlCodeLength() {
        return urlCodeLength;
    }

    public void setUrlCodeLength(Integer urlCodeLength) {
        this.urlCodeLength = urlCodeLength;
    }


}
