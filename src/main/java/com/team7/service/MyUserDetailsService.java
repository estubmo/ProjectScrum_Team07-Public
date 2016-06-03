/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.service;

import com.team7.Domain.Bruker;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Eirik
 */
public class MyUserDetailsService implements UserDetailsService {
    
    List<Bruker> brukerListe = DatabaseCom.hentAlleBrukere();

    @Override
    public UserDetails loadUserByUsername(String epost) throws UsernameNotFoundException {
        Bruker bruker = DatabaseCom.hentBruker(epost);
        List auth = new ArrayList();
        auth.add(new SimpleGrantedAuthority(bruker.getRettighet()));

        UserDetails ud = new User(epost, bruker.getPassord(), true, true, true, true, auth);
        return ud;
    }

}
