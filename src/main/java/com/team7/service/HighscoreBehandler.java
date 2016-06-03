/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.service;

import com.team7.Domain.Bruker;
import com.team7.Domain.Highscore;
import com.team7.Domain.Svar;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimenLaptop
 */
@Service
public class HighscoreBehandler {

    public List<Bruker> hentHighscore() {

        List<Highscore> highscoreListe = DatabaseCom.hentHighscoreListe();
        if (highscoreListe == null || highscoreListe.isEmpty()) {
            return null;
        }

        List<Bruker> brukerListe = new ArrayList<>();
        for (Highscore highscore : highscoreListe) {
            Bruker bruker = DatabaseCom.hentBruker(highscore.getEpost());
            if(bruker == null){
                bruker = new Bruker();
                bruker.setFornavn(highscore.getEpost());
                bruker.setEpost(highscore.getEpost());
            }
            List<Svar> listen = DatabaseCom.hentAlleSvarBruker(highscore.getEpost());
            int score = 0;
            if (listen == null) {
                bruker.setScore(score);
                brukerListe.add(bruker);

            } else {
                for (int i = 0; i < listen.size(); i++) {
                    score += listen.get(i).getScore();

                }
                bruker.setScore(score);
                brukerListe.add(bruker);

            }

        }

        brukerListe = sorterHighscore(brukerListe);
        return brukerListe;
    }

    public boolean oppdaterHighscore(String epost) {
        List<Svar> listen = DatabaseCom.hentAlleSvarBruker(epost); // svarene til valgt bruker
        List<Bruker> highscoreListe = hentHighscore();

        if (highscoreListe == null || highscoreListe.isEmpty()) {
            // Det er ingen highscore
            DatabaseCom.pushHighscore(epost);
            return true;
        } else {
            // Det er noen highscores
            int scoreBruker = 0;
            for (int i = 0; i < listen.size(); i++) {
                scoreBruker += listen.get(i).getScore();
            }
            if (highscoreListe.size() < 10) {
                // om det er plass i highscore
                DatabaseCom.pushHighscore(epost);
            } else {
                // om det er fult i highscore
                if(scoreBruker > highscoreListe.get(highscoreListe.size() -1).getScore()){
                    DatabaseCom.popHighscore(highscoreListe.get(highscoreListe.size()-1).getEpost());
                    DatabaseCom.pushHighscore(epost);
                }
                

            }

        }
        return true;
    }

    private List<Bruker> sorterHighscore(List<Bruker> highscoreListe) {
        int storst = 0;
        List<Bruker> ferdigListe = new ArrayList<Bruker>();
        int lengde = highscoreListe.size();
        for (int j = 0; j < lengde; j++) {
            for (int i = 0; i < highscoreListe.size(); i++) {
                if (highscoreListe.get(storst).getScore() < highscoreListe.get(i).getScore()) {
                    storst = i;
                }
            }
            ferdigListe.add(highscoreListe.get(storst));
            highscoreListe.remove(highscoreListe.get(storst));
            storst = 0;
        }
        return ferdigListe;

    }
}
