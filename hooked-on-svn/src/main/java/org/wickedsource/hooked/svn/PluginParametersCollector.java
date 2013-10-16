package org.wickedsource.hooked.svn;

import org.wickedsource.hooked.plugins.filedata.params.ParameterizedPlugin;
import org.wickedsource.hooked.plugins.filedata.params.PluginParameter;

import java.util.Set;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class PluginParametersCollector {

    @SuppressWarnings("unchecked")
    public void collectParameters() throws MissingRequiredParameterException {
        Set<ParameterizedPlugin> plugins = PluginRegistry.INSTANCE.getParameterizedPlugins();
        for (ParameterizedPlugin plugin : plugins) {
            for (PluginParameter requiredParameter : plugin.getParameters()) {
                String propertyName = getSystemPropertyName(plugin, requiredParameter);
                String propertyValue = System.getProperty(propertyName);
                if (requiredParameter.isRequired() && propertyValue == null) {
                    throw new MissingRequiredParameterException(plugin, propertyName);
                } else {
                    requiredParameter.setValue(requiredParameter.fromString(propertyValue));
                }
            }
        }
    }

    private String getSystemPropertyName(ParameterizedPlugin plugin, PluginParameter parameter) {
        return String.format("%s.%s", plugin.getClass().getSimpleName(), parameter.getName());
    }

}
