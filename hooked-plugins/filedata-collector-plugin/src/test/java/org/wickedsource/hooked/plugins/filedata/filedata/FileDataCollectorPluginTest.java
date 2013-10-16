package org.wickedsource.hooked.plugins.filedata.filedata;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wickedsource.hooked.plugins.filedata.FileDataCollectorPlugin;
import org.wickedsource.hooked.plugins.filedata.FileDataMetrics;
import org.wickedsource.hooked.plugins.filedata.collector.CommittedFile;
import org.wickedsource.hooked.plugins.filedata.collector.FileMetaData;
import org.wickedsource.hooked.plugins.filedata.collector.FileType;
import org.wickedsource.hooked.plugins.filedata.collector.ModificationType;
import org.wickedsource.hooked.plugins.filedata.notifier.FileMetrics;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileDataCollectorPluginTest {

    @Test
    public void test() {
        // given a file to analyze
        FileMetaData metadata = new FileMetaData();
        metadata.setPath("testfile.txt");
        metadata.setModificationType(ModificationType.ADDED);
        metadata.setFileType(FileType.FILE);
        byte[] content = readByteArrayFromClasspath("testfile.txt");
        CommittedFile file = new CommittedFile(metadata, content);
        List<CommittedFile> fileList = Collections.singletonList(file);

        // when analyzing the file with our plugin
        FileDataCollectorPlugin plugin = new FileDataCollectorPlugin();
        FileMetrics fileMetrics = plugin.analyzeCommittedFiles(fileList);

        // then the analyis statistics must be correct
        Assert.assertEquals(fileMetrics.get("testfile.txt", FileDataMetrics.LINES), Long.valueOf(14));
        Assert.assertEquals(fileMetrics.get("testfile.txt", FileDataMetrics.BYTES), Long.valueOf(631));
        Assert.assertEquals(fileMetrics.get("testfile.txt", FileDataMetrics.EMPTY_LINES), Long.valueOf(2));


    }

    private byte[] readByteArrayFromClasspath(String path) {
        try {
            InputStream in = getClass().getResourceAsStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}