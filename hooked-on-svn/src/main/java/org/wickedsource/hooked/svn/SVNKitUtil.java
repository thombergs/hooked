package org.wickedsource.hooked.svn;

import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc.admin.SVNLookClient;

public final class SVNKitUtil {

    private SVNKitUtil() {
    }

    public static SVNLookClient createSVNLookClient() {
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager cm = SVNClientManager.newInstance(options);
        return cm.getLookClient();
    }
}