package br.com.ellisonalves.crawlers.application.crawlers;

import java.util.Map;

/**
 *
 * @author ellison
 */
public interface Crawlable {

    // Parâmetros utilizados pelos crawlers
    final static String URL_PARAM = "urlParam";

    final static String REGEX_LINKS_VALIDOS_PARAM = "regexLinksValidosParam";

    final static String PROFUNDIDADE_EXPLORACAO_PARAM = "profundidadeParam";

    final static String PATH_INICIAL_PARAM = "pathInicialParam";

    static String TEXTO_BUSCA_PARAM = "textoBuscaParam";

    void crawl(Map<String, Object> config);

}
