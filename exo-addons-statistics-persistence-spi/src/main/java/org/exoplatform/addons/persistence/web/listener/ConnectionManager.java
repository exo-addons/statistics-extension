package org.exoplatform.addons.persistence.web.listener;

import org.exoplatform.addons.persistence.services.mongodb.MongoBootstrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionManager implements ServletContextListener {

  private static MongoBootstrap mongoBootstrap;
  private static Logger log = Logger.getLogger("ConnectionManager");

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

      log.info("INITIALIZING MONGODB");

      mongoBootstrap = new MongoBootstrap();
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {

      log.info("CLOSING MONGODB");

      mongoBootstrap.close();
  }

  public static MongoBootstrap getInstance()
  {
    return mongoBootstrap;
  }

  public static MongoBootstrap forceNew()
  {
    log.warning("ConnectionManager.forceNew has been used : this should never happen in Production!");
    if (mongoBootstrap!=null)
      mongoBootstrap.close();
    mongoBootstrap = new MongoBootstrap();
    return mongoBootstrap;
  }
}
