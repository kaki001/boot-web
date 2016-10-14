package com.geh.utils;

import com.geh.bean.ResultObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HuoBi on 2016/10/10.
 */
public class HttpUtils {

    private static final int MAX_TIMEOUT = 10000;
    public static final String HTTP_ENCODE = "UTF-8";
    private static PoolingHttpClientConnectionManager connectionManager;
    private static RequestConfig requestConfig;

    static {
        //设置连接池
        connectionManager = new PoolingHttpClientConnectionManager();
        //设置连接池大小
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(50);

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        //设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        //设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        //设置从连接池获取的连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    /**
     * POST方法
     *
     * @param apiUrl 请求URL
     * @param params 请求参数
     * @return String
     */
    public static JSONPObject doPost(String apiUrl, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        String result = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(HTTP_ENCODE)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, HTTP_ENCODE);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, JSONPObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            httpPost.releaseConnection();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * POST方法
     *
     * @param apiUrl 请求URL
     * @return String
     */
    public static ResultObject doGet(String apiUrl) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        String result = null;
        HttpGet httpPost = new HttpGet(apiUrl);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, HTTP_ENCODE);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, ResultObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            httpPost.releaseConnection();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String args[]) {
        System.out.println(doGet("http://localhost:8011/api/jyt/ashare/login?inputid=12pp"));
    }
}
