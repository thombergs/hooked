package org.wickedsource.hooked.plugins.collector.filedata;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wickedsource.hooked.plugins.collector.api.CollectorPlugin;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class ServiceLoaderTest {

    @Test
    public void test() {
        ServiceLoader<CollectorPlugin> loader = ServiceLoader.load(CollectorPlugin.class);

        Iterator<CollectorPlugin> iterator = loader.iterator();
        while (iterator.hasNext()) {
            CollectorPlugin plugin = iterator.next();
            Assert.assertEquals(FileDataCollectorPlugin.class, plugin.getClass());
        }
    }
}
