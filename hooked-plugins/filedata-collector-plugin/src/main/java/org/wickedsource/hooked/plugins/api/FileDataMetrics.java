package org.wickedsource.hooked.plugins.api;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileDataMetrics extends FileMetrics {

    public static final Metric LINES = new Metric(FileDataCollectorPlugin.class.getName() + ".lines");
    public static final Metric LINES_DELETED = new Metric(FileDataCollectorPlugin.class.getName() + ".linesDeleted");
    public static final Metric BYTES = new Metric(FileDataCollectorPlugin.class.getName() + ".bytes");
    public static final Metric BYTES_DELETED = new Metric(FileDataCollectorPlugin.class.getName() + ".bytesDeleted");
    public static final Metric EMPTY_LINES = new Metric(FileDataCollectorPlugin.class.getName() + ".emptyLines");
    public static final Metric EMPTY_LINES_DELETED = new Metric(FileDataCollectorPlugin.class.getName() + "" +
            ".emptyLinesDeleted");

    public void addLines(String filename, Long value) {
        add(filename, LINES, value);
    }

    public void addBytes(String filename, Long value) {
        add(filename, BYTES, value);
    }

    public void addEmptyLines(String filename, Long value) {
        add(filename, EMPTY_LINES, value);
    }

    public void addEmptyLinesDeleted(String filename, Long value) {
        add(filename, EMPTY_LINES_DELETED, value);
    }

    public void addLinesDeleted(String filename, Long value) {
        add(filename, LINES_DELETED, value);
    }

    public void addBytesDeleted(String filename, Long value) {
        add(filename, BYTES_DELETED, value);
    }

    public long getBytes(String filename){
        return get(filename, BYTES);
    }

    public long getBytesDeleted(String filename){
        return get(filename, BYTES_DELETED);
    }

}
