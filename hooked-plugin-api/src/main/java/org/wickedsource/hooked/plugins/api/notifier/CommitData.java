package org.wickedsource.hooked.plugins.api.notifier;

import org.wickedsource.hooked.plugins.api.FileMetrics;

import java.io.Serializable;

/**
 * Data structure that contains all the data that was collected during a VCS commit.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommitData implements Serializable {

    private VCSSpecificCommitData vcsSpecificData;

    private FileMetrics fileMetrics;

    /**
     * Data on the commit that is specific to a given VCS.
     *
     * @return VCS-specific data on the commit.
     */
    public VCSSpecificCommitData getVcsSpecificData() {
        return vcsSpecificData;
    }

    public void setVcsSpecificData(VCSSpecificCommitData vcsSpecificData) {
        this.vcsSpecificData = vcsSpecificData;
    }

    /**
     * The metrics for the files of a commit that have been collected by all installed collector plugins.
     *
     * @return aggregated collected numeric metrics on each file of a commit.
     */
    public FileMetrics getFileMetrics() {
        return fileMetrics;
    }

    public void setFileMetrics(FileMetrics fileMetrics) {
        this.fileMetrics = fileMetrics;
    }
}
