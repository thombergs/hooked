package org.wickedsource.hooked.plugins.collector.api;

import org.wickedsource.hooked.plugins.notifier.FileMetrics;

import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface CollectorPlugin {

    FileMetrics analyzeCommittedFiles(List<CommittedFile> committedFiles);

}
