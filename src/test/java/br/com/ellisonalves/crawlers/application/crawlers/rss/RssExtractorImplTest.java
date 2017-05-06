package br.com.ellisonalves.crawlers.application.crawlers.rss;

import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by ellison on 06/05/17.
 */
public class RssExtractorImplTest {

    private RssExtractor rssExtractor;

    private SyndFeedWrapperImpl syndFeedWrapper;

    @Before
    public void setUp() throws Exception {
        syndFeedWrapper = mock(SyndFeedWrapperImpl.class);
        rssExtractor = new RssExtractorImpl(syndFeedWrapper);
    }

    @After
    public void tearDown() throws Exception {
        reset(syndFeedWrapper);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowMalformedURLExceptionWhenAnInvalidURLIsInformed() {
        try {
            String malformedUrl = "any_url_here";
            doCallRealMethod().when(syndFeedWrapper).getEntriesFor(malformedUrl);
            doCallRealMethod().when(syndFeedWrapper).getSyndFeed(malformedUrl);

            rssExtractor.extract(malformedUrl);
        } catch (RuntimeException e) {
            assertEquals("java.net.MalformedURLException: no protocol: any_url_here", e.getMessage());
            throw e;
        }
    }

    @Test
    public void shouldReadFromFeedUrlAndTranslateItToExtractedDataInterface() {
        List<ExtractedData> extractedDataList = rssExtractor.extract("http://feed.scicast.com.br/");
        assertTrue(extractedDataList.isEmpty());
    }

}