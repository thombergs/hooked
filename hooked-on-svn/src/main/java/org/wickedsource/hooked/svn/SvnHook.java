package org.wickedsource.hooked.svn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wickedsource.hooked.plugins.api.notifier.CommitData;
import org.wickedsource.hooked.plugins.api.notifier.FileMetrics;
import org.wickedsource.hooked.svn.data.SvnCommitData;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class SvnHook {

    private static Logger logger = LoggerFactory.getLogger(SvnHook.class);

    private String repositoryRoot;

    private Long revisionNumber;

    public static void main(String... args) {
        SvnHook hook = createSvnHook(args);
        hook.run();
    }

    public SvnHook(String repositoryRoot, Long revisionNumber) {
        this.repositoryRoot = repositoryRoot;
        this.revisionNumber = revisionNumber;
    }

    /**
     * Creates a fresh SvnHook object from input parameters.
     */
    private static SvnHook createSvnHook(String[] args) {
        if (args.length != 2) {
            logger.error("SvnHook has been called with the wrong number of parameters! Should be called with path to " +
                    "local SVN repository as first parameter and SVN revision number as second parameter!");
            System.exit(-1);
        }

        String repositoryRoot = args[0];
        String revisionString = args[1];
        Long revisionNumber = null;
        try {
            revisionNumber = Long.valueOf(revisionString);
        } catch (NumberFormatException e) {
            logger.error(String.format("Could not parse revision number '%s'!", revisionString));
            System.exit(-2);
        }

        return new SvnHook(repositoryRoot, revisionNumber);
    }

    public void run() {
        try {
            SvnCommitDataCollector collector = new SvnCommitDataCollector(repositoryRoot, revisionNumber);
            SvnCommitData svnData = collector.collectCommitData();
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Successfully collected SVN specific commit data: %s", svnData));
            }

            CollectorPluginVisitor collectorPluginVisitor = new CollectorPluginVisitor(svnData, repositoryRoot,
                    revisionNumber);
            FileMetrics metrics = collectorPluginVisitor.visitCollectorPlugins();
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Successfully collected data on %d files from collector plugins",
                        metrics.getFileCount()));
            }

            CommitData commitData = new CommitData();
            commitData.setVcsSpecificData(svnData);
            commitData.setFileMetrics(metrics);

            NotifierPluginVisitor notifierPluginVisitor = new NotifierPluginVisitor();
            notifierPluginVisitor.visitNotifierPlugins(commitData);
            if (logger.isDebugEnabled()) {
                logger.debug("Successfully distributed data to notifier plugins");
            }

            logger.info(String.format("Successfully processed commit on repository %s by user '%s'",
                    svnData.getRepositoryUrl(), svnData.getAuthor()));
        } catch (Exception e) {
            logger.error("Exception while collecting svn commit data!", e);
        }
    }


}
