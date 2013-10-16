package org.wickedsource.hooked.svn;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.wc.admin.ISVNChangeEntryHandler;
import org.tmatesoft.svn.core.wc.admin.SVNChangeEntry;
import org.wickedsource.hooked.plugins.filedata.collector.FileType;
import org.wickedsource.hooked.plugins.filedata.collector.ModificationType;
import org.wickedsource.hooked.svn.data.SvnFileMetaData;

import java.util.ArrayList;
import java.util.List;

public class SvnFileMetaDataCollector implements ISVNChangeEntryHandler {

    private List<SvnFileMetaData> collectedData = new ArrayList<>();

    @Override
    public void handleEntry(SVNChangeEntry entry) throws SVNException {
        SvnFileMetaData file = new SvnFileMetaData();
        file.setCopyFromPath(entry.getCopyFromPath());
        file.setCopyFromRevision(entry.getCopyFromRevision());
        file.setHasPropertyModifications(entry.hasPropertyModifications());
        file.setHasTextModifications(entry.hasTextModifications());
        file.setFilePath(entry.getPath());
        file.setModificationType(mapModificationType(entry.getType()));
        file.setFileType(mapFileType(entry.getKind()));
        collectedData.add(file);
    }

    private ModificationType mapModificationType(char type) {
        switch (type) {
            case SVNChangeEntry.TYPE_ADDED:
                return ModificationType.ADDED;
            case SVNChangeEntry.TYPE_DELETED:
                return ModificationType.DELETED;
            case SVNChangeEntry.TYPE_UPDATED:
                return ModificationType.MODIFIED;
            default:
                throw new IllegalStateException(String.format("Invalid modification type: %s", type));
        }
    }

    private FileType mapFileType(SVNNodeKind type) {
        if (type == SVNNodeKind.DIR) {
            return FileType.DIRECTORY;
        } else if (type == SVNNodeKind.FILE) {
            return FileType.FILE;
        } else {
            return FileType.UNKNOWN;
        }
    }

    public List<SvnFileMetaData> getCollectedMetaData() {
        return collectedData;
    }
}
