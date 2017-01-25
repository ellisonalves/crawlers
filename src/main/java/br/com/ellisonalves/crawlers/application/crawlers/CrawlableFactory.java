package br.com.ellisonalves.crawlers.application.crawlers;

import br.com.ellisonalves.crawlers.application.crawlers.filesystem.FileSystemCrawler;
import br.com.ellisonalves.crawlers.application.crawlers.gmail.GmailCrawler;
import br.com.ellisonalves.crawlers.application.crawlers.rss.RssCrawler;
import br.com.ellisonalves.crawlers.application.crawlers.twitter.TwitterCrawler;
import br.com.ellisonalves.crawlers.application.crawlers.web.WebPageCrawler;

import static br.com.ellisonalves.crawlers.domain.repository.RepositoryInstances.getDocumentRepositoryInstance;

/**
 * Instâncias dos tipos de crawlers disponiveis.
 *
 * @author ellison
 */
public enum CrawlableFactory {

    WEB_PAGES("wp", new WebPageCrawler()),
    FILE_SYSTEM("fs", new FileSystemCrawler(getDocumentRepositoryInstance())),
    TWITTER("twt", new TwitterCrawler()),
    GMAIL("gmail", new GmailCrawler()),
    RSS("rss", new RssCrawler());

    private final String abreviacao;
    private final Crawlable crawlable;

    CrawlableFactory(String abreviacao, Crawlable crawlable) {
        this.abreviacao = abreviacao;
        this.crawlable = crawlable;
    }

    public static Crawlable createCrawlableFrom(String abreviacao) {
        return from(abreviacao).crawlable;
    }

    private static CrawlableFactory from(String abreviacao) {
        for (CrawlableFactory dce : CrawlableFactory.values()) {
            if (dce.abreviacao.equals(abreviacao)) {
                return dce;
            }
        }

        return null;
    }

}
