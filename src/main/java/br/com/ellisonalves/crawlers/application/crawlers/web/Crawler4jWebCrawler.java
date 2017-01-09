package br.com.ellisonalves.crawlers.application.crawlers.web;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author ellison
 */
public class Crawler4jWebCrawler extends WebCrawler {

    private static final Logger LOGGER = Logger.getLogger(Crawler4jWebCrawler.class.getName());

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    private static final Set<String> URLS_VISITADAS = new HashSet<>();

    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean urlValida = !FILTERS.matcher(href).matches();
        boolean deveVisitar = urlValida && !URLS_VISITADAS.contains(href);
        if (urlValida) {
            URLS_VISITADAS.add(href);
        }
        LOGGER.log(Level.INFO, "shouldVisit {0} ? " + deveVisitar, href);
        return deveVisitar;
    }

    @Override
    public void visit(Page page) {
        LOGGER.log(Level.INFO, "Visiting: {0}", page.getWebURL().getURL());

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
        }

    }

}
