package ru.edu;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.service.UserInfo;
import ru.edu.service.UsersCatalog;

@Controller
@RequestMapping(value = "users", consumes = MediaType.ALL_VALUE )
public class UserViewController {

  private UsersCatalog usersCatalog;
  private String author;

  @Value("${program.author.name}") // DI через сеттер из application.properties
  public void setAuthor(String author) {
    this.author = author;
  }

  @Autowired // DI через сеттер
  public void setUsersCatalog(UsersCatalog usersCatalog) {
    this.usersCatalog = usersCatalog;
  }

  @GetMapping
  public ModelAndView info() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/hello.jsp");
    modelAndView.addObject("author", author);
    return modelAndView;
  }

  @GetMapping("all")
  public ModelAndView allUsers() {
    Collection<UserInfo> users = usersCatalog.getAll();

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/users.jsp");
    modelAndView.addObject("userList", users);
    return modelAndView;
  }

}
