package br.com.ellisonalves.crawlers.application.crawlers.rss;

import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;

/**
 * Created by ellison on 06/05/17.
 */
public interface SyndFeedWrapper {

    SyndFeed getSyndFeed(String feedUrl);

    List getEntriesFor(String feedUrl);

}
