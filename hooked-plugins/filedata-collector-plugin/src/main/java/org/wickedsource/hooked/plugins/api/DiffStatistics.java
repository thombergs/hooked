package org.wickedsource.hooked.plugins.api;

import org.wickedsource.diffparser.api.model.Diff;
import org.wickedsource.diffparser.api.model.Hunk;
import org.wickedsource.diffparser.api.model.Line;

/**
 * Helper class to calculate some statistics on a Diff.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
@SuppressWarnings("UnusedDeclaration")
public class DiffStatistics {

    private Diff diff;

    public DiffStatistics(Diff diff) {
        this.diff = diff;
    }

    public int changes() {
        return diff.getHunks().size();
    }

    public int addedTotalLines() {
        int fromLinesCount = 0;
        int toLinesCount = 0;
        for (Hunk hunk : diff.getHunks()) {
            for (Line line : hunk.getLines()) {
                switch (line.getLineType()) {
                    case FROM:
                        fromLinesCount++;
                        break;
                    case TO:
                        toLinesCount++;
                        break;
                }
            }
        }
        return toLinesCount - fromLinesCount;
    }

    public int addedBytes() {
        int fromBytesCount = 0;
        int toBytesCount = 0;
        for (Hunk hunk : diff.getHunks()) {
            for (Line line : hunk.getLines()) {
                switch (line.getLineType()) {
                    case FROM:
                        fromBytesCount += line.getContent().length();
                        break;
                    case TO:
                        toBytesCount += line.getContent().length();
                        break;
                }
            }
        }
        return toBytesCount - fromBytesCount;
    }

    public int addedEmptyLines() {
        int fromLinesCount = 0;
        int toLinesCount = 0;
        for (Hunk hunk : diff.getHunks()) {
            for (Line line : hunk.getLines()) {
                if ("".equals(line.getContent().trim()))
                    switch (line.getLineType()) {
                        case FROM:
                            fromLinesCount++;
                            break;
                        case TO:
                            toLinesCount++;
                            break;
                    }
            }
        }
        return toLinesCount - fromLinesCount;
    }

    public int addedNonEmptyLines() {
        int fromLinesCount = 0;
        int toLinesCount = 0;
        for (Hunk hunk : diff.getHunks()) {
            for (Line line : hunk.getLines()) {
                if (!"".equals(line.getContent().trim()))
                    switch (line.getLineType()) {
                        case FROM:
                            fromLinesCount++;
                            break;
                        case TO:
                            toLinesCount++;
                            break;
                    }
            }
        }
        return toLinesCount - fromLinesCount;
    }


}