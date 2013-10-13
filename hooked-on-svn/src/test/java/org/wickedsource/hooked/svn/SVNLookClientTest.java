package org.wickedsource.hooked.svn;

import org.pojava.datetime.DateTime;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNRevisionProperty;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc.admin.SVNLookClient;

import java.io.File;
import java.util.Date;

/**
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class SVNLookClientTest {

    public static void main(String[] args) throws SVNException {
        SVNLookClient client = createSVNLookClient();
        String dateString = client.doGetRevisionProperty(new File("D:/repos"), SVNRevisionProperty.DATE,
                SVNRevision.create(1l)).getString();
        DateTime date = DateTime.parse(dateString);
        System.out.println(date.toDate());
    }

    private static SVNLookClient createSVNLookClient() {
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager cm = SVNClientManager.newInstance(options);
        SVNLookClient client = cm.getLookClient();
        return client;
    }
}
