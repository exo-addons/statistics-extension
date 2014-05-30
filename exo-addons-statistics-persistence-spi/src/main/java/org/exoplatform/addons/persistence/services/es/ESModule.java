package org.exoplatform.addons.persistence.services.es;

import com.google.inject.AbstractModule;
import org.exoplatform.addons.persistence.services.StatisticsService;

/**
 * Created by menzli on 22/04/14.
 */
public class ESModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StatisticsService.class).to(StatisticsServiceImpl.class);
    }

}
