package br.com.ellisonalves.crawlers.application.crawlers.rss;

import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;
import br.com.ellisonalves.crawlers.application.crawlers.Extractor;

import java.util.List;

/**
 * Created by ellison on 06/05/17.
 */
public interface RssExtractor extends Extractor<ExtractedData> {

    List<ExtractedData> extract(String feedUrl);

}
