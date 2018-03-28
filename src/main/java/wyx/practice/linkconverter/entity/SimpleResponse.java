package wyx.practice.linkconverter.entity;

import java.io.Serializable;

/**
 * 神兽护佑,bug退散
 *
 * @author wuyuxiao created on 2018-3-27
 */
public class SimpleResponse<T> implements Serializable{

    private static final long serialVersionUID = 4267717561058620713L;
    private T result;

    public SimpleResponse(T result) {
        this.result = result;
    }

    public SimpleResponse() {
    }



    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
