/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.service;

import com.team7.Domain.Fasit;
import com.team7.Domain.Oppgave;
import com.team7.Domain.Stakk;
import com.team7.Domain.Svar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author HavardTollefsen
 */
@Service
public class SpillBehandler {

    Fasit fasit;
    Oppgave oppgave;
    Svar svar;
    
    public SpillBehandler() {
    }

    /*

    Metoder for jobbing på database
    */
    
    public void slettAlleSvar() {
        DatabaseCom.slettAlleSvar();
    }
    

    public Oppgave hentOppgave(int oppgaveID) {
        return DatabaseCom.hentOppgave(oppgaveID);
    }

    public static List<Oppgave> hentAlleInteraktiveOppgaver() {
        List<Oppgave> oppgavene = DatabaseCom.hentAlleInteraktiveOppgaver();
        for (Oppgave oppgaven : oppgavene) {
            oppgaven.setOppgaveid(oppgaven.getOppgaveid() * (-1));
        }
        int storst = 0;
        List<Oppgave> ferdigListe = new ArrayList<>();
        int lengde = oppgavene.size();
        for (int j = 0; j < lengde; j++) {
            for (int i = 0; i < oppgavene.size(); i++) {
                if (oppgavene.get(storst).getOppgaveid() > oppgavene.get(i).getOppgaveid()) {
                    storst = i;
                }
            }
            ferdigListe.add(oppgavene.get(storst));
            oppgavene.remove(oppgavene.get(storst));
            storst = 0;
        }
        return ferdigListe;
    }

    public void registrerInteraktivScore(String mail, int tid, int oppgave) {
        int score = ((100 - (tid / 1000))/10);
        if(score <= 0){
            score = 0;
        }
        int oppgavenrNegativ = oppgave * -1;
        Svar gammeltSvar = hentSvar(oppgavenrNegativ,mail);
        if (gammeltSvar.getScore() < score) {
            HighscoreBehandler high = new HighscoreBehandler();
            svar = new Svar();
            svar.setEpost(mail);
            svar.setGodkjent(true);
            svar.setScore(score);
            svar.setSvarTekst("");
            svar.setOppgaveid(oppgavenrNegativ);
            DatabaseCom.leggTilSvar(svar);
            high.oppdaterHighscore(mail);
        }
    }

    public boolean sjekkOmSvarErGodkjent(String mail, int oppgaveid) {
        if (hentSvar((oppgaveid), mail).isGodkjent()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sjekkOmInteraktivtSvarErGodkjent(String mail, int oppgaveid) {
        if (hentSvar((oppgaveid * -1), mail).isGodkjent()) {
            return true;
        } else {
            return false;
        }
    }

    private static Fasit hentFasit(int fasitID) {
        return DatabaseCom.hentFasit(fasitID);
    }
   
    public int finnMaxGodkjentSvar(String epost) {
        int maxz = 1;
        ArrayList<Svar> list = new ArrayList<Svar>();
        list = (ArrayList<Svar>) DatabaseCom.hentAlleSvarBruker(epost);
        if (list == null){
            return maxz;
        }
        Collections.sort(list);
        for (Svar list1 : list) {
            if (list1.isGodkjent()) {
                maxz=list1.getOppgaveid()+1;
                System.out.println("Spillbehandler, finnMaxGodkjentSvar sout:"+list1.getOppgaveid());
            }
        }
        if(maxz>10){maxz=10;}
        return maxz;
    }

    public boolean sjekkGodkjent(String epost){
        List<Svar> list = DatabaseCom.hentAlleSvarBruker(epost);
        int antGodkjente=0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getOppgaveid()>0&&list.get(i).isGodkjent()){
                antGodkjente++;
            }
        }
        System.out.println(antGodkjente);
        if(antGodkjente>9){
            return true;
        }
        return false;
    }
    

    public Svar hentSvar(int oppgaveID, String epost) {
        Svar svar = new Svar();
        if (DatabaseCom.hentSvar(oppgaveID, epost) != null) {
            svar = DatabaseCom.hentSvar(oppgaveID, epost);
            svar.setSvarTekst(fiksQuote(svar.getSvarTekst()));
            return svar;
        }
        svar.setSvarTekst(hentOppgave(oppgaveID).getOppgaveTekst());
        return svar;
    }

    
    public void resetSvar(int oppgaveID, String epost){
        if(DatabaseCom.hentSvar(oppgaveID, epost)!=null){
            setSvar(oppgaveID,DatabaseCom.hentOppgave(oppgaveID).getOppgaveTekst(),epost);
        }
    }
    
    public Fasit finnFasit(int oppgaveid){
        return DatabaseCom.hentFasit(oppgaveid);
    }

    public int setSvar(int oppgaveID, String svarString, String epost) {
        if (svarString.trim().equals("")) {
            return 0;
        }
        if (skjekkHtmlBrackets(svarString) != 0 || skjekkKrollBrackets(svarString) != 0) {
            return -1;
        }
        svar = new Svar();
        svar.setOppgaveid(oppgaveID);
        svar.setSvarTekst(fiksQuote(svarString));
        svar.setEpost(epost);
        svarGodkjent(svarString, oppgaveID);
        if (DatabaseCom.hentSvar(oppgaveID, epost) != null) {
            //TODO: helt likt svar sletter begge svara fra databasen, men bare av og til?
            DatabaseCom.endreSvar(svar);
            return 1;
        } else {
            DatabaseCom.leggTilSvar(svar);
            return 1;
        }
    }

