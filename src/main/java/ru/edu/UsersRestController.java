package ru.edu;

import java.util.Collection;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.service.UserInfo;
import ru.edu.service.UsersCatalog;

@RestController
@RequestMapping(value = "/api/users", consumes = MediaType.ALL_VALUE)
public class UsersRestController {

  private final UsersCatalog usersCatalog;

  @Autowired // DI через конструктор
  public UsersRestController(UsersCatalog usersCatalog) {
    this.usersCatalog = usersCatalog;
  }

  // возвращаем данные в формате json
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
  public Collection<UserInfo> getAll() {
    return usersCatalog.getAll();
  }

  @GetMapping("/find")
  public UserInfo find(@RequestParam(value = "id") String id) {
    UserInfo userInfo = usersCatalog.getById(id);
    if (Objects.isNull(userInfo)) {
      throw new IllegalStateException("id " + id + " not found");
    }

    return userInfo;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  // RequestBody - десериализует тело json запроса в UserInfo
  public UserInfo get(@RequestBody UserInfo userInfo) {
    return usersCatalog.create(userInfo);
  }

  @DeleteMapping("/{id}")
  public UserInfo delete(@PathVariable String id) {
    return usersCatalog.deleteById(id);
  }

}
