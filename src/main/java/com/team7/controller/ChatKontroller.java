package com.team7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Eirik
 */
@Controller
public class ChatKontroller {

    @RequestMapping("/main/chat")
    public String openChat(Model model) {
        return "chat";
    }
}
