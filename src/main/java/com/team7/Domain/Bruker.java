/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team7.Domain;

/**
 *
 * @author Ivar
 */
public class Bruker implements Comparable{
    int bruker_id;
    
    String epost, fornavn, etternavn, passord, rettighet;

    public String getRettighet() {
        return rettighet;
    }

    public void setRettighet(String rettighet) {
        this.rettighet = rettighet;
    }
    int antGodkjente, score;
    

    public int getBruker_id() {
        return bruker_id;
    }

    public void setBruker_id(int bruker_id) {
        this.bruker_id = bruker_id;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public int getAntGodkjente() {
        return antGodkjente;
    }

    public void setAntGodkjente(int antGodkjente) {
        this.antGodkjente = antGodkjente;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    //Brukes til å sorte etter etternavn
    @Override
    public int compareTo(Object o) {
        Bruker that = (Bruker) o;
        char first = that.getEtternavn().charAt(0);
        return this.getEtternavn().charAt(0) - first;
    }
    
    
}
