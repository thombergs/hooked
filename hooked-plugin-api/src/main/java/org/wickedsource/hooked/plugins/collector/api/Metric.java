package org.wickedsource.hooked.plugins.collector.api;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public final class Metric {

    private final String id;

    private long value;

    public Metric(String id, long value) {
        this.id = id;
    }

    public Metric(String id) {
        this(id, 0);
    }

    public String getId() {
        return id;
    }

    public long getValue() {
        return value;
    }

    public void addToValue(long addition) {
        this.value += addition;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metric metric = (Metric) o;

        if (!id.equals(metric.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
