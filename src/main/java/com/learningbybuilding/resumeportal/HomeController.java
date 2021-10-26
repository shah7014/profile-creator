package com.learningbybuilding.resumeportal;

import com.learningbybuilding.resumeportal.profile.UserProfile;
import com.learningbybuilding.resumeportal.profile.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class HomeController {
    private final UserProfileRepository userProfileRepository;

    @GetMapping("/view/{userName}")
    public String view(@PathVariable String userName, Model model) {
        UserProfile userProfile = userProfileRepository.findByUserName(userName)
                        .orElseThrow(() -> new RuntimeException(userName + "not found"));
        model.addAttribute("userProfile", userProfile);
        return "resume-templates/" + userProfile.getTheme() +"/index";
    }
}
