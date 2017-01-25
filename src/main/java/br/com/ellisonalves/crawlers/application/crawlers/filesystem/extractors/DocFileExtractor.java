package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;

class DocFileExtractor extends OLEFileExtractorTemplate {
    @Override
    protected POIOLE2TextExtractor createExtractorFor(InputStream inputStream) throws IOException {
        return new WordExtractor(new POIFSFileSystem(inputStream));
    }
}
