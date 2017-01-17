package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;
import java.io.File;

/**
 * Contract for all kinds of extractor inside the application.
 *
 * @author ellison
 */
public interface FileExtractor {

    ExtractedData extract(File file);

}
