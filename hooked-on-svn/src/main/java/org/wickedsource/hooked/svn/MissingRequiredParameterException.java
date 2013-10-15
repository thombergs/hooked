package org.wickedsource.hooked.svn;

import org.wickedsource.hooked.plugins.api.params.ParameterizedPlugin;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class MissingRequiredParameterException extends Exception {

    private final ParameterizedPlugin plugin;

    private final String parameterName;

    public MissingRequiredParameterException(ParameterizedPlugin plugin, String parameterName) {
        this.plugin = plugin;
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

    public ParameterizedPlugin getPlugin() {
        return plugin;
    }
}
