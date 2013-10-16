package org.wickedsource.hooked.plugins.filedata.notifier;

import java.io.Serializable;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommitData implements Serializable{

    private VCSSpecificCommitData vcsSpecificData;

    private FileMetrics fileMetrics;

    public VCSSpecificCommitData getVcsSpecificData() {
        return vcsSpecificData;
    }

    public void setVcsSpecificData(VCSSpecificCommitData vcsSpecificData) {
        this.vcsSpecificData = vcsSpecificData;
    }

    public FileMetrics getFileMetrics() {
        return fileMetrics;
    }

    public void setFileMetrics(FileMetrics fileMetrics) {
        this.fileMetrics = fileMetrics;
    }
}
