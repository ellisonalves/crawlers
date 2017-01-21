package br.com.ellisonalves.crawlers.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Contract of the files that were its data extracted.
 *
 * @author ellison
 */
public interface ExtractedData extends Serializable {

    Date getDate();

    String getAuthor();

    String getTitle();

    String getText();

}
