package org.wickedsource.hooked.plugins.api.collector;

/**
 * Contents and metadata of a file (or folder) that was touched during a VCS commit.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommittedItem {

    private final CommittedItemMetaData metaData;

    private final byte[] content;

    public CommittedItem(CommittedItemMetaData metaData, byte[] content) {
        this.metaData = metaData;
        this.content = content;
    }

    public CommittedItemMetaData getMetaData() {
        return metaData;
    }

    /**
     * The content of the committed item as byte array.
     * <p/>
     * Note the following special cases:
     * <ul>
     * <li>the content will only be filled if the item's {@link org.wickedsource.hooked.plugins.api.collector.FileType} is
     * {@link org.wickedsource.hooked.plugins.api.collector.FileType#FILE}.</li>
     * <li>if the item's {@link org.wickedsource.hooked.plugins.api.collector.ModificationType} is
     * {@link org.wickedsource.hooked.plugins.api.collector.ModificationType#DELETED} the content will actually be the content of the
     * item's <strong>previous revision</strong> in the VCS</li>
     * </ul>
     *
     * @return the content of this item as byte array.
     */
    public byte[] getContent() {
        return content;
    }
}
