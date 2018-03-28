package wyx.practice.linkconverter.repository;

import org.springframework.data.repository.CrudRepository;
import wyx.practice.linkconverter.entity.dto.UrlDao;

import java.util.List;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
public interface ConverterRepository extends CrudRepository<UrlDao,Integer>{

    /**
     * 根据短码查询
     * @param keyword
     * @param deleted
     * @return
     */
    UrlDao findByKeywordAndDeleted(String keyword,Integer deleted);

}
