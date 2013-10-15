package org.wickedsource.hooked.plugins.api.filedata;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wickedsource.hooked.plugins.api.collector.CollectorPlugin;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class ServiceLoaderTest {

    @Test
    public void test() {
        ServiceLoader<CollectorPlugin> loader = ServiceLoader.load(CollectorPlugin.class);
        for (CollectorPlugin plugin : loader) {
            Assert.assertEquals(FileDataCollectorPlugin.class, plugin.getClass());
        }
    }
}
