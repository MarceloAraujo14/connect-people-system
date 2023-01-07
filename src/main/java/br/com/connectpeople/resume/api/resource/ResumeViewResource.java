package br.com.connectpeople.resume.api.resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ResumeViewResource {

    @GetMapping
    public String home(Authentication authentication, Model model){
        model.addAttribute("username", getUsername(authentication));
        return "resume-form";
    }

    @GetMapping("/resume")
    public String resume(Authentication authentication, Model model){
        model.addAttribute("username", getUsername(authentication));
        return "resume-form";
    }

    private String getUsername(Authentication authentication){
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        if(user.getAttributes().containsKey("localizedFirstName")){
            return user.getAttribute("localizedFirstName") + " " + user.getAttribute("localizedLastName");
        }
        if(user.getAttributes().containsKey("given_name")){
            return user.getAttribute("given_name");
        }
        return user.getName();
    }

}
