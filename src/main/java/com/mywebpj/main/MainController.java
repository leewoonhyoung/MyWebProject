package com.mywebpj.main;

import com.mywebpj.account.CurrentUser;
import com.mywebpj.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentUser Account account, Model model){
        if (account != null ){
            model.addAttribute(account);
        }

        return "index";
    }

 //TEst code
    @GetMapping("/login")
    public String login(@CurrentUser Account account , Model model){
        model.addAttribute("submitHelp",);
        model.addAttribute("username", account.getNickname());
        model.addAttribute("emailHelp", );
        return "login";

    }
}


