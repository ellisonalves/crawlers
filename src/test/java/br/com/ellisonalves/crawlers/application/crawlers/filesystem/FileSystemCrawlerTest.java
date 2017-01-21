package br.com.ellisonalves.crawlers.application.crawlers.filesystem;

import br.com.ellisonalves.crawlers.application.crawlers.ConfigParameterNotFoundException;
import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import br.com.ellisonalves.crawlers.domain.repository.DocumentRepository;
import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.HashMap;

import static org.mockito.Mockito.*;

public class FileSystemCrawlerTest {

    private FileSystemCrawler fileSystemCrawler;

    private DocumentRepository documentRepository;

    @Before
    public void setUp() throws Exception {
        documentRepository = mock(DocumentRepository.class);
        fileSystemCrawler = new FileSystemCrawler(documentRepository);
    }

    @Test(expected = ConfigParameterNotFoundException.class)
    public void shouldThrowAnExceptionWhenAllParametersAreMissing() {
        HashMap config = new HashMap();
        fileSystemCrawler.crawl(config);
    }

    @Test(expected = ConfigParameterNotFoundException.class)
    public void shouldThrowAnExceptionWhenInitialPathParameterIsMissing() throws Exception {
        HashMap<String, Object> config = new HashMap<>();
        config.put(Crawlable.SEARCH_DEPTH_PARAM, 2);
        fileSystemCrawler.crawl(config);
    }

    @Test(expected = ConfigParameterNotFoundException.class)
    public void shouldThrowAnExceptionWhenSearchDepthParameterIsMissing() throws Exception {
        HashMap<String, Object> config = new HashMap<>();
        config.put(Crawlable.INITIAL_PATH_PARAM, ".");
        fileSystemCrawler.crawl(config);
    }

    @Test
    public void shouldExploreTheFileSystem() throws Exception {
        HashMap<String, Object> config = new HashMap<>();

        config.put(Crawlable.SEARCH_DEPTH_PARAM, 2);
        config.put(Crawlable.INITIAL_PATH_PARAM, SystemUtils.USER_HOME + SystemUtils.FILE_SEPARATOR + "Documentos");

        fileSystemCrawler.crawl(config);

        verify(documentRepository, atLeast(1)).insert(Matchers.anyObject());
    }

}
