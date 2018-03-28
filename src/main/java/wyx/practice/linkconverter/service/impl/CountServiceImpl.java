package wyx.practice.linkconverter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyx.practice.linkconverter.entity.dto.AccessDao;
import wyx.practice.linkconverter.repository.AccessRepository;
import wyx.practice.linkconverter.repository.ConverterRepository;
import wyx.practice.linkconverter.service.CountService;

import java.time.LocalDateTime;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
@Service
public class CountServiceImpl implements CountService{

    private final AccessRepository accessRepository;

    @Autowired
    public CountServiceImpl(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Override
    public int count(int id) {
        return this.accessRepository.updateAccessTimes(id);
    }

    @Override
    public AccessDao saveUrlAccess(int id) {
        AccessDao dao = new AccessDao();
        dao.setUrlId(id);
        dao.setAccessTimes(0);
        dao.setCreatedTime(LocalDateTime.now());
        dao.setUpdatedTime(LocalDateTime.now());
        dao = this.accessRepository.save(dao);
        return dao;
    }
}
