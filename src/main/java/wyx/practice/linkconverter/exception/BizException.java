package wyx.practice.linkconverter.exception;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
public class BizException extends RuntimeException{
    private static final long serialVersionUID = -316475459998065098L;

    public BizException(String message) {
        super(message);
    }
}
