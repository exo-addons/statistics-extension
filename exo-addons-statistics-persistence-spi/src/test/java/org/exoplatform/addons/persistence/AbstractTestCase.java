package org.exoplatform.addons.persistence;

import org.exoplatform.addons.persistence.bootstrap.ServiceBootstrap;
import org.exoplatform.addons.persistence.web.listener.ConnectionManager;
import org.exoplatform.addons.persistence.web.listener.GuiceManager;
import org.exoplatform.addons.utils.PropertyManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by menzli on 29/04/14.
 */
public class AbstractTestCase {

    static Logger log = Logger.getLogger("AbstractTestCase");

    @BeforeClass
    public static void before() throws IOException {

        PropertyManager.overrideProperty(PropertyManager.PROPERTY_DB_SERVER_TYPE, PropertyManager.PROPERTY_DB_SERVER_TYPE_EMBED);

        PropertyManager.overrideProperty(PropertyManager.PROPERTY_DB_SERVER_PORT, "27017");

        ConnectionManager.forceNew();

        ConnectionManager.getInstance().getDB("unittest");

        GuiceManager.forceNew();

        ServiceBootstrap.init();

    }

    @AfterClass
    public static void teardown() throws Exception {

        ConnectionManager.getInstance().close();

    }

}
