package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.application.crawlers.FileExtractor;
import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;

class XLSFileExtractor extends OLEFileExtractorTemplate implements FileExtractor {
    @Override
    protected POIOLE2TextExtractor createExtractorFor(InputStream inputStream) throws IOException {
        return new ExcelExtractor(new POIFSFileSystem(inputStream));
    }
}
