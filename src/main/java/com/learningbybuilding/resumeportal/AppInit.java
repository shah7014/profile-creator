package com.learningbybuilding.resumeportal;

import com.learningbybuilding.resumeportal.security.models.User;
import com.learningbybuilding.resumeportal.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        User user1 = new User("foo", "foo");
        User user2 = new User("bar", "bar");
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
