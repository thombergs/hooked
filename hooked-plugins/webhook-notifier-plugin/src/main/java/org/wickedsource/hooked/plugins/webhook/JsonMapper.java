package org.wickedsource.hooked.plugins.webhook;

import org.codehaus.jackson.map.ObjectMapper;
import org.wickedsource.hooked.plugins.api.notifier.CommitData;
import org.wickedsource.hooked.plugins.webhook.data.CommitDataToJsonDataMapper;
import org.wickedsource.hooked.plugins.webhook.data.JsonData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JsonMapper {

    /**
     * Maps CommitData to a JSON string.
     */
    public String mapToJson(CommitData data) {
        try {
            CommitDataToJsonDataMapper commitDataMapper = new CommitDataToJsonDataMapper();
            JsonData jsonData = commitDataMapper.map(data);
            ObjectMapper mapper = new ObjectMapper();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            mapper.writeValue(out, jsonData);
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error while mapping object to JSON!", e);
        }
    }
}
