package org.wickedsource.hooked.plugins.api;

import java.io.Serializable;

/**
 * Represents a numeric metric on a committed file.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public final class Metric implements Serializable {

    private final String id;

    /**
     * Constructor.
     *
     * @param id the unique ID of the metric to define with this object. It is advised to create this id by <strong>concatenating your
     *           plugin's fully qualified class name and a simple name</strong> for your metric to avoid name clashes with other plugins.
     */
    public Metric(String id) {
        this.id = id;
    }

    /**
     * This metric's unique ID.
     *
     * @return this metric's unique ID.
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metric metric = (Metric) o;

        return id.equals(metric.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
