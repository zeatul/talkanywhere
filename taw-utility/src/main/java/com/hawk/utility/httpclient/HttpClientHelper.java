package com.hawk.utility.httpclient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

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

	public HttpClientHelper() {
		// HttpParams httpParams = new BasicHttpParams();
		// String userAgent =
		// "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
		// HttpProtocolParams.setUserAgent(httpParams, userAgent);
		// HttpProtocolParams.setContentCharset(httpParams, "utf-8");
		// HttpConnectionParams.setConnectionTimeout(httpParams, 8000);
		// HttpConnectionParams.setSoTimeout(httpParams, 30000);
	}

	private ConnectionKeepAliveStrategy keepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {

		@Override
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			long keepAlive = super.getKeepAliveDuration(response, context);
			if (keepAlive == -1) {
				// 如果服务器没有设置keep-alive这个参数，我们就把它设置成5秒
				keepAlive = 5000;
			}
			return keepAlive;
		}

	};
	
	private HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {  
		  
	    public boolean retryRequest(  
	            IOException exception,  
	            int executionCount,  
	            HttpContext context) {  
	        if (executionCount >= 5) {  
	            // Do not retry if over max retry count  
	            return false;  
	        }  
	        if (exception instanceof InterruptedIOException) {  
	            // Timeout  
	            return false;  
	        }  
	        if (exception instanceof UnknownHostException) {  
	            // Unknown host  
	            return false;  
	        }  
	        if (exception instanceof ConnectTimeoutException) {  
	            // Connection refused  
	            return false;  
	        }  
	        if (exception instanceof SSLException) {  
	            // SSL handshake exception  
	            return false;  
	        }  
	        HttpClientContext clientContext = HttpClientContext.adapt(context);  
	        HttpRequest request = clientContext.getRequest();  
	        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);  
	        if (idempotent) {  
	            // Retry if the request is considered idempotent  
	            return true;  
	        }  
	        return false;  
	    }  
	};

	private URIBuilder generateURIBuilder(String path, Map<String, String> params) {
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(scheme);
		uriBuilder.setHost(hostname);
		uriBuilder.setPort(port);
		uriBuilder.setPath(path);

		if (params != null) {
			List<NameValuePair> p = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				p.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			uriBuilder.setCustomQuery(URLEncodedUtils.format(p, "UTF-8"));
		}
		return uriBuilder;
	}

	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).setConnectionRequestTimeout(3000).build();

	private CloseableHttpClient buileHttpClient() {
		CloseableHttpClient client = HttpClients.custom()
				.setUserAgent(userAgent)				
				.setKeepAliveStrategy(keepAliveStrategy) //连接保持策略
				.setRetryHandler(retryHandler) //重试策略				
				.build();
		return client;
	}

	public String get(String path, Map<String, String> params) throws Exception {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = buileHttpClient();

			HttpGet httpGet = new HttpGet(generateURIBuilder(path, params).build());
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
			
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (Exception e) {

			}
		}
	}

	public String post(String path, String content, Map<String, String> params) throws Exception {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = buileHttpClient();
			StringEntity input = new StringEntity(content, "utf-8");
			input.setContentType("application/json");
			HttpPost httpPost = new HttpPost(generateURIBuilder(path, params).build());
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(input);
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (Exception e) {

			}
		}

	}

}
