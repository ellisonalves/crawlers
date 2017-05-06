package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;
import br.com.ellisonalves.crawlers.application.crawlers.FileExtractor;
import br.com.ellisonalves.crawlers.domain.valueobject.FileData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class PDFFileExtractor implements FileExtractor {

    private static final Logger LOGGER = Logger.getLogger(PDFFileExtractor.class.getName());

    @Override
    public ExtractedData extract(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            return FileData.create(stripper.getText(document), null, null, null);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "I couldn't read the file {0} because: {1}",
                    new Object[]{
                            file.getName(),
                            ex.getCause().getMessage()
                    }
            );
        }
        return FileData.create("", null, null, null);
    }
}
