package org.wickedsource.hooked.plugins.collector.filedata;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wickedsource.hooked.plugins.collector.api.CommittedFile;
import org.wickedsource.hooked.plugins.collector.api.FileMetaData;
import org.wickedsource.hooked.plugins.collector.api.FileType;
import org.wickedsource.hooked.plugins.collector.api.ModificationType;
import org.wickedsource.hooked.plugins.notifier.FileMetrics;

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
        InputStream in = getClass().getResourceAsStream("testfile.txt");
        CommittedFile file = new CommittedFile(metadata, in);
        List<CommittedFile> fileList = Collections.singletonList(file);

        // when analyzing the file with our plugin
        FileDataCollectorPlugin plugin = new FileDataCollectorPlugin();
        List<FileMetrics> metrics = plugin.analyzeCommittedFiles(fileList);
        FileMetrics fileMetrics = metrics.get(0);
        FileDataMetricsContainer metricsContainer = new FileDataMetricsContainer(fileMetrics.getMetrics(),
                FileDataCollectorPlugin.class);

        // then the analyis statistics must be correct
        Assert.assertEquals(metrics.size(), 1);
        Assert.assertEquals(metricsContainer.getMetricIds().size(), 3);
        Assert.assertEquals(metricsContainer.getLines(), 14);
        Assert.assertEquals(metricsContainer.getBytes(), 631);
        Assert.assertEquals(metricsContainer.getEmptyLines(), 2);
    }

}
