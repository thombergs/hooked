package org.wickedsource.hooked.plugins.webhook;

import org.codehaus.jackson.map.ObjectMapper;
import org.wickedsource.hooked.plugins.filedata.notifier.CommitData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JsonMapper {

    /**
     * Maps CommitData to a JSON string.
     */
    public String mapToJson(CommitData data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            mapper.writeValue(out, data);
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error while mapping object to JSON!", e);
        }
    }
}
