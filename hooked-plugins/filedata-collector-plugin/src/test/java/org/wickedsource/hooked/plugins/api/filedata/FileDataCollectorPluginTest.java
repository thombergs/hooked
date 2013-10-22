package org.wickedsource.hooked.plugins.api.filedata;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wickedsource.hooked.plugins.api.FileDataCollectorPlugin;
import org.wickedsource.hooked.plugins.api.FileDataMetrics;
import org.wickedsource.hooked.plugins.api.collector.CommittedItem;
import org.wickedsource.hooked.plugins.api.collector.CommittedItemMetaData;
import org.wickedsource.hooked.plugins.api.collector.FileType;
import org.wickedsource.hooked.plugins.api.collector.ModificationType;
import org.wickedsource.hooked.plugins.api.FileMetrics;

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
        CommittedItemMetaData metadata = new CommittedItemMetaData();
        metadata.setPath("testfile.txt");
        metadata.setModificationType(ModificationType.ADDED);
        metadata.setFileType(FileType.FILE);
        byte[] content = readByteArrayFromClasspath("testfile.txt");
        CommittedItem file = new CommittedItem(metadata, content);
        List<CommittedItem> fileList = Collections.singletonList(file);

        // when analyzing the file with our plugin
        FileDataCollectorPlugin plugin = new FileDataCollectorPlugin();
        FileMetrics fileMetrics = plugin.collectFileMetrics(fileList);

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
