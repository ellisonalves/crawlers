package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

/**
 *
 * @author ellison
 */
public enum FileExtractorFactory {

    TXT(".txt", TxtExtractor.getInstance()),
    PDF(".pdf", PDFFileExtractor.getInstance()),
    DOC(".doc", DocFileExtractor.getInstance()),
    PPT(".ppt", PPTFileExtractor.getInstance()),
    XLS(".xls", XLSFileExtractor.getInstance());

    private final String fileExtension;
    private final FileExtractor instance;

    FileExtractorFactory(String fileExtension, FileExtractor instance) {
        this.fileExtension = fileExtension;
        this.instance = instance;
    }

    public static FileExtractor extractorOf(final String fileExtension) {
        if (null != fileExtension) {
            for (FileExtractorFactory extractor : FileExtractorFactory.values()) {
                if (fileExtension.equals(extractor.fileExtension)) {
                    return extractor.instance;
                }
            }
        }
        throw new RuntimeException("Nenhuma instância para " + fileExtension + " não pode ser criada.");
    }

}
