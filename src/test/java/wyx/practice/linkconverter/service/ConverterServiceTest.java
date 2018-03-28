package wyx.practice.linkconverter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wyx.practice.linkconverter.entity.UrlEntity;
import wyx.practice.linkconverter.entity.dto.UrlDao;
import wyx.practice.linkconverter.exception.BizException;
import wyx.practice.linkconverter.repository.ConverterRepository;

import static org.junit.Assert.*;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ConverterServiceTest {
    private static final String TEST_URL="http://www.baidu.com";
    private static final String ERROR_URL="ws://www.baidu.com";
    @Autowired
    ConverterService converterService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ConverterRepository repository;
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testAdd(){
        String url = TEST_URL;
        converterService.generateUrlCode(url);
        UrlDao entity = this.repository.findByUrl(TEST_URL);
        assertNotNull("no result for this url",entity);
        assertTrue("no keyword generate for url", StringUtils.isNotBlank(entity.getKeyword()));
    }

    @Test
    public void testAddErrorUrl(){
        String url = ERROR_URL;
        thrown.expect(BizException.class);
        thrown.expectMessage("url is invalid,please check you url.");
        converterService.generateUrlCode(url);
        UrlDao entity = this.repository.findByUrl(ERROR_URL);
        assertNull("error url saved in database",entity);

    }


    @Test
    public void testQuery(){
        String url = TEST_URL;
        converterService.generateUrlCode(url);
        UrlDao dao = this.repository.findByUrl(TEST_URL);
        UrlEntity entity = this.converterService.findByUrlCode(dao.getKeyword());
        assertNotNull("no result for this url",entity);
    }


}
