/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

import com.team7.Domain.Oppgave;
import com.team7.service.SpillBehandler;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Steffen
 */
@Controller
@SessionAttributes("email")
public class InteraktivtSpillKontroller {

    @Autowired
    private SpillBehandler spillbehandler;

    @RequestMapping(value = "main/spill/interaktiv", method = RequestMethod.GET)
    public String Visindex(Model model, HttpSession session, int oppgave) {
        List<Oppgave> oppgavene = SpillBehandler.hentAlleInteraktiveOppgaver();
        String brukerEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Boolean> godkjentliste = new ArrayList();

        for (int i = 1; i <= oppgavene.size(); i++) {
            godkjentliste.add(spillbehandler.sjekkOmInteraktivtSvarErGodkjent(brukerEmail, i));
        }

        model.addAttribute("godkjentliste", godkjentliste);

        if (oppgave > oppgavene.size() || oppgave < 1) {

            model.addAttribute("oppgavene", oppgavene);
            String[] splittetOppgaveTekst = spillbehandler.delInteraktivTekst(oppgavene.get(0).getOppgaveTekst());
            model.addAttribute("oppgavetekst", splittetOppgaveTekst[0]);
            model.addAttribute("standardoppgavetekst", splittetOppgaveTekst[1]);
            model.addAttribute("oppgavebeskrivelse", oppgavene.get(0).getOppgaveBeskrivelse());
            model.addAttribute("oppgavenr", oppgavene.get(0).getOppgaveid());
        } else {

            model.addAttribute("oppgavene", oppgavene);
            String[] splittetOppgaveTekst = spillbehandler.delInteraktivTekst(oppgavene.get(oppgave - 1).getOppgaveTekst());
            model.addAttribute("oppgavetekst", splittetOppgaveTekst[0]);
            model.addAttribute("standardoppgavetekst", splittetOppgaveTekst[1]);
            model.addAttribute("oppgavebeskrivelse", oppgavene.get(oppgave - 1).getOppgaveBeskrivelse());
            model.addAttribute("oppgavenr", oppgavene.get(oppgave - 1).getOppgaveid());
        }
        return "spill2";

    }

    @RequestMapping(value = "/main/spill/interaktiv", method = RequestMethod.POST)
    public String submitForm(Model model, HttpSession session, String tid, int oppgave) {
        // Logget inn

        String brukerEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        spillbehandler.registrerInteraktivScore(brukerEmail, Integer.parseInt(tid), oppgave);
        List<Oppgave> oppgavene = SpillBehandler.hentAlleInteraktiveOppgaver();
        List<Boolean> godkjentliste = new ArrayList();

        for (int i = 1; i <= oppgavene.size(); i++) {
            godkjentliste.add(spillbehandler.sjekkOmInteraktivtSvarErGodkjent(brukerEmail, i));
        }

        model.addAttribute("godkjentliste", godkjentliste);

        //model.addAttribute("oppgave", oppgave + 1);
        if (oppgave > oppgavene.size() || oppgave < 1) {

            model.addAttribute("oppgavene", oppgavene);
            String[] splittetOppgaveTekst = spillbehandler.delInteraktivTekst(oppgavene.get(0).getOppgaveTekst());
            model.addAttribute("oppgavetekst", splittetOppgaveTekst[0]);
            model.addAttribute("standardoppgavetekst", splittetOppgaveTekst[1]);
            model.addAttribute("oppgavebeskrivelse", oppgavene.get(0).getOppgaveBeskrivelse());
            model.addAttribute("oppgavenr", oppgavene.get(0).getOppgaveid());
        } else {

            model.addAttribute("oppgavene", oppgavene);
            String[] splittetOppgaveTekst = spillbehandler.delInteraktivTekst(oppgavene.get(oppgave - 1).getOppgaveTekst());
            model.addAttribute("oppgavetekst", splittetOppgaveTekst[0]);
            model.addAttribute("standardoppgavetekst", splittetOppgaveTekst[1]);
            model.addAttribute("oppgavebeskrivelse", oppgavene.get(oppgave - 1).getOppgaveBeskrivelse());
            model.addAttribute("oppgavenr", oppgavene.get(oppgave - 1).getOppgaveid());
        }
        return "spill2";
    }

}
