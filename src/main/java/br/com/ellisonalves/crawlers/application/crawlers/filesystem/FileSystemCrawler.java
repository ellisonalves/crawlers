package br.com.ellisonalves.crawlers.application.crawlers.filesystem;

import br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors.FileExtractorFactory;
import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors.Extractor;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author ellison
 */
public class FileSystemCrawler implements Crawlable {

    private final static Logger LOGGER = Logger.getLogger(FileSystemCrawler.class.getName());
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(txt|pdf|doc|xls|ppt))$");

    @Override
    public void crawl(Map<String, Object> config) {
        File file = new File((String) config.get(Crawlable.PATH_INICIAL_PARAM));
        explore(file, (int) config.get(Crawlable.PROFUNDIDADE_EXPLORACAO_PARAM));
    }

    private void explore(File file, int depth) {
        if (depth != 0) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File subFile : files) {
                    explore(subFile, depth - 1);
                }
            }
            crawlerFor(file);
        }
    }

    private void crawlerFor(File file) {
        if (!file.isDirectory() && FILTERS.matcher(file.getName()).matches()) {
            final String name = file.getName();
            final String fileExtension = name.substring(name.lastIndexOf("."), name.length());

            Extractor extractor = FileExtractorFactory.getFileExtractor(fileExtension);
            LOGGER.log(Level.INFO, extractor.extract(file));
        }
    }
}
