/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team7.controller;

import com.team7.service.BrukerBehandler;
import com.team7.service.SpillBehandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Eirik
 */
@Controller
@RequestMapping(value="/main/admin")
public class AdminKontroller {
    

    @Autowired
    SpillBehandler spillBehandler;
    
    @Autowired
    BrukerBehandler brukerBehandler;
    
    @RequestMapping(method = RequestMethod.GET)

    public String view(Model model){
        return "admin";
    }
    

    @RequestMapping(value = "/main/admin", method = RequestMethod.POST)
    public String slettSvar(@ModelAttribute("passord") String passord, Model model) {
        if (brukerBehandler.sjekkPassord(SecurityContextHolder.getContext().getAuthentication().getName(), passord)) {
            spillBehandler.slettAlleSvar();
        }
        
        return "admin";
    }

    
}
