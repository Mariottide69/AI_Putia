
package it.arces.ai_putia.event;

import org.springframework.context.ApplicationEvent;

import it.arces.ai_putia.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
   private User user;
   private String applicationUrl;

   public RegistrationCompleteEvent(User user, String applicationUrl) {
      super(user);
      this.user = user;
      this.applicationUrl = applicationUrl;
   }

}
