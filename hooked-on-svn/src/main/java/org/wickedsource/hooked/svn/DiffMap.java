package org.wickedsource.hooked.svn;

import org.wickedsource.diffparser.api.model.Diff;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class DiffMap {

    private Map<String, Diff> map = new HashMap<>();

    public void addDiff(String filename, Diff diff) {
        map.put(filename, diff);
    }

    public Diff getDiffForFile(String filename) {
        return map.get(filename);
    }
}
