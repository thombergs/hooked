package org.wickedsource.hooked.plugins.collector.api;

import org.wickedsource.hooked.plugins.notifier.FileMetrics;

import java.util.List;
import java.util.Set;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface CollectorPlugin {

    List<FileMetrics> analyzeCommittedFiles(List<CommittedFile> committedFiles);

}
