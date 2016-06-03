/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

import com.team7.Domain.Bruker;
import com.team7.service.BrukerBehandler;
import com.team7.service.RegistrerBruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginKontroller {

    @Autowired
    private RegistrerBruker registrerService;
    
    @Autowired
    private BrukerBehandler brukerRepository;

    @RequestMapping(value = "/main/", method = RequestMethod.GET)
    public String defaultPage() {
        return "redirect:/main/highscore";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model) {
        model.addAttribute("errorpw", "hidden");
        model.addAttribute("successNewPw", "hidden");
        model.addAttribute("errorNewPw", "hidden");
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            if (error != null) {
                model.addAttribute("errorpw", "");
                System.out.println("Wrong password");
            }
            return "login";
        } else {
            if (logout != null) {
                SecurityContextHolder.clearContext();
                System.out.println("Logged out");
                return "login";
            }
            return "redirect:/main/highscore";
        }
    }

    @RequestMapping(value = "/glemtpassord", method = RequestMethod.POST)
    public String glemtPassord(@ModelAttribute("bruker") Bruker b, Model model) {
        model.addAttribute("errorpw", "hidden");
        model.addAttribute("successNewPw", "hidden");
        model.addAttribute("errorNewPw", "hidden");
        if (registrerService.sjekkEpost(b.getEpost())) {
            model.addAttribute("errorNewPw", "");
            model.addAttribute("epost", b.getEpost());
        } else {
            Bruker bruker = brukerRepository.hentBruker(b.getEpost());
            RegistrerBruker.nyttPassord(bruker);
            model.addAttribute("successNewPw", "");
            model.addAttribute("epost", b.getEpost());
        }
        return "login";
    }
}
