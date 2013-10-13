package org.wickedsource.hooked.plugins.collector.filedata;

import org.wickedsource.hooked.plugins.collector.api.CollectorPlugin;
import org.wickedsource.hooked.plugins.collector.api.CommittedFile;
import org.wickedsource.hooked.plugins.collector.api.Metric;
import org.wickedsource.hooked.plugins.notifier.FileMetrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileDataCollectorPlugin implements CollectorPlugin {

    @Override
    public List<FileMetrics> analyzeCommittedFiles(List<CommittedFile> committedFiles) {
        List<FileMetrics> resultList = new ArrayList<>();
        for (CommittedFile file : committedFiles) {
            try {
                List<Metric> metrics = analyzeFile(file);
                FileMetrics fileMetrics = new FileMetrics(file.getMetaData().getPath(), metrics);
                resultList.add(fileMetrics);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    private List<Metric> analyzeFile(CommittedFile file) throws IOException {
        FileDataMetricsContainer metrics = new FileDataMetricsContainer();
        BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            metrics.addBytes(line.length() + 1); // +1 for newline
            metrics.addLines(1);
            if ("".equals(line.trim())) {
                metrics.addEmptyLines(1);
            }
        }
        // correction since the last line does not contain a newline character
        metrics.addBytes(-1);
        return metrics.toList();
    }


}
