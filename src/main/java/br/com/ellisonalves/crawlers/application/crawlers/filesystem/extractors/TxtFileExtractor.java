package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;
import br.com.ellisonalves.crawlers.application.crawlers.FileExtractor;
import br.com.ellisonalves.crawlers.domain.valueobject.FileData;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class TxtExtractor implements FileExtractor {
    @Override
    public ExtractedData extract(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader in = new BufferedReader(new InputStreamReader(fis))) {

            String linha;
            StringBuilder sb = new StringBuilder();

            while ((linha = in.readLine()) != null) {
                sb.append(linha);
            }

            return FileData.create(sb.toString(), null, null, null);
        } catch (IOException ex) {
            Logger.getLogger(TxtExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);

            throw new RuntimeException(ex);
        }
    }
}
