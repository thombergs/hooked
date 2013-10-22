package org.wickedsource.hooked.svn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wickedsource.hooked.plugins.api.notifier.CommitData;
import org.wickedsource.hooked.plugins.api.notifier.FileMetrics;
import org.wickedsource.hooked.svn.data.SvnCommitData;

/**
 * Main class for the Subversion Hook. The main method of this class can be called from a shell script that is hooked into SVN.
 * <p/>
 * <strong>Command line parameters (in this order):</strong>
 * <ul>
 * <li>path to the root of the Subversion repository in the Subversion server's local filesystem (this is also the first parameter
 * that Subversion passes into the hook shell script of a post-commit hook)
 * </li>
 * <li>
 * number of the current commit's revision (this is also the second parameter that Subversion passes into the hook shell script of a
 * post-commit hook)
 * </li>
 * <li>
 * URL of the Subversion repository. This URL is used as an identifier for the commit data.
 * </li>
 * </ul>
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class SvnHook {

    private static Logger logger = LoggerFactory.getLogger(SvnHook.class);

    private String repositoryRoot;

    private Long revisionNumber;

    private String repositoryUrl;

    public static void main(String... args) {
        SvnHook hook = createSvnHook(args);
        hook.run();
    }

    public SvnHook(String repositoryRoot, Long revisionNumber, String repositoryUrl) {
        this.repositoryRoot = repositoryRoot;
        this.revisionNumber = revisionNumber;
        this.repositoryUrl = repositoryUrl;
    }

    /**
     * Creates a fresh SvnHook object from the command line arguments.s
     */
    private static SvnHook createSvnHook(String[] args) {
        if (args.length != 3) {
            logger.error("SvnHook has been called with the wrong number of parameters! Expecting 1) path to SVN repo " +
                    "in local filesystem 2) revision number of checking 3) URL of the SVN repo");
            System.exit(-1);
        }

        String repositoryRoot = args[0];
        String revisionString = args[1];
        String repositoryUrl = args[2];
        Long revisionNumber = null;
        try {
            revisionNumber = Long.valueOf(revisionString);
        } catch (NumberFormatException e) {
            logger.error(String.format("Could not parse revision number '%s'!", revisionString));
            System.exit(-2);
        }

        return new SvnHook(repositoryRoot, revisionNumber, repositoryUrl);
    }

    /**
     * Starts the processing of the Subversion hook. First collects Subversion specific data on the commit. Then passes this data to all
     * installed collector plugins to let them add to the data. Finally passes the aggregated collected data to all notifier plugins to
     * let them send the data to any receivers.
     */
    public void run() {
        try {
            SvnCommitDataCollector collector = new SvnCommitDataCollector(repositoryRoot, revisionNumber, repositoryUrl);
            SvnCommitData svnData = collector.collectCommitData();
            logger.debug(String.format("Successfully collected SVN specific commit data: %s", svnData));

            CollectorPluginVisitor collectorPluginVisitor = new CollectorPluginVisitor(svnData, repositoryRoot,
                    revisionNumber);
            FileMetrics metrics = collectorPluginVisitor.visitCollectorPlugins();
            logger.debug(String.format("Successfully collected data on %d files from collector plugins",
                    metrics.getFileCount()));

            CommitData commitData = new CommitData();
            commitData.setVcsSpecificData(svnData);
            commitData.setFileMetrics(metrics);

            NotifierPluginVisitor notifierPluginVisitor = new NotifierPluginVisitor();
            notifierPluginVisitor.visitNotifierPlugins(commitData);
            logger.debug("Successfully distributed data to notifier plugins");

            logger.info(String.format("Successfully processed commit on repository %s by user '%s'",
                    svnData.getRepositoryUrl(), svnData.getAuthor()));
        } catch (Exception e) {
            logger.error("Exception while collecting svn commit data!", e);
        }
    }


}
