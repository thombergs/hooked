package org.wickedsource.hooked.plugins.collector.api;

import java.util.*;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class MetricsContainer {

    private Map<String, Metric> metrics = new HashMap<>();

    private Class<? extends CollectorPlugin> namespace;

    public MetricsContainer(Class<? extends CollectorPlugin> namespace) {
        this.namespace = namespace;
    }

    public MetricsContainer() {

    }

    public void setMetricValue(String metricId, long value) {
        Metric metric = getMetricFromSet(metricId);
        metric.setValue(value);
    }

    public MetricsContainer(List<Metric> metrics) {
        for (Metric metric : metrics) {
            setMetricValue(metric.getId(), metric.getValue());
        }
    }

    public MetricsContainer(List<Metric> metrics, Class<? extends CollectorPlugin> namespace) {
        this(metrics);
        this.namespace = namespace;
    }

    public void addToMetricValue(String metricId, long value) {
        Metric metric = getMetricFromSet(metricId);
        metric.addToValue(value);
    }

    public long getMetricValue(String metricId) {
        Metric metric = getMetricFromSet(metricId);
        return metric.getValue();
    }

    private Metric getMetricFromSet(String metricId) {
        String namespacedMetricId = prependPluginNamespace(metricId);
        Metric metric = metrics.get(namespacedMetricId);
        if (metric == null) {
            metric = new Metric(namespacedMetricId, 0);
            metrics.put(namespacedMetricId, metric);
        }
        return metric;
    }

    private String prependPluginNamespace(String metricId) {
        if (namespace != null) {
            return namespace.getName() + "." + metricId;
        } else {
            return metricId;
        }
    }

    public Set<String> getMetricIds() {
        return metrics.keySet();
    }

    public List<Metric> toList() {
        return new ArrayList<>(metrics.values());
    }

}
