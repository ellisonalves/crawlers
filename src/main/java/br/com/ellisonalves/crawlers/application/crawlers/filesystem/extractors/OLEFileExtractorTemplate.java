package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;
import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.hpsf.SummaryInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Act as a template in order to group the same operations required by
 * documents like word, excel and powerpoint.
 * <p>
 * Created by ellison on 19/01/17.
 */
abstract class OLEFileExtractorTemplate implements FileExtractor {

    @Override
    public ExtractedData extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIOLE2TextExtractor extractor = createExtractorFor(fis);

            SummaryInformation summaryInformation = extractor.getSummaryInformation();

            return FileData.create(extractor.getText(),
                    summaryInformation.getCreateDateTime(),
                    summaryInformation.getAuthor(),
                    summaryInformation.getTitle()
            );
        } catch (IOException ex) {
            Logger.getLogger(OLEFileExtractorTemplate.class.getName()).log(
                    Level.SEVERE,
                    "An error occurred",
                    ex
            );
            throw new RuntimeException(ex);
        }
    }

    protected abstract POIOLE2TextExtractor createExtractorFor(InputStream inputStream) throws IOException;
}
