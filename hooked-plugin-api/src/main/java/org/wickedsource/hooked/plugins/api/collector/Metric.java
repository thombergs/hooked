package org.wickedsource.hooked.plugins.api.collector;

import java.io.Serializable;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public final class Metric implements Serializable{

    private final String id;

    public Metric(String id) {
        this.id = id;
    }

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
