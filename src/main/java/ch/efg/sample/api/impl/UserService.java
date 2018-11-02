package ch.efg.sample.api.impl;

import ch.efg.sample.api.IUserService;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class UserService implements IUserService<User, String> {

    private Map<String, User> storage = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    public User findById(String s) {
        return storage.get(s);
    }

    public <S extends User> List<S> saveAll(Iterable<S> var1) {
        List<S> list = StreamSupport.stream(var1.spliterator(), false).collect(toList());
        Map<String, User> entries = list.stream().collect(toMap(User::getId, identity()));
        storage.putAll(entries);
        return list;
    }

    public <S extends User> S save(S var1) {
        storage.put(var1.getId(), var1);
        return var1;
    }

    public User delete(String var1) {
        return storage.remove(var1);
    }

    public Map<String, List<User>> findAllGroupByGroupId() {
        return storage.values().stream().collect(toMap(
                User::getGroupId,
                Arrays::asList,
                (current, next) -> Stream.concat(current.stream(), next.stream()).collect(toList())
        ));
    }
}
