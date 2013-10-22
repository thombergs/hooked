package org.wickedsource.hooked.svn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wickedsource.hooked.plugins.api.notifier.CommitData;
import org.wickedsource.hooked.plugins.api.notifier.NotifierPlugin;

import java.util.Set;

public class NotifierPluginVisitor {

    private static final Logger logger = LoggerFactory.getLogger(NotifierPluginVisitor.class);

    /**
     * Sends the aggregated data collected from the Subversion commit to all {@link org.wickedsource.hooked.plugins.api.notifier.NotifierPlugin}s
     * that are currently installed.
     *
     * @param data the data collected from the Subversion commit.
     */
    public void visitNotifierPlugins(CommitData data) {
        Set<NotifierPlugin> plugins = PluginRegistry.INSTANCE.getNotifierPlugins();
        for (NotifierPlugin plugin : plugins) {
            try {
                plugin.notify(data);
            } catch (Exception e) {
                logger.error(String.format("Notifier plugin %s did not execute normally. Skipped plugin execution.",
                        plugin.getClass()), e);
            }
        }
    }

}
