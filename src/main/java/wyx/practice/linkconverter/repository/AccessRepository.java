package wyx.practice.linkconverter.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import wyx.practice.linkconverter.entity.dto.AccessDao;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
public interface AccessRepository extends CrudRepository<AccessDao, Integer> {
    /**
     * 增加计数
     * @param urlId
     * @return
     */
    @Modifying
    @Transactional
    @Query("update t_url_access_times set access_times=access_times+1 where fk_uk_id= ?1")
    int updateAccessTimes(Integer urlId);

    /**
     * @param urlId
     * @return
     */
    @Cacheable(value = "accessDao",key = "#urlId")
    AccessDao findByUrlId(Integer urlId);
}
