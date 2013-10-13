package org.wickedsource.hooked.plugins.collector.api;

import java.io.InputStream;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommittedFile {

    private final FileMetaData metaData;

    private final InputStream inputStream;

    public CommittedFile(FileMetaData metaData, InputStream inputStream) {
        this.metaData = metaData;
        this.inputStream = inputStream;
    }

    public FileMetaData getMetaData() {
        return metaData;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
