    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

import com.team7.Domain.Passord;
import com.team7.service.BrukerBehandler;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Eirik
 */
@ControllerAdvice
@Controller
public class InnstillingerKontroller {

    @Autowired
    BrukerBehandler brukerbehandler;

    @ModelAttribute
    public void passwordChange(Model model, Principal principal) {
        Passord passwordChange = new Passord();
        System.out.println("***** Currently authenticated as: " + SecurityContextHolder.getContext().getAuthentication().getName() + " Access: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString() + " *****");
        model.addAttribute("passwordChange", passwordChange);
    }

    @RequestMapping(value = "/main/endrepassord", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute(value = "passwordChange") Passord passord, HttpServletRequest request, Model model) {
        model.addAttribute("endret", "hidden");
        model.addAttribute("feil", "hidden");
        if (brukerbehandler.endrePassord(SecurityContextHolder.getContext().getAuthentication().getName(), passord)) {
            System.out.println("Bruker: " + SecurityContextHolder.getContext().getAuthentication().getName() + "\n Passord: '" + passord.getGammeltPassord() + "' endret til '" + passord.getNyttPassord() + "'");
            model.addAttribute("endret", "");
            return "passordendret";
        } else {
            System.out.println("Gammelt passord er feil");
            model.addAttribute("feil", "");
            return "passordendret";
        }

    }

    @RequestMapping(value = "/main/passordendret", method = RequestMethod.GET)
    public String passordEndretView(Model model) {
        model.addAttribute("endret", "hidden");
        model.addAttribute("feil", "hidden");
        return "passordendret";
    }
}

//    @Controller
//    public class MyController {
//
//        @RequestMapping(method = RequestMethod.POST)
//        public String processSubmit(@ModelAttribute("product") Product myProduct, BindingResult result, SessionStatus status) {
//
//            new ProductValidator().validate(myProduct, result);
//            if (result.hasErrors()) {
//                return "productForm";
//            } else {
//                this.productsService.saveProduct(myProduct);
//                status.setComplete();
//                return "productSaved";
//            }
//        }
//    }
