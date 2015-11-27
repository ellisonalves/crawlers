package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import java.io.File;

/**
 *
 * @author ellison
 */
public interface Extractor {

    String extract(File file);

}
