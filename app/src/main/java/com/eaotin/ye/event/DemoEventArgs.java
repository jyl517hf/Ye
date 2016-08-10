package com.eaotin.ye.event;

import com.eaotin.framework.event.EventArgs;

/**
 * DemoEventArgs.java是Demo的事件参数类,需要继承事件参数基类EventArgs。
 *
 * @author 金玉龙
 * @version 2.0 16/8/10
 * @modify
 */
public class DemoEventArgs extends EventArgs {
    /**
     *结果码
     */
    private int resultCode;
    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 构造器
     * @param errorCode 结果吗
     * @param publicKey 公钥
     */
    public DemoEventArgs(int errorCode, String publicKey) {
        this.resultCode = errorCode;
        this.publicKey = publicKey;
    }

    /**
     * 获取结果码的getter
     * @return resultCode
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * 获取公钥的getter
     * @return publicKey
     */
    public String getPublicKey() {
        return publicKey;
    }
}