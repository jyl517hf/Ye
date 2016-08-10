package com.eaotin.ye.protocol;

import com.eaotin.framework.base.BaseProtocol;

import org.json.JSONObject;

/**
 * DemoProtocol.java是Demo的请求服务端的协议接口类
 *
 * @author 金玉龙
 * @version 2.0 16/8/10
 * @modify
 */
public class DemoProtocol extends BaseProtocol {

    private static final String url = "/common/get_public_key.html";

    private String publicKey;

    @Override
    protected String getRequestUrl() {
        return url;
    }

    @Override
    protected String getInfoParameter() {
        return null;
    }

    @Override
    protected void onResult(JSONObject result) {
        if (result != null) {
            publicKey = result.optString("public_key");
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    @Override
    protected String getFakeResult() {
        return null;
    }
}
