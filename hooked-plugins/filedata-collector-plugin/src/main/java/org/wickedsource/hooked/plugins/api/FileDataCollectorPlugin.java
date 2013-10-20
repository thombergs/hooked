package org.wickedsource.hooked.plugins.api;

import org.wickedsource.hooked.plugins.api.collector.CollectorPlugin;
import org.wickedsource.hooked.plugins.api.collector.CommittedFile;
import org.wickedsource.hooked.plugins.api.collector.ModificationType;
import org.wickedsource.hooked.plugins.api.notifier.FileMetrics;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileDataCollectorPlugin implements CollectorPlugin {

    @Override
    public FileMetrics analyzeCommittedFiles(List<CommittedFile> committedFiles) {
        FileMetrics resultList = new FileMetrics();
        for (CommittedFile file : committedFiles) {
            try {
                FileMetrics metrics = analyzeFile(file);
                resultList.join(metrics);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    private FileMetrics analyzeFile(CommittedFile file) throws IOException {

        String filename = file.getMetaData().getPath();
        FileDataMetrics metrics = new FileDataMetrics();
        BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getContent())));
        String line;
        while ((line = in.readLine()) != null) {
            if (file.getMetaData().getModificationType() == ModificationType.DELETED) {
                getDeletedMetrics(filename, metrics, line);
            } else {
                getAddedMetrics(filename, metrics, line);
            }
        }

        // correction since the last line does not contain a newline character
        if (metrics.getBytes(filename) != 0) {
            metrics.addBytes(filename, (long) -1);
        }
        if (metrics.getBytesDeleted(filename) != 0) {
            metrics.addBytesDeleted(filename, (long) -1);
        }

        return metrics;
    }

    private void getAddedMetrics(String filename, FileDataMetrics metrics, String line) {
        metrics.addBytes(filename, (long) (line.length() + 1)); // +1 for newline
        metrics.addLines(filename, 1l);
        if ("".equals(line.trim())) {
            metrics.addEmptyLines(filename, 1l);
        }
    }

    private void getDeletedMetrics(String filename, FileDataMetrics metrics, String line) {
        metrics.addBytesDeleted(filename, (long) (line.length() + 1)); // +1 for newline
        metrics.addLinesDeleted(filename, 1l);
        if ("".equals(line.trim())) {
            metrics.addEmptyLinesDeleted(filename, 1l);
        }
    }

    @Override
    public void init(Properties properties) {
        // nothing to do, since we don't need any external parameters
    }
}
