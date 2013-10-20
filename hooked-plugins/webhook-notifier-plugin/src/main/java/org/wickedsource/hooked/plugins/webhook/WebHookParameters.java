package org.wickedsource.hooked.plugins.webhook;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class WebHookParameters {

    private final String url;

    private final String payloadParameterName;

    public WebHookParameters(String url, String payloadParameterName) {
        this.url = url;
        this.payloadParameterName = payloadParameterName;
    }

    public String getUrl() {
        return url;
    }

    public String getPayloadParameterName() {
        return payloadParameterName;
    }

}
