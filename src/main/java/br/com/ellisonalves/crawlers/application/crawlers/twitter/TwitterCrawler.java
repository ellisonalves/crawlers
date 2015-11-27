package br.com.ellisonalves.crawlers.application.crawlers.twitter;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author ellison
 */
public class TwitterCrawler implements Crawlable {

    private static final Logger LOGGER = Logger.getLogger(TwitterCrawler.class.getName());

    @Override
    public void crawl(Map<String, Object> config) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("IfF64p0LWqCJB0WC8uAn9I8Jd")
                .setOAuthConsumerSecret("eBGuXrEGcGxVlFre99esfSqJdnpaXx3GlyxYgdDcsjnUre4E7x")
                .setOAuthAccessToken("113908422-GTA47myXBThxI0hjuND23BVxA9gh2iqYqRSJWsoT")
                .setOAuthAccessTokenSecret("kys5Fn8f8f5uQcLq553bssRDqtRzMJdsrOHK6lJVHL8UY");

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        Query query = new Query((String) config.get(Crawlable.TEXTO_BUSCA_PARAM));
        query.setLang("pt");
        query.setResultType(Query.ResultType.mixed);
        query.setCount(100);

        try {
            QueryResult result;
            StringBuilder sb = new StringBuilder();
            do {
                result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    sb.append("@");
                    sb.append(status.getUser().getScreenName());
                    sb.append(":");
                    sb.append(status.getText());
                    sb.append("\n");
                }
                LOGGER.info(sb.toString());
            } while (null != (query = result.nextQuery()));

        } catch (TwitterException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }

    }

}
