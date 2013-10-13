package org.wickedsource.hooked.plugins.notifier;

import org.wickedsource.hooked.plugins.collector.api.Metric;

import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileMetrics {

    private final String filePath;

    private final List<Metric> metrics;

    public FileMetrics(String filePath, List<Metric> metrics) {
        this.filePath = filePath;
        this.metrics = metrics;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }
}
