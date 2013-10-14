package org.wickedsource.hooked.svn;

import io.airlift.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wickedsource.hooked.plugins.notifier.FileMetrics;
import org.wickedsource.hooked.svn.data.SvnCommitData;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
@Command(name = "svnhook", description = "Hook for collecting data on SVN checkins and notifying subscribers of " +
        "the results")
public class SvnHook {

    private Logger logger = LoggerFactory.getLogger(SvnHook.class);

    @Inject
    private HelpOption help;

    @Option(name = {"-repo"}, description = "Path to the SVN repository root in the local filesystem " +
            "(parameter REPOS-PATH from SVN hook script).")
    private String repositoryRoot;

    @Option(name = {"-rev"}, description = "Revision number of the checkin that is intercepted " +
            "(parameter REV from SVN hook script).")
    private Long revision;

    public static void main(String... args) {
        SvnHook hook = SingleCommand.singleCommand(SvnHook.class).parse(args);

        if (hook.repositoryRoot == null || hook.revision == null) {
            System.out.println("Missing command line parameter! See help below.");
            Help.help(hook.help.commandMetadata);
            return;
        }

        if (hook.help.showHelpIfRequested()) {
            return;
        }

        hook.run();
    }

    public void run() {
        try {
            SvnCommitDataCollector collector = new SvnCommitDataCollector(repositoryRoot, revision);
            SvnCommitData data = collector.collectCommitData();

            CollectorPluginVisitor collectorPluginNotifier = new CollectorPluginVisitor(data, repositoryRoot, revision);
            List<FileMetrics> metrics = collectorPluginNotifier.visitCollectorPlugins();

            // TODO: send data to all notifier plugins
            logger.info(String.format("Successfully collected svn commit data: %s", data));
        } catch (Exception e) {
            logger.error("Exception while collecting svn commit data!", e);
        }
    }


}
