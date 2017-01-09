package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TxtExtractor implements Extractor {

    private static final TxtExtractor INSTANCE = new TxtExtractor();
    
    public static TxtExtractor getInstance() {
        return INSTANCE;
    }

    private TxtExtractor() {

    }

    @Override
    public String extract(File file) {
        try (FileInputStream fis = new FileInputStream(file);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis))) {

            String linha;
            StringBuilder sb = new StringBuilder();

            while ((linha = in.readLine()) != null) {
                sb.append(linha);
            }

            return sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(TxtExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);

            throw new RuntimeException(ex);
        }
    }

}
