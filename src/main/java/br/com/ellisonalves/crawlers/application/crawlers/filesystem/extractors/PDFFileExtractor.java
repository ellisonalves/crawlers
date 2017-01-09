package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFFileExtractor implements Extractor {

    private static final PDFFileExtractor INSTANCE = new PDFFileExtractor();
    
    public static PDFFileExtractor getInstance() {
        return INSTANCE;
    }

    private PDFFileExtractor() {

    }

    @Override
    public String extract(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException ex) {
            Logger.getLogger(PDFFileExtractor.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new RuntimeException("File: " + file.getAbsolutePath(), ex);
        }
    }

}
