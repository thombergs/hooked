package org.wickedsource.hooked.plugins.webhook.data;

import org.wickedsource.hooked.plugins.api.collector.Metric;
import org.wickedsource.hooked.plugins.api.notifier.CommitData;
import org.wickedsource.hooked.plugins.api.notifier.FileMetrics;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommitDataToJsonDataMapper {

    public JsonData map(CommitData data) {
        JsonData json = new JsonData();
        json.setVcsSpecific(data.getVcsSpecificData());
        FileMetrics fileMetrics = data.getFileMetrics();
        for (String filePath : fileMetrics.getFilenames()) {
            FileData fileData = new FileData();
            fileData.setFilePath(filePath);
            for (Metric metric : fileMetrics.getMetrics(filePath)) {
                MetricData metricData = new MetricData();
                metricData.setMetric(metric.getId());
                long value = fileMetrics.get(filePath, metric);
                metricData.setValue(value);
                fileData.getMetrics().add(metricData);
            }
            json.getFiles().add(fileData);
        }
        return json;
    }
}
