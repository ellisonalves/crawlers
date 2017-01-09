package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocFileExtractor implements Extractor {

    private static final DocFileExtractor INSTANCE = new DocFileExtractor();
    
    public static DocFileExtractor getInstance() {
        return INSTANCE;
    }

    private DocFileExtractor() {
    }

    @Override
    public String extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            WordExtractor wordExtractor = new WordExtractor(fs);
            return wordExtractor.getText();
        } catch (IOException ex) {
            Logger.getLogger(DocFileExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
