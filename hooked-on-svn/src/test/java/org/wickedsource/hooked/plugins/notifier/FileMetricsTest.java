package org.wickedsource.hooked.plugins.notifier;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wickedsource.hooked.plugins.collector.api.Metric;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class FileMetricsTest {

    private static final Metric METRIC1 = new Metric("metric1");

    private static final Metric METRIC2 = new Metric("metric2");

    @Test
    public void testAdd() throws Exception {
        // given
        FileMetrics metrics = new FileMetrics();

        // when
        metrics.add("file1", METRIC1, 1L);
        metrics.add("file1", METRIC1, 2L);
        metrics.add("file1", METRIC2, 1L);
        metrics.add("file2", METRIC1, 2L);

        // then
        Assert.assertEquals(metrics.get("file1", METRIC1), Long.valueOf(3));
        Assert.assertEquals(metrics.get("file1", METRIC2), Long.valueOf(1));
        Assert.assertEquals(metrics.get("file2", METRIC1), Long.valueOf(2));
    }

    @Test
    public void testSubtract() throws Exception {
        // given
        FileMetrics metrics = new FileMetrics();
        metrics.add("file1", METRIC1, 1L);
        metrics.add("file1", METRIC1, 2L);

        // when
        metrics.subtract("file1", METRIC1, 2L);
        metrics.subtract("file2", METRIC1, 2L);

        // then
        Assert.assertEquals(metrics.get("file1", METRIC1), Long.valueOf(1));
        Assert.assertEquals(metrics.get("file2", METRIC1), Long.valueOf(-2));
    }

    @Test
    public void testSet() throws Exception {
        // given
        FileMetrics metrics = new FileMetrics();

        // when
        metrics.set("file1", METRIC1, 1L);
        metrics.set("file1", METRIC1, 2L);
        metrics.set("file1", METRIC2, 1L);
        metrics.set("file2", METRIC1, 2L);

        // then
        Assert.assertEquals(metrics.get("file1", METRIC1), Long.valueOf(2));
        Assert.assertEquals(metrics.get("file1", METRIC2), Long.valueOf(1));
        Assert.assertEquals(metrics.get("file2", METRIC1), Long.valueOf(2));
    }

    @Test
    public void testJoin() throws Exception {
        // given
        FileMetrics metrics1 = new FileMetrics();
        FileMetrics metrics2 = new FileMetrics();

        // when
        metrics1.set("file1", METRIC1, 1L);
        metrics1.set("file1", METRIC2, 2L);
        metrics1.set("file2", METRIC1, 3L);
        metrics2.set("file1", METRIC1, 1L);
        metrics2.set("file1", METRIC2, 2L);
        metrics2.set("file2", METRIC1, 3L);
        metrics1.join(metrics2);

        // then
        Assert.assertEquals(metrics1.get("file1", METRIC1), Long.valueOf(2));
        Assert.assertEquals(metrics1.get("file1", METRIC2), Long.valueOf(4));
        Assert.assertEquals(metrics1.get("file2", METRIC1), Long.valueOf(6));
    }
}
