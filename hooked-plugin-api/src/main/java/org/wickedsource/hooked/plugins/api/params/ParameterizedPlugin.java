package org.wickedsource.hooked.plugins.api.params;

import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public interface ParameterizedPlugin {

    public List<? extends PluginParameter> getRequiredParameters();

    public List<? extends PluginParameter> getOptionalParameters();

}
