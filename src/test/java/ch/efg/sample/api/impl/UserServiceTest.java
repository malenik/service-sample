package ch.efg.sample.api.impl;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void testFindAllIsEmpty() {
        UserService underTest = new UserService();
        List<User> users = underTest.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    public void testFindAll() {
        UserService underTest = new UserService();
        User testUser1 = new User("1", "name1", "1");
        User testUser2 = new User("2", "name2", "1");

        List<User> emptyUsers = underTest.findAll();
        underTest.save(testUser1);
        underTest.save(testUser2);
        List<User> actualUsers = underTest.findAll();

        assertTrue(emptyUsers.isEmpty());
        assertEquals(2, actualUsers.size());
    }

    @Test
    public void testFindByIdNone() {
        UserService underTest = new UserService();
        User user = underTest.findById("1");
        assertNull(user);
    }

    @Test
    public void testFindById() {
        UserService underTest = new UserService();
        User testUser = new User("1", "name1", "1");

        User noneUsers = underTest.findById(testUser.getId());
        underTest.save(testUser);
        User actualUser = underTest.findById(testUser.getId());

        assertNull(noneUsers);
        assertEquals(testUser, actualUser);
    }

    @Test
    public void testSave() {
        UserService underTest = new UserService();
        User testUser = new User("1", "name1", "1");

        User persistedUser = underTest.save(testUser);
        User actualUser = underTest.findById(testUser.getId());

        assertEquals(testUser, persistedUser);
        assertEquals(testUser, actualUser);
    }

    @Test
    public void testSaveAllEmpty() {
        UserService underTest = new UserService();

        List<User> persistedUsers = underTest.saveAll(Collections.emptyList());
        List<User> actualUsers = underTest.findAll();

        assertTrue(persistedUsers.isEmpty());
        assertTrue(actualUsers.isEmpty());
    }


    @Test
    public void testSaveAll() {
        UserService underTest = new UserService();
        User testUser1 = new User("1", "name1", "1");
        User testUser2 = new User("2", "name2", "2");

        List<User> testUsers = Arrays.asList(testUser1, testUser2);
        List<User> persistedUsers = underTest.saveAll(testUsers);
        List<User> actualUsers = underTest.findAll();

        assertEquals(actualUsers.size(), 2);
        assertEquals(persistedUsers, testUsers);
        assertEquals(actualUsers, testUsers);
    }

    @Test
    public void testDeleteEmpty() {
        UserService underTest = new UserService();
        User actualUser = underTest.delete("1");
        assertNull(actualUser);
    }


    @Test
    public void testDelete() {
        UserService underTest = new UserService();
        User testUser = new User("1", "name1", "1");
        User persistedUser = underTest.save(testUser);
        User deletedUser = underTest.delete(testUser.getId());
        User actualUser = underTest.findById(testUser.getId());

        assertEquals(deletedUser, testUser);
        assertNull(actualUser);
    }

    @Test
    public void testFindAllGroupByGroupIdEmpty() {
        UserService underTest = new UserService();
        Map<String, List<User>> actualUsers = underTest.findAllGroupByGroupId();
        assertTrue(actualUsers.isEmpty());
    }

    @Test
    public void findAllGroupByGroupId() {
        UserService underTest = new UserService();
        String groupId1 = "1";
        String groupId2 = "2";

        User testUser1 = new User("1", "name1", groupId1);
        User testUser2 = new User("2", "name2", groupId1);
        User testUser3 = new User("3", "name2", groupId2);

        underTest.save(testUser1);
        underTest.save(testUser2);
        underTest.save(testUser3);

        Map<String, List<User>> actualUsers = underTest.findAllGroupByGroupId();
        assertEquals(actualUsers.get(groupId1).size(), 2);
        assertEquals(actualUsers.get(groupId2).size(), 1);
    }
}