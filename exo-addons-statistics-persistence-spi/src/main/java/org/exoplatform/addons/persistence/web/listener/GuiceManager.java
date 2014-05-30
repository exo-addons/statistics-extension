package org.exoplatform.addons.persistence.web.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.exoplatform.addons.persistence.services.es.ESModule;
import org.exoplatform.addons.persistence.services.jdbc.JDBCModule;
import org.exoplatform.addons.persistence.services.mongodb.MongoModule;
import org.exoplatform.addons.utils.Constants;
import org.exoplatform.addons.utils.PropertyManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class GuiceManager implements ServletContextListener
{

  private static Logger log = Logger.getLogger("GuiceManager");

  private static Injector injector_;

    private static String implementation ="mongodb";

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

      log.info("INITIALIZING GUICE");
      GuiceManager.forceNew();

  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {

      log.info("CLOSING GUICE");

  }

  public static Injector getInstance()
  {
    return injector_;
  }

  public static void forceNew()
  {
      if (PropertyManager.getProperty(PropertyManager.DB_PERSISTENCE_IMPLEMENTATION) != null) {

          implementation = PropertyManager.getProperty(PropertyManager.DB_PERSISTENCE_IMPLEMENTATION);
      }

      //TODO : what about adding new implementation, do we add new if bloc !?!

      if (injector_== null) {

          if (implementation.equalsIgnoreCase(PropertyManager.DB_PERSISTENCE_IMPLEMENTATION_MONGODB)) {


              injector_ = Guice.createInjector(new MongoModule());


          } else if (implementation.equalsIgnoreCase (PropertyManager.DB_PERSISTENCE_IMPLEMENTATION_ES)) {

              injector_ = Guice.createInjector(new ESModule());

          } else if (implementation.equalsIgnoreCase (PropertyManager.DB_PERSISTENCE_IMPLEMENTATION_JDBC)) {

              injector_ = Guice.createInjector(new JDBCModule());

          } else {

              injector_ = Guice.createInjector(new MongoModule());

          }

      }
  }
}
