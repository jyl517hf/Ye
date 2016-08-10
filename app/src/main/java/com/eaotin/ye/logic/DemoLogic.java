package com.eaotin.ye.logic;

import com.eaotin.framework.base.BaseLogic;
import com.eaotin.framework.event.EventListener;
import com.eaotin.framework.http.ResponseListener;
import com.eaotin.ye.event.DemoEventArgs;
import com.eaotin.ye.protocol.DemoProtocol;

/**
 * DemoLogic.java是Demo的请求服务端的逻辑接口类,该类继承BaseLogic并且是单例的
 *
 * @author 金玉龙
 * @version 2.0 16/8/10
 * @modify
 */
public class DemoLogic extends BaseLogic {
    /**
     * 通过私有的构造方法,强化单例模式
     */
    private DemoLogic() {
    }

    private static final DemoLogic instance = new DemoLogic();

    public static DemoLogic getInstance() {
        return instance;
    }

    /**
     * 获取公钥的逻辑方法
     * @param listener 事件监听器,用于传递事件参数
     */
    public void getPublicKey(final EventListener listener) {
        final DemoProtocol protocol = new DemoProtocol();
        protocol.run(null, new ResponseListener() {
            @Override
            public void onResponse(String requestId) {
                DemoEventArgs demoEventArgs = new DemoEventArgs(protocol.getResultCode(), protocol.getPublicKey());
                fireEventWithArgs(listener, demoEventArgs);
            }
        });
    }

}
