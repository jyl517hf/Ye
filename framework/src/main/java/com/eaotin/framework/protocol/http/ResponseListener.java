package com.eaotin.framework.protocol.http;

/**
 * ResponseListener.java是Http响应监听接口
 *
 * @author 金玉龙
 * @version 2.0 16/8/2
 * @modify
 */
public interface ResponseListener {
    void onResponse(String requestId);
}
