package br.com.ellisonalves.crawlers.application.crawlers.filesystem;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;
import br.com.ellisonalves.crawlers.domain.repository.DocumentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

public class FileSystemCrawlerTest {

    private FileSystemCrawler fileSystemCrawler;

    private DocumentRepository documentRepository;

    @Before
    public void setUp() throws Exception {
        documentRepository = mock(DocumentRepository.class);
        fileSystemCrawler = new FileSystemCrawler(documentRepository);
    }

    @After
    public void tearDown() throws Exception {
        reset(documentRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenAllParametersAreMissing() {
        HashMap config = new HashMap();
        fileSystemCrawler.crawl(config);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInitialPathParameterIsMissing() {
        HashMap<String, Object> config = new HashMap<>();
        config.put(Crawlable.SEARCH_DEPTH_PARAM, 2);
        fileSystemCrawler.crawl(config);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenSearchDepthParameterIsMissing() {
        HashMap<String, Object> config = new HashMap<>();
        config.put(Crawlable.INITIAL_PATH_PARAM, ".");
        fileSystemCrawler.crawl(config);
    }

    @Test
    public void shouldExploreTheFileSystem() {
        HashMap<String, Object> config = new HashMap<>();
        config.put(Crawlable.SEARCH_DEPTH_PARAM, 1);
        config.put(Crawlable.INITIAL_PATH_PARAM, FileSystemCrawlerTest.class.getResource("/file_system_crawler_test_files").getFile());

        fileSystemCrawler.crawl(config);

        doNothing().when(documentRepository).insert(any(ExtractedData.class));

        verify(documentRepository, times(5)).insert(anyListOf(ExtractedData.class));
    }

}