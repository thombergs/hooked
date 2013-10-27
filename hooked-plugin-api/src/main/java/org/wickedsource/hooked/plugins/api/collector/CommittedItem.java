package org.wickedsource.hooked.plugins.api.collector;

import org.wickedsource.diffparser.api.model.Diff;

/**
 * Contents and metadata of a file (or folder) that was touched during a VCS commit.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
@SuppressWarnings("UnusedDeclaration")
public class CommittedItem {

    private final CommittedItemMetaData metaData;

    private final byte[] content;

    private final Diff diff;

    public CommittedItem(CommittedItemMetaData metaData, byte[] content, Diff diff) {
        this.metaData = metaData;
        this.content = content;
        this.diff = diff;
    }

    public CommittedItemMetaData getMetaData() {
        return metaData;
    }

    /**
     * The content of the committed item as byte array.
     * <p/>
     * Note that the content will be empty in the following cases:
     * <ul>
     * <li>the item's {@link org.wickedsource.hooked.plugins.api.collector.FileType} is not
     * {@link org.wickedsource.hooked.plugins.api.collector.FileType#FILE}.</li>
     * <li>the item's {@link org.wickedsource.hooked.plugins.api.collector.ModificationType} is
     * {@link org.wickedsource.hooked.plugins.api.collector.ModificationType#DELETED}
     * </ul>
     *
     * @return the content of this item as byte array.
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * The diff of the checked-in version of the file and the previous revision.
     *
     * @return Diff object containing the changes that were made to the file.
     */
    public Diff getDiff() {
        return diff;
    }
}
