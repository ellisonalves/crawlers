package br.com.ellisonalves.crawlers.application.crawlers.filesystem;

import br.com.ellisonalves.crawlers.application.crawlers.ConfigParameterNotFoundException;
import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors.FileExtractor;
import br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors.FileExtractorFactory;
import br.com.ellisonalves.crawlers.domain.repository.DocumentRepository;
import org.apache.commons.lang.Validate;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Crawler that explores files for a specified path in the file system.
 *
 * @author ellison
 */
public class FileSystemCrawler implements Crawlable {

    private final static Logger LOGGER = Logger.getLogger(FileSystemCrawler.class.getName());
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(txt|pdf|doc|xls|ppt))$");

    private DocumentRepository documentRepository;

    public FileSystemCrawler(DocumentRepository documentRepository) {
        Validate.notNull(documentRepository, "Cannot create a FileSystemCrawler without a documentRepository.");
        this.documentRepository = documentRepository;
    }

    @Override
    public void crawl(Map<String, Object> config) {
        final Object initialPath = config.get(Crawlable.INITIAL_PATH_PARAM);
        final Object searchDepth = config.get(Crawlable.SEARCH_DEPTH_PARAM);

        if (initialPath == null || searchDepth == null) {
            throw new ConfigParameterNotFoundException("Missing required parameters: ", config);
        }

        File file = new File((String) initialPath);
        explore(file, (int) searchDepth);
    }

    private void explore(File file, int depth) {
        if (depth != -1) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null)
                    for (File subFile : files) {
                        explore(subFile, depth - 1);
                    }
            } else {
                extract(file);
            }
        }
    }

    private void extract(File file) {
        if (!file.isDirectory() && FILTERS.matcher(file.getName()).matches()) {
            final String name = file.getName();
            final String fileExtension = name.substring(name.lastIndexOf("."), name.length());

            LOGGER.log(Level.INFO, "Opening the file: {0}", name);
            FileExtractor extractor = FileExtractorFactory.extractorOf(fileExtension);
            documentRepository.insert(extractor.extract(file));
        }
    }

}