package com.eaotin.framework.base;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.eaotin.framework.http.HttpComm;
import com.eaotin.framework.http.ResponseListener;
import com.eaotin.framework.util.LoggerUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * BaseProtocol.java是进行网络请求的protocol基类。
 *
 * @author 金玉龙
 * @version 2.0 16/8/2
 * @modify
 */
public abstract class BaseProtocol {

    private final String clazz = getClass().getSimpleName();
    private static final LoggerUtil logger = new LoggerUtil("protocol");

    /**
     * 获得请求地址
     */
    abstract protected String getRequestUrl();

    /**
     * 获得 请求时的info参数，派生类实现
     */
    abstract protected String getInfoParameter();

    /**
     * 通信完成，解析结果，派生类实现
     */
    abstract protected void onResult(JSONObject result);

    /**
     * 获得测试用假数据
     */
    abstract protected String getFakeResult();

    /**
     * 执行Http请求
     *
     * @param requestId 请求的id
     * @param listener  监听器
     */
    public void run(String requestId, ResponseListener listener) {
        new AsyncComm(requestId, listener).execute();
    }

    private class AsyncComm extends AsyncTask<Void, Void, Void> {
        /* 请求id */
        private String mRequestId = "";
        /* response的监听器 */
        private ResponseListener mListener = null;
        /* 请求参数 */
        private String parameter;

        /**
         * 构造方法
         *
         * @param requestId 请求id
         * @param listener  response的监听器
         */
        public AsyncComm(String requestId, ResponseListener listener) {
            mRequestId = requestId;
            mListener = listener;
        }

        @Override
        protected Void doInBackground(Void... params) {
//            //从相对路径拼为绝对路径
//            String url = Const.BASE_URL + getRequestUrl();
//            //加入TOKEN参数
//            User cacheUser = Cache.getInstance().getUser();
//            String token;
//            if (cacheUser == null) {
//                token = "";
//            } else {
//                token = cacheUser.getToken();
//            }
            String url = "www.baicu.com";
//            if (url.indexOf("?") == -1) {
//                url = url + "?t=" + token;
//            } else {
//                url = url + "&t=" + token;
//            }
            parameter = getInfoParameter();
            if (!TextUtils.isEmpty(parameter)) {
                parameter = parameter.replace("\\/", "/");
            }
            logger.d(String.format("send name:%s url:%s param:%s",
                    clazz, url, parameter));
            final HttpComm comm = HttpComm.getInstance();
            String result = comm.post(url, parameter);
            logger.d(String.format("recv name:%s url:%s result:%s", clazz, url, result));
            try {
                JSONObject o = new JSONObject(result);
                onResult(o);
            } catch (JSONException e) {
                logger.d("recv response url:" + url + "; fail");
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Void result) {
            mListener.onResponse(mRequestId);
        }

    }
}
