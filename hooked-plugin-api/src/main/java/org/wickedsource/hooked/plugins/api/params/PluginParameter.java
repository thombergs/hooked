package org.wickedsource.hooked.plugins.api.params;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public abstract class PluginParameter<T> {

    private final String name;

    private final String description;

    private T value;

    public PluginParameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PluginParameter(String name, String description, T value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract T fromString(String stringValue);
}
