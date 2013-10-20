package org.wickedsource.hooked.svn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wickedsource.hooked.plugins.api.Plugin;
import org.wickedsource.hooked.plugins.api.collector.CollectorPlugin;
import org.wickedsource.hooked.plugins.api.notifier.NotifierPlugin;

import java.util.HashSet;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public enum PluginRegistry {

    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(PluginRegistry.class);

    private Set<CollectorPlugin> collectorPlugins;

    private Set<NotifierPlugin> notifierPlugins;

    private PluginPropertiesLoader pluginPropertiesLoader = new PluginPropertiesLoader();

    public synchronized Set<CollectorPlugin> getCollectorPlugins() {
        if (this.collectorPlugins == null) {
            ServiceLoader<CollectorPlugin> loader = ServiceLoader.load(CollectorPlugin.class);
            Set<CollectorPlugin> plugins = new HashSet<>();
            for (CollectorPlugin plugin : loader) {
                Properties properties = pluginPropertiesLoader.loadPluginProperties(plugin.getClass());
                plugin.init(properties);
                plugins.add(plugin);
            }
            this.collectorPlugins = plugins;
            logLoadedPlugins(this.collectorPlugins);
        }
        return this.collectorPlugins;
    }

    public synchronized Set<NotifierPlugin> getNotifierPlugins() {
        if (this.notifierPlugins == null) {
            ServiceLoader<NotifierPlugin> loader = ServiceLoader.load(NotifierPlugin.class);
            Set<NotifierPlugin> plugins = new HashSet<>();
            for (NotifierPlugin plugin : loader) {
                Properties properties = pluginPropertiesLoader.loadPluginProperties(plugin.getClass());
                plugin.init(properties);
                plugins.add(plugin);
            }
            this.notifierPlugins = plugins;
        }
        return this.notifierPlugins;
    }

    private void logLoadedPlugins(Set<? extends Plugin> plugins) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Loaded %d plugins:", plugins.size()));
            for (Plugin plugin : plugins) {
                logger.trace(plugin.getClass().getName());
            }
        }
    }

}
