/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.service;

import com.team7.Domain.Bruker;
import com.team7.Mail.SendMail;
import com.team7.Validator.PasswordValidator;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ivar
 */
@Service
public class RegistrerBruker {

    private final String STUDENT = "student";
    private final String ADMIN = "admin";
    private final String LÆRER = "lærer";
    private final String STUDASS = "studass";

    public boolean regBruker(Bruker bruker) {
        if (sjekkEpost(bruker.getEpost())) {
            //Her kommer et valideringsfilter
            String passord = genPassord();
            bruker.setPassord(PasswordValidator.hashPassord(passord));
            bruker.setRettighet(STUDENT);
            DatabaseCom.nyBruker(bruker);
            sendPassord(passord, bruker);
            return true;
        }

        return false;
    }
    /*
     Sjekker om epostadressen eksisterer fra før
     */

    public boolean sjekkEpost(String epost) {
        List<Bruker> liste = DatabaseCom.hentAlleBrukere();
        for (Bruker b : liste) {
            if (b.getEpost().equalsIgnoreCase(epost)) {
                return false;
            }

        }
        return true;
    }

    public static void nyttPassord(Bruker bruker) {
        String passord = genPassord();
        DatabaseCom.endrePassord(bruker.getEpost(), PasswordValidator.hashPassord(passord));
        sendPassord(passord, bruker);

    }

    public static String genPassord() {
        //alphabet//      
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int i = 0;
        int Alphalaenge = alphabet.length();
        String pw = "";

        while (i < 15) {
            int rand = (int) (Math.random() * Alphalaenge);
            pw += alphabet.charAt(rand);
            ++i;
        }
        return pw;
    }

    public static void sendPassord(String passord, Bruker bruker) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        SendMail mm = (SendMail) context.getBean("mailMail");
        mm.sendEpost(passord, bruker);
    }
}
