package com.hawk.utility.httpclient;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public class HttpClientHelper {
	
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * 主机名
	 */
	private String hostname;
	/**
	 * 协议名
	 */
	private String scheme;
	/**
	 * 端口号
	 */
	private int port; 
	
	private String userAgent = "httpclient4.5.1";
	
	
	
	
	public  HttpClientHelper(){
//		HttpParams httpParams = new BasicHttpParams();
//		String userAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
//		HttpProtocolParams.setUserAgent(httpParams, userAgent);
//		HttpProtocolParams.setContentCharset(httpParams, "utf-8");
//		HttpConnectionParams.setConnectionTimeout(httpParams, 8000);  
//        HttpConnectionParams.setSoTimeout(httpParams, 30000);
	}
	
	private ConnectionKeepAliveStrategy keepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {

        @Override
        public long getKeepAliveDuration(
            HttpResponse response,
            HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    //如果服务器没有设置keep-alive这个参数，我们就把它设置成5秒
                    keepAlive = 5000;
                }
                return keepAlive;
        }

    };
    
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(1000)
            .setConnectTimeout(1000)
            .build();
	
	private CloseableHttpClient buileHttpClient(){
		CloseableHttpClient client = HttpClients.custom()
				.setUserAgent(userAgent)
				.setKeepAliveStrategy(keepAliveStrategy)
				.setr
				.build();
		return client;
	}
	
	public void post(String path , Object o , Map<String,Object> params ){
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost httpPost = null;
		httpPost.setConfig(requestConfig);
		HttpGet httpGet = null;
		httpGet.setCancellable(cancellable);
		httpClient.
		httpClient.execute(httpPost);
	}
	
	

}
