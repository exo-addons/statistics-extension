/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.exoplatform.addons.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static Properties properties;

    private static final String PROPERTIES_PATH = System.getProperty("catalina.base")+"/conf/persistence.properties";

    private static final String PROPERTY_DB_STATISTICS_SYSTEM_PREFIX = "statistic.";

    //TODO : use unified parameters label to connect to DB for all services Statistics|Chat|Notification
    /** DB connection parameters*/
    public static final String PROPERTY_DB_SERVER_NAME = "database.server.name";

    public static final String PROPERTY_DB_SERVER_TYPE = "database.server.type";

    public static final String PROPERTY_DB_SERVER_HOST = "database.server.host";

    public static final String PROPERTY_DB_SERVER_PORT = "database.server.port";

    public static final String PROPERTY_DB_SERVER_AUTHENTICATION = "database.server.authentication";

    public static final String PROPERTY_DB_SERVER_USER = "database.server.user";

    public static final String PROPERTY_DB_SERVER_PASSWORD = "database.server.password";

    public static final String PROPERTY_DB_SERVER_TYPE_EMBED = "embed";

    public static final String PROPERTY_DB_SERVER_TYPE_MONGO = "mongo";
    /** FIN */

    /** Constants to init SP modules*/

    public static final String  DB_PERSISTENCE_IMPLEMENTATION = "db.persistence.implementation";

    public static final String  DB_PERSISTENCE_IMPLEMENTATION_MONGODB = "mongodb";

    public static final String  DB_PERSISTENCE_IMPLEMENTATION_ES = "elasticsearch";

    public static final String  DB_PERSISTENCE_IMPLEMENTATION_JDBC = "jdbc";
    /** FIN */

  public static String getProperty(String key)
  {
    String value = (String)properties().get(key);
    //System.out.println("PROP:"+key+"="+value);
    return value;
  }

  private static Properties properties()
  {
    if (properties==null)
    {
      properties = new Properties();
      InputStream stream = null;
      try
      {
        stream = new FileInputStream(PROPERTIES_PATH);
        properties.load(stream);
        stream.close();
      }
      catch (Exception e)
      {
      }

      overridePropertyIfNotSet(DB_PERSISTENCE_IMPLEMENTATION, DB_PERSISTENCE_IMPLEMENTATION_MONGODB);
      overridePropertyIfNotSet(PROPERTY_DB_SERVER_TYPE, PROPERTY_DB_SERVER_TYPE_EMBED);
      overridePropertyIfNotSet(PROPERTY_DB_SERVER_HOST, "localhost");
      overridePropertyIfNotSet(PROPERTY_DB_SERVER_PORT, "27017");
      overridePropertyIfNotSet(PROPERTY_DB_SERVER_NAME, "persistence-sp");
      overridePropertyIfNotSet(PROPERTY_DB_SERVER_AUTHENTICATION, "false");
      overridePropertyIfNotSet(PROPERTY_DB_SERVER_USER, "");
      overridePropertyIfNotSet(PROPERTY_DB_SERVER_PASSWORD, "");
    }
    return properties;
  }

  private static void overridePropertyIfNotSet(String key, String value) {
    if (properties().getProperty(key)==null)
    {
      properties().setProperty(key, value);
    }
    if (System.getProperty(PROPERTY_DB_STATISTICS_SYSTEM_PREFIX+key)!=null) {
      properties().setProperty(key, System.getProperty(PROPERTY_DB_STATISTICS_SYSTEM_PREFIX+key));
    }

  }

  public static void overrideProperty(String key, String value) {
    properties().setProperty(key, value);
  }
}
