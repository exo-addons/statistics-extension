package org.exoplatform.addons.persistence.services.mongodb;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.exoplatform.addons.persistence.AbstractTestCase;
import org.exoplatform.addons.persistence.bo.StatisticBO;
import org.exoplatform.addons.persistence.bootstrap.ServiceBootstrap;
import org.exoplatform.addons.persistence.services.StatisticsService;
import org.exoplatform.addons.persistence.web.listener.ConnectionManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by menzli on 29/04/14.
 */
public class StatisticsServiceImplTestCase extends AbstractTestCase {

    String user = "user";
    String from = "from";
    String type = "type";
    String category = "category";
    String categoryId = "categoryId";
    String content = "content";
    String link = "link";
    String site = "site";
    String siteType = "siteType";
    boolean isRead = true;


    StatisticsService statisticsService;

    List<StatisticBO> statisticBOs = null;

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Before
    public void setUp() {

        ConnectionManager.getInstance().getDB().getCollection(StatisticsService.M_STATISTICS).drop();

        statisticBOs = new ArrayList<StatisticBO>();

        statisticsService = ServiceBootstrap.getStatisticsService();

    }

    @Test
    //@PerfTest(invocations = 10, threads = 1)
    public void testAddEntry() throws Exception {

        addStatistics(20,null,null,null,null,null,null);

        statisticBOs = statisticsService.getStatistics(0);

        assertThat(statisticBOs.size()).isEqualTo(20);

        statisticsService.cleanupStatistics(0);

    }
    /** When fired alone the testcase pass but when activated with other unit test it fail : I don't know why ??!!!
     @Test
     public void testCleanupStatistics() throws Exception {

     addStatistics(50,null,null,null);

     long currentTimestamp1 = System.currentTimeMillis();

     Thread.currentThread().sleep(6*1000);

     addStatistics(50,null,null,null);

     long currentTimestamp2 = System.currentTimeMillis();

     Thread.currentThread().sleep(6*1000);

     statisticsService.cleanupStatistics(currentTimestamp1);

     statisticBOs = statisticsService.getStatistics(0);

     assertThat(statisticBOs.size()).isEqualTo(50);

     statisticsService.cleanupStatistics(currentTimestamp2);

     statisticBOs = statisticsService.getStatistics(0);

     assertThat(statisticBOs.size()).isEqualTo(0);

     }
     */

    @Test
    @PerfTest(invocations = 5, threads = 1)
    public void testGetAllStatistics() throws Exception {

        addStatistics(5,null,null,null,null,null,null);

        statisticBOs = statisticsService.getStatistics(0);

        assertThat(statisticBOs.size()).isEqualTo(5);

        statisticsService.cleanupStatistics(0);

        addStatistics(10,null,null,null,null,null,null);

        statisticBOs = statisticsService.getStatistics(0);

        assertThat(statisticBOs.size()).isEqualTo(10);

        statisticsService.cleanupStatistics(0);

        addStatistics(20,null,null,null,null,null,null);

        statisticBOs = statisticsService.getStatistics(0);

        assertThat(statisticBOs.size()).isEqualTo(20);

        statisticsService.cleanupStatistics(0);



    }

    @Test
    public void testFilter() throws Exception {

        addStatistics(5,null,null,null,null,null,null);
        // user, category, categoryId, type, isPrivate, timestamp)
        statisticBOs = statisticsService.filter("user1", null ,null ,null ,null ,null, null ,false ,0);

        assertThat(statisticBOs.size()).isEqualTo(1);

        statisticBOs = statisticsService.filter("user1" ,null ,"AAA" ,null ,null ,null ,null, false,0);

        assertThat(statisticBOs.size()).isEqualTo(0);

        statisticBOs = statisticsService.filter("user1" ,null ,"categoryId1" ,null ,null ,null ,null ,false ,0);

        assertThat(statisticBOs.size()).isEqualTo(1);

        statisticsService.cleanupStatistics(0);

        addStatistics(5,"khemais", null, null, null, null,null);

        long currenttime1 = System.currentTimeMillis();

        Thread.currentThread().sleep(3*1000);

        addStatistics(5 ,"khemais" ,"category" ,null ,null, null,null);

        statisticBOs = statisticsService.filter("khemais" ,"category0" ,null ,null ,null ,null, null ,false ,currenttime1);

        assertThat(statisticBOs.size()).isEqualTo(1);

        statisticBOs = statisticsService.filter(null ,null , null ,null ,null,null ,null ,false,currenttime1);

        assertThat(statisticBOs.size()).isEqualTo(15);


        /** TEST Search by Content **/

        //--- cleanup all statistics
        statisticsService.cleanupStatistics(0);

        //--- add statistics
        addStatistics(5,null,null,null,null,null,"Je suis un exoers");

        //--- Should returns all tuples
        statisticBOs = statisticsService.filter( null,null ,null ,null ,null ,null, null ,false ,currenttime1);

        assertThat(statisticBOs.size()).isEqualTo(10);

        //--- return only tuples with content contains%suis%
        statisticBOs = statisticsService.filter( null,null ,null ,null ,null ,null, "suis" ,false ,currenttime1);

        assertThat(statisticBOs.size()).isEqualTo(5);

        //--- return only tuples with content contains%pres%
        statisticBOs = statisticsService.filter( null,null ,null ,null ,null ,null, "pres" ,false ,currenttime1);

        assertThat(statisticBOs.size()).isEqualTo(0);


        /** FIN **/



    }

    @Test
    public void testExportStatistics() throws Exception {

    }

    @Test
    public void testImportStatistics() throws Exception {

    }


    @Test
    public void testSearch() throws Exception {

        addStatistics(5,"demo","presales","exoplatform", null, null,null);

        statisticBOs = statisticsService.search("demo","user",10,10,1,1,0);

        assertThat(statisticBOs.size()).isEqualTo(5);

        statisticBOs = statisticsService.search("presales","category",10,10,1,1,0);

        assertThat(statisticBOs.size()).isEqualTo(5);

        statisticBOs = statisticsService.search("exoplatform","type",10,10,1,1,0);

        assertThat(statisticBOs.size()).isEqualTo(5);

        statisticBOs = statisticsService.search("exoplatform","DSE",10,10,1,1,0);

        assertThat(statisticBOs.size()).isEqualTo(0);

        statisticBOs = statisticsService.search("sd","type",10,10,1,1,0);

        assertThat(statisticBOs.size()).isEqualTo(0);

        statisticBOs = statisticsService.search("fer","ALL",10,10,1,1,0);

        assertThat(statisticBOs.size()).isEqualTo(0);

    }

    private void addStatistics (int total, String theUser, String theCategory, String theType, String theSite, String theSiteType, String theContent) throws Exception {

        for (int i = 0; i < total; i++) {

            if ( theCategory != null) {

                statisticsService.addEntry(user+i, from+i, type+i, theCategory, categoryId+i, content+i, link+i, site+i, siteType+i);

            }

            if ( theType != null) {

                statisticsService.addEntry(user+i, from+i, theType, category+i, categoryId+i, content+i, link+i, site+i, siteType+i);

            }

            if ( theUser != null) {

                statisticsService.addEntry(theUser, from+i, type+i, category+i, categoryId+i, content+i, link+i, site+i, siteType+i);

            }
            if ( theContent != null) {

                statisticsService.addEntry(user+i, from+i, type+i, category+i, categoryId+i, theContent, link+i, site+i, siteType+i);

            }


            statisticsService.addEntry(user+i, from+i, type+i , category+i, categoryId+i, content+i, link+i, site+i, siteType+i);

        }

    }


}
