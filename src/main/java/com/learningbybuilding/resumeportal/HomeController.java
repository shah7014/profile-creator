package com.learningbybuilding.resumeportal;

import com.learningbybuilding.resumeportal.profile.Job;
import com.learningbybuilding.resumeportal.profile.UserProfile;
import com.learningbybuilding.resumeportal.profile.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {
    private final UserProfileRepository userProfileRepository;

    @GetMapping("/edit")
    public String edit(Principal principal, Model model) {
        String userName = principal.getName();
        UserProfile userProfile = userProfileRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User does not exist with user name:- " + userName));
        model.addAttribute("userProfile", userProfile);
        return "edit-profile";
    }

    @PostMapping("/edit")
    public String postEdit(@ModelAttribute("userProfile") UserProfile userProfile, Principal principal, Model model) {
        String userName = principal.getName();
        UserProfile savedUserProfile = userProfileRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User does not exist with user name:- " + userName));

        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(savedUserProfile.getUserName());

        userProfileRepository.save(userProfile);

        return "redirect:/view/" + principal.getName();
    }

    @GetMapping("/view/{userName}")
    public String view(@PathVariable String userName, Model model) {
        UserProfile userProfile = userProfileRepository.findByUserName(userName)
                        .orElseThrow(() -> new RuntimeException(userName + "not found"));
        model.addAttribute("userProfile", userProfile);
        return "resume-templates/" + userProfile.getTheme() +"/index";
    }
}
