package org.wickedsource.hooked.svn.data;

import org.wickedsource.hooked.plugins.filedata.notifier.VCSSpecificCommitData;

import java.util.Date;
import java.util.List;

/**
 * Contains all the data collected during analysis of a commit to a version control system.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class SvnCommitData implements VCSSpecificCommitData {

    private String author;

    private Date timestamp;

    private String message;

    private List<SvnFileMetaData> svnFileMetaData;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SvnFileMetaData> getSvnFilesMetaData() {
        return svnFileMetaData;
    }

    public void setSvnFilesMetaData(List<SvnFileMetaData> svnFileMetaData) {
        this.svnFileMetaData = svnFileMetaData;
    }
}
