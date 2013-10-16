package org.wickedsource.hooked.plugins.webhook;


import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebhookCaller {

    /**
     * Sends a JSON formatted String to a given URL via POST.
     *
     * @param url           the URL of the webhook to send the payload to
     * @param parameterName the name of the parameter which should include the payload
     * @param jsonPayload   the JSON payload
     */
    public void callWebhook(String url, String parameterName, String jsonPayload) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.setConfig(getRequestConfig());
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(parameterName, jsonPayload));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
            response.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error trying to send JSON payload to webhook at URL '%s'!",
                    url), e);
        }
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
    }
}
