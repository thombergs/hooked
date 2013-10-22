package org.wickedsource.hooked.plugins.api.collector;

/**
 * Contains some metadata on an item that was part of a VCS commit.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class CommittedItemMetaData {

    private String path;

    private FileType fileType;

    private ModificationType modificationType;

    /**
     * The full path to the committed item, starting from the repository root.
     *
     * @return this item's file path.
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * This item's type.
     *
     * @return this item's type.
     */
    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    /**
     * The type of modification this item was subject to during the commit.
     *
     * @return this item's modification type.
     */
    public ModificationType getModificationType() {
        return modificationType;
    }

    public void setModificationType(ModificationType modificationType) {
        this.modificationType = modificationType;
    }
}
