/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.service;

import com.team7.Domain.Bruker;
import com.team7.Domain.Passord;
import com.team7.Domain.Svar;
import com.team7.Validator.PasswordValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimenLaptop
 */
@Service
public class BrukerBehandler {
    
    @Autowired
    PasswordValidator passwordValidator;

    public boolean sjekkPassord(String epost, String passord) {
        if (epost != null && passord != null) {
            Bruker bruker = DatabaseCom.hentBruker(epost);
            if (passwordValidator.matches(passord.subSequence(0, passord.length()), bruker.getPassord())){
                return true;
            }
        }
        return false;

    }

    public boolean endrePassord(String epost, Passord passord) {
        Bruker bruker = DatabaseCom.hentBruker(epost);
        if (bruker != null) {
            if (passord.getNyttPassord().equals(passord.getNyttPassordGjenta())) {
                System.out.println("epost: " + epost + " bruker: " + bruker.getEpost() + "\ngammeltpw:" + passord.getGammeltPassord() + " nytt pw: " + passord.getNyttPassord());
                if (sjekkPassord(epost, passord.getGammeltPassord())) {
                    DatabaseCom.endrePassord(epost, PasswordValidator.hashPassord(passord.getNyttPassord()));
                    return true;
                }
            }
        }
        return false;
    }
    
    public Bruker hentBruker(String epost){
        return DatabaseCom.hentBruker(epost);
    }
}
