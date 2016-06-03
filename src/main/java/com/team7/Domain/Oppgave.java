/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.Domain;

/**
 *
 * @author SimenLaptop
 */
public class Oppgave {
    private int oppgaveid;
    private String oppgaveTekst;
    private String oppgaveBeskrivelse;

    public Oppgave(){
        
    }
    public Oppgave(int oppgaveid, String oppgaveTekst, String oppgaveBeskrivelse) {
        this.oppgaveid = oppgaveid;
        this.oppgaveTekst = oppgaveTekst;
        this.oppgaveBeskrivelse = oppgaveBeskrivelse;
    }

    public int getOppgaveid() {
        return oppgaveid;
    }

    public void setOppgaveid(int oppgaveid) {
        this.oppgaveid = oppgaveid;
    }

    public String getOppgaveTekst() {
        return oppgaveTekst;
    }

    public void setOppgaveTekst(String oppgaveTekst) {
        this.oppgaveTekst = oppgaveTekst;
    }

    public String getOppgaveBeskrivelse() {
        return oppgaveBeskrivelse;
    }

    public void setOppgaveBeskrivelse(String oppgaveBeskrivelse) {
        this.oppgaveBeskrivelse = oppgaveBeskrivelse;
    }
    @Override
    public String toString(){
        String out = "";
        out += oppgaveid + "\n";
        out += oppgaveBeskrivelse + "\n\n";
        out += oppgaveTekst;
        
        return out;
    }
    
}
