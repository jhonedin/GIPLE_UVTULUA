/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 * 
 * @author Usuario
 */
public class NotificacionEmail {
    
    public static String Username = "laboratorio.electronica.tulua@correounivalle.edu.co";
    public static String PassWord = "Contrasena";
    String Mensage = ""; 
    String To = ""; 
    String Subject = "";
    
    public NotificacionEmail(Usuario user){
        To = user.getEmail();
    }
    
    public void setMensage(String m){
        Mensage = m;
    }
    
    public void setCorreoDestino(String correo){
        To = correo;
    }
    
    public void setAsunto(String asunto){
        Subject = asunto;
    }
    
    public void EnviarEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);

            Transport.send(message);
            System.out.println("Su mensaje ha sido enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    } 
    
}
