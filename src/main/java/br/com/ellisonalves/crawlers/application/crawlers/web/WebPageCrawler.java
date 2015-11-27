package br.com.ellisonalves.crawlers.application.crawlers.web;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ellison
 */
public class WebPageCrawler implements Crawlable {

    private static final Logger LOGGER = Logger.getLogger(WebPageCrawler.class.getName());

    @Override
    public void crawl(Map<String, Object> config) {
        LOGGER.info("crawl ... pode demorar ...");
        String crawlStorageFolder = "./data/crawl/root";
        int numberOfCrawlers = 1;

        CrawlConfig crawlerConfig = new CrawlConfig();
        crawlerConfig.setCrawlStorageFolder(crawlStorageFolder);
        crawlerConfig.setMaxDepthOfCrawling(Integer.valueOf(config.get(Crawlable.PROFUNDIDADE_EXPLORACAO_PARAM).toString()));

        try {
            PageFetcher pageFetcher = new PageFetcher(crawlerConfig);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

            CrawlController controller = new CrawlController(crawlerConfig, pageFetcher, robotstxtServer);
            controller.addSeed((String) config.get(URL_PARAM));

            LOGGER.info("start crawler...");
            controller.start(Crawler4jWebCrawler.class, numberOfCrawlers);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

}
