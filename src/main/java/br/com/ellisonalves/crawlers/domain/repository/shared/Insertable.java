package br.com.ellisonalves.crawlers.domain.repository.shared;

import java.io.Serializable;
import java.util.List;

/**
 * A contract that explicits a way insert objects on repository
 *
 * @param <T> type of the object to be inserted
 * @author ellison
 */
public interface Insertable<T extends Serializable> extends Repository {

    void insert(T object);

    void insert(List<T> list);
}
