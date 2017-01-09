package br.com.ellisonalves.crawlers.application.crawlers.gmail;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ellison
 */
public class GmailCrawler implements Crawlable {

    @Override
    public void crawl(Map<String, Object> confidg) {

        try {
            Gmail service = GmailOAuth2Service.getGmailService();
            final String userId = "me";

            ListMessagesResponse response = service
                    .users()
                    .messages()
                    .list(userId)
                    .execute();

            List<Message> messages = new ArrayList<>();

            while (response.getMessages()!= null) {
                messages.addAll(response.getMessages());

                if (response.getNextPageToken() != null) {
                    String pageToken = response.getNextPageToken();
                    response = service
                            .users()
                            .messages()
                            .list(userId)
                            .setPageToken(pageToken)
                            .execute();
                } else {
                    break;
                }
            }

            for (Message message : messages) {

                try {
                    final Message msg = service
                            .users()
                            .messages()
                            .get(userId, message.getId())
                            .execute();
                    
                    System.out.println(msg.toPrettyString());

                } catch (GoogleJsonResponseException ex) {
                    System.out.println("Erro na thread id: " + message.getId());
                    System.out.println("Erro: " + ex.getMessage());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
