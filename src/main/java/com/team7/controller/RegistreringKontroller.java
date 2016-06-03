/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

import com.team7.Domain.Bruker;
import com.team7.service.RegistrerBruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author HavardTollefsen
 */
@Controller
public class RegistreringKontroller {

    @Autowired
    private RegistrerBruker registrerService;

    @RequestMapping(value = "/registrering", method = RequestMethod.GET)
    public String regis(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            model.addAttribute("success", "hidden");
            model.addAttribute("error", "hidden");
            return "registrering";
        } else {
            return "redirect:/main/highscore";
        }

    }

    @RequestMapping(value = "/registrering", method = RequestMethod.POST)
    public String submitReg(@ModelAttribute("regBruker") Bruker nyBruker, Model model) {
        model.addAttribute("epost", nyBruker.getEpost());
        model.addAttribute("success", "hidden");
        model.addAttribute("error", "hidden");
        if (registrerService.regBruker(nyBruker)) {
            System.out.println("Bruker " + nyBruker.getEpost() + " registrert");
            model.addAttribute("success", "");
            return "registrering";
        } else {
            model.addAttribute("error", "");
            return "registrering";
        }
    }
}
