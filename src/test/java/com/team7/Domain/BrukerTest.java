/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.Domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HavardTollefsen
 */
public class BrukerTest {
    
    public BrukerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRettighet method, of class Bruker.
     */
    @Test
    public void testGetRettighet() {
        System.out.println("getRettighet");
        try {
            Bruker instance = new Bruker();
        instance.setRettighet("admin");
        String expResult = "admin";
        String result = instance.getRettighet();
        assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setRettighet method, of class Bruker.
     */
    @Test
    public void testSetRettighet() {
        System.out.println("setRettighet");
        try {
            String rettighet = "student";
            Bruker instance = new Bruker();
            instance.setRettighet(rettighet);
            String expResult = "student";
            String result = instance.getRettighet();
            assertEquals(expResult, result);
        } catch (Exception e) {
        fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getBruker_id method, of class Bruker.
     */
    @Test
    public void testGetBruker_id() {
        System.out.println("getBruker_id");
        try {
            int id = 1;
            Bruker instance = new Bruker();
            instance.setBruker_id(id);
            int expResult = 1;
            int result = instance.getBruker_id();
            assertEquals(expResult, result);
        } catch (Exception e) {
        fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setBruker_id method, of class Bruker.
     */
    @Test
    public void testSetBruker_id() {
        System.out.println("setBruker_id");
        try {
            int bruker_id = 0;
            Bruker instance = new Bruker();
            instance.setBruker_id(bruker_id);
            int expResult = 0;
            int result = instance.getBruker_id();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getEpost method, of class Bruker.
     */
    @Test
    public void testGetEpost() {
        System.out.println("getEpost");
        try {
            Bruker instance = new Bruker();
            String expResult = "test@test.com";
            instance.setEpost("test@test.com");
            String result = instance.getEpost();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setEpost method, of class Bruker.
     */
    @Test
    public void testSetEpost() {
        System.out.println("setEpost");
        try {
            String epost = "test@test.com";
            Bruker instance = new Bruker();
            instance.setEpost(epost);
            String expResult = "test@test.com";
            String result = instance.getEpost();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getFornavn method, of class Bruker.
     */
    @Test
    public void testGetFornavn() {
        System.out.println("getFornavn");
        try {
            Bruker instance = new Bruker();
            String expResult = "Test";
            instance.setFornavn("Test");
            String result = instance.getFornavn();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setFornavn method, of class Bruker.
     */
    @Test
    public void testSetFornavn() {
        System.out.println("setFornavn");
        try {
            String fornavn = "Test";
            Bruker instance = new Bruker();
            instance.setFornavn(fornavn);
            String expResult = "Test";
            String result = instance.getFornavn();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getEtternavn method, of class Bruker.
     */
    @Test
    public void testGetEtternavn() {
        System.out.println("getEtternavn");
        try {
            Bruker instance = new Bruker();
            String expResult = "Test";
            instance.setEtternavn("Test");
            String result = instance.getEtternavn();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setEtternavn method, of class Bruker.
     */
    @Test
    public void testSetEtternavn() {
        System.out.println("setEtternavn");
        try {
            String etternavn = "Test";
            Bruker instance = new Bruker();
            instance.setEtternavn(etternavn);
            String expResult = "Test";
            String result = instance.getEtternavn();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getPassord method, of class Bruker.
     */
    @Test
    public void testGetPassord() {
        System.out.println("getPassord");
        try {
            Bruker instance = new Bruker();
            String expResult = "passord";
            instance.setPassord("passord");
            String result = instance.getPassord();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setPassord method, of class Bruker.
     */
    @Test
    public void testSetPassord() {
        System.out.println("setPassord");
        try {
            String passord = "passord";
            Bruker instance = new Bruker();
            instance.setPassord(passord);
            String expResult = "passord";
            String result = instance.getPassord();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getAntGodkjente method, of class Bruker.
     */
    @Test
    public void testGetAntGodkjente() {
        System.out.println("getAntGodkjente");
        try {
            Bruker instance = new Bruker();
            int expResult = 0;
            instance.setAntGodkjente(0);
            int result = instance.getAntGodkjente();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setAntGodkjente method, of class Bruker.
     */
    @Test
    public void testSetAntGodkjente() {
        System.out.println("setAntGodkjente");
        try {
            int antGodkjente = 0;
            Bruker instance = new Bruker();
            instance.setAntGodkjente(antGodkjente);
            int expResult = 0;
            int result = instance.getAntGodkjente();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getScore method, of class Bruker.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        try {
            Bruker instance = new Bruker();
            int expResult = 0;
            instance.setScore(0);
            int result = instance.getScore();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setScore method, of class Bruker.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        try {
            int score = 0;
            Bruker instance = new Bruker();
            instance.setScore(score);
            int expResult = 0;
            int result = instance.getScore();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of compareTo method, of class Bruker.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        try {
            Bruker o = new Bruker();
            o.setEtternavn("a");
            Bruker instance = new Bruker();
            instance.setEtternavn("b");
            int expResult = 1;
            int result = instance.compareTo(o);
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }
    
}
