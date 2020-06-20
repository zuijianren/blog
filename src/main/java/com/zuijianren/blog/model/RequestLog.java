package com.zuijianren.blog.model;

import java.util.Arrays;

/**
 * 请求的日志记录类
 */
public class RequestLog {
    public String url;
    private String ip;
    private String classMethod;
    private Object[] args;

    @Override
    public String toString() {
        return "ResultLog{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }

    public RequestLog(String url, String ip, String classMethod, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
    }
}
