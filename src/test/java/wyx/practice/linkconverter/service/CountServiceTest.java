package wyx.practice.linkconverter.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.support.DaoSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wyx.practice.linkconverter.entity.dto.AccessDao;
import wyx.practice.linkconverter.repository.AccessRepository;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CountServiceTest {

    @Autowired
    private AccessRepository accessRepository;

    @Test
    public void testAdd(){
        AccessDao dao = new AccessDao();
        dao.setUrlId(1);
        dao.setCreatedTime(LocalDateTime.now());
        dao.setAccessTimes(1);
        accessRepository.save(dao);
        dao = this.accessRepository.findByUrlId(1);
        assertNotNull("add failed",dao);
    }

    @Test
    public void testCountPlus(){
        int i = accessRepository.updateAccessTimes(1);
        assertTrue(i==1);
    }
}
