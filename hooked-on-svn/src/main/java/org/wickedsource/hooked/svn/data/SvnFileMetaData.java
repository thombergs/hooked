package org.wickedsource.hooked.svn.data;

import org.wickedsource.hooked.plugins.filedata.collector.FileType;
import org.wickedsource.hooked.plugins.filedata.collector.ModificationType;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class SvnFileMetaData {

    private String filePath;

    private FileType fileType;

    private ModificationType modificationType;

    private String copyFromPath;

    private long copyFromRevision;

    private boolean hasPropertyModifications;

    private boolean hasTextModifications;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public String getCopyFromPath() {
        return copyFromPath;
    }

    public void setCopyFromPath(String copyFromPath) {
        this.copyFromPath = copyFromPath;
    }

    public long getCopyFromRevision() {
        return copyFromRevision;
    }

    public void setCopyFromRevision(long getCopyFromRevision) {
        this.copyFromRevision = getCopyFromRevision;
    }

    public boolean isHasPropertyModifications() {
        return hasPropertyModifications;
    }

    public void setHasPropertyModifications(boolean hasPropertyModifications) {
        this.hasPropertyModifications = hasPropertyModifications;
    }

    public boolean isHasTextModifications() {
        return hasTextModifications;
    }

    public void setHasTextModifications(boolean hasTextModifications) {
        this.hasTextModifications = hasTextModifications;
    }
}
