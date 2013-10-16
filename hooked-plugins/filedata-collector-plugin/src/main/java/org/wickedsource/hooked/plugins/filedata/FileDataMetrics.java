package org.wickedsource.hooked.plugins.filedata;

import org.wickedsource.hooked.plugins.filedata.collector.Metric;
import org.wickedsource.hooked.plugins.filedata.notifier.FileMetrics;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileDataMetrics extends FileMetrics {

    public static final Metric LINES = new Metric(FileDataCollectorPlugin.class.getName() + ".lines");
    public static final Metric BYTES = new Metric(FileDataCollectorPlugin.class.getName() + ".bytes");
    public static final Metric EMPTY_LINES = new Metric(FileDataCollectorPlugin.class.getName() + ".emptyLines");

    public void addLines(String filename, Long value) {
        add(filename, LINES, value);
    }

    public void addBytes(String filename, Long value) {
        add(filename, BYTES, value);
    }

    public void addEmptyLines(String filename, Long value) {
        add(filename, EMPTY_LINES, value);
    }

}
