package br.com.ellisonalves.crawlers.application.crawlers.rss;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by ellison on 06/05/17.
 */
public class SyndFeedWrapperImpl implements SyndFeedWrapper {

    private SyndFeedInput feedInput;

    public SyndFeedWrapperImpl(SyndFeedInput feedInput) {
        this.feedInput = feedInput;
    }

    public SyndFeed getSyndFeed(String feedUrl) {
        try {
            URL url = new URL(feedUrl);
            return feedInput.build(new XmlReader(url));
        } catch (FeedException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List getEntriesFor(String feedUrl) {
        SyndFeed syndFeed = getSyndFeed(feedUrl);
        return syndFeed.getEntries();
    }
}
