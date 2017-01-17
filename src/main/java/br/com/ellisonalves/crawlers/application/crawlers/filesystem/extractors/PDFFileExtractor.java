package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFFileExtractor implements FileExtractor {

    private static final Logger LOGGER = Logger.getLogger(PDFFileExtractor.class.getName());

    private static final PDFFileExtractor INSTANCE = new PDFFileExtractor();

    public static final PDFFileExtractor getInstance() {
        return INSTANCE;
    }

    private PDFFileExtractor() {

    }

    @Override
    public ExtractedData extract(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            return FileData.create(stripper.getText(document));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, 
                    "I couldn''t read the file {0} because: {1}", 
                    new Object[] {
                        file.getName(), 
                        ex.getCause().getMessage()
                    }
            );
        }
        return FileData.create("");
    }

}
