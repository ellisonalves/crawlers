package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class XLSFileExtractor implements Extractor {

    private static XLSFileExtractor INSTANCE = new XLSFileExtractor();
    
    public static XLSFileExtractor getInstance() {
        return INSTANCE;
    }

    private XLSFileExtractor() {
    }

    @Override
    public String extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            ExcelExtractor excelExtractor = new ExcelExtractor(fs);
            return excelExtractor.getText();
        } catch (IOException ex) {
            Logger.getLogger(XLSFileExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);

            throw new RuntimeException(ex);
        }
    }

}
