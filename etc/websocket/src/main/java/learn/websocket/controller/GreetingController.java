package learn.websocket.controller;

import learn.websocket.model.GreetingModel;
import learn.websocket.model.NameModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public GreetingModel greeting(NameModel name) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return new GreetingModel("Hello, " + HtmlUtils.htmlEscape(name.getName()) + "!");
  }
}
