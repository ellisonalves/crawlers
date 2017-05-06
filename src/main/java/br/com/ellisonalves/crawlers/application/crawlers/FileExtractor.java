package br.com.ellisonalves.crawlers.application.crawlers;

import java.io.File;

/**
 * Contract for all kinds of extractor inside the application.
 *
 * @author ellison
 */
public interface FileExtractor extends Extractor {

    ExtractedData extract(File file);

}
