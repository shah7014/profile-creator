package com.learningbybuilding.resumeportal;

import com.learningbybuilding.resumeportal.profile.Education;
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
        List<String> skills = new ArrayList<>();
        skills.add("Quantum mechanics");
        skills.add("Violin");
        skills.add("Astronomy");
        skills.add("Mathematics");

        List<Education> edus = new ArrayList<>();
        Education e1 = new Education();
        e1.setCollege("Awesome College");
        e1.setQualification("Good Degree");
        Education e2 = new Education();
        e2.setCollege("Awesome College");
        e2.setQualification("Good Degree");
        edus.add(e1);
        edus.add(e2);

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
        userProfileEinstein.setSkills(skills);
        userProfileEinstein.setEducations(edus);
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
        userProfileNewton.setSkills(skills);
        userProfileNewton.setEducations(edus);
        userProfileRepository.save(userProfileNewton);
    }

    List<Job> createJobs(int numberOfJobs) {
        List<String> responsibilities = new ArrayList<>();
        responsibilities.add("Work on theory of relativity");
        responsibilities.add("Do some research on atoms");
        responsibilities.add("Blow people's minds");
        List<Job> jobs = new ArrayList<>();
        IntStream.rangeClosed(1, numberOfJobs).forEach(id -> {
            Job job = new Job();
            job.setCompany("company-" + id);
            job.setDesignation("designation-" + id);
            job.setStartDate(LocalDate.of(2015 + id, 1, 1));
            job.setResponsibilities(responsibilities);
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
