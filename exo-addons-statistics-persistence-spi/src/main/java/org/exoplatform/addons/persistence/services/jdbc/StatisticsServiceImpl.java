package org.exoplatform.addons.persistence.services.jdbc;

import org.exoplatform.addons.persistence.bo.StatisticBO;
import org.exoplatform.addons.persistence.services.StatisticsService;
import org.exoplatform.services.rest.resource.ResourceContainer;

import java.util.List;

/**
 * Created by menzli on 21/04/14.
 */
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public void cleanupStatistics(long timestamp) throws Exception {
        throw new UnsupportedOperationException( "Method not yet implemented" );
    }

    @Override
    public List<StatisticBO> search(String word, String type, int offset, int limit, int sort, int order, long timestamp) throws Exception {
        throw new UnsupportedOperationException( "Method not yet implemented" );
    }

    @Override
    public List<StatisticBO> filter(String user, String category, String categoryId, String type, String site, String siteType, String content, boolean isPrivate, long timestamp) throws Exception {
        throw new UnsupportedOperationException( "Method not yet implemented" );
    }

    @Override
    public StatisticBO addEntry(String user, String from, String type, String category, String categoryId, String content, String link, String site, String siteType) throws Exception {
        throw new UnsupportedOperationException( "Method not yet implemented" );

    }

    @Override
    public List<StatisticBO> getStatistics(long timestamp) throws Exception {
        throw new UnsupportedOperationException( "Method not yet implemented" );
    }

    @Override
    public void exportStatistics() throws Exception {
        throw new UnsupportedOperationException( "Method not yet implemented" );
    }

    @Override
    public boolean importStatistics() throws Exception {
        throw new UnsupportedOperationException( "Method not yet implemented" );
    }
}
