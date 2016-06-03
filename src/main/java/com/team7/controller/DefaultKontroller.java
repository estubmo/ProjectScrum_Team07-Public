/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Eirik
 */
@Controller
@RequestMapping("/")
public class DefaultKontroller {

    @RequestMapping
    public String defaultView() {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            return "redirect:/login";
        } else {
            return "redirect:/main/highscore";
        }

    }

}
