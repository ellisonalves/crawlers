package br.com.ellisonalves.crawlers.domain.valueobject;

import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;

import java.util.Date;

/**
 * Created by ellison on 05/05/17.
 */
public class RssData extends ExtractedData {

    private final String link;
    private final Date updatedDate;

    public RssData(String text, Date date, String author, String title,
                   String link, Date updatedDate) {
        super(text, date, author, title);
        this.link = link;
        this.updatedDate = updatedDate;
    }

}
