/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team7.service;

/**
 *
 * @author Ivar
 */
import com.team7.Domain.Bruker;
import com.team7.Domain.Fasit;
import com.team7.Domain.Highscore;
import com.team7.Domain.Oppgave;
import com.team7.Domain.Svar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Ivar
 */
public class DatabaseCom {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
//          Configuration configuration = new Configuration();
            Configuration configuration = new Configuration().configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException he) {
            System.err.println("Error creating Session: " + he);
            throw new ExceptionInInitializerError(he);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //Bruker
    public static List<Bruker> hentAlleBrukere() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Bruker> l = new ArrayList<Bruker>();
        try {
            tx = session.beginTransaction();
            List brukere = session.createQuery("FROM Bruker").list();
            for (Iterator iterator
                    = brukere.iterator(); iterator.hasNext();) {
                l.add((Bruker) iterator.next());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return l;
    }
    
    public static void registrerInteraktivScore(String email, int score,int oppgave){
        // TODO: MASSE
        
    }

    public static void nyBruker(Bruker bruker) {
        if (bruker != null) {
            Session session = getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(bruker);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        }

    }

    public static Bruker hentBruker(String epost) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Bruker> svar = new ArrayList<Bruker>();
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Bruker.class);
            cr.add(Restrictions.like("epost", epost));
            List<Bruker> temp = cr.list();
            for (Iterator iterator
                    = temp.iterator(); iterator.hasNext();) {
                svar.add((Bruker) iterator.next());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        if (!svar.isEmpty()) {
            return svar.get(0);
        }
        return null;
    }
    
    public static void setRettighetBruker(String epost, String rettighet) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Bruker ut = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Bruker.class);
            cr.add(Restrictions.like("epost", epost));
            List<Bruker> brukere = cr.list();
            ut = brukere.get(0);
            if (ut != null) {
                ut.setRettighet(rettighet);
                session.update(ut);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void endrePassord(String epost, String passord) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Bruker ut = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Bruker.class);
            cr.add(Restrictions.like("epost", epost));
            List<Bruker> brukere = cr.list();
            ut = brukere.get(0);
            if (ut != null) {
                ut.setPassord(passord);
                session.update(ut);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //Oppgave
    public static void leggTilOppgave(Oppgave oppgave) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(oppgave);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Oppgave hentOppgave(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Oppgave opp = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Oppgave.class);
            cr.add(Restrictions.like("oppgaveid", id));
            List<Oppgave> temp = cr.list();

            tx.commit();
            if (!temp.isEmpty()) {
                opp = temp.get(0);
            }

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return opp;
    }

    //Svar
    public static void slettAlleSvar() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Svar");
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public static void leggTilSvar(Svar svar) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(svar);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Svar hentSvar(int id, String epost) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Svar svar = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Svar.class);
            cr.add(Restrictions.like("oppgaveid", id));
            cr.add(Restrictions.like("epost", epost));
            List<Svar> temp = cr.list();

            tx.commit();
            if (!temp.isEmpty()) {
                svar = temp.get(0);
            }

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return svar;
    }
    /*
     ADMIN***
     Endre svar på deloppgave. Score og godkjent kan endres.
     */

    public static Svar endreSvar(Svar nyttsvar) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Svar svar = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Svar.class);
            cr.add(Restrictions.like("oppgaveid", nyttsvar.getOppgaveid()));
            cr.add(Restrictions.like("epost", nyttsvar.getEpost()));
            List<Svar> temp = cr.list();
            svar = temp.get(0);
            if (svar != null) {
                session.delete(svar);
                tx.commit();
                tx = session.beginTransaction();
                if (!(nyttsvar.getSvarTekst().equalsIgnoreCase(svar.getSvarTekst()))) {
                    svar.setSvarTekst(nyttsvar.getSvarTekst());
                }
                if (!(nyttsvar.isGodkjent() == svar.isGodkjent())) {
                    svar.setGodkjent(nyttsvar.isGodkjent());
                }
                if (!(nyttsvar.getScore() == svar.getScore())) {
                    svar.setScore(nyttsvar.getScore());
                }
                session.save(svar);
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return svar;
    }

    public static List<Svar> hentAlleSvarBruker(String epost) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Svar> svar = new ArrayList<Svar>();
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Svar.class);
            cr.add(Restrictions.like("epost", epost));
            List<Svar> temp = cr.list();
            for (Iterator iterator
                    = temp.iterator(); iterator.hasNext();) {
                svar.add((Svar) iterator.next());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return svar;
    }

    //Fasit
    public static void leggTilFasit(Fasit fasit) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(fasit);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    

    public static List<Oppgave> hentAlleInteraktiveOppgaver(){
        // TODO: Hent alle oppgaver fra databsen med id < 0. JA MINDRE ENN NULL. DET ER RIKTIG.
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        boolean ikkeTom = false;
        List<Oppgave> l= new ArrayList<Oppgave>();
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Oppgave.class);
            cr.add(Restrictions.between("oppgaveid", -500, 0));
 
            List<Oppgave> temp = cr.list();
               for (Iterator iterator
                    = temp.iterator(); iterator.hasNext();) {
                l.add((Oppgave) iterator.next());
            }

            tx.commit();
            if (!temp.isEmpty()) {
                ikkeTom = true;
            }

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return l;
    }

    public static Fasit hentFasit(int id) {

        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Fasit fasit = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Fasit.class);
            cr.add(Restrictions.like("oppgaveid", id));
            List<Fasit> temp = cr.list();

            tx.commit();
            if (!temp.isEmpty()) {
                fasit = temp.get(0);
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return fasit;
    }

    //Highscore
    public static List<Highscore> hentHighscoreListe() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Highscore> l = new ArrayList<Highscore>();
        try {
            tx = session.beginTransaction();
            List brukere = session.createQuery("FROM Highscore").list();
            for (Iterator iterator
                    = brukere.iterator(); iterator.hasNext();) {
                l.add((Highscore) iterator.next());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        if (l.isEmpty()) {
            return null;
        }
        return l;
    }

    public static void pushHighscore(String epost) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            System.out.println("her skjer det");
            tx = session.beginTransaction();
            session.save(new Highscore(epost));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /*
     ADMIN*
     Brukes ved push av ny highscore dersom liste = 10, eller ved sletting av score.
     */

    public static void popHighscore(String epost) {
        if (epost != null) {
            Session session = getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(new Highscore(epost));
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        }

    }

    /*
     ADMIN**
     Reset/tøm highscore
     */
    public static void resetHighscore() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Highscore");
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}




