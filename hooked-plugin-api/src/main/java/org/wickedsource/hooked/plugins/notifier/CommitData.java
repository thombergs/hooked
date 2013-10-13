package org.wickedsource.hooked.plugins.notifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommitData {

    private VCSSpecificCommitData vcsData;

    private List<FileMetrics> fileMetrics = new ArrayList<>();

    public List<FileMetrics> getFileMetrics() {
        return fileMetrics;
    }

    public void setFileMetrics(List<FileMetrics> fileMetrics) {
        this.fileMetrics = fileMetrics;
    }

    public VCSSpecificCommitData getVcsData() {
        return vcsData;
    }

    public void setVcsData(VCSSpecificCommitData vcsData) {
        this.vcsData = vcsData;
    }
}
