package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocFileExtractor implements FileExtractor {

    private static final DocFileExtractor INSTANCE = new DocFileExtractor();

    public static final DocFileExtractor getInstance() {
        return INSTANCE;
    }

    private DocFileExtractor() {
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public ExtractedData extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            WordExtractor wordExtractor = new WordExtractor(fs);
            return FileData.create(wordExtractor.getText());
        } catch (IOException ex) {
            Logger.getLogger(DocFileExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
