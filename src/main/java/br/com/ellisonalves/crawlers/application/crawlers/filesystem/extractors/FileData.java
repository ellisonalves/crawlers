package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;

import java.util.Date;

/**
 * Data extracted from files.
 *
 * @author ellison
 */
class FileData implements ExtractedData {

    private final String text;
    private final Date date;
    private final String author;
    private final String title;

    private FileData(String text, Date date, String author, String title) {
        this.text = text;
        this.date = date;
        this.author = author;
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        return text;
    }

    public static FileData create(String text, Date date, String author, String title) {
        return new FileData(
                text,
                date,
                author,
                title
        );
    }

}
