package org.exoplatform.addons.persistence.services.mongodb;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.exoplatform.addons.persistence.services.StatisticsService;
import org.exoplatform.addons.persistence.services.mongodb.StatisticsServiceImpl;

/**
 * Created by menzli on 22/04/14.
 */
public class MongoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StatisticsService.class).to(StatisticsServiceImpl.class).in(Scopes.SINGLETON);
    }
}
