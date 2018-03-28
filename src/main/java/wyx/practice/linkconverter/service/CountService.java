package wyx.practice.linkconverter.service;

import wyx.practice.linkconverter.entity.dto.AccessDao;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
public interface CountService {
    /**
     * @param id url id
     */
    int count(int id);

    /**
     * 创建url访问记录
     * @param id url id
     * @return
     */
    AccessDao saveUrlAccess(int id);
}
