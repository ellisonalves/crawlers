package br.com.ellisonalves.crawlers.domain.repository;

/**
 *
 * @author ellison
 */
public class RepositoryFactory {
    
    private static DocumentRepository documentRepository;

    public static final DocumentRepository createDocumentRepository() {
        if (documentRepository == null) {
            documentRepository = new DocumentRepositoryImpl();
        }
        return documentRepository;
    }

}
