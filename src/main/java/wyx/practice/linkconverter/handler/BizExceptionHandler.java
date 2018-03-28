package wyx.practice.linkconverter.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wyx.practice.linkconverter.exception.BizException;
import wyx.practice.linkconverter.exception.ErrorInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-28
 */
@ControllerAdvice
public class BizExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, BizException e){
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setUrl(req.getRequestURI());
        return r;
    }
}
