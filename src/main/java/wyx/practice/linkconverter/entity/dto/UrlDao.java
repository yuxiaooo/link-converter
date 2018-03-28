package wyx.practice.linkconverter.entity.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
@Entity(name = "t_url_keyword")
public class UrlDao implements Serializable{
    private static final long serialVersionUID = 1879805867342465342L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 长链接
     */
    @Column
    private String url;
    /**
     * 短码
     */
    @Column
    private String keyword;
    /**
     * 系统: “0” 自定义: “1”
     */
    @Column
    private Integer type;
    /**
     * 0：未删除 1：删除
     */
    @Column
    private Integer deleted;
    /**
     * 创建时间
     */
    @Column
    private LocalDateTime createdTime;
    /**
     * 更新时间
     */
    @Column
    private LocalDateTime updatedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
