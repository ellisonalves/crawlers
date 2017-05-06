package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.application.crawlers.FileExtractor;

/**
 * Place where it is possible to put the instances of each implemented file extractor.
 *
 * @author ellison
 */
public enum FileExtractorInstances {

    TXT(new TxtExtractor()),
    PDF(new PDFFileExtractor()),
    DOC(new DocFileExtractor()),
    PPT(new PPTFileExtractor()),
    XLS(new XLSFileExtractor());

    private final FileExtractor instance;

    FileExtractorInstances(FileExtractor instance) {
        this.instance = instance;
    }

    public static final TxtExtractor getTxtExtractorInstance() {
        return (TxtExtractor) TXT.instance;
    }

    public static final PDFFileExtractor getPDFExtractorInstance() {
        return (PDFFileExtractor) PDF.instance;
    }

    public static final DocFileExtractor getDocFileExtractorInstance() {
        return (DocFileExtractor) DOC.instance;
    }

    public static final PPTFileExtractor getPPTFileExtractorInstance() {
        return (PPTFileExtractor) PPT.instance;
    }

    public static final XLSFileExtractor getXLSFileExtractorInstance() {
        return (XLSFileExtractor) XLS.instance;
    }

}
