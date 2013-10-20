package org.wickedsource.hooked.svn;

import org.testng.annotations.Test;

public class PluginRegistryTest {

    /**
     * Tests the static initialization of PluginRegistry.
     */
    @Test
    public void test(){
        System.setProperty(PluginPropertiesLoader.SYSTEM_PROPERTY_CONFIG_PATH, "some/path");
        PluginRegistry.INSTANCE.getNotifierPlugins();
        PluginRegistry.INSTANCE.getCollectorPlugins();
    }
}
