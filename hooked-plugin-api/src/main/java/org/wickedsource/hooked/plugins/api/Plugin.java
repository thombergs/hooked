package org.wickedsource.hooked.plugins.api;

import java.util.Properties;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface Plugin {

    /**
     * This method will be called on initializing the plugin.
     *
     * @param properties the properties that contain the configuration parameters for this plugin.
     */
    void init(Properties properties);

}
