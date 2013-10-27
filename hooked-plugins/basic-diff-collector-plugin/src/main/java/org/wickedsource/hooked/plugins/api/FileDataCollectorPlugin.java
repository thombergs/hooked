package org.wickedsource.hooked.plugins.api;

import org.wickedsource.hooked.plugins.api.collector.CollectorPlugin;
import org.wickedsource.hooked.plugins.api.collector.CommittedItem;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileDataCollectorPlugin implements CollectorPlugin {

    @Override
    public FileMetrics collectFileMetrics(List<CommittedItem> committedItems) {
        FileMetrics resultList = new FileMetrics();
        for (CommittedItem file : committedItems) {
            try {
                FileMetrics metrics = analyzeFile(file);
                resultList.join(metrics);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    private FileMetrics analyzeFile(CommittedItem file) throws IOException {
        String filename = file.getMetaData().getPath();
        FileDataMetrics metrics = new FileDataMetrics();
        DiffStatistics stats = new DiffStatistics(file.getDiff());

        long addedLines = stats.addedTotalLines();
        if (addedLines > 0) {
            metrics.addLines(filename, addedLines);
        } else if (addedLines < 0) {
            metrics.addLinesDeleted(filename, addedLines);
        }

        long addedEmptyLines = stats.addedEmptyLines();
        if (addedEmptyLines > 0) {
            metrics.addEmptyLines(filename, addedEmptyLines);
        } else if (addedLines < 0) {
            metrics.addEmptyLinesDeleted(filename, addedEmptyLines);
        }

        long addedNonEmptyLines = stats.addedNonEmptyLines();
        if (addedNonEmptyLines > 0) {
            metrics.addNonEmptyLines(filename, addedNonEmptyLines);
        } else if (addedLines < 0) {
            metrics.addNonEmptyLinesDeleted(filename, addedNonEmptyLines);
        }

        long addedBytes = stats.addedBytes();
        if (addedBytes > 0) {
            metrics.addBytes(filename, addedBytes);
        } else if (addedLines < 0) {
            metrics.addBytesDeleted(filename, addedBytes);
        }

        return metrics;
    }

    @Override
    public void init(Properties properties) {
        // nothing to do, since we don't need any external parameters
    }
}
