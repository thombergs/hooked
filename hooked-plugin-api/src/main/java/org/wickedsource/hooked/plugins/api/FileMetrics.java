package org.wickedsource.hooked.plugins.api;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This data structure is used to represent numeric data on several files that is collected during a VCS commit. Each file is identified
 * by its path (which should be unique in any given VCS repository). For each file, several
 * {@link Metric}s can be stored, each with its own numeric value per file.
 * <p/>
 * The interface of this class has been designed to be convenient during the collection of file metrics. While iterating through the
 * files of a commit you can use the methods {@link #add(String, Metric, Long)} and
 * {@link #set(String, Metric, Long)} to manipulate the current values stored for each file
 * and metric.
 * <p/>
 * Implementation note: when implementing a collector plugin, it is advised that you create a subclass of this class and provide
 * convenience method to add or set the values of your Metrics for better readability (e.g. create methods like "addLines" if you are
 * counting the lines of the committed files).
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileMetrics implements Serializable {

    private Map<String, Map<Metric, Long>> metrics = new HashMap<>();

    /**
     * Adds the given value to the given metric of the given file.
     *
     * @param filename the full path of the file starting from the root of the VCS repository.
     * @param metric   the metric whose value to add to.
     * @param value    the value to add to the given metric.
     */
    public final void add(String filename, Metric metric, Long value) {
        Map<Metric, Long> metricValues = getNullSafe(filename);
        Long currentValue = metricValues.get(metric);
        if (currentValue == null) {
            metricValues.put(metric, value);
        } else {
            metricValues.put(metric, currentValue + value);
        }
    }

    /**
     * Sets the value of the given metric of the given file to a specific value.
     *
     * @param filename the full path of the file starting from the root of the VCS repository.
     * @param metric   the metric whose value to add to.
     * @param value    the value to set the metric to.
     */
    public final void set(String filename, Metric metric, Long value) {
        Map<Metric, Long> metricValues = getNullSafe(filename);
        metricValues.put(metric, value);
    }

    /**
     * Returns the current value of the given metric on the given file.
     *
     * @param filename the full path of the file starting from the root of the VCS repository.
     * @param metric   the metric whose value to add to.
     * @return the current metric value.
     */
    public final Long get(String filename, Metric metric) {
        Map<Metric, Long> metricValues = metrics.get(filename);
        if (metricValues != null) {
            Long value = metricValues.get(metric);
            if (value == null) {
                return 0L;
            } else {
                return value;
            }
        } else {
            return 0L;
        }
    }

    private Map<Metric, Long> getNullSafe(String filename) {
        Map<Metric, Long> metricValues = metrics.get(filename);
        if (metricValues == null) {
            metricValues = new HashMap<>();
            metrics.put(filename, metricValues);
        }
        return metricValues;
    }

    /**
     * Returns a list of all metrics that are currently stored in this data structure for the given file.
     *
     * @param filename the full path of the file starting from the root of the VCS repository.
     * @return list of metrics collected for the given file.
     */
    public final Set<Metric> getMetrics(String filename) {
        Map<Metric, Long> metricValues = metrics.get(filename);
        if (metricValues == null) {
            return Collections.emptySet();
        } else {
            return metricValues.keySet();
        }
    }

    /**
     * Returns a list of all files for which data is currently stored in this data structure.
     *
     * @return list of files.
     */
    public final Set<String> getFilenames() {
        return metrics.keySet();
    }

    /**
     * Joins the given FileMetrics object to this. All metric values for all files will be added to existing metric values in this object
     * or created if they don't exist yet.F
     *
     * @param fileMetrics the FileMetrics object whose contents to add to this one.
     */
    public final void join(FileMetrics fileMetrics) {
        for (String filename : fileMetrics.getFilenames()) {
            for (Metric metric : fileMetrics.getMetrics(filename)) {
                Long value = fileMetrics.get(filename, metric);
                this.add(filename, metric, value);
            }
        }
    }

    /**
     * Returns the number of files for which metric data is stored in this object.
     *
     * @return number of files.s
     */
    public final int getFileCount() {
        return metrics.size();
    }

    /**
     * Adds a new file with empty metrics to the data structure if it does not exist yet.
     *
     * @param filename the name of the file.
     */
    public void addFile(String filename) {
        if (metrics.get(filename) == null) {
            this.metrics.put(filename, new HashMap<Metric, Long>());
        }
    }
}
