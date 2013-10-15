package org.wickedsource.hooked.svn;

import org.wickedsource.hooked.plugins.api.collector.CollectorPlugin;
import org.wickedsource.hooked.plugins.api.params.ParameterizedPlugin;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public enum PluginRegistry {

    INSTANCE;

    public Set<CollectorPlugin> getCollectorPlugins() {
        ServiceLoader<CollectorPlugin> loader = ServiceLoader.load(CollectorPlugin.class);
        Set<CollectorPlugin> plugins = new HashSet<>();
        for (CollectorPlugin plugin : loader) {
            plugins.add(plugin);
        }
        return plugins;
    }

    public Set<ParameterizedPlugin> getParameterizedPlugins(){
        ServiceLoader<ParameterizedPlugin> loader = ServiceLoader.load(ParameterizedPlugin.class);
        Set<ParameterizedPlugin> plugins = new HashSet<>();
        for (ParameterizedPlugin plugin : loader) {
            plugins.add(plugin);
        }
        return plugins;
    }
}
