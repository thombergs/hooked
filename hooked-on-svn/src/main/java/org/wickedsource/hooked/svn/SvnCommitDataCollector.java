package org.wickedsource.hooked.svn;

import org.pojava.datetime.DateTime;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNRevisionProperty;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.admin.SVNLookClient;
import org.wickedsource.hooked.svn.data.SvnCommitData;

import java.io.File;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class SvnCommitDataCollector {

    private File repositoryRoot;

    private SVNRevision svnRevision;

    public SvnCommitDataCollector(String repositoryRoot, Long svnRevision) {
        this.repositoryRoot = new File(repositoryRoot);
        this.svnRevision = SVNRevision.create(svnRevision);
    }

    public SvnCommitData collectCommitData() {
        try {
            SvnCommitData data = new SvnCommitData();
            SVNLookClient lookClient = SVNKitUtil.createSVNLookClient();

            addGeneralMetaData(data, lookClient);
            addFileMetaData(data, lookClient);

            return data;
        } catch (SVNException e) {
            throw new RuntimeException(e);
        }
    }

    private void addFileMetaData(SvnCommitData data, SVNLookClient lookClient) throws SVNException {
        SvnFileMetaDataCollector collector = new SvnFileMetaDataCollector();
        lookClient.doGetChanged(repositoryRoot, svnRevision, collector, true);
        data.setSvnFilesMetaData(collector.getCollectedMetaData());
    }

    private void addGeneralMetaData(SvnCommitData data, SVNLookClient lookClient) throws SVNException {
        SVNProperties revisionProperties = lookClient.doGetRevisionProperties(repositoryRoot, svnRevision);
        data.setAuthor(revisionProperties.getStringValue(SVNRevisionProperty.AUTHOR));
        data.setMessage(revisionProperties.getStringValue(SVNRevisionProperty.LOG));
        String dateString = revisionProperties.getStringValue(SVNRevisionProperty.DATE);
        DateTime dateTime = DateTime.parse(dateString);
        data.setTimestamp(dateTime.toDate());
    }

}
