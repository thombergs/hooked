package org.wickedsource.hooked.plugins.webhook;

import org.wickedsource.hooked.plugins.filedata.notifier.CommitData;
import org.wickedsource.hooked.plugins.filedata.notifier.NotifierPlugin;
import org.wickedsource.hooked.plugins.filedata.params.ParameterizedPlugin;
import org.wickedsource.hooked.plugins.filedata.params.PluginParameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class WebhookNotifierPlugin implements NotifierPlugin, ParameterizedPlugin {

    private UrlsParameter urls = new UrlsParameter();

    private ParameterNamesParameter parameterNames = new ParameterNamesParameter();

    @Override
    public void notify(CommitData data) {
        JsonMapper mapper = new JsonMapper();
        String json = mapper.mapToJson(data);

        WebhookCaller caller = new WebhookCaller();
        Iterator<String> urlIterator = urls.getUrls().iterator();
        Iterator<String> paramsIterator = parameterNames.getParameterNames().iterator();

        while (urlIterator.hasNext()) {
            String url = urlIterator.next();
            String paramName = "payload";
            if (paramsIterator.hasNext()) {
                paramName = paramsIterator.next();
            }
            caller.callWebhook(url, paramName, json);
        }

    }

    @Override
    public List<? extends PluginParameter> getParameters() {
        List<PluginParameter> params = new ArrayList<>();
        params.add(urls);
        params.add(parameterNames);
        return params;
    }
}
