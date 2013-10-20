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
     * @param params the parameters for this webhook caller
     * @param jsonPayload   the JSON payload
     */
    public void callWebhook(WebHookParameters params, String jsonPayload) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(params.getUrl());
            post.setConfig(getRequestConfig());
            List<NameValuePair> httpParams = new ArrayList<>();
            httpParams.add(new BasicNameValuePair(params.getPayloadParameterName(), jsonPayload));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(httpParams, Consts.UTF_8);
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
            response.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error trying to send JSON payload to webhook at URL '%s'!",
                    params.getUrl()), e);
        }
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
    }
}
