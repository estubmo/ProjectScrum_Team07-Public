/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

/**
 *
 * @author SimenLaptop
 */
import com.team7.service.HighscoreBehandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/highscore")
public class HighscoreKontroller {

    @Autowired
    private HighscoreBehandler highscorebehandler;

    @RequestMapping
    public String highscore(Model model) {
        model.addAttribute("brukere", highscorebehandler.hentHighscore());
        return "highscore";
    }
}
