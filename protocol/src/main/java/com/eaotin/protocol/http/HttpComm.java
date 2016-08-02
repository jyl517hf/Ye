package com.eaotin.protocol.http;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 进行Http网络连接建立的类
 */
public class HttpComm {
    private final static String SERVER_URL = "http://uat.b.quancome.com/platform/api";
    private final static String REQUEST_METHOD = "POST";
    private final static String ENCODE_TYPE = "UTF-8";
    private final static int TIME_OUT = 15000;
    private static HttpComm instance = null;

    private HttpComm() {
    }

    /**
     * 单例模式创建网络链接类
     *
     * @return 网络链接类HttpComm
     */
    public static HttpComm getInstance() {
        if (instance == null) {
            instance = new HttpComm();
        }
        return instance;
    }

    /**
     * 进行Post请求,并获得返回参数字符串
     *
     * @param url   请求接口URL
     * @param param 请求接口参数
     * @return 返回值
     */
    public String post(String url, String param) {
        String result = null;
        //获得Http网络链接
        HttpURLConnection urlConnection = getConnection();
        try {
            urlConnection.connect();
            if (urlConnection.getRequestMethod().equals(REQUEST_METHOD) && !TextUtils.isEmpty(param)) {
                byte[] paramBytes = param.getBytes(ENCODE_TYPE);
                DataOutputStream dos = new DataOutputStream(urlConnection.getOutputStream());
                dos.write(paramBytes);
                dos.flush();
                dos.close();
            }
            int httpResponseCode = urlConnection.getResponseCode();
            // 判断请求是否成功
            if (httpResponseCode == HttpURLConnection.HTTP_OK) {
                //获取响应输入流对象
                InputStream inputStream = urlConnection.getInputStream();
                //创建字节流输出对象
                ByteArrayOutputStream outputSteam = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputSteam.write(buffer, 0, len);
                }
                //释放资源
                outputSteam.close();
                inputStream.close();
                byte[] data = outputSteam.toByteArray();
                result = new String(data, ENCODE_TYPE);
            } else {
                result = putHttpErrorCodeToJsonString(httpResponseCode);
            }
        } catch (Exception e) {
            result = putHttpErrorCodeToJsonString(ResultCode.EXCEPTION_ERROR);
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            return result;
        }

    }

    /**
     * 获得HttURL链接的方法
     *
     * @return HttpURLConnection
     */
    private HttpURLConnection getConnection() {
        HttpURLConnection connection = null;
        // 初始化connection
        try {
            // 根据地址创建URL对象
            URL url = new URL(SERVER_URL);
            // 根据URL对象打开链接
            connection = (HttpURLConnection) url.openConnection();
            // 设置请求的方式
            connection.setRequestMethod(REQUEST_METHOD);
            // 发送POST请求必须设置允许输入，默认为true
            connection.setDoInput(true);
            // 发送POST请求必须设置允许输出
            connection.setDoOutput(true);
            // 设置不使用缓存
            connection.setUseCaches(false);
            // 设置请求的超时时间
            connection.setReadTimeout(TIME_OUT);
            connection.setConnectTimeout(TIME_OUT);
            // 配置请求Content-Type
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setChunkedStreamingMode(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 将整形的HTTP错误码转换为JSON字符串,便于在界面层进行解析与操作
     *
     * @param errorCode 错误码
     * @return 返回JSON字符串
     * @throws JSONException
     */
    private String putHttpErrorCodeToJsonString(int errorCode) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result_code", errorCode);
        return jsonObject.toString();
    }

}
