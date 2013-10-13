package org.wickedsource.hooked.plugins.collector.filedata;

import org.wickedsource.hooked.plugins.collector.api.CollectorPlugin;
import org.wickedsource.hooked.plugins.collector.api.Metric;
import org.wickedsource.hooked.plugins.collector.api.MetricsContainer;

import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileDataMetricsContainer extends MetricsContainer {

    public static final String LINES = "lines";
    public static final String BYTES = "bytes";
    public static final String EMPTY_LINES = "emptyLines";

    public FileDataMetricsContainer() {
        super(FileDataCollectorPlugin.class);
    }

    public FileDataMetricsContainer(List<Metric> metrics) {
        super(metrics);
    }

    public FileDataMetricsContainer(List<Metric> metrics, Class<? extends CollectorPlugin> namespace) {
        super(metrics, namespace);
    }

    public void addLines(int value) {
        addToMetricValue(LINES, value);
    }

    public void addBytes(int value) {
        addToMetricValue(BYTES, value);
    }

    public void addEmptyLines(int value) {
        addToMetricValue(EMPTY_LINES, value);
    }

    public long getLines() {
        return getMetricValue(LINES);
    }

    public long getBytes() {
        return getMetricValue(BYTES);
    }

    public long getEmptyLines() {
        return getMetricValue(EMPTY_LINES);
    }


}
