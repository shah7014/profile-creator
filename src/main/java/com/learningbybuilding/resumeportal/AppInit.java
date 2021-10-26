package com.learningbybuilding.resumeportal;

import com.learningbybuilding.resumeportal.profile.Job;
import com.learningbybuilding.resumeportal.profile.UserProfile;
import com.learningbybuilding.resumeportal.profile.UserProfileRepository;
import com.learningbybuilding.resumeportal.security.models.User;
import com.learningbybuilding.resumeportal.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initLoginUser();
        initUserProfile();
    }

    private void initLoginUser() {
        User user1 = new User("alb", "alb");
        User user2 = new User("new", "new");
        userRepository.save(user1);
        userRepository.save(user2);
    }

    private void initUserProfile() {
        UserProfile userProfileEinstein = new UserProfile();
        userProfileEinstein.setUserName("alb");
        userProfileEinstein.setFirstName("Albert");
        userProfileEinstein.setLastName("Einstein");
        userProfileEinstein.setPhone("111-222");
        userProfileEinstein.setEmail("einstein@gmail.com");
        userProfileEinstein.setSummary("Einstein is best known for developing the theory of relativity, but he also made important contributions to the development of the theory of quantum mechanics.");
        userProfileEinstein.setDesignation("Theoretical physicist");
        userProfileEinstein.setJobs(createJobs(3));
        userProfileEinstein.setTheme(1);
        userProfileRepository.save(userProfileEinstein);

        UserProfile userProfileNewton = new UserProfile();
        userProfileNewton.setUserName("new");
        userProfileNewton.setFirstName("Isaac");
        userProfileNewton.setLastName("Newton");
        userProfileNewton.setPhone("000-111");
        userProfileNewton.setEmail("newton@gmail.com");
        userProfileNewton.setSummary("is widely recognised as one of the greatest mathematicians, physicists, and most influential scientists of all time. He was a key figure in the philosophical revolution known as the Enlightenment.");
        userProfileNewton.setDesignation("Mathematician physicist");
        userProfileNewton.setJobs(createJobs(2));
        userProfileNewton.setTheme(3);
        userProfileRepository.save(userProfileNewton);
    }

    List<Job> createJobs(int numberOfJobs) {
        List<Job> jobs = new ArrayList<>();
        IntStream.rangeClosed(1, numberOfJobs).forEach(id -> {
            Job job = new Job();
            job.setCompany("company-" + id);
            job.setDesignation("designation-" + id);
            job.setStartDate(LocalDate.of(2015 + id, 1, 1));
            if (id == numberOfJobs) {
                job.setPresent(true);
            } else {
                job.setPresent(false);
                job.setEndDate(LocalDate.of(2015 + id, 8, 1));
            }
            jobs.add(job);
        });
        return jobs;
    }
}
