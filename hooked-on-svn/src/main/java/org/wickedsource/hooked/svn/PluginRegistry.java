package org.wickedsource.hooked.svn;

import org.wickedsource.hooked.plugins.api.collector.CollectorPlugin;

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
}
