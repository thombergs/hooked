package org.wickedsource.hooked.svn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.admin.SVNLookClient;
import org.wickedsource.diffparser.api.DiffParser;
import org.wickedsource.diffparser.api.UnifiedDiffParser;
import org.wickedsource.diffparser.api.model.Diff;
import org.wickedsource.hooked.plugins.api.FileMetrics;
import org.wickedsource.hooked.plugins.api.collector.CollectorPlugin;
import org.wickedsource.hooked.plugins.api.collector.CommittedItem;
import org.wickedsource.hooked.plugins.api.collector.CommittedItemMetaData;
import org.wickedsource.hooked.plugins.api.collector.FileType;
import org.wickedsource.hooked.svn.data.SvnCommitData;
import org.wickedsource.hooked.svn.data.SvnFileMetaData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CollectorPluginVisitor {

    private static Logger logger = LoggerFactory.getLogger(CollectorPluginVisitor.class);

    private final SvnCommitData data;

    private final String repositoryRoot;

    private final Long revision;

    private FileMetrics metrics = new FileMetrics();

    public CollectorPluginVisitor(SvnCommitData data, String repositoryRoot, Long revision) {
        this.data = data;
        this.repositoryRoot = repositoryRoot;
        this.revision = revision;
    }

    /**
     * Goes through all files of a Subversion commit and passes them to the method to each installed
     * {@link org.wickedsource.hooked.plugins.api.collector.CollectorPlugin}.
     *
     * @return the aggregated file metrics gathered by all collector plugins.
     */
    public FileMetrics visitCollectorPlugins() {
        try {
            SVNLookClient lookClient = SVNKitUtil.createSVNLookClient();
            DiffMap diffMap = getDiffMap(lookClient);
            List<CommittedItem> committedItems = new ArrayList<>();
            for (SvnFileMetaData svnFile : data.getSvnFilesMetaData()) {
                metrics.addFile(svnFile.getFilePath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                if (svnFile.getFileType() == FileType.FILE) {
                    lookClient.doCat(new File(repositoryRoot), svnFile.getFilePath(),
                            SVNRevision.create(revision), out);
                }
                committedItems.add(new CommittedItem(mapFileMetaData(svnFile), out.toByteArray(), diffMap.getDiffForFile(svnFile.getFilePath())));
            }
            visitCollectorPlugins(committedItems);
            return metrics;
        } catch (SVNException e) {
            throw new RuntimeException(e);
        }
    }

    private DiffMap getDiffMap(SVNLookClient lookClient) throws SVNException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        lookClient.doGetDiff(new File(repositoryRoot), SVNRevision.create(revision), true, true, true, out);
        DiffParser diffParser = new UnifiedDiffParser();
        List<Diff> diffs = diffParser.parse(out.toByteArray());
        DiffMap diffMap = new DiffMap();
        for (Diff diff : diffs) {
            diffMap.addDiff(diff.getToFileName(), diff);
        }
        return diffMap;
    }

    private void visitCollectorPlugins(List<CommittedItem> committedItems) {
        Set<CollectorPlugin> plugins = PluginRegistry.INSTANCE.getCollectorPlugins();
        for (CollectorPlugin plugin : plugins) {
            try {
                metrics.join(plugin.collectFileMetrics(committedItems));
            } catch (Exception e) {
                logger.error(String.format("Collector plugin %s did not execute normally. Skipped plugin execution.",
                        plugin.getClass()), e);
            }
        }
    }

    private CommittedItemMetaData mapFileMetaData(SvnFileMetaData svnFile) {
        CommittedItemMetaData file = new CommittedItemMetaData();
        file.setFileType(svnFile.getFileType());
        file.setModificationType(svnFile.getModificationType());
        file.setPath(svnFile.getFilePath());
        return file;
    }

}
