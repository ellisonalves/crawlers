package br.com.ellisonalves.crawlers.application.crawlers.rss;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Extrai o conteúdo da tag descripts dos feeds rss.
 *
 * @author ellison
 */
public class RssCrawler implements Crawlable {

    private static final Logger LOGGER = Logger.getLogger(RssCrawler.class.getName());

    @Override
    public void crawl(Map<String, Object> config) {

        final String feedUrl = (String) config.get(Crawlable.URL_PARAM);

        try {
            URL url = new URL(feedUrl);

            LOGGER.info("Lendo o feed. Isso pode demorar um pouco...");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));

            final Iterator entries = feed.getEntries().iterator();
            final StringBuilder sb = new StringBuilder();

            while (entries.hasNext()) {
                SyndEntry syndEntry = (SyndEntry) entries.next();

                sb.append("Title: ")
                        .append(syndEntry.getTitle())
                        .append("\n");
                sb.append("Author: ")
                        .append(syndEntry.getAuthor())
                        .append("\n");;
                sb.append("Link: ")
                        .append(syndEntry.getLink())
                        .append("\n");;
                sb.append("URI: ")
                        .append(syndEntry.getUri())
                        .append("\n");;
                sb.append("PublishedDate: ")
                        .append(syndEntry.getPublishedDate())
                        .append("\n");;
                sb.append("UpdatedDate: ")
                        .append(syndEntry.getUpdatedDate())
                        .append("\n");;
                sb.append("DescriptionTagValue: ")
                        .append(syndEntry.getDescription().getValue())
                        .append("\n\n");
            }
            
            LOGGER.info(sb.toString());
            LOGGER.info("Terminei. Processando a tag description do feed...");

        } catch (IllegalArgumentException | FeedException | IOException ex) {
            LOGGER.log(Level.SEVERE, "Ocorreu algum erro durante o parser do feed", ex);
            throw new RuntimeException(ex);
        }

        LOGGER.info("Documento pronto para continuar...");
    }

}
