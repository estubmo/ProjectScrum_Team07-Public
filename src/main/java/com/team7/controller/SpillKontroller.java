/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

import com.team7.Domain.Oppgave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.team7.service.SpillBehandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("oppgavenr")
@RequestMapping(value = {"/main/spill", "/main"})
public class SpillKontroller {

   
    
    @Autowired
    private SpillBehandler spillBehandler;

    @RequestMapping(method = RequestMethod.GET)
    public String load(Model model, int oppgave, HttpSession session) {
        if (oppgave < 1 || oppgave > spillBehandler.finnMaxGodkjentSvar(SecurityContextHolder.getContext().getAuthentication().getName())) { //Oppgave som ikke finnes eller høyere oppgave enn maks godkjent
            int oppg=Integer.parseInt(session.getAttribute("oppgavenr").toString());
            model.addAttribute("svar", spillBehandler.hentSvar(oppg, SecurityContextHolder.getContext().getAuthentication().getName()).getSvarTekst());
            model.addAttribute("oppgavenr", oppg);
            model.addAttribute("oppgaveBeskrivelse", spillBehandler.hentOppgave(oppg).getOppgaveBeskrivelse());
            model.addAttribute("maxoppgave", spillBehandler.finnMaxGodkjentSvar(SecurityContextHolder.getContext().getAuthentication().getName()));
            model.addAttribute("godkjent", spillBehandler.sjekkOmSvarErGodkjent(SecurityContextHolder.getContext().getAuthentication().getName(), oppgave));
            model.addAttribute("score", spillBehandler.hentSvar(oppgave, SecurityContextHolder.getContext().getAuthentication().getName()).getScore());
            model.addAttribute("minpoeng", spillBehandler.finnFasit(oppgave).getMinpoeng());
        }else{
        model.addAttribute("svar", spillBehandler.hentSvar(oppgave, SecurityContextHolder.getContext().getAuthentication().getName()).getSvarTekst());
        model.addAttribute("oppgavenr", oppgave);
        model.addAttribute("oppgaveBeskrivelse", spillBehandler.hentOppgave(oppgave).getOppgaveBeskrivelse());
        model.addAttribute("maxoppgave", spillBehandler.finnMaxGodkjentSvar(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("godkjent", spillBehandler.sjekkOmSvarErGodkjent(SecurityContextHolder.getContext().getAuthentication().getName(), oppgave));
        model.addAttribute("score", spillBehandler.hentSvar(oppgave, SecurityContextHolder.getContext().getAuthentication().getName()).getScore());
        model.addAttribute("minpoeng", spillBehandler.finnFasit(oppgave).getMinpoeng());
        }
        return "spill";
    }

    

    @RequestMapping(params = "submit", method = RequestMethod.POST)
    public String submitFormen(@ModelAttribute("postSvar") String input, HttpSession session, Model model) {
        Oppgave o = spillBehandler.hentOppgave(Integer.parseInt(session.getAttribute("oppgavenr").toString()));
        spillBehandler.setSvar(Integer.parseInt(session.getAttribute("oppgavenr").toString()), input, SecurityContextHolder.getContext().getAuthentication().getName());
        if(spillBehandler.sjekkGodkjent(SecurityContextHolder.getContext().getAuthentication().getName())){
            return "ferdigspill";
        }
        
        model.addAttribute("svar", spillBehandler.hentSvar(Integer.parseInt(session.getAttribute("oppgavenr").toString()), SecurityContextHolder.getContext().getAuthentication().getName()).getSvarTekst());
        model.addAttribute("oppgavenr", o.getOppgaveid());
        model.addAttribute("oppgaveBeskrivelse", o.getOppgaveBeskrivelse());
        model.addAttribute("maxoppgave", spillBehandler.finnMaxGodkjentSvar(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("godkjent", spillBehandler.sjekkOmSvarErGodkjent(SecurityContextHolder.getContext().getAuthentication().getName(), Integer.parseInt(session.getAttribute("oppgavenr").toString())));
        model.addAttribute("score", spillBehandler.hentSvar(Integer.parseInt(session.getAttribute("oppgavenr").toString()), SecurityContextHolder.getContext().getAuthentication().getName()).getScore());
        model.addAttribute("minpoeng", spillBehandler.finnFasit(Integer.parseInt(session.getAttribute("oppgavenr").toString())).getMinpoeng());
        return "spill";
    }
    
    @RequestMapping(value={"/reset"}, method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("postSvar") String input, HttpSession session, Model model) {
        Oppgave o = spillBehandler.hentOppgave(Integer.parseInt(session.getAttribute("oppgavenr").toString()));
        spillBehandler.setSvar(Integer.parseInt(session.getAttribute("oppgavenr").toString()), input, SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("svar", spillBehandler.hentSvar(Integer.parseInt(session.getAttribute("oppgavenr").toString()), SecurityContextHolder.getContext().getAuthentication().getName()).getSvarTekst());
        model.addAttribute("oppgavenr", o.getOppgaveid());
        model.addAttribute("oppgaveBeskrivelse", o.getOppgaveBeskrivelse());
        model.addAttribute("maxoppgave", spillBehandler.finnMaxGodkjentSvar(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("godkjent", spillBehandler.sjekkOmSvarErGodkjent(SecurityContextHolder.getContext().getAuthentication().getName(), Integer.parseInt(session.getAttribute("oppgavenr").toString())));
        model.addAttribute("score", spillBehandler.hentSvar(Integer.parseInt(session.getAttribute("oppgavenr").toString()), SecurityContextHolder.getContext().getAuthentication().getName()).getScore());
        model.addAttribute("minpoeng", spillBehandler.finnFasit(Integer.parseInt(session.getAttribute("oppgavenr").toString())).getMinpoeng());
        return "spill";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String reset(Model model, HttpSession session) {
        int oppgave=Integer.parseInt(session.getAttribute("oppgavenr").toString());
        
        spillBehandler.resetSvar(oppgave, SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("svar", spillBehandler.hentSvar(oppgave, SecurityContextHolder.getContext().getAuthentication().getName()).getSvarTekst());
        model.addAttribute("oppgavenr", oppgave);
        model.addAttribute("oppgaveBeskrivelse", spillBehandler.hentOppgave(oppgave).getOppgaveBeskrivelse());
        model.addAttribute("maxoppgave", spillBehandler.finnMaxGodkjentSvar(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("godkjent", spillBehandler.sjekkOmSvarErGodkjent(SecurityContextHolder.getContext().getAuthentication().getName(), Integer.parseInt(session.getAttribute("oppgavenr").toString())));
        model.addAttribute("score", spillBehandler.hentSvar(Integer.parseInt(session.getAttribute("oppgavenr").toString()), SecurityContextHolder.getContext().getAuthentication().getName()).getScore());
        model.addAttribute("minpoeng", spillBehandler.finnFasit(Integer.parseInt(session.getAttribute("oppgavenr").toString())).getMinpoeng());
        return "spill";
    }
    
    @RequestMapping(value = "/startspill")
    public String welcome(Model model){
        return "startspill";
    }
    @RequestMapping(value = "/ferdigspill")
    public String ferdig(Model model){
        return "ferdigspill";
    }
}
