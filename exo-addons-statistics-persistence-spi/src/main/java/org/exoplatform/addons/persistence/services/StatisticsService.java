package org.exoplatform.addons.persistence.services;

import org.exoplatform.addons.persistence.bo.StatisticBO;

import java.util.List;

/**
 * Created by menzli on 21/04/14.
 */
public interface StatisticsService {

    //TODO : add methods to calculate count of returned results according to a given criteria

    public static final String M_STATISTICS = "statistics";

    public void cleanupStatistics(long timestamp) throws Exception;

    public List<StatisticBO> search(String word, String type, int offset, int limit, int sort, int order, long timestamp) throws Exception;

    public List<StatisticBO> filter(String user, String category, String categoryId, String type, String site, String siteType, String content, boolean isPrivate, long timestamp) throws Exception;

    public StatisticBO addEntry(String user, String from, String type, String category, String categoryId, String content, String link, String site, String siteType) throws Exception;

    public List<StatisticBO> getStatistics(long timestamp) throws Exception;

    public void exportStatistics() throws Exception;

    public boolean importStatistics() throws Exception;
}
