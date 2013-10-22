package org.wickedsource.hooked.plugins.api;

import java.util.Properties;

/**
 * Base interface for all types VCS hook plugins.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface Plugin {

    /**
     * This method will be called by the VCS hook on initializing the plugin.
     *
     * @param properties the properties that contain the configuration parameters for this plugin. Where these properties are defined is
     *                   defined by the VCS hook (for example, it may be some property file, system properties, or whatever else).
     */
    void init(Properties properties);

}
