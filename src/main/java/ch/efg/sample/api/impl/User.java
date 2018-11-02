package ch.efg.sample.api.impl;

import ch.efg.sample.api.IUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User implements IUser {
    private String id;
    private String name;
    private String groupId;
}
