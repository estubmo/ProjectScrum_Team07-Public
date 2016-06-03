/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.controller;

import com.team7.Domain.Bruker;
import com.team7.service.RegistrerBruker;
import java.util.ArrayList;
import java.util.List;
import static javafx.beans.binding.Bindings.when;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 *
 * @author Eirik
 */
public class LoginKontrollerTest {

    public LoginKontrollerTest() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDefaultPage() throws Exception {
        LoginKontroller instance = new LoginKontroller();
        String expResult = "redirect:/main/highscore";
        String wrongResult = "/main/highscore";
        String result = instance.defaultPage();
        try {
            assertEquals(expResult, result);
            assertNotSame(wrongResult, result);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testLogin() {
        String error = "error";
        String noError = null;
        String logout = "logout";
        String noLogout = null;

        Model model = new ExtendedModelMap();
        LoginKontroller instance = new LoginKontroller();
        List auth = new ArrayList();
        SimpleGrantedAuthority sga = new SimpleGrantedAuthority("[ROLE_ANONYMOUS]");
        auth.add(sga);
        System.out.println(auth.get(0));
        User user = new User("testbrukerepost", "testbrukerpassord", true, true, true, true, auth);
        Authentication authent = new UsernamePasswordAuthenticationToken(user, model);
        SecurityContextHolder.getContext().setAuthentication(authent);

        String resultIfNone = instance.login(noError, noLogout, model);
        String resultIfLogout = instance.login(noError, logout, model);

        String expResultIfNone = "redirect:/main/highscore";
        String expResultIfLogout = "login";

        assertEquals(resultIfNone, expResultIfNone);
        assertEquals(resultIfLogout, expResultIfLogout);

        //Error ikke testet, da SecurityContextHolder er vanskelig å mocke.
    }

}
