package org.exoplatform.addons.persistence.services.jdbc;

import com.google.inject.AbstractModule;
import org.exoplatform.addons.persistence.services.StatisticsService;

/**
 * Created by menzli on 22/04/14.
 */
public class JDBCModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StatisticsService.class).to(StatisticsServiceImpl.class);
    }
}
