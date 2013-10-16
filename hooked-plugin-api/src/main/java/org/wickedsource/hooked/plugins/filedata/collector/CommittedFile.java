package org.wickedsource.hooked.plugins.filedata.collector;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommittedFile {

    private final FileMetaData metaData;

    private final byte[] content;

    public CommittedFile(FileMetaData metaData, byte[] content) {
        this.metaData = metaData;
        this.content = content;
    }

    public FileMetaData getMetaData() {
        return metaData;
    }

    public byte[] getContent() {
        return content;
    }
}
