package br.com.ellisonalves.crawlers.application.crawlers.rss;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import br.com.ellisonalves.crawlers.domain.repository.DocumentRepository;
import org.apache.commons.lang.Validate;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Extrai o conteúdo da tag descripts dos feeds rss.
 *
 * @author ellison
 */
public class RssCrawler implements Crawlable {

    private static final Logger LOGGER = Logger.getLogger(RssCrawler.class.getName());

    private RssExtractor rssExtractor;

    private DocumentRepository documentRepository;

    @Override
    public void crawl(Map<String, Object> config) {

        final String feedUrl = (String) config.get(Crawlable.URL_PARAM);

        Validate.notNull(feedUrl, "must have a feed url parameter");

        documentRepository.insert(rssExtractor.extract(feedUrl));
    }

}
