package org.wickedsource.hooked.plugins.api.notifier;

import org.wickedsource.hooked.plugins.api.collector.Metric;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileMetrics implements Serializable {

    private Map<String, Map<Metric, Long>> metrics = new HashMap<>();

    public void add(String filename, Metric metric, Long value) {
        Map<Metric, Long> metricValues = getNullSafe(filename);
        Long currentValue = metricValues.get(metric);
        if (currentValue == null) {
            metricValues.put(metric, value);
        } else {
            metricValues.put(metric, currentValue + value);
        }
    }

    public void subtract(String filename, Metric metric, Long value) {
        Map<Metric, Long> metricValues = getNullSafe(filename);
        Long currentValue = metricValues.get(metric);
        if (currentValue == null) {
            metricValues.put(metric, -value);
        } else {
            metricValues.put(metric, currentValue - value);
        }
    }

    public void set(String filename, Metric metric, Long value) {
        Map<Metric, Long> metricValues = getNullSafe(filename);
        metricValues.put(metric, value);
    }

    public Long get(String filename, Metric metric) {
        Map<Metric, Long> metricValues = metrics.get(filename);
        if (metricValues != null) {
            Long value =  metricValues.get(metric);
            if(value == null){
                return 0L;
            }else{
                return value;
            }
        } else {
            return 0L;
        }
    }

    public Map<Metric, Long> getNullSafe(String filename) {
        Map<Metric, Long> metricValues = metrics.get(filename);
        if (metricValues == null) {
            metricValues = new HashMap<>();
            metrics.put(filename, metricValues);
        }
        return metricValues;
    }

    public Set<Metric> getMetrics(String filename) {
        Map<Metric, Long> metricValues = metrics.get(filename);
        if (metricValues == null) {
            return Collections.emptySet();
        } else {
            return metricValues.keySet();
    }
}

    public Set<String> getFilenames() {
        return metrics.keySet();
    }

    public void join(FileMetrics container) {
        for (String filename : container.getFilenames()) {
            for (Metric metric : container.getMetrics(filename)) {
                Long value = container.get(filename, metric);
                this.add(filename, metric, value);
            }
        }
    }

    public int getFileCount(){
        return metrics.size();
    }
}
