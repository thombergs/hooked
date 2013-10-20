package org.wickedsource.hooked.plugins.webhook.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileData {

    private String filePath;

    private List<MetricData> metrics = new ArrayList<>();

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<MetricData> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricData> metrics) {
        this.metrics = metrics;
    }
}
