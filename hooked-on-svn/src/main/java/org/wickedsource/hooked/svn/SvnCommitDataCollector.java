package org.wickedsource.hooked.svn;

import org.pojava.datetime.DateTime;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNRevisionProperty;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.admin.SVNLookClient;
import org.wickedsource.hooked.svn.data.SvnCommitData;

import java.io.File;

public class SvnCommitDataCollector {

    private final File repositoryRoot;

    private final SVNRevision svnRevision;

    private final String repositoryUrl;

    /**
     * Constructor.
     *
     * @param repositoryRoot the absolute path to the root of the Subversion repository in the local file system.
     * @param svnRevision    the revision number of the current Subversion commit.
     * @param repositoryUrl  the URL under which the Subversion repository is accessible from outside.
     */
    public SvnCommitDataCollector(String repositoryRoot, Long svnRevision, String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
        this.repositoryRoot = new File(repositoryRoot);
        this.svnRevision = SVNRevision.create(svnRevision);
    }

    /**
     * Collects some basic data on the current Subversion commit.
     *
     * @return Subversion-specific data on the commit.
     */
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
        data.setRepositoryUrl(repositoryUrl);
    }

}
