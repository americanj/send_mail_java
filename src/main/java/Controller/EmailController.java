package Controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailController {
    private String email;
    private String senha;

    private String cabecalho;
    private String destinatarios;
    private String remetente;
    private String assunto;


    public void enviar(Boolean envio) throws MessagingException, UnsupportedEncodingException {
        Properties properties = new Properties();
        //ssl
        properties.put("mail.smtp.ssl.trust", "*");
        //autorização
        properties.put("mail.smtp.auth", "true");
        //autenticação
        properties.put("mail.smtp.starttls", "true");
        //servidor email
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //port
        properties.put("mail.smtp.port", "465");
        // especifica a porta a ser conectada pelo socket
        properties.put("mail.smtp.socketFactory.port", "465");
        // class socket de conexão com protocol smtp
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, senha);
            }
        });

        Address[] usuarios = InternetAddress.parse(destinatarios);
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(email, remetente));
        message.setRecipients(Message.RecipientType.TO, usuarios);
        message.setSubject(cabecalho);

        if(envio == true){
            message.setContent(assunto, "text/html; charset=UTF-8");
            Transport.send(message);
            System.out.println("\u001B[36mEmail Enviado Com Sucesso!");
        } else {
            System.out.println("\u001B[31mEmail Não Enviado!");
            //message.setText(assunto);
        }


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
