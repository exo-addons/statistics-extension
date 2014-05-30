package org.exoplatform.addons.persistence.bootstrap;

import org.exoplatform.addons.persistence.services.StatisticsService;
import org.exoplatform.addons.persistence.web.listener.GuiceManager;
/**
 * Created by menzli on 29/04/14.
 */
public class ServiceBootstrap {

    private static StatisticsService statisticsService;

    public static void init() {

        statisticsService = GuiceManager.getInstance().getInstance(StatisticsService.class);

    }

    public static StatisticsService getStatisticsService() {

        return statisticsService;

    }

}
