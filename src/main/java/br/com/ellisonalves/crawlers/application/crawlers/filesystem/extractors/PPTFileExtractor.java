package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PPTFileExtractor implements FileExtractor {

    private static final PPTFileExtractor INSTANCE = new PPTFileExtractor();

    public static final PPTFileExtractor getInstance() {
        return INSTANCE;
    }

    private PPTFileExtractor() {

    }

    @Override
    public ExtractedData extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            PowerPointExtractor pptExtractor = new PowerPointExtractor(fs);
            return FileData.create(pptExtractor.getText());
        } catch (IOException ex) {
            Logger.getLogger(PPTFileExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
