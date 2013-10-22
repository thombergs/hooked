package org.wickedsource.hooked.plugins.api.collector;

import org.wickedsource.hooked.plugins.api.Plugin;
import org.wickedsource.hooked.plugins.api.FileMetrics;

import java.util.List;

/**
 * A collector plugin can be added to the processing of a VCS hook to collect arbitrary data on the committed files.
 * <p/>
 * If not specified otherwise, a collector plugin is added to a VCS hook via Java's {@link java.util.ServiceLoader}. That means,
 * if you want to provide an implementation of a collector plugin, you have to add a File named org.wickedsource.hooked.plugins.api
 * .collector.CollectorPlugin containing one line with the fully qualified name of your implementation class to the META-INF folder of
 * your JAR. Then, simply drop the JAR (and it's dependencies) into the classpath of the hook.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface CollectorPlugin extends Plugin {

    /**
     * This method is called by a VCS hook during a commit. It is provided with contents and some metadata of all files that were touched
     * during the commit. The method should go through the files and collect whatever (numeric) metrics are desired (see the
     * {@link org.wickedsource.hooked.plugins.api.FileMetrics} class for a description of the data structure that can be
     * collected).
     *
     * @param committedItems the contents and metadata of all files and directories that were touched during a commit to a VCS.
     * @return the metrics for the files and directories that were collected.
     */
    FileMetrics collectFileMetrics(List<CommittedItem> committedItems);

}
