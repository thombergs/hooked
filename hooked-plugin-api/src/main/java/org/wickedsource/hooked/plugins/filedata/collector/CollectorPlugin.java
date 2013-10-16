package org.wickedsource.hooked.plugins.filedata.collector;

import org.wickedsource.hooked.plugins.filedata.notifier.FileMetrics;

import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface CollectorPlugin {

    FileMetrics analyzeCommittedFiles(List<CommittedFile> committedFiles);

}
