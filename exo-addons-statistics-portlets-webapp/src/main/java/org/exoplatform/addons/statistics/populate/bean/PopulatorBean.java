package org.exoplatform.addons.statistics.populate.bean;

import java.util.List;

/**
 * Created by menzli on 22/05/14.
 */
public class PopulatorBean {

    List<StatisticTO> statistics;

    public List<StatisticTO> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticTO> statistics) {
        this.statistics = statistics;
    }
}
