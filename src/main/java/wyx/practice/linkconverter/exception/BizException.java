package wyx.practice.linkconverter.exception;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
public class BizException extends RuntimeException{
    private static final long serialVersionUID = -316475459998065098L;
    public static final String URL_EMPTY="url can't be empty.";
    public static final String BUSY_SERVER="server is busy,please try later.";
    public static final String ILLEGAL_URL="url is invalid,please check you url.Make sure that you url like 'http(s)://xxx/xxx'";
    public static final String INVALID_ARGS="invalid args";
    public static final String DUPLICATED_URL="duplicated url";
    public static final String DUPLICATED_KEYWORD="duplicated keyword";
    public BizException(String message) {
        super(message);
    }
}
