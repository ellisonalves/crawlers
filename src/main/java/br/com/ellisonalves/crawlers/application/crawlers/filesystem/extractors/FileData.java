package br.com.ellisonalves.crawlers.application.crawlers.filesystem.extractors;

import br.com.ellisonalves.crawlers.domain.model.ExtractedData;

/**
 *
 * @author ellison
 */
class FileData implements ExtractedData {

    private final String data;

    private FileData(String data) {
        this.data = data;
    }

    @Override
    public String asString() {
        return data;
    }

    public static FileData create(String data) {
        return new FileData(data);
    }

}
