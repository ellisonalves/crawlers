package br.com.ellisonalves.crawlers.application.crawlers.rss;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import org.junit.Before;
import org.junit.Test;
import ucar.nc2.util.HashMapLRU;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by ellison on 05/05/17.
 */
public class RssCrawlerTest {

    private RssCrawler rssCrawler;

    @Before
    public void setUp() throws Exception {
        rssCrawler = new RssCrawler();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenParametersAreMissing() throws Exception {
        rssCrawler.crawl(new HashMap<>());
    }


}