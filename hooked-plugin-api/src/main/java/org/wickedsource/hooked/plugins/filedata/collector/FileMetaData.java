package org.wickedsource.hooked.plugins.filedata.collector;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileMetaData {

    private String path;

    private FileType fileType;

    private ModificationType modificationType;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public ModificationType getModificationType() {
        return modificationType;
    }

    public void setModificationType(ModificationType modificationType) {
        this.modificationType = modificationType;
    }
}
