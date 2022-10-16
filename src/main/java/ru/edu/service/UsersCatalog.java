package ru.edu.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class UsersCatalog {

  private final Map<String, UserInfo> users = new HashMap<>();

  @PostConstruct
  public void init() {
    create(new UserInfo("1", "Test1"));
    create(new UserInfo("2", "Test2"));
  }

  public Collection<UserInfo> getAll() {
    return users.values();
  }

  public UserInfo create(UserInfo userInfo) {
    users.put(userInfo.getId(), userInfo);
    return userInfo;
  }

  public UserInfo update(UserInfo userInfo) {
    users.put(userInfo.getId(), userInfo);
    return userInfo;
  }

  public UserInfo deleteById(String id ) {
    return users.remove(id);
  }

  public UserInfo getById(String id ) {
    return users.get(id);
  }

}
