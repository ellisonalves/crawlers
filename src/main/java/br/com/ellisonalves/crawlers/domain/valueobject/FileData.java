package br.com.ellisonalves.crawlers.domain.valueobject;

import br.com.ellisonalves.crawlers.application.crawlers.ExtractedData;

import java.util.Date;

/**
 * Data extracted from files.
 *
 * @author ellison
 */
public class FileData extends ExtractedData {

    protected FileData(String text, Date date, String author, String title) {
        super(text, date, author, title);
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
