package org.wickedsource.hooked.plugins.webhook.data;

import org.wickedsource.hooked.plugins.api.notifier.VCSSpecificCommitData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class JsonData {

    private VCSSpecificCommitData vcsSpecific;

    private List<FileData> files = new ArrayList<>();

    public VCSSpecificCommitData getVcsSpecific() {
        return vcsSpecific;
    }

    public void setVcsSpecific(VCSSpecificCommitData vcsSpecific) {
        this.vcsSpecific = vcsSpecific;
    }

    public List<FileData> getFiles() {
        return files;
    }

    public void setFiles(List<FileData> files) {
        this.files = files;
    }
}
