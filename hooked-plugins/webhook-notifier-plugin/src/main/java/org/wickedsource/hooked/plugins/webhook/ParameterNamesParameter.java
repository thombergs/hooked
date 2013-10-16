package org.wickedsource.hooked.plugins.webhook;

import org.wickedsource.hooked.plugins.filedata.params.PluginParameter;

import java.util.Arrays;
import java.util.List;

public class ParameterNamesParameter extends PluginParameter<String> {

    public ParameterNamesParameter() {
        super("params", "Comma-separated list of the names of the POST parameter that should contain the JSON data" +
                " for each of the specified URLs (default: 'payload')", false);
    }

    @Override
    public String fromString(String stringValue) {
        return stringValue;
    }

    /**
     * Retrieves the parameter names specified as system properties at program start.
     */
    public List<String> getParameterNames() {
        if (getValue() == null) {
            return null;
        } else {
            String[] urls = getValue().split(",");
            return Arrays.asList(urls);
        }
    }
}
