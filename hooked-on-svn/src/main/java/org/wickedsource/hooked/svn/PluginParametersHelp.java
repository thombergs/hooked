package org.wickedsource.hooked.svn;

import org.wickedsource.hooked.plugins.api.params.ParameterizedPlugin;
import org.wickedsource.hooked.plugins.api.params.PluginParameter;

import java.util.Set;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class PluginParametersHelp {

    public static void showHelp() {
        Set<ParameterizedPlugin> plugins = PluginRegistry.INSTANCE.getParameterizedPlugins();
        for (ParameterizedPlugin plugin : plugins) {
            System.out.println(String.format("SYSTEM PROPERTIES FOR PLUGIN %s", plugin.getClass().getSimpleName()));
            for (PluginParameter param : plugin.getRequiredParameters()) {
                System.out.println(String.format("        -D%s.%s=<value>", plugin.getClass().getSimpleName(),
                        param.getName()));
                System.out.println(String.format("            %s", param.getDescription()));
                System.out.println();
            }
            for (PluginParameter param : plugin.getOptionalParameters()) {
                System.out.println(String.format("        -D%s.%s=<value> (optional)",
                        plugin.getClass().getSimpleName(), param.getName()));
                System.out.println(String.format("            %s", param.getDescription()));
                System.out.println();
            }
            System.out.println();
            // TODO: pretty print descriptions so that the line breaks after 80 characters
        }
    }
}
