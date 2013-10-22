package org.wickedsource.hooked.plugins.api.notifier;

import org.wickedsource.hooked.plugins.api.Plugin;

/**
 * A notifier plugin can be added to the processing of a VCS hook to send the data collected by collector plugins to one or more external
 * receivers.
 * <p/>
 * If not specified otherwise, a notifier plugin is added to a VCS hook via Java's {@link java.util.ServiceLoader}. That means,
 * if you want to provide an implementation of a notifier plugin, you have to add a File named
 * org.wickedsource.hooked.plugins.api.notifier.NotifierPlugin containing one line with the fully qualified name of your implementation
 * class to the META-INF folder of your JAR. Then, simply drop the JAR (and it's dependencies) into the classpath of the hook.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface NotifierPlugin extends Plugin {

    /**
     * This method is called by a VCS hook after all data on a commit is collected. This data is passed into the method to be sent to
     * external reveivers.
     *
     * @param data the data to send to external receivers.
     */
    void notify(CommitData data);

}
