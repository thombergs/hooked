package org.wickedsource.hooked.plugins.api.notifier;

import org.wickedsource.hooked.plugins.api.Plugin;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface NotifierPlugin extends Plugin{

    void notify(CommitData data);

}
