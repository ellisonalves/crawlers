package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

/**
 * @author ellison
 */
public enum FileExtractorFactory {

    TXT(".txt", new TxtExtractor()),
    PDF(".pdf", new PDFFileExtractor()),
    DOC(".doc", new DocFileFileExtractor()),
    PPT(".ppt", new PPTFileExtractor()),
    XLS(".xls", new XLSFileExtractor());

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
        throw new RuntimeException("Any instance of the extension " + fileExtension + " could be created.");
    }

}
