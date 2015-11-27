package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class PPTFileExtractor implements Extractor {

    private static final PPTFileExtractor INSTANCE = new PPTFileExtractor();
    
    public static PPTFileExtractor getInstance() {
        return INSTANCE;
    }

    private PPTFileExtractor() {

    }

    @Override
    public String extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            PowerPointExtractor pptExtractor = new PowerPointExtractor(fs);
            return pptExtractor.getText();
        } catch (IOException ex) {
            Logger.getLogger(PPTFileExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
