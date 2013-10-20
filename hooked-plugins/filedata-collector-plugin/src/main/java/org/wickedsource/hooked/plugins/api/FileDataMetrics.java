package org.wickedsource.hooked.plugins.api;

import org.wickedsource.hooked.plugins.api.collector.Metric;
import org.wickedsource.hooked.plugins.api.notifier.FileMetrics;

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

    public long getBytes(String filename){
        return get(filename, BYTES);
    }

    public long getLines(String filename){
        return get(filename, LINES);
    }

    public long getEmptyLines(String filename){
        return get(filename, EMPTY_LINES);
    }

}
