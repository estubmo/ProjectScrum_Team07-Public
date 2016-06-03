package com.team7.Mail;

import com.team7.Domain.Bruker;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendMail {

    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    /*
     public void sendMail(String dear, String content) {

     SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
		
     message.setText(String.format(
     simpleMailMessage.getText(), dear, content));

     mailSender.send(message);
		
     }
     */

    public void sendEpost(String passord, Bruker bruker) {

        SimpleMailMessage melding = new SimpleMailMessage(simpleMailMessage);

        String sentMelding = "Hei, " + bruker.getFornavn() + " " + bruker.getEtternavn() + ". "
                + "Her er ditt genererte passord: " + passord
                + "\n1. Vennligst logg inn med generert passord."
                + "\n2. Du vil så få mulighet til å endre passord. Lykke til!"
                + "\nMed vennlig hilsen Team 7";


        melding.setTo(bruker.getEpost());
        melding.setSubject("Registrering vellykket - PST");
        melding.setText(sentMelding);
        System.out.println("Sender mail til: " + bruker.getEpost());
        mailSender.send(melding);
    }
}