    /*
     Logikkmetoder
     */
    public int hentPoeng(String epost) {
        int antRiktige = 0;
        for (int oppgaver = 0; oppgaver < 10; oppgaver++) {
            if (hentSvar(oppgaver, epost).getSvarTekst().trim().equals(hentFasit(oppgaver).getFasit().trim())) {
                antRiktige++;
            }
        }
        return antRiktige;
    }


    public static boolean svarGodkjent(int oppgaveID, String svar) {
        return hentFasit(oppgaveID).getFasit().equals(svar);
    }

    public String[] delInteraktivTekst(String text) {
        // String[0] = Kode som ikke skal kunne endres
        // String[1] = Kode som skal kunne endres
        if (text.contains("|")) {
            return text.split("[|]");
        } else {
            String[] ikkeFunnet = new String[2];
            ikkeFunnet[0] = text;
            ikkeFunnet[1] = "Det var et problem når man lastet oppgaven. Ta kontakt med systemadministrator.";
            return ikkeFunnet;
        }
    }
    
    public void svarGodkjent(String svarString, int oppgaveid) {
        int poeng = 0;
        Fasit f = hentFasit(oppgaveid);
        String tagger = f.getFasit();
        String[] tags = tagger.split(",");
        String[] s = svarString.split("(?=<)|(?<=>)"); //splitter svaret så tags står alene
        Stakk stk = new Stakk(s.length); //Stakk med godkjente tags
        Stakk stk2 = new Stakk(s.length); //Stakk med andre tags
        
        
        for (String s1 : s) { //går gjennom hver split i svaret
            if(s1.length()>0){
                boolean inni = false; //brukes for å ikke legge svarsplit i stakk2 mer enn en gang
                for (String tag : tags) { //går gjennom hver tag
                    if (s1.equals(tag) &&!inni) { //finner ut om svarsplitten er en godkjent tag
                        inni=true;
                        if (!stk.tom() && s1.charAt(1) == '/') { //finner ut om det er en avslutter tag
                            if (stk.sjekkStakk().toString().substring(1, stk.sjekkStakk().toString().length()-1).contains(s1.substring(2, s1.length()-1))) {
                                //ser om avsluttertaggen er passer til forgje tag
                                stk.pop();
                                poeng++; //poeng fordi det er blitt fullført ein tag som er relevant for oppgava
                                String taggen = s1.substring(2,s1.length()-1);
                                int t=0;
                                for (int i = 0; i < tags.length; i++) {
                                    if(tags[i].contains(taggen)){
                                            tags[i]="@@@@@@@";
                                            t++;
                                            if(t>1){
                                                break;
                                            }
                                    }else if(tags[i].substring(1,tags[i].length()-1).contains(taggen)){
                                        tags[i]="@@@@@@@";
                                            t++;
                                            if(t>1){
                                                break;
                                            }
                                    }
                                }
                            }
                        } else {stk.push(s1);} //legger taggen i stakk1
                    }
                    else if (s1.charAt(0) == '<' && s1.charAt(s1.length()-1) == '>' && !tagger.contains(s1) && !inni) { 
                        //ser om svarsplitten er en tag som ikke har med oppgaven og om den er blitt lagt inn før
                        inni = true; //boolean som passer på at iffen kun skjer max en gang per svarsplit
                        if (s1.charAt(1) == '/') {
                            if (stk2.sjekkStakk().toString().substring(1, stk2.sjekkStakk().toString().length()-1).contains(s1.substring(2, s1.length()-1))) {
                                stk2.pop();
                            }
                        } else {stk2.push(s1);} //legger taggen i stakk2
                    }

                }
            }
        }
        this.svar.setScore(poeng); //antall godkjente taggs som er fullført
        boolean riktig = f.getMinpoeng() <= poeng;
        this.svar.setGodkjent(riktig); //Oppgaven er godkjent dersom det er nok poeng
    }
    
    
    
    /*
    Hjelpemetoder for kompilering
    */
    
    
    
    private String fiksQuote(String tekst) {
        String ret = "";
        for (int i = 0; i < tekst.length(); i++) {
            switch (tekst.charAt(i)) {
                case '"':
                    ret += "&quot;";
                    break;
                case '<':
                    ret += "&lt;";
                    break;
                case '>':
                    ret += "&gt;";
                    break;
                /*case '$':
                    ret += "&#36;";
                    break;*/
                default:
                    ret += tekst.charAt(i);
                    break;
            }
        }
        return ret;
    }

    private static int skjekkHtmlBrackets(String tekst) {
        int teller = 0;
        for (int t = 0; t < tekst.length(); t++) {
            switch (tekst.charAt(t)) {
                case '<':
                    teller++;
                    break;
                case '>':
                    teller--;
                    break;
                
                    
            }
            if (teller < 0) {
                return teller;
            }
        }
        return teller;
    }

    private static int skjekkKrollBrackets(String tekst) {
        int teller = 0;
        for (int t = 0; t < tekst.length(); t++) {
            switch (tekst.charAt(t)) {
                case '{':
                    teller++;
                    break;
                case '}':
                    teller--;
                    break;
            }
            if (teller < 0) {
                return teller;
            }
        }
        return teller;
    }
}
