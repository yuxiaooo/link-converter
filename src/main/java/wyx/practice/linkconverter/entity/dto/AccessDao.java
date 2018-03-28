package wyx.practice.linkconverter.entity.dto;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
@Entity(name = "t_url_access_times")
public class AccessDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pkId;
    @Column(name = "fk_uk_id")
    private Integer urlId;
    @Column(name = "access_times")
    private long accessTimes;
    private LocalDateTime updatedTime;
    private LocalDateTime createdTime;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public long getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(long accessTimes) {
        this.accessTimes = accessTimes;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
