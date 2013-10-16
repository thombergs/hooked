package org.wickedsource.hooked.plugins.webhook;

import org.wickedsource.hooked.plugins.filedata.params.PluginParameter;

import java.util.Arrays;
import java.util.List;

public class UrlsParameter extends PluginParameter<String> {

    public UrlsParameter() {
        super("urls", "Comma-separated list of URLs to which to send the commit data as JSON via HTTP POSTs", true);
    }

    @Override
    public String fromString(String stringValue) {
        return stringValue;
    }

    /**
     * Retrieves the URLs specified as system properties at program start.
     */
    public List<String> getUrls() {
        if (getValue() == null) {
            return null;
        } else {
            String[] urls = getValue().split(",");
            return Arrays.asList(urls);
        }
    }
}
