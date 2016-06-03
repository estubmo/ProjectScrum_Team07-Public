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
public class Highscore {
    String epost;

    public Highscore(String epost) {
        this.epost = epost;
    }
    public Highscore(){
        
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }
    
}
