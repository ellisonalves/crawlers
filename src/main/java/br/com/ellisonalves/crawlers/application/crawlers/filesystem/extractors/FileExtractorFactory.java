package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

/**
 *
 * @author ellison
 */
public class FileExtractorFactory {

    public static Extractor getFileExtractor(final String fileExtension) {
        if (null != fileExtension) {
            switch (fileExtension) {
                case ".txt":
                    return TxtExtractor.getInstance();
                case ".pdf":
                    return PDFFileExtractor.getInstance();
                case ".doc":
                    return DocFileExtractor.getInstance();
                case ".ppt":
                    return PPTFileExtractor.getInstance();
                case ".xls":
                    return XLSFileExtractor.getInstance();
                default:
                    throw new RuntimeException("A instância " + fileExtension + " ainda não foi implementada");
            }
        }
        throw new RuntimeException("Nenhuma instância válida pode ser criada.");
    }
}
