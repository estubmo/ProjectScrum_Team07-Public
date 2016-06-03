package com.team7.Domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivar
 */
public class Svar implements Comparable{
    int oppgaveid, score;
    String epost, svarTekst;
    boolean godkjent;
    
    public Svar(){};
    public Svar(int oppgaveid, int score, String epost, String svarTekst, boolean godkjent) {
        this.oppgaveid = oppgaveid;
        this.score = score;
        this.epost = epost;
        this.svarTekst = svarTekst;
        this.godkjent = godkjent;
    }

    public int getOppgaveid() {
        return oppgaveid;
    }

    public void setOppgaveid(int oppgaveid) {
        this.oppgaveid = oppgaveid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getSvarTekst() {
        return svarTekst;
    }

    public void setSvarTekst(String svarTekst) {
        this.svarTekst = svarTekst;
    }

    public boolean isGodkjent() {
        return godkjent;
    }

    public void setGodkjent(boolean godkjent) {
        this.godkjent = godkjent;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Svar){
            Svar that = (Svar) o;
            return this.getOppgaveid() - that.getOppgaveid();
        }
        return -1;
    }
    
    
    
}
