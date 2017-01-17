package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XLSFileExtractor implements FileExtractor {

    private static final XLSFileExtractor INSTANCE = new XLSFileExtractor();

    public static final XLSFileExtractor getInstance() {
        return INSTANCE;
    }

    private XLSFileExtractor() {
    }

    @Override
    public ExtractedData extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            ExcelExtractor excelExtractor = new ExcelExtractor(fs);
            return FileData.create(excelExtractor.getText());
        } catch (IOException ex) {
            Logger.getLogger(XLSFileExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);

            throw new RuntimeException(ex);
        }
    }

}
