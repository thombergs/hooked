package org.wickedsource.hooked.plugins.filedata.params;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public abstract class PluginParameter<T> {

    private final String name;

    private final String description;

    private final boolean required;

    private T value;

    public PluginParameter(String name, String description) {
        this.name = name;
        this.description = description;
        this.required = false;
    }

    public PluginParameter(String name, String description, boolean required) {
        this.name = name;
        this.description = description;
        this.required = required;
    }

    protected PluginParameter(String name, String description, boolean required, T value) {
        this.name = name;
        this.description = description;
        this.required = required;
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

    public boolean isRequired() {
        return required;
    }
}
