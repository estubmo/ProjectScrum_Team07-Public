/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.service;

import com.team7.Domain.Bruker;
import com.team7.Domain.Svar;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimenLaptop
 */
@Service
public class GodkjenningBehandler {
    public List<Bruker> hentAlleBrukere(){
        List<Bruker> brukere =  DatabaseCom.hentAlleBrukere();
        brukere = sorterOgSlettDuplicates(brukere);
        
        return brukere;
    }
    public List<Svar> hentAlleSvarBruker(String epost){
        List<Svar> list = DatabaseCom.hentAlleSvarBruker(epost);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getOppgaveid()<1){
                int nyid=(abs(list.get(i).getOppgaveid())+10);
                list.get(i).setOppgaveid(nyid);
            }
        }
        Collections.sort(list);
        return list;
    }
    private List<Bruker> sorterOgSlettDuplicates(List<Bruker> brukere){
        List<Bruker> ut = new ArrayList<>();
        for(Bruker bruker: brukere){
            boolean funnet = false;
            for(Bruker bruker2: ut){
                if(bruker.getEpost().equals(bruker2.getEpost())){
                    funnet = true;
                }
            }
            if(!funnet){
                ut.add(bruker);
            }
        }
        Collections.sort(ut);
        return ut;
    }
    
    public Bruker settRettighet(String epost, String rettighet) {
        DatabaseCom.setRettighetBruker(epost, rettighet);
        return DatabaseCom.hentBruker(epost);
    }
    
    public Bruker hentBruker(String epost) {
        return DatabaseCom.hentBruker(epost);
    }

}
