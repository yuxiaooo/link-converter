package wyx.practice.linkconverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import wyx.practice.linkconverter.shortid.ShortId;
import wyx.practice.linkconverter.utils.Validator;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
public class UTLinkConverter {

    //测试随机字符串冲突
    @Test
    public void testRandomString() {
        Set<String> s = new HashSet<>(12000000);
//        String s1 = RandomStringUtils.randomAlphanumeric(10);
        for (int i = 0; i < 10000000; i++) {
            String random = ShortId.generate();
            int retryTime = 1;
            while(s.contains(random)){
                System.out.println("string " + random + " is duplicated");
                System.out.println("retry "+ retryTime++ +"...");
                random = ShortId.generate();
            }
            s.add(random);
        }
//        System.out.println(s1);
    }

    @Test
    public void testRandom(){
        ShortId.worker(1);
        ShortId.seed(3213124324L);
        System.out.println(ShortId.generate());
    }

    @Test
    public void testUrlMatches(){
        String url = "http://baidu.com/sfdsa/erqewrew";
        System.out.println(Validator.isUrl(url));
    }
}
