/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

/**
 *
 * @author Ivar
 */
import com.team7.service.GodkjenningBehandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/main/godkjenningsliste")
public class GodkjenningKontroller {

    @Autowired
    private GodkjenningBehandler godkjenningbehandler;

    @RequestMapping
    public String welcome(Model model) {
        model.addAttribute("brukere", godkjenningbehandler.hentAlleBrukere());
            return "godkjenningsliste";
    }

    @RequestMapping("/all")
    public String godkjenningsliste(Model model) {
            model.addAttribute("brukere", godkjenningbehandler.hentAlleBrukere());
            return "godkjenningsliste";
    }

    @RequestMapping("/bruker")
    public String getProductById(@RequestParam("id") String epost, Model model) {
            model.addAttribute("brukersvar", godkjenningbehandler.hentAlleSvarBruker(epost));
            return "brukersvar";
    }
    
    @RequestMapping("/brukerrett")
    public String getBruker(@RequestParam("id") String epost, @RequestParam("rett") String rettighet, Model model) {
        godkjenningbehandler.settRettighet(epost, rettighet);
        model.addAttribute("bruker", godkjenningbehandler.hentBruker(epost));
        return "brukerrett";
    }

}
