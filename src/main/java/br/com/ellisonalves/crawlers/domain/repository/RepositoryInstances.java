package br.com.ellisonalves.crawlers.domain.repository;

import br.com.ellisonalves.crawlers.domain.repository.shared.Repository;

/**
 * Place where it is possible to put repository instances.
 *
 * @author ellison
 */
public enum RepositoryInstances {

    DOCUMENT_REPOSITORY(new DocumentRepositoryImpl());

    private final Repository repository;

    RepositoryInstances(Repository repository) {
        this.repository = repository;
    }

    public static final DocumentRepository getDocumentRepositoryInstance() {
        return (DocumentRepository) DOCUMENT_REPOSITORY.repository;
    }
}
