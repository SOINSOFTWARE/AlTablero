/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @since 08/05/2015
 */
@Service
public class HttpRequest implements ResponseHandler<String>{
    
    private static final String URL = "http://localhost:1517/schoolmanagement/";
    private static final String EXCEPTION_MESSAGE = "Unexpected response status: ";
    
    public String sendGet(String methodAndParams) throws IOException {
        String completeUrl = URL + methodAndParams;
        HttpGet httpget = new HttpGet(completeUrl);
        return this.getResponse(httpget);
    }
    
    public String sendPost(String method, String object) throws IOException {
        String completeUrl = URL + method;
        HttpPost httppost = new HttpPost(completeUrl);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("object", object));
        httppost.setEntity(new UrlEncodedFormEntity(urlParameters));
        return this.getResponse(httppost);
    }
    
    private String getResponse(HttpRequestBase httpRequest) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            return httpclient.execute(httpRequest, this);
        }
    }
    
    @Override
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } else {
            throw new ClientProtocolException(EXCEPTION_MESSAGE + status);
        }
    }
}
