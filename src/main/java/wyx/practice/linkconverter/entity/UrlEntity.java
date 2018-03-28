package wyx.practice.linkconverter.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
public class UrlEntity implements Serializable{

    private static final long serialVersionUID = -6124458321245547331L;
    /**
     * 短码
     */
    private String keyword;
    /**
     * 链接真实地址
     */
    private String url;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
