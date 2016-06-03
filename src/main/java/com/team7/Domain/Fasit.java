package com.team7.Domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SimenLaptop
 */
public class Fasit {
    private int oppgaveid;
    private String fasit;
    private int minpoeng;

    
    public Fasit(){};
    public Fasit(int oppgaveid, String fasit){
        this.fasit = fasit;
        this.oppgaveid = oppgaveid;
    }
    
    public int getMinpoeng() {
        return minpoeng;
    }

    public void setMinpoeng(int minPoeng) {
        this.minpoeng = minPoeng;
    }

    public int getOppgaveid() {
        return oppgaveid;
    }

    public String getFasit() {
        return fasit;
    }

    public void setOppgaveid(int oppgaveid) {
        this.oppgaveid = oppgaveid;
    }

    public void setFasit(String fasit) {
        this.fasit = fasit;
    }
    @Override
    public String toString(){
        String out = "";
        out += oppgaveid + "\n";
        out += fasit;
        return out;
    }
}
