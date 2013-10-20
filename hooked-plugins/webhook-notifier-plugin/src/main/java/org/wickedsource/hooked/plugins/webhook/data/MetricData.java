package org.wickedsource.hooked.plugins.webhook.data;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class MetricData {

    private String metric;

    private long value;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
