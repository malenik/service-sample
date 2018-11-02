package ch.efg.sample.api;

import java.util.List;
import java.util.Map;

/**
 * @author igor
 */
public interface IUserService<T, ID extends String> {

    List<T> findAll();

    /*

    Is it right to return a list of entitites by its identifier?
    If yes - which of them we should to return after 'delete'?

    List<T> findById(ID id);

    */

    T findById(ID id);

    <S extends T> List<S> saveAll(Iterable<S> var1);

    <S extends T> S save(S var1);

    T delete(ID var1);

    /**
     * @return {@code Map} of users by groupId
     */
    Map<String, List<T>> findAllGroupByGroupId();
}
