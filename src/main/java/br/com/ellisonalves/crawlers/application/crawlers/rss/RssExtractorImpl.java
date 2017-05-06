package br.com.ellisonalves.crawlers.application.crawlers.rss;

import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;
import br.com.ellisonalves.crawlers.domain.valueobject.RssData;
import com.sun.syndication.feed.synd.SyndEntry;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ellison on 06/05/17.
 */
public class RssExtractorImpl implements RssExtractor {

    private static final Logger LOGGER = Logger.getLogger(RssExtractorImpl.class.getName());

    private SyndFeedWrapper syndFeedWrapper;

    public RssExtractorImpl(SyndFeedWrapper syndFeedWrapper) {
        this.syndFeedWrapper = syndFeedWrapper;
    }

    @Override
    public List<ExtractedData> extract(String feedUrl) {
        final List<ExtractedData> extractedDataList = new LinkedList<>();
        LOGGER.info("Reading the feed, it can take sometime...");
        try {
            final Iterator entries = syndFeedWrapper.getEntriesFor(feedUrl).iterator();

            while (entries.hasNext()) {
                SyndEntry syndEntry = (SyndEntry) entries.next();
                RssData rssData = new RssData(syndEntry.getDescription().getValue(),
                        syndEntry.getPublishedDate(),
                        syndEntry.getAuthor(),
                        syndEntry.getTitle(),
                        syndEntry.getLink(),
                        syndEntry.getUpdatedDate()
                );
                extractedDataList.add(rssData);
            }
            LOGGER.info("Finished. Processando a tag description do feed...");
        } catch (IllegalArgumentException ex) {
            LOGGER.info("Ocorreu algum erro durante o parser do feed");
            throw new RuntimeException(ex);
        }
        return extractedDataList;
    }
}
