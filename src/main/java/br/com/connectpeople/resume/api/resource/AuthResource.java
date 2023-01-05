package br.com.connectpeople.resume.api.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthResource {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


}
