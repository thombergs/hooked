package org.wickedsource.hooked.plugins.webhook;

import org.wickedsource.hooked.plugins.api.notifier.CommitData;
import org.wickedsource.hooked.plugins.api.notifier.NotifierPlugin;

import java.util.List;
import java.util.Properties;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class WebhookNotifierPlugin implements NotifierPlugin {

    private WebhookNotifierPluginProperties properties;

    @Override
    public void notify(CommitData data) {
        JsonMapper mapper = new JsonMapper();
        String json = mapper.mapToJson(data);

        WebhookCaller caller = new WebhookCaller();

        List<WebHookParameters> webhookParams = properties.getWebHookParameters();
        for (WebHookParameters params : webhookParams) {
            caller.callWebhook(params, json);
        }

    }

    @Override
    public void init(Properties properties) {
        this.properties = new WebhookNotifierPluginProperties(properties);
    }
}
