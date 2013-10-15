package org.wickedsource.hooked.svn;

import org.wickedsource.hooked.plugins.api.params.ParameterizedPlugin;
import org.wickedsource.hooked.plugins.api.params.PluginParameter;

import java.util.Set;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class PluginParametersCollector {

    @SuppressWarnings("unchecked")
    public void collectParameters() throws MissingRequiredParameterException {
        Set<ParameterizedPlugin> plugins = PluginRegistry.INSTANCE.getParameterizedPlugins();
        for (ParameterizedPlugin plugin : plugins) {

            for (PluginParameter requiredParameter : plugin.getRequiredParameters()) {
                String propertyName = getSystemPropertyName(plugin, requiredParameter);
                String propertyValue = System.getProperty(propertyName);
                if (propertyValue == null) {
                    throw new MissingRequiredParameterException(plugin, propertyName);
                } else {
                    requiredParameter.setValue(requiredParameter.fromString(propertyValue));
                }
            }

            for (PluginParameter optionalParameter : plugin.getOptionalParameters()) {
                String propertyName = getSystemPropertyName(plugin, optionalParameter);
                String propertyValue = System.getProperty(propertyName);
                if (propertyValue != null) {
                    optionalParameter.setValue(optionalParameter.fromString(propertyValue));
                }
            }
        }
    }

    private String getSystemPropertyName(ParameterizedPlugin plugin, PluginParameter parameter) {
        return String.format("%s.%s", plugin.getClass().getSimpleName(), parameter.getName());
    }

}
