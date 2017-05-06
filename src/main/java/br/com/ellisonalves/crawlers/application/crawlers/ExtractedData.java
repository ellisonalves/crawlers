package br.com.ellisonalves.crawlers.application.crawlers;

import java.io.Serializable;
import java.util.Date;

/**
 * Contract of the files that had its data extracted.
 *
 * @author ellison
 */
public abstract class ExtractedData implements Serializable {

    private final String text;
    private final Date date;
    private final String author;
    private final String title;

    protected ExtractedData(String text, Date date, String author, String title) {
        this.text = text;
        this.date = date;
        this.author = author;
        this.title = title;
    }

    protected String getText() {
        return text;
    }

    protected Date getDate() {
        return date;
    }

    protected String getAuthor() {
        return author;
    }

    protected String getTitle() {
        return title;
    }

}
